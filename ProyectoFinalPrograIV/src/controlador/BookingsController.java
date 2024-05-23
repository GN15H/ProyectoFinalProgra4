package controlador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import modelos.Booking;
import modelos.Constants;

public class BookingsController extends CrudController<Booking, Integer> {
	
	private List<Booking> bookingsList;

	public BookingsController() {
		super(Constants.BOOKINGS_FILE);
		this.bookingsList = super.getAll();
	}
	
	@Override 
	public List<Booking> getAll(){
		return this.bookingsList;
	}

	@Override
	public void addElement(Booking element) {
		int id = bookingsList.size() + 1;
		element.setId(id);
		super.addElement(element);
	}
	
	@Override
	public Optional<Booking> getElementById(Integer id) {
		return bookingsList.stream()
				.filter(e -> e.getId() == id)
				.findFirst();
	}
	
	@Override
	public void updateElement(Booking booking, Booking updatedBooking) {
		updatedBooking.setId(booking.getId());
		super.updateElement(booking, updatedBooking);
		
	}

	private boolean isDateWithinRange(LocalDate date, LocalDate startDate, LocalDate endDate) {
        return !date.isBefore(startDate) && !date.isAfter(endDate);
    }
	
	public List<Booking> getBookingsByDate(List<Booking> bookings, LocalDate date) {
        List<Booking> bookingsForDate = new ArrayList<>();
        
        for (Booking booking : bookings) {
            if (isDateWithinRange(date, booking.getArrivalDate(), booking.getDepartureDate())) {
                bookingsForDate.add(booking);
            }
        }
        
        return bookingsForDate;
    }

}
