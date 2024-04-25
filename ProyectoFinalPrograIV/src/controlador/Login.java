package controlador;

import modelos.User;

import java.util.List;

import entities.UserData;
import main.ProyectoFinal;

public class Login {
	private String username;
	private String password;
	boolean successfulLogin = false;
	private UserData userData;
	
	private boolean verifyUser() {
		List<User> loggedUser;
		System.out.println("Ingrese su nombre de usuario");
		username = ProyectoFinal.scanner.next();
		
		loggedUser = userData.getUser(username);
		
		if(loggedUser.isEmpty()) { //if user does not exist
			System.out.println("Usuario no existente.");
			System.out.println("------------------------");
			return false;
		}
		 
		System.out.println("Ingrese la contraseña");
		password = ProyectoFinal.scanner.next();
			
		if(!password.equals(loggedUser.get(0).getPassword())) { //password does not match
			System.out.println("Contraseña incorrecta.");
			System.out.println("------------------------");
			return false;
		}
		
		return true;
	}
	
	public boolean verifyUserRAM(String username, String password) {
		List<User> loggedUser;
		UserController userController = new UserController();
		userController.readObjectsFromFile("usuarios.txt");
		
		loggedUser = userData.getUser(username);
		
		if(loggedUser.isEmpty()) { //if user does not exist
			System.out.println("Usuario no existente.");
			System.out.println("------------------------");
			return false;
		}

		if(!password.equals(loggedUser.get(0).getPassword())) { //password does not match
			System.out.println("Contraseña incorrecta.");
			System.out.println("------------------------");
			return false;
		}
		
		return true;
	}
	
	public List<User> verifyUser(String username, String password) {
		List<User> loggedUser;
		UserController userController = new UserController();
		List<User> usersList = userController.readObjectsFromFile("usuarios.txt");
		
		loggedUser = userController.getUser(username, usersList);
		
		/*if(loggedUser.isEmpty()) { //if user does not exist
			System.out.println("Usuario no existente.");
			System.out.println("------------------------");
			return false;
		}

		if(!password.equals(loggedUser.get(0).getPassword())) { //password does not match
			System.out.println("Contraseña incorrecta.");
			System.out.println("------------------------");
			return false;
		}*/
		
		return loggedUser;
	}
	
	public boolean loginUser() {
		int counter = 0;
		boolean exists = false;
		while(counter < 3 && !exists) {
			exists = verifyUser();
			counter++;
		}
		return exists;
	}
	
	
	/*private List<User> getUser(String username) {
		List<User> userExistence = ProyectoFinal.usersList.stream()
												.filter(e -> e.getEmail().equals(username))
												.collect(Collectors.toList());
		return userExistence;
	}*/
	
	public Login(UserData userData) {
		this.userData = userData;
	}
}