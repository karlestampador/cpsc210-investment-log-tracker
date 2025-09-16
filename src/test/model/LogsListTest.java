package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LogsListTest {

    private InvestmentLog i1;
    private InvestmentLog i2;

    private InvestmentLogsList l1;

    @BeforeEach
    void runBefore() {
        i1 = new InvestmentLog("Telus", false, "Technology", "03/26/1997", 10, 110);
        i2 = new InvestmentLog("Epic Games", true, "Entertainment", "06/28/2020", 1560, 6780);
        l1 = new InvestmentLogsList("Karl's log tracker");
    }

    @Test
    public void testConstructor() {
        assertEquals(0, l1.numLogs());
    }

    @Test
    public void testAddOneLogToList() {
        l1.addNewLogToList(i1);
        assertEquals(1, l1.numLogs()); 
        assertEquals(i1, l1.getLogIndex(0));
    }

    @Test
    public void testAddMultipleLogsToList() {
        l1.addNewLogToList(i1);
        l1.addNewLogToList(i2);
        assertEquals(2, l1.numLogs());
        assertEquals(i2, l1.getLogIndex(1)); 
    }


    @Test
    public void testGetInvestedAmountAsString() {
        assertEquals("$10", l1.getInvestedAmountAsString(i1));
        assertEquals("$1560", l1.getInvestedAmountAsString(i2));
    }

    @Test
    public void testGetPresentValueAsString() {
        assertEquals("$110", l1.getPresentValueAsString(i1));
        assertEquals("$6780", l1.getPresentValueAsString(i2));
    }

    @Test
    public void testRemoveLogFromList() {
        l1.addNewLogToList(i1);
        l1.addNewLogToList(i2);
        assertEquals(i1, l1.getLogIndex(0));
        assertEquals(2, l1.numLogs()); 
        l1.removeLogFromList(0);
        assertEquals(1, l1.numLogs());
        assertEquals(i2, l1.getLogIndex(0));
    }

    @Test
    void testGetInstanceReturnsSameObject() {
        InvestmentLogsList instance1 = InvestmentLogsList.getInstance();
        InvestmentLogsList instance2 = InvestmentLogsList.getInstance();

        assertNotNull(instance1);
        assertEquals(instance1, instance2);
    }

    @Test
    void testGetInstanceInitializesProperly() {
        InvestmentLogsList instance = InvestmentLogsList.getInstance();
        assertEquals("Logs list", instance.getName());
    }
}
