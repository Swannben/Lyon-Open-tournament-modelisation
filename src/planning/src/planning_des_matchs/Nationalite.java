/***********************************************************************
 * Module:  Nationalite.java
 * Author:  swann
 * Purpose: Defines the Class Nationalite
 ***********************************************************************/

package planning_des_matchs;

public class Nationalite {
    private int id;
    private String libelle;
    // TODO: On doit mettre une collection joueurs ?
    
    Nationalite() {
        // TODO
    }
    
    public int getID() {
        return id;
    }

    public String getLibelle() {
        return libelle;
    }
    
    public void setLibelle(String newLibelle) {
        libelle = newLibelle;
    }
    
}
