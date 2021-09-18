package com.Routing.util;

import java.util.ArrayList;

import com.Routing.Model.RouterDetails;
import com.Routing.View.EfficencyDetails;
import com.Routing.View.RoutingOpt;
import com.Routing.View.Topology;

public class HotPotato {

	public ArrayList<RouterDetails> routeTrace(
			ArrayList<RouterDetails> nodeDetails, String sourceNode,
			String destinNode) {
		  //Returns the path to draw...
		// TODO Auto-generated method stub
		System.out.println(" inside HotPotato.java @ route Trace ()");
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
//O(n ln n)
		double efficency=0;
		try{
		int edgeCount=Topology.nodeDetails.size();
		 efficency=edgeCount * Math.log(edgeCount);
		
		}
		
		
		catch(NullPointerException e){
			return 0 ;
	}
		return efficency;
}}
