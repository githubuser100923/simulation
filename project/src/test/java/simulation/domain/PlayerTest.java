
package simulation.domain;

import javafx.scene.shape.Rectangle;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PlayerTest {
    @Test
    public void initialization() {
        String username = "Elias";
        Player player = new Player(username, 0, 0);
        assertEquals(player.getName(), username);
    }
    
    @Test
    public void sameEquals() {
        String username = "Lankinen";
        Player player1 = new Player(username);
        Player player2 = new Player(username, 0, 0);
        assertEquals(player1, player2);
    }
    
    @Test
    public void getCharacter() {
        Player player = new Player("Elias");
        assertEquals(50.0, player.getCharacter().getWidth(), 0.1);
        assertEquals(50.0, player.getCharacter().getHeight(), 0.1);
    }
    
    @Test
    public void move() {
        Player p = new Player("Elias");
        p.move(10, 10);
        assertEquals(p.getCharacter().getX(), 0.0, 0.1);
        assertEquals(p.getCharacter().getY(), 0.0, 0.1);
    }
    
    @Test
    public void move2() {
        Player p = new Player("Elias");
        p.move(1000, 1000);
        assertEquals(p.getCharacter().getX(), 0.0, 0.1);
        assertEquals(p.getCharacter().getY(), 0.0, 0.1);
    }
}
