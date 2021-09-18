package com.Routing.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

import com.Routing.Model.RouterDetails;
import com.Routing.View.RoutingOpt;

public class DistanceVector {

	private double pathDistance=0;
	private List<Vertex> shortPath=null;
	
	public static void computePaths(Vertex source) {
		source.minDistance = 0.;
		PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
		vertexQueue.add(source);

		while (!vertexQueue.isEmpty()) {
			Vertex u = vertexQueue.poll();

			// Visit each edge exiting u
			for (Edge e : u.adjacencies) {
				Vertex v = e.target;
				double weight = e.weight;
				double distanceThroughU = u.minDistance + weight;
				if (distanceThroughU < v.minDistance) {
					vertexQueue.remove(v);
					v.minDistance = distanceThroughU;
					v.previous = u;
					vertexQueue.add(v);
				}
			}
		}
	}

	public static List<Vertex> getShortestPathTo(Vertex target) {
		List<Vertex> path = new ArrayList<Vertex>();
		for (Vertex vertex = target; vertex != null; vertex = vertex.previous)
			path.add(vertex);
		Collections.reverse(path);
		return path;
	}
	
	
	

	public double minimum(ArrayList<RouterDetails> nodeDetails, String src,String des) {

		double distance=0;
		
		
		System.out.println(" inside distance vector . java");
		Vertex[] vertices=new Vertex[RoutingOpt.noofnodes];
		
		
		for(int i=0;i<vertices.length;i++)
			vertices[i]=new Vertex("R"+i);              //Adding Router name to vertices...      
		
		System.out.println(" size of vertices :  "+vertices.length);
		

		for(int i=0;i<vertices.length;i++)
		{
			Edge[] edges=new Edge[vertices.length-1];
			System.out.println("length of edges is  "+edges.length);
			int post=1;
			for(int j=0;j<edges.length;j++)
			{
				System.out.println("from vertex " +i +" to vertex " +j);
				double weight=0;
				for(RouterDetails rd: nodeDetails)
				{
					//System.out.println(rd.getStartRouter() + " "+ vertices[i].toString() +" "+ rd.getEndRouter() + " "+vertices[post].toString());
					if((rd.getStartRouter().equalsIgnoreCase(vertices[i].toString())&rd.getEndRouter().equalsIgnoreCase(vertices[post].toString()))||(rd.getStartRouter().equalsIgnoreCase(vertices[post].toString())&rd.getEndRouter().equalsIgnoreCase(vertices[i].toString())))
					{
						weight=rd.getDistance();
						break;
					}
					else
					{
						continue;
					}
				}
				if(weight==0)
					weight=Double.POSITIVE_INFINITY;
				edges[j]=new Edge(vertices[post],weight);
				System.out.println(" weight added :"+weight);
				post++;
			}
			
			vertices[i].adjacencies=edges;
		}
		
		//computePaths(vertices[0]);
		//int position= Integer.parseInt(RoutingOpt.sourceRouter.substring(1,RoutingOpt.sourceRouter.length()));
		computePaths(vertices[Integer.parseInt(src.substring(1,src.length()))]);
		
		
		for (Vertex v : vertices) {
			int posit=Integer.parseInt(des.substring(1, des.length()));
			v=vertices[posit];
			distance=v.minDistance;
			pathDistance=v.minDistance;
			System.out.println("Distance to " + v + ": " + v.minDistance);
			List<Vertex> path = getShortestPathTo(v);
			shortPath=path;
			System.out.println("paths identified: " + path);
		
		}
	
		return distance;
	}

	public double getShortestDistance() {
		return pathDistance;
	}

	public List<Vertex> getShortestPath() {
		// TODO Auto-generated method stub
		return shortPath;
	}

	


}
