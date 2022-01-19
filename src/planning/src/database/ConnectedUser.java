package database;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;


public class ConnectedUser {
    
    public ConnectedUser() {
        
    }
    
    
    public int getId () { 
        return id; 
    }
    
    public void setId(int id) { 
        this.id = id;
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
