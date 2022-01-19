/***********************************************************************
 * Module:  MatchSimple.java
 * Author:  swann
 * Purpose: Defines the Class MatchSimple
 ***********************************************************************/

package planning_des_matchs;

import database.DatabaseConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class MatchSimple extends Match {
    private java.util.List<Joueur> joueurs;
    private boolean estQualif;
        
    private static List<MatchSimple> list = new LinkedList<>(); 

    
    public boolean estQualif() {
        return estQualif;
    }

    /** @param newEstQualif */
    public void setEstQualif(boolean newEstQualif) {
        estQualif = newEstQualif;
    }

   
    public MatchSimple(int id, Creneau creneau, List<Set> score, boolean estQualif, Arbitre arbitreChaise, 
            java.util.List<Joueur> joueurs, java.util.List<Arbitre> arbitresLigne, 
            java.util.List<EquipeRamassage> equipesRamassage) {
        super(id, creneau, score, arbitresLigne, equipesRamassage);
        this.estQualif=estQualif;

        setArbitreChaise(arbitreChaise);

        if (joueurs == null)
            joueurs = new ArrayList(2);
        this.joueurs = joueurs;
    }
    
    
    /** @pdGenerated default getter */
    public java.util.List<Joueur> getJoueurs() {
        if (joueurs == null)
            joueurs = new java.util.ArrayList<Joueur>();
        return joueurs;
    }
   
    /** @pdGenerated default iterator getter */
    public java.util.Iterator getIteratorJoueurs() {
        if (joueurs == null)
            joueurs = new java.util.ArrayList<Joueur>();
        return joueurs.iterator();
    }
   
    /** @pdGenerated default setter
      * @param newJoueur */
    public void setJoueurs(java.util.List<Joueur> newJoueurs) {
        removeAllJoueurs();
        for (java.util.Iterator iter = newJoueurs.iterator(); iter.hasNext();)
            addJoueur((Joueur)iter.next());
    }
   
    /** @pdGenerated default add
      * @param newJoueur */
    public void addJoueur(Joueur newJoueur) {
        if (newJoueur == null)
           return;
        if (this.joueurs == null)
           this.joueurs = new java.util.ArrayList<Joueur>();
        if (!this.joueurs.contains(newJoueur)) {
           this.joueurs.add(newJoueur);   
        }
    }
   
    /** @pdGenerated default remove
      * @param oldJoueur */
    public void removeJoueur(Joueur oldJoueur) {
        if (oldJoueur == null)
            return;
        if (this.joueurs != null) {
            if (this.joueurs.contains(oldJoueur)) {
                this.joueurs.remove(oldJoueur);
            }
        }
    }
   
    /** @pdGenerated default removeAll */
    public void removeAllJoueurs() {
        if (joueurs != null) {
            Joueur oldJoueur;
            for (java.util.Iterator iter = getIteratorJoueurs(); iter.hasNext();) {
                oldJoueur = (Joueur)iter.next();
                iter.remove();
            }
        }
    }
   
    /** @pdGenerated default parent setter
      * @param newArbitre */
    public void setArbitreChaise(Arbitre newArbitre) {
        if (this.arbitreChaise == null || !this.arbitreChaise.equals(newArbitre)) {
            if (this.arbitreChaise != null) {
                Arbitre oldArbitre = this.arbitreChaise;
                this.arbitreChaise = null;
                oldArbitre.subMatchSimple();
            }
            if (newArbitre != null) {
                this.arbitreChaise = newArbitre;
                this.arbitreChaise.addMatchSimple();
            }
        }
    }

    @Override
    public void assignerArbitres() {
        /*arbitres=Arbitre.getList();
        int i, c = 1;
        //TODO ajouter tous le traitement de l'emploi du temps de l'arbitre.
        arbitreChaise = arbitres.get(new Random().nextInt(arbitres.size()));
        
        while (c>0){
            c=0;
            if (arbitreChaise.getNbMatchsFaitsS()>=2 || arbitreChaise.getCertification()!="ITT1"){
                c++;
                for (i=0;i<joueurs.size();i++){
                    if (arbitreChaise.getNationalite().equals(joueurs.get(i).getNationalite())){
                        c++;
                    }
                }
            }
        }
        
        for (i=0;i<6;i++){
            arbitresLigne.add(arbitres.get(new Random().nextInt(arbitres.size())));

        }
        
        */
    }

    
    public static List<MatchSimple> getListFromDatabase() {
        // Delete list
        if (list != null) {
            MatchSimple matchSimple;
            for (java.util.Iterator iter = list.iterator(); iter.hasNext();) {
                matchSimple = (MatchSimple)iter.next();
                iter.remove();
            }
        }
        
        // New list
        List<MatchSimple> newList = new LinkedList<>();
        
        DatabaseConnection connection = DatabaseConnection.get();
        
        try {
            Statement statement = connection.getStatement();
            ResultSet matchSimpleResult = statement.executeQuery("select * from matchsimple natural join match");
            ResultSet creneauResult = null;
            int matchID;
            Creneau creneau = null;
            
            while (matchSimpleResult.next()) {
                matchID = matchSimpleResult.getInt("idmatch");
                
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
                        setID = matchSimpleResult.getInt("set"+i);
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
                
                // Get joueurs
                ArrayList<Joueur> joueurs = new ArrayList<>(2);
                result = statement.executeQuery("select idjoueur from jouesimple where idmatch = " + matchID);
                {
                    int i = 0;
                    while (result.next() && i < 2) {
                        joueurs.add(Joueur.get(result.getInt("idjoueur")));
                        i++;
                    }
                }
                if (result != null) {
                    result.close();
                    result = null;
                }
                
                // New MatchSimple
                MatchSimple matchSimple = new MatchSimple(
                        matchID,
                        creneau,
                        sets,
                        matchSimpleResult.getBoolean("estqualif"),
                        Arbitre.get(matchSimpleResult.getInt("idarbitre")),
                        joueurs,
                        arbitresLigne,
                        equipesRamassage

                );

                newList.add(matchSimple);
            }
            
            matchSimpleResult.close();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        
        list = newList;
        return list;
    }

}