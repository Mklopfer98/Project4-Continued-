

import java.awt.*;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;

public class TabelPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/** JTable to hold the values the user wishes to display **/
	private JTable table = new JTable(new TableModel());

	/** Create scroll pane **/
	private JScrollPane scroll = new JScrollPane(table);

	public TabelPanel() {

		setLayout(new BorderLayout());
		
		/** Set the font and color of the frame **/
		table.setFont(MesonetFrame.timesRoman);
		table.setBackground(Color.WHITE);
		table.setShowGrid(false);

		add(scroll); // Add the scrollPane

	}

	public static class TableModel extends AbstractTableModel {

		private static final long serialVersionUID = 1L;

		/** Create the table column values **/
		private String[] columNames = {"Station", "Parameter", "Statistics", "Value", "Reporting Stations", "Data"};
		
		/** Create row container **/
		private Object[][] cell =
			{
					{"", "", "", "", "", "",},
					{"", "", "", "", "", "",},
					{"", "", "", "", "", "",},
					{"", "", "", "", "", "",},
					{"", "", "", "", "", "",},
					{"", "", "", "", "", "",},
					{"", "", "", "", "", "",},
					{"", "", "", "", "", "",},
			};
		
		@Override
		public int getColumnCount() {
			return columNames.length;
		}

		@Override
		public int getRowCount() {
			return cell.length;
		}

		@Override
		public Object getValueAt(int row, int column) {
			return cell[row][column];
		}
		
		public String getColumnName(int column) {
			return columNames[column];
		}

	}

	public void newDataRow(String stid, String p, String string, double value, int numberOfReportingStations,
			String utcDateTimeString) {
		// TODO Auto-generated method stub
		
	}
}
