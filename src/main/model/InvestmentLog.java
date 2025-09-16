// USED CODE FROM APPLICATION IN PHASE 2 EDX ("JSON SERIALIZATION DEMO")

package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents an investment log having a name, current status, sector, date, invested date, and its current value
public class InvestmentLog implements Writable {
    
    private String initDay;
    private String initMonth;
    private String initYear;
    private String date;

    private boolean current;
    private String sector;
    private String name;

    private int initInvested;
    private int currValue;
    
    // EFFECTS: creates a new investment log
    public InvestmentLog(String name, boolean current, String sector, String date, int initInvested, int currValue) {
        this.date = date;
        this.current = current;
        this.initInvested = initInvested;
        this.currValue = currValue;
        this.sector = sector;
        this.name = name;
        EventLog.getInstance().logEvent(new Event("Created a new log for " + name));
    }

    // SETTERS
    public void setName(String name) {
        this.name = name;
    }

    public void setCurrentStatus(boolean current) {
        this.current = current; 
    }

    public void setSector(String sector) {
        this.sector = sector; 
    }

    public void setInitYear(String initYear) {
        this.initYear = initYear; 
    } 

    public void setInitMonth(String initMonth) {
        this.initMonth = initMonth;
    }

    public void setInitDay(String initDay) {
        this.initDay = initDay; 
    }

    public void setInitDate(String date) {
        this.date = date;
    }

    public void setInitInvested(int initInvested) {
        this.initInvested = initInvested; 
    }

    public void setCurrValue(int currValue) {
        this.currValue = currValue; 
    }

    // GETTERS
    public String getName() {
        return this.name;
    }

    public boolean getCurrentStatus() {
        return this.current; 
    }

    public String getSector() {
        return this.sector; 
    }

    public String getInitYear() {
        return this.initYear; 
    } 

    public String getInitMonth() {
        return this.initMonth;
    }

    public String getInitDay() {
        return this.initDay;
    }

    public String getInitDate() {
        return this.date;
    }

    public int getInitInvested() {
        return this.initInvested; 
    }

    public int getCurrValue() {
        return this.currValue; 
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("current", current);
        json.put("sector", sector);
        json.put("date", date);
        json.put("initInvested", initInvested);
        json.put("currValue", currValue);
        EventLog.getInstance().logEvent(new Event("Saved " + name + " to Logs list "));
        return json; 
    }
}
