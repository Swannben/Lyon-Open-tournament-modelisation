/***********************************************************************
 * Module:  Entrainement.java
 * Author:  swann
 * Purpose: Defines the Class Entrainement
 ***********************************************************************/

package model.entity;

import java.util.*;

public class Entrainement {
    private int id;
    private int heure;      // TODO: L'heure est un int ou c'est plus complexe ?
    private int duree;      // TODO: La duree est en minute ?

    public Jour jour;
    public Court court;
    
    public Entrainement(int id, int heure, int duree, Jour jour, Court court){
        this.id=id;
        this.heure=heure;
        this.duree=duree;
        this.jour=jour;
        this.court=court;
    }

    public int getID() {
       return id;
    }

    public int getHeure() {
       return heure;
    }

    /** @param newHeure */
    public void setHeure(int newHeure) {
       heure = newHeure;
    }

    public int getDuree() {
       return duree;
    }

    /** @param newDuree */
    public void setDuree(int newDuree) {
       duree = newDuree;
    }

    public Entrainement() {
       // TODO: implement
    }
   
    /** @pdGenerated default parent getter */
    public Jour getJour() {
        return jour;
    }
   
    /** @pdGenerated default parent setter
      * @param newJour */
    public void setJour(Jour newJour) {
        if (this.jour == null || !this.jour.equals(newJour)) {
            if (this.jour != null) {
                Jour oldJour = this.jour;
                this.jour = null;
                oldJour.removeEntrainement(this);
            }
            if (newJour != null) {
                this.jour = newJour;
                this.jour.addEntrainement(this);
            }
        }
    }

    
    /** @pdGenerated default parent getter */
    public Court getCourt() {
        return court;
    }
   
    /** @pdGenerated default parent setter
      * @param newCourt */
    public void setCourt(Court newCourt) {
        if (this.court == null || !this.court.equals(newCourt)) {
            if (this.court != null) {
                Court oldCourt = this.court;
                this.court = null;
                oldCourt.removeEntrainement(this);
            }
            if (newCourt != null) {
                this.court = newCourt;
                this.court.addEntrainement(this);
            }
        }
    }

}