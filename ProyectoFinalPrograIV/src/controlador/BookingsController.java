package controlador;

import java.util.List;
import java.util.Optional;

import modelos.Booking;
import views.Constants;

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
	public Optional<Booking> getElementById(Integer id) {
		return bookingsList.stream()
				.filter(e -> e.getId() == id)
				.findFirst();
	}


}
