package it.prova.pizzastore.web.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.prova.pizzastore.dto.PizzaDTO;
import it.prova.pizzastore.model.Pizza;
import it.prova.pizzastore.service.pizza.PizzaService;
import it.prova.pizzastore.web.api.exeption.ClienteNotFound;
import it.prova.pizzastore.web.api.exeption.PizzaNotFound;

@RestController
@RequestMapping("/api/pizza")
public class PizzaController {

	@Autowired
	private PizzaService pizzaService;

	@GetMapping
	public List<PizzaDTO> listAll() {
		return PizzaDTO.createPizzaDTOListFromModelList(pizzaService.listAll());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PizzaDTO inserisci(@Valid @RequestBody PizzaDTO pizza) {
		if (pizza.getId() != null)
			throw new PizzaNotFound("non e stato possibile trovare nessuna pizza con questo id" + pizza.getId());

		Pizza result = pizzaService.inserisciNuovo(pizza.buildPizzaModel());
		return PizzaDTO.buildPizzaDTOFromModel(result);
	}

	@GetMapping("/{id}")
	public PizzaDTO findById(@PathVariable(value = "id", required = true) long id) {
		Pizza pizza = pizzaService.caricaSigolaPizza(id);

		if (pizza == null)
			throw new ClienteNotFound("Pizza not found con id: " + id);

		return PizzaDTO.buildPizzaDTOFromModel(pizza);
	}

	@DeleteMapping
	public void elimina(@PathVariable(value = "id", required = true) long id) {
		pizzaService.elimina(id);
	}
	
	@GetMapping("changeAbilitation/{id}")
	public void changeAbilitation(@PathVariable(value = "id", required = true) long id) {
		pizzaService.calbiaAbilitation(id);
		
	}

	@PutMapping("/{id}")
	public PizzaDTO update(@Valid @RequestBody PizzaDTO pizzaInput, @PathVariable(required = true) Long id) {
		Pizza pizza = pizzaService.caricaSigolaPizza(id);

		if (pizza == null)
			throw new ClienteNotFound("Cliente not found con id: " + id);

		pizzaInput.setId(id);
		Pizza pizzaAggiornato = pizzaService.aggiorna(pizzaInput.buildPizzaModel());
		return PizzaDTO.buildPizzaDTOFromModel(pizzaAggiornato);
	}

	@PostMapping("/search")
	public List<PizzaDTO> search(@RequestBody PizzaDTO example) {
		return PizzaDTO.createPizzaDTOListFromModelList(pizzaService.findByExample(example.buildPizzaModel()));
	}

}
