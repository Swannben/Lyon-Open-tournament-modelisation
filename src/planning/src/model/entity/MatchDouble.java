/***********************************************************************
 * Module:  MatchDouble.java
 * Author:  swann
 * Purpose: Defines the Class MatchDouble
 ***********************************************************************/

package model.entity;

import model.database.DatabaseConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class MatchDouble extends Match {
    private java.util.List<Equipe> equipes;
    private Arbitre arbitreChaise;
    int id;
    private java.util.List<EquipeRamassage> equipesRamassage;
    
    private static List<MatchDouble> list = new LinkedList<>(); 

    
    public MatchDouble(){
        super();
    }
    

    public MatchDouble(int id, Creneau creneau, List<Set> score, Arbitre arbitreChaise, java.util.List<Arbitre> arbitresLigne, 
            java.util.List<EquipeRamassage> equipesRamassage, List<Equipe> equipes) {
        super(id, creneau, score, arbitresLigne, equipesRamassage);
        
        setArbitreChaise(arbitreChaise);

        if (equipes == null)
            equipes = new ArrayList(2);
        this.equipes = equipes;
    }
    
    @Override
    public MatchDouble getMatchA() {
        return (MatchDouble) matchA;
    }
    
    @Override
    public MatchDouble getMatchB() {
        return (MatchDouble) matchB;
    }
    
    public List<Joueur> getJoueurs(){
        ArrayList<Joueur> joueurs= new ArrayList<>(4);
        joueurs.addAll(equipes.get(1).getJoueurs());
        joueurs.addAll(equipes.get(0).getJoueurs());
        return joueurs;
    }
    
    /** @pdGenerated default getter */
    public java.util.List<Equipe> getEquipes() {
        if (equipes == null)
            equipes = new java.util.ArrayList<Equipe>();
        return equipes;
    }
   
    /** @pdGenerated default iterator getter */
    public java.util.Iterator getIteratorEquipes() {
        if (equipes == null)
            equipes = new java.util.ArrayList<Equipe>();
        return equipes.iterator();
    }
   
    /** @pdGenerated default setter
      * @param newEquipe */
    public void setEquipes(java.util.List<Equipe> newEquipes) {
        removeAllEquipes();
        for (java.util.Iterator iter = newEquipes.iterator(); iter.hasNext();)
            addEquipe((Equipe)iter.next());
    }
   
    /** @pdGenerated default add
      * @param newEquipe */
    public void addEquipe(Equipe newEquipe) {
        if (newEquipe == null)
           return;
        if (this.equipes == null)
           this.equipes = new java.util.ArrayList<Equipe>();
        if (!this.equipes.contains(newEquipe)) {
           this.equipes.add(newEquipe);     
        }
    }
   
    /** @pdGenerated default remove
      * @param oldEquipe */
    public void removeEquipe(Equipe oldEquipe) {
        if (oldEquipe == null)
            return;
        if (this.equipes != null) {
            if (this.equipes.contains(oldEquipe)) {
                this.equipes.remove(oldEquipe);
            }
        }
    }
   
    /** @pdGenerated default removeAll */
    public void removeAllEquipes() {
        if (equipes != null) {
            Equipe oldEquipe;
            for (java.util.Iterator iter = getIteratorEquipes(); iter.hasNext();) {
                oldEquipe = (Equipe)iter.next();
                iter.remove();
            }
        }
    }
   
    
    /** @pdGenerated default parent setter
      * @param newArbitre */
    @Override
    public void setArbitreChaise(Arbitre newArbitre) {
        if (this.arbitreChaise == null || !this.arbitreChaise.equals(newArbitre)) {
            if (this.arbitreChaise != null) {
                Arbitre oldArbitre = this.arbitreChaise;
                this.arbitreChaise = null;
                oldArbitre.subMatchDouble();
            }
            if (newArbitre != null) {
                this.arbitreChaise = newArbitre;
                this.arbitreChaise.addMatchDouble();
            }
        }
    }

    @Override
    public void assignerArbitres() {
        List<Arbitre> arbitres=Arbitre.getList();
        int i, c = 1;
        //TODO ajouter tous le traitement de l'emploi du temps de l'arbitre.
        arbitreChaise = arbitres.get(new Random().nextInt(arbitres.size()));
        
        while (c>0){
            c=0;
            if (arbitreChaise.getNbMatchsFaitsD()>=2 || arbitreChaise.getCertification()!="ITT1"){
                c++;
                for (i=0;i<4;i++){
                    if (arbitreChaise.getNationalite().equals(this.getJoueurs().get(i).getNationalite())){
                        c++;
                    }
                }
            }
        }
        
        for (i=0;i<6;i++){
            arbitresLigne.add(arbitres.get(new Random().nextInt(arbitres.size())));

        }
        
    }
    
    
    public static List<MatchDouble> getListFromDatabase() {
        // Delete list
        if (list != null) {
            MatchDouble matchDouble;
            for (java.util.Iterator iter = list.iterator(); iter.hasNext();) {
                matchDouble = (MatchDouble)iter.next();
                iter.remove();
            }
        }
        
        // New list
        List<MatchDouble> newList = new LinkedList<>();
        
        DatabaseConnection connection = DatabaseConnection.get();
        
        try {
            Statement statement = connection.getStatement();
            ResultSet matchDoubleResult = statement.executeQuery("select * from matchdouble natural join match");
            ResultSet creneauResult = null;
            int matchID;
            Creneau creneau = null;
            
            while (matchDoubleResult.next()) {
                matchID = matchDoubleResult.getInt("idmatch");
                
                // Find creneau
                ResultSet result = statement.executeQuery("select * from creneau where matchid = " + matchID);
                if (result.next()) {
                    creneau = Creneau.get(
                            Jour.get(result.getDate("jour")), 
                            result.getInt("heure")
                    );
                }
                if (result != null) {
                    result.close();
                    result = null;
                }
                
                // Get score
                ArrayList<Set> sets = new ArrayList<>(5);
                {
                    int setID;
                    for (int i = 1; i <= 5; i++) {
                        setID = matchDoubleResult.getInt("set"+i);
                        if (setID == 0)
                            break;
                        
                        result = statement.executeQuery("select * from set where idset = " + setID);
                        if (result.next()) {
                            sets.add(new Set(
                                    result.getInt("jeux1"), result.getInt("jeux2"), 
                                    result.getInt("pointsdernierjeu1"), result.getInt("pointsdernierjeu1")
                            ));
                        }
                        else {
                            break;
                        }
                    }
                    if (result != null) {
                        result.close(); 
                        result = null;
                    }
                }
                
                // Get arbitresLigne
                ArrayList<Arbitre> arbitresLigne = new ArrayList<>(6);
                result = statement.executeQuery("select idarbitre from ligne where idmatch = " + matchID);
                {
                    int i = 0;
                    while (result.next() && i < 6) {
                        arbitresLigne.add(Arbitre.get(result.getInt("idarbitre")));
                        i++;
                    }
                }
                if (result != null) {
                    result.close();
                    result = null;
                }
                
                // Get equipesRamassage
                ArrayList<EquipeRamassage> equipesRamassage = new ArrayList<>(2);
                result = statement.executeQuery("select idequiperam from ramassage where idmatch = " + matchID);
                {
                    int i = 0;
                    while (result.next() && i < 2) {
                        equipesRamassage.add(EquipeRamassage.get(result.getInt("idequiperam")));
                        i++;
                    }
                }
                if (result != null) {
                    result.close();
                    result = null;
                }
                
                // Get equipes
                ArrayList<Equipe> equipes = new ArrayList<>(2);
                result = statement.executeQuery("select idequipe from jouedouble where idmatch = " + matchID);
                {
                    int i = 0;
                    while (result.next() && i < 2) {
                        equipes.add(Equipe.get(result.getInt("idequipe")));
                        i++;
                    }
                }
                if (result != null) {
                    result.close();
                    result = null;
                }
                
                // New MatchDouble
                MatchDouble matchDouble = new MatchDouble(
                        matchID,
                        creneau,
                        sets,
                        Arbitre.get(matchDoubleResult.getInt("idarbitre")),
                        arbitresLigne,
                        equipesRamassage,
                        equipes
                );

                newList.add(matchDouble);
            }
            
            matchDoubleResult.close();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        
        list = newList;
        return list;
    }
    
    public static List<MatchDouble> getList() {
        return list;
    }
    
    public static MatchDouble get(int id) {
        for (MatchDouble matchDouble : list) {
            if (matchDouble.id == id) {
                return matchDouble;
            }
        }
        return null;
    }
}
