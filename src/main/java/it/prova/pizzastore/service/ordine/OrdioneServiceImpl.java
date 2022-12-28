package it.prova.pizzastore.service.ordine;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import it.prova.pizzastore.model.Ordine;
import it.prova.pizzastore.repository.ordine.OrdineRepository;

public class OrdioneServiceImpl implements OrdineService{
	
	@Autowired
	private OrdineRepository ordineRepository;

	@Override
	public List<Ordine> listAll() {
		return (List<Ordine>) ordineRepository.findAll();
	}

	@Override
	public Ordine caricaSingoloOrdine(Long id) {
		return ordineRepository.findById(id).orElse(null);
	}
	
	@Override
	public Ordine caricaSingoloOrdineEager(Long id) {
		return ordineRepository.findByIdEager(id).orElse(null);
	}

	@Override
	public void aggiorna(Ordine ordineInstance) {
		 ordineRepository.save(ordineInstance);
		
	}

	@Override
	public void inserisciNuovo(Ordine ordineInstance) {
		ordineRepository.save(ordineInstance);
		
	}

	@Override
	public void elimina(Long idRemove) {
		ordineRepository.deleteById(idRemove);
		
	}

	@Override
	public List<Ordine> findByExample(Ordine example) {
		// TODO Auto-generated method stub
		return null;
	}

}
