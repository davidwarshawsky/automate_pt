package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class GridBagLayoutTesting {
	public static void main(String[] args) {
        
		final JFrame frame = new JFrame("Testing GridBagLayout");
		frame.setFont(new Font("SansSerif", Font.PLAIN, 14));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.addWindowListener(new WindowAdapter(){
		     @Override
		    public void windowClosing(WindowEvent e){
		        frame.dispose();
		    }
		});
		frame.setLocationByPlatform(true);
		
		GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        Border blackline = BorderFactory.createLineBorder(Color.black);
        
		
		
		JPanel panel = new JPanel();
		panel.setLayout(gridbag);
		
		JPanel topPanel = new JPanel();
		topPanel.setBorder(blackline);
		JPanel bottomPanel = new JPanel();
		bottomPanel.setBorder(blackline);
		        
		
		panel.add(topPanel);
        panel.add(bottomPanel);
        panel.setSize(1000,600);
        c.weightx = 1;
        c.weighty = 1;
        c.gridx = 0;
        c.gridy = 0;
        gridbag.setConstraints(topPanel, c);
        
        c.gridx = 1;
        gridbag.setConstraints(bottomPanel, c);
        frame.add(panel);
		
        frame.setSize(1000,600);
        frame.setVisible(true);
		
		
	}

}
