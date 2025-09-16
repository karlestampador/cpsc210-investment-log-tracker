package persistence;

import model.InvestmentLog;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkLog(String name, boolean curr, String sec, String date, int inv, int cval, InvestmentLog log) {
        assertEquals(name, log.getName());
        assertEquals(curr, log.getCurrentStatus());
        assertEquals(sec, log.getSector());
        assertEquals(date, log.getInitDate());
        assertEquals(inv, log.getInitInvested());
        assertEquals(cval, log.getCurrValue());
    }
}
