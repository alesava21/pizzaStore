package it.prova.pizzastore.web.api.exeption;

public class PizzaNotFound extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public PizzaNotFound() {
	}

	public PizzaNotFound(String message) {
		super(message);
	}

}
