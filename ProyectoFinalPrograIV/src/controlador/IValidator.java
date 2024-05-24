package controlador;

import java.util.List;


public abstract class IValidator<T, E extends Enum<E>> {
	
	public abstract E validate(List<String> elements);
	
	public abstract T parseObject(List<Object> elements);	
	
	protected boolean areFieldsEmpty(String... fields) {
		for(String item: fields) {
			if(item.isEmpty()) {
				return true;
			}
		}
		return false;
	}
}
