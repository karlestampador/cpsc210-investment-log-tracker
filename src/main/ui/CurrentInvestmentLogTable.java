package ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import java.awt.Dimension;
import java.awt.GridLayout;

import java.util.List;
import java.util.ArrayList;
import model.InvestmentLog;
import model.InvestmentLogsList;

public class CurrentInvestmentLogTable extends JPanel {
    private InvestmentLogsList logsList = InvestmentLogsList.getInstance();
    private JFrame parentFrame;
    private List<InvestmentLog> currentLogs = new ArrayList<>();
    private List<InvestmentLog> totalLogs = logsList.getLogs();

    // creates a new table for current investments
    public CurrentInvestmentLogTable()  {
        super(new GridLayout(1,0));
        filterCurrentInvestments();
        JTable table = new JTable(new MyTableModel());
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Add the scroll pane to this panel.
        add(scrollPane);
    }

    // EFFECTS: filters current investments
    public void filterCurrentInvestments() {
        for (InvestmentLog log : this.totalLogs)  {
            if (log.getCurrentStatus()) {
                currentLogs.add(log);
            }
        }
    }

    // creates the model for the table
    class MyTableModel extends AbstractTableModel {

        private String[] columnNames = {"Company Name",
                                        "Currently Invested?",
                                        "Sector",
                                        "Date",
                                        "Amount Invested",
                                        "Present Investment Value"};

        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            return currentLogs.size();
        }

        public String getColumnName(int col) {
            return columnNames[col];
        }

        public Object getValueAt(int row, int col) {
            InvestmentLog log = currentLogs.get(row);

            switch (col) {
                case 0: return log.getName();
                case 1: return log.getCurrentStatus();
                case 2: return log.getSector();
                case 3: return log.getInitDate();
                case 4: return log.getInitInvested();
                case 5: return log.getCurrValue();
                default: return null;
            }
        }

        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        // EFFECTS: ensures cell is not editbale
        public boolean isCellEditable(int row, int col) {
            return false;
        }
    }

    // EFFECTS: ensures the table is visible to the user
    public void showInFrame() {
        parentFrame = new JFrame("Current Investment Log Table");
        parentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only this window when exiting
        parentFrame.setContentPane(this);
        parentFrame.pack();
        parentFrame.setLocationRelativeTo(null); // Center the window
        parentFrame.setVisible(true);
    }
}
