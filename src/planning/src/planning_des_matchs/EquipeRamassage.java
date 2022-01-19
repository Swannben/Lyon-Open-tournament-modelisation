/***********************************************************************
 * Module:  EquipeRamassage.java
 * Author:  swann
 * Purpose: Defines the Class EquipeRamassage
 ***********************************************************************/

package planning_des_matchs;

import database.DatabaseConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class EquipeRamassage {
    private int id;
    private String nomEquipe;

    //public java.util.List<Match> matchs;
    
    private static List<EquipeRamassage> list = new LinkedList<>();
    

    public int getID() {
        return id;
    }

    public String getNomEquipe() {
        return nomEquipe;
    }

    /** @param newNomEquipe */
    public void setNomEquipe(String newNomEquipe) {
        nomEquipe = newNomEquipe;
    }

    public EquipeRamassage(int id, String nomEquipe) {
        this.id=id;
        this.nomEquipe=nomEquipe;
    }
   
   
    /** @pdGenerated default getter */
    /*public java.util.List<Match> getMatchs() {
        if (matchs == null)
            matchs = new java.util.ArrayList<Match>();
        return matchs;
    }*/
   
    /** @pdGenerated default iterator getter */
    /*public java.util.Iterator getIteratorMatchs() {
        if (matchs == null)
            matchs = new java.util.ArrayList<Match>();
        return matchs.iterator();
    }*/
   
    /** @pdGenerated default setter
      * @param newMatch */
    /*public void setMatchs(java.util.List<Match> newMatch) {
        removeAllMatchs();
        for (java.util.Iterator iter = newMatch.iterator(); iter.hasNext();)
            addMatch((Match)iter.next());
    }*/
   
    /** @pdGenerated default add
      * @param newMatch */
    /*public void addMatch(Match newMatch) {
        if (newMatch == null)
            return;
        if (this.matchs == null)
            this.matchs = new java.util.ArrayList<Match>();
        if (!this.matchs.contains(newMatch)) {
            this.matchs.add(newMatch);
            newMatch.addEquipeRamassage(this);      
        }
    }*/
   
    /** @pdGenerated default remove
      * @param oldMatch */
    /*public void removeMatch(Match oldMatch) {
        if (oldMatch == null)
            return;
        if (this.matchs != null) {
            if (this.matchs.contains(oldMatch)) {
                this.matchs.remove(oldMatch);
                oldMatch.removeEquipeRamassage(this);
            }
        }
    }*/
   
    /** @pdGenerated default removeAll */
    /*public void removeAllMatchs() {
        if (matchs != null) {
            Match oldMatch;
            for (java.util.Iterator iter = getIteratorMatchs(); iter.hasNext();) {
                oldMatch = (Match)iter.next();
                iter.remove();
                oldMatch.removeEquipeRamassage(this);
            }
        }
    }*/
    
    
    public static List<EquipeRamassage> getListFromDatabase() {
        // Delete list
        if (list != null) {
            EquipeRamassage equipeRamassage;
            for (java.util.Iterator iter = list.iterator(); iter.hasNext();) {
                equipeRamassage = (EquipeRamassage)iter.next();
                iter.remove();
            }
        }
        
        // New list
        List<EquipeRamassage> newList = new LinkedList<>();
        
        DatabaseConnection connection = DatabaseConnection.get();
        
        try {
            Statement statement = connection.getStatement();
            ResultSet result = statement.executeQuery("select * from equipeRamassage");

            while (result.next()) {
                // TODO: get players : select * from equipeRamassage natural join joueur order by idequipeRamassage ?
                EquipeRamassage equipeRamassage = new EquipeRamassage(
                        result.getInt("idequipeRamassage"),
                        result.getString("nomequipe")
                );

                newList.add(equipeRamassage);
            }
            
            result.close();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        
        list = newList;
        return list;
    }
    
    public static List<EquipeRamassage> getList() {
        return list;
    }
    
    public static EquipeRamassage get(int id) {
        for (EquipeRamassage equipeRamassage : list) {
            if (equipeRamassage.id == id) {
                return equipeRamassage;
            }
        }
        return null;
    }

}