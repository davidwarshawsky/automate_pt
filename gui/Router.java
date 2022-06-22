package gui;

import java.awt.Dimension;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Router {
	public String hostname;
	
	public static void main(String[] args) {
		Router router1 = new Router();
	}
	
	public Router() {
		String hostname = null;
		Object messageType = JOptionPane.PLAIN_MESSAGE;
		while ((hostname == null) || (hostname.length() == 0)) {
			hostname = (String)JOptionPane.showInputDialog(
	                null,
	                "Enter Router Name: ",
	                "New Router Name",
	                (int) messageType);
			if ((hostname != null) && (hostname.length() > 0)) {
				this.hostname = hostname;
//				System.out.println("Router hostname is now: " + this.hostname);
//			    setLabel("Green eggs and... " + s + "!");
			}else {
//				System.out.println("Router hostname is null");
				messageType = JOptionPane.ERROR_MESSAGE;
			}
		}
	}
	
	public void setName(String hostname) {
		if (hostname != this.hostname) {
			this.hostname = hostname;
		}
	}

}
