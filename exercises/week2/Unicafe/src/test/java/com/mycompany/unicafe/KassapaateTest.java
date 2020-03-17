package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KassapaateTest {
    
    Kassapaate kp;

    @Before
    public void setUp() {
        kp = new Kassapaate();
    }
    
    @Test
    public void konstruktori() {
        assertEquals(100000, kp.kassassaRahaa());
        assertEquals(0, kp.maukkaitaLounaitaMyyty());
        assertEquals(0, kp.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void cashPaymentSuccess() {
        assertEquals(500-240,kp.syoEdullisesti(500));
        assertEquals(1, kp.edullisiaLounaitaMyyty());
        assertEquals(100000+240, kp.kassassaRahaa());
        assertEquals(500-400,kp.syoMaukkaasti(500));
        assertEquals(1, kp.maukkaitaLounaitaMyyty());
        assertEquals(100000+240+400, kp.kassassaRahaa());
    }
    
    @Test
    public void cashPaymentFail() {
        assertEquals(100,kp.syoEdullisesti(100));
        assertEquals(0, kp.edullisiaLounaitaMyyty());
        assertEquals(100000, kp.kassassaRahaa());
        assertEquals(100,kp.syoMaukkaasti(100));
        assertEquals(0, kp.maukkaitaLounaitaMyyty());
        assertEquals(100000, kp.kassassaRahaa());
    }
    
    @Test
    public void cardPaymentSuccess() {
        Maksukortti mk = new Maksukortti(500);
        assertEquals(true,kp.syoEdullisesti(mk));
        assertEquals(1, kp.edullisiaLounaitaMyyty());
        assertEquals(100000, kp.kassassaRahaa());
        
        mk = new Maksukortti(500);
        assertEquals(true,kp.syoMaukkaasti(mk));
        assertEquals(1, kp.maukkaitaLounaitaMyyty());
        assertEquals(100000, kp.kassassaRahaa());
    }
    
    @Test
    public void cardPaymentFail() {
        Maksukortti mk = new Maksukortti(100);
        assertEquals(false,kp.syoEdullisesti(mk));
        assertEquals(0, kp.edullisiaLounaitaMyyty());
        assertEquals(100000, kp.kassassaRahaa());
        
        
        assertEquals(false,kp.syoMaukkaasti(mk));
        assertEquals(0, kp.maukkaitaLounaitaMyyty());
        assertEquals(100000, kp.kassassaRahaa());
    }
    
    @Test
    public void lataaRahaa() {
        Maksukortti mk = new Maksukortti(100);
        kp.lataaRahaaKortille(mk, 500);
        assertEquals(100000+500, kp.kassassaRahaa());
        assertEquals(100+500, mk.saldo());
        
        mk = new Maksukortti(100);
        kp.lataaRahaaKortille(mk, -10);
        assertEquals(100000+500, kp.kassassaRahaa());
        assertEquals(100, mk.saldo());
    }
}
