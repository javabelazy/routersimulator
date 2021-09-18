package com.Routing.View;


import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.Routing.Model.RouterDetails;
import javax.swing.JLabel;

public class RouteTable extends JPanel {

	private static final long serialVersionUID = 1L;
	private JScrollPane jScrollPane = null;
	private JTable RouteTable = null;
	private double[][] weightInfo=null;
	private double[][] routeTableInfo=null;
	private Vector<String> columnNames = null;
	private   Vector<Vector> rowData = null;
	private JLabel jLabel = null;
	/**
	 * This is the default constructor
	 */
	public RouteTable() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		
		jLabel = new JLabel();
		jLabel.setBounds(new Rectangle(106, 14, 216, 13));
		jLabel.setText("source and next hops ");
		this.setSize(450, 350);
		this.setLayout(null);
		this.add(getJScrollPane(), null);
		this.add(jLabel, null);
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
			jScrollPane.setViewportView(getRouteTable());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes RouteTable	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getRouteTable() {
		if (RouteTable == null) {
			tableDetails();
			
			columnNames = new Vector<String>();
			rowData = new Vector<Vector>();
			columnNames.addElement("");
			for(String name: RoutingOpt.routers)
			{
			 columnNames.addElement(name);  //Adding Router name to table heading...
			}
			
			for(int i=0;i<RoutingOpt.routers.size();i++)  //Adding datas to router table (weight..)
			{
				Vector<String> row = new Vector<String>();
				row.addElement(RoutingOpt.routers.get(i));
				for(int j=0;j<RoutingOpt.routers.size();j++)
				{
					if(weightInfo[i][j]==0)
				
						row.addElement("Infinity");
					else
						row.addElement(String.valueOf(weightInfo[i][j]));
						
				}
				rowData.addElement(row);
			}
			RouteTable = new JTable(rowData,columnNames);
			RouteTable.setEnabled(false);
			
			RouteTable.setSelectionForeground(Color.red);
			RouteTable.setToolTipText("Vertex and their weigh ");
			
		}
		return RouteTable;
	}



	private void tableDetails() {
		
		weightInfo=new double[RoutingOpt.routers.size()][RoutingOpt.routers.size()];
		routeTableInfo=new double[RoutingOpt.routers.size()][RoutingOpt.routers.size()];
		for(RouterDetails rdetails: Topology.nodeDetails)
		{
		weightInfo[Integer.parseInt(rdetails.getStartRouter().substring(1,rdetails.getStartRouter().length()))][Integer.parseInt(rdetails.getEndRouter().substring(1, rdetails.getEndRouter().length()))]=rdetails.getDistance();
		weightInfo[Integer.parseInt(rdetails.getEndRouter().substring(1, rdetails.getEndRouter().length()))][Integer.parseInt(rdetails.getStartRouter().substring(1,rdetails.getStartRouter().length()))]=rdetails.getDistance();
		}
		
	}

}
