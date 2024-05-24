package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controlador.BookingsController;
import modelos.Booking;
import modelos.User;
import views.userViews.BookingDetailsView;
import views.widgets.BookingCellRenderer;

public class BookingsView {
	
	User user;
	BookingsController bookingsController = new BookingsController();
	
	public BookingsView(User user) {
		this.user = user;
		
		JFrame frame = new JFrame("Bookings View");
        frame.setSize(600, 400);

        // Create a list of Room objects
        List<Booking> bookingsList = new ArrayList<>( bookingsController.getUserBookings(user));
        bookingsList.sort(Comparator.comparingInt(Booking::getId));
        
        if (bookingsList.isEmpty()) {
        	JOptionPane.showMessageDialog(frame, "No tiene reservas", "Error", JOptionPane.CLOSED_OPTION);
        	frame.dispose();
        }
        
        // Create the JList with a DefaultListModel
        DefaultListModel<Booking> listModel = new DefaultListModel<>();
        for (Booking booking : bookingsList) {
            listModel.addElement(booking);
        }

        JList<Booking> jList = new JList<>(listModel);
        jList.setCellRenderer(new BookingCellRenderer());
        jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // Add ListSelectionListener to handle selection changes
        jList.addListSelectionListener(new ListSelectionListener() {
            @SuppressWarnings("unused")
			@Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    Booking selectedBooking = jList.getSelectedValue();
                    if (selectedBooking != null) {
                    	BookingDetailsView bookingDetailsView = new BookingDetailsView(selectedBooking);
                        System.out.println("Selected Booking: " + selectedBooking.getId() + ", " +
                                           "huespedes: " + selectedBooking.getGuestAmount() + ", " +
                                           "Fecha llegada" + selectedBooking.getArrivalDate() + ", " +
                                           "Fecha salida: " + selectedBooking.getDepartureDate() + ", " +
                                           "Usuario: " + selectedBooking.getUser().getNames() + ", "+
                                           "Habitación: " + selectedBooking.getRoom().getId());
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
