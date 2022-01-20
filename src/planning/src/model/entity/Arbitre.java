/***********************************************************************
 * Module:  Arbitre.java
 * Author:  swann
 * Purpose: Defines the Class Arbitre
 ***********************************************************************/

package model.entity;

import model.database.DatabaseConnection;
import java.util.*;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;


public class Arbitre extends VIP {
    private String certification;
    private int nbMatchsFaitsS;
    private int nbMatchsFaitsD;
    
    private static List<Arbitre> list = new LinkedList<>();

    
    public Arbitre(int idArbitre, int idUtilisateur, String certification, String nom, String prenom, 
            String numeroTel, int typeVIP, Nationalite nationalite) {
        super(idArbitre, idUtilisateur, nom, prenom, numeroTel, typeVIP, nationalite);
        //TODO vérifier que la certification est reconnue
        this.certification=certification;  
        nbMatchsFaitsS = 0;     // TODO
        nbMatchsFaitsD = 0;
    }
   
    public String getCertification() {
        return certification;
    }

    /** @param newCertification */
    public void setCertification(String newCertification) {
        // TODO: Tester si la certification est valide/connue.
        certification = newCertification;
    }

    public int getNbMatchsFaitsS() {
        return nbMatchsFaitsS;
    }
    
    public int addMatchSimple() {
        return ++nbMatchsFaitsS;
    }
    
    public int subMatchSimple() {
        
        return --nbMatchsFaitsS;
    }
        
    public int getNbMatchsFaitsD() {
        return nbMatchsFaitsD;
    }
    
    public int addMatchDouble() {
        return ++nbMatchsFaitsD;
    }
    
    public int subMatchDouble() {
        return --nbMatchsFaitsD;
    }
   
    
    public static List getListFromDatabase() {
        // Delete list
        if (list != null) {
            Arbitre arbitre;
            for (java.util.Iterator iter = list.iterator(); iter.hasNext();) {
                arbitre = (Arbitre)iter.next();
                iter.remove();
            }
        }
        
        // New list
        List<Arbitre> newList = new LinkedList<>();
        
        DatabaseConnection connection = DatabaseConnection.get();
        
        try {
            Statement statement = connection.getStatement();
            ResultSet result = statement.executeQuery("select * from arbitre, vip where idarbitre = idvip");

            while (result.next()) {
                Arbitre arbitre = new Arbitre(
                    result.getInt("idvip"), 
                    result.getInt("idutilisateur"), 
                    result.getString("certification"), 
                    result.getString("nom"), 
                    result.getString("prenom"),
                    result.getString("numerotel"), 
                    result.getInt("typevip"), 
                    Nationalite.get(result.getInt("idnationalite"))
                );

                newList.add(arbitre);
            }
            
            result.close();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        
        list = newList;
        return list;
    }
    
    public static List getList() {
        return list;
    }
    
    public static Arbitre get(int id) {
        for (Arbitre row : list) {
            if (row.id == id) {
                return row;
            }
        }
        return null;
    }
}