package it.prova.pizzastore.web.api.exeption;

public class ClienteNotFound extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ClienteNotFound() {
	}

	public ClienteNotFound(String message) {
		super(message);
	}

}
