package it.prova.pizzastore.service.ordine;

import java.util.List;

import it.prova.pizzastore.model.Ordine;

public interface OrdineService {
	
	public List<Ordine> listAll();
	
	public Ordine caricaSingoloOrdine(Long id);
	
	public Ordine caricaSingoloOrdineEager(Long id);
	
	public void aggiorna (Ordine ordineInstance);
	
	public void inserisciNuovo(Ordine ordineInstance);
	
	public void elimina(Long idRemove);
	
	public List<Ordine> findByExample(Ordine example);

}
