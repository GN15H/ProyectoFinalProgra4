package controlador;

import modelos.UserType;
import modelos.states.RegisterStates;

import java.util.List;

import modelos.User;


public class RegisterValidator extends Validator<User, RegisterStates> {
	private final int ID_TYPE = 0;
	private final int ID = 1; 
	private final int NAMES = 2;
	private final int LAST_NAMES = 3;
	private final int EMAIL = 4;
	private final int RESIDENCE_ADDRESS = 5;
	private final int RESIDENCE_CITY = 6;
	private final int PHONE = 7;
	private final int PASSWORD = 8;
	private final int CONFIRMED_PASSWORD = 9;
	
	public void registerUser(
				String idType, 
				String id, 
				String names, 
				String lastNames,
				String email,
				String residenceAddress,
				String residenceCity,
				String phone,
				String password
				) 
	{
		UserController userController = new UserController();
		userController.addElement(new User(idType, id, names, lastNames, email, residenceAddress, residenceCity, phone, password, UserType.client));
	}

	@Override
	public RegisterStates validate(List<String> elements) {
		final String idType = elements.get(ID_TYPE);
		final String id = elements.get(ID);
		final String names = elements.get(NAMES);
		final String lastNames = elements.get(LAST_NAMES);
		final String email = elements.get(EMAIL);
		final String residenceAddress = elements.get(RESIDENCE_ADDRESS);
		final String residenceCity = elements.get(RESIDENCE_CITY);
		final String phone = elements.get(PHONE);
		final String password = elements.get(PASSWORD);
		final String confirmedPassword = elements.get(CONFIRMED_PASSWORD);
		
		if(super.areFieldsEmpty(idType, id, names, lastNames, email, residenceAddress, residenceCity, phone, password, confirmedPassword)) return RegisterStates.emptyFields;
		
		if(!password.equals(confirmedPassword)) return RegisterStates.wrongPassword;
		
		return RegisterStates.verified;
		
	}

	@Override
	public User parseObject(List<Object> elements) {
		final String idType = (String) elements.get(ID_TYPE);
		final String id = (String) elements.get(ID);
		final String names = (String) elements.get(NAMES);
		final String lastNames = (String) elements.get(LAST_NAMES);
		final String email = (String) elements.get(EMAIL);
		final String residenceAddress = (String) elements.get(RESIDENCE_ADDRESS);
		final String residenceCity = (String) elements.get(RESIDENCE_CITY);
		final String phone = (String) elements.get(PHONE);
		final String password = (String) elements.get(PASSWORD);
		
		return new User(idType, id, names, lastNames, email, residenceAddress, residenceCity, phone, password, UserType.client);
	}
	

}
