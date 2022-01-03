/***********************************************************************
 * Module:  Court.java
 * Author:  swann
 * Purpose: Defines the Class Court
 ***********************************************************************/

package planning_des_matchs;

import java.util.*;

public class Court {
    private int id;
    private String nom;
    private boolean estPrincipal;

    public java.util.Collection<Match> matchs;
    public java.util.Collection<Entrainement> entrainements;

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
   
    public boolean estPrincipal() {
        return estPrincipal;
    }

    /** @param estPrincipal */
    public void setPrincipal(boolean estPrincipal) {
        this.estPrincipal = estPrincipal;
    }
   
    public Court() {
        // TODO: implement
    }
   
   
    /** @pdGenerated default getter */
    public java.util.Collection<Match> getMatchs() {
        if (matchs == null)
            matchs = new java.util.HashSet<Match>();
        return matchs;
    }

    /** @pdGenerated default iterator getter */
    public java.util.Iterator getIteratorMatchs() {
        if (matchs == null)
            matchs = new java.util.HashSet<Match>();
        return matchs.iterator();
    }

    /** @pdGenerated default setter
      * @param newMatch */
    public void setMatchs(java.util.Collection<Match> newMatch) {
        removeAllMatchs();
        for (java.util.Iterator iter = newMatch.iterator(); iter.hasNext();)
            addMatch((Match)iter.next());
    }

    /** @pdGenerated default add
      * @param newMatch */
    public void addMatch(Match newMatch) {
        if (newMatch == null)
            return;
        if (this.matchs == null)
            this.matchs = new java.util.HashSet<Match>();
        if (!this.matchs.contains(newMatch)) {
            this.matchs.add(newMatch);
            newMatch.setCourt(this);      
        }
    }

    /** @pdGenerated default remove
      * @param oldMatch */
    public void removeMatch(Match oldMatch) {
        if (oldMatch == null)
            return;
        if (this.matchs != null) {
            if (this.matchs.contains(oldMatch)) {
                this.matchs.remove(oldMatch);
                oldMatch.setCourt((Court)null);
            }
        }
    }

    /** @pdGenerated default removeAll */
    public void removeAllMatchs() {
        if (matchs != null) {
            Match oldMatch;
            for (java.util.Iterator iter = getIteratorMatchs(); iter.hasNext();) {
                oldMatch = (Match)iter.next();
                iter.remove();
                oldMatch.setCourt((Court)null);
            }
        }
    }
    
    /** @pdGenerated default getter */
    public java.util.Collection<Entrainement> getEntrainements() {
        if (entrainements == null)
            entrainements = new java.util.HashSet<Entrainement>();
        return entrainements;
    }

    /** @pdGenerated default iterator getter */
    public java.util.Iterator getIteratorEntrainements() {
        if (entrainements == null)
            entrainements = new java.util.HashSet<Entrainement>();
        return entrainements.iterator();
    }

    /** @pdGenerated default setter
      * @param newEntrainement */
    public void setEntrainements(java.util.Collection<Entrainement> newEntrainement) {
        removeAllEntrainements();
        for (java.util.Iterator iter = newEntrainement.iterator(); iter.hasNext();)
            addEntrainement((Entrainement)iter.next());
    }

    /** @pdGenerated default add
      * @param newEntrainement */
    public void addEntrainement(Entrainement newEntrainement) {
        if (newEntrainement == null)
            return;
        if (this.entrainements == null)
            this.entrainements = new java.util.HashSet<Entrainement>();
        if (!this.entrainements.contains(newEntrainement)) {
            this.entrainements.add(newEntrainement);
            newEntrainement.setCourt(this);      
        }
    }

    /** @pdGenerated default remove
      * @param oldEntrainement */
    public void removeEntrainement(Entrainement oldEntrainement) {
        if (oldEntrainement == null)
            return;
        if (this.entrainements != null)
            if (this.entrainements.contains(oldEntrainement)) {
                this.entrainements.remove(oldEntrainement);
                oldEntrainement.setCourt((Court)null);
            }
    }

    /** @pdGenerated default removeAll */
    public void removeAllEntrainements() {
        if (entrainements != null) {
            Entrainement oldEntrainement;
            for (java.util.Iterator iter = getIteratorEntrainements(); iter.hasNext();) {
                oldEntrainement = (Entrainement)iter.next();
                iter.remove();
                oldEntrainement.setCourt((Court)null);
            }
        }
    }

}