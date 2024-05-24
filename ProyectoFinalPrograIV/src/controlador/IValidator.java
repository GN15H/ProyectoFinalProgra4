package controlador;

import java.util.List;


public interface IValidator<T, E extends Enum<E>> {
	public E validate(List<String> elements);
	public T parseObject(List<Object> elements);
}
