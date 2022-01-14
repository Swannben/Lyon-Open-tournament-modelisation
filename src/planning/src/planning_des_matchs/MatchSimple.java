/***********************************************************************
 * Module:  MatchSimple.java
 * Author:  swann
 * Purpose: Defines the Class MatchSimple
 ***********************************************************************/

package planning_des_matchs;

import java.util.*;

public class MatchSimple extends Match {
    public java.util.List<Joueur> joueurs;
    private boolean estQualif;
    
    
    
    
    public boolean estQualif() {
        return estQualif;
    }

    /** @param newEstQualif */
    public void setEstQualif(boolean newEstQualif) {
        estQualif = newEstQualif;
    }

   
    public MatchSimple(int id, Creneau creneau, Court court, boolean estQualif, Arbitre arbitreChaise, Joueur joueur1,Joueur joueur2) {
        super(id,creneau,court,arbitreChaise);
        this.estQualif=estQualif;
        joueurs = new ArrayList();
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
                oldArbitre.removeMatchSimpleChaise(this);
            }
            if (newArbitre != null) {
                this.arbitreChaise = newArbitre;
                this.arbitreChaise.addMatchSimpleChaise(this);
            }
        }
    }

    @Override
    public void assignerArbitres() {
        arbitres=Arbitre.getArbitres();
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