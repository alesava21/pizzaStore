package it.prova.pizzastore.web.api.exeption;

public class FattorinoNotFound extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public FattorinoNotFound () {}
	
	public FattorinoNotFound ( String message) {
		super(message);
	}

}
