package controlador;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import modelos.Booking;
import modelos.User;
import modelos.Room;
import modelos.states.ValidatorStates;

public class BookingValidator implements IValidator<Booking> {
	
	private final int GUEST_AMOUNT = 0;
	private final int ARRIVAL_DATE = 1;
	private final int DEPARTURE_DATE = 2;
	private final int USER = 3;
	private final int ROOM = 4;
	
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	
	@Override
	public ValidatorStates validate(List<String> elements) {
		String guestAmount = elements.get(GUEST_AMOUNT);
		String arrivalDate = elements.get(ARRIVAL_DATE);
		String departureDate = elements.get(DEPARTURE_DATE);
		
		if(guestAmount.isEmpty() || arrivalDate.isEmpty() || departureDate.isEmpty()) {
			return ValidatorStates.emptyFields;
		}
		
		if(!isNumeric(guestAmount) || !isDate(arrivalDate) || !isDate(departureDate)) {
			return ValidatorStates.wrongFormat;
		}
		
		LocalDate arrivalDateObj = LocalDate.parse((String) elements.get(ARRIVAL_DATE), formatter);
		LocalDate departureDateObj = LocalDate.parse((String) elements.get(DEPARTURE_DATE), formatter); 
		
		if(departureDateObj.isBefore(arrivalDateObj)) {
			return ValidatorStates.wrongFormat;
		}
		
		return ValidatorStates.verified;
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
