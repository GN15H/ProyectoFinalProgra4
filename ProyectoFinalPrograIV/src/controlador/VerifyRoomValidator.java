package controlador;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import modelos.VerifyRoom;
import modelos.states.VerifyRoomStates;

public class VerifyRoomValidator extends Validator<VerifyRoom, VerifyRoomStates> {
	private final int ARRIVAL_DATE = 0;
	private final int DEPARTURE_DATE = 1;
	
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

	
	@Override
	public VerifyRoomStates validate(List<String> elements) {
		final String arrivalDate = elements.get(ARRIVAL_DATE);
		final String departureDate = elements.get(DEPARTURE_DATE);
		
		if(super.areFieldsEmpty(elements.get(ARRIVAL_DATE), elements.get(DEPARTURE_DATE))) return VerifyRoomStates.emptyFields;
		
		if(invalidFormat(arrivalDate, departureDate)) return VerifyRoomStates.invalidDate;
		
		LocalDate arrivalDateObj = LocalDate.parse((String) elements.get(ARRIVAL_DATE), formatter);
		LocalDate departureDateObj = LocalDate.parse((String) elements.get(DEPARTURE_DATE), formatter); 
		
		if(super.invalidDates(arrivalDateObj, departureDateObj)) return VerifyRoomStates.invalidDate;
		
		return VerifyRoomStates.verified;
	}

	@Override
	public VerifyRoom parseObject(List<Object> elements) {
		LocalDate arrivalDate = LocalDate.parse((String) elements.get(ARRIVAL_DATE), formatter);
		LocalDate departureDate = LocalDate.parse((String) elements.get(DEPARTURE_DATE), formatter); 		
		return new VerifyRoom(arrivalDate, departureDate);
	}
	
	private boolean invalidFormat(String arrivalDate, String departureDate) {return !super.isDate(arrivalDate) || !super.isDate(departureDate);}
	

}
