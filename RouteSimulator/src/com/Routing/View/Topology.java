package com.Routing.View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Point;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;

import com.Routing.Model.RouterDetails;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Topology extends JPanel {

	private static final long serialVersionUID = 1L;
	private int noofnode=0;     // no of routers...
	public static String topologyName=null;  //  @jve:decl-index=0:
	private int noOfLine=0;         //no of paths ............
	private Point point;  //  @jve:decl-index=0:
	public static ArrayList<Point> nodePositionArray=null;  //position of each router
	public static ArrayList<RouterDetails> nodeDetails=null;  // returns the details of each router  //  @jve:decl-index=0:
	private BufferedImage img=null;
	
	
	/**
	 * This is the default constructor
	 */
	public Topology() {
		super();
		initialize();
	}
	public void paint(Graphics g)  // Draw the Topology 
	{
		try {
			img =ImageIO.read(new File("Images/router.jpeg"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for(int i=0;i<noofnode;i++){ //Displaying No of  Nodes (Router)>>>
			g.setColor(Color.RED);
			
			Point pts=nodePositionArray.get(i);
			//g.fillOval((int)pts.getX(),(int)pts.getY() , 15, 15);
			g.drawImage(img,(int)(pts.getX()-7),(int)(pts.getY()-7),this);
			g.drawString(RoutingOpt.routers.get(i), (int)(pts.getX()+10), (int)(pts.getY()-10));
			System.out.println(pts.getX()+pts.getY()+" and "+RoutingOpt.routers.get(i));
		}
		if(topologyName.equalsIgnoreCase("RING"))
		{
		for(int i=0;i<noOfLine;i++)
		{
			g.setColor(Color.orange);
			try
			{
				g.drawLine((int)nodePositionArray.get(i).getX(), (int)nodePositionArray.get(i).getY(),(int)nodePositionArray.get(i+1).getX(),(int)nodePositionArray.get(i+1).getY());
				findpathLenth((int)nodePositionArray.get(i).getX(), (int)nodePositionArray.get(i).getY(),(int)nodePositionArray.get(i+1).getX(),(int)nodePositionArray.get(i+1).getY(),RoutingOpt.routers.get(i),RoutingOpt.routers.get(i+1));
			}catch (Exception e) {
				// TODO: handle exception
				g.drawLine((int)nodePositionArray.get(i).getX(), (int)nodePositionArray.get(i).getY(),(int)nodePositionArray.get(0).getX(),(int)nodePositionArray.get(0).getY());
				findpathLenth((int)nodePositionArray.get(i).getX(), (int)nodePositionArray.get(i).getY(),(int)nodePositionArray.get(0).getX(),(int)nodePositionArray.get(0).getY(),RoutingOpt.routers.get(i),RoutingOpt.routers.get(0));
				}
		}
		}
		else if(topologyName.equalsIgnoreCase("MESH"))
		{
			g.setColor(Color.orange);
			for(int i=0;i<noOfLine;i++)
				for(int j=i;j<noOfLine;j++){
				g.drawLine((int)nodePositionArray.get(i).getX(), (int)nodePositionArray.get(i).getY(),(int)nodePositionArray.get(j).getX(),(int)nodePositionArray.get(j).getY());
				findpathLenth((int)nodePositionArray.get(i).getX(), (int)nodePositionArray.get(i).getY(),(int)nodePositionArray.get(j).getX(),(int)nodePositionArray.get(j).getY(),RoutingOpt.routers.get(i),RoutingOpt.routers.get(j));
				}
		}
		else if(topologyName.equalsIgnoreCase("BUS"))
		{
			g.setColor(Color.orange);
			for(int i=0;i<noOfLine;i++)
			{
				try
			{
				g.drawLine((int)nodePositionArray.get(i).getX(), (int)nodePositionArray.get(i).getY(),(int)nodePositionArray.get(i+1).getX(),(int)nodePositionArray.get(i+1).getY());
				findpathLenth((int)nodePositionArray.get(i).getX(), (int)nodePositionArray.get(i).getY(),(int)nodePositionArray.get(i+1).getX(),(int)nodePositionArray.get(i+1).getY(),RoutingOpt.routers.get(i),RoutingOpt.routers.get(i+1));
			}catch (Exception e) {}
			}
		}
		else if(topologyName.equalsIgnoreCase("STAR"))
		{
			g.setColor(Color.ORANGE);
			for(int i=1;i<noOfLine;i++)
			{
				g.drawLine((int)nodePositionArray.get(0).getX(), (int)nodePositionArray.get(0).getY(),(int)nodePositionArray.get(i).getX(),(int)nodePositionArray.get(i).getY());
				findpathLenth((int)nodePositionArray.get(0).getX(), (int)nodePositionArray.get(0).getY(),(int)nodePositionArray.get(i).getX(),(int)nodePositionArray.get(i).getY(),RoutingOpt.routers.get(0),RoutingOpt.routers.get(i));
			}
		}

	}

	public int getEdgeCount() {
		int edgeCount=Topology.nodeDetails.size();
		System.out.println("5555555555555555555555555555"+edgeCount);
		return edgeCount;
	}
	private void findpathLenth(int x1, int y1, int x2, int y2, String startNode,String endNode) { //find the path length between two Routers and add the details to a arrayList
		// The Function will give you details about which routers are interconnected ....
		
		int distance=(int)Math.sqrt((Math.pow((double)(x2-x1), 2.0)+Math.pow((double)(y2-y1), 2.0))); //distance formula
			if(distance!=0)
			{
			RouterDetails routerdetails=new RouterDetails();
			routerdetails.setStartRouter(startNode);
			routerdetails.setEndRouter(endNode);
			routerdetails.setDistance(distance);
			nodeDetails.add(routerdetails);
			System.out.println("router added to node details"+startNode +endNode +distance);
			}
			
	}
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		
		this.setSize(450, 350);
		nodePositionArray=new ArrayList<Point>();
		nodeDetails = new ArrayList<RouterDetails>();
		try{
		noofnode=RoutingOpt.noofnodes;
		topologyName=RoutingOpt.topoloyType.trim();
		}
		catch (NullPointerException nullptrexp) {
		
			JOptionPane.showMessageDialog(null, "Select toplogy type and no of nodes");
		}
		noOfLine=pathLines(noofnode,topologyName);
		nodePosition(noofnode);
		this.setLayout(null);
	
	}
	private void nodePosition(int node) {
		// TODO Finds the positon of each Router 
		Random ptgeneration=new Random();
		for(int i=1;i<=node;i++)
		{
		int xaxis=ptgeneration.nextInt(375);
		int yaxis=ptgeneration.nextInt(275);
		System.out.println(" points at"+xaxis+" and "+yaxis);
		Point pt=new Point();
		pt.setLocation(xaxis, yaxis);
		nodePositionArray.add(pt);             // saving the position of each Router
		}
	}
	private int pathLines(int node, String topName) {
		int n=0;
		// TODO pathLines() is not need in this class
		if(topName.equalsIgnoreCase("RING"))
			n=node;
		else if(topName.equalsIgnoreCase("MESH"))
			n=node;
		else if(topName.equalsIgnoreCase("BUS"))
			n=node;
		else if(topName.equals("STAR"))
			n=node;
		return n;
	}
	

}  //  @jve:decl-index=0:visual-constraint="8,8"
