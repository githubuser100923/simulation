
package simulation.domain;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ServiceTest {
    
    Service service;
    
    @Before
    public void setUpClass() {
        service = new Service();
    }
    
    @Test
    public void joinButton(){
        assertEquals(service.joinButtonPressed("Elias"), null);
        assertEquals(service.joinButtonPressed("E"), "Type at least 3 characters");
    }
    
    @Test
    public void createNewPlayer() {
        service.createNewPlayer("Elias", 0, 0);
        assertEquals(service.getPlayer().getName(), "Elias");
        assertEquals(service.getPlayer().getName(), "Elias");
    }
}
