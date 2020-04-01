
package simulation.domain;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PlayerTest {
    @Test
    public void initialization() {
        String username = "Elias";
        Player player = new Player(username);
        assertEquals(player.getName(), username);
    }
    
    @Test
    public void sameEquals() {
        String username = "Lankinen";
        Player player1 = new Player(username);
        Player player2 = new Player(username);
        assertEquals(player1, player2);
    }
}
