package views.userViews;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controlador.BookingValidator;
import controlador.BookingsController;
import modelos.Booking;
import modelos.Room;
import modelos.User;
import modelos.states.ValidatorStates;

public class BookView {

	private final int k = 4;
	
	private Room room;
	private User user;
	
	private BookingsController bookingsController = new BookingsController();
	private BookingValidator bookingValidator = new BookingValidator();
	
	
	JFrame f = new JFrame("Register"); 
	JComboBox<String> roomTypeField;
	JTextField guestAmountField, arrivalDateField, departureDateField;
	
	
	public BookView(Room room, User user) {	
		this.room = room;
		this.user = user;
		
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
		ValidatorStates state = bookingValidator.validate(
				guestAmountField.getText(),
				arrivalDateField.getText(),
				departureDateField.getText());
		
		switch(state) {
			case emptyFields:
				JOptionPane.showMessageDialog(f, "Tiene campos vacios", "Error", JOptionPane.CLOSED_OPTION);
				return;
			case wrongFormat:
				JOptionPane.showMessageDialog(f, "Tiene campos con valores no válidos", "Error", JOptionPane.CLOSED_OPTION);
				return;
			case verified:
				int guestAmountNum = Integer.parseInt(guestAmountField.getText());
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
				LocalDate arrivalDate = LocalDate.parse(arrivalDateField.getText(), formatter);
				LocalDate departureDate = LocalDate.parse(departureDateField.getText(), formatter);
				bookingsController.addElement(new Booking(guestAmountNum, arrivalDate, departureDate, user, room));
				
				JOptionPane.showMessageDialog(f, "Reserva creada correctamente", "Éxito", JOptionPane.CLOSED_OPTION);
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

		guestAmountField = new JTextField();
		guestAmountField.setBounds(200, 140-(10*k), 200, 20);
		f.add(guestAmountField);

		arrivalDateField = new JTextField();
		arrivalDateField.setBounds(200, 190-(10*k), 200, 20);
		f.add(arrivalDateField);

		departureDateField = new JTextField();
		departureDateField.setBounds(200, 240-(10*k), 200, 20);
		f.add(departureDateField);

	}
	
	private void addLabels() {
		JLabel guestAmountLabel, arrivalDateLabel, departureDateLabel;
		
		guestAmountLabel = new JLabel("Cantidad huespedes");
		guestAmountLabel.setBounds(200, 120-(10*k), 150, 20);
		
		arrivalDateLabel = new JLabel("Fecha de llegada");
		arrivalDateLabel.setBounds(200, 170-(10*k), 150, 20);
		
		departureDateLabel = new JLabel("Fecha de salida");
		departureDateLabel.setBounds(200, 220-(10*k), 150, 20);

	    f.add(guestAmountLabel);
	    f.add(arrivalDateLabel);
	    f.add(departureDateLabel);
	}
}
