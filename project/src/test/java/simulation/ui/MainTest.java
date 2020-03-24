package simulation.ui;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class MainTest {
    Main main;

    @Before
    public void setUp() {
        main = new Main();
    }

    @Test
    public void konstruktoriAsettaaSaldonOikein() {
        assertEquals(true, main.startPressed("eli"));
        assertEquals(false, main.startPressed("el"));
        assertEquals(false, main.startPressed(""));
    }

}
