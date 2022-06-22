package gui;

import java.awt.Component;
//import javax.swing.Box;
//import javax.swing.Icon;
//import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import image_mod.ChangeImageSize;

public class FrontPage extends JFrame {
	// ToDo:
	//1) New router button
	//1) Make router pop up in the center of the screen when clicked
	//2) Add event handler for router clicked on
	//2) add Interface when right click on 
	//3)
	public ArrayList<Router> routers;
	public JFrame frame;
	
	public FrontPage() {
		
		routers = new ArrayList<Router>();
		frame = new JFrame("Cisco Automate");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationByPlatform(true);
		frame.setSize(500, 300);
		
		Box box = Box.createVerticalBox();
		
		JButton newRouterButton = new JButton("New Router");
		newRouterButton.setSize(100,100);
		addNewRouterButtonListener(newRouterButton);
		
		
		
	}
	
	private void addNewRouterButtonListener(JButton button) {
		button.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				Router newRouter = new Router();
				routers .add(newRouter);
				System.out.println("Hostname is now: " + routers.get(0).hostname);		
				ImageIcon ii = ChangeImageSize.resize("gui/router.png", 200, 200);
				JButton iib = new JButton(ii);
				JTextField name = new JTextField(newRouter.hostname);
				iib.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						if (SwingUtilities.isRightMouseButton(e)) {
							PopupRouterMenu menu = new PopupRouterMenu();
					        menu.show(e.getComponent(), e.getX(), e.getY());
						}
					}			
				});
				frame.add(name);
				frame.add(iib);
				frame.revalidate();
				frame.repaint();
				System.out.println(frame.getComponentCount());  
			}    
		});
		
	}
	
	public static void getInfo(Component c) {
		System.out.println(c.getLocation()); //returns 0
		System.out.println(c.getX());
		System.out.println(c.getY());
		System.out.println(c.getLocationOnScreen());
	}
	
	public static void main(String[] args) {
		ArrayList<Router> routers = new ArrayList<Router>();
		JFrame frame = new JFrame("Cisco Automate");				
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationByPlatform(true);
		frame.setSize(500, 300);
		Box box = Box.createVerticalBox();
		JButton button1 = new JButton("New Router");
				
		button1.setSize(100,100);
		button1.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				Router newRouter = new Router();
				routers .add(newRouter);
				System.out.println("Hostname is now: " + routers.get(0).hostname);		
				ImageIcon ii = ChangeImageSize.resize("gui/router.png", 200, 200);
				JButton iib = new JButton(ii);
				JTextField name = new JTextField(newRouter.hostname);
				iib.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						if (SwingUtilities.isRightMouseButton(e)) {
							PopupRouterMenu menu = new PopupRouterMenu();
					        menu.show(e.getComponent(), e.getX(), e.getY());
						}
					}			
				});
				frame.add(name);
				frame.add(iib);
				frame.revalidate();
				frame.repaint();
				System.out.println(frame.getComponentCount());  
			}    
		});  
		box.add(button1);
		frame.add(box);				
		frame.setVisible(true);
		return;
		}
}
