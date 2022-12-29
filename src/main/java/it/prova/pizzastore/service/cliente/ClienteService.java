package it.prova.pizzastore.service.cliente;

import java.util.List;

import it.prova.pizzastore.model.Cliente;

public interface ClienteService {
	
	public List<Cliente> listAll();	
	
	public Cliente caricaSingoloCliente(Long id);
	
	public Cliente aggiorna(Cliente clienteInstance);
	
	public Cliente InserisciNuovo(Cliente clienteInstance);
	
	public void rimuovi(Long idRemove);
	
	public List<Cliente> findByExample(Cliente example);
	
	public void cambiaAbilitazione(Long id);

}
