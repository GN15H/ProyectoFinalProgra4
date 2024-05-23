package modelos.states;

public enum LoginStates {
	emptyFields("Tiene campos vacios"),
	wrongUser("Usuario no existente"),
	wrongPassword("Contraseña incorrecta"),
	verified("Inicio de sesión exitoso");
	
	private final String value;
	
	LoginStates(String value){
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
}
