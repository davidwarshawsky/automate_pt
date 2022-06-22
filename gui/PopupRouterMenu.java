package gui;

import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;
import java.awt.event.*;

public class PopupRouterMenu extends JPopupMenu {
	private static final long serialVersionUID = 1L;
	
	JMenu interfacesMenu;
	
    public PopupRouterMenu() {
        interfacesMenu = new JMenu("Add Interface");
        ArrayList<String> interfaces = new ArrayList<String>(Arrays.asList("RIP1","RIP2","OSPF1","OSPF2"));
        for(String router_interface: interfaces) {
        	interfacesMenu.add(new JMenuItem(router_interface));
        }
        add(interfacesMenu);
    }
        
        
    
    @SuppressWarnings("unused")
	private void doPop(MouseEvent e) {
        PopupRouterMenu menu = new PopupRouterMenu();
        menu.show(e.getComponent(), e.getX(), e.getY());
    }

}
