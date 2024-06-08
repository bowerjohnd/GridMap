package dev.ArkNLA.GridMap;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PaneMap extends JPanel implements MouseListener, MouseMotionListener{

	private static final long serialVersionUID = 1L;
	
	// Hard Coded entities
	ArrayList<Point> pt1 = new ArrayList<Point>(),
					pt2 = new ArrayList<Point>(),
					pt3 = new ArrayList<Point>(),
					pt4 = new ArrayList<Point>(),
					pt5 = new ArrayList<Point>(),
					pt6 = new ArrayList<Point>(),
					
					tt1 = new ArrayList<Point>(),
	
					ot1 = new ArrayList<Point>();
	
    int mouseStartX = 0;
    int mouseStartY = 0;
    int cursorPositionX = 0;
    int cursorPositionY = 0;
    
    int worldSize;
    int gridSize;
    int gridsPerWorld;
    
    Rectangle viewport = this.getVisibleRect();

	PaneMap() {
		worldSize = GridMapMain.worldSize;
		gridsPerWorld = GridMapMain.gridsPerWorld;
		gridSize = (int)(worldSize/gridsPerWorld);
		
		hardCodeEntities();
		
		addMouseListener(this);
		addMouseMotionListener(this);
		
		setVisible(true);

	}
	
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(worldSize, worldSize);
    }



    @Override
    protected void paintComponent(Graphics g) {
    	super.paintComponent(g);
	    
    	//Point vpp = new Point(GridMapMain.centerViewportCoordX * gridSize, GridMapMain.centerViewportCoordY * gridSize);
	    
		//scrollRectToVisible(new Rectangle(vpp, this.getVisibleRect().getSize()));
		
    	worldSize = GridMapMain.worldSize;
		gridsPerWorld = GridMapMain.gridsPerWorld;
		gridSize = (int)(worldSize/gridsPerWorld);

		viewport = this.getVisibleRect();

    	Graphics2D g2 = (Graphics2D) g;
    			        


        
        // Draw gridlines
        
        for (int i = 0; i < worldSize; i++) {
        	if (i%gridSize == 0) {
        		g2.drawLine(0, i, worldSize, i);
        		g2.drawLine(i,  0,  i, worldSize);
        	}
        }        

        // Draw circle on center of viewport and center of map
        
    	g2.fillOval(viewport.x + (viewport.width/2), viewport.y + (viewport.height/2), 7, 7);
    	
    	g2.drawOval(worldSize/2 - 10, worldSize/2 - 10, 20, 20);
    	
    	// Draw entities on map
    	if (GridMapMain.paneEntities.arrayEntities.size() > 0) {
	    	for(EntityObject ent : GridMapMain.paneEntities.arrayEntities) {
	    		
	    		int ptx = ent.getCoordX();
	    		int pty = ent.getCoordY();
	    		int x = ptx * gridSize;
	    		int y = worldSize - (pty * gridSize);
	
	    		if (ent.getGroup().toLowerCase().equals("titan")) {
	    	    	g2.setColor(Color.orange);
	        		g2.fillRect(x,y,gridSize,gridSize);
	    		}
	    		else if (ent.getGroup().toLowerCase().equals("player")) {
	    	    	g2.setColor(Color.black);
	        		g2.fillRect(x,y,gridSize,gridSize);
	    		}
	    		else {
	    	    	g2.setColor(Color.BLUE);
	        		g2.fillOval(x,y,gridSize,gridSize);
	    		}
	    	}
    	}

    	
    	// draw hard coded positions, bottom left is 0,0
    	/*
    	g2.setColor(new Color(255,0,200,200));
    	for (int i = 0; i < pt1.size(); i++) {
    		int ptx = pt1.get(i).x;
    		int pty = pt1.get(i).y;
    		int x = ptx * gridSize;
    		int y = worldSize - (pty * gridSize);
			g2.fillRect(x,y,gridSize,gridSize);

    		if (i ==0) {
    	    	g2.setColor(new Color(0,0,0,150));
        		g2.fillOval(x,y,gridSize,gridSize);
            	g2.setColor(new Color(255,0,200,200));
    		}
    	}
    	g2.setColor(new Color(200,255,80,200));
    	for (int i = 0; i < pt2.size(); i++) {
    		int ptx = pt2.get(i).x;
    		int pty = pt2.get(i).y;
    		int x = ptx * gridSize;
    		int y = worldSize - (pty * gridSize);
			g2.fillRect(x,y,gridSize,gridSize);

			if (i ==0) {
    	    	g2.setColor(new Color(0,0,0,150));
        		g2.fillOval(x,y,gridSize,gridSize);
            	g2.setColor(new Color(200,255,80,200));
    		}
    	}
    	g2.setColor(new Color(0,0,255,100));
    	for (int i = 0; i < pt3.size(); i++) {
    		int ptx = pt3.get(i).x;
    		int pty = pt3.get(i).y;
    		int x = ptx * gridSize;
    		int y = worldSize - (pty * gridSize);
			g2.fillRect(x,y,gridSize,gridSize);

			if (i ==0) {
    	    	g2.setColor(new Color(0,0,0,150));
        		g2.fillOval(x,y,gridSize,gridSize);
            	g2.setColor(new Color(0,0,255,100));
    		}
    	}
    	g2.setColor(new Color(255,100,100,200));
    	for (int i = 0; i < pt4.size(); i++) {
    		int ptx = pt4.get(i).x;
    		int pty = pt4.get(i).y;
    		int x = ptx * gridSize;
    		int y = worldSize - (pty * gridSize);
			g2.fillRect(x,y,gridSize,gridSize);

			if (i ==0) {
    	    	g2.setColor(new Color(0,0,0,150));
        		g2.fillOval(x,y,gridSize,gridSize);
            	g2.setColor(new Color(255,100,100,200));
    		}
    	}
    	g2.setColor(new Color(100,255,100,200));
    	for (int i = 0; i < pt5.size(); i++) {
    		int ptx = pt5.get(i).x;
    		int pty = pt5.get(i).y;
    		int x = ptx * gridSize;
    		int y = worldSize - (pty * gridSize);
			g2.fillRect(x,y,gridSize,gridSize);

			if (i ==0) {
    	    	g2.setColor(new Color(0,0,0,150));
        		g2.fillOval(x,y,gridSize,gridSize);
            	g2.setColor(new Color(100,255,100,200));
    		}
    	}
    	g2.setColor(new Color(0,100,255,200));
    	for (int i = 0; i < pt6.size(); i++) {
    		int ptx = pt6.get(i).x;
    		int pty = pt6.get(i).y;
    		int x = ptx * gridSize;
    		int y = worldSize - (pty * gridSize);
			g2.fillRect(x,y,gridSize,gridSize);

			if (i ==0) {
    	    	g2.setColor(new Color(0,0,0,150));
        		g2.fillOval(x,y,gridSize,gridSize);
            	g2.setColor(new Color(0,100,255,200));
    		}
    	}
    	g2.setColor(new Color(100,100,100,200));
    	for (int i = 0; i < tt1.size(); i++) {
    		int ptx = tt1.get(i).x;
    		int pty = tt1.get(i).y;
    		int x = ptx * gridSize;
    		int y = worldSize - (pty * gridSize);
			g2.fillRect(x,y,gridSize,gridSize);

			if (i ==0) {
    	    	g2.setColor(new Color(0,0,0,150));
        		g2.fillOval(x,y,gridSize,gridSize);
            	g2.setColor(new Color(100,100,100,200));
    		}
    	}
    	g2.setColor(new Color(150,100,220,200));
    	for (int i = 0; i < ot1.size(); i++) {
    		int ptx = ot1.get(i).x;
    		int pty = ot1.get(i).y;
    		int x = ptx * gridSize;
    		int y = worldSize - (pty * gridSize);
			g2.fillRect(x,y,gridSize,gridSize);

			if (i ==0) {
    	    	g2.setColor(new Color(0,0,0,150));
        		g2.fillOval(x,y,gridSize,gridSize);
            	g2.setColor(new Color(150,100,220,200));
    		}
    	}
    	*/
    	
    	// Draw cursor and coord info on map
    	
    	g2.setColor(Color.white);    	
    	g2.fillRect(100, 100, 200, 50);
    	g2.setColor(Color.black);
    	g2.drawString("Right Click to save a position", 105, 120);

    	g2.setColor(Color.white);
    	g2.fillRect(0, 0, (int)viewport.getX() + 110, (int)viewport.getY() + 35);

    	g2.setColor(Color.black);
    	g2.drawString(String.valueOf("Cursor: " + String.valueOf(cursorPositionX)) 
        		+ ", " + String.valueOf(cursorPositionY), 
        		(int)viewport.getX() + 5, 
        		(int)viewport.getY() + 10);
        
        g2.drawString("VP x,y: " + String.valueOf(viewport.x) + ", " 
        		+ String.valueOf(viewport.y), 
        		(int)viewport.getX() + 5, 
        		(int)viewport.getY() + 20);
        
        g2.drawString("Dot: " + String.valueOf(viewport.x + (viewport.width/2)) + ", " 
        		+ String.valueOf(viewport.y + (viewport.height/2)), 
        		(int)viewport.getX() + 5, 
        		(int)viewport.getY() + 30);


        // center coord in bottom of viewport
        
    	String coordString =
        		String.valueOf(
        				(viewport.x + (viewport.width/2)) / gridSize  
        				) + ", " 
        		+ String.valueOf(
        				(worldSize / gridSize) -
        				((viewport.y + (viewport.height/2)) / gridSize)
        				);

    	g2.setColor(Color.white);
    	g2.fillRect((int)viewport.getX() + ((int)viewport.width/2) - 60, 
    			((int)viewport.getY() + (int)viewport.height - 25), 
    			80, 
    			20);
    	g2.setColor(Color.black);
    	g2.setFont(new Font("default", Font.BOLD, 16));
        g2.drawString(coordString,
        		(int)viewport.getX() + ((int)viewport.width/2) - 50, 
        		(int)viewport.getY() + (int)viewport.height - 10);
        
    }


    public void gotoCoord() {

		int x = GridMapMain.centerViewportCoordX * gridSize;
		int y = worldSize - (GridMapMain.centerViewportCoordY  * gridSize);
		
		scrollRectToVisible(new Rectangle(x - (viewport.width/2),
				y - (viewport.height/2), viewport.width, viewport.height));

    }

	@Override
	public void mouseDragged(MouseEvent e) {
	    
	    Point vpp = new Point(this.getVisibleRect().x, this.getVisibleRect().y);
	    
		vpp.translate(mouseStartX-e.getX(), mouseStartY-e.getY());
		scrollRectToVisible(new Rectangle(vpp, this.getVisibleRect().getSize()));

        GridMapMain.centerViewportCoordX = (int)((viewport.x + (viewport.width/2)) / gridSize);
       	GridMapMain.centerViewportCoordY = (int)(worldSize / gridSize) - ((viewport.y + (viewport.height/2)) / gridSize);
}



	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
		cursorPositionX = e.getX();
		cursorPositionY = e.getY();
		repaint();
	}



	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

		if (e.getButton() == MouseEvent.BUTTON1) {
			
			int x = 307 * gridSize;
			int y = worldSize - (395 * gridSize);
			
			//scrollRectToVisible(new Rectangle(x - (viewport.width/2),
			//		y - (viewport.height/2), viewport.width, viewport.height));
			
	        GridMapMain.centerViewportCoordX = (int)((viewport.x + (viewport.width/2)) / gridSize);
	       	GridMapMain.centerViewportCoordY = (int)(worldSize / gridSize) - ((viewport.y + (viewport.height/2)) / gridSize);
		}
		
		if (e.getButton() == MouseEvent.BUTTON3) {
	        
			int tempX = (int)((viewport.x + (viewport.width/2)) / gridSize);
	       	int tempY = (int)(worldSize / gridSize) - ((viewport.y + (viewport.height/2)) / gridSize);
	       	
	        JTextField field1 = new JTextField("Name");
	        JTextField field2 = new JTextField(String.valueOf(tempX));
	        JTextField field3 = new JTextField(String.valueOf(tempY));
	        JTextField field4 = new JTextField("0");
	        
	        JPanel popup = new JPanel(new GridLayout(0, 1));
	        popup.add(new JLabel("Name:"));
	        popup.add(field1);
	        popup.add(new JLabel("X:"));
	        popup.add(field2);
	        popup.add(new JLabel("Y:"));
	        popup.add(field3);
	        popup.add(new JLabel("Group:"));
	        popup.add(field4);
	        int result = JOptionPane.showConfirmDialog(null, popup, "Save Position",
	            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
	        if (result == JOptionPane.OK_OPTION) {
	        	
	        	try {
	        		String name = field1.getText();
	        		int x = Integer.parseInt(field2.getText());
	        		int y = Integer.parseInt(field3.getText());
	        		String group = field4.getText();

	        		GridMapMain.paneEntities.addEntity(new EntityObject(name, x, y, group));
		        	GridMapMain.paneEntities.revalidate();
	        		
	        	} catch (Exception er) {
	        		// add error message somewhere for correct inputs
	        	}
	        	
	        } else {
	            //System.out.println("Cancelled");
	        }
		}
	}



	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

		mouseStartX = e.getX();
	    mouseStartY = e.getY();
		
	}



	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	// Hard coded entities
	private void hardCodeEntities() {
		
		pt1.add(new Point(323,380)); //titan
		pt1.add(new Point(325,380));
		pt1.add(new Point(328,385));
		pt1.add(new Point(328,376));
		pt1.add(new Point(328,374));

		pt2.add(new Point(306,396)); //titan
		pt2.add(new Point(309,402));
		pt2.add(new Point(305,300));
		pt2.add(new Point(302,392));
		pt2.add(new Point(314,385));
		pt2.add(new Point(307,385));
		
		pt3.add(new Point(321,387)); //titan
		pt3.add(new Point(321,388));
		pt3.add(new Point(324,386));
		pt3.add(new Point(324,392));
		//pt3.add(new Point(327,381));
		pt3.add(new Point(328,385));
		
		pt4.add(new Point(333,419)); //titan
		pt4.add(new Point(331,415));
		pt4.add(new Point(327,424));
		pt4.add(new Point(329,423));
		pt4.add(new Point(333,425));
		pt4.add(new Point(335,422));
		
		pt5.add(new Point(312,383)); //titan
		pt5.add(new Point(307,389));
		pt5.add(new Point(310,378));
		pt5.add(new Point(317,377));
		pt5.add(new Point(314,383));
		
		pt6.add(new Point(335,424)); //titan
		pt6.add(new Point(332,429));
		pt6.add(new Point(329,423));
		pt6.add(new Point(335,426));
		pt6.add(new Point(338,424));
		pt6.add(new Point(336,422));
		
		tt1.add(new Point(282,380)); //titan
		tt1.add(new Point(291,390)); //brew
		tt1.add(new Point(292,387)); //brew
		tt1.add(new Point(291,385)); //brew
		tt1.add(new Point(275,378)); //brew
		tt1.add(new Point(285,382)); //brew
		tt1.add(new Point(280,373)); //brew
		tt1.add(new Point(287,372)); //brew
		tt1.add(new Point(277,370)); //brew
		tt1.add(new Point(274,383)); //key
		tt1.add(new Point(288,374)); //key
		tt1.add(new Point(285,377)); //key
		tt1.add(new Point(284,377)); //key
		tt1.add(new Point(274,372)); //key
		tt1.add(new Point(281,370)); //key
		tt1.add(new Point(290,376)); //chow
		tt1.add(new Point(289,375)); //chow
		tt1.add(new Point(280,377)); //chow
		tt1.add(new Point(281,374)); //chow
		
		ot1.add(new Point(331,410)); //titan

		ot1.add(new Point(329,407)); //brew
		ot1.add(new Point(334,413)); //brew
		ot1.add(new Point(335,412)); //brew
		ot1.add(new Point(331,413)); //brew		
		ot1.add(new Point(332,412)); //brew		
		ot1.add(new Point(333,411)); //brew
		
		ot1.add(new Point(330,411)); //chow
		ot1.add(new Point(328,410)); //chow		
		ot1.add(new Point(333,410)); //chow
		ot1.add(new Point(332,409)); //chow
		ot1.add(new Point(330,407)); //chow
		ot1.add(new Point(329,408)); //chow
		
		ot1.add(new Point(332,414)); //key
		ot1.add(new Point(335,409)); //key		
		ot1.add(new Point(335,414)); //key
		ot1.add(new Point(334,412)); //key
		ot1.add(new Point(333,409)); //key
		ot1.add(new Point(331,411)); //key
		ot1.add(new Point(329,409)); //key		
		
		ot1.add(new Point(329,412)); //jewel
		ot1.add(new Point(327,410)); //jewel
		ot1.add(new Point(329,406)); //jewel
		ot1.add(new Point(331,409)); //jewel
		ot1.add(new Point(329,413)); //jewel

	}
}

