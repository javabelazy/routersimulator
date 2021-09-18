package com.Routing.util;

import java.util.ArrayList;

import com.Routing.Model.RouterDetails;
import com.Routing.View.RoutingOpt;
import com.Routing.View.Topology;

public class Router {
	
	private static ArrayList<RouterDetails> elementsToDelete=null;

	public static  void crashTheRouter(String removeRouter, String algorithmType) {
		System.out.println("inside Router class  crashTherouter()");
		viewNodeDetails();
		elementsToDelete=new ArrayList<RouterDetails>();
		int i=0;
		try{
		for(RouterDetails routerDetails: Topology.nodeDetails)
		{
			if(algorithmType.equalsIgnoreCase("Flooding")||(algorithmType.equalsIgnoreCase("Hotpotato")))
			{
				if(routerDetails.getStartRouter().equalsIgnoreCase(removeRouter))
				{
				elementsToDelete.add(routerDetails);
					
				}
			}
			else if(routerDetails.getStartRouter().equalsIgnoreCase(removeRouter)||(routerDetails.getEndRouter().equalsIgnoreCase(removeRouter)))
			{
				//System.out.println("<<<"+routerDetails.getDistance()+"@"+i);
				elementsToDelete.add(routerDetails);
				
			}
			
			i++;
		}
		showpositionDelete();
		shownodesAfterDelete();
		
	}catch(NullPointerException e){}
	}

	private static void shownodesAfterDelete() {
		// TODO Auto-generated method stub
		int count=0;
		System.out.println(" elements after deletion....");
		for(RouterDetails rd: Topology.nodeDetails)
		{
			System.out.println(count +")"+rd.getStartRouter() +""+rd.getEndRouter());
			count++;
		}
		
	}

	private static void showpositionDelete() {
		for(RouterDetails inte: elementsToDelete)
		{
			System.out.println(" positions "+inte);
		}
		// TODO Auto-generated method stub
		
		deleteTheRouter();
		
	}

	private static void deleteTheRouter() {
		// TODO Auto-generated method stub
		for(RouterDetails rdetials: elementsToDelete)
		{
			Topology.nodeDetails.remove(rdetials);
			System.out.println(rdetials +"removed ");
		}
		
	}

	private static void viewNodeDetails() {
		int count=0;
		// View all the details of router before crashing....
		System.out.println(" view nodedetails");
		
		for(RouterDetails rd: Topology.nodeDetails)
		{
			System.out.println(count +")"+" " +rd.getStartRouter() +""+rd.getEndRouter());
			count++;
		}
		
		
	}

	
}
