package dev.ArkNLA.GridMap;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PaneAdd extends JPanel implements ActionListener{

	private final String[] options = {"Player", "Treasure Titan", "Preston Titan"};
	private JComboBox<String> entitySelect = new JComboBox<>(options);
	
	JPanel paneNorth = new JPanel();
	JPanel paneCenter = new JPanel();
	JPanel panePlayer = new JPanel();
	JPanel paneTreasureTitan = new JPanel();
	JPanel panePrestonTitan = new JPanel();

	JTextField coordX, coordY;
	
	JTextField textPlayerName, textPlayerMight, textPlayerTownHallLevel;
	JLabel labelPlayerName = new JLabel("Player Name: "),
			labelPlayerMight = new JLabel("Might: "),
			labelPlayerTownHallLevel = new JLabel("TH Level: ");
	
	PaneAdd() {
		setLayout(new BorderLayout());
		
		panePlayer.setLayout(new GridLayout());
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
