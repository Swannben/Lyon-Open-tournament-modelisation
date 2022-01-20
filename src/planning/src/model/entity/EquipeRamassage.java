/***********************************************************************
 * Module:  EquipeRamassage.java
 * Author:  swann
 * Purpose: Defines the Class EquipeRamassage
 ***********************************************************************/

package model.entity;

import model.database.DatabaseConnection;
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