package it.prova.pizzastore.service.cliente;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.pizzastore.model.Cliente;

@Service
@Transactional(readOnly = true)
public class ClienteServiceImpl implements ClienteService{

	@Override
	public List<Cliente> listAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cliente caricaSingoloCliente() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void aggiorna() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void InserisciNuovo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rimuovi() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Cliente> findByExample(Cliente example) {
		// TODO Auto-generated method stub
		return null;
	}

}
