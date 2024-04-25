package main;


import java.util.Scanner;

import entities.UserData;
import entities.UserType;
import modelos.User;
import views.LoginView;

public class ProyectoFinal {
	
	public static Scanner scanner = new Scanner (System.in);
	
	public static void showLoginRegisterMenu() {
		//Menu menu = new Menu();
		//menu.menuLoop();
		
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		UserData userData = new UserData();
		userData.addUser(new User("CC", "1089097158", "Cristobal", "Ochoa", "cristobalochoacadavid@gmail.com", "El Dorado", "Pereira", "3202599542", "12345",UserType.client));
		//RegisterView registerView = new RegisterView();
		LoginView loginView = new LoginView(userData);
		//usersList.add(new User("CC", "1089097158", "Cristobal", "Ochoa", "cristobalochoacadavid@gmail.com", "El Dorado", "Pereira", "3202599542", "12345"));
		/*showLoginRegisterMenu();*/
		scanner.close();
	}

}
