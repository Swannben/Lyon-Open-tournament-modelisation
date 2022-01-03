/***********************************************************************
 * Module:  Joueur.java
 * Author:  swann
 * Purpose: Defines the Class Joueur
 ***********************************************************************/

package planning_des_matchs;

import java.util.*;

public class Joueur {
    private int id;
    private String nom;
    private String prenom;
    private Nationalite nationalite;

    public Equipe equipe;
    public java.util.Collection<MatchSimple> matchsSimples;

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

    public Joueur() {
        // TODO: implement
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
    public java.util.Collection<MatchSimple> getMatchsSimples() {
        if (matchsSimples == null)
            matchsSimples = new java.util.HashSet<MatchSimple>();
        return matchsSimples;
    }
   
    /** @pdGenerated default iterator getter */
    public java.util.Iterator getIteratorMatchsSimples() {
        if (matchsSimples == null)
            matchsSimples = new java.util.HashSet<MatchSimple>();
        return matchsSimples.iterator();
    }
   
    /** @pdGenerated default setter
      * @param newMatchSimple */
    public void setMatchsSimples(java.util.Collection<MatchSimple> newMatchSimple) {
        removeAllMatchsSimples();
        for (java.util.Iterator iter = newMatchSimple.iterator(); iter.hasNext();)
            addMatchSimple((MatchSimple)iter.next());
    }
   
    /** @pdGenerated default add
      * @param newMatchSimple */
    public void addMatchSimple(MatchSimple newMatchSimple) {
        if (newMatchSimple == null)
            return;
        if (this.matchsSimples == null)
            this.matchsSimples = new java.util.HashSet<MatchSimple>();
        if (!this.matchsSimples.contains(newMatchSimple)) {
            this.matchsSimples.add(newMatchSimple);
            newMatchSimple.addJoueur(this);      
        }
    }
   
    /** @pdGenerated default remove
      * @param oldMatchSimple */
    public void removeMatchSimple(MatchSimple oldMatchSimple) {
        if (oldMatchSimple == null)
            return;
        if (this.matchsSimples != null)
            if (this.matchsSimples.contains(oldMatchSimple)) {
               this.matchsSimples.remove(oldMatchSimple);
               oldMatchSimple.removeJoueur(this);
            }
    }
   
    /** @pdGenerated default removeAll */
    public void removeAllMatchsSimples() {
        if (matchsSimples != null) {
            MatchSimple oldMatchSimple;
            for (java.util.Iterator iter = getIteratorMatchsSimples(); iter.hasNext();) {
                oldMatchSimple = (MatchSimple)iter.next();
                iter.remove();
                oldMatchSimple.removeJoueur(this);
            }
        }
    }

}