package controlador;

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
		userController.addElement(new User(idType, id, names, lastNames, email, residenceAddress, residenceCity, phone, password, UserType.client));
	}

}
