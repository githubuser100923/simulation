
package simulation.domain;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*

Functionalities

*/


public class Service {
    private Player player;
    private Firestore db;
    
    public Service() {
        this.player = new Player("default",0,0);
                
        GoogleCredentials credentials = null;
        try {
            InputStream serviceAccount = new FileInputStream("/home/elias/simpleaction-32740-48947941311f.json");
            credentials = GoogleCredentials.fromStream(serviceAccount);
        } catch (IOException ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
        FirebaseOptions options = new FirebaseOptions.Builder()
            .setCredentials(credentials)
            .build();
        FirebaseApp.initializeApp(options);

        db = FirestoreClient.getFirestore();
        
        loadOthers();
    }
    
    public String joinButtonPressed(String username) {
        if (username.length() < 3) {
            return "Type at least 3 characters";
        }
        return null;
    }
    
    public void createNewPlayer(String username, int x, int y) {
        player = new Player(username, x, y);
    }
    
    public void loadOthers() {
        ApiFuture<QuerySnapshot> query = db.collection("players").get();
        QuerySnapshot querySnapshot = null;
        try {
            querySnapshot = query.get();
        } catch (InterruptedException ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        for (QueryDocumentSnapshot document : documents) {
          System.out.println("Player: " + document.getId());
          System.out.println("X: " + document.getString("x"));
          System.out.println("Y: " + document.getString("y"));
          System.out.println("Character: " + document.getLong("character"));
        }
    }
    
    public Player getPlayer() {
        return player;
    }
}
