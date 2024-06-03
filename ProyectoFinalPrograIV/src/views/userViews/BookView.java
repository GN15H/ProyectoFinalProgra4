package views.userViews;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
import modelos.states.BookingStates;

public class BookView {

	private final int k = 4;
	
	private Room room;
	private User user;
	private Optional<Booking> booking;
	
	private BookingsController bookingsController = new BookingsController();
	
	JFrame f = new JFrame("Register"); 
	JComboBox<String> roomTypeField;
	JTextField guestAmountField, arrivalDateField, departureDateField;
	
	public BookView(Room room, User user, Optional<Booking> booking) {	
		this.room = room;
		this.user = user;
		this.booking = booking;
		
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
		List<String> elements = Arrays.asList(guestAmountField.getText(), arrivalDateField.getText(), departureDateField.getText(), String.valueOf(user.getId()),String.valueOf(room.getId()));
		List<Object> objElements = Arrays.asList(guestAmountField.getText(), arrivalDateField.getText(), departureDateField.getText(), user, room);
		BookingValidator bookingValidator = new BookingValidator();

		BookingStates state;
		
		if(booking.isEmpty()) {
			state = bookingValidator.validate(elements);
		}else {
			state = bookingValidator.validateEdit(elements, user);
		}
		
		switch(state) {
			case emptyFields:
				JOptionPane.showMessageDialog(f, "Tiene campos vacios", "Error", JOptionPane.CLOSED_OPTION);
				return;
			case wrongFormat:
				JOptionPane.showMessageDialog(f, "Tiene campos con valores no válidos", "Error", JOptionPane.CLOSED_OPTION);
				return;
			case invalidDate:
				JOptionPane.showMessageDialog(f, BookingStates.invalidDate.getValue(), "Error", JOptionPane.CLOSED_OPTION);
				return;
			case invalidGuestAmount:
				JOptionPane.showMessageDialog(f, BookingStates.invalidGuestAmount.getValue(),"Error", JOptionPane.CLOSED_OPTION);
				return;
			case notAvailable:
				JOptionPane.showMessageDialog(f, "Habitación no disponible para esta fecha", "Error", JOptionPane.CLOSED_OPTION);
				return;
			case verified:
				Booking bookingObj = bookingValidator.parseObject(objElements);
				if(booking.isEmpty()) {
					bookingsController.addElement(bookingObj);
					JOptionPane.showMessageDialog(f, "Reserva creada correctamente", "Éxito", JOptionPane.CLOSED_OPTION);
					
				}else {
					bookingsController.updateElement(booking.get(),bookingObj);
					JOptionPane.showMessageDialog(f, "Reserva actualizada correctamente", "Éxito", JOptionPane.CLOSED_OPTION);
					
				}
				f.dispose();
				return;
			default:
				break;
		}
	}
	
	private void addButtons() {
		JButton registerButton, loginButton;
		
		registerButton = new JButton(booking.isPresent() ? "Editar" : "Crear");
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
		
		if(booking.isPresent()) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

			guestAmountField.setText(String.valueOf(booking.get().getGuestAmount()));
			arrivalDateField.setText(String.valueOf(booking.get().getArrivalDate().format(formatter)));
			departureDateField.setText(String.valueOf(booking.get().getDepartureDate().format(formatter)));

		}
		
	}
	
	private void addLabels() {
		JLabel guestAmountLabel, arrivalDateLabel, departureDateLabel;
		
		guestAmountLabel = new JLabel("Cantidad huespedes");
		guestAmountLabel.setBounds(200, 120-(10*k), 150, 20);
		
		arrivalDateLabel = new JLabel("Fecha de llegada yyyy/MM/dd");
		arrivalDateLabel.setBounds(200, 170-(10*k), 200, 20);
		
		departureDateLabel = new JLabel("Fecha de salida yyyy/MM/dd");
		departureDateLabel.setBounds(200, 220-(10*k), 200, 20);

	    f.add(guestAmountLabel);
	    f.add(arrivalDateLabel);
	    f.add(departureDateLabel);
	}
}
