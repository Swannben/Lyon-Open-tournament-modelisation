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

    public Equipe equipe;
    //public java.util.List<MatchSimple> matchsSimples;
    
    private static List<Joueur> list = new LinkedList<>();
    

    public Joueur(int id, String nom, String prenom, Nationalite nationalite) {
        this.id=id;
        this.nationalite=nationalite;
        this.nom=nom;
        this.prenom=prenom;
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
    
    /** @pdGenerated default getter */
    /*public java.util.List<MatchSimple> getMatchsSimples() {
        if (matchsSimples == null)
            matchsSimples = new java.util.HashSet<MatchSimple>();
        return matchsSimples;
    }*/
   
    /** @pdGenerated default iterator getter */
    /*public java.util.Iterator getIteratorMatchsSimples() {
        if (matchsSimples == null)
            matchsSimples = new java.util.HashSet<MatchSimple>();
        return matchsSimples.iterator();
    }*/
   
    /** @pdGenerated default setter
      * @param newMatchSimple */
    /*public void setMatchsSimples(java.util.List<MatchSimple> newMatchSimple) {
        removeAllMatchsSimples();
        for (java.util.Iterator iter = newMatchSimple.iterator(); iter.hasNext();)
            addMatchSimple((MatchSimple)iter.next());
    }*/
   
    /** @pdGenerated default add
      * @param newMatchSimple */
    /*public void addMatchSimple(MatchSimple newMatchSimple) {
        if (newMatchSimple == null)
            return;
        if (this.matchsSimples == null)
            this.matchsSimples = new java.util.HashSet<MatchSimple>();
        if (!this.matchsSimples.contains(newMatchSimple)) {
            this.matchsSimples.add(newMatchSimple);
            newMatchSimple.addJoueur(this);      
        }
    }*/
   
    /** @pdGenerated default remove
      * @param oldMatchSimple */
    /*public void removeMatchSimple(MatchSimple oldMatchSimple) {
        if (oldMatchSimple == null)
            return;
        if (this.matchsSimples != null)
            if (this.matchsSimples.contains(oldMatchSimple)) {
               this.matchsSimples.remove(oldMatchSimple);
               oldMatchSimple.removeJoueur(this);
            }
    }*/
   
    /** @pdGenerated default removeAll */
    /*public void removeAllMatchsSimples() {
        if (matchsSimples != null) {
            MatchSimple oldMatchSimple;
            for (java.util.Iterator iter = getIteratorMatchsSimples(); iter.hasNext();) {
                oldMatchSimple = (MatchSimple)iter.next();
                iter.remove();
                oldMatchSimple.removeJoueur(this);
            }
        }public static List<Joueur> getListFromDatabase() {
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
                        Nationalite.get(result.getInt("nationalite"))
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
    }*/

    
    
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
                        Nationalite.get(result.getInt("nationalite"))
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