package com.Routing.View;


import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.Routing.Model.RouterDetails;
import com.Routing.util.Router;
import com.Routing.util.RoutingTable;

public class RoutingOpt extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	public JPanel ViewPanel = null;
	private JPanel ControlPanel = null;
	private JComboBox TopologyComboBox = null;
	private JButton ViewButton = null;
	private JComboBox AlgorithmComboBox = null;
	public static int noofnodes=0;
	public static String topoloyType=null;
	private boolean clicked=false;
	private boolean simulateButtonClicked=false;
	public static ArrayList<String> routers=null;  //  @jve:decl-index=0:
	public static String algorithmType=null;
	
	private JButton tableButton = null;
	private RouteTable routeTable=null;
	public JPanel SimulatedPanel = null;
	private JPanel userInputPanel = null;
	private JLabel SrcDest = null;
	private JComboBox SourceComboBox = null;
	private JComboBox DestinComboBox = null;
	private JLabel SourceLabel = null;
	private JLabel DestLabel = null;
	private JButton SimulateButton = null;
	public static String sourceRouter=null;
	public static String destinationRouter=null; 
	public static String crashedRouter=null;
	private Topology topology=null;
	private JMenuBar RoutingOptJMenuBar = null;
	private JMenu FileMenu = null;
	private JMenuItem FileMenuItem = null;
	private JMenu HelpMenu = null;
	private JButton ClearButton = null;
	private JPanel EfficencyPanel = null;
	protected JPanel DetailsPanel = null;
	private JMenuItem AboutMenuItem = null;
	private JMenuItem NoteMenuItem = null;
	private JComboBox CrashComboBox = null;
	private JLabel CrashLabel = null;
	private EfficencyDetails efficenydetails=null;
	private JButton EfficiencyButton = null;
	/**
	 * This is the default constructor
	 */
	public RoutingOpt() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(900, 800);
		this.setJMenuBar(getRoutingOptJMenuBar());
	    this.setContentPane(getJContentPane());
		this.setTitle(" Routing Simulator");
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBackground(new Color(244,244,244));
		
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getViewPanel(), null);
			jContentPane.add(getControlPanel(), null);
			jContentPane.add(getSimulatedPanel(), null);
			jContentPane.add(getUserInputPanel(), null);
			jContentPane.add(getEfficencyPanel(), null);
			jContentPane.add(getDetailsPanel(), null);
		
		}
		return jContentPane;
	}

	/**
	 * This method initializes ViewPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getViewPanel() {
		if (ViewPanel == null) {
			ViewPanel = new JPanel();
			ViewPanel.setLayout(new GridBagLayout());
			ViewPanel.setBounds(new Rectangle(7, 32, 450, 350));
			ViewPanel.setBackground(new Color(219,225,213));
			ViewPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		}
		return ViewPanel;
	}

	/**
	 * This method initializes ControlPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getControlPanel() {
		if (ControlPanel == null) {
			ControlPanel = new JPanel();
			ControlPanel.setLayout(null);
			ControlPanel.setBounds(new Rectangle(476, 29, 214, 177));
			ControlPanel.setBackground(new Color(219,225,213));
			ControlPanel.add(getTopologyComboBox(), null);
			ControlPanel.add(getViewButton(), null);
			ControlPanel.add(getAlgorithmComboBox(), null);
			ControlPanel.add(getTableButton(), null);
			ControlPanel.add(getClearButton(), null);
		}
		return ControlPanel;
	}

	/**
	 * This method initializes TopologyComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getTopologyComboBox() {
		if (TopologyComboBox == null) {
			TopologyComboBox = new JComboBox();
			TopologyComboBox.setBounds(new Rectangle(50, 10, 115, 25));
			TopologyComboBox.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("topology clicked"); // TODO Auto-generated Event stub actionPerfor
					System.out.println(TopologyComboBox.getSelectedItem());
					if(!TopologyComboBox.getSelectedItem().equals("Select Topology")){
					try{
						noofnodes=Integer.parseInt(JOptionPane.showInputDialog("Enter Number Of Nodes"));
					}catch (NumberFormatException nfe) {
							JOptionPane.showMessageDialog(null," check the input ");
							}
					topoloyType=TopologyComboBox.getSelectedItem().toString();
					System.out.println("Nodes:"+noofnodes);
					routerNames(noofnodes);
					viewDetailsPanel();
					}
				}
			});
		}
		TopologyComboBox.addItem("Select Topology");
		TopologyComboBox.addItem("  BUS ");
		TopologyComboBox.addItem(" MESH ");
		TopologyComboBox.addItem(" RING ");
		TopologyComboBox.addItem(" STAR ");
		return TopologyComboBox;
	}

	protected void viewDetailsPanel() {
		// TODO Auto-generated method stub
		Detail detailPanel=new Detail();
		DetailsPanel.removeAll();
		DetailsPanel.add(detailPanel);
		DetailsPanel.repaint();
	}

	public int getnoOfnode() {
		int nodes=RoutingOpt.noofnodes;
		return nodes;
	}

	protected void routerNames(int nfnodes) {
		// Setting the name of Routers
		String rinput="";
		routers=new ArrayList<String>();
		for(int i=0;i<nfnodes;i++)
		{
		routers.add("R"+i);	
		rinput=rinput+i;
		}
		addNode();
	}

	/**
	 * This method initializes ViewButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getViewButton() {
		if (ViewButton == null) {
			ViewButton = new JButton();
			ViewButton.setBounds(new Rectangle(50, 40, 115, 25));
			ViewButton.setText("View");
			ViewButton.setBackground(new Color(255,255,255));
			ViewButton.setToolTipText(" To View the Router Topology ");
			ViewButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("view button clicked"); // TODO Auto-generated Event stub actionPerformed()
					if(clicked==false)
					{
				topology=new Topology();
				//topology.repaint();
				ViewPanel.removeAll();
				ViewPanel.add(topology); // To view jpanel in view button click
				ViewPanel.repaint();
				//clicked=true;
					}
				
				}
			});
		}
		return ViewButton;
	}

	/**
	 * This method initializes AlgorithmComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getAlgorithmComboBox() {
		if (AlgorithmComboBox == null) {
			AlgorithmComboBox = new JComboBox();
			AlgorithmComboBox.setBounds(new Rectangle(50, 100, 115, 25));
			AlgorithmComboBox.setToolTipText("Select a routing algorithm");
		}
		AlgorithmComboBox.addItem("Select Algorithm");
		AlgorithmComboBox.addItem("Flooding");
		AlgorithmComboBox.addItem("Hot-Potato");
		AlgorithmComboBox.addItem("SourceRouting");
		AlgorithmComboBox.addItem("RoutingInformationProtocol");
		AlgorithmComboBox.addItem("LinkStateProtocol");
	
		
		return AlgorithmComboBox;
	}

	protected void addNode() {
		// TODO Auto-generated method stub
		int i=0;
		CrashComboBox.addItem("");
		for(String s: RoutingOpt.routers)
		{
			SourceComboBox.addItem(RoutingOpt.routers.get(i));
			DestinComboBox.addItem(RoutingOpt.routers.get(i));
			CrashComboBox.addItem(RoutingOpt.routers.get(i));
			i++;
		}
		
	}

	/**
	 * This method initializes tableButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getTableButton() {
		if (tableButton == null) {
			tableButton = new JButton();
			tableButton.setBounds(new Rectangle(52, 135, 115, 25));
			tableButton.setText("Table ");
			tableButton.setToolTipText("Routing table");
			tableButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//RoutingTable rTable=new RoutingTable();
					//rTable.getRoutingTable();
					
					System.out.println("table button clicked"); // TODO Auto-generated Event stub actionPerformed()
					routeTable=new RouteTable();
					//ViewPanel.removeAll();
					//ViewPanel.add(routeTable);
					//ViewPanel.repaint();
					SimulatedPanel.removeAll();
					SimulatedPanel.add(routeTable);
					SimulatedPanel.repaint();
					viewDetailsPanel();
				}
			});
			tableButton.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if(e.getClickCount()==2)
					{
						RoutingDetails routingDetails=new RoutingDetails();
						SimulatedPanel.removeAll();
						SimulatedPanel.add(routingDetails);
						SimulatedPanel.repaint();
					}
				}
			});
			
		}
		return tableButton;
	}

	/**
	 * This method initializes SimulatedPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	public JPanel getSimulatedPanel() {
		if (SimulatedPanel == null) {
			SimulatedPanel = new JPanel();
			SimulatedPanel.setBackground(new Color(219,225,213));
			SimulatedPanel.setLayout(new GridBagLayout());
			SimulatedPanel.setBounds(new Rectangle(8, 386, 450, 330));
			SimulatedPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		}
		return SimulatedPanel;
	}

	/**
	 * This method initializes userInputPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getUserInputPanel() {
		if (userInputPanel == null) {
			CrashLabel = new JLabel();
			CrashLabel.setText("crash");
			CrashLabel.setBounds(new Rectangle(50, 86, 44, 22));
			DestLabel = new JLabel();
			DestLabel.setBounds(new Rectangle(50, 60, 51, 18));
			DestLabel.setText("Destin :");
			SourceLabel = new JLabel();
			SourceLabel.setBounds(new Rectangle(50, 30, 48, 18));
			SourceLabel.setText("src :");
			SrcDest = new JLabel();
			SrcDest.setBounds(new Rectangle(50, 3, 116, 18));
			SrcDest.setText("Source and Destination");
			userInputPanel = new JPanel();
			//userInputPanel.setVisible(false);
			userInputPanel.setLayout(null);
			userInputPanel.setBounds(new Rectangle(476,228, 215, 193));
			userInputPanel.setBackground(new Color(219,225,213));
			userInputPanel.add(SrcDest, null);
			userInputPanel.add(getSourceComboBox(), null);
			userInputPanel.add(getDestinComboBox(), null);
			userInputPanel.add(SourceLabel, null);
			userInputPanel.add(DestLabel, null);
			userInputPanel.add(getViewButton2(), null);
			userInputPanel.add(getCrashComboBox(), null);
			userInputPanel.add(CrashLabel, null);
			userInputPanel.add(getEfficiencyButton(), null);
		}
		return userInputPanel;
	}

	/**
	 * This method initializes SourceComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getSourceComboBox() {
		if (SourceComboBox == null) {
			SourceComboBox = new JComboBox();
			SourceComboBox.setToolTipText("Starting Router");
			SourceComboBox.setBounds(new Rectangle(100, 25, 65, 25));
		}
		return SourceComboBox;
	}

	/**
	 * This method initializes DestinComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getDestinComboBox() {
		if (DestinComboBox == null) {
			DestinComboBox = new JComboBox();
			DestinComboBox.setToolTipText("Ending Router");
			DestinComboBox.setBounds(new Rectangle(100, 55,65, 25));
		}
		return DestinComboBox;
	}

	/**
	 * This method initializes viewButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getViewButton2() {
		if (SimulateButton == null) {
			SimulateButton = new JButton();
			SimulateButton.setBounds(new Rectangle(51, 113, 115, 25));
			SimulateButton.setText("Simulate");
			SimulateButton.setToolTipText("simulated view of router");
			SimulateButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("smulate button clicked.."); // TODO Auto-generated Event stub actionPerformed()
					
					algorithmType=AlgorithmComboBox.getSelectedItem().toString().trim();
					try
					{
					if(AlgorithmComboBox.getSelectedIndex()>0)
					{
					sourceRouter=SourceComboBox.getSelectedItem().toString().trim();
					destinationRouter=DestinComboBox.getSelectedItem().toString().trim();
					crashedRouter=CrashComboBox.getSelectedItem().toString().trim();
					System.out.println("ssr"+sourceRouter +""+destinationRouter);
					showDetails(); 
					if(crashedRouter.length()>0)
					{if(CrashComboBox.getSelectedItem()==SourceComboBox.getSelectedItem()||CrashComboBox.getSelectedItem()==DestinComboBox)
					{System.out.println("Source & Destination routers can't be crashed ");
					JOptionPane.showMessageDialog(null, "Source or Destination routers can't be crashed ");
					}Router router=new Router();
					router.crashTheRouter(crashedRouter,algorithmType);
					}
		
					SimulatedView simulatedViewPanel =new SimulatedView();
					SimulatedPanel.removeAll();
					
					SimulatedPanel.add(simulatedViewPanel);
					SimulatedPanel.setBounds(15, 368, 450, 350);
					SimulatedPanel.repaint();
					
				
					/*
					Detail detail=new Detail();
					DetailsPanel.removeAll();
					DetailsPanel.add(detail);
					DetailsPanel.repaint();
					*/
					}
					}catch (NullPointerException nullptrexp) {
						JOptionPane.showMessageDialog(null, "Draw the Topology first ");
					}
					viewDetailsPanel();
				}
		});
		}
		return SimulateButton;
	}

	

	protected void showDetails() {
		// TODO List the details of all connected Routers..... + their distance
		
		System.out.println(" inside showDetails() @ setNode.java");
		for(RouterDetails r: Topology.nodeDetails)
		{
			System.out.println(r.getStartRouter() +":"+ r.getEndRouter() +"@"+r.getDistance());
		}	
	}

	/**
	 * This method initializes RoutingOptJMenuBar	
	 * 	
	 * @return javax.swing.JMenuBar	
	 */
	private JMenuBar getRoutingOptJMenuBar() {
		if (RoutingOptJMenuBar == null) {
			RoutingOptJMenuBar = new JMenuBar();
			RoutingOptJMenuBar.add(getFileMenu());
			RoutingOptJMenuBar.add(getHelpMenu());
		}
		return RoutingOptJMenuBar;
	}

	/**
	 * This method initializes FileMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getFileMenu() {
		if (FileMenu == null) {
			FileMenu = new JMenu();
			FileMenu.setText("File");
			FileMenu.add(getFileMenuItem());
		}
		return FileMenu;
	}

	/**
	 * This method initializes FileMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getFileMenuItem() {
		if (FileMenuItem == null) {
			FileMenuItem = new JMenuItem();
			FileMenuItem.setText("Exit");
			FileMenuItem.setToolTipText("To close the program");
			FileMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("Exit menu clicked"); // TO dispose the Router simulator...
					dispose();                   
				}
			});
		}
		return FileMenuItem;
	}

	/**
	 * This method initializes HelpMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getHelpMenu() {
		if (HelpMenu == null) {
			HelpMenu = new JMenu();
			HelpMenu.setText("Help");
			HelpMenu.setToolTipText("show the help");
			HelpMenu.add(getAboutMenuItem());
			HelpMenu.add(getNoteMenuItem());
		}
		return HelpMenu;
	}

	/**
	 * This method initializes ClearButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getClearButton() {
		if (ClearButton == null) {
			ClearButton = new JButton();
			ClearButton.setBounds(new Rectangle(50, 76, 115, 25));
			ClearButton.setText(" Clear");
			ClearButton.setToolTipText("Clear all");
			ClearButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("clear Button"); // TODO Auto-generated Event stub actionPerformed()
					clearAll();
				}
			});
		}
		return ClearButton;
	}

	protected void clearAll() {
		// Clearing 
		ViewPanel.removeAll();
		ViewPanel.repaint();
		EfficencyPanel.removeAll();
		EfficencyPanel.repaint();
		DetailsPanel.removeAll();
		DetailsPanel.repaint();
		SimulatedPanel.removeAll();
		SimulatedPanel.repaint();
		//clicked=false;
		simulateButtonClicked=false;
		SourceComboBox.removeAllItems();
		DestinComboBox.removeAllItems();
		CrashComboBox.removeAllItems();
		
		RoutingOpt.noofnodes=0;;
		Topology.nodeDetails.clear();
	//	TopologyComboBox.s
	
	}

	/**
	 * This method initializes EfficencyPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getEfficencyPanel() {
		if (EfficencyPanel == null) {
			EfficencyPanel = new JPanel();
			EfficencyPanel.setLayout(null);
			EfficencyPanel.setBackground(new Color(219,225,213));
			EfficencyPanel.setBounds(new Rectangle(477, 425, 215, 110));
		}
		return EfficencyPanel;
	}

	/**
	 * This method initializes DetailsPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	public JPanel getDetailsPanel() {
		if (DetailsPanel == null) {
			DetailsPanel = new JPanel();
			DetailsPanel.setLayout(null);
			DetailsPanel.setBackground(new Color(219,225,213));
			DetailsPanel.setBounds(new Rectangle(477, 539, 214, 195));
		}
		return DetailsPanel;
	}

	/**
	 * This method initializes AboutMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getAboutMenuItem() {
		if (AboutMenuItem == null) {
			AboutMenuItem = new JMenuItem();
			AboutMenuItem.setText("About");
			AboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("About clicked ");
					AboutDetails about =new AboutDetails();
					about.setVisible(true);// TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return AboutMenuItem;
	}

	/**
	 * This method initializes NoteMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getNoteMenuItem() {
		if (NoteMenuItem == null) {
			NoteMenuItem = new JMenuItem();
			NoteMenuItem.setText("Notes");
			NoteMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Notes note=new Notes();
					note.setVisible(true);   //note.show(); is depricated 	
				}
			});
		}
		return NoteMenuItem;
	}

	/**
	 * This method initializes CrashComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getCrashComboBox() {
		if (CrashComboBox == null) {
			CrashComboBox = new JComboBox();
			CrashComboBox.setBounds(new Rectangle(100, 85,65, 25));
			CrashComboBox.setToolTipText("Crash a particular router");
			
		}
		return CrashComboBox;
	}

	/**
	 * This method initializes EfficiencyButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getEfficiencyButton() {
		if (EfficiencyButton == null) {
			EfficiencyButton = new JButton();
			EfficiencyButton.setBounds(new Rectangle(51, 147, 115, 25));
			EfficiencyButton.setText(" Efficiency");
			EfficiencyButton.setToolTipText("Efficency of algorithms");
			EfficiencyButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					efficenydetails =new EfficencyDetails();
					EfficencyPanel.removeAll();
					EfficencyPanel.add(efficenydetails);
					EfficencyPanel.repaint();
					viewDetailsPanel();
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return EfficiencyButton;
	}
	

}  //  @jve:decl-index=0:visual-constraint="29,-111"

