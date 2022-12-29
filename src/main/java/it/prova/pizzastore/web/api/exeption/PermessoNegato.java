package it.prova.pizzastore.web.api.exeption;

public class PermessoNegato extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public PermessoNegato() {
	}

	public PermessoNegato(String message) {
		super(message);
	}

}
