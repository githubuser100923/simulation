
package simulation.domain;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.common.reflect.TypeToken;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*

Functionalities

*/


public class Service {
    private Player player;
    private ArrayList<Player> players = new ArrayList<>();
    private Firestore db;
    
    public Service() throws Exception {
        this.player = new Player("default", 0, 0);
        
        loadOthers();
    }
    
    public String joinButtonPressed(String username) {
        if (username.length() < 3) {
            return "Type at least 3 characters";
        }
        return null;
    }
    
    public void createNewPlayer(String username, int x, int y) {
        player = new Player(username, x, y, db);
    }
    
    public static String getRequest(String urlToRead) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        return result.toString();
    }
    
    public void loadOthers() throws Exception {
        String response = getRequest("https://firestore.googleapis.com/v1beta1/projects/simpleaction-32740/databases/(default)/documents/players/");
        
        JsonElement json = new JsonParser().parse(response);
        if (json.isJsonObject()) {
            JsonObject jsonObject = json.getAsJsonObject();
            JsonArray d = jsonObject.get("documents").getAsJsonArray();
            for (int i = 0; i < Math.min(d.size(), 5); i++) {
                JsonObject a = d.get(i).getAsJsonObject();
                JsonObject fields = a.get("fields").getAsJsonObject();
                
                JsonObject x = fields.get("x").getAsJsonObject();
                JsonObject y = fields.get("y").getAsJsonObject();
                
                Player p = new Player("abc", x.get("stringValue").getAsInt(), y.get("stringValue").getAsInt());
                this.players.add(p);
            }
        }
    }
    
    public ArrayList<Player> getPlayers() {
        return players;
    }
    
    public Player getPlayer() {
        return player;
    }
}
