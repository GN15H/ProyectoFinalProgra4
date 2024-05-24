package controlador;


import java.util.List;

import modelos.Room;
import modelos.RoomType;
import modelos.states.RoomCreationStates;

public class RoomValidator implements IValidator<Room, RoomCreationStates>{
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
		
		if(roomType.isEmpty() || capacity.isEmpty() || price.isEmpty() || comfort.isEmpty()) {
			return RoomCreationStates.emptyFields;
		}
		
		if(!isNumeric(capacity) || !isDecimal(price)) {
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
	
	private boolean isNumeric(String str) {
        return str.matches("\\d+");
    }
	
	private boolean isDecimal(String str) {
		return str.matches("^\\d+(\\.\\d+)?$");
	}

}
