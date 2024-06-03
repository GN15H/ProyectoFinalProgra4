package views.userViews;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controlador.RoomsController;
import controlador.Validator;
import controlador.RoomFilterValidator;

import modelos.Room;
import modelos.RoomFilter;
import modelos.RoomType;
import modelos.User;
import modelos.states.RoomFilterStates;
import views.RoomsView;

public class RoomFilterView {
	private final int k = 4;
	
	private User user;

	private RoomsController roomsController = new RoomsController();
	
	final String[] roomTypes = {RoomType.simple.getValue(),RoomType.multiple.getValue()}; //list of id types
	
	JFrame f = new JFrame("RoomFilter"); 
	JComboBox<String> roomTypeField;
	JTextField capacity, price, arrivalDate, departureDate;
	
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
		
	@SuppressWarnings("unused")
	private void filterRoomHandler() {
		List<String> elements = Arrays.asList(roomTypeField.getItemAt(roomTypeField.getSelectedIndex()),capacity.getText(),price.getText(),arrivalDate.getText(), departureDate.getText());
		List<Object> objElements = Arrays.asList(roomTypeField.getItemAt(roomTypeField.getSelectedIndex()),capacity.getText(),price.getText(),arrivalDate.getText(), departureDate.getText());
		Validator<RoomFilter, RoomFilterStates> roomFilterValidator = new RoomFilterValidator();
		
		RoomFilterStates state = roomFilterValidator.validate(elements);
		
		switch(state) {
			case emptyFields:
				JOptionPane.showMessageDialog(f, "Tiene campos vacios", "Error", JOptionPane.CLOSED_OPTION);
				return;
			case wrongFormat:
				JOptionPane.showMessageDialog(f, "Tiene campos con valores no válidos", "Error", JOptionPane.CLOSED_OPTION);
				return;
			case invalidDate:
				JOptionPane.showMessageDialog(f, "Tiene fechas no válidas", "Error", JOptionPane.CLOSED_OPTION);
				return;
			case verified:
				RoomFilter roomFilter = roomFilterValidator.parseObject(objElements); 
				List<Room> filteredRooms = roomsController.getFilteredRooms(roomFilter);
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
    			filterRoomHandler();
        	}
        });
		
		loginButton = new JButton("Sin filtro");
		loginButton.setBounds(210,430-(10*k),180, 20);
		loginButton.addActionListener(new ActionListener() {
        	@SuppressWarnings("unused")
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

		price = new JTextField();
		price.setBounds(200, 190-(10*k), 200, 20);

		arrivalDate = new JTextField();
		arrivalDate.setBounds(200, 240-(10*k), 200, 20);
		
		departureDate = new JTextField();
		departureDate.setBounds(200, 290-(10*k), 200, 20);
		
		f.add(roomTypeField);
		f.add(capacity);
		f.add(arrivalDate);
		f.add(departureDate);
		f.add(price);

	}
	
	private void addLabels() {
		JLabel roomTypeLabel, capacityLabel, priceLabel, arrivalDateLabel, departureDateLabel;
		
		roomTypeLabel = new JLabel("Tipo de habitación");
		roomTypeLabel.setBounds(200, 70-(10*k), 150, 20);
		
		capacityLabel = new JLabel("Capacidad");
		capacityLabel.setBounds(200, 120-(10*k), 150, 20);
		
		priceLabel = new JLabel("Precio");
		priceLabel.setBounds(200, 170-(10*k), 150, 20);
		
		arrivalDateLabel = new JLabel("Fecha de llegada");
		arrivalDateLabel.setBounds(200, 220-(10*k), 150, 20);
		
		departureDateLabel = new JLabel("Fecha de salida");
		departureDateLabel.setBounds(200, 270-(10*k), 150, 20);

		f.add(roomTypeLabel);
	    f.add(capacityLabel);
	    f.add(priceLabel);
	    f.add(departureDateLabel);
	}
}
