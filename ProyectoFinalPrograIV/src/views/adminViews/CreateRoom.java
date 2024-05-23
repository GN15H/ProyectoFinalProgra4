package views.adminViews;

import java.awt.event.*;

import javax.swing.*;

import controlador.RoomValidator;
import controlador.RoomsController;
import modelos.Room;
import modelos.RoomType;
import modelos.states.ValidatorStates;


public class CreateRoom {
	private final int k = 4;
	
	private RoomValidator roomValidator = new RoomValidator();
	private RoomsController roomsController = new RoomsController();
	
	final String[] roomTypes = {RoomType.simple.getValue(),RoomType.multiple.getValue()}; //list of id types
	
	JFrame f = new JFrame("CreateRoom"); 
	JComboBox<String> roomTypeField;
	JTextField capacity, price, comfort;
	
	
	public CreateRoom() {	
		
	    addLabels();
	    addFields();
	    addButtons();	    
	    
	    f.setTitle("Crear Habitación");
	    f.setSize(600, 500);
	    f.setResizable(false);
	    f.setLayout(null);
	    f.setVisible(true);
    } 
		
	private void createRoomHandler() {
		ValidatorStates state = roomValidator.validate(roomTypeField.getItemAt(roomTypeField.getSelectedIndex()),
				capacity.getText(),
				price.getText(),
				comfort.getText());
		
		switch(state) {
			case emptyFields:
				JOptionPane.showMessageDialog(f, "Tiene campos vacios", "Error", JOptionPane.CLOSED_OPTION);
				return;
			case wrongFormat:
				JOptionPane.showMessageDialog(f, "Tiene campos con valores no válidos", "Error", JOptionPane.CLOSED_OPTION);
				return;
			case verified:
				int capacityNum = Integer.parseInt(capacity.getText());
				double priceNum = Double.parseDouble(price.getText());
				RoomType roomTypeEnum = roomTypeField.getItemAt(roomTypeField.getSelectedIndex()).equals(new String("Simple")) ? RoomType.simple : RoomType.multiple;
				roomsController.addElement(new Room(capacityNum, priceNum, comfort.getText(), roomTypeEnum));
				JOptionPane.showMessageDialog(f, "Habitación creada correctamente", "Éxito", JOptionPane.CLOSED_OPTION);
				f.dispose();
				return;
			default:
				break;
		}
	}
	
	private void addButtons() {
		JButton registerButton, loginButton;
		
		registerButton = new JButton("Crear");
		registerButton.setBounds(210,380-(10*k),180, 20);
		registerButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
    			createRoomHandler();
        	}
        		
        });
		
		loginButton = new JButton("Salir");
		loginButton.setBounds(210,430-(10*k),180, 20);
		loginButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		f.dispose();
        	}
        		
        });
		
		f.add(registerButton);
		f.add(loginButton);
	}
	
	private void addFields() {
		roomTypeField = new JComboBox<String>(roomTypes);  
		roomTypeField.setBounds(200, 90-(10*k), 200, 20);
		f.add(roomTypeField);

		capacity = new JTextField();
		capacity.setBounds(200, 140-(10*k), 200, 20);
		f.add(capacity);

		price = new JTextField();
		price.setBounds(200, 190-(10*k), 200, 20);
		f.add(price);

		comfort = new JTextField();
		comfort.setBounds(200, 240-(10*k), 200, 20);
		f.add(comfort);

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
