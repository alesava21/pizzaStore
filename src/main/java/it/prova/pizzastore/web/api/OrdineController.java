package it.prova.pizzastore.web.api;

import java.security.Principal;
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
import it.prova.pizzastore.dto.InformazioniOrdineDTO;
import it.prova.pizzastore.dto.OrdineDTO;
import it.prova.pizzastore.model.Ordine;
import it.prova.pizzastore.service.ordine.OrdineService;
import it.prova.pizzastore.web.api.exeption.ClienteNotFound;
import it.prova.pizzastore.web.api.exeption.OrdineNotFound;

@RestController
@RequestMapping("/api/ordine")
public class OrdineController {
	
	@Autowired
	private OrdineService ordineService;
	
	@GetMapping
	public List<OrdineDTO> listAll() {
		return OrdineDTO.createOrdineDTOListFromModelList(ordineService.listAll());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OrdineDTO inserisci(@Valid @RequestBody OrdineDTO ordine) {
		if (ordine.getId() != null)
			throw new OrdineNotFound("non e stato possibile trovare nessun ordine con questo id" + ordine.getId());

		Ordine result = ordineService.inserisciNuovo(ordine.buildOrdineModel());
		return OrdineDTO.buildOrdineDTOFromModel(result);
	}
	
	@GetMapping("/{id}")
	public OrdineDTO findById(@PathVariable(value = "id", required = true) long id) {
		Ordine ordine = ordineService.caricaSingoloOrdine(id);

		if (ordine == null)
			throw new OrdineNotFound("Ordine not found con id: " + id);

		return OrdineDTO.buildOrdineDTOFromModel(ordine);
	}
	
	@PutMapping("/{id}")
	public OrdineDTO update(@Valid @RequestBody OrdineDTO ordineInput, @PathVariable(required = true) Long id) {
		Ordine ordine = ordineService.caricaSingoloOrdine(id);

		if (ordine == null)
			throw new ClienteNotFound("Cliente not found con id: " + id);

		ordineInput.setId(id);
		Ordine ordineAggiornato = ordineService.aggiorna(ordineInput.buildOrdineModel());
		return OrdineDTO.buildOrdineDTOFromModel(ordineAggiornato);
	}
	
	@PostMapping("/search")
	public List<OrdineDTO> search(@RequestBody OrdineDTO example) {
		return OrdineDTO.createOrdineDTOListFromModelList(ordineService.findByExample(example.buildOrdineModel()));
	}
	
	@GetMapping("changeAbilitation/{id}")
	public void changeAbilitation(@PathVariable(value = "id", required = true) long id) {
		ordineService.cambiaAbilitation(id);
	}
	
	@PostMapping("/ricaviTotaliBetween")
	public Integer ricaviTotaliBetween(@Valid @RequestBody InformazioniOrdineDTO dateInput) {
		return ordineService.ricaviTotaliBetween(dateInput.getDataInizio(), dateInput.getDataFine());
	}
	
	@PostMapping("/ordiniTotaliBetween")
	public Integer ordiniTotaliBetween(@Valid @RequestBody InformazioniOrdineDTO dateInput) {
		return ordineService.allOrdiniBetween(dateInput.getDataInizio(), dateInput.getDataFine());
	}

	@PostMapping("/pizzeTotaliOrderedBetween")
	public Integer pizzeTotaliOrderedBetween(@Valid @RequestBody InformazioniOrdineDTO dateInput) {
		return ordineService.pizzeOrdinateBetween(dateInput.getDataInizio(), dateInput.getDataFine());
	}

	@PostMapping("/clientiVirtuosiWithOrdineBetween")
	public List<ClienteDTO> clientiVirtuosiWithOrdineBetween(@Valid @RequestBody InformazioniOrdineDTO dateInput) {
		return ClienteDTO.createClienteDTOListFromModelList(
				ordineService.clientiVirtuosiBetween(dateInput.getDataInizio(), dateInput.getDataFine()));
	}

	@GetMapping("/fattorino")
	public List<OrdineDTO> ordiniPerFattorino(Principal principal) {
		return OrdineDTO.createOrdineDTOListFromModelList(ordineService.ordiniPerFattorino(principal.getName()));
	}

}
