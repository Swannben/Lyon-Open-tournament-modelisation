/***********************************************************************
 * Module:  Match.java
 * Author:  swann
 * Purpose: Defines the Class Match
 ***********************************************************************/

package planning_des_matchs;

import database.DatabaseConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;


public abstract class Match {
    protected final int id;
    protected Creneau creneau;
    private List<Set> score;    // sets gagnés
    protected java.util.List<Arbitre> arbitresLigne;
    private java.util.List<EquipeRamassage> equipesRamassage;
    Match matchA;
    Match matchB;
    protected Arbitre arbitreChaise;
    protected int profondeur;



    abstract public Match getMatchA();

    public void setMatchA(Match matchA) {
        this.matchA = matchA;
    }

    abstract public Match getMatchB() ;

    public void setMatchB(Match matchB) {
        this.matchB = matchB;
    }

    public int getProfondeur() {
        return profondeur;
    }

    public void setProfondeur(int profondeur) {
        this.profondeur = profondeur;
    }
   
    
    protected static class Set {
        public Set() {}
        
        public Set(int jeux1, int jeux2, int pointsDernierJeuPerdant1, int pointsDernierJeuPerdant2) {
            this.jeux1 = jeux1;
            this.jeux2 = jeux2;
            this.pointsDernierJeuPerdant1 = pointsDernierJeuPerdant1;
            this.pointsDernierJeuPerdant2 = pointsDernierJeuPerdant2;
        }
        
        public int jeux1 = 0;
        public int jeux2 = 0;
        public int pointsDernierJeuPerdant1 = 0;
        public int pointsDernierJeuPerdant2 = 0;
    }
    
    

    
    
    public int getID() {
        return id;
    }
    public Match(){
        id=0;
    }
   
    public Match(int id, Creneau creneau, List<Set> score, java.util.List<Arbitre> arbitresLigne, java.util.List<EquipeRamassage> equipesRamassage) {
        this.id = id;
        setCreneau(creneau);
        
        if (score == null)
            score = new ArrayList(2);
        this.score = score;
        
        if (arbitresLigne == null)
            arbitresLigne = new ArrayList(6);
        this.arbitresLigne = arbitresLigne;

        if (equipesRamassage == null)
            equipesRamassage = new ArrayList(2);
        this.equipesRamassage = equipesRamassage;
    }
        
    public Creneau getCreneau() {
        return creneau;
    }
    
    /** @param newCreneau */
    public void setCreneau(Creneau newCreneau) {
        if (this.creneau == null || !this.creneau.equals(newCreneau)) {
            if (this.creneau != null) {
                Creneau oldCreneau = this.creneau;
                this.creneau = null;
                oldCreneau.setMatch(null);
            }
            if (newCreneau != null) {
                this.creneau = newCreneau;
                this.creneau.setMatch(this);
            }
        }    
    }
   
    /** @pdGenerated default getter */
    public java.util.List<Arbitre> getArbitresLigne() {
        if (arbitresLigne == null)
            arbitresLigne = new java.util.ArrayList<Arbitre>();
        return arbitresLigne;
    }
   
    /** @pdGenerated default iterator getter */
    public java.util.Iterator getIteratorArbitresLigne() {
        if (arbitresLigne == null)
            arbitresLigne = new java.util.ArrayList<Arbitre>();
        return arbitresLigne.iterator();
   }
   
    /** @pdGenerated default setter
      * @param newLigne */
    public void setArbitresLigne(java.util.List<Arbitre> newLigne) {
        removeAllArbitresLigne();
        for (java.util.Iterator iter = newLigne.iterator(); iter.hasNext();)
            addArbitreLigne((Arbitre)iter.next());
    }
   
    /** @pdGenerated default add
      * @param newArbitre */
    public void addArbitreLigne(Arbitre newArbitre) {
        if (this.arbitresLigne == null)
            this.arbitresLigne = new java.util.ArrayList<Arbitre>();
        if (!this.arbitresLigne.contains(newArbitre)) {
            this.arbitresLigne.add(newArbitre);
        }
    }
   
    /** @pdGenerated default remove
      * @param oldArbitre */
    public void removeArbitreLigne(Arbitre oldArbitre) {
        if (oldArbitre == null)
           return;
        if (this.arbitresLigne != null) {
            if (this.arbitresLigne.contains(oldArbitre)) {
                this.arbitresLigne.remove(oldArbitre);
            }
        }
    }
   
    /** @pdGenerated default removeAll */
    public void removeAllArbitresLigne() {
        if (arbitresLigne != null) {
            Arbitre oldArbitre;
            for (java.util.Iterator iter = getIteratorArbitresLigne(); iter.hasNext();) {
                oldArbitre = (Arbitre)iter.next();
                iter.remove();
            }
        }
    }
    
    
    abstract public void assignerArbitres();


    /** @pdGenerated default getter */
    public java.util.List<EquipeRamassage> getEquipesRamassage() {
        if (equipesRamassage == null)
            equipesRamassage = new java.util.ArrayList<EquipeRamassage>();
        return equipesRamassage;
    }
   
    /** @pdGenerated default iterator getter */
    public java.util.Iterator getIteratorEquipesRamassage() {
        if (equipesRamassage == null)
            equipesRamassage = new java.util.ArrayList<EquipeRamassage>();
        return equipesRamassage.iterator();
    }
   
    /** @pdGenerated default setter
      * @param newEquipeRamassage */
    public void setEquipesRamassage(java.util.List<EquipeRamassage> newEquipeRamassage) {
        removeAllEquipesRamassage();
        for (java.util.Iterator iter = newEquipeRamassage.iterator(); iter.hasNext();)
            addEquipeRamassage((EquipeRamassage)iter.next());
    }
   
    /** @pdGenerated default add
      * @param newEquipeRamassage */
    public void addEquipeRamassage(EquipeRamassage newEquipeRamassage) {
        if (newEquipeRamassage == null)
            return;
        if (this.equipesRamassage == null)
            this.equipesRamassage = new java.util.ArrayList<EquipeRamassage>();
        if (!this.equipesRamassage.contains(newEquipeRamassage)) {
            this.equipesRamassage.add(newEquipeRamassage);
        }
    }
   
    /** @pdGenerated default remove
      * @param oldEquipeRamassage */
    public void removeEquipeRamassage(EquipeRamassage oldEquipeRamassage) {
        if (oldEquipeRamassage == null)
            return;
        if (this.equipesRamassage != null) {
            if (this.equipesRamassage.contains(oldEquipeRamassage)) {
                this.equipesRamassage.remove(oldEquipeRamassage);
            }
        }
    }
   
    /** @pdGenerated default removeAll */
    public void removeAllEquipesRamassage() {
        if (equipesRamassage != null) {
            EquipeRamassage oldEquipeRamassage;
            for (java.util.Iterator iter = getIteratorEquipesRamassage(); iter.hasNext();)
            {
                oldEquipeRamassage = (EquipeRamassage)iter.next();
                iter.remove();
            }
        }
    }
    
    public void assignerEquipesRamassage(){
        int i;
        //TODO gérer l'emploi du temps des listes
        List<EquipeRamassage> listEquipes = EquipeRamassage.getList();
        int randomIndex = new Random().nextInt(listEquipes.size());
        for (i=0;i<2;i++){
            equipesRamassage.add(listEquipes.get(randomIndex));
        }
    }
    
    
        /** @pdGenerated default parent getter */
    public Arbitre getArbitreChaise() {
        return arbitreChaise;
    }
   
    /** @pdGenerated default parent setter
      * @param newArbitre */
    abstract public void setArbitreChaise(Arbitre newArbitre);

}
