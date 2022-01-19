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
    
    private static List<Nationalite> table = new LinkedList<>();

    
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
        if (table != null) {
            Nationalite nationalite;
            for (java.util.Iterator iter = table.iterator(); iter.hasNext();) {
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
                int i = result.getInt("userid");
                String str = result.getString("username");
                
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
        
        table = list;
        return list;
    }
    
    public static List<Nationalite> getList() {
        return table;
    }
          
    public static Nationalite get(int id) {
        for (Nationalite row : table) {
            if (row.id == id) {
                return row;
            }
        }
        return null;
    }
}
