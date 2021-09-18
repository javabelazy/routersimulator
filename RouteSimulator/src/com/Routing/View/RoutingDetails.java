package com.Routing.View;

import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.JTableHeader;

import com.Routing.Model.RouterDetails;
import com.Routing.util.RoutingTable;

public class RoutingDetails extends JPanel {

	private static final long serialVersionUID = 1L;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	private Vector<String> columnNames = null;
	private Vector<Vector> rowData = null;
	private ArrayList<String> via=null;
	private ArrayList<String> destinations=null;
	private double[][] routerTable=null;
	private RoutingTable routeTable=null;


	/**
	 * This is the default constructor
	 */
	public RoutingDetails() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(450, 350);
		this.setLayout(null);
		this.add(getJScrollPane(), null);
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(23, 43, 386, 249));
			jScrollPane.setViewportView(getJTable());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jTable	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getJTable() {
		if (jTable == null) {
			columnNames = new Vector<String>();
			rowData = new Vector<Vector>();
			
			via=new ArrayList<String>();
			destinations=new ArrayList<String>();
			routeTable=new RoutingTable();
			via=routeTable.getVia();
			System.out.println("............................"+via.size());
			destinations=routeTable.getDestination();
			routerTable=new double[destinations.size()][via.size()/2];
			routerTable=routeTable.getRoutingTable();
			//columnNames.addElement("Start Router");
			//columnNames.addElement(" End Router ");
			//columnNames.addElement("  Distance  ");
				columnNames.addElement(" ");
			for(String columnElements:via)
			{
				columnNames.addElement(columnElements);
			}
			
			for(int row=0;row<destinations.size();row++)
			{
				Vector<String> rows=new Vector<String>();
				rows.addElement(destinations.get(row));
				for(int col=0;col<via.size()/2;col++)
				{
				rows.addElement(String.valueOf(routerTable[row][col]));	
				System.out.println(routerTable[row][col]);
				}
				rowData.addElement(rows);
			}
			
//			for(RouterDetails rd : Topology.nodeDetails)
//			{
//				Vector<String> row = new Vector<String>();
//				row.addElement(rd.getStartRouter().replace("R", "ROUTER_"));
//				row.addElement(rd.getEndRouter().replace("R", "ROUTER_"));
//				row.addElement(String.valueOf(rd.getDistance()));
//				rowData.addElement(row);
//			}
			jTable = new JTable(rowData,columnNames);
			JTableHeader header =jTable.getTableHeader();
			header.setBackground(new Color(219,225,213));
		}
		return jTable;
	}
}
