package modelos;

public enum UserType {
	admin("Administrador"),
	client("Cliente");
	
	private final String value;
	
	UserType(String value){
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
