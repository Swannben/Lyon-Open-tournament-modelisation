/***********************************************************************
 * Module:  VIP.java
 * Author:  swann
 * Purpose: Defines the Class VIP
 ***********************************************************************/

package model.entity;

import model.database.DatabaseConnection;
import java.util.*;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;


public abstract class VIP {
    protected final int id;
    private int idUtilisateur;
    private String nom;
    private String prenom;
    private String numeroTel;
    private int type;
    private Nationalite nationalite;
    
    
    public VIP(int idVIP, int idUtilisateur, String nom, String prenom, String numeroTel, 
            int type, Nationalite nationalite) {
        this.id=idVIP;
        this.idUtilisateur = idUtilisateur;
        this.nom=nom;
        this.prenom=prenom;
        this.numeroTel = numeroTel;
        this.type = type;
        this.nationalite=nationalite;
    }

    public Nationalite getNationalite() {
        return nationalite;
    }

    public void setNationalite(Nationalite nationalite) {
        this.nationalite = nationalite;
    }
    
    
    public int getId() {
        return id;
    }
   
    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    /** @param newIdUtilisateur */
    public void setIdUtilisateur(int newIdUtilisateur) {
        idUtilisateur = newIdUtilisateur;
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

    public String getNumeroTel() {
        return numeroTel;
    }

    /** @param newNumeroTel */
    public void setNumeroTel(String newNumeroTel) {
        numeroTel = newNumeroTel;
    }

    public int getType() {
        return type;
    }

    /** @param newType */
    public void setType(int newType) {
        type = newType;
    }

}