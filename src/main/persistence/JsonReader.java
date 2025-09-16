// USED CODE FROM APPLICATION IN PHASE 2 EDX ("JSON SERIALIZATION DEMO")

package persistence;

import model.InvestmentLog;
import model.InvestmentLogsList;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public InvestmentLogsList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseLogTracker(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        
        return contentBuilder.toString(); 
    }

    // EFFECTS: parses log tracker from JSON object and returns it
    private InvestmentLogsList parseLogTracker(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        InvestmentLogsList lt = new InvestmentLogsList(name);
        addLogs(lt, jsonObject);
        return lt; 
    }

    // MODIFIES: lt
    // EFFECTS: parses logs from JSON object and adds them to log tracker
    private void addLogs(InvestmentLogsList lt, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("logs");
        for (Object json : jsonArray) {
            JSONObject nextLog = (JSONObject) json;
            addLog(lt, nextLog);
        }
    }

    // MODIFIES: lt
    // EFFECTS: parse InvestmentLog from JSON object and adds it to log tracker
    private void addLog(InvestmentLogsList lt, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Boolean current = jsonObject.getBoolean("current");
        String sector = jsonObject.getString("sector");
        String date = jsonObject.getString("date");
        int initInvested = jsonObject.getInt("initInvested");
        int currValue = jsonObject.getInt("currValue");
        InvestmentLog newLog = new InvestmentLog(name, current, sector, date, initInvested, currValue);
        lt.addNewLogToList(newLog);
    }
}
