package controlador;

import java.util.List;
import entities.UserType;
import modelos.User;


public class Register {
   
	
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
		List<User> userList = userController.readObjectsFromFile("usuarios.txt");
		userList.add(new User(idType, id, names, lastNames, email, residenceAddress, residenceCity, phone, password, UserType.client));
		userController.storeObjectsToFile("usuarios.txt", userList);
	}
	
	public Register() {
	}
}
