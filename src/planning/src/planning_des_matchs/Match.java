/***********************************************************************
 * Module:  Match.java
 * Author:  swann
 * Purpose: Defines the Class Match
 ***********************************************************************/

package planning_des_matchs;

import database.DatabaseConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public abstract class Match {
    private int id;
    private Creneau creneau;
    private Court court;
    protected java.util.List<Arbitre> arbitresLigne;
    private java.util.List<EquipeRamassage> equipesRamassage;
    protected Arbitre arbitreChaise;
   
    public int getID() {
        return id;
    }


   
    public Match(int id, Creneau creneau, Court court, Arbitre arbitreChaise, java.util.List<Arbitre> arbitresLigne, 
            java.util.List<EquipeRamassage> equipesRamassage) {
        this.id = id;
        this.creneau = creneau;
        this.court = court;
        
        this.arbitreChaise = arbitreChaise;

        if (arbitresLignes == null)
            arbitresLigne = new ArrayList(6);
        else
            this.arbitresLigne = arbitresLigne;

        if (equipesRamassage == null)
            equipesRamassage = new ArrayList(2);
        else
            this.equipesRamassage = equipesRamassage;
    }
        
    public Creneau getCreneau() {
        return creneau;
    }
    
    /** @param newCreneau */
    public void setCreneau(Creneau newCreneau) {
        if (this.creneau == null || !this.creneau.equals(newCreneau)) {
            if (this.creneau != null) {
                Creneau oldCreneau = this.creneau;
                this.creneau = null;
                oldCreneau.setMatch(null);
            }
            if (newCreneau != null) {
                this.creneau = newCreneau;
                this.creneau.setMatch(this);
            }
        }    
    }
   
    /** @pdGenerated default getter */
    public java.util.List<Arbitre> getArbitresLigne() {
        if (arbitresLigne == null)
            arbitresLigne = new java.util.ArrayList<Arbitre>();
        return arbitresLigne;
    }
   
    /** @pdGenerated default iterator getter */
    public java.util.Iterator getIteratorArbitresLigne() {
        if (arbitresLigne == null)
            arbitresLigne = new java.util.ArrayList<Arbitre>();
        return arbitresLigne.iterator();
   }
   
    /** @pdGenerated default setter
      * @param newLigne */
    public void setArbitresLigne(java.util.List<Arbitre> newLigne) {
        removeAllArbitresLigne();
        for (java.util.Iterator iter = newLigne.iterator(); iter.hasNext();)
            addArbitreLigne((Arbitre)iter.next());
    }
   
    /** @pdGenerated default add
      * @param newArbitre */
    public void addArbitreLigne(Arbitre newArbitre) {
        if (this.arbitresLigne == null)
            this.arbitresLigne = new java.util.ArrayList<Arbitre>();
        if (!this.arbitresLigne.contains(newArbitre)) {
            this.arbitresLigne.add(newArbitre);
            newArbitre.addMatchLigne(this);      
        }
    }
   
    /** @pdGenerated default remove
      * @param oldArbitre */
    public void removeArbitreLigne(Arbitre oldArbitre) {
        if (oldArbitre == null)
           return;
        if (this.arbitresLigne != null) {
            if (this.arbitresLigne.contains(oldArbitre)) {
                this.arbitresLigne.remove(oldArbitre);
                oldArbitre.removeMatch(this);
            }
        }
    }
   
    /** @pdGenerated default removeAll */
    public void removeAllArbitresLigne() {
        if (arbitresLigne != null) {
            Arbitre oldArbitre;
            for (java.util.Iterator iter = getIteratorArbitresLigne(); iter.hasNext();) {
                oldArbitre = (Arbitre)iter.next();
                iter.remove();
                oldArbitre.removeMatch(this);
            }
        }
    }
    
    
    abstract public void assignerArbitres();

    
    /** @pdGenerated default getter */
    public java.util.List<EquipeRamassage> getEquipesRamassage() {
        if (equipesRamassage == null)
            equipesRamassage = new java.util.ArrayList<EquipeRamassage>();
        return equipesRamassage;
    }
   
    /** @pdGenerated default iterator getter */
    public java.util.Iterator getIteratorEquipesRamassage() {
        if (equipesRamassage == null)
            equipesRamassage = new java.util.ArrayList<EquipeRamassage>();
        return equipesRamassage.iterator();
    }
   
    /** @pdGenerated default setter
      * @param newEquipeRamassage */
    public void setEquipesRamassage(java.util.List<EquipeRamassage> newEquipeRamassage) {
        removeAllEquipesRamassage();
        for (java.util.Iterator iter = newEquipeRamassage.iterator(); iter.hasNext();)
            addEquipeRamassage((EquipeRamassage)iter.next());
    }
   
    /** @pdGenerated default add
      * @param newEquipeRamassage */
    public void addEquipeRamassage(EquipeRamassage newEquipeRamassage) {
        if (newEquipeRamassage == null)
            return;
        if (this.equipesRamassage == null)
            this.equipesRamassage = new java.util.ArrayList<EquipeRamassage>();
        if (!this.equipesRamassage.contains(newEquipeRamassage)) {
            this.equipesRamassage.add(newEquipeRamassage);
            newEquipeRamassage.addMatch(this);      
        }
    }
   
    /** @pdGenerated default remove
      * @param oldEquipeRamassage */
    public void removeEquipeRamassage(EquipeRamassage oldEquipeRamassage) {
        if (oldEquipeRamassage == null)
            return;
        if (this.equipesRamassage != null) {
            if (this.equipesRamassage.contains(oldEquipeRamassage)) {
                this.equipesRamassage.remove(oldEquipeRamassage);
                oldEquipeRamassage.removeMatch(this);
            }
        }
    }
   
    /** @pdGenerated default removeAll */
    public void removeAllEquipesRamassage() {
        if (equipesRamassage != null) {
            EquipeRamassage oldEquipeRamassage;
            for (java.util.Iterator iter = getIteratorEquipesRamassage(); iter.hasNext();)
            {
                oldEquipeRamassage = (EquipeRamassage)iter.next();
                iter.remove();
                oldEquipeRamassage.removeMatch(this);
            }
        }
    }
    
    
        /** @pdGenerated default parent getter */
    public Arbitre getArbitreChaise() {
        return arbitreChaise;
    }
   
    /** @pdGenerated default parent setter
      * @param newArbitre */
    abstract public void setArbitreChaise(Arbitre newArbitre);

    
    public static List<Joueur> getListFromDatabase() {
        // Delete list
        if (list != null) {
            Joueur joueur;
            for (java.util.Iterator iter = list.iterator(); iter.hasNext();) {
                joueur = (Joueur)iter.next();
                iter.remove();
            }
        }
        
        // New list
        List<Joueur> newList = new LinkedList<>();
        
        DatabaseConnection connection = DatabaseConnection.get();
        
        try {
            Statement statement = connection.getStatement();
            ResultSet result = statement.executeQuery("select * from joueur");

            while (result.next()) {
                // TODO: get players : select * from joueur natural join joueur order by idjoueur ?
                Joueur joueur = new Joueur(
                        result.getInt("idjoueur"),
                        result.getString("nom"),
                        result.getString("prenom"),
                        Nationalite.get(result.getInt("nationalite"))
                );

                newList.add(joueur);
            }
            
            result.close();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        
        list = newList;
        return list;
    }
    
    public static List<Joueur> getList() {
        return list;
    }
    
    public static Joueur get(int id) {
        for (Joueur joueur : list) {
            if (joueur.id == id) {
                return joueur;
            }
        }
        return null;
    }
}