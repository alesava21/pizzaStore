package it.prova.pizzastore.service.cliente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.pizzastore.model.Cliente;
import it.prova.pizzastore.repository.cliente.ClienteRepository;

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
	public void aggiorna(Cliente clienteInstance) {
		clienteRepository.save(clienteInstance);

	}

	@Override
	@Transactional
	public void InserisciNuovo(Cliente clienteInstance) {
		clienteRepository.save(clienteInstance);

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

}
