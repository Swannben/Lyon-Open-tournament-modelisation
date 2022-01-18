/***********************************************************************
 * Module:  Arbitre.java
 * Author:  swann
 * Purpose: Defines the Class Arbitre
 ***********************************************************************/

package planning_des_matchs;

import database.DatabaseConnection;
import java.util.*;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;


public class Arbitre {
    private int id;
    private String certification;
    private int nbMatchsFaitsS;
    private int nbMatchsFaitsD;
    private String nom;
    private String prenom;
    private Nationalite nationalite;

    //private java.util.List<MatchSimple> matchsSimplesChaise;
    //private java.util.List<MatchDouble> matchsDoublesChaise;
    //private java.util.List<Match> matchsLigne;
    
    private static List<Arbitre> list = new LinkedList<>();

    
    
    public Arbitre(int idArbitre, String certification, String nom, String prenom, Nationalite nationalite) {
        this.id=idArbitre;
        //TODO vérifier que la certification est reconnue
        this.certification = certification;
        this.nom = nom;
        this.prenom = prenom;
        this.nationalite = nationalite;
        nbMatchsFaitsS = 0;
        nbMatchsFaitsD = 0;
    }
    
    
    public int getId() {
        return id;
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
   
    
    
    /** @pdGenerated default getter */
    /*public java.util.List<MatchDouble> getMatchsDoublesChaise() {
        if (matchsDoublesChaise == null)
            matchsDoublesChaise = new java.util.HashSet<MatchDouble>();
        return matchsDoublesChaise;
    }*/

    /** @pdGenerated default iterator getter */
    /*public java.util.Iterator getIteratorMatchsDoublesChaise() {
        if (matchsDoublesChaise == null)
            matchsDoublesChaise = new java.util.HashSet<MatchDouble>();
        return matchsDoublesChaise.iterator();
    }*/

    /** @pdGenerated default setter
      * @param newMatchDouble */
    /*public void setMatchsDoublesChaises(java.util.List<MatchDouble> newMatchsDoublesChaise) {
        removeAllMatchsDoublesChaise();
        for (java.util.Iterator iter = newMatchsDoublesChaise.iterator(); iter.hasNext();)
            addMatchDoubleChaise((MatchDouble)iter.next());
    }*/
   
    /** @pdGenerated default add
      * @param newMatchDouble */
    /*public void addMatchDoubleChaise(MatchDouble newMatchDouble) {
        if (newMatchDouble == null)
            return;
        if (this.matchsDoublesChaise == null)
            this.matchsDoublesChaise = new java.util.HashSet<MatchDouble>();
        if (!this.matchsDoublesChaise.contains(newMatchDouble)) {
            this.matchsDoublesChaise.add(newMatchDouble);
            newMatchDouble.setArbitreChaise(this);      
        }
    }*/
   
    /** @pdGenerated default remove
      * @param oldMatchDouble */
    /*public void removeMatchDoubleChaise(MatchDouble oldMatchDouble) {
        if (oldMatchDouble == null)
            return;
        if (this.matchsDoublesChaise != null)
            if (this.matchsDoublesChaise.contains(oldMatchDouble)) {
                this.matchsDoublesChaise.remove(oldMatchDouble);
                oldMatchDouble.setArbitreChaise((Arbitre)null);
            }
    }*/
   
    /** @pdGenerated default removeAll */
    /*public void removeAllMatchsDoublesChaise() {
        if (matchsDoublesChaise != null) {
            MatchDouble oldMatchDouble;
            for (java.util.Iterator iter = getIteratorMatchsDoublesChaise(); iter.hasNext();) {
                oldMatchDouble = (MatchDouble)iter.next();
                iter.remove();
                oldMatchDouble.setArbitreChaise((Arbitre)null);
            }
        }
    }*/
        
    /** @pdGenerated default getter */
    /*public java.util.List<MatchSimple> getMatchsSimplesChaise() {
        if (matchsSimplesChaise == null)
            matchsSimplesChaise = new java.util.HashSet<MatchSimple>();
        return matchsSimplesChaise;
    }*/

    /** @pdGenerated default iterator getter */
    /*public java.util.Iterator getIteratorMatchsSimplesChaise() {
        if (matchsSimplesChaise == null)
            matchsSimplesChaise = new java.util.HashSet<MatchSimple>();
        return matchsSimplesChaise.iterator();
    }*/

    /** @pdGenerated default setter
      * @param newMatchSimple */
    /*public void setMatchsSimplesChaises(java.util.List<MatchSimple> newMatchsSimplesChaise) {
        removeAllMatchsSimplesChaise();
        for (java.util.Iterator iter = newMatchsSimplesChaise.iterator(); iter.hasNext();)
            addMatchSimpleChaise((MatchSimple)iter.next());
    }*/
   
    /** @pdGenerated default add
      * @param newMatchSimple */
    /*public void addMatchSimpleChaise(MatchSimple newMatchSimple) {
        if (newMatchSimple == null)
            return;
        if (this.matchsSimplesChaise == null)
            this.matchsSimplesChaise = new java.util.HashSet<MatchSimple>();
        if (!this.matchsSimplesChaise.contains(newMatchSimple)) {
            this.matchsSimplesChaise.add(newMatchSimple);
            newMatchSimple.setArbitreChaise(this);      
        }
    }*/
   
    /** @pdGenerated default remove
      * @param oldMatchSimple */
    /*public void removeMatchSimpleChaise(MatchSimple oldMatchSimple) {
        if (oldMatchSimple == null)
            return;
        if (this.matchsSimplesChaise != null)
            if (this.matchsSimplesChaise.contains(oldMatchSimple)) {
                this.matchsSimplesChaise.remove(oldMatchSimple);
                oldMatchSimple.setArbitreChaise((Arbitre)null);
            }
    }*/
   
    /** @pdGenerated default removeAll */
    /*public void removeAllMatchsSimplesChaise() {
        if (matchsSimplesChaise != null) {
            MatchSimple oldMatchSimple;
            for (java.util.Iterator iter = getIteratorMatchsSimplesChaise(); iter.hasNext();) {
                oldMatchSimple = (MatchSimple)iter.next();
                iter.remove();
                oldMatchSimple.setArbitreChaise((Arbitre)null);
            }
        }
    }*/
    
    /** @pdGenerated default getter */
    /*public java.util.List<Match> getMatchsLigne() {
        if (matchsLigne == null)
            matchsLigne = new java.util.HashSet<Match>();
        return matchsLigne;
    }*/
   
    /** @pdGenerated default iterator getter */
    /*public java.util.Iterator getIteratorMatchsLigne() {
        if (matchsLigne == null)
            matchsLigne = new java.util.HashSet<Match>();
        return matchsLigne.iterator();
    }*/
   
    /** @pdGenerated default setter
      * @param newMatch */
    /*public void setMatchsLigne(java.util.List<Match> newMatchsLigne) {
        removeAllMatch();
        for (java.util.Iterator iter = newMatchsLigne.iterator(); iter.hasNext();)
            addMatchLigne((Match)iter.next());
    }*/
   
    /** @pdGenerated default add
      * @param newMatch */
    /*public void addMatchLigne(Match newMatch) {
        if (newMatch == null)
            return;
        if (this.matchsLigne == null)
            this.matchsLigne = new java.util.HashSet<Match>();
        if (!this.matchsLigne.contains(newMatch)) {
            this.matchsLigne.add(newMatch);
            newMatch.addArbitreLigne(this);      
        }
    }*/
   
    /** @pdGenerated default remove
      * @param oldMatch */
    /*public void removeMatch(Match oldMatch) {
        if (oldMatch == null)
            return;
        if (this.matchsLigne != null)
            if (this.matchsLigne.contains(oldMatch)) {
                this.matchsLigne.remove(oldMatch);
                oldMatch.removeArbitreLigne(this);
            }
    }*/
   
    /** @pdGenerated default removeAll */
    /*public void removeAllMatch() {
        if (matchsLigne != null) {
            Match oldMatch;
            for (java.util.Iterator iter = getIteratorMatchsLigne(); iter.hasNext();) {
                oldMatch = (Match)iter.next();
                iter.remove();
                oldMatch.removeArbitreLigne(this);
            }
        }
    }*/
    
    
    
    
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
            ResultSet result = statement.executeQuery("select * from arbitre");

            while (result.next()) {
                Arbitre arbitre = new Arbitre(
                        result.getInt("idarbitre"), 
                        result.getString("certification"), 
                        result.getString("nom"), 
                        result.getString("prenom"),
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