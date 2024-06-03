package modelos.states;

public enum RegisterStates {
	emptyFields("Tiene campos vacios"),
	wrongPassword("Contraseñas no coinciden"),
	verified("Inicio de sesión exitoso");
	
	private final String value;
	
	RegisterStates(String value){
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
}
