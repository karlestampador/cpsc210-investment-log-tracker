// RESOURCES: 
// FOR CONSOLE IMPLEMENTATION: LAB 4 CODE 
// FOR PARSEINT: https://stackoverflow.com/questions/26586489/integer-parseintscanner-nextline-vs-scanner-nextint
// FOR THE INVESTMENT TABLE: https://stackoverflow.com/questions/15215326/how-can-i-create-table-using-ascii-in-a-console
// FOR PHASE 2 IMPLEMENTATION: JSON SERIALIZATION DEMO

package ui;

import model.Event;
import model.EventLog;
import model.InvestmentLog;
import model.InvestmentLogsList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
// import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

// An investment tracker enabling users to add/delete and view their current and total logs.
public class InvestmentLogTracker extends JPanel implements ActionListener {
    private InvestmentLogsList logs;

    private Scanner scanner;
    private boolean isAppRunning;
    private boolean isCurrentValid;
    private boolean consoleOn;

    private String name;
    private Boolean current;
    private String sector;
    private String initYear;
    private String initMonth;
    private String initDay;
    private String date;
    private int initInvested;
    private int currValue;

    private static final String JSON_STORE = "./data/logtracker.json";
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;

    protected JButton b1;
    protected JButton b2;
    protected JButton b3; 
    protected JButton b4;
    protected JButton b5;
    protected JButton b6;
    protected JButton b7;
    protected JButton b8;

    private JFrame frame;

    private AddNewLog addLogPanel;
    private CurrentInvestmentLogTable currentLogPanel;
    private TotalInvestmentLogTable totalLogPanel;

    // EFFECTS: constructs log tracker and runs application
    public InvestmentLogTracker() { 
        start();
    }

    // EFFECTS: creates the gui and shows it
    public static void createAndShowGUI() {

        // Creates and sets up the window
        JFrame frame = new JFrame("InvestmentLogTracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creates and sets up the content pane
        InvestmentLogTracker newContentPane = new InvestmentLogTracker();
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);

        // Displays the window
        frame.pack();
        frame.setVisible(true);
    }
    
    // EFFECTS: initializes the application with the starting values
    public void start() {
        this.logs = new InvestmentLogsList("My log tracker");
        this.scanner = new Scanner(System.in);
        this.isAppRunning = true;
        this.isCurrentValid = false;
        this.consoleOn = false;
        this.jsonReader = new JsonReader(JSON_STORE);
        this.jsonWriter = new JsonWriter(JSON_STORE);
        startGUI();
    }

    // EFFECTS: initializes application with values for the buttons
    public void startGUI() {
        startButtons();
        addActionListeners();
        setToolTipText();
        addButtons();
    }

    // EFFECTS: triggers all buttons to be initiated
    public void startButtons() {
        startAddNewLogButton(); 
        startViewCurrInvButton();
        startViewAllInvButton();
        startDeleteInvButton();
        startSaveListButton();
        startLoadListButton();
        startViewStatsButton();
        startQuitAppButton();
    }

    // EFFECTS: initializes add new log button
    public void startAddNewLogButton() {
        b1 = new JButton("Add new log");
        b1.setVerticalTextPosition(AbstractButton.CENTER);
        b1.setHorizontalTextPosition(AbstractButton.CENTER); 
    }

    // EFFECTS: initializes view current investments button
    public void startViewCurrInvButton() {
        b2 = new JButton("View current investments");
        b2.setVerticalTextPosition(AbstractButton.CENTER);
        b2.setHorizontalTextPosition(AbstractButton.CENTER); 
    }

    // EFFECTS: initializes view all investments button
    public void startViewAllInvButton() {
        b3 = new JButton("View all investments");
        b3.setVerticalTextPosition(AbstractButton.CENTER);
        b3.setHorizontalTextPosition(AbstractButton.CENTER); 
    }

    // EFFECTS: initializes delete investment button
    public void startDeleteInvButton() {
        b4 = new JButton("Delete an investment");
        b4.setVerticalTextPosition(AbstractButton.CENTER);
        b4.setHorizontalTextPosition(AbstractButton.CENTER); 
    }

    // EFFECTS: initializes save list button
    public void startSaveListButton() {
        b5 = new JButton("Save log tracker to file");
        b5.setVerticalTextPosition(AbstractButton.CENTER);
        b5.setHorizontalTextPosition(AbstractButton.CENTER); 
    }

    // EFFECTS: initializes load list button
    public void startLoadListButton() {
        b6 = new JButton("Load log tracker to file");
        b6.setVerticalTextPosition(AbstractButton.CENTER);
        b6.setHorizontalTextPosition(AbstractButton.CENTER); 
    }

    // EFFECTS: initializes view stats button
    public void startViewStatsButton() {
        b7 = new JButton("View Investment Statistics");
        b7.setVerticalTextPosition(AbstractButton.CENTER);
        b7.setHorizontalTextPosition(AbstractButton.CENTER); 
    }

    // EFFECTS: initializes quit app button
    public void startQuitAppButton() {
        b8 = new JButton("Quit application");
        b8.setVerticalTextPosition(AbstractButton.CENTER);
        b8.setHorizontalTextPosition(AbstractButton.CENTER); 
    }

    // EFFECTS: adds action listeners to each of the buttons
    public void addActionListeners() {
        addNewLogActionListener(); 
        viewCurrInvActionListener();
        viewAllInvActionListener();
        deleteLogActionListener();
        saveInvLogActionListener();
        loadInvLogActionListener();
        b7.addActionListener(this);
        quitAppActionListener();
    }

    public void setToolTipText() {
        b1.setToolTipText("Click this button to add a new investment log.");
        b2.setToolTipText("Click this button to view your current investments.");
        b3.setToolTipText("Click this button to view all of your investments.");
        b4.setToolTipText("Click this button to delete an investment log.");
        b5.setToolTipText("Click this button to save this list of investment logs.");
        b6.setToolTipText("Click this button to load your saved list of investment logs.");
        b7.setToolTipText("Click this button to view your investment statistics.");
        b8.setToolTipText("Click this button to close the application.");
    }

    // EFFECTS: add buttons to the container
    public void addButtons() {
        add(b1);
        add(b2);
        add(b3);
        add(b4);
        add(b5);
        add(b6);
        add(b7);
        add(b8);
    }

    // EFFECTS: initializes addNewLog page
    public void addNewLogActionListener() {
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNewLog();
            } 
        });
    }

    // EFFECTS: initializes a table of current investments
    public void viewCurrInvActionListener() {
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewCurrentInvestments();
            }
        });
    }

    // EFFECTS: initializes a table of all investments
    public void viewAllInvActionListener() {
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewAllInvestments();
            }
        });
    }

    public void deleteLogActionListener() {
        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteLog();
            }
        });
    }

    public void saveInvLogActionListener() {
        b5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveInvestmentLog();
            }
        });
    }

    public void loadInvLogActionListener() {
        b6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadInvestmentLog();
            }
        });
    }

    public void quitAppActionListener() {
        b8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quitApplication();
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        notImplemented();
    }

    // MODIFIES: logs
    // EFFECTS: creates a new Investment Log an arbitrary number of times
    public void addNewLog() {
        String input = JOptionPane.showInputDialog(frame, 
                                            "How many investment logs would you like to add?", 
                                            "User Input", 
                                            JOptionPane.QUESTION_MESSAGE);
        try {
            int answer = Integer.parseInt(input);
            if (answer > 0) {
                JFrame addLogFrame = new JFrame("Adding new log");
                addLogFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                
                this.addLogPanel = new AddNewLog(answer);
                this.addLogPanel.showInFrame();
            } else {
                JOptionPane.showMessageDialog(frame, 
                                    "Please choose an integer greater than 0.");
                addNewLog();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, 
                                    "Please enter an integer.");
            addNewLog();
        }
    }

    // EFFECTS: enables the user to see a list of current investments
    public void viewCurrentInvestments() {
        this.currentLogPanel = new CurrentInvestmentLogTable();
        this.currentLogPanel.showInFrame();
    }

    // EFFECTS: enables the user to see a list of all investments
    public void viewAllInvestments() {
        this.totalLogPanel = new TotalInvestmentLogTable();
        this.totalLogPanel.showInFrame();
    }

    // EFFECTS: enables the user to delete a particular investment
    public void deleteLog() {
        String input = JOptionPane.showInputDialog(frame, 
                "What's the name of the company whose investment log you'd like to delete?", 
                                            "User Input", 
                                            JOptionPane.QUESTION_MESSAGE);
        try {
            InvestmentLogsList.getInstance().logInList(input);
            JOptionPane.showMessageDialog(frame, 
                        "Your investment log " + input + " has been deleted!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, 
                                    "Please enter an integer.");
            deleteLog();
        }
    }

    // EFFECTS: saves the investment log list to file
    public void saveInvestmentLog() {
        this.logs = InvestmentLogsList.getInstance();
        int n = JOptionPane.showConfirmDialog(
                            frame, "Would you like to save your investment log?",
                            "Investment Log Tracker",
                            JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            try {
                jsonWriter.open();
                jsonWriter.write(logs);
                jsonWriter.close();
                JOptionPane.showMessageDialog(frame, 
                                    "Your investment log has been saved!");
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(frame, 
                                    "Your investment log has not been saved. Unable to save file to " + JSON_STORE);
            }
        }           
    }

    // MODIFIES: this
    // EFFECTS: loads the investment log list from file
    public void loadInvestmentLog() {
        int n = JOptionPane.showConfirmDialog(
                frame, "Would you like to load your investment log?",
                "Investment Log Tracker",
                JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            try {
                InvestmentLogsList newLogs = jsonReader.read();
                InvestmentLogsList.getInstance().setLogs(newLogs.getLogs());

                JOptionPane.showMessageDialog(frame, 
                        "Your investment log has been loaded!");

                currentLogPanel = new CurrentInvestmentLogTable();
                totalLogPanel = new TotalInvestmentLogTable();

            } catch (IOException e) {
                JOptionPane.showMessageDialog(frame, 
                        "Your investment log has not been loaded. Unable to read file from " + JSON_STORE);
            }
        }     
    }

    // EFFECTS: closes the application
    public void quitApplication() {
        printLog(EventLog.getInstance());
        System.exit(0);
    }

    public void printLog(EventLog el) {
        for (Event next : el) {
            System.out.println(next.toString());
        }
    }

    // EFFECTS: notifies the user that the calling method is not implemented
    public void notImplemented() {
        JOptionPane.showMessageDialog(frame, "Not implemented yet! Check back for future updates!");
    }

    public static void main(String[] args) {
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI(); 
            }
        });
    }
}