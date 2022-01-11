/***********************************************************************
 * Module:  Date.java
 * Author:  swann
 * Purpose: Defines the Class Jour
 ***********************************************************************/

package planning_des_matchs;

import java.util.*;

public class Jour {
    private java.util.Date date;

    public java.util.Collection<Creneau> creneaux;
    public java.util.Collection<Entrainement> entrainements;

    public java.util.Date getDate() {
        return date;
    }

    /** @param newDate */
    public void setDate(java.util.Date newDate) {
        date = newDate;
    }
   
    public Jour(Date date) {
        this.date =date;
    }
   
   
    /** @pdGenerated default getter */
    public java.util.Collection<Creneau> getCreneaux() {
        if (creneaux == null)
            creneaux = new java.util.HashSet<Creneau>();
        return creneaux;
    }
   
    /** @pdGenerated default iterator getter */
    public java.util.Iterator getIteratorCreneaux() {
        if (creneaux == null)
            creneaux = new java.util.HashSet<Creneau>();
        return creneaux.iterator();
    }
   
    /** @pdGenerated default setter
      * @param newCreneaux */
    public void setCreneaux(java.util.Collection<Creneau> newCreneaux) {
        removeAllCreneaux();
        for (java.util.Iterator iter = newCreneaux.iterator(); iter.hasNext();)
            addCreneau((Creneau)iter.next());
    }
   
    /** @pdGenerated default add
      * @param newCreneau */
    public void addCreneau(Creneau newCreneau) {
        if (newCreneau == null)
            return;
        if (this.creneaux == null)
            this.creneaux = new java.util.HashSet<Creneau>();
        if (!this.creneaux.contains(newCreneau)) {
            this.creneaux.add(newCreneau);
            newCreneau.setJour(this);      
        }
    }
   
   /** @pdGenerated default remove
     * @param oldCreneau */
   public void removeCreneau(Creneau oldCreneau) {
      if (oldCreneau == null)
         return;
      if (this.creneaux != null)
         if (this.creneaux.contains(oldCreneau))
         {
            this.creneaux.remove(oldCreneau);
            oldCreneau.setJour((Jour)null);
         }
   }
   
    /** @pdGenerated default removeAll */
    public void removeAllCreneaux() {
        if (creneaux != null) {
            Creneau oldCreneau;
            for (java.util.Iterator iter = getIteratorCreneaux(); iter.hasNext();) {
                oldCreneau = (Creneau)iter.next();
                iter.remove();
                oldCreneau.setJour((Jour)null);
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
            newEntrainement.setJour(this);      
        }
    }

    /** @pdGenerated default remove
      * @param oldEntrainement */
    public void removeEntrainement(Entrainement oldEntrainement) {
        if (oldEntrainement == null)
            return;
        if (this.entrainements != null) {
            if (this.entrainements.contains(oldEntrainement)) {
                this.entrainements.remove(oldEntrainement);
                oldEntrainement.setJour((Jour)null);
            }
        }
    }

    /** @pdGenerated default removeAll */
    public void removeAllEntrainements() {
        if (entrainements != null) {
            Entrainement oldEntrainement;
            for (java.util.Iterator iter = getIteratorEntrainements(); iter.hasNext();) {
                oldEntrainement = (Entrainement)iter.next();
                iter.remove();
                oldEntrainement.setJour((Jour)null);
            }
        }
    }

}