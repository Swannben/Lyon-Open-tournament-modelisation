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
    private Boolean estQualifie;
    private Equipe equipe;
    

    public Joueur( int id, String nom, String prenom, Nationalite nationalite, Equipe equipe, Boolean estQualifie) {
        this.id=id;
        this.nationalite=nationalite;
        this.nom=nom;
        this.prenom=prenom;
        this.equipe=equipe;
        this.estQualifie=estQualifie;
    }
    
    static List<Joueur> getJoueursQualifie(){
        List<Joueur> joueurs= Joueur.getList();
        for (Joueur j :joueurs){
            if (!j.getEstQualifie()){
                joueurs.remove(j);
            }
        }
        
    }
    
    static List<Joueur> getJoueursPartQualif(){
        List<Joueur> joueurs= Joueur.getList();
        for (Joueur j :joueurs){
            if (j.getEstQualifie()){
                joueurs.remove(j);
            }
        }
        
    }
    
    public Boolean getEstQualifie(){
        return estQualifie;
    }
    
    public void setEstQualifie(Boolean estQualif){
        estQualifie=estQualif;
    }
    
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
    
   
    

}