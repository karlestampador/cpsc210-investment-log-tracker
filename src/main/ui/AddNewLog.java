package ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
// import javax.swing.border.Border;
import javax.swing.text.*;

import model.InvestmentLog;
import model.InvestmentLogsList;

public class AddNewLog extends JPanel implements ActionListener, FocusListener {
    private JTextField nameField;
    private JSpinner currentSpinner;
    private JTextField sectorField;
    private JFormattedTextField dateField;
    private JTextField amtInvestedField;
    private JTextField currValueField;

    private static final int GAP = 10;

    private JLabel logNumberDisplay;

    private JButton addButton;
    private JButton clearButton;

    private boolean current;
    private int count;
    private int logNum;

    private InvestmentLogsList logs;
    private JFrame parentFrame;

    // adds a new log
    public AddNewLog(int count) {
        this.count = count;
        this.logNum = 1;
        this.logs = InvestmentLogsList.getInstance();
        setLayout(new BorderLayout(GAP, GAP));
        initalizeComponents();
    }

    // EFFECTS: initializes the panels
    private void initalizeComponents() {
        JPanel formPanel = createFormPanel();
        JPanel buttonPanel = createButtonPanel();
        JPanel logPanel = createLogPanel();
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        add(logPanel, BorderLayout.EAST);
    }

    private JPanel createFormPanel() {
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        int row = 0;
        formPanel.add(createLabel("Company Name:"), getGBC(0, row));
        formPanel.add(nameField = createTextField(), getGBC(1, row++));
        formPanel.add(createLabel("Current Status:"), getGBC(0, row));
        formPanel.add(currentSpinner = createCurrentSpinner(), getGBC(1, row++));
        formPanel.add(createLabel("Sector:"), getGBC(0, row));
        formPanel.add(sectorField = createTextField(), getGBC(1, row++));
        formPanel.add(createLabel("Date of Investment:"), getGBC(0, row));
        formPanel.add(dateField = createDateField(), getGBC(1, row++));
        formPanel.add(createLabel("Amount Invested:"), getGBC(0, row));
        formPanel.add(amtInvestedField = createTextField(), getGBC(1, row++));
        formPanel.add(createLabel("Current Value:"), getGBC(0, row));
        formPanel.add(currValueField = createTextField(), getGBC(1, row++));
        return formPanel;
    }

    // EFFECTS: creates button panel
    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        addButton = new JButton("Set Log");
        clearButton = new JButton("Clear Log");
        addButton.addActionListener(this);
        clearButton.addActionListener(this);
        clearButton.setActionCommand("clear");
        buttonPanel.add(addButton);
        buttonPanel.add(clearButton);
        return buttonPanel;
    }

    // EFFECTS: creates log panel
    private JPanel createLogPanel() {
        logNumberDisplay = new JLabel(formatLogNumber(), JLabel.CENTER);
        logNumberDisplay.setFont(new Font("SansSerif", Font.BOLD, 24));
        JPanel logPanel = new JPanel();
        logPanel.add(logNumberDisplay);
        return logPanel;
    }

    // EFFECTS: creates label with specified text
    private JLabel createLabel(String text) {
        return new JLabel(text, JLabel.RIGHT);
    }

    // EFFECTS: creates text field
    private JTextField createTextField() {
        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(200, 30));
        return textField;
    }

    // EFFECTS: creates date field
    private JFormattedTextField createDateField() {
        JFormattedTextField field = new JFormattedTextField(createFormatter("##/##/####"));
        field.setPreferredSize(new Dimension(200, 30));
        return field;
    }

    // EFFECTS: creates spinner with two options
    private JSpinner createCurrentSpinner() {
        String[] options = {"Currently invested", "Not invested"};
        return new JSpinner(new SpinnerListModel(options));
    }

    // EFFECTS: creates constraints for grid
    private GridBagConstraints getGBC(int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = (x == 1) ? 1.0 : 0.0;
        return gbc;
    }
    
    // EFFECTS: either clears all text fields or adds a new log depending on button pressed
    public void actionPerformed(ActionEvent e) {
        if ("clear".equals(e.getActionCommand())) {
            clearTextFields();
        } else {
            addOneNewLog();
        }
    }

    // MODIFIES: logs
    // EFFECTS: adds new log to logs
    public void addOneNewLog() {
        String name = nameField.getText();
        getCurrent();
        String sector = sectorField.getText();
        int initialInvestment = Integer.parseInt(amtInvestedField.getText());
        int currentValue = Integer.parseInt(currValueField.getText());
        String date = dateField.getText();
        InvestmentLog newLog = new InvestmentLog(name, this.current, sector, date, initialInvestment, currentValue);
        logs.addNewLogToList(newLog);
        checkIfAddNewLog();
    }

    // EFFECTS: clears all text fields
    public void clearTextFields() {
        nameField.setText("");
        sectorField.setText("");
        amtInvestedField.setText("");
        currValueField.setText("");
    }

    // MODIFIES: this
    // EFFECTS: sets current value based on currentSpinner
    public void getCurrent() {
        String value = (String) currentSpinner.getValue();
        this.current = "Currently invested".equals(value);
    }

    // EFFECTS: checks if number of logs added = number of logs wanted to add
    public void checkIfAddNewLog() {
        logNum++;
        if (logNum <= count) {
            updateDisplays();
        } else {
            parentFrame.dispose();
        }
    }

    // EFFECTS: updates log number display based on number of logs added by user
    public void updateDisplays() {
        clearTextFields();
        logNumberDisplay.setText(formatLogNumber());
    }

    // EFFECTS: creates a MaskFormatter
    private MaskFormatter createFormatter(String s) {
        try {
            return new MaskFormatter(s);
        } catch (java.text.ParseException e) {
            System.err.println("Error in date format: " + e.getMessage());
            return null;
        }
    }

    // EFFECTS: formats log number
    private String formatLogNumber() {
        return "Log #" + logNum;
    }

    // EFFECTS: selects the focused field
    public void focusGained(FocusEvent e) {
        if (e.getComponent() instanceof JTextField) {
            ((JTextField) e.getComponent()).selectAll();
        }
    }

    // needed for FocusListener interface
    public void focusLost(FocusEvent e) {
        // no implementation
    }

    // EFFECTS: ensures the window is visible to the user
    public void showInFrame() {
        parentFrame = new JFrame("Add New Investment Log");
        parentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only this window when exiting
        parentFrame.setContentPane(this);
        parentFrame.pack();
        parentFrame.setLocationRelativeTo(null); // Center the window
        parentFrame.setVisible(true);
    }
}
