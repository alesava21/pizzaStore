package it.prova.pizzastore.service.pizza;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.pizzastore.model.Pizza;
import it.prova.pizzastore.repository.pizza.PizzaRepository;
import it.prova.pizzastore.web.api.exeption.PizzaNotFound;

@Service
@Transactional(readOnly = true)
public class PizzaServiceImpl implements PizzaService {

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
	public Pizza aggiorna(Pizza pizzaInstance) {
		Pizza pizzaReload = pizzaRepository.findById(pizzaInstance.id()).orElse(null);

		if (pizzaReload == null) {
			throw new PizzaNotFound("Non e stato possibile trovare nessuna pizza con questo id");

		}

		pizzaReload.descrizione(pizzaInstance.descrizione());
		pizzaReload.ingredienti(pizzaInstance.ingredienti());
		pizzaReload.prezzoBase(pizzaInstance.prezzoBase());

		return pizzaRepository.save(pizzaReload);

	}

	@Override
	public Pizza inserisciNuovo(Pizza pizzaInstance) {

		pizzaInstance.attivo(true);
		return pizzaRepository.save(pizzaInstance);

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

	@Override
	public void calbiaAbilitation(Long id) {
		Pizza pizzaInstant = caricaSigolaPizza(id);
		if (pizzaInstant == null)
			throw new PizzaNotFound("Non e stato possibile trovare nessuna pizza con questo id");

		if (pizzaInstant.attivo()) {
			pizzaInstant.attivo(false);

		} else {
			pizzaInstant.attivo(true);
		}

	}

}
