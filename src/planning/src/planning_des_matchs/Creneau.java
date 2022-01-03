/***********************************************************************
 * Module:  Creneau.java
 * Author:  swann
 * Purpose: Defines the Class Creneau
 ***********************************************************************/

package planning_des_matchs;

import java.util.*;

public class Creneau {
    private int heure;
    public Jour jour;

    public java.util.Collection<Match> matchs;
   
   
    public int getHeure() {
        return heure;
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
            newMatch.setCreneau(this);      
        }
    }
   
    /** @pdGenerated default remove
      * @param oldMatch */
    public void removeMatch(Match oldMatch) {
        if (oldMatch == null)
            return;
        if (this.matchs != null)
            if (this.matchs.contains(oldMatch)) {
                this.matchs.remove(oldMatch);
                oldMatch.setCreneau((Creneau)null);
           }
    }
   
    /** @pdGenerated default removeAll */
    public void removeAllMatchs() {
        if (matchs != null) {
            Match oldMatch;
            for (java.util.Iterator iter = getIteratorMatchs(); iter.hasNext();) {
                oldMatch = (Match)iter.next();
                iter.remove();
                oldMatch.setCreneau((Creneau)null);
            }
        }
    }
    
    /** @pdGenerated default parent getter */
    public Jour getJour() {
        return jour;
    }
   
    /** @pdGenerated default parent setter
      * @param newJour */
    public void setJour(Jour newJour) {
        if (this.jour == null || !this.jour.equals(newJour))
        {
            if (this.jour != null) {
                Jour oldJour = this.jour;
                this.jour = null;
                oldJour.removeCreneau(this);
            }
            if (newJour != null) {
                this.jour = newJour;
                this.jour.addCreneau(this);
            }
        }
    }

}