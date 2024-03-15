package main;
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
	
    
	private void registerUserData() {
		System.out.println("Ingrese el tipo de id");
		idType = ProyectoFinal.scanner.next();
		System.out.println("Ingrese el id");
		id = ProyectoFinal.scanner.next();
		System.out.println("Ingrese su nombre(s)");
		names = ProyectoFinal.scanner.next();
		System.out.println("Ingrese su apellido(s)");
		lastNames = ProyectoFinal.scanner.next();
		System.out.println("Ingrese su email");
		email = ProyectoFinal.scanner.next();
		System.out.println("Ingrese su dirección residencial");
		residenceAddress = ProyectoFinal.scanner.next();
		System.out.println("Ingrese su ciudad de residencia");
		residenceCity = ProyectoFinal.scanner.next();
		System.out.println("Ingrese su teléfono");
		phone = ProyectoFinal.scanner.next();
		System.out.println("Ingrese su contraseña");
		password = ProyectoFinal.scanner.next();
		System.out.println("Confirme su contraseña");
		confirmPassword = ProyectoFinal.scanner.next();
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
		ProyectoFinal.usersList.add(new User(idType, id, names, lastNames, email, residenceAddress, residenceCity, phone, password));
	}
	
	public Register() {
		
	}
}
