package persistence;

import model.InvestmentLog;
import model.InvestmentLogsList;

// import org.json.JSONWriter;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            InvestmentLogsList lt = new InvestmentLogsList("My log tracker");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyLogTracker() {
        try {
            InvestmentLogsList lt = new InvestmentLogsList("My log tracker");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyLogTracker.json");
            writer.open();
            writer.write(lt);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyLogTracker.json");
            lt = reader.read();
            assertEquals("My log tracker", lt.getName());
            assertEquals(0, lt.numLogs());
            
        } catch (IOException e) {
            fail("Exception should have not been thrown");
        }
    }

    @Test
    void testWriterGeneralLogTracker() {
        try {
            InvestmentLogsList lt = new InvestmentLogsList("My log tracker");
            lt.addNewLogToList(new InvestmentLog("Google", true, "Technology", "03/27/2020", 420000, 4000000));
            lt.addNewLogToList(new InvestmentLog("Boeing", false, "Utilities", "10/21/1996", 500000, 300000));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralLogTracker.json");
            writer.open();
            writer.write(lt);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralLogTracker.json");
            lt = reader.read();
            assertEquals("My log tracker", lt.getName());
            List<InvestmentLog> logs = lt.getLogs();
            assertEquals(2, logs.size());
            checkLog("Google", true, "Technology", "03/27/2020", 420000, 4000000, logs.get(0));
            checkLog("Boeing", false, "Utilities", "10/21/1996", 500000, 300000, logs.get(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
