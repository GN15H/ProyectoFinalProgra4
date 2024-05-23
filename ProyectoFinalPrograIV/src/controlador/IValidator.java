package controlador;

import java.util.List;

import modelos.states.ValidatorStates;

public interface IValidator<T> {
	public ValidatorStates validate(List<String> elements);
	public T parseObject(List<Object> elements);
}
