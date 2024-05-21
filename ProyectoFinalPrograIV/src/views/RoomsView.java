package views;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;



public class RoomsView {

	public RoomsView() {
		JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(400, 300);

        // Create a list of items
        List<String> itemList = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {  // Add 100 items for demonstration
            itemList.add("Item " + i);
        }

        // Create a DefaultListModel and add items to it
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (String item : itemList) {
            listModel.addElement(item);
        }

        // Create the JList with the DefaultListModel
        JList<String> jList = new JList<>(listModel);
        jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Wrap the JList in a JScrollPane
        JScrollPane scrollPane = new JScrollPane(jList);

        // Set the preferred size of the scroll pane
        scrollPane.setPreferredSize(new Dimension(350, 250));

        // Add the scroll pane to the frame
        f.add(scrollPane);
        
        f.setTitle("Habitaciones");
        f.setResizable(false);
        f.setSize(600,550);  
        f.setLayout(null);  
        f.setVisible(true);
 
	}
}
