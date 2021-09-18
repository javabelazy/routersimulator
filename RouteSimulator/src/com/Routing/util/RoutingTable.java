package com.Routing.util;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.Routing.Model.RouterDetails;
import com.Routing.View.RoutingOpt;
import com.Routing.View.Topology;

public class RoutingTable {
	
	private double distanceInfo[][]=null;
	private ArrayList<String> via=null;
	private ArrayList<String> dest=null;
	private String sourceRouter=null;
	private double routerTable[][]=null;
	private DistanceVector distanceVector=null;
	
	
	public double[][] getRoutingTable()
	{
		createDistanceTable();
		getVia();
		getDestination();
		createRoutingTable();
		return routerTable;
	}

	private void createRoutingTable() {
		distanceVector =new DistanceVector();
		routerTable=new double[dest.size()][via.size()];
		int sourceVal=Integer.parseInt(sourceRouter.substring(1, sourceRouter.length()));
		for(int row=0;row<dest.size();row++)
		{
			for(int col=0;col<via.size();col++)
			{
				int y=Integer.parseInt(via.get(col).substring(1, via.get(col).length()));
				routerTable[row][col]=distanceInfo[sourceVal][y]+distanceVector.minimum(Topology.nodeDetails,via.get(col),dest.get(row));
			}
		}
		
		showRoutingTable();
	}

	

	

	private void showRoutingTable() {
		System.out.println(" Output is : ");
		for(int row=0;row<dest.size();row++)
		{
			for(int col=0;col<via.size();col++)
			{
				System.out.print(routerTable[row][col] + " ");
			}
			System.out.println();
		}
		
	}

	public ArrayList<String> getDestination() {
		dest=new ArrayList<String>();
		for(String destination: RoutingOpt.routers)
		{
			if(!(destination.equalsIgnoreCase(sourceRouter)))
				dest.add(destination);
		}
		showdesti();
		return dest;
	}

	private void showdesti() {
		System.out.println(" destinations");
				for(String d:dest)
					System.out.println(d);
	}

	public ArrayList<String> getVia() {
		via=new ArrayList<String>();
		via.clear();
		if(RoutingOpt.sourceRouter!=null)
		sourceRouter=RoutingOpt.sourceRouter;
		else
			JOptionPane.showMessageDialog(null, "no source router selected");
		//sourceRouter="R1";
		for(RouterDetails routerdetails: Topology.nodeDetails)
		{
			if(routerdetails.getStartRouter().equalsIgnoreCase(sourceRouter)&&(routerdetails.getEndRouter().equalsIgnoreCase(sourceRouter)))
				System.out.println();
			else if(routerdetails.getStartRouter().equalsIgnoreCase(sourceRouter))
				via.add(routerdetails.getEndRouter());
			else if(routerdetails.getEndRouter().equalsIgnoreCase(sourceRouter))
				via.add(routerdetails.getStartRouter());
			}
		//showvia();
		return via;
	}

	private void showvia() {
		System.out.println(" showing via"+via.size());
		// TODO Auto-generated method stub
		for(String v: via)
		{
			System.out.println(v);
		}
		
	}

	private void createDistanceTable() {
		distanceInfo=new double[RoutingOpt.routers.size()][RoutingOpt.routers.size()];
		
		for(RouterDetails rdetails: Topology.nodeDetails)
		{
			distanceInfo[Integer.parseInt(rdetails.getStartRouter().substring(1,rdetails.getStartRouter().length()))][Integer.parseInt(rdetails.getEndRouter().substring(1, rdetails.getEndRouter().length()))]=rdetails.getDistance();
			distanceInfo[Integer.parseInt(rdetails.getEndRouter().substring(1, rdetails.getEndRouter().length()))][Integer.parseInt(rdetails.getStartRouter().substring(1,rdetails.getStartRouter().length()))]=rdetails.getDistance();

		}
	showDistanceTable()	;
	}

	private void showDistanceTable() {
		System.out.println("<< Distance Table >>");
		for(int i=0;i<distanceInfo.length;i++)
		{
			for(int j=0;j<distanceInfo.length;j++)
			{
				System.out.print(distanceInfo[i][j] + " ");
			}
			System.out.println();
		}


		}
	
}
