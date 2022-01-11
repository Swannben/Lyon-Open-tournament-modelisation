/***********************************************************************
 * Module:  MatchDouble.java
 * Author:  swann
 * Purpose: Defines the Class MatchDouble
 ***********************************************************************/

package planning_des_matchs;

import java.util.*;

public class MatchDouble extends Match {
    public java.util.Collection<Equipe> equipes;
    public Arbitre arbitreChaise;
    int id;
    Creneau creneau;
    Court court;
    private java.util.Collection<Arbitre> arbitresLigne;
    private java.util.Collection<EquipeRamassage> equipesRamassage;

    public MatchDouble(int id, Creneau creneau, Court court) {
        super(id,creneau,court);
        equipes = new ArrayList(2);
    }
    
    
    /** @pdGenerated default getter */
    public java.util.Collection<Equipe> getEquipes() {
        if (equipes == null)
            equipes = new java.util.HashSet<Equipe>();
        return equipes;
    }
   
    /** @pdGenerated default iterator getter */
    public java.util.Iterator getIteratorEquipes() {
        if (equipes == null)
            equipes = new java.util.HashSet<Equipe>();
        return equipes.iterator();
    }
   
    /** @pdGenerated default setter
      * @param newEquipe */
    public void setEquipes(java.util.Collection<Equipe> newEquipes) {
        removeAllEquipes();
        for (java.util.Iterator iter = newEquipes.iterator(); iter.hasNext();)
            addEquipe((Equipe)iter.next());
    }
   
    /** @pdGenerated default add
      * @param newEquipe */
    public void addEquipe(Equipe newEquipe) {
        if (newEquipe == null)
           return;
        if (this.equipes == null)
           this.equipes = new java.util.HashSet<Equipe>();
        if (!this.equipes.contains(newEquipe)) {
           this.equipes.add(newEquipe);
           newEquipe.addMatchDouble(this);      
        }
    }
   
    /** @pdGenerated default remove
      * @param oldEquipe */
    public void removeEquipe(Equipe oldEquipe) {
        if (oldEquipe == null)
            return;
        if (this.equipes != null) {
            if (this.equipes.contains(oldEquipe)) {
                this.equipes.remove(oldEquipe);
                oldEquipe.removeMatchDouble(this);
            }
        }
    }
   
    /** @pdGenerated default removeAll */
    public void removeAllEquipes() {
        if (equipes != null) {
            Equipe oldEquipe;
            for (java.util.Iterator iter = getIteratorEquipes(); iter.hasNext();) {
                oldEquipe = (Equipe)iter.next();
                iter.remove();
                oldEquipe.removeMatchDouble(this);
            }
        }
    }
    
    /** @pdGenerated default parent getter */
    public Arbitre getArbitreChaise() {
        return arbitreChaise;
    }
   
    /** @pdGenerated default parent setter
      * @param newArbitre */
    public void setArbitreChaise(Arbitre newArbitre) {
        if (this.arbitreChaise == null || !this.arbitreChaise.equals(newArbitre)) {
            if (this.arbitreChaise != null) {
                Arbitre oldArbitre = this.arbitreChaise;
                this.arbitreChaise = null;
                oldArbitre.removeMatchDoubleChaise(this);
            }
            if (newArbitre != null) {
                this.arbitreChaise = newArbitre;
                this.arbitreChaise.addMatchDoubleChaise(this);
            }
        }
    }

}