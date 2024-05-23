package modelos;

public enum RoomType{
	simple("Simple"),
	multiple("Multiple");
	
	private final String value;
	
	RoomType(String value){
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
}
