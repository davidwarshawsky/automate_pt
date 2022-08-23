package gui;


//https://stackoverflow.com/questions/12694790/dragging-jpanels-inside-jframe
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import image_mod.ChangeImageSize;

public class FrontPage extends JFrame{
	
//	Class Instance Variables 
	public ArrayList<Router> routers;
//	public ArrayList<JPanel> routerPanels;
	JPanel panel;
	JPanel optionsPanel;
	JPanel networkPanel;
	Border blackline;
	int x = 0;
	int y = 0;
	
	
	
//	Constructor
	public FrontPage() {
//		Set Frame router_name to "Cisco Automate"
		super("Cisco Automate");
		panel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 0.0;
		c.weighty = 0.0;
		
//		Initialize routers array		
		routers = new ArrayList<Router>();
		blackline = BorderFactory.createLineBorder(Color.black);
		setupNetworkPanel();
		setupOptionPanel();
		
		setup();
		
		c.gridx = 0;
		c.gridy = 0;
		//columns
		c.gridwidth = 1;
		//rows
		c.gridheight = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(optionsPanel,c);
		
		
		c.gridx = 0;
		c.gridy = 1;
		//columns
		c.gridwidth = 1;
		//rows
		c.gridheight = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(networkPanel,c);
		
		setSize(1000, 600);
		setBorders();
		finish();
		
	}
	
	private void setBorders() {
		optionsPanel.setBorder(blackline);
		networkPanel.setBorder(blackline);
		panel.setBorder(blackline);
	}
	
	private void setupOptionPanel() {
		optionsPanel = new JPanel();
		JButton newRouterButton = new JButton("New Router");
		newRouterButton = addNewRouterButtonListener(newRouterButton);
		
		JButton exportNetworkScriptButton = new JButton("Export Network Script");
		
//		Add  the buttons to a box which will be added to optionsPanel 
		optionsPanel.add(newRouterButton);
		optionsPanel.add(exportNetworkScriptButton);
//		newRouterButton.setSize(100,20);
//		exportNetworkScriptButton.setSize(100,20);
		return;
	}
	private void setupNetworkPanel() {
		
		networkPanel = new JPanel();
		return;
	}
	
	private void setup() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		addWindowListener(new WindowAdapter(){
		     @Override
		    public void windowClosing(WindowEvent e){
		        FrontPage.this.dispose();
		    }
		});
		setLocationByPlatform(true);
		
	}
	
	private void finish() {
		setContentPane(panel);
		optionsPanel.setVisible(true);
		networkPanel.setVisible(true);
		panel.setVisible(true);
		networkPanel.invalidate();
		optionsPanel.invalidate();
		networkPanel.revalidate();
		optionsPanel.revalidate();
		networkPanel.repaint();
		optionsPanel.repaint();
		invalidate();
		revalidate();
		pack();
		repaint();
		setVisible(true);
	}
	
	private JButton addNewRouterButtonListener(JButton button) {
//		Add an actionListener to the new Router button
		button.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
//				Create a new Router
				Router newRouter = new Router();
				routers.add(newRouter);
//				Create an editable Router router_name field (to be below the router image)
				JTextField router_name = new JTextField(newRouter.hostname);
//				Add the new router to ArrayList of routers
				routers.add(newRouter);
				final JPanel routerPanel = new JPanel();
//				Create a 200x200 ImageIcon of a router
				ImageIcon ii = ChangeImageSize.resize("gui/router.png", 200, 200);
//				Create a JButton from the ImageIcon router.
				JButton router = new JButton(ii);
				routerPanel.add(router);
				routerPanel.setBorder(blackline);
				router.setAlignmentX(Component.LEFT_ALIGNMENT);
				routerPanel.setLayout(new BoxLayout(routerPanel,BoxLayout.Y_AXIS));
				routerPanel.setSize(200,220);
				routerPanel.setBorder(blackline);
				
				router.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						if (SwingUtilities.isRightMouseButton(e)) {
							PopupRouterMenu menu = new PopupRouterMenu();
					        menu.show(e.getComponent(), e.getX(), e.getY());
						}
					}			
				});
//				routerPanel.add(router); //,Component.CENTER_ALIGNMENT
//				router.setAlignmentX(Component.CENTER_ALIGNMENT);
//				Add the JTextField router router_name to the routerPanel.
//				routerPanel.add(router_name); // , BorderLayout.CENTER
//				router_name.setAlignmentX(Component.CENTER_ALIGNMENT);
//				router_name.setSize(100,20);
				routerPanel.add(router_name);
				router_name.setAlignmentX(Component.LEFT_ALIGNMENT);
				routerPanel.addMouseListener(new MouseAdapter() {
					@Override
		            public void mouseClicked(MouseEvent e) {
						handleDrag(routerPanel);
						networkPanel.invalidate();
						networkPanel.revalidate();
						networkPanel.repaint();
					}
				});
				networkPanel.add(routerPanel);			
				networkPanel.revalidate();
				networkPanel.repaint();
//				System.out.println(router_name.getRootPane().getParent());
						
//						getLocationOnScreen());
//				System.out.println(frame.getComponentCount());  
			}    
		});
		return button;
	}
	
	public void handleDrag(final JPanel panel){ 
	    panel.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mousePressed(MouseEvent me) {
	             x = me.getX();
	             y = me.getY();
	        }
	    });
	    panel.addMouseMotionListener(new MouseMotionAdapter() {
	        @Override
	        public void mouseDragged(MouseEvent me) {
	            me.translatePoint(me.getComponent().getLocation().x-x, me.getComponent().getLocation().y-y);
	            panel.setLocation(me.getX(), me.getY());
	            
	        }
	    });
	}

//	Meant for GridBagLayout
//	public void addComponent(JPanel p,Component comp,,GridBagConstraints c) {
//		c.fill = GridBagConstraints.BOTH;
//		p.add(comp,c);
//	}
	
}
	

