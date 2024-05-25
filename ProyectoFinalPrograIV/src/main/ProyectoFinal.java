package main;

import java.util.List;

import views.LoginView;


public class ProyectoFinal {
	
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		//UserController userController = new UserController();
		//userController.addElement(new User("C.C", "1089097158", "Cristobal", "Ochoa", "root", "dir", "dir", "3202599542", "12345", UserType.admin));
		LoginView loginView = new LoginView();
	}
	
	public static <T> void printArray(List<T> array) {
        for (T element : array) {
            System.out.print(element.toString() + " ");
        }
        System.out.println();
    }

}
