package com.Routing.View;


import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Rectangle;
import java.util.Vector;

import javax.swing.JTable;

import com.Routing.util.Flooding;
import com.Routing.util.HotPotato;
import com.Routing.util.LinkStateRouting;
import com.Routing.util.Rip;
import com.Routing.util.SourceRouting;

public class EfficencyDetails extends JPanel {

	private static final long serialVersionUID = 1L;
	private JScrollPane EfficencyScrollPane = null;
	private JTable EfficencyTable = null;
	
	private double floodingEfficency=0;
	private double hotpotatoEfficency=0;
	private double sourceroutingEfficency=0;
	private double ripEfficency=0;
	private double linkstateEfficency=0;
	
	private Vector<String> columnNames = null;
	private Vector<Vector> rowData = null;
	
	/**
	 * This is the default constructor
	 */
	public EfficencyDetails() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		getEfficencyDetails();
		this.setSize(215, 98);
		this.setLayout(null);
		this.add(getEfficencyScrollPane(), null);
	}

	private void getEfficencyDetails() {
		
		floodingEfficency=new Flooding().getEfficency();
		hotpotatoEfficency=new HotPotato().getEfficency();
		sourceroutingEfficency=new SourceRouting().getEfficency();
		ripEfficency=new Rip().getEfficency();
		linkstateEfficency=new LinkStateRouting().getEfficency();
	}

	/**
	 * This method initializes EfficencyScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getEfficencyScrollPane() {
		if (EfficencyScrollPane == null) {
			EfficencyScrollPane = new JScrollPane();
			EfficencyScrollPane.setBounds(new Rectangle(2, 1, 211, 151));
			EfficencyScrollPane.setViewportView(getEfficencyTable());
		}
		return EfficencyScrollPane;
	}

	/**
	 * This method initializes EfficencyTable	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getEfficencyTable() {
		if (EfficencyTable == null) {
			columnNames=new Vector<String>();
			columnNames.addElement("Algorithm");
			columnNames.addElement("Efficency");
			rowData=new Vector<Vector>();
			addElementstoRowData();
			EfficencyTable = new JTable(rowData,columnNames);
			EfficencyTable.setEnabled(false);
		}
		return EfficencyTable;
	}

	private void addElementstoRowData() {
		Vector<String> floodingrow=new Vector<String>();
		floodingrow.addElement("Flooding");
		floodingrow.addElement(""+floodingEfficency);
		rowData.addElement(floodingrow);
		
		Vector<String> hotpotatorow=new Vector<String>();
		hotpotatorow.addElement("Hotpotato");
		hotpotatorow.addElement(""+hotpotatoEfficency);
		rowData.addElement(hotpotatorow);
		
		Vector<String> sourceRoutingrow=new Vector<String>();
		sourceRoutingrow.addElement("SourceRouting");
		sourceRoutingrow.addElement(""+sourceroutingEfficency);
		rowData.addElement(sourceRoutingrow);
		
		Vector<String> riprow =new Vector<String>();
		riprow.addElement("Rip");
		riprow.addElement(""+ripEfficency);
		rowData.addElement(riprow);
		
		Vector<String> linkstaterow=new Vector<String>();
		linkstaterow.addElement("LinkState");
		linkstaterow.addElement(""+linkstateEfficency);
		rowData.addElement(linkstaterow);
		
	}

	public String getEfficentAlogorithm() {
		// TODO Auto-generated method stub
		return null;
	}

	public double getEfficencyValue() {
		// TODO Auto-generated method stub
		return 0;
	}

}
