package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
//import javax.swing.Box;
//import javax.swing.Icon;
//import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import image_mod.ChangeImageSize;

public class FrontPage extends JFrame{
	
//	Class Instance Variables 
	public ArrayList<Router> routers;
//	public ArrayList<JPanel> routerPanels;
	JPanel panel;
	JPanel optionsPanel;
	JPanel networkPanel;
	
	
//	Constructor
	public FrontPage() {
//		Set Frame name to "Cisco Automate"
		super("Cisco Automate");
		panel = new JPanel();
//		Set width: 500px height: 300px
		setSize(1000,600);
//		Initialize the panel
		optionsPanel = new JPanel();
		optionsPanel.setSize(1000,220);
		networkPanel = new JPanel();
		networkPanel.setSize(1000,440);
//		networkPanel.setLayout(new BoxLayout(networkPanel, BoxLayout.PAGE_AXIS));
//        networkPanel.setBorder(new EmptyBorder(28, 28, 28, 28));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		addWindowListener(new WindowAdapter(){
		     @Override
		    public void windowClosing(WindowEvent e){
		        FrontPage.this.dispose();
		    }
		});
		setLocationByPlatform(true);
		
//		Initialize routers array		
		routers = new ArrayList<Router>();
		
//		Make a button that creates a new router.
		JButton newRouterButton = new JButton("New Router");
		newRouterButton.setSize(100,100);
		newRouterButton = addNewRouterButtonListener(newRouterButton);
		
		
//		Make a button that exports the script of the network.
		JButton exportNetworkScriptButton = new JButton("Export Network Script");
		exportNetworkScriptButton.setSize(100,100);
		
//		Add  the buttons to a box which will be added to optionsPanel 
		optionsPanel.add(newRouterButton);
		optionsPanel.add(exportNetworkScriptButton);
//		optionsPanel.setSize(1000,200);
		
		panel.add(optionsPanel); //,BorderLayout.NORTH
		panel.add(networkPanel); //,BorderLayout.CENTER
		optionsPanel.setVisible(true);
		networkPanel.setVisible(true);
		panel.setVisible(true);
		add(panel);
		setVisible(true);
	}
	
	private JButton addNewRouterButtonListener(JButton button) {
//		Add an actionListener to the new Router button
		button.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
//				Create a new Router
				Router newRouter = new Router();
				routers.add(newRouter);
//				Create an editable Router name field (to be below the router image)
				JTextField name = new JTextField(newRouter.hostname);
//				Add the new router to ArrayList of routers
				routers.add(newRouter);
//				Create a 200x200 ImageIcon of a router
				ImageIcon ii = ChangeImageSize.resize("gui/router.png", 200, 200);
//				Create a JButton from the ImageIcon router.
				JButton iib = new JButton(ii);
				
				iib.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						if (SwingUtilities.isRightMouseButton(e)) {
							PopupRouterMenu menu = new PopupRouterMenu();
					        menu.show(e.getComponent(), e.getX(), e.getY());
						}
					}			
				});
//				routerPanel.add(iib); //,Component.CENTER_ALIGNMENT
				iib.setAlignmentX(Component.CENTER_ALIGNMENT);
//				Add the JTextField router name to the routerPanel.
//				routerPanel.add(name); // , BorderLayout.CENTER
				name.setAlignmentX(Component.CENTER_ALIGNMENT);
				networkPanel.add(iib);
				networkPanel.add(name);
				
				networkPanel.revalidate();
				networkPanel.repaint();
				System.out.println(name.getRootPane().getParent());
						
//						getLocationOnScreen());
//				System.out.println(frame.getComponentCount());  
			}    
		});
		return button;
	}
	
}

