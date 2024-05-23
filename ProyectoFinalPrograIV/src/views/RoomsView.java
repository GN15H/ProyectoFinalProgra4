package views;

import java.awt.*;
import java.util.List;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controlador.RoomsController;
import modelos.Room;
import modelos.User;
import views.widgets.RoomCellRenderer;


public class RoomsView {
	User user;
	RoomsController roomsController = new RoomsController();
	
	public RoomsView(User user) {
		this.user = user;
		
		JFrame frame = new JFrame("Rooms View");
        frame.setSize(600, 400);

        // Create a list of Room objects
        List<Room> roomList = roomsController.getAll();

        // Create the JList with a DefaultListModel
        DefaultListModel<Room> listModel = new DefaultListModel<>();
        for (Room room : roomList) {
            listModel.addElement(room);
        }

        JList<Room> jList = new JList<>(listModel);
        jList.setCellRenderer(new RoomCellRenderer());
        jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // Add ListSelectionListener to handle selection changes
        jList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    Room selectedRoom = jList.getSelectedValue();
                    if (selectedRoom != null) {
                    	RoomDetailsView roomDetailsView = new RoomDetailsView(selectedRoom, user);
                        System.out.println("Selected Room: " + selectedRoom.getId() + ", " +
                                           "Capacity: " + selectedRoom.getCapacity() + ", " +
                                           "Price: $" + selectedRoom.getPrice() + ", " +
                                           "Comfort: " + selectedRoom.getComfort() + ", " +
                                           "Room Type: " + selectedRoom.getRoomType());
                    }
                }
            }
        });
        
        // Wrap the JList in a JScrollPane
        JScrollPane scrollPane = new JScrollPane(jList);

        // Set the preferred size of the scroll pane
        scrollPane.setPreferredSize(new Dimension(580, 350));

        // Add the scroll pane to the frame
        frame.setLayout(new BorderLayout());
        frame.add(scrollPane, BorderLayout.CENTER);

        // Make the frame visible
        frame.setResizable(false);
        frame.setVisible(true);
	}
}