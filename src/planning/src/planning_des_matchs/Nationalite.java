/***********************************************************************
 * Module:  Nationalite.java
 * Author:  swann
 * Purpose: Defines the Class Nationalite
 ***********************************************************************/

package planning_des_matchs;

import database.DatabaseConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;


public class Nationalite {
    private int id;
    private String libelle;
    
    private static List<Nationalite> list = new LinkedList<>();

    
    Nationalite(int id, String libelle) {
        this.id=id;
        this.libelle=libelle;
    }
    
    public int getID() {
        return id;
    }

    public String getLibelle() {
        return libelle;
    }
    
    public void setLibelle(String newLibelle) {
        libelle = newLibelle;
    }
    
    
    public static List getListFromDatabase() {
        // Delete table
        if (list != null) {
            Nationalite nationalite;
            for (java.util.Iterator iter = list.iterator(); iter.hasNext();) {
                nationalite = (Nationalite)iter.next();
                iter.remove();
            }
        }
        
        // New table
        List<Nationalite> list = new LinkedList<>();
        
        DatabaseConnection connection = DatabaseConnection.get();
        
        try {
            Statement statement = connection.getStatement();
            ResultSet result = statement.executeQuery("select * from nationalite");

            while (result.next()) {
                Nationalite nationalite = new Nationalite(
                        result.getInt("idnationalite"), 
                        result.getString("libelle")
                );

                list.add(nationalite);
            }
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        
        list = list;
        return list;
    }
    
    public static List<Nationalite> getList() {
        return list;
    }
          
    public static Nationalite get(int id) {
        for (Nationalite row : list) {
            if (row.id == id) {
                return row;
            }
        }
        return null;
    }
}
