package it.prova.pizzastore.dto;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import it.prova.pizzastore.model.Ordine;
import it.prova.pizzastore.model.Pizza;
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
public class OrdineDTO {

	private Long id;

	@NotNull(message = "{data.notnull}")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate data;

	@NotBlank(message = "{codice.notblank}")
	private String codice;

	private Integer costoTotale;

	private Boolean closed;

	@NotNull(message = "{pizze.notnull}")
	private Long[] pizzaIds;

	@NotNull(message = "{fattorino.notnull}")
	private UtenteDTO fattorino;

	@NotNull(message = "{cliente.notnull}")
	private ClienteDTO cliente;

	public Ordine buildOrdineModel() {
		Ordine result = Ordine.builder().id(id).data(data).codice(codice).costoTotale(costoTotale).closed(closed)
				.build();
		if (pizzaIds != null)
			result.pizze(Arrays.asList(pizzaIds).stream().map(id -> Pizza.builder().id(id).build())
					.collect(Collectors.toSet()));
		if (fattorino != null)
			result.fattorino(fattorino.buildUtenteModel(false));
		if (this.cliente != null)
			result.cliente(cliente.buildClienteModel());

		return result;
	}

	public static OrdineDTO buildOrdineDTOFromModel(Ordine ordineModel) {
		OrdineDTO result = OrdineDTO.builder().id(ordineModel.id()).data(ordineModel.data())
				.codice(ordineModel.codice()).costoTotale(ordineModel.costoTotale()).closed(ordineModel.closed())
				.build();

		if (!ordineModel.pizze().isEmpty())
			result.pizzaIds = ordineModel.pizze().stream().map(p -> p.id()).collect(Collectors.toList())
					.toArray(new Long[] {});

		if (!ordineModel.fattorino().equals(null))
			result.setFattorino(UtenteDTO.buildUtenteDTOFromModel(ordineModel.fattorino()));

		if (!ordineModel.cliente().equals(null))
			result.setCliente(ClienteDTO.buildClienteDTOFromModel(ordineModel.cliente()));

		return result;
	}

	public static List<OrdineDTO> createOrdineDTOListFromModelList(List<Ordine> modelListInput) {
		return modelListInput.stream().map(ordineEntity -> {
			OrdineDTO result = OrdineDTO.buildOrdineDTOFromModel(ordineEntity);
			return result;
		}).collect(Collectors.toList());
	}

}
