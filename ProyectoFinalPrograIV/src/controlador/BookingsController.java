package controlador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import modelos.Booking;
import modelos.Constants;
import modelos.User;

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
	
	public List<Booking> getUserBookings(User user){
		return bookingsList.stream()
				.filter(e -> e.getUser().getId().equals(user.getId()))
				.toList();
	}

	public boolean isDateWithinRange(LocalDate bookingArrivalDate, LocalDate bookingDepartureDate, LocalDate startDate, LocalDate endDate) {
        return (!bookingArrivalDate.isBefore(startDate) && !bookingArrivalDate.isAfter(endDate))
        		|| (!bookingDepartureDate.isBefore(startDate) && !bookingDepartureDate.isAfter(endDate))
        		|| (!bookingArrivalDate.isAfter(startDate) && !bookingDepartureDate.isBefore(endDate));
    }
	
	public List<Booking> getBookingsByDate(LocalDate arrivalDate, LocalDate departureDate) {
        List<Booking> bookingsByDate = new ArrayList<>();
        
        for (Booking booking : bookingsList) {
            if (isDateWithinRange(arrivalDate, departureDate, booking.getArrivalDate(), booking.getDepartureDate()) ) {
                bookingsByDate.add(booking);
            }
        }
        
        return bookingsByDate;
    }
	

}
