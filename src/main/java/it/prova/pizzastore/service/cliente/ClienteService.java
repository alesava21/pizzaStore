package it.prova.pizzastore.service.cliente;

import java.util.List;

import it.prova.pizzastore.model.Cliente;

public interface ClienteService {
	
	public List<Cliente> listAll();	
	
	public Cliente caricaSingoloCliente(Long id);
	
	public void aggiorna(Cliente clienteInstance);
	
	public void InserisciNuovo(Cliente clienteInstance);
	
	public void rimuovi(Long idRemove);
	
	public List<Cliente> findByExample(Cliente example);

}
