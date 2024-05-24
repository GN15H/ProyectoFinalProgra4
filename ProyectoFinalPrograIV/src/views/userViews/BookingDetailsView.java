package views.userViews;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import controlador.BookingsController;
import modelos.Booking;
import modelos.User;


public class BookingDetailsView {
	private Booking booking;
	private JFrame f;
	private JLabel idBookingLabel, guestAmountLabel, arrivalDateLabel, departureDateLabel, userLabel, roomLabel;
	private JButton editButton, deleteButton;
	private BookingsController bookingsController = new BookingsController();
	
	private void addLabels() {
		idBookingLabel = new JLabel("Reserva: " + booking.getId());
		idBookingLabel.setBounds(180,20,80,20);
		
		guestAmountLabel = new JLabel("Huespedes : " + booking.getGuestAmount());
		guestAmountLabel.setBounds(180,60,80,20);
		
		arrivalDateLabel = new JLabel("Fecha de llegada: " + booking.getArrivalDate());
		arrivalDateLabel.setBounds(180,80,180,20);
		
		departureDateLabel = new JLabel("Fecha de salida: " + booking.getDepartureDate());
		departureDateLabel.setBounds(180,100,180,80);
		
		userLabel = new JLabel("Usuario : " + booking.getUser().getNames() + " " + booking.getUser().getLastNames());
		userLabel.setBounds(180,180,180,20);
		
		roomLabel = new JLabel("Habitación: " + booking.getRoom().getId());
		roomLabel.setBounds(180,220,180,80);
		
		f.add(idBookingLabel);
		f.add(guestAmountLabel);
		f.add(arrivalDateLabel);
		f.add(departureDateLabel);
		f.add(userLabel);		
		f.add(roomLabel);

	}
	
	private void addButtons() {

		editButton = new JButton("Editar");
		editButton.setBounds(200,200,100,20);
		editButton.addActionListener(new ActionListener() {
        	@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent e) {
    			f.dispose();
    			BookView bookView = new BookView(booking.getRoom(), booking.getUser(), Optional.of(booking));
        	}
        		
        });
		
		deleteButton = new JButton("Borrar");
		deleteButton.setBounds(200,230,100,20);
		deleteButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
    			f.dispose();
    			bookingsController.deleteElement(booking);
    			JOptionPane.showMessageDialog(f, "Reserva borrada", "Éxito", JOptionPane.CLOSED_OPTION);
        	}
        		
        });
		
		
		if(booking.getArrivalDate().isBefore(LocalDate.now())) {
			f.add(editButton);
			f.add(deleteButton);
		}
	}
	
	public BookingDetailsView(Booking booking) {
		this.booking = booking;
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

