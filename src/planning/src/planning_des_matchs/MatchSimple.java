/***********************************************************************
 * Module:  MatchSimple.java
 * Author:  swann
 * Purpose: Defines the Class MatchSimple
 ***********************************************************************/

package planning_des_matchs;

import java.util.*;

public class MatchSimple extends Match {
    private java.util.List<Joueur> joueurs;
    private boolean estQualif;
        
    
    public boolean estQualif() {
        return estQualif;
    }

    /** @param newEstQualif */
    public void setEstQualif(boolean newEstQualif) {
        estQualif = newEstQualif;
    }

   
    public MatchSimple(int id, Creneau creneau, Court court, boolean estQualif, Arbitre arbitreChaise, Joueur joueur1,Joueur joueur2) {
        super(id,creneau,court);
        this.estQualif=estQualif;
        setArbitreChaise(arbitreChaise);
        joueurs = new ArrayList(2);
        joueurs.add(joueur1);
        joueurs.add(joueur2);
    }
    
    
    /** @pdGenerated default getter */
    public java.util.List<Joueur> getJoueurs() {
        if (joueurs == null)
            joueurs = new java.util.HashSet<Joueur>();
        return joueurs;
    }
   
    /** @pdGenerated default iterator getter */
    public java.util.Iterator getIteratorJoueurs() {
        if (joueurs == null)
            joueurs = new java.util.HashSet<Joueur>();
        return joueurs.iterator();
    }
   
    /** @pdGenerated default setter
      * @param newJoueur */
    public void setJoueurs(java.util.List<Joueur> newJoueurs) {
        removeAllJoueurs();
        for (java.util.Iterator iter = newJoueurs.iterator(); iter.hasNext();)
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
           newJoueur.addMatchSimple(this);      
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
                oldJoueur.removeMatchSimple(this);
            }
        }
    }
   
    /** @pdGenerated default removeAll */
    public void removeAllJoueurs() {
        if (joueurs != null) {
            Joueur oldJoueur;
            for (java.util.Iterator iter = getIteratorJoueurs(); iter.hasNext();) {
                oldJoueur = (Joueur)iter.next();
                iter.remove();
                oldJoueur.removeMatchSimple(this);
            }
        }
    }
   
    /** @pdGenerated default parent setter
      * @param newArbitre */
    public void setArbitreChaise(Arbitre newArbitre) {
        if (this.arbitreChaise == null || !this.arbitreChaise.equals(newArbitre)) {
            if (this.arbitreChaise != null) {
                Arbitre oldArbitre = this.arbitreChaise;
                this.arbitreChaise = null;
                oldArbitre.subMatchSimple();
            }
            if (newArbitre != null) {
                this.arbitreChaise = newArbitre;
                this.arbitreChaise.addMatchDouble();
            }
        }
    }

    @Override
    public void assignerArbitre() {
        arbitres=Arbitre.getArbitres();
        
        Arbitre selectionne;
        selectionne = arbitres.get(new Random().nextInt(arbitres.size()));
        
        if (selectionne=
    }


}