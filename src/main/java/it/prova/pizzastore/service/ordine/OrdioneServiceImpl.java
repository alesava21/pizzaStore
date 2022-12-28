package it.prova.pizzastore.service.ordine;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.pizzastore.model.Ordine;
import it.prova.pizzastore.repository.ordine.OrdineRepository;
import it.prova.pizzastore.web.api.exeption.OrdineNotFound;

@Service
@Transactional(readOnly = true)
public class OrdioneServiceImpl implements OrdineService {

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
	@Transactional
	public Ordine aggiorna(Ordine ordineInstance) {

		Ordine ordineReload = ordineRepository.findById(ordineInstance.id()).orElse(null);

		if (ordineReload == null) {
			throw new OrdineNotFound("Non e stato possibile trovate un ordine con questo id");
		}

		ordineReload.codice(ordineInstance.codice());
		ordineReload.data(ordineInstance.data());
		ordineReload.cliente(ordineInstance.cliente());
		ordineReload.fattorino(ordineInstance.fattorino());
		ordineReload.pizze(ordineInstance.pizze());
		ordineReload.costoTotale(calcolaPrezzoOrdine(ordineInstance.id()));
		return ordineRepository.save(ordineReload);

	}

	@Override
	@Transactional
	public Ordine inserisciNuovo(Ordine ordineInstance) {

		ordineInstance.closed(false);
		ordineInstance.costoTotale(calcolaPrezzoOrdine(ordineInstance.id()));
		return ordineRepository.save(ordineInstance);

	}

	@Override
	public void elimina(Long idRemove) {
		ordineRepository.deleteById(idRemove);

	}

	@Override
	@Transactional
	public List<Ordine> findByExample(Ordine example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer calcolaPrezzoOrdine(Long idOrdine) {
		return ordineRepository.calcolaSommaPrezzi(idOrdine);
	}

}
