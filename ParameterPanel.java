
/** @author Matthew Klopfer
 *  @version 12/5/2018
 *  Lab 11
 * 
 * This file will hold the classes necessary for the program to make the ParameterPanel
 */

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.TitledBorder;

/**
 * This class will hold the contents needed to make the parameter panel to hold
 * the choices for the user in the GUI
 * 
 * @author Matthew Klopfer
 *
 */
public class ParameterPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/** Button that can be pressed if TAIR is chosen **/
	private JCheckBox TAIR = new JCheckBox("TAIR");

	/** Button that can be pressed if TA9M is chosen **/
	private JCheckBox TA9M = new JCheckBox("TA9M");

	/** Button that can be pressed if TAIR is chosen **/
	private JCheckBox SRAD = new JCheckBox("SRAD");

	/** Button that can be pressed if TA9M is chosen **/
	private JCheckBox WSPD = new JCheckBox("WSPD");

	/** Button that can be pressed if TAIR is chosen **/
	private JCheckBox PRES = new JCheckBox("PRES");

	/** New border for the parameter choices **/
	private TitledBorder parameter = new TitledBorder(MesonetFrame.blackline, "Parameter");

	/**
	 * Constructor for the parameter panel that will hold the parameters the user
	 * may chose to get statistics from.
	 * 
	 */
	public ParameterPanel() {

		/* Set the layout, font and the border of the JPanel */
		parameter.setTitleFont(MesonetFrame.timesRoman);
		setLayout(new GridLayout(5, 0));
		setBorder(parameter);

		/* Set the colors of the JRadioButtons and the panel background */
		TAIR.setBackground(MesonetFrame.BACK);
		TA9M.setBackground(MesonetFrame.BACK);
		SRAD.setBackground(MesonetFrame.BACK);
		WSPD.setBackground(MesonetFrame.BACK);
		PRES.setBackground(MesonetFrame.BACK);

		/* Add all of the components of the JPanel */
		add(TAIR).setFont(MesonetFrame.timesRoman);
		add(TA9M).setFont(MesonetFrame.timesRoman);
		add(SRAD).setFont(MesonetFrame.timesRoman);
		add(WSPD).setFont(MesonetFrame.timesRoman);
		add(PRES).setFont(MesonetFrame.timesRoman);

	}

	/**
	 * Method will get the parameters that are pressed from the GUI and give them to
	 * the program
	 * 
	 * @return ArrayList<String> a list with the chosen parameters from the user
	 * 
	 */
	public ArrayList<String> isSelected() {

		ArrayList<String> params = new ArrayList<String>();

		if (TAIR.isSelected()) {
			params.add("TAIR");
		}

		if (TA9M.isSelected()) {
			params.add("TA9M");
		}

		if (SRAD.isSelected()) {
			params.add("SRAD");
		}

		if (WSPD.isSelected()) {
			params.add("WSPD");
		}

		if (PRES.isSelected()) {
			params.add("PRES");
		}

		return params; // Give the program the parameters
	}
}
