package controlador;

import java.util.List;

import entities.UserData;
import entities.UserType;
import main.ProyectoFinal;
import modelos.User;


public class Register {

	private String idType;
    private String id;
    private String names;
    private String lastNames;
    private String email;
    private String residenceAddress;
    private String residenceCity;
    private String phone;
    private String password;
    private String confirmPassword;
	private UserData userData;
    
	private void registerUserData() {
		ProyectoFinal.scanner.nextLine();
		System.out.println("Ingrese el tipo de id");
		idType = ProyectoFinal.scanner.nextLine();
		System.out.println("Ingrese el id");
		id = ProyectoFinal.scanner.nextLine();
		System.out.println("Ingrese su nombre(s)");
		names = ProyectoFinal.scanner.nextLine();
		System.out.println("Ingrese su apellido(s)");
		lastNames = ProyectoFinal.scanner.nextLine();
		System.out.println("Ingrese su email");
		email = ProyectoFinal.scanner.nextLine();
		System.out.println("Ingrese su dirección residencial");
		residenceAddress = ProyectoFinal.scanner.nextLine();
		System.out.println("Ingrese su ciudad de residencia");
		residenceCity = ProyectoFinal.scanner.nextLine();
		System.out.println("Ingrese su teléfono");
		phone = ProyectoFinal.scanner.nextLine();
		System.out.println("Ingrese su contraseña");
		password = ProyectoFinal.scanner.nextLine();
		System.out.println("Confirme su contraseña");
		confirmPassword = ProyectoFinal.scanner.nextLine();
	}
	
	private void confirmPassword() {
		while(!password.equals(confirmPassword)) {
			System.out.println("Contraseñas no coinciden");
			System.out.println("Ingrese su contraseña");
			password = ProyectoFinal.scanner.next();
			System.out.println("Confirme su contraseña");
			confirmPassword = ProyectoFinal.scanner.next();
		}
	}
	
	public void registerUser() {
		registerUserData();
		confirmPassword();
		
		userData.addUser(new User(idType, id, names, lastNames, email, residenceAddress, residenceCity, phone, password, UserType.client));
	}
	
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
		userData.addUser(new User(idType, id, names, lastNames, email, residenceAddress, residenceCity, phone, password, UserType.client));
	}
	
	public Register(UserData userData) {
		this.userData = userData;
	}
}
