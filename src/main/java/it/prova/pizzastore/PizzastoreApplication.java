package it.prova.pizzastore;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.prova.pizzastore.model.Ruolo;
import it.prova.pizzastore.model.Utente;
import it.prova.pizzastore.service.ruolo.RuoloService;
import it.prova.pizzastore.service.utente.UtenteService;

@SpringBootApplication
public class PizzastoreApplication implements CommandLineRunner{
	
	@Autowired
	private RuoloService ruoloServiceInstance;
	
	@Autowired
	private UtenteService utenteServiceInstance;

	public static void main(String[] args) {
		SpringApplication.run(PizzastoreApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", Ruolo.ROLE_ADMIN) == null) {
			ruoloServiceInstance
					.inserisciNuovo(Ruolo.builder().descrizione("Administrator").codice(Ruolo.ROLE_ADMIN).build());
		}

		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Fattorino", Ruolo.ROLE_FATTORINO) == null) {
			ruoloServiceInstance
					.inserisciNuovo(Ruolo.builder().descrizione("Fattorino").codice(Ruolo.ROLE_FATTORINO).build());
		}
		
		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Pizzaiolo", Ruolo.ROLE_PIZZAIOLO) == null) {
			ruoloServiceInstance
					.inserisciNuovo(Ruolo.builder().descrizione("Pizzaiolo").codice(Ruolo.ROLE_PIZZAIOLO).build());
		}
		
		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Proprietario", Ruolo.ROLE_PROPRIETARIO) == null) {
			ruoloServiceInstance
					.inserisciNuovo(Ruolo.builder().descrizione("Proprietario").codice(Ruolo.ROLE_PROPRIETARIO).build());
		}

		if (utenteServiceInstance.findByUsername("admin") == null) {
			Utente admin = Utente.builder()
					.nome("mario")
					.cognome("rossi")
					.username("admin")
					.password("admin")
					.dateCreated(LocalDate.now())
					.build();
			admin.ruoli().add(ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", Ruolo.ROLE_ADMIN));
			utenteServiceInstance.inserisciNuovo(admin);
			utenteServiceInstance.changeUserAbilitation(admin.id());
		}

		if (utenteServiceInstance.findByUsername("fattorino") == null) {
			Utente fattorino = Utente.builder()
					.nome("michel")
					.cognome("mardino")
					.username("fattorino")
					.password("fattorino")
					.dateCreated(LocalDate.now())
					.build();
			fattorino.ruoli()
					.add(ruoloServiceInstance.cercaPerDescrizioneECodice("Fattorino", Ruolo.ROLE_FATTORINO));
			utenteServiceInstance.inserisciNuovo(fattorino);
			utenteServiceInstance.changeUserAbilitation(fattorino.id());
		}
		
		if (utenteServiceInstance.findByUsername("pizzaiolo") == null) {
			Utente pizzaiolo = Utente.builder()
					.nome("roberto")
					.cognome("esposito")
					.username("pizzaiolo")
					.password("pizzaiolo")
					.dateCreated(LocalDate.now())
					.build();
			pizzaiolo.ruoli()
					.add(ruoloServiceInstance.cercaPerDescrizioneECodice("pizzaiolo", Ruolo.ROLE_PIZZAIOLO));
			utenteServiceInstance.inserisciNuovo(pizzaiolo);
			utenteServiceInstance.changeUserAbilitation(pizzaiolo.id());
		}
		
		if (utenteServiceInstance.findByUsername("ceo") == null) {
			Utente proprietario = Utente.builder()
					.nome("alessandro")
					.cognome("sava")
					.username("ceo")
					.password("ceo")
					.dateCreated(LocalDate.now())
					.build();
			proprietario.ruoli()
					.add(ruoloServiceInstance.cercaPerDescrizioneECodice("Proprietario", Ruolo.ROLE_PROPRIETARIO));
			utenteServiceInstance.inserisciNuovo(proprietario);
			utenteServiceInstance.changeUserAbilitation(proprietario.id());
		}
		
	}

}
