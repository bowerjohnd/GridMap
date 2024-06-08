package dev.ArkNLA.GridMap;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PaneEntity extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	ArrayList<EntityObject> arrayEntities = new ArrayList<EntityObject>();
	ArrayList<JButton> butGoto = new ArrayList<JButton>();
	ArrayList<JButton> butDelete = new ArrayList<JButton>();

	JLabel statusMsg = new JLabel("", SwingConstants.LEFT);
	JPanel paneGoto = new JPanel();

	PaneEntity() {

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		add(statusMsg);

		loadEntitiesFromFile();

		populateEntities();

		setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object source = e.getSource();

		for(int i = 0; i < butGoto.size(); i++) {
			if (source == butGoto.get(i)) {

				GridMapMain.centerViewportCoordX = arrayEntities.get(i).getCoordX();
				GridMapMain.centerViewportCoordY = arrayEntities.get(i).getCoordY();

				GridMapMain.paneMap.gotoCoord();

			}
		}

		for(int i = 0; i < butDelete.size(); i++) {
			if (source == butDelete.get(i)) {

				arrayEntities.remove(i);
				butGoto.remove(i);
				butDelete.remove(i);
				saveEntitiesToFile();

				populateEntities();

				revalidate();
			}
		}
	}

	public void addEntity(EntityObject ent) {
		arrayEntities.add(ent);
		saveEntitiesToFile();
		populateEntities();

		revalidate();
	}

	private void populateEntities() {

		removeAll();

		add(statusMsg);
		
		// TODO: Create expandable groups
		
		for(int i = 0; i < arrayEntities.size(); i++) {

			statusMsg.setText("Saved Positions");
			
			JPanel box = new JPanel();
			
			// all components width sum = 300
			
			JLabel jlGroup = new JLabel(arrayEntities.get(i).getGroup(), SwingConstants.LEFT);
			jlGroup.setPreferredSize(new Dimension(50,20));
			jlGroup.setAlignmentX(Component.LEFT_ALIGNMENT);

			JLabel jlName = new JLabel(arrayEntities.get(i).getName(), SwingConstants.LEFT);
			jlName.setPreferredSize(new Dimension(100,20));
			jlName.setAlignmentX(Component.LEFT_ALIGNMENT);

			JLabel jlCoord = new JLabel(arrayEntities.get(i).getCoordX() + ", "
					+ arrayEntities.get(i).getCoordY(), SwingConstants.LEFT);
			jlCoord.setPreferredSize(new Dimension(50,20));
			jlCoord.setAlignmentX(Component.LEFT_ALIGNMENT);

			butGoto.add(new JButton("Goto"));
			butGoto.get(i).setMargin(new Insets(1,1,1,1));
			butGoto.get(i).setPreferredSize(new Dimension(50,20));
			butGoto.get(i).addActionListener(this);

			butDelete.add(new JButton("Delete"));
			butDelete.get(i).setMargin(new Insets(1,1,1,1));
			butDelete.get(i).setPreferredSize(new Dimension(50,20));
			butDelete.get(i).addActionListener(this);

			box.add(jlGroup);
			box.add(jlName);
			box.add(jlCoord);
			box.add(butGoto.get(i));
			box.add(butDelete.get(i));

			add(box);
		}

		if (arrayEntities.size() > 0) {
			GridMapMain.centerViewportCoordX = arrayEntities.get(0).getCoordX();
			GridMapMain.centerViewportCoordY = arrayEntities.get(0).getCoordY();
		}
		
		revalidate();
	}

	@SuppressWarnings("unchecked")
	private void loadEntitiesFromFile() {

		File file = new File("GridMapEntities.dat");

		if (!file.exists()) {
			try {
				FileOutputStream fos = new FileOutputStream("GridMapEntities.dat");
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				arrayEntities.add(new EntityObject());
				oos.writeObject(arrayEntities);
				oos.close();
			} catch (IOException e) {
				System.out.println(e);

			} 
		} else {

			try {
				FileInputStream fis = new FileInputStream("GridMapEntities.dat");
				ObjectInputStream ois = new ObjectInputStream(fis);
				arrayEntities = (ArrayList<EntityObject>) ois.readObject();
				ois.close();

			} catch (FileNotFoundException fnf) {

				statusMsg.setText("File Not Found");

			} catch (EOFException eof) {

				statusMsg.setText("No Entities");

			} catch (Exception ex) {

				System.out.println(ex);
				statusMsg.setText("Unknown Error");

			}
		}
		
	}

	private void saveEntitiesToFile() {

		try {

			FileOutputStream fos = new FileOutputStream("GridMapEntities.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(arrayEntities);
			oos.close();
			statusMsg.setText("Save Success.");
		} catch (Exception ex) {
			statusMsg.setText("Save Error.");
		}
	}
}
