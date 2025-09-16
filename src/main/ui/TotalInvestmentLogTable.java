// FROM JAVA TUTORIAL

package ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import java.awt.Dimension;
import java.awt.GridLayout;

import java.util.List;
import model.InvestmentLog;
import model.InvestmentLogsList;

// creates a new table for all investments
public class TotalInvestmentLogTable extends JPanel {
    private InvestmentLogsList logsList;
    private JFrame parentFrame;

    public TotalInvestmentLogTable() {
        super(new GridLayout(1,0));
        this.logsList = InvestmentLogsList.getInstance();

        JTable table = new JTable(new MyTableModel());
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Add the scroll pane to this panel.
        add(scrollPane);
    }

    // creates the model for the table
    class MyTableModel extends AbstractTableModel {
        List<InvestmentLog> logs = logsList.getLogs();
        
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
            return logs.size();
        }

        public String getColumnName(int col) {
            return columnNames[col];
        }

        public Object getValueAt(int row, int col) {
            InvestmentLog log = logs.get(row);

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

        /*
         * JTable uses this method to determine the default renderer/
         * editor for each cell.  If we didn't implement this method,
         * then the last column would contain text ("true"/"false"),
         * rather than a check box.
         */
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        // EFFECTS: ensures cell is not editable
        public boolean isCellEditable(int row, int col) {
            return false;
        }
    }

    // EFFECTS: ensures the table is visible to the user
    public void showInFrame() {
        parentFrame = new JFrame("Total Investment Log Table");
        parentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only this window when exiting
        parentFrame.setContentPane(this);
        parentFrame.pack();
        parentFrame.setLocationRelativeTo(null); // Center the window
        parentFrame.setVisible(true);
    }
}
