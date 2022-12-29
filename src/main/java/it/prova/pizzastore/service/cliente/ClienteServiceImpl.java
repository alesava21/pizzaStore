package it.prova.pizzastore.service.cliente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.pizzastore.model.Cliente;
import it.prova.pizzastore.repository.cliente.ClienteRepository;
import it.prova.pizzastore.web.api.exeption.ClienteNotFound;

@Service
@Transactional(readOnly = true)
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public List<Cliente> listAll() {
		return (List<Cliente>) clienteRepository.findAll();
	}

	@Override
	public Cliente caricaSingoloCliente(Long id) {
		return clienteRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Cliente aggiorna(Cliente clienteInstance) {
		Cliente clienteReloadCliente = clienteRepository.findById(clienteInstance.id()).orElse(null);
		if (clienteReloadCliente == null) {
			throw new ClienteNotFound("Cliente non trovato");

		}

		clienteReloadCliente.nome(clienteInstance.nome());
		clienteReloadCliente.cognome(clienteInstance.cognome());
		clienteReloadCliente.indirizzo(clienteInstance.indirizzo());

		return clienteRepository.save(clienteInstance);

	}

	@Override
	@Transactional
	public Cliente InserisciNuovo(Cliente clienteInstance) {

		return clienteRepository.save(clienteInstance);

	}

	@Override
	@Transactional
	public void rimuovi(Long idRemove) {
		Cliente clienteInstance = clienteRepository.findById(idRemove).orElse(null);

		clienteInstance.attivo(false);

		clienteRepository.save(clienteInstance);
	}

	@Override
	public List<Cliente> findByExample(Cliente example) {
		return clienteRepository.findByExample(example);
	}

	@Override
	@Transactional
	public void cambiaAbilitazione(Long id) {
		Cliente clienteInstance = caricaSingoloCliente(id);
		if (clienteInstance == null)
			throw new ClienteNotFound("Elemento non trovato.");

		if (clienteInstance.attivo()) {
			clienteInstance.attivo(false);
		} else {
			clienteInstance.attivo(true);
		}
		
	}

}
