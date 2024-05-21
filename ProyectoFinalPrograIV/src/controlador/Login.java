package controlador;

import modelos.LoginStates;
import modelos.User;

import java.util.Optional;



public class Login {
	boolean successfulLogin = false;
	private UserController userController = new UserController();
	
	public LoginStates verifyUser(String username, String password) {
		if(username.isEmpty() || password.isEmpty()) {
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
	
	public Login() {

	}
}