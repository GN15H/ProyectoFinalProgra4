package controlador;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import modelos.Booking;
import modelos.User;
import modelos.Room;
import modelos.states.BookingStates;

public class BookingValidator implements IValidator<Booking, BookingStates> {
	
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
		
		//campos vacios
		if(guestAmount.isEmpty() || arrivalDate.isEmpty() || departureDate.isEmpty()) {
			return BookingStates.emptyFields;
		}
		
		//cantidad de huespedes no es numero o las fechas no tienen el formato
		if(!isNumeric(guestAmount) || !isDate(arrivalDate) || !isDate(departureDate)) {
			return BookingStates.wrongFormat;
		}
		
		LocalDate arrivalDateObj = LocalDate.parse((String) elements.get(ARRIVAL_DATE), formatter);
		LocalDate departureDateObj = LocalDate.parse((String) elements.get(DEPARTURE_DATE), formatter); 
		
		//fecha de salida antes de la de llegada o la de llegada antes que el día presente
		if(departureDateObj.isBefore(arrivalDateObj) || arrivalDateObj.isBefore(LocalDate.now())) {
			return BookingStates.invalidDate;
		}
		
		if(!roomsController.isRoomAvailable(roomsController.getElementById(Integer.parseInt( elements.get(ROOM))).get(), arrivalDateObj, departureDateObj)) {
			return BookingStates.notAvailable;
		}
		
		if(Integer.parseInt(elements.get(GUEST_AMOUNT)) > roomsController.getElementById(Integer.parseInt( elements.get(ROOM))).get().getCapacity()) {
			return BookingStates.invalidGuestAmount;
		}
		
		
		return BookingStates.verified;
	}
	
	@Override
	public Booking parseObject(List<Object> elements) {
		int guestAmountNum = Integer.parseInt((String) elements.get(GUEST_AMOUNT));
		LocalDate arrivalDate = LocalDate.parse((String) elements.get(ARRIVAL_DATE), formatter);
		LocalDate departureDate = LocalDate.parse((String) elements.get(DEPARTURE_DATE), formatter); 		
		return new Booking(guestAmountNum, arrivalDate, departureDate, (User) elements.get(USER), (Room) elements.get(ROOM));
		
	}
	
	private boolean isNumeric(String str) {
        return str.matches("\\d+");
    }
	
	private boolean isDate(String str) {
		return str.matches("^\\d\\d\\d\\d\\/((0[13578]|1[02])\\/([0-2][0-9]|3[01])|(0[469]|11)\\/([0-2][0-9]|30)|02\\/([0-1][0-9]|2[0-8]))$");
	}
}
