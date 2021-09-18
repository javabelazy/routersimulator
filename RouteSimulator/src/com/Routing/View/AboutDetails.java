package com.Routing.View;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.JTextArea;

public class AboutDetails extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;  //  @jve:decl-index=0:visual-constraint="118,42"
	private JLabel NameLabel = null;
	private JLabel DonebyLabel = null;
	private JLabel DonebyLabelName = null;
	private JLabel DonebyLabelName1 = null;

	/**
	 * This is the default constructor
	 */
	public AboutDetails() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(300, 200);
		this.setContentPane(getJContentPane());
		this.setTitle("About Window");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			DonebyLabel = new JLabel();
			DonebyLabel.setBounds(new Rectangle(96, 64, 86, 33));
			DonebyLabel.setText("   Done By :");
			DonebyLabelName = new JLabel();
			DonebyLabelName.setBounds(new Rectangle(96, 90, 86, 33));
			DonebyLabelName.setText("   Nimisha.Anil");
			DonebyLabelName1 = new JLabel();
			DonebyLabelName1.setBounds(new Rectangle(96, 110, 86, 33));
			DonebyLabelName1.setText("   Prabila.T.T");
			NameLabel = new JLabel();
			NameLabel.setText("          Routing Simulation");
			NameLabel.setLocation(new Point(63, 15));
			NameLabel.setSize(new Dimension(173, 43));
			NameLabel.setBackground(Color.BLUE);
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.setSize(new Dimension(291, 165));
			jContentPane.add(NameLabel, BorderLayout.WEST);
			jContentPane.add(DonebyLabel, null);
			jContentPane.add(DonebyLabelName, null);
			jContentPane.add(DonebyLabelName1, null);
		}
		return jContentPane;
	}

}  //  @jve:decl-index=0:visual-constraint="113,12"
