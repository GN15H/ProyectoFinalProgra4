package main;

import controlador.Register;
import entities.UserData;

public class Menu {
	
	int menuOption = 1;
	private UserData userData;
	
	private void handleLogin() {
		/*Login login = new Login();
		if(!login.loginUser()) {
			menuOption = 0;
		}else {
			System.out.println("Usuario logueado correctamente");
		}*/
	}
	
	private void handleRegister() {
		Register register = new Register(userData);
		register.registerUser();
		System.out.println("Usuario registrado extosamente");
	}
	
	private void printMenu() {
		System.out.println("Bienvenido a MyHotel...");
		System.out.println("Mas que un lugar para descansar.");
		System.out.println("--------------------------------");
		System.out.println("Ingrese la opcion deseada");
		System.out.println("1. Registrarse como cliente.");
		System.out.println("2. Iniciar Sesion.");
		System.out.println("3. Salir.");
	}
	
	
	
	public void menuLoop() {
		while(menuOption != 0 ) {			
			printMenu();
			menuOption = ProyectoFinal.scanner.nextInt();
			switch(menuOption) {
				case 1:
					System.out.println("---------------------");
					handleRegister();
					break;
				case 2:
					System.out.println("---------------------");
					handleLogin();
					break;
				default:
					menuOption = 0;
					break;
			}	
		}
		System.out.println("Hasta luego");
	}
	
	
	public Menu(UserData userData) {
		this.userData = userData;
	}
	
}
