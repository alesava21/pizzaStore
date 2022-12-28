package it.prova.pizzastore.service.ordine;

import java.util.List;

import it.prova.pizzastore.model.Ordine;

public interface OrdineService {
	
	public List<Ordine> listAll();
	
	public Ordine caricaSingoloOrdine(Long id);
	
	public Ordine caricaSingoloOrdineEager(Long id);
	
	public Ordine aggiorna (Ordine ordineInstance);
	
	public Ordine inserisciNuovo(Ordine ordineInstance);
	
	public void elimina(Long idRemove);
	
	public List<Ordine> findByExample(Ordine example);
	
	public Integer calcolaPrezzoOrdine(Long idOrdine);

}
