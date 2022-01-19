package database;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class UserConnection {
    
    public static void open(String email, String password) {
        if (instance != null) {
            instance.close();
        }
        
        DatabaseConnection connection = DatabaseConnection.create("skdvfgqmajapzi", "93e159d04627aa1c6ce3ebb26f97aaa0797ac98804a1de5d428663fdd3f1cfec");

        // https://stackoverflow.com/questions/3324717/sending-http-post-request-in-java
        
        try {
            // Connection configuration
            URL url = new URL("https://cpoa-canada.herokuapp.com/api/login/");
            URLConnection con = url.openConnection();
            HttpURLConnection http = (HttpURLConnection)con;
            http.setRequestMethod("POST");
            http.setDoOutput(true);
            http.setConnectTimeout(5000);
            http.setReadTimeout(5000);
            
            // POST arguments
            Map<String,String> arguments = new HashMap<>();
            arguments.put("email", email);
            arguments.put("password", password);
            StringJoiner sj = new StringJoiner("&");
            for(Map.Entry<String,String> entry : arguments.entrySet())
                sj.add(URLEncoder.encode(entry.getKey(), "UTF-8") + "=" 
                     + URLEncoder.encode(entry.getValue(), "UTF-8"));
            byte[] out = sj.toString().getBytes(StandardCharsets.UTF_8);
            int length = out.length;
            
            // Connection
            http.setFixedLengthStreamingMode(length);
            http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            http.connect();
            try(OutputStream os = http.getOutputStream()) {
                os.write(out);
            }
            
            // Input
            BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream()));            
            ObjectMapper objectMapper = new ObjectMapper();
            String line = in.readLine();
            System.out.println(line);
            ConnectedUser user = objectMapper.readValue(line, ConnectedUser.class);
            if (user.getId() != -1) {
                // Authentication success
                instance = new UserConnection(user);
            }
            else {
                // Authentication failure
                connection.close();
            }
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    
    public void close() {
        // Close connection
        DatabaseConnection connection = DatabaseConnection.get();
        if (connection != null) {
            connection.close();
        }
        
        instance = null;
    }
    
    public static UserConnection get() {
        return instance;
    }
    
    
    private UserConnection(ConnectedUser user) {
        authenticatedUser = user;
    }
    
    public ConnectedUser getAuthenticatedUser() {
        return authenticatedUser;
    }
    
    
    private static UserConnection instance = null;
    
    private ConnectedUser authenticatedUser;
}
