package controlador;

import modelos.states.ValidatorStates;

public class BookingValidator {
	BookingsController bookingsController = new BookingsController();
	
	public ValidatorStates validate(String guestAmount, String arrivalDate, String departureDate) {
		if(guestAmount.isEmpty() || arrivalDate.isEmpty() || departureDate.isEmpty()) {
			return ValidatorStates.emptyFields;
		}
		
		if(!isNumeric(guestAmount) || !isDate(arrivalDate) || !isDate(departureDate)) {
			return ValidatorStates.wrongFormat;
		}
		
		return ValidatorStates.verified;
	}
	
	private boolean isNumeric(String str) {
        return str.matches("\\d+");
    }
	
	private boolean isDate(String str) {
		return str.matches("^\\d\\d\\d\\d\\/\\d\\d\\/\\d\\d$");
	}
}
