package it.prova.pizzastore.repository.ordine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;

import it.prova.pizzastore.model.Ordine;

public class CustumOrdineRepositoryImpl implements CustumOrdineRepository{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Ordine> findyExample(Ordine example) {
		
		
		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder("select o from Ordine o left join o.pizze p where o.id = o.id");
		

		if (StringUtils.isNotEmpty(example.codice())) {
			whereClauses.add(" o.codice  like :codice ");
			paramaterMap.put("codice", "%" + example.codice() + "%");
		}
		
		if (example.data() != null) {
			whereClauses.add(" o.data  >= :data ");
			paramaterMap.put("data", "%" + example.data() + "%");
		}
		
		if (example.pizze() != null) {
			whereClauses.add(" p in :pizze ");
			paramaterMap.put("pizze", "%" + example.pizze() + "%");
		}
		
		if (example.costoTotale() != null) {
			whereClauses.add(" o.costoTotatel >= :costoTotale ");
			paramaterMap.put("costoTotale", "%" + example.costoTotale() + "%");
		}
		
		if (example.closed() != null) {
			whereClauses.add(" o.closed = :closed ");
			paramaterMap.put("closed", "%" + example.closed() + "%");
		}
		
		if (example.cliente() != null && example.cliente().id() != null) {
			whereClauses.add(" o.cliente.id = :idCliente ");
			paramaterMap.put("idCliente", "%" + example.cliente().id() + "%");
		}
		
		if (example.fattorino() != null && example.fattorino().id() != null) {
			whereClauses.add(" o.fattorino.id = :idFattorino ");
			paramaterMap.put("idFattorino", "%" + example.fattorino().id() + "%");
		}
		

		queryBuilder.append(!whereClauses.isEmpty() ? " and " : "");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<Ordine> typedQuery = entityManager.createQuery(queryBuilder.toString(), Ordine.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}

		return typedQuery.getResultList();
		
		
	}

}
