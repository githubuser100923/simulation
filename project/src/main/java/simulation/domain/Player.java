
package simulation.domain;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import java.util.HashMap;
import java.util.Map;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

/**
 * @author elias
 * Player
 */
public class Player {
    private String name;
    private Rectangle character;
    private int x;
    private int y;
    private Firestore db;
    
    public Player(String name) {
        this(name, 0, 0);
    }
    
    public Player(String name, int x, int y) {
        this(name, x, y, null);
    }
    
    public Player(String name, int x, int y, Firestore db) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.character = new Rectangle(50, 50);
        this.character.setTranslateX(x);
        this.character.setTranslateY(y);
        this.db = db;
        
        if (db != null) {
            DocumentReference docRef = db.collection("players").document(name);
            Map<String, Object> data = new HashMap<>();
            data.put("x", x);
            data.put("y", y);
            data.put("character", character);
            docRef.set(data);
        }
    }

    public String getName() {
        return this.name;
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }

    public Rectangle getCharacter() {
        return this.character;
    }
    
    public void setCharacterPosition(int x, int y) {
        this.character.setTranslateX(x);
        this.character.setTranslateY(y);
    }
    
    public void setImage(Image img) {
        this.character.setFill(new ImagePattern(img));
    }
    
    public void move(int x, int y) {
        Point2D movement = new Point2D(x, y);
        
        double newX = this.character.getTranslateX() + movement.getX();
        if (newX > 600) {
            newX = 0.0;
        } else if (newX < 0.0) {
            newX = 600.0;
        }
        double newY = this.character.getTranslateY() + movement.getY();
        if (newY > 400) {
            newY = 0.0;
        } else if (newY < 0.0) {
            newY = 400.0;
        }
        
        this.character.setTranslateX(newX);
        this.character.setTranslateY(newY);
    }
    
    @Override
    public String toString() {
        return this.name;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        Player other = (Player) obj;
        if (this.getName().equals(other.getName())) {
            return true;
        }
        return false;
    }
}
