package dev.ArkNLA.GridMap;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PaneZoomControl extends JPanel implements ActionListener, ChangeListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JTextField slideValue = new JTextField(3);
	JSlider slideZoom = new JSlider(5,100,25);
	
	JButton butGoto = new JButton("Goto:");
	JTextField textX = new JTextField(5);
	JTextField textY = new JTextField(5);
	
	PaneZoomControl() {

		butGoto.addActionListener(this);
		
		slideValue.setEditable(false);
		slideValue.setText("25");
		
		slideZoom.setPaintTrack(true);
		slideZoom.setPaintTicks(true);
		slideZoom.setPaintLabels(true);
		slideZoom.setMajorTickSpacing(10);
		slideZoom.setMinorTickSpacing(10);
		slideZoom.addChangeListener(this);
		
		add(slideValue);
		add(slideZoom);
		add(butGoto);
		add(textX);
		add(textY);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		Object source = e.getSource();
		
		if (source == butGoto) {			
			try {
				GridMapMain.centerViewportCoordX = Integer.parseInt(textX.getText());
				GridMapMain.centerViewportCoordY = Integer.parseInt(textY.getText());
			} catch (Exception er) {
				textX.setText("0");
				textY.setText("1000");
			}
		}
		
		GridMapMain.paneMap.gotoCoord();
		GridMapMain.paneMap.repaint();
	}

	@Override
	public void stateChanged(ChangeEvent e) {

		slideValue.setText(String.valueOf(slideZoom.getValue()));
		GridMapMain.worldSize = 1000 * slideZoom.getValue();
		GridMapMain.paneMap.gotoCoord();
		GridMapMain.paneMap.repaint();
	}

}
