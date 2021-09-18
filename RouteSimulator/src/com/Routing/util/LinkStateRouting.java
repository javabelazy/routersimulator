package com.Routing.util;

import java.util.ArrayList;
import java.util.List;

import com.Routing.Model.RouterDetails;
import com.Routing.View.RoutingOpt;
import com.Routing.View.Topology;

public class LinkStateRouting {
	private Dijkstra dijkstra=null;
	private List shortestWay=null;

	public ArrayList<RouterDetails> routeTrace(
			ArrayList<RouterDetails> nodeDetails, String sourceNode,
			String destinNode) {
		shortestWay=new ArrayList();
		dijkstra =new Dijkstra();
		ArrayList<RouterDetails> route =new ArrayList<RouterDetails>();
		shortestWay=dijkstra.dijkstraShortestPath(nodeDetails);
		System.out.println(" insided rip" +shortestWay);
		
		for(int i=0;i<shortestWay.size()-1;i++)
		{
			for(RouterDetails rd: nodeDetails)
			{
				
				if(((rd.getStartRouter().equalsIgnoreCase(shortestWay.get(i).toString()))&&(rd.getEndRouter().equalsIgnoreCase(shortestWay.get(i+1).toString()))||((rd.getStartRouter().equalsIgnoreCase(shortestWay.get(i+1).toString()))&&(rd.getEndRouter().equalsIgnoreCase(shortestWay.get(i).toString())))))
						{
						route.add(rd);
						System.out.println(" Link state Routing calculated");
						}
			}
		}
		
		return route;
	}

	public double getEfficency() {
		// O(|E|+2|V|2)
		try{
		int vertexCount=RoutingOpt.noofnodes;
		int edgeCount=Topology.nodeDetails.size();
		double efficency=2*edgeCount + Math.pow(vertexCount, 2);
		return efficency;
	}
	catch(NullPointerException e)
	{
	}
	return 0;
	
	}

}
