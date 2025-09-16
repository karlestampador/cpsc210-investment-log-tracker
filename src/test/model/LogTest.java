package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LogTest {
    
    private InvestmentLog t1;
    private InvestmentLog t2;

    @BeforeEach
    void runBefore() {
        t1 = new InvestmentLog("UBC Hospital",true, "Health", "10/31/2024", 1000, 1500);
        t2 = new InvestmentLog("Microsoft", false, "Technology","10/31/2025", 500, 300);
    }

    @Test
    public void testConstructor() {
        assertEquals("UBC Hospital", t1.getName());
        assertTrue(t1.getCurrentStatus());
        assertFalse(t2.getCurrentStatus());
        assertEquals("Health", t1.getSector());
        assertEquals("10/31/2024", t1.getInitDate());
        assertEquals(500, t2.getInitInvested());
        assertEquals(1500, t1.getCurrValue());
    }

    @Test
    public void testName() {
        t2.setName("Bank of America");
        assertEquals("Bank of America", t2.getName());
        t2.setName("BMO");
        t2.setName("BMO");
        assertEquals("BMO", t2.getName());
    }

    @Test
    public void testCurrentStatus() {
        t1.setCurrentStatus(false);
        assertFalse(t1.getCurrentStatus());
        t1.setCurrentStatus(true);
        t1.setCurrentStatus(true);
        assertTrue(t1.getCurrentStatus());
        t2.setCurrentStatus(false);
        t2.setCurrentStatus(true);
        assertTrue(t2.getCurrentStatus());    
    }

    @Test
    public void testSector() {
        t2.setSector("Energy");
        assertEquals("Energy", t2.getSector());
        t2.setSector("Healthcare");
        t2.setSector("Healthcare");
        assertEquals("Healthcare", t2.getSector());
    }

    @Test
    public void testInitYear() {
        t2.setInitYear("2020");
        assertEquals("2020", t2.getInitYear());
        t2.setInitYear("2025");
        t2.setInitYear("2025");
        assertEquals("2025", t2.getInitYear());
    }

    @Test
    public void testInitMonth() {
        t2.setInitMonth("12");
        assertEquals("12", t2.getInitMonth());
        t2.setInitMonth("6");
        t2.setInitMonth("6");
        assertEquals("6", t2.getInitMonth());
    }

    @Test
    public void testInitDay() {
        t2.setInitDay("31");
        assertEquals("31", t2.getInitDay());
        t2.setInitDay("1");
        t2.setInitDay("1");
        assertEquals("1", t2.getInitDay());
    }

    @Test
    public void testInitDate() {
        t1.setInitDate("3/21/2025");
        assertEquals("3/21/2025", t1.getInitDate());
    }

    @Test
    public void testInitInvested() {
        t2.setInitInvested(1000);
        assertEquals(1000, t2.getInitInvested());
        t2.setInitInvested(1500);
        t2.setInitInvested(1250);
        assertEquals(1250, t2.getInitInvested());
    }

    @Test 
    public void testCurrValue() {
        t2.setCurrValue(500);
        assertEquals(500, t2.getCurrValue());
        t2.setCurrValue(1250);
        t2.setCurrValue(1000);
        assertEquals(1000, t2.getCurrValue());
    }
}
