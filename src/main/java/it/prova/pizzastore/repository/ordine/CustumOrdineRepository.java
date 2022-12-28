package it.prova.pizzastore.repository.ordine;

import java.util.List;

import it.prova.pizzastore.model.Ordine;

public interface CustumOrdineRepository {
	
	List<Ordine> findyExample(Ordine example);

}
