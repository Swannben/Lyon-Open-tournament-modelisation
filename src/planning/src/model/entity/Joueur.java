/***********************************************************************
 * Module:  Joueur.java
 * Author:  swann
 * Purpose: Defines the Class Joueur
 ***********************************************************************/

package model.entity;

import model.database.DatabaseConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class Joueur extends VIP {
    private boolean estQualifie;
    private Equipe equipe;
    
    private static List<Joueur> list = new LinkedList<>();
    

    public Joueur(int idJoueur, int idUtilisateur, String nom, String prenom, String numeroTel, 
            int typeVIP, Nationalite nationalite, Equipe equipe, boolean estQualifie) {
        super(idJoueur, idUtilisateur, nom, prenom, numeroTel, typeVIP, nationalite);
        this.equipe=equipe;
        this.estQualifie=estQualifie;
    }
    
    static List<Joueur> getJoueursQualifie(){
        List<Joueur> joueurs= new LinkedList<>();
        for (Joueur j :Joueur.getList()){
            if (!j.getEstQualifie()){
                joueurs.add(j);
            }
        }
        return joueurs;
    }
    
    public static List<Joueur> getJoueursPartQualif(){
        List<Joueur> joueurs= new LinkedList<>();
        
        for (Joueur j : Joueur.getList()){
            if (j.getEstQualifie()){
                joueurs.add(j);
            }
        }
        return joueurs;
    }
    
    public Boolean getEstQualifie(){
        return estQualifie;
    }
    
    public void setEstQualifie(Boolean estQualif){
        estQualifie=estQualif;
    }

    
    /** @pdGenerated default parent getter */
    public Equipe getEquipe() {
        return equipe;
    }
   
    /** @pdGenerated default parent setter
      * @param newEquipe */
    public void setEquipe(Equipe newEquipe) {
        if (this.equipe == null || !this.equipe.equals(newEquipe)) {
            if (this.equipe != null) {
                Equipe oldEquipe = this.equipe;
                this.equipe = null;
                oldEquipe.removeJoueur(this);
            }
            if (newEquipe != null) {
                this.equipe = newEquipe;
                this.equipe.addJoueur(this);
            }
        }
    }
    
    
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
            ResultSet result = statement.executeQuery("select * from joueur, vip where idjoueur = idvip");

            while (result.next()) {
                // TODO: get players : select * from joueur natural join joueur order by idjoueur ?
                Joueur joueur = new Joueur(
                        result.getInt("idvip"), 
                        result.getInt("idutilisateur"), 
                        result.getString("nom"), 
                        result.getString("prenom"),
                        result.getString("numerotel"), 
                        result.getInt("typevip"), 
                        Nationalite.get(result.getInt("idnationalite")),
                        null, // TODO: equipe
                        result.getBoolean("estqualifie")
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
