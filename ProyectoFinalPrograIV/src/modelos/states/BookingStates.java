package modelos.states;

public enum BookingStates {
	emptyFields("Tiene campos vacios"),
	wrongFormat("Tiene campos con valores no válidos"),
	invalidDate("Tiene fechas no válidas"),
	invalidGuestAmount("Tiene mas huespedes que la capacidad de la habitación"),
	notAvailable("Habitación no disponible"),
	verified("Inicio de sesión exitoso");
	
	private final String value;
	
	BookingStates(String value){
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
