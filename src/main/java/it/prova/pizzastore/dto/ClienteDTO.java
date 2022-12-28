package it.prova.pizzastore.dto;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;

import it.prova.pizzastore.model.Cliente;
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
public class ClienteDTO {

	private Long id;

	@NotBlank(message = "{nome.notblank}")
	private String nome;

	@NotBlank(message = "{cognome.notblank}")
	private String cognome;

	@NotBlank(message = "{indirizzo.notblank}")
	private String indirizzo;

	private Boolean attivo;

	public Cliente buildClienteModel() {
		return Cliente.builder().id(id).nome(nome).cognome(cognome).indirizzo(indirizzo).attivo(attivo).build();
	}

	public static ClienteDTO buildClienteDTOFromModel(Cliente clienteModel) {
		return ClienteDTO.builder().id(clienteModel.id()).nome(clienteModel.nome()).cognome(clienteModel.cognome())
				.attivo(clienteModel.attivo()).build();
	}

	public static List<ClienteDTO> createClienteDTOListFromModelList(List<Cliente> modelListInput) {
		return modelListInput.stream().map(clienteEntity -> {
			ClienteDTO result = ClienteDTO.buildClienteDTOFromModel(clienteEntity);
			return result;
		}).collect(Collectors.toList());
	}

}
