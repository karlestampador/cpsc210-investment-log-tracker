// USED CODE FROM APPLICATION IN PHASE 2 EDX ("JSON SERIALIZATION DEMO")

package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Represents a list of investment logs
public class InvestmentLogsList implements Writable {
    private String name;
    private List<InvestmentLog> logs;
    private static InvestmentLogsList instance;

    // creates an empty list of investment logs
    public InvestmentLogsList(String name) {
        this.name = name;
        this.logs = new ArrayList<>(); 
    }

    // EFFECTS: creates a singular instance of investmentlogslist (Singleton Pattern)
    public static InvestmentLogsList getInstance() {
        if (instance == null) {
            instance = new InvestmentLogsList("Logs list");
        }
        return instance;
    }

    // EFFECTS: checks if log is in list
    public void logInList(String name) {
        InvestmentLog removedLog = null;
        for (InvestmentLog log : logs) {
            if (log.getName().equals(name)) {
                removedLog = log;
                break;
            }
        }

        if (removedLog != null) {
            removeLogFromList(removedLog);
        }
    }

    // EFFECTS: adds an investment log to the list
    public void addNewLogToList(InvestmentLog newLog) {
        this.logs.add(newLog);
        EventLog.getInstance().logEvent(new Event("Added " + newLog.getName() + " to " + name));
    }

    // MODIFIES: this
    // EFFECTS: gets investedAmount and changes it to string
    public String getInvestedAmountAsString(InvestmentLog log) {
        String investedAmt = "$" + Integer.toString(log.getInitInvested());
        return investedAmt; 
    }

    // MODIFIES: this
    // EFFECTS: gets presentValue and changes it to string
    public String getPresentValueAsString(InvestmentLog log) {
        String presentVal = "$" + Integer.toString(log.getCurrValue());
        return presentVal; 
    }

    // MODIFIES: this
    // EFFECTS: removes a log of a certain name from list
    public void removeLogFromList(InvestmentLog log) {
        logs.remove(log);
        EventLog.getInstance().logEvent(new Event("Removed " + log.getName() + " from " + name));
    }

    // MODIFIES: this
    // EFFECTS: removes a log of a certain ID from list
    public void removeLogFromList(int removedID) {
        logs.remove(removedID);
        EventLog.getInstance().logEvent(new Event("Removed log index number " + removedID + " from " + name));
    }

    // returns numbers of logs in this log tracker
    public int numLogs() {
        return logs.size();
    }

    // returns InvestmentLog at the specific index number
    public InvestmentLog getLogIndex(int number) {
        return logs.get(number);
    }

    public List<InvestmentLog> getLogs() {
        return Collections.unmodifiableList(logs); 
    }

    public void setLogs(List<InvestmentLog> newLogs) {
        this.logs = new ArrayList<>(newLogs);
    }

    public String getName() {
        return name;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("logs", logsToJson());
        return json; 
    }

    // EFFECTS: returns logs in this workroom as a JSON array
    private JSONArray logsToJson() {
        JSONArray jsonArray = new JSONArray(); 
        for (InvestmentLog log : logs) {
            jsonArray.put(log.toJson());
        }
        EventLog.getInstance().logEvent(new Event("Saved new logs to JSON file!"));
        return jsonArray;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((logs == null) ? 0 : logs.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        InvestmentLogsList other = (InvestmentLogsList) obj;
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (logs == null) {
            if (other.logs != null) {
                return false;
            }
        } else if (!logs.equals(other.logs)) {
            return false;
        }
        return true;
    }
}
