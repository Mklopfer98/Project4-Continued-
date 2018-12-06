
/** @author Matthew Klopfer
 *  @version 12/5/2018
 *  Lab 11
 * 
 * This is the file that will hold the necessary code to create a functional JTable
 */
import java.awt.*;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 * This class will create a TabelPanel to be implemented by the GUI
 * 
 * @author Matthew Klopfer
 *
 */
public class TabelPanel extends JPanel {

	/** Create the table column values **/
	private static String[] columNames = { "Station", "Parameter", "Statistics", "Value", "Reporting Stations",
			"Date" };

	private static final long serialVersionUID = 1L;

	/** JTable to hold the values the user wishes to display **/
	private JTable table = new JTable(100, 6);

	/** Create scroll pane **/
	private JScrollPane scroll = new JScrollPane(table);

	/** Number of rows in the table **/
	private int rows = 0;

	/**
	 * Constructor for the table and its components
	 * 
	 */
	public TabelPanel() {

		setLayout(new BorderLayout()); // Set the layout of the table

		/* Set the font and color of the frame */
		table.setFont(MesonetFrame.timesRoman);
		table.setBackground(Color.WHITE);
		table.setShowGrid(false);

		add(scroll); // Add the scrollPane

		for (int i = 0; i < columNames.length; ++i) { // Set the headers in the table
			table.getColumnModel().getColumn(i).setHeaderValue(columNames[i]);
		}

	}

	/**
	 * This method will set the new row value with the ones the user wishes to
	 * display
	 * 
	 * @param stid
	 * @param stat
	 * @param param
	 * @param value
	 * @param stations
	 * @param utcDateTimeString
	 */
	public void newDataRow(String stid, String stat, String param, double value, int stations,
			String utcDateTimeString) {

		table.setValueAt(stid, rows, 0);
		table.setValueAt(stat, rows, 1);
		table.setValueAt(param, rows, 2);
		table.setValueAt(value, rows, 3);
		table.setValueAt(stations, rows, 4);
		table.setValueAt(utcDateTimeString, rows, 5);
		++rows; // Increment the number of rows

		/* Set the column to the width of the cell to fit the text */
		resizeColumnWidth();
	}

	/**
	 * This method will auto resize the table once the user decides to calculate out
	 * all of the statistics they wish for
	 * 
	 */
	public void resizeColumnWidth() {

		final TableColumnModel columnModel = table.getColumnModel(); // Table Model

		for (int column = 0; column < table.getColumnCount(); column++) {
			int width = 15; // The minimum width
			for (int row = 0; row < table.getRowCount(); ++row) {

				TableCellRenderer renderer = table.getCellRenderer(row, column); // Create a renderer for the cell
				Component comp = table.prepareRenderer(renderer, row, column);
				width = Math.max(comp.getPreferredSize().width + 1, width);
			}
			if (width > 300) // If the width is to great, we need to cut it down more
				width = 300;
			columnModel.getColumn(column).setPreferredWidth(width);
		}
	}

	/**
	 * Clear the table when the user hits the calculate button again
	 * 
	 */
	public void clearTable() {

		for (int i = 0; i < 100; ++i) { // Increment through the table
			for (int j = 0; j < columNames.length; ++j) {
				table.setValueAt("", i, j);
			}

			this.rows = 0; // Reset the number of rows to 0

		}
	}

}
