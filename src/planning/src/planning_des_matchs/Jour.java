/***********************************************************************
 * Module:  Date.java
 * Author:  swann
 * Purpose: Defines the Class Jour
 ***********************************************************************/

package planning_des_matchs;

import database.DatabaseConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;


public class Jour {
    private java.util.Date date;

    public java.util.List<Creneau> creneaux;
    public java.util.List<Entrainement> entrainements;
    
    public static List<Jour> list = new LinkedList<>();
    

    public java.util.Date getDate() {
        return date;
    }

    /** @param newDate */
    public void setDate(java.util.Date newDate) {
        date = newDate;
    }
   
    public Jour(Date date) {
        this.date =date;
    }
    
    
    /** @pdGenerated default getter */
    public java.util.List<Creneau> getCreneaux() {
        if (creneaux == null)
            creneaux = new ArrayList<Creneau>();
        return creneaux;
    }
    
    /** @pdGenerated default iterator getter */
    public java.util.Iterator getIteratorCreneaux() {
        if (creneaux == null)
            creneaux = new java.util.ArrayList<Creneau>();
        return creneaux.iterator();
    }
    
    /** @pdGenerated default setter
      * @param newCreneaux */
    public void setCreneaux(java.util.ArrayList<Creneau> newCreneaux) {
        removeAllCreneaux();
        for (java.util.Iterator iter = newCreneaux.iterator(); iter.hasNext();)
            addCreneau((Creneau)iter.next());
    }
    
    /** @pdGenerated default add
      * @param newCreneau */
    public void addCreneau(Creneau newCreneau) {
        if (newCreneau == null)
            return;
        if (this.creneaux == null)
            this.creneaux = new java.util.ArrayList<Creneau>();
        if (!this.creneaux.contains(newCreneau)) {
            this.creneaux.add(newCreneau);
            newCreneau.setJour(this);      
        }
    }
    
    /** @pdGenerated default remove
      * @param oldCreneau */
    public void removeCreneau(Creneau oldCreneau) {
        if (oldCreneau == null)
            return;
        if (this.creneaux != null) {
            if (this.creneaux.contains(oldCreneau)) {
                this.creneaux.remove(oldCreneau);
                oldCreneau.setJour((Jour)null);
            }
        }
    }
   
    /** @pdGenerated default removeAll */
    public void removeAllCreneaux() {
        if (creneaux != null) {
            Creneau oldCreneau;
            for (java.util.Iterator iter = getIteratorCreneaux(); iter.hasNext();) {
                oldCreneau = (Creneau)iter.next();
                iter.remove();
                oldCreneau.setJour((Jour)null);
            }
        }
    }
   
    /** @pdGenerated default getter */
    public java.util.List<Entrainement> getEntrainements() {
        if (entrainements == null)
            entrainements = new java.util.ArrayList<Entrainement>();
        return entrainements;
    }

    /** @pdGenerated default iterator getter */
    public java.util.Iterator getIteratorEntrainements() {
        if (entrainements == null)
            entrainements = new java.util.ArrayList<Entrainement>();
        return entrainements.iterator();
    }

    /** @pdGenerated default setter
      * @param newEntrainement */
    public void setEntrainements(java.util.List<Entrainement> newEntrainement) {
        removeAllEntrainements();
        for (java.util.Iterator iter = newEntrainement.iterator(); iter.hasNext();)
            addEntrainement((Entrainement)iter.next());
    }

    /** @pdGenerated default add
      * @param newEntrainement */
    public void addEntrainement(Entrainement newEntrainement) {
        if (newEntrainement == null)
            return;
        if (this.entrainements == null)
            this.entrainements = new java.util.ArrayList<Entrainement>();
        if (!this.entrainements.contains(newEntrainement)) {
            this.entrainements.add(newEntrainement);
            newEntrainement.setJour(this);      
        }
    }

    /** @pdGenerated default remove
      * @param oldEntrainement */
    public void removeEntrainement(Entrainement oldEntrainement) {
        if (oldEntrainement == null)
            return;
        if (this.entrainements != null) {
            if (this.entrainements.contains(oldEntrainement)) {
                this.entrainements.remove(oldEntrainement);
                oldEntrainement.setJour((Jour)null);
            }
        }
    }

    /** @pdGenerated default removeAll */
    public void removeAllEntrainements() {
        if (entrainements != null) {
            Entrainement oldEntrainement;
            for (java.util.Iterator iter = getIteratorEntrainements(); iter.hasNext();) {
                oldEntrainement = (Entrainement)iter.next();
                iter.remove();
                oldEntrainement.setJour((Jour)null);
            }
        }
    }

    
    public static List<Jour> getListFromDatabase() {
        // Delete list
        if (list != null) {
            Jour jour;
            for (java.util.Iterator iter = list.iterator(); iter.hasNext();) {
                jour = (Jour)iter.next();
                iter.remove();
            }
        }
        
        // New list
        List<Jour> newList = new LinkedList<>();
        
        DatabaseConnection connection = DatabaseConnection.get();
        
        try {
            Statement statement = connection.getStatement();
            ResultSet result = statement.executeQuery("select distinct jour from creneau");

            while (result.next()) {
                Jour jour = new Jour(
                        result.getDate("jour")
                );

                newList.add(jour);
            }
            
            result.close();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        
        list = newList;
        return list;
    }
    
    public static List<Jour> getList() {
        return list;
    }
    
    public static Jour get(Date date) {
        for (Jour jour : list) {
            if (jour.date == date) {
                return jour;
            }
        }
        return null;
    }
}