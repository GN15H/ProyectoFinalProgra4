package controlador;

import java.util.List;
import java.util.Optional;

import modelos.Constants;
import modelos.User;

public class UserController extends CrudController<User, String> {
	
	private List<User> usersList;

	public UserController() {
		super(Constants.USERS_FILE);
		usersList = super.getAll();
	}

	@Override 
	public List<User> getAll(){
		return this.usersList;
	}

	@Override
	public Optional<User> getElementById(String username) {
			return usersList.stream()
					.filter(e -> e.getEmail().equals(username))
					.findFirst();
	}

}
