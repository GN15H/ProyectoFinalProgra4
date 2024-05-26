package views.adminViews;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controlador.RoomsController;
import controlador.Validator;
import controlador.VerifyRoomValidator;
import modelos.Room;
import modelos.VerifyRoom;
import modelos.states.VerifyRoomStates;

public class VerifyRoomView {
private final int k = 4;
	
	private Room room;

	private RoomsController roomsController = new RoomsController();
	
	JFrame f = new JFrame("VerifyRoom"); 
	JTextField arrivalDateField, departureDateField;
	
	public VerifyRoomView(Room room) {
		this.room = room;
		
	    addLabels();
	    addFields();
	    addButtons();	    
	    
	    f.setTitle("Verificar Habitacion");
	    f.setSize(600, 500);
	    f.setResizable(false);
	    f.setLayout(null);
	    f.setVisible(true);
    } 
		
	private void createRoomHandler() {
		List<String> elements = Arrays.asList(arrivalDateField.getText(), departureDateField.getText());
		List<Object> objElements = Arrays.asList(arrivalDateField.getText(), departureDateField.getText());
		Validator<VerifyRoom, VerifyRoomStates> verifyRoomValidator = new VerifyRoomValidator();
		
		VerifyRoomStates state = verifyRoomValidator.validate(elements);
		
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
				VerifyRoom verifyRoom = verifyRoomValidator.parseObject(objElements); 
				boolean isAvailable = roomsController.isRoomAvailable(room, verifyRoom.getArrivalDate(), verifyRoom.getDepartureDate());
				JOptionPane.showMessageDialog(f, isAvailable ? "Habitación disponible" : "Habitación no disponible", "Aviso", JOptionPane.CLOSED_OPTION);
				return;
			default:
				break;
		}
	}
	
	
	private void addButtons() {
		JButton verifyButton, exitButton;
		
		verifyButton = new JButton("Verificar Disponibilidad");
		verifyButton.setBounds(210,380-(10*k),180, 20);
		verifyButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
    			createRoomHandler();
        	}
        });
		
		exitButton = new JButton("Salir");
		exitButton.setBounds(210,430-(10*k),180, 20);
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
        		f.dispose();
        	}	
        });
		
		f.add(verifyButton);
		f.add(exitButton);
	}
	
	private void addFields() {
		arrivalDateField = new JTextField();  
		arrivalDateField.setBounds(200, 90-(10*k), 200, 20);
		
		departureDateField = new JTextField();
		departureDateField.setBounds(200, 140-(10*k), 200, 20);

		f.add(arrivalDateField);
		f.add(departureDateField);
	}
	
	private void addLabels() {
		JLabel arrivalDateLabel, departureDateLabel;
		
		arrivalDateLabel = new JLabel("Fecha de llegada");
		arrivalDateLabel.setBounds(200, 70-(10*k), 150, 20);
		
		departureDateLabel = new JLabel("Fecha de salida");
		departureDateLabel.setBounds(200, 120-(10*k), 150, 20);
		
		f.add(arrivalDateLabel);
	    f.add(departureDateLabel);
	}
}
