package main;

import java.util.List;

import views.LoginView;


public class ProyectoFinal {
	
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		LoginView loginView = new LoginView();
	}
	
	public static <T> void printArray(List<T> array) {
        for (T element : array) {
            System.out.print(element.toString() + " ");
        }
        System.out.println();
    }

}
