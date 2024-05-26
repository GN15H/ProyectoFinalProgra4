package controlador;

import modelos.User;
import modelos.states.LoginStates;

import java.util.List;
import java.util.Optional;

public class LoginValidator extends Validator<User, LoginStates>{
	private final int USERNAME = 0;
	private final int PASSWORD = 1;
	
	private UserController userController = new UserController();

	@Override
	public LoginStates validate(List<String> elements) {
		final String username = elements.get(USERNAME);
		final String password = elements.get(PASSWORD);
		
		if(super.areFieldsEmpty(username, password)) {
			return LoginStates.emptyFields;
		}
		
		Optional<User> user = userController.getElementById(username);
		
		if(user.isEmpty()) {
			return LoginStates.wrongUser;
		}
		
		if(!user.get().getPassword().equals(password)) {
			return LoginStates.wrongPassword;
		}
		
		return LoginStates.verified;
	}

	@Override
	public User parseObject(List<Object> elements) {
		// TODO Auto-generated method stub
		return null;
	}
	
}