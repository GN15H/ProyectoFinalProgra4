package controlador;

import modelos.states.ValidatorStates;

public class RoomValidator {

	RoomsController roomsController = new RoomsController();
	
	public ValidatorStates validate(String roomType, String capacity, String price, String comfort) {
		if(roomType.isEmpty() || capacity.isEmpty() || price.isEmpty() || comfort.isEmpty()) {
			return ValidatorStates.emptyFields;
		}
		
		
		if(!isNumeric(capacity) || !isDecimal(price)) {
			return ValidatorStates.wrongFormat;
		}
		
		
		return ValidatorStates.verified;
	}
	
	private boolean isNumeric(String str) {
        return str.matches("\\d+");
    }
	
	private boolean isDecimal(String str) {
		return str.matches("^\\d+(\\.\\d+)?$");
	}
}
