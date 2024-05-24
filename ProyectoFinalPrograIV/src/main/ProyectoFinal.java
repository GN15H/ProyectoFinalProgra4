package main;

import java.util.List;

import entities.UserData;
import entities.UserType;
import modelos.User;
import views.LoginView;

public class ProyectoFinal {
	
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		UserData userData = new UserData();
		userData.addUser(new User("CC", "1089097158", "Cristobal", "Ochoa", "cristobalochoacadavid@gmail.com", "El Dorado", "Pereira", "3202599542", "12345",UserType.client));
		LoginView loginView = new LoginView(userData);
		//RoomsView roomsView = new RoomsView();

	}
	
	public static <T> void printArray(List<T> array) {
        for (T element : array) {
            System.out.print(element.toString() + " ");
        }
        System.out.println();
    }

}
