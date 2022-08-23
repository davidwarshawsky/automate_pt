package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import image_mod.ChangeImageSize;


public class MyFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	int x = 0;
	int y = 0;
    JButton newRouterButton;
    JButton exportNetworkScriptButton;
    List<JPanel> mypanels = new ArrayList<JPanel>();
    public MyFrame() {
    	super("Cisco Automate");
        newRouterButton = new JButton("New Router");
        newRouterButton.setBounds(10, 10, 100, 50);
        exportNetworkScriptButton = new JButton("Export Network Script");
        exportNetworkScriptButton.setBounds(10,80,200,50);
        setSize(new Dimension(1000, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        add(newRouterButton);
        add(exportNetworkScriptButton);
        setVisible(true);
        initialize();
    }


    public void initialize() {

        newRouterButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // ImageIcon ii = ChangeImageSize.resize("gui/router.png", 200, 200);
//				//Create a JButton from the ImageIcon router.
				//JButton router = new JButton(ii);
            	
                JPanel panel = new JPanel();
                panel.setBounds(150,150,150,150);
                ImageIcon ii = new ImageIcon("gui/router.png");
//                ImageIcon ii = ChangeImageSize.resize("gui/router.png", 149, 149);
                JButton router = new JButton(ii);
                Border blackline = BorderFactory.createLineBorder(Color.black);
                panel.setBorder(blackline);
                panel.add(router);
                mypanels.add(panel);
                add(panel);
                panel.invalidate();
                panel.revalidate();
                panel.repaint();
                repaint();
                handleDrag(router);
                handleDrag(panel);
            }
        });

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
    
    public void handleDrag(final JButton button){
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                 x = me.getX();
                 y = me.getY();
            }
        });
        button.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent me) {
                me.translatePoint(me.getComponent().getParent().getLocation().x-x, me.getComponent().getParent().getLocation().y-y);
                button.getParent().setLocation(me.getX(), me.getY());
            }
        });
    }

    public static void main(String args[]) {

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                new MyFrame();
            }
        });

    }
}