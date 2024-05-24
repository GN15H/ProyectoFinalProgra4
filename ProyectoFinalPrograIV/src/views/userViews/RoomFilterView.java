package views.userViews;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controlador.IValidator;
import controlador.RoomValidator;
import controlador.RoomsController;
import main.ProyectoFinal;
import modelos.Room;
import modelos.RoomType;
import modelos.User;
import modelos.states.RoomCreationStates;
import views.RoomsView;

public class RoomFilterView {
private final int k = 4;
	
	private User user;

	private IValidator<Room, RoomCreationStates> roomValidator = new RoomValidator();
	private RoomsController roomsController = new RoomsController();
	
	final String[] roomTypes = {RoomType.simple.getValue(),RoomType.multiple.getValue()}; //list of id types
	
	JFrame f = new JFrame("RoomFilter"); 
	JComboBox<String> roomTypeField;
	JTextField capacity, price, comfort;
	
	
	public RoomFilterView(User user) {	
		this.user = user;
		
	    addLabels();
	    addFields();
	    addButtons();	    
	    
	    f.setTitle("Filtrar Habitaciones");
	    f.setSize(600, 500);
	    f.setResizable(false);
	    f.setLayout(null);
	    f.setVisible(true);
    } 
		
	private void createRoomHandler() {
		List<String> elements = Arrays.asList(roomTypeField.getItemAt(roomTypeField.getSelectedIndex()),capacity.getText(),price.getText(),comfort.getText());
		List<Object> objElements = Arrays.asList(roomTypeField.getItemAt(roomTypeField.getSelectedIndex()),capacity.getText(),price.getText(),comfort.getText());
		
		RoomCreationStates state = roomValidator.validate(elements);
		switch(state) {
			case emptyFields:
				JOptionPane.showMessageDialog(f, "Tiene campos vacios", "Error", JOptionPane.CLOSED_OPTION);
				return;
			case wrongFormat:
				JOptionPane.showMessageDialog(f, "Tiene campos con valores no válidos", "Error", JOptionPane.CLOSED_OPTION);
				return;
			case verified:
				Room room = roomValidator.parseObject(objElements);
				List<Room> filteredRooms = roomsController.getFilteredRooms(room);
				System.out.println("AVER PUES OMEBUTIFARRA");
				ProyectoFinal.printArray(filteredRooms);
				RoomsView roomsView = new RoomsView(user, filteredRooms);
				f.dispose();
				return;
			default:
				break;
		}
	}
	
	
	private void addButtons() {
		JButton registerButton, loginButton;
		
		registerButton = new JButton("Buscar");
		registerButton.setBounds(210,380-(10*k),180, 20);
		registerButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
    			createRoomHandler();
        	}
        		
        });
		
		loginButton = new JButton("Sin filtro");
		loginButton.setBounds(210,430-(10*k),180, 20);
		loginButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		RoomsView roomsView = new RoomsView(user, roomsController.getAll());
        		f.dispose();
        	}
        		
        });
		
		f.add(registerButton);
		f.add(loginButton);
	}
	
	private void addFields() {
		roomTypeField = new JComboBox<String>(roomTypes);  
		roomTypeField.setBounds(200, 90-(10*k), 200, 20);
		

		capacity = new JTextField();
		capacity.setBounds(200, 140-(10*k), 200, 20);
		capacity.setText(null);

		price = new JTextField();
		price.setBounds(200, 190-(10*k), 200, 20);

		comfort = new JTextField();
		comfort.setBounds(200, 240-(10*k), 200, 20);
		
		/*if(roomToUpdate.isPresent()) {
			roomTypeField.setSelectedItem(roomToUpdate.get().getRoomType().getValue());
			capacity.setText(String.valueOf(roomToUpdate.get().getCapacity()));
			price.setText(String.valueOf(roomToUpdate.get().getPrice()));
			comfort.setText(roomToUpdate.get().getComfort());
		}*/
		
		
		f.add(roomTypeField);
		f.add(capacity);
		f.add(comfort);
		f.add(price);

	}
	
	private void addLabels() {
		JLabel roomTypeLabel, capacityLabel, priceLabel, comfortLabel;
		
		roomTypeLabel = new JLabel("Tipo de habitación");
		roomTypeLabel.setBounds(200, 70-(10*k), 150, 20);
		
		capacityLabel = new JLabel("Capacidad");
		capacityLabel.setBounds(200, 120-(10*k), 150, 20);
		
		priceLabel = new JLabel("Precio");
		priceLabel.setBounds(200, 170-(10*k), 150, 20);
		
		comfortLabel = new JLabel("Comodidades");
		comfortLabel.setBounds(200, 220-(10*k), 150, 20);

		f.add(roomTypeLabel);
	    f.add(capacityLabel);
	    f.add(priceLabel);
	    f.add(comfortLabel);
	}
}
