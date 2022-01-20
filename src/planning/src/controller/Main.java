/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.entity.Match;
import model.entity.Court;
import model.entity.MatchDouble;
import model.entity.Arbitre;
import model.entity.MatchSimple;
import model.entity.Equipe;
import model.entity.Creneau;
import model.entity.EquipeRamassage;
import model.entity.Joueur;
import model.entity.Jour;
import model.entity.Nationalite;
import view.Fenetre;

/**
 *
 * @author swann
 */

import java.util.*;

public class Main {
    
    public static void main(String[] args)
    {        
        Fenetre.main(args);
    }
    
    public static void retriveDatabase() {
        Nationalite.getListFromDatabase();
        Jour.getListFromDatabase();
        Creneau.getListFromDatabase();
        Arbitre.getListFromDatabase();
        Joueur.getListFromDatabase();
        Equipe.getListFromDatabase();
        EquipeRamassage.getListFromDatabase();
        Court.getListFromDatabase();
        //Entrainement.getListFromDatabase();
        MatchDouble.getListFromDatabase();
        MatchSimple.getListFromDatabase();
    }
    
    public static void cleanData() {
        // TODO
    }
    
    public static void genererTournoi(){
        int i;
        List<Joueur> joueursMatch, joueursPartQualif= Joueur.getJoueursPartQualif();
        List<MatchSimple> matchsQualif= new ArrayList();  //second tour
        MatchSimple matchQualif;
        Joueur joueur2,joueur1;
        for (i=0;i<4;i++){
            matchQualif = new MatchSimple();
            matchQualif.setProfondeur(1);
            matchQualif.setEstQualif(true);
            matchsQualif.add(matchQualif);
            matchQualif.setMatchA(new MatchSimple());
            matchQualif.setMatchB(new MatchSimple());
        }
        
        
        
        
        for (MatchSimple match : matchsQualif){
            
            joueursMatch= new ArrayList<Joueur>();
            joueur1=joueursPartQualif.get(new Random().nextInt(joueursPartQualif.size()));
            joueursPartQualif.remove(joueur1);
            joueursMatch.add(joueur1);
            joueur2=joueursPartQualif.get(new Random().nextInt(joueursPartQualif.size()));
            joueursPartQualif.remove(joueur2);
            joueursMatch.add(joueur2);
            
            matchQualif= match.getMatchA();
            matchQualif.setJoueurs(joueursMatch);
            matchQualif.assignerArbitres();
            matchQualif.setEstQualif(true);
            matchQualif.assignerEquipesRamassage();
            matchQualif.setProfondeur(2);
            //TODO ajouter le nouveau match dans la base de donnée 
            //TODO une fois la gestion du créneau ajouté remettre assigner arbitre et assigner équipe ramassage après l'assignation du créneau
            
            joueursMatch= new ArrayList<Joueur>();
            joueur1=joueursPartQualif.get(new Random().nextInt(joueursPartQualif.size()));
            joueursPartQualif.remove(joueur1);
            joueursMatch.add(joueur1);
            joueur2=joueursPartQualif.get(new Random().nextInt(joueursPartQualif.size()));
            joueursPartQualif.remove(joueur2);
            joueursMatch.add(joueur2);
            
            matchQualif= match.getMatchB();
            matchQualif.setJoueurs(joueursMatch);
            matchQualif.assignerArbitres();
            matchQualif.setEstQualif(true);
            matchQualif.assignerEquipesRamassage();
            matchQualif.setProfondeur(2);
            //TODO ajouter le nouveau match dans la base de donnée 
            //TODO une fois la gestion du créneau ajouté remettre assigner arbitre et assigner équipe ramassage après l'assignation du créneau
        }
        
        
        Match matchAct= new MatchDouble();  // match actuel 
        Match finaleDouble= matchAct;
        List<Match> matchsATraiter= new LinkedList<>();
        matchsATraiter.add(matchAct);
        do{
           matchAct.setMatchA(new MatchDouble());
           matchsATraiter.add(matchAct.getMatchA());
           matchAct.getMatchA().setProfondeur(matchAct.getProfondeur()+1);
           matchAct.setMatchB(new MatchDouble());
           matchsATraiter.add(matchAct.getMatchB());
           matchAct.getMatchA().setProfondeur(matchAct.getProfondeur()+1);
           matchsATraiter.remove(matchAct);
           matchAct=matchsATraiter.get(0);
           
        }while (matchAct.getProfondeur()<4);
        
        
        List<Equipe> equipesMatch, listeEquipe=Equipe.getList();
        Equipe equipe1, equipe2;
        MatchDouble matchD;
        for (Match match : matchsATraiter){
            matchD= (MatchDouble)match;
            equipesMatch= new ArrayList<>(2);
            equipe1= listeEquipe.get(new Random().nextInt(joueursPartQualif.size()));
            equipesMatch.add(equipe1);
            listeEquipe.remove(equipe1);
            equipe2= listeEquipe.get(new Random().nextInt(joueursPartQualif.size()));
            equipesMatch.add(equipe2);
            listeEquipe.remove(equipe2);  
            
            matchD.setEquipes(listeEquipe);
            matchD.assignerArbitres();
            matchD.assignerEquipesRamassage();
            match=matchD;
            //TODO ajouter le nouveau match dans la base de donnée 
            //TODO une fois la gestion du créneau ajouté remettre assigner arbitre et assigner équipe ramassage après l'assignation du créneau
        }
        
        //MatchSimple matchAct= new MatchSimple();  // match actuel 
        Match finaleSimple= matchAct;
        matchsATraiter= new LinkedList<>();
        matchsATraiter.add(matchAct);
        do{
           matchAct.setMatchA(new MatchSimple());
           matchsATraiter.add(matchAct.getMatchA());
           matchAct.getMatchA().setProfondeur(matchAct.getProfondeur()+1);
           matchAct.setMatchB(new MatchSimple());
           matchsATraiter.add(matchAct.getMatchB());
           matchAct.getMatchA().setProfondeur(matchAct.getProfondeur()+1);
           matchsATraiter.remove(matchAct);
           matchAct=matchsATraiter.get(0);
           
        }while (matchAct.getProfondeur()<4);
        
        
        List<Joueur> listeJoueur=Joueur.getList();
        MatchSimple matchS;
        for (Match match : matchsATraiter){
            matchS=(MatchSimple)match;
            joueursMatch= new ArrayList<>(2);
            joueur1= listeJoueur.get(new Random().nextInt(joueursPartQualif.size()));
            joueursMatch.add(joueur1);
            listeJoueur.remove(joueur1);
            joueur2= listeJoueur.get(new Random().nextInt(joueursPartQualif.size()));
            joueursMatch.add(joueur2);
            listeJoueur.remove(joueur2);  
            
            matchS.setJoueurs(listeJoueur);
            matchS.assignerArbitres();
            matchS.assignerEquipesRamassage();
            //TODO ajouter le nouveau match dans la base de donnée 
            //TODO une fois la gestion du créneau ajouté remettre assigner arbitre et assigner équipe ramassage après l'assignation du créneau
        }
        
        
        
    }
    
}
