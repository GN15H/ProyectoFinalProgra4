package main;

import modelos.User;

import java.util.List;

import entities.UserData;

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
	
	public boolean verifyUser(String username, String password) {
		List<User> loggedUser;
		
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
