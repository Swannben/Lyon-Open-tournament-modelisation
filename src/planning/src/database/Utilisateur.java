package database;


public class Utilisateur {
    
    public Utilisateur() {
        
    }


    public int getId () { 
        return type; 
    }
    
    public void setId(int type) { 
        this.type = type;
    }
    
    public String getNom () { 
        return nom; 
    }
    
    public void setNom(String nom) { 
        this.nom = nom;
    }
    
    public int getType () { 
        return type; 
    }
    
    public void setType(int type) { 
        this.type = type;
    }
    
    
    private int id = -1;
    private String nom = null;
    private int type = -1;
}
