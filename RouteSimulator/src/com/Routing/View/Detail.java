package com.Routing.View;


import javax.swing.JPanel;
import javax.swing.JLabel;

import com.Routing.util.Dijkstra;
import com.Routing.util.DistanceVector;
import com.Routing.util.Vertex;



import java.awt.Rectangle;
import java.util.List;

public class Detail extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel SrcDestinLabel = null;
	private JLabel NodesLabel = null;
	private JLabel AlgorithmLabel = null;
	private JLabel shortestpathLabel = null;
	private JLabel shortestDistanceLabel = null;
	private JLabel EfficencyLabel = null;
	private Dijkstra dijkstra=null;  //  @jve:decl-index=0:
	private DistanceVector disVector=null;  //  @jve:decl-index=0:
	private EfficencyDetails effdetails=null;
	

	/**
	 * This is the default constructor
	 */
	public Detail() {
		super();
		initialize();
		
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		EfficencyLabel = new JLabel();
		EfficencyLabel.setBounds(new Rectangle(50, 165, 160, 20));
		
		shortestDistanceLabel = new JLabel();
		shortestDistanceLabel.setBounds(new Rectangle(50, 135, 160, 20));
		
		shortestpathLabel = new JLabel();
		shortestpathLabel.setBounds(new Rectangle(50, 105, 160, 20));
		
		AlgorithmLabel = new JLabel();
		AlgorithmLabel.setBounds(new Rectangle(50, 75, 160, 20));
		
		NodesLabel = new JLabel();
		NodesLabel.setBounds(new Rectangle(50, 45, 160, 20));
		
		SrcDestinLabel = new JLabel();
		SrcDestinLabel.setBounds(new Rectangle(50, 15, 160, 20));
		setDetails();
		this.setSize(214, 195);
		this.setLayout(null);
		this.add(SrcDestinLabel, null);
		this.add(NodesLabel, null);
		this.add(AlgorithmLabel, null);
		this.add(shortestpathLabel, null);
		this.add(shortestDistanceLabel, null);
		this.add(EfficencyLabel, null);
	}

	private void setDetails() {
		dijkstra=new Dijkstra();
		disVector=new DistanceVector();
		effdetails=new EfficencyDetails();
		setSourceDestLabel();
		setNodesLabel();
		setAlgoLabel();
		setShortestPath();
		setShortestDistance();
		setEfficencyLabel();
		}
		

	private void setEfficencyLabel() {
		String algor=effdetails.getEfficentAlogorithm();
		double value=effdetails.getEfficencyValue();
		if(algor!=null && value>0)
		EfficencyLabel.setText("Efficency : "+algor+"("+value+")");
		
	}

	private void setShortestDistance() {
	
		double dist=dijkstra.getShortestDistance();
		double distnce=disVector.getShortestDistance();
		if(dist!=0)
			shortestDistanceLabel.setText("Distance Traveled : "+dist);
		else if(distnce!=0)
			shortestDistanceLabel.setText("Distance Traveled : "+dist);
		else
			shortestDistanceLabel.setText("Distance Traveled :  Infinity");
		
		
	}

	private void setShortestPath() {
		
		
		List<Vertex> paths=disVector.getShortestPath();
		if(Dijkstra.shortPath!=null)
			shortestpathLabel.setText("shortest path: "+Dijkstra.shortPath);
		else if(paths!=null)
			shortestpathLabel.setText("shortest path: "+paths);
		
		
	}

	private void setAlgoLabel() {
		if(RoutingOpt.algorithmType!=null)
		AlgorithmLabel.setText("Algorithm:"+RoutingOpt.algorithmType);
		
	}

	private void setNodesLabel() {
		if(RoutingOpt.noofnodes!=0)
		NodesLabel.setText(" Routers:"+RoutingOpt.noofnodes);
	}

	private void setSourceDestLabel() {
		if(RoutingOpt.sourceRouter!=null)
			SrcDestinLabel.setText(RoutingOpt.sourceRouter+"    To    "+RoutingOpt.destinationRouter);
	}
}
