package controlador;

import modelos.states.CreateRoomStates;

public class RoomValidator {

	RoomsController roomsController = new RoomsController();
	
	public CreateRoomStates validate(String roomType, String capacity, String price, String comfort) {
		if(roomType.isEmpty() || capacity.isEmpty() || price.isEmpty() || comfort.isEmpty()) {
			return CreateRoomStates.emptyFields;
		}
		
		
		if(!isNumeric(capacity) || !isDecimal(price)) {
			return CreateRoomStates.wrongFormat;
		}
		
		
		return CreateRoomStates.verified;
	}
	
	private boolean isNumeric(String str) {
        return str.matches("\\d+");
    }
	
	private boolean isDecimal(String str) {
		return str.matches("^\\d+(\\.\\d+)?$");
	}
}
