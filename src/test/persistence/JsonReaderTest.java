// USED CODE FROM APPLICATION IN PHASE 2 EDX ("JSON SERIALIZATION DEMO")

package persistence;

import model.InvestmentLog;
import model.InvestmentLogsList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./ProjectStarter/data/noSuchFile.json");
        try {
            InvestmentLogsList lt = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyLogTracker() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyLogTracker.json");
        try {
            InvestmentLogsList lt = reader.read();
            assertEquals("My log tracker", lt.getName());
            assertEquals(0, lt.numLogs());
        } catch (IOException e) {
            fail("Couldn't read from file"); // test cant read file
        }
    }

    @Test
    void testReaderGeneralLogTracker() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralLogTracker.json");
        try {
            InvestmentLogsList lt = reader.read();
            assertEquals("My log tracker", lt.getName());
            List<InvestmentLog> logs = lt.getLogs();
            assertEquals(2, logs.size());
            checkLog("Google", true, "Technology", "03/27/2020", 420000, 4000000, logs.get(0));
            checkLog("Boeing", false, "Utilities", "10/21/1996", 500000, 300000, logs.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file"); // test cant read file
        }
    }

}
