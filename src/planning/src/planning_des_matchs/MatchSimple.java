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

   
    public MatchSimple(int id, Creneau creneau, Court court, boolean estQualif, Arbitre arbitreChaise,  java.util.List<Joueur> joueurs, java.util.List<Arbitre> arbitresLigne, 
            java.util.List<EquipeRamassage> equipesRamassage) {
        super(id, creneau, court, arbitreChaise, arbitresLigne, equipesRamassage);
        this.estQualif=estQualif;

        setArbitreChaise(arbitreChaise);

        if (joueurs == null)
            joueurs = new ArrayList(2);
        this.joueurs = joueurs;
    }
    
    
    /** @pdGenerated default getter */
    public java.util.List<Joueur> getJoueurs() {
        if (joueurs == null)
            joueurs = new java.util.ArrayList<Joueur>();
        return joueurs;
    }
   
    /** @pdGenerated default iterator getter */
    public java.util.Iterator getIteratorJoueurs() {
        if (joueurs == null)
            joueurs = new java.util.ArrayList<Joueur>();
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
           this.joueurs = new java.util.ArrayList<Joueur>();
        if (!this.joueurs.contains(newJoueur)) {
           this.joueurs.add(newJoueur);   
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
    public void assignerArbitres() {
        arbitres=Arbitre.getList();
        int i, c = 1;
        //TODO ajouter tous le traitement de l'emploi du temps de l'arbitre.
        arbitreChaise = arbitres.get(new Random().nextInt(arbitres.size()));
        
        while (c>0){
            c=0;
            if (arbitreChaise.getNbMatchsFaitsS()>=2 || arbitreChaise.getCertification()!="ITT1"){
                c++;
                for (i=0;i<joueurs.size();i++){
                    if (arbitreChaise.getNationalite().equals(joueurs.get(i).getNationalite())){
                        c++;
                    }
                }
            }
        }
        
        for (i=0;i<6;i++){
            arbitresLigne.add(arbitres.get(new Random().nextInt(arbitres.size())));

        }
        
        
    }


}