package it.prova.pizzastore.service.pizza;

import java.util.List;

import it.prova.pizzastore.model.Pizza;

public interface PizzaService {
	
	public List<Pizza> listAll();
	
	public Pizza caricaSigolaPizza(Long id);
	
	public void aggiorna(Pizza pizzaInstance);
	
	public void inserisciNuovo(Pizza pizzaInstance);
	
	public void elimina(Long id);
	
	public List<Pizza> findByExample(Pizza example);

}
