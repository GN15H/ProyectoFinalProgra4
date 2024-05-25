package modelos.states;

public enum RoomFilterStates {
	emptyFields("Tiene campos vacios"),
	wrongFormat("Tiene campos con valores no válidos"),
	invalidDate("Tiene fechas no válidas"),
	verified("Inicio de sesión exitoso");
	
	private final String value;
	
	RoomFilterStates(String value){
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
