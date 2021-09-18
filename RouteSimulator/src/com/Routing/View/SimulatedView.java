package com.Routing.View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.Routing.Model.RouterDetails;
import com.Routing.util.Flooding;
import com.Routing.util.HotPotato;
import com.Routing.util.LinkStateRouting;
import com.Routing.util.Rip;
import com.Routing.util.SourceRouting;

public class SimulatedView extends JPanel {

	private static final long serialVersionUID = 1L;
	private String algoType=null;
	private String sourceNode=null;
	private String destinNode=null;
	private JPanel DetailsPanel = null;
	private Flooding flooding=null;
	private HotPotato hotPotato=null;
	private LinkStateRouting linkstaterouting=null;
	private Rip rip=null;
	private SourceRouting sourcerouting=null;
	private ArrayList<RouterDetails> pathsToDraw=null;
	public  static int distances=0;
	

	/**
	 * This is the default constructor
	 */
	public SimulatedView() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		algoType=RoutingOpt.algorithmType; 
		this.setSize(450, 330);
		drawingDetails();
		this.setLayout(null);
		this.setBackground(new Color(219,225,213));
		//System.out.println(" 888888888888888888888888888888");
	}

	
	
	
	
	private void drawingDetails() { // Details needed to Draw Simulated Router ie source and destination node...
		
		pathsToDraw=new ArrayList<RouterDetails>();
		sourceNode=RoutingOpt.sourceRouter.trim();
		destinNode=RoutingOpt.destinationRouter.trim();
		System.out.println(" <<<<<<<<<<<<<<<<Algorithm Selected is "+algoType);
		if(algoType.equalsIgnoreCase("Flooding"))
		{
		flooding=new Flooding();
		pathsToDraw=flooding.routeTrace(Topology.nodeDetails,sourceNode, destinNode);
		
		}
		else if(algoType.equalsIgnoreCase("Hot-Potato"))
		{
			hotPotato=new HotPotato();
			pathsToDraw=hotPotato.routeTrace(Topology.nodeDetails,sourceNode,destinNode);
		}
		else if(algoType.equalsIgnoreCase("SourceRouting"))
		{
			System.out.println(" source routing selected .....");
			sourcerouting=new SourceRouting();
			pathsToDraw=sourcerouting.routeTrace(Topology.nodeDetails,sourceNode,destinNode);
		}
		else if(algoType.equalsIgnoreCase("RoutingInformationProtocol"))
		{
			rip=new Rip();
			pathsToDraw = rip.routerTrace(Topology.nodeDetails,sourceNode,destinNode);
		}
		else if(algoType.equalsIgnoreCase("LinkStateProtocol"))
		{
			linkstaterouting=new LinkStateRouting();
			pathsToDraw=linkstaterouting.routeTrace(Topology.nodeDetails,sourceNode,destinNode);
		}

		repaint();
	}
	
	public void paint(Graphics g)
	{
		String routernme=null;
		int count=0;
		BufferedImage img=null;
		for(Point pts: Topology.nodePositionArray){ //Displays Router in simulated environment 
			try {
				 img=ImageIO.read(new File("Images/router.jpeg"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			g.setColor(Color.BLUE);
			if((RoutingOpt.routers.get(count).equals(sourceNode)))
			{
			routernme=RoutingOpt.routers.get(count)+"(S)";
			}
			else if ((RoutingOpt.routers.get(count).equals(destinNode)))
			{
				routernme=RoutingOpt.routers.get(count)+"(D)";
			}
			else
			{
			g.setColor(Color.red);
			routernme=RoutingOpt.routers.get(count);
			}
			//g.fillOval((int)(pts.getX()),(int)(pts.getY()+50) , 15, 15);
			g.drawImage(img, (int)(pts.getX()+10),(int)(pts.getY()+60), this);  //Displaying the router image ...
			g.drawString(routernme, (int)(pts.getX()+10), (int)(pts.getY()+40));
			count++;
		}
		
		System.out.println("size of pathtodrw "+pathsToDraw.size());
		int distanceTraveled=0;
		for(RouterDetails r: pathsToDraw)  // Display router length in simulated environment..
		{
			g.setColor(Color.magenta);
			g.drawLine((int)(Topology.nodePositionArray.get(Integer.parseInt((r.getStartRouter()).substring(1, (r.getStartRouter().length()))))).getX()+7,(int) (Topology.nodePositionArray.get(Integer.parseInt((r.getStartRouter()).substring(1, (r.getStartRouter().length()))))).getY()+57, (int)(Topology.nodePositionArray.get(Integer.parseInt((r.getEndRouter()).substring(1, (r.getEndRouter().length()))))).getX()+7, (int)(Topology.nodePositionArray.get(Integer.parseInt((r.getEndRouter()).substring(1, (r.getEndRouter().length()))))).getY()+57);
			//g.drawString(""+r.getDistance(), (((int)(Topology.nodePositionArray.get(Integer.parseInt((r.getStartRouter()).substring(1, (r.getStartRouter().length()))))).getX()+7)+( (int)(Topology.nodePositionArray.get(Integer.parseInt((r.getEndRouter()).substring(1, (r.getEndRouter().length()))))).getX()+7)/2), (((int) (Topology.nodePositionArray.get(Integer.parseInt((r.getStartRouter()).substring(1, (r.getStartRouter().length()))))).getY()+57)+((int)(Topology.nodePositionArray.get(Integer.parseInt((r.getEndRouter()).substring(1, (r.getEndRouter().length()))))).getY()+57))/2);
			//Point distancePoint=getDistance((int)(Topology.nodePositionArray.get(Integer.parseInt((r.getStartRouter()).substring(1, (r.getStartRouter().length()))))).getX()+7,(int) (Topology.nodePositionArray.get(Integer.parseInt((r.getStartRouter()).substring(1, (r.getStartRouter().length()))))).getY()+57, (int)(Topology.nodePositionArray.get(Integer.parseInt((r.getEndRouter()).substring(1, (r.getEndRouter().length()))))).getX()+7, (int)(Topology.nodePositionArray.get(Integer.parseInt((r.getEndRouter()).substring(1, (r.getEndRouter().length()))))).getY()+57);
			System.out.println(" *********  Line Drawn *************** ****** ****");
			distanceTraveled= distanceTraveled+(int)r.getDistance();
			/*
			int dist=distanceTraveled/2;
			g.drawString("Source :" +sourceNode, 490, 590);
			g.drawString("Destin :" +destinNode, 490, 620);
			g.drawString("Distnc :" +dist, 490, 650);
			*/
		}
		pathDistance(distanceTraveled);
		
		/*	
		int xPosition=490;
		int yPosition=100;
		
		for(RouterDetails r: Topology.nodeDetails)
			
		{
			g.setColor(Color.BLACK);
			//if(!r.getStartRouter().equalsIgnoreCase(r.getEndRouter())&& ((r.getStartRouter().equalsIgnoreCase(sourceNode))||(r.getEndRouter().equals(sourceNode))))
			{
			g.drawString(r.getStartRouter() + " to " +r.getEndRouter() + " is " +r.getDistance() , xPosition, yPosition);
			yPosition = yPosition+20;
			}
		}
		*/
		
		
	}

	

	private void pathDistance(int i) {
		distances=i/2;
		System.out.println(" distance travelled "+distances);
		
	}

	private Point getDistance(int x1, int y1, int x2, int y2) {
		Point point=null;
		point.setLocation(((x1+x2)/2), ((y1+y2)/2));
		System.out.println(" Point : At"+point);
		return point;
	}


}
