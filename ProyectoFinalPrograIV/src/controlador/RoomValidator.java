package controlador;


import java.util.List;

import modelos.Room;
import modelos.RoomType;
import modelos.states.RoomCreationStates;

public class RoomValidator extends Validator<Room, RoomCreationStates>{
	private final int ROOM_TYPE = 0;
	private final int CAPACITY = 1;
	private final int PRICE = 2;
	private final int COMFORT = 3;
	
	@Override
	public RoomCreationStates validate(List<String> elements) {
		String roomType = elements.get(ROOM_TYPE);
		String capacity = elements.get(CAPACITY);
		String price = elements.get(PRICE);
		String comfort = elements.get(COMFORT);
		
		if(super.areFieldsEmpty(roomType, capacity, price, comfort)) {
			return RoomCreationStates.emptyFields;
		}
		
		if(invalidFormat(capacity,price)) {
			return RoomCreationStates.wrongFormat;
		}
		
		return RoomCreationStates.verified;
	}
	
	@Override
	public Room parseObject(List<Object> elements) {
		int capacityNum = Integer.parseInt((String) elements.get(CAPACITY));
		double priceNum = Double.parseDouble((String) elements.get(PRICE));
		RoomType roomTypeEnum = elements.get(ROOM_TYPE).equals(new String("Simple")) ? RoomType.simple : RoomType.multiple;
		return new Room(capacityNum, priceNum, (String) elements.get(COMFORT), roomTypeEnum);
	}
	

	private boolean invalidFormat(String capacity, String price) {
		return !super.isNumeric(capacity) || !super.isDecimal(price);
	}
	
}
