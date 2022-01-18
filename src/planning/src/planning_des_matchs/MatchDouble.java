/***********************************************************************
 * Module:  MatchDouble.java
 * Author:  swann
 * Purpose: Defines the Class MatchDouble
 ***********************************************************************/

package planning_des_matchs;

import java.util.*;

public class MatchDouble extends Match {
    private java.util.List<Equipe> equipes;


    public MatchDouble(int id, Creneau creneau, Court court,Arbitre arbitreChaise,java.util.List<Arbitre> arbitresLigne, 
            java.util.List<EquipeRamassage> equipesRamassage, List<Equipe> equipes) {
        super(id,creneau,court,arbitreChaise,arbitresLigne,equipesRamassage);
        
        if (equipes == null)
            equipes = new ArrayList(2);
        this.equipes = equipes;

        
    
    /** @pdGenerated default getter */
    public java.util.List<Equipe> getEquipes() {
        if (equipes == null)
            equipes = new java.util.ArrayList<Equipe>();
        return equipes;
    }
   
    /** @pdGenerated default iterator getter */
    public java.util.Iterator getIteratorEquipes() {
        if (equipes == null)
            equipes = new java.util.ArrayList<Equipe>();
        return equipes.iterator();
    }
   
    /** @pdGenerated default setter
      * @param newEquipe */
    public void setEquipes(java.util.List<Equipe> newEquipes) {
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
           this.equipes = new java.util.ArrayList<Equipe>();
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
   
    
    /** @pdGenerated default parent setter
      * @param newArbitre */
    @Override
    public void setArbitreChaise(Arbitre newArbitre) {
        if (this.arbitreChaise == null || !this.arbitreChaise.equals(newArbitre)) {
            if (this.arbitreChaise != null) {
                Arbitre oldArbitre = this.arbitreChaise;
                this.arbitreChaise = null;
                oldArbitre.subMatchDouble();
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
            if (arbitreChaise.getNbMatchsFaitsD()>=2 || arbitreChaise.getCertification()!="ITT1"){
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