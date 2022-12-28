package it.prova.pizzastore.dto;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;

import it.prova.pizzastore.model.Ruolo;
import it.prova.pizzastore.model.StatoUtente;
import it.prova.pizzastore.model.Utente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UtenteDTO {
	
	private Long id;

	@NotBlank(message = "{username.notblank}")
	@Size(min = 3, max = 15, message = "Il valore inserito '${validatedValue}' deve essere lungo tra {min} e {max} caratteri")
	private String username;

	@NotBlank(message = "{password.notblank}")
	@Size(min = 8, max = 15, message = "Il valore inserito deve essere lungo tra {min} e {max} caratteri")
	private String password;

	private String confermaPassword;

	@NotBlank(message = "{nome.notblank}")
	private String nome;

	@NotBlank(message = "{cognome.notblank}")
	private String cognome;

	private LocalDate dateCreated;

	private StatoUtente stato;

	private Long[] ruoliIds;
	
	
	
	public Utente buildutenteModel(boolean includeIdRoles) {
		Utente result = Utente.builder()
				.id(id)
				.username(username)
				.password(password)
				.nome(nome)
				.cognome(cognome)
				.dateCreated(dateCreated)
				.stato(stato)
				.build();
		
		if (includeIdRoles && ruoliIds != null)
			result.ruoli(Arrays.asList(ruoliIds).stream().map(id -> Ruolo.builder().id(id).build()).collect(Collectors.toSet()));

		return result;
				
	}
	
	//no password
	public UtenteDTO buildUtenteDTOFromModel(Utente utenteModel) {
		UtenteDTO result = UtenteDTO.builder()
				.id(utenteModel.id())
				.username(utenteModel.username())
				.nome(utenteModel.nome())
				.cognome(utenteModel.cognome())
				.stato(utenteModel.stato())
				.build();
		
		if (!utenteModel.ruoli().isEmpty())
			result.ruoliIds = utenteModel.ruoli().stream().map(r -> r.id()).collect(Collectors.toList())
					.toArray(new Long[] {});

		return result;
	}
	

}
