package main;

import modelos.User;

import java.util.List;
import java.util.stream.Collectors;

public class Login {
	private String username;
	private String password;
	boolean successfulLogin = false;
	
	public boolean verifyUser() {
		List<User> loggedUser;
		System.out.println("Ingrese su nombre de usuario");
		username = ProyectoFinal.scanner.next();
		
		loggedUser = getUser(username);
		
		if(loggedUser.isEmpty()) { //if user does not exist
			System.out.println("Usuario no existente.");
			System.out.println("-----------------------");
			return false;
		}
		 
		System.out.println("Ingrese la contraseña");
		password = ProyectoFinal.scanner.next();
			
		if(!password.equals(loggedUser.get(0).getPassword())) { //password does not match
			System.out.println("Contraseña incorrecta");
			System.out.println("-----------------------");
			return false;
		}
		
		return true;
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
	
	
	public List<User> getUser(String username) {
		List<User> userExistence = ProyectoFinal.usersList.stream()
												.filter(e -> e.getEmail().equals(username))
												.collect(Collectors.toList());
		return userExistence;
	}
	
	public Login() {
		
	}
}
