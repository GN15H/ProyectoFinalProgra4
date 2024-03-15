package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import modelos.User;


public class ProyectoFinal {
	
	public static Scanner scanner = new Scanner (System.in);
	public static List<User> usersList = new ArrayList<>();
	
	public static void showLoginRegisterMenu() {
		Menu menu = new Menu();
		menu.menuLoop();
		
	}
	
	public static void main(String[] args) {
		usersList.add(new User("CC", "1089097158", "Cristobal", "Ochoa", "cristobalochoacadavid@gmail.com", "El Dorado", "Pereira", "3202599542", "12345"));
		showLoginRegisterMenu();
		scanner.close();
	}

}
