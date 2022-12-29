package it.prova.pizzastore.web.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.prova.pizzastore.dto.ClienteDTO;
import it.prova.pizzastore.model.Cliente;
import it.prova.pizzastore.service.cliente.ClienteService;
import it.prova.pizzastore.web.api.exeption.ClienteNotFound;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@GetMapping
	public List<ClienteDTO> listAll() {
		return ClienteDTO.createClienteDTOListFromModelList(clienteService.listAll());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ClienteDTO inserisci(@Valid @RequestBody ClienteDTO cliente) {
		if (cliente.getId() != null)
			throw new ClienteNotFound("non e stato possibile trovare nessun cliente con questo id");

		Cliente result = clienteService.InserisciNuovo(cliente.buildClienteModel());
		return ClienteDTO.buildClienteDTOFromModel(result);
	}

	@GetMapping("/{id}")
	public ClienteDTO findById(@PathVariable(value = "id", required = true) long id) {
		Cliente cliente = clienteService.caricaSingoloCliente(id);

		if (cliente == null)
			throw new ClienteNotFound("Cliente not found con id: " + id);

		return ClienteDTO.buildClienteDTOFromModel(cliente);
	}

	@PutMapping("/{id}")
	public ClienteDTO update(@Valid @RequestBody ClienteDTO clienteInput, @PathVariable(required = true) Long id) {
		Cliente cliente = clienteService.caricaSingoloCliente(id);

		if (cliente == null)
			throw new ClienteNotFound("Cliente not found con id: " + id);

		clienteInput.setId(id);
		Cliente clienteAggiornato = clienteService.aggiorna(clienteInput.buildClienteModel());
		return ClienteDTO.buildClienteDTOFromModel(clienteAggiornato);
	}

	@PostMapping("/search")
	public List<ClienteDTO> search(@RequestBody ClienteDTO example) {
		return ClienteDTO.createClienteDTOListFromModelList(clienteService.findByExample(example.buildClienteModel()));
	}

	@GetMapping("changeAbilitation/{id}")
	public void changeAbilitation(@PathVariable(value = "id", required = true) long id) {
		clienteService.cambiaAbilitazione(id);
	}

}
