package it.prova.pizzastore.service.ordine;

import java.time.LocalDate;
import java.util.List;

import it.prova.pizzastore.model.Cliente;
import it.prova.pizzastore.model.Ordine;

public interface OrdineService {

	public List<Ordine> listAll();

	public Ordine caricaSingoloOrdine(Long id);

	public Ordine caricaSingoloOrdineEager(Long id);

	public Ordine aggiorna(Ordine ordineInstance);

	public Ordine inserisciNuovo(Ordine ordineInstance);

	public void elimina(Long idRemove);

	public List<Ordine> findByExample(Ordine example);

	public Integer ricaviTotaliBetween(LocalDate dataInizio, LocalDate dataFine);

	public void cambiaAbilitation(Long id);

	public Integer calcolaPrezzoOrdine(Long idOrdine);
	
	public Integer allOrdiniBetween(LocalDate dataInizio, LocalDate dataFine);
	
	public Integer pizzeOrdinateBetween(LocalDate dataInizio, LocalDate dataFine);
	
	public List<Ordine> ordiniPerFattorino(String username);
	
	public List<Cliente> clientiVirtuosiBetween(LocalDate dataInizio, LocalDate dataFine);


}
