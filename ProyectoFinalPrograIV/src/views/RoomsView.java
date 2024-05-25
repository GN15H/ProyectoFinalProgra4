package views;

import java.awt.*;
import java.util.Comparator;
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
	
	public RoomsView(User user, List<Room> roomList) {
		this.user = user;
		
		JFrame frame = new JFrame("Rooms View");
        frame.setSize(600, 400);

        roomList.sort(Comparator.comparingDouble(Room::getId));
        
        DefaultListModel<Room> listModel = new DefaultListModel<>();
        for (Room room : roomList) {
            listModel.addElement(room);
        }

        JList<Room> jList = new JList<>(listModel);
        jList.setCellRenderer(new RoomCellRenderer());
        jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        jList.addListSelectionListener(new ListSelectionListener() {
            @SuppressWarnings("unused")
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
        
        JScrollPane scrollPane = new JScrollPane(jList);

        scrollPane.setPreferredSize(new Dimension(580, 350));

        frame.setLayout(new BorderLayout());
        frame.add(scrollPane, BorderLayout.CENTER);

        // Make the frame visible
        frame.setResizable(false);
        frame.setVisible(true);
	}
}