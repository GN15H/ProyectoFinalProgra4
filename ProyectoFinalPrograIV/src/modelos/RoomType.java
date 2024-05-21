package modelos;

public enum RoomType{
	simple("simple"),
	multiple("multiple");
	
	private final String value;
	
	RoomType(String value){
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
}
