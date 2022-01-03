/***********************************************************************
 * Module:  Equipe.java
 * Author:  swann
 * Purpose: Defines the Class Equipe
 ***********************************************************************/

package planning_des_matchs;

import java.util.*;

public class Equipe {
    private int id;

    public java.util.Collection<Joueur> joueurs;
    public java.util.Collection<MatchDouble> matchsDoubles;

    public int getID() {
        return id;
    }

    public Equipe() {
        // TODO: implement
        joueurs = new ArrayList(2);
    }


    /** @pdGenerated default getter */
    public java.util.Collection<Joueur> getJoueurs() {
        if (joueurs == null)
            joueurs = new java.util.HashSet<Joueur>();
        return joueurs;
    }

    /** @pdGenerated default iterator getter */
    public java.util.Iterator getIteratorJoueur() {
        if (joueurs == null)
            joueurs = new java.util.HashSet<Joueur>();
        return joueurs.iterator();
    }
   
    /** @pdGenerated default setter
      * @param newJoueur */
    public void setJoueurs(java.util.Collection<Joueur> newJoueur) {
        removeAllJoueurs();
        for (java.util.Iterator iter = newJoueur.iterator(); iter.hasNext();)
            addJoueur((Joueur)iter.next());
    }

    /** @pdGenerated default add
      * @param newJoueur */
    public void addJoueur(Joueur newJoueur) {
        if (newJoueur == null)
            return;
        if (this.joueurs == null)
            this.joueurs = new java.util.HashSet<Joueur>();
        if (!this.joueurs.contains(newJoueur)) {
            this.joueurs.add(newJoueur);
            newJoueur.setEquipe(this);      
        }
    }
   
    /** @pdGenerated default remove
      * @param oldJoueur */
    public void removeJoueur(Joueur oldJoueur) {
        if (oldJoueur == null)
            return;
        if (this.joueurs != null) {
            if (this.joueurs.contains(oldJoueur)) {
                this.joueurs.remove(oldJoueur);
                oldJoueur.setEquipe((Equipe)null);
            }
        }
    }
   
    /** @pdGenerated default removeAll */
    public void removeAllJoueurs() {
        if (joueurs != null) {
            Joueur oldJoueur;
            for (java.util.Iterator iter = getIteratorJoueur(); iter.hasNext();) {
                oldJoueur = (Joueur)iter.next();
                iter.remove();
                oldJoueur.setEquipe((Equipe)null);
            }
        }
    }
    
    /** @pdGenerated default getter */
    public java.util.Collection<MatchDouble> getMatchsDoubles() {
        if (matchsDoubles == null)
            matchsDoubles = new java.util.HashSet<MatchDouble>();
        return matchsDoubles;
    }
   
    /** @pdGenerated default iterator getter */
    public java.util.Iterator getIteratorMatchsDoubles() {
        if (matchsDoubles == null)
            matchsDoubles = new java.util.HashSet<MatchDouble>();
        return matchsDoubles.iterator();
    }
   
    /** @pdGenerated default setter
      * @param newMatchDouble */
    public void setMatchsDoubles(java.util.Collection<MatchDouble> newMatchDouble) {
        removeAllMatchsDoubles();
        for (java.util.Iterator iter = newMatchDouble.iterator(); iter.hasNext();)
            addMatchDouble((MatchDouble)iter.next());
    }
   
    /** @pdGenerated default add
      * @param newMatchDouble */
    public void addMatchDouble(MatchDouble newMatchDouble) {
        if (newMatchDouble == null)
            return;
        if (this.matchsDoubles == null)
            this.matchsDoubles = new java.util.HashSet<MatchDouble>();
        if (!this.matchsDoubles.contains(newMatchDouble)) {
            this.matchsDoubles.add(newMatchDouble);
            newMatchDouble.addEquipe(this);      
        }
    }

    /** @pdGenerated default remove
      * @param oldMatchDouble */
    public void removeMatchDouble(MatchDouble oldMatchDouble) {
        if (oldMatchDouble == null)
            return;
        if (this.matchsDoubles != null)
            if (this.matchsDoubles.contains(oldMatchDouble)) {
                this.matchsDoubles.remove(oldMatchDouble);
                oldMatchDouble.removeEquipe(this);
            }
    }
   
    /** @pdGenerated default removeAll */
    public void removeAllMatchsDoubles() {
        if (matchsDoubles != null) {
            MatchDouble oldMatchDouble;
            for (java.util.Iterator iter = getIteratorMatchsDoubles(); iter.hasNext();) {
                oldMatchDouble = (MatchDouble)iter.next();
                iter.remove();
                oldMatchDouble.removeEquipe(this);
            }
        }
    }

}