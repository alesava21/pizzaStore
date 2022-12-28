package it.prova.pizzastore.service.pizza;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.pizzastore.model.Pizza;
import it.prova.pizzastore.repository.pizza.PizzaRepository;

@Service
@Transactional(readOnly = true)
public class PizzaServiceImpl implements PizzaService{
	
	@Autowired
	private PizzaRepository pizzaRepository;

	@Override
	public List<Pizza> listAll() {
		return (List<Pizza>) pizzaRepository.findAll();
	}

	@Override
	public Pizza caricaSigolaPizza(Long id) {
		return pizzaRepository.findById(id).orElse(null);
	}

	@Override
	public void aggiorna(Pizza pizzaInstance) {
		pizzaRepository.save(pizzaInstance);
		
	}

	@Override
	public void inserisciNuovo(Pizza pizzaInstance) {
		pizzaRepository.save(pizzaInstance);
		
	}

	@Override
	public void elimina(Long id) {
		pizzaRepository.deleteById(id);
	}

	@Override
	public List<Pizza> findByExample(Pizza example) {
		// TODO Auto-generated method stub
		return null;
	}

}
