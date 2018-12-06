
/** @author Matthew Klopfer
 *  @version 12/5/2018
 *  Lab 11
 * 
 * This is the Frame file that will add all of the components of the frame into one class to then be given to the user
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * This class contains some sub classes and all of the necessary implementations
 * to create a spectacular looking GUI
 * 
 * @author Matthew Klopfer
 *
 */
public class MesonetFrame extends JFrame {

	public static final long serialVersionUID = 1L;

	/** Create a new Font **/
	protected static Font timesRoman = new Font("TimesRoman", Font.BOLD, 18);

	/** Create the black border **/
	protected static Border blackline = BorderFactory.createLineBorder(Color.BLACK);

	/** Create a color for the back Panels (New custom color) **/
	protected static final Color BACK = new Color(220, 220, 220);

	/** Panel to hold the Parameters and the Statistics choices **/
	JPanel paramStat = new JPanel();

	/** File that the user wishes to open **/
	private static File file = null;

	/** Parameter Panel **/
	ParameterPanel param = new ParameterPanel();

	/** Statistics Panel **/
	StatisticsPanel stats = new StatisticsPanel();

	/** Table Panel **/
	TabelPanel table = new TabelPanel();

	/** Bottom Panel **/
	BottomPanel buttons = new BottomPanel();

	/** File Menu Bar **/
	FileMenuBar menu = new FileMenuBar("File");

	/**
	 * Constructor for the frame that will be given to the user
	 * 
	 * @param title
	 *            the title of the frame
	 * @throws IOException
	 *             an exception that could occur
	 */
	public MesonetFrame(String title) throws IOException {
		super(title);

		/* Add the panel that contains the parameter and statistics options list */
		paramStat.setLayout(new GridLayout(0, 2));
		paramStat.add(param);
		paramStat.add(stats);
		add(paramStat, BorderLayout.WEST);

		/* Add the panels to the frame from the other class */
		add(table, BorderLayout.CENTER);
		add(buttons, BorderLayout.SOUTH);
		add(menu, BorderLayout.NORTH);
		buttons.setTable(table);

		/* Set the size of the frame and make the close operation */
		setSize(1300, 600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	/**
	 * This class is a subset class of the MesonetFrame class that will hold the
	 * components of the file menu bar that will allow the user to choose a file to
	 * calculate statistics on and exit the program. This class will also contain
	 * the slogan for the program that is displayed just under the file menu bar
	 * 
	 * @author Matthew Klopfer
	 *
	 */
	public class FileMenuBar extends JMenuBar implements ActionListener {

		private static final long serialVersionUID = 1L;

		/* Create the menu bar */
		JMenuBar menu = new JMenuBar();
		JMenu fileBar = new JMenu("File");
		JMenuItem getFile = new JMenuItem("Open Data File");
		JMenuItem exit = new JMenuItem("Exit");

		/* JPanel to hold the slogan of our program */
		JPanel slogan = new JPanel();

		/* Slogan of the program */
		JLabel meso = new JLabel("Mesonet - We don't set records, we report them!");

		/**
		 * The bar that will hold the file button to open up the file chooser
		 * 
		 * @param title
		 *            the title of the file menu bar
		 */
		public FileMenuBar(String title) {

			/* Set the color and layout */
			setBackground(Color.WHITE);
			setLayout(new GridLayout(2, 0));

			/* Change font of all menu components */
			getFile.setFont(timesRoman);
			exit.setFont(timesRoman);
			menu.setFont(timesRoman);
			meso.setFont(timesRoman);
			fileBar.setFont(timesRoman);

			slogan.add(meso); // Add the slogan to the JPanel
			slogan.setBackground(Color.GRAY); // Set the color of the slogan label

			add(menu, BorderLayout.EAST); // Add menu components to the menu bar
			menu.add(fileBar);

			/* Add the action listener to the file bar's componets */
			exit.addActionListener(this);
			getFile.addActionListener(this);

			fileBar.add(getFile); // Add the component list to the menu, and the slogan bar
			fileBar.add(exit);
			add(slogan);
		}

		/**
		 * Method will react to when a button in the file menu bar is pressed
		 * 
		 * @param ActionEvent
		 *            the even that is occurring in the GUI
		 */
		@Override
		public void actionPerformed(ActionEvent event) {

			JMenuItem action = (JMenuItem) event.getSource(); // Get the action from the GUI

			if (action == exit) { // If the user presses Exit
				System.exit(0);
			}

			else if (action == getFile) { // If the user gets the File

				JFileChooser choice = new JFileChooser("data");
				FileNameExtensionFilter filter = new FileNameExtensionFilter("MDF files", "mdf", "All files");

				choice.setFileFilter(filter); // Filter the contents for the correct file

				choice.showOpenDialog(this); // Open the file

				try { // Throw an exception if the file fails to open
					fileChoice(choice.getSelectedFile());

				} catch (IOException e) { // Catch a failed file to open
					System.out.println(e.getMessage());
					System.exit(0);
				} catch (NullPointerException e) {
					// If they exit from the file chooser
				}
			}
		}

	}

	/**
	 * This public method will hold the bottom panel to hold the calculate and the
	 * exit buttons for the user to activate when they please
	 * 
	 * @author Matthew Klopfer
	 *
	 */
	public class BottomPanel extends JPanel implements ActionListener {

		private static final long serialVersionUID = 1L;

		/** JButton to hold the calculate script **/
		private JButton calc = new JButton("Calculate");

		/** JButton to hold the exit script **/
		private JButton exit = new JButton("Exit");

		/** Panel to hold the buttons **/
		private JPanel buttonPane = new JPanel();

		/** Data object with the data **/
		private MapData data;

		/** Table for the data **/
		@SuppressWarnings("unused")
		private TabelPanel tabel;

		/**
		 * The bottom panel on the GUI to hold the 2 buttons for the user
		 * 
		 */
		public BottomPanel() {

			/* Set the layout of the bottom panel */
			setLayout(new GridLayout(1, 1));

			/* Set the text of the buttons to timesRoman */
			calc.setFont(timesRoman);
			exit.setFont(timesRoman);

			/* Add the action listener to the buttons */
			calc.addActionListener(this);
			exit.addActionListener(this);

			/* Add the buttons to the bottom panel */
			buttonPane.add(calc);
			buttonPane.add(exit);
			add(buttonPane);
			pack(); // Pack the panel to make it look nicer
		}

		/**
		 * Method will set the table that is currently being used
		 * 
		 * @param tabel
		 *            the table that is in the GUI
		 */
		public void setTable(TabelPanel tabel) {
			this.tabel = tabel;
		}

		/**
		 * Method will give the GUI the data that it is to pull from
		 * 
		 * @param data
		 *            the data that was calculated from the MapData class
		 */
		public void setData(MapData data) {
			this.data = data;
		}

		/**
		 * Will wait for an event to occur in the bottom panel and react when it does
		 * 
		 * @param ActionEvent
		 *            the event we are waiting to see
		 */
		@Override
		public void actionPerformed(ActionEvent e) {

			JButton choice = (JButton) e.getSource(); // Get the choice from the panel

			if (choice == exit) { // Exit if the exit button is pressed
				System.exit(0);
			}

			else if (choice == calc) { // Calculate out the statistics if the calc button is pressed

				/* Clear the table */
				table.clearTable();

				/* Get the desired options from the user */
				StatsType stat = stats.isSelected();
				ArrayList<String> paramID = param.isSelected();

				/* Get the desired data */
				if (!paramID.isEmpty()) {
					for (String p : paramID) { // For each parameter chosen set the cell values
						Statistics statCurrent = data.getStatistics(stat, p);
						table.newDataRow(statCurrent.getStid(), p, statCurrent.getStatType().toString(),
								statCurrent.getValue(), statCurrent.getNumberOfReportingStations(),
								statCurrent.getUTCDateTimeString());
					}
				} else { // If there is not parameters chosen then tell the user to do so
					JOptionPane.showMessageDialog(null, "No Arguments were chosen", "ERROR", JOptionPane.ERROR_MESSAGE);
				}

			}

		}
	}

	/**
	 * Method will give the file to MapData class to get the statistics
	 * 
	 * @param chosenFile
	 *            the user chosen file
	 * @throws IOException
	 *             the exception that is thrown if the file fails to open
	 */
	public void fileChoice(File chosenFile) throws IOException {
		file = chosenFile;
		MapData userData = new MapData(file);
		buttons.setData(userData);

	}
}
