package controlador;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import modelos.RoomFilter;
import modelos.RoomType;
import modelos.states.RoomFilterStates;

public class RoomFilterValidator extends Validator<RoomFilter, RoomFilterStates> {

	private final int ROOM_TYPE = 0;
	private final int CAPACITY = 1;
	private final int PRICE = 2;
	private final int ARRIVAL_DATE = 3;
	private final int DEPARTURE_DATE = 4;
	
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

	@Override
	public RoomFilterStates validate(List<String> elements) {
		String roomType = elements.get(ROOM_TYPE);
		String capacity = elements.get(CAPACITY);
		String price = elements.get(PRICE);
		String arrivalDate = elements.get(ARRIVAL_DATE);
		String departureDate = elements.get(DEPARTURE_DATE);
		
		if(super.areFieldsEmpty(roomType, capacity, price, arrivalDate, departureDate)) return RoomFilterStates.emptyFields;
		
		
		if(invalidFormat(capacity, price, arrivalDate, departureDate)) return RoomFilterStates.wrongFormat;
		
		LocalDate arrivalDateObj = LocalDate.parse((String) elements.get(ARRIVAL_DATE), formatter);
		LocalDate departureDateObj = LocalDate.parse((String) elements.get(DEPARTURE_DATE), formatter); 
		
		if(super.invalidDates(arrivalDateObj, departureDateObj)) return RoomFilterStates.invalidDate;
		
		return RoomFilterStates.verified;
	}
	
	@Override
	public RoomFilter parseObject(List<Object> elements) {
		int capacityNum = Integer.parseInt((String) elements.get(CAPACITY));
		double priceNum = Double.parseDouble((String) elements.get(PRICE));
		RoomType roomTypeEnum = elements.get(ROOM_TYPE).equals(new String("Simple")) ? RoomType.simple : RoomType.multiple;
		LocalDate arrivalDate = LocalDate.parse((String) elements.get(ARRIVAL_DATE), formatter);
		LocalDate departureDate = LocalDate.parse((String) elements.get(DEPARTURE_DATE), formatter); 
		return new RoomFilter(roomTypeEnum, capacityNum, priceNum, arrivalDate, departureDate);
	}
	
	private boolean invalidFormat(String capacity, String price, String arrivalDate, String departureDate) {
		return !isNumeric(capacity) || !isDecimal(price) || !super.isDate(arrivalDate) || !super.isDate(departureDate);
	}
	

}
