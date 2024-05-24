package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import controlador.RoomsController;
import entities.UserType;
import modelos.Room;
import modelos.RoomType;
import modelos.User;
import views.adminViews.CreateRoom;
import views.userViews.BookView;

public class RoomDetailsView {
	private Room room;
	private User user;
	private JFrame f;
	private JLabel idRoomLabel, capacityLabel, priceLabel, comfortLabel, roomTypeLabel;
	private JButton bookButton, editButton, deleteButton, verifyButton;
	private RoomsController roomsController = new RoomsController();
	
	private void addLabels() {
		idRoomLabel = new JLabel("Habitación " + room.getId());
		idRoomLabel.setBounds(180,20,80,20);
		
		capacityLabel = new JLabel("Capacidad: " + room.getCapacity());
		capacityLabel.setBounds(180,60,80,20);
		
		priceLabel = new JLabel("Precio por noche: " + room.getPrice());
		priceLabel.setBounds(180,80,180,20);
		
		comfortLabel = new JLabel("Comodidades \n" + room.getComfort());
		comfortLabel.setBounds(180,100,180,80);
		
		roomTypeLabel = new JLabel("Tipo de habitación: " + (room.getRoomType() == RoomType.simple ? "Simple" : "Compuesta"));
		roomTypeLabel.setBounds(180,180,180,20);
		
		f.add(idRoomLabel);
		f.add(capacityLabel);
		f.add(priceLabel);
		f.add(comfortLabel);
		f.add(roomTypeLabel);
	}
	
	private void addButtons() {
		bookButton = new JButton("Reservar");
		bookButton.setBounds(200,200,100,20);
		bookButton.addActionListener(new ActionListener() {
        	@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent e) {
    			f.dispose();
    			BookView bookView = new BookView(room,user, Optional.empty());
        	}
        		
        });
		
		editButton = new JButton("Editar");
		editButton.setBounds(200,200,100,20);
		editButton.addActionListener(new ActionListener() {
        	@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent e) {
    			f.dispose();
    			CreateRoom createRoom = new CreateRoom(Optional.of(room));
        	}
        		
        });
		
		deleteButton = new JButton("Borrar");
		deleteButton.setBounds(200,230,100,20);
		deleteButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
    			f.dispose();
    			roomsController.deleteElement(room);
    			JOptionPane.showMessageDialog(f, "Habitación borrada", "Éxito", JOptionPane.CLOSED_OPTION);
        	}
        		
        });
		
		verifyButton = new JButton("Disponibilidad");
		verifyButton.setBounds(200,270,100,20);
		
		if(user.getUserType() == UserType.admin) {
			f.add(editButton);
			f.add(deleteButton);
			f.add(verifyButton);
		}else {
			f.add(bookButton);
		}
	}
	
	public RoomDetailsView(Room room, User user) {
		this.room = room;
		this.user = user;
		f = new JFrame("Habitación");
		
		addLabels();
		addButtons();
		
		f.setSize(500, 500);
		f.setTitle("Login");
		f.setResizable(false);
        f.setLayout(null);  
        f.setVisible(true); 
	}
	
	
}
