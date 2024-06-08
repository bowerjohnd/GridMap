package dev.ArkNLA.GridMap;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

public class GridMapMain {
	
	public static int worldSize = 10000;
	public static int gridsPerWorld = 1000;
	public static PaneMap paneMap = new PaneMap();
	public static PaneEntity paneEntities = new PaneEntity();
	public static int centerViewportCoordX = 282;
	public static int centerViewportCoordY = 380;
	
	JFrame frame = new JFrame();
	JTabbedPane tabbedPane = new JTabbedPane();
	PaneZoomControl paneZoom = new PaneZoomControl();
	PaneAdd paneAdd = new PaneAdd();
	
    JScrollPane scrollpaneMap, scrollpaneEntities;

	private final int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
	private final int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
	private int frameX = screenWidth/5;
	private int frameY = screenHeight/5;
	private int frameW = screenWidth/5*3;
	private int frameH = screenHeight/5*3;
	
	
	GridMapMain() {
		createGui();

		scrollpaneMap = new JScrollPane(paneMap);
		tabbedPane.add("Map", scrollpaneMap);
		
		scrollpaneEntities = new JScrollPane(paneEntities);
		
		frame.setLayout(new BorderLayout());
		
		frame.add(paneZoom, BorderLayout.SOUTH);
		frame.add(tabbedPane, BorderLayout.CENTER);
		frame.add(scrollpaneEntities, BorderLayout.EAST);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		GridMapMain start = new GridMapMain();
		
		// center viewport on first saved entity
		
		centerViewportCoordX = paneEntities.arrayEntities.get(0).getCoordX() +28; // Adjusting for unknown offset
		centerViewportCoordY = paneEntities.arrayEntities.get(0).getCoordY() -19; // Adjusting for unknown offset
		paneMap.gotoCoord();

	}
	
	public void createGui() {
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(frameX, frameY, frameW, frameH);
		frame.setTitle("GridMap by ArkNLA");
		frame.setMinimumSize(new Dimension(screenWidth/5*2, screenHeight/5*2));
		frame.setResizable(true);			
	}

}
