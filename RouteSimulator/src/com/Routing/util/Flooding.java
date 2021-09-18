package com.Routing.util;

import java.util.ArrayList;

import com.Routing.Model.RouterDetails;
import com.Routing.View.RoutingOpt;
import com.Routing.View.Topology;

public class Flooding {

	public Flooding()
	{
		
	}

	public void setNodePosition() {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<RouterDetails> routeTrace(ArrayList<RouterDetails> nodeDetails, String source, String Destination) {  //Returns the path to draw...
		// TODO Auto-generated method stub
		System.out.println(" inside Flooding @ route Trace ()");
		ArrayList<RouterDetails> route =new ArrayList<RouterDetails>();
		for(RouterDetails rd: nodeDetails)
		{
			RouterDetails routings=new RouterDetails();
			routings.setStartRouter(rd.getStartRouter());
			routings.setEndRouter(rd.getEndRouter());
			System.out.println(rd.getStartRouter() +" and "+ rd.getEndRouter() + " Added to ArrayList ");
			route.add(routings);
		}
		return route;
	}

	public double getEfficency() {
		//2e-n+1
		double efficency=0;
		try{
			int vertexCount=RoutingOpt.noofnodes;
		int edgeCount=Topology.nodeDetails.size();
		 efficency=(2*edgeCount) - vertexCount+1;
		}
		catch (NullPointerException e) {

		}
		return efficency;
	}

	
}
