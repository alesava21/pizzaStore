package it.prova.pizzastore.service.cliente;

import java.util.List;

import it.prova.pizzastore.model.Cliente;

public interface ClienteService {
	
	public List<Cliente> listAll();
	
	public Cliente caricaSingoloCliente();
	
	public void aggiorna();
	
	public void InserisciNuovo();
	
	public void rimuovi();
	
	public List<Cliente> findByExample(Cliente example);

}
