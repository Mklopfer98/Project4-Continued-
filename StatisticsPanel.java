
/** @author Matthew Klopfer
 *  @version 12/5/2018
 *  Lab 11
 * 
 * This is the file that will contain the statistics panel class
 */
import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

/**
 * This class will create a statistics panel and give the user the option to
 * chose different statistics
 * 
 * @author Matthew Klopfer
 *
 */
public class StatisticsPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/** JRadioButton for minimum calculations **/
	private JRadioButton min = new JRadioButton("MINIMUM");

	/** JRadioButton for average calculations **/
	private JRadioButton ave = new JRadioButton("AVERAGE");

	/** JRadioButton for maximum calculations, default choice when opened **/
	private JRadioButton max = new JRadioButton("MAXIMUM", true);

	/** New button group for the JRadioButtons **/
	private ButtonGroup stats = new ButtonGroup();

	/** New border for the parameter choices **/
	private TitledBorder statistics = new TitledBorder(MesonetFrame.blackline, "Statistics");

	/**
	 * Constructor for the StatisticsPanel and its components
	 * 
	 */
	public StatisticsPanel() {

		/* Set the layout, font and the border of the JPanel */
		statistics.setTitleFont(MesonetFrame.timesRoman);
		setLayout(new GridLayout(3, 0));
		setBorder(statistics);

		/* Add all of the components of the JPanel */
		add(min).setFont(MesonetFrame.timesRoman);
		add(ave).setFont(MesonetFrame.timesRoman);
		add(max).setFont(MesonetFrame.timesRoman);

		/* Set the background colors of the buttons */
		min.setBackground(Color.LIGHT_GRAY);
		ave.setBackground(Color.LIGHT_GRAY);
		max.setBackground(Color.LIGHT_GRAY);

		/* Add the JRadioButtons to the ButtonGroup */
		stats.add(min);
		stats.add(ave);
		stats.add(max);

	}

	/**
	 * Method will get the choice of the statsType from the GUI
	 * 
	 * @return The selected StatsType to perform operation on
	 */
	public StatsType isSelected() {

		if (max.isSelected()) {
			return StatsType.MAXIMU;
		}

		else if (ave.isSelected()) {
			return StatsType.AVERAGE;
		}

		else if (min.isSelected()) {
			return StatsType.MINIMUM;
		}

		// If for some reason it is not selected
		return null;

	}
}
