package modelos.states;

public enum CreateRoomStates {
	emptyFields("Tiene campos vacios"),
	wrongFormat("Tiene campos con valores no validos"),
	verified("Inicio de sesión exitoso");
	
	private final String value;
	
	CreateRoomStates(String value){
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
}
