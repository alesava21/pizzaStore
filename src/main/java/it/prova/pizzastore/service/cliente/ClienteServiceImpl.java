package it.prova.pizzastore.service.cliente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.pizzastore.model.Cliente;
import it.prova.pizzastore.repository.cliente.ClienteRepository;

@Service
@Transactional(readOnly = true)
public class ClienteServiceImpl implements ClienteService{
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public List<Cliente> listAll() {
		return (List<Cliente>) clienteRepository.findAll();
	}

	@Override
	public Cliente caricaSingoloCliente(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void aggiorna(Cliente clienteInstance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void InserisciNuovo(Cliente clienteInstance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rimuovi(Long idRemove) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public List<Cliente> findByExample(Cliente example) {
		// TODO Auto-generated method stub
		return null;
	}

}
