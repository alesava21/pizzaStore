package it.prova.pizzastore.dto;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

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
public class PizzaDTO {

	private Long id;

	@NotBlank(message = "{descrizione.notblank}")
	private String descrizione;

	@NotBlank(message = "{ingredienti.notblank}")
	private String ingredienti;

	@NotNull(message = "{prezzobase.notnull}")
	private Integer prezzoBase;

	private Boolean attivo;

	public Pizza buildPizzaModel() {
		return Pizza.builder().id(id).descrizione(descrizione).ingredienti(ingredienti).prezzoBase(prezzoBase)
				.attivo(attivo).build();
	}

	public static PizzaDTO buildPizzaDTOFromModel(Pizza pizzaModel) {
		return PizzaDTO.builder().id(pizzaModel.id()).descrizione(pizzaModel.descrizione())
				.ingredienti(pizzaModel.ingredienti()).prezzoBase(pizzaModel.prezzoBase()).attivo(pizzaModel.attivo())
				.build();
	}

	public static List<PizzaDTO> createPizzaDTOListFromModelList(List<Pizza> modelListInput) {
		return modelListInput.stream().map(pizzaEntity -> {
			PizzaDTO result = PizzaDTO.buildPizzaDTOFromModel(pizzaEntity);
			return result;
		}).collect(Collectors.toList());
	}

}
