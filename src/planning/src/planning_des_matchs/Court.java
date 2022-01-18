/***********************************************************************
 * Module:  Court.java
 * Author:  swann
 * Purpose: Defines the Class Court
 ***********************************************************************/

package planning_des_matchs;

import database.DatabaseConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class Court {
    private int id;
    private String nom;
    private boolean estPrincipal;

    public java.util.List<Match> matchs;
    public java.util.List<Entrainement> entrainements;
    
    private static List<Court> list = new LinkedList<>();

    
    public Court(int id, String nom, boolean estPrincipal) {
        this.id=id;
        this.nom=nom;
        this.estPrincipal=estPrincipal;
    }
   

    public int getID() {
       return id;
    }
   
    public String getNom() {
        return nom;
    }
   
    /** @param newNom */
    public void setNom(String newNom) {
        nom = newNom;
    }
   
    public boolean estPrincipal() {
        return estPrincipal;
    }

    /** @param estPrincipal */
    public void setPrincipal(boolean estPrincipal) {
        this.estPrincipal = estPrincipal;
    }
   

   
    /** @pdGenerated default getter */
    public java.util.List<Match> getMatchs() {
        if (matchs == null)
            matchs = new java.util.HashSet<Match>();
        return matchs;
    }

    /** @pdGenerated default iterator getter */
    public java.util.Iterator getIteratorMatchs() {
        if (matchs == null)
            matchs = new java.util.HashSet<Match>();
        return matchs.iterator();
    }

    /** @pdGenerated default setter
      * @param newMatch */
    public void setMatchs(java.util.List<Match> newMatch) {
        removeAllMatchs();
        for (java.util.Iterator iter = newMatch.iterator(); iter.hasNext();)
            addMatch((Match)iter.next());
    }

    /** @pdGenerated default add
      * @param newMatch */
    public void addMatch(Match newMatch) {
        if (newMatch == null)
            return;
        if (this.matchs == null)
            this.matchs = new java.util.HashSet<Match>();
        if (!this.matchs.contains(newMatch)) {
            this.matchs.add(newMatch);
            newMatch.setCourt(this);      
        }
    }

    /** @pdGenerated default remove
      * @param oldMatch */
    public void removeMatch(Match oldMatch) {
        if (oldMatch == null)
            return;
        if (this.matchs != null) {
            if (this.matchs.contains(oldMatch)) {
                this.matchs.remove(oldMatch);
                oldMatch.setCourt((Court)null);
            }
        }
    }

    /** @pdGenerated default removeAll */
    public void removeAllMatchs() {
        if (matchs != null) {
            Match oldMatch;
            for (java.util.Iterator iter = getIteratorMatchs(); iter.hasNext();) {
                oldMatch = (Match)iter.next();
                iter.remove();
                oldMatch.setCourt((Court)null);
            }
        }
    }
    
    /** @pdGenerated default getter */
    public java.util.List<Entrainement> getEntrainements() {
        if (entrainements == null)
            entrainements = new java.util.HashSet<Entrainement>();
        return entrainements;
    }

    /** @pdGenerated default iterator getter */
    public java.util.Iterator getIteratorEntrainements() {
        if (entrainements == null)
            entrainements = new java.util.HashSet<Entrainement>();
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
            this.entrainements = new java.util.HashSet<Entrainement>();
        if (!this.entrainements.contains(newEntrainement)) {
            this.entrainements.add(newEntrainement);
            newEntrainement.setCourt(this);      
        }
    }

    /** @pdGenerated default remove
      * @param oldEntrainement */
    public void removeEntrainement(Entrainement oldEntrainement) {
        if (oldEntrainement == null)
            return;
        if (this.entrainements != null)
            if (this.entrainements.contains(oldEntrainement)) {
                this.entrainements.remove(oldEntrainement);
                oldEntrainement.setCourt((Court)null);
            }
    }

    /** @pdGenerated default removeAll */
    public void removeAllEntrainements() {
        if (entrainements != null) {
            Entrainement oldEntrainement;
            for (java.util.Iterator iter = getIteratorEntrainements(); iter.hasNext();) {
                oldEntrainement = (Entrainement)iter.next();
                iter.remove();
                oldEntrainement.setCourt((Court)null);
            }
        }
    }

    
    
    public static List getListFromDatabase() {
        // Delete list
        if (list != null) {
            Court court;
            for (java.util.Iterator iter = list.iterator(); iter.hasNext();) {
                court = (Court)iter.next();
                iter.remove();
            }
        }
        
        // New list
        List<Court> newList = new LinkedList<>();
        
        DatabaseConnection connection = DatabaseConnection.get();
        
        try {
            Statement statement = connection.getStatement();
            ResultSet result = statement.executeQuery("select * from court");

            while (result.next()) {
                Court court = new Court(
                    result.getInt("idcourt"), 
                    result.getString("nom"), 
                    result.getBoolean("estprincipal")
                );

                newList.add(court);
            }
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
    
    public static Court get(int id) {
        for (Court row : list) {
            if (row.id == id) {
                return row;
            }
        }
        return null;
    }
}