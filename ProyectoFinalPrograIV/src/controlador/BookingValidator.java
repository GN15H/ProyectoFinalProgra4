package controlador;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import modelos.Booking;
import modelos.User;
import modelos.Room;
import modelos.states.BookingStates;

public class BookingValidator extends Validator<Booking, BookingStates> {
	
	private final int GUEST_AMOUNT = 0;
	private final int ARRIVAL_DATE = 1;
	private final int DEPARTURE_DATE = 2;
	private final int USER = 3;
	private final int ROOM = 4;
	
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	private final RoomsController roomsController = new RoomsController();
	
	@Override
	public BookingStates validate(List<String> elements) {
		String guestAmount = elements.get(GUEST_AMOUNT);
		String arrivalDate = elements.get(ARRIVAL_DATE);
		String departureDate = elements.get(DEPARTURE_DATE);
		
		if(super.areFieldsEmpty(guestAmount, arrivalDate, departureDate)) return BookingStates.emptyFields;
		
		if(invalidFormat(guestAmount, arrivalDate, departureDate)) return BookingStates.wrongFormat;
		
		LocalDate arrivalDateObj = LocalDate.parse((String) elements.get(ARRIVAL_DATE), formatter);
		LocalDate departureDateObj = LocalDate.parse((String) elements.get(DEPARTURE_DATE), formatter); 
		
		if(super.invalidDates(arrivalDateObj, departureDateObj)) return BookingStates.invalidDate;
		
		if(!roomsController.isRoomAvailable(roomsController.getElementById(Integer.parseInt( elements.get(ROOM))).get(), arrivalDateObj, departureDateObj)) 
			return BookingStates.notAvailable;
		
		if(invalidGuestAmount(Integer.parseInt(elements.get(GUEST_AMOUNT)), roomsController.getElementById(Integer.parseInt( elements.get(ROOM))).get().getCapacity()))
			return BookingStates.invalidGuestAmount;
		
		return BookingStates.verified;
	}
	
	
	@Override
	public Booking parseObject(List<Object> elements) {
		int guestAmountNum = Integer.parseInt((String) elements.get(GUEST_AMOUNT));
		LocalDate arrivalDate = LocalDate.parse((String) elements.get(ARRIVAL_DATE), formatter);
		LocalDate departureDate = LocalDate.parse((String) elements.get(DEPARTURE_DATE), formatter); 		
		return new Booking(guestAmountNum, arrivalDate, departureDate, (User) elements.get(USER), (Room) elements.get(ROOM));
		
	}
	
	private boolean invalidFormat(String guestAmount, String arrivalDate, String departureDate) {
		return !super.isNumeric(guestAmount) || !super.isDate(arrivalDate) || !super.isDate(departureDate);
	}
	
	
	private boolean invalidGuestAmount(int guestAmount, int capacity) { return guestAmount > capacity; }
}
