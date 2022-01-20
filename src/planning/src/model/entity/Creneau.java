/***********************************************************************
 * Module:  Creneau.java
 * Author:  swann
 * Purpose: Defines the Class Creneau
 ***********************************************************************/

package model.entity;

import model.database.DatabaseConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class Creneau {
    private int heure;
    public Jour jour;

    private Match match;
    private Court court;
    
    public static List<Creneau> list = new LinkedList<>();
    
    
    Creneau(int heure, Jour jour){
        this.heure=heure;
        this.jour=jour;
    }
   
   
    public int getHeure() {
        return heure;
    }
    
    /** @pdGenerated default parent getter */
    public Match getMatch() {
        return match;
    }
   
    /** @pdGenerated default parent setter
      * @param newMatch */
    public void setMatch(Match newMatch) {
        if (this.match == null || !this.match.equals(newMatch)) {
            if (this.match != null) {
                Match oldMatch = this.match;
                this.match = null;
                oldMatch.setCreneau(null);
            }
            if (newMatch != null) {
                this.match = newMatch;
                this.match.setCreneau(this);
            }
        }
    }    
    
    /** @pdGenerated default parent getter */
    public Court getCourt() {
        return court;
    }
    
    /** @pdGenerated default parent setter
      * @param newCourt */
    public void setCourt(Court newCourt) {
        this.court = newCourt;
    }
  
    
    /** @pdGenerated default parent getter */
    public Jour getJour() {
        return jour;
    }
   
    /** @pdGenerated default parent setter
      * @param newJour */
    public void setJour(Jour newJour) {
        if (this.jour == null || !this.jour.equals(newJour))
        {
            if (this.jour != null) {
                Jour oldJour = this.jour;
                this.jour = null;
                oldJour.removeCreneau(this);
            }
            if (newJour != null) {
                this.jour = newJour;
                this.jour.addCreneau(this);
            }
        }
    }
    
    
    
    public static List getListFromDatabase() {
        // Delete list
        if (list != null) {
            Creneau court;
            for (java.util.Iterator iter = list.iterator(); iter.hasNext();) {
                court = (Creneau)iter.next();
                iter.remove();
            }
        }
        
        // New list
        List<Creneau> newList = new LinkedList<>();
        
        DatabaseConnection connection = DatabaseConnection.get();
        
        try {
            Statement statement = connection.getStatement();
            ResultSet result = statement.executeQuery("select * from creneau");

            while (result.next()) {
                Creneau creneau = new Creneau(
                    result.getInt("heure"), 
                    Jour.get(result.getDate("idjour"))
                );

                newList.add(creneau);
            }
            
            result.close();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        
        list = newList;
        return newList;
    }
    
    public static List getList() {
        return list;
    }
    
    public static Creneau get(Jour jour, int heure) {
        for (Creneau creneau : list) {
            if (creneau.jour.equals(jour) && creneau.heure == heure) {
                return creneau;
            }
        }
        return null;
    }
    
    
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Creneau))
            return false;
        
        Creneau creneau = (Creneau)other;
        return this.jour.equals(creneau) && this.heure == creneau.heure;
    }
}
