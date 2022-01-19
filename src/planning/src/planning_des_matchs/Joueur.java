/***********************************************************************
 * Module:  Joueur.java
 * Author:  swann
 * Purpose: Defines the Class Joueur
 ***********************************************************************/

package planning_des_matchs;

import database.DatabaseConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class Joueur {
    private final int id;
    private String nom;
    private String prenom;
    private Nationalite nationalite;
    private Boolean estQualifie;
    private Equipe equipe;
    

    public Joueur( int id, String nom, String prenom, Nationalite nationalite, Equipe equipe, Boolean estQualifie) {
        this.id=id;
        this.nationalite=nationalite;
        this.nom=nom;
        this.prenom=prenom;
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
   
    public String getPrenom() {
        return prenom;
    }
   
    /** @param newPrenom */
    public void setPrenom(String newPrenom) {
        prenom = newPrenom;
    }
   
    public Nationalite getNationalite() {
        return nationalite;
    }

    /** @param newNationalite */
    public void setNationalite(Nationalite newNationalite) {
        nationalite = newNationalite;
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
            ResultSet result = statement.executeQuery("select * from joueur");

            while (result.next()) {
                // TODO: get players : select * from joueur natural join joueur order by idjoueur ?
                Joueur joueur = new Joueur(
                        result.getInt("idjoueur"),
                        result.getString("nom"),
                        result.getString("prenom"),
                        Nationalite.get(result.getInt("nationalite")),
                        null // TODO: equipe
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
