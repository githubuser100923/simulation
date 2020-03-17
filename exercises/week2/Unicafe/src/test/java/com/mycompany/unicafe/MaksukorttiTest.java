package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void konstruktoriAsettaaSaldonOikein() {
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void rahaEiTarpeeksi() {
        kortti.otaRahaa(400);
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void rahaEiTarpeeksiTrueOrFalse() {
        assertEquals(false, kortti.otaRahaa(400));
        assertEquals(true, kortti.otaRahaa(1));
    }
    
    
}
