package it.prova.pizzastore.web.api.exeption;

public class OrdineNotFound extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public OrdineNotFound() {
	}

	public OrdineNotFound(String message) {
		super(message);
	}

}
