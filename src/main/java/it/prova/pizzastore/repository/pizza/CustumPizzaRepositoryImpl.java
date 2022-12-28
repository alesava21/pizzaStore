package it.prova.pizzastore.repository.pizza;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;

import it.prova.pizzastore.model.Pizza;

public class CustumPizzaRepositoryImpl implements CustumPizzaRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Pizza> findyExample(Pizza example) {

		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder("select p from Pizza p where p.id = p.id ");

		if (StringUtils.isNotEmpty(example.descrizione())) {
			whereClauses.add(" p.descrizione  like :descrizione ");
			paramaterMap.put("descrizione", "%" + example.descrizione() + "%");
		}

		if (StringUtils.isNotEmpty(example.ingredienti())) {
			whereClauses.add(" p.ingredienti  like :ingredienti ");
			paramaterMap.put("ingredienti", "%" + example.ingredienti() + "%");
		}

		if (example.prezzoBase() != null) {
			whereClauses.add(" p.prezzoBase in :prezzoBase ");
			paramaterMap.put("prezzoBase", "%" + example.prezzoBase() + "%");
		}

		queryBuilder.append(!whereClauses.isEmpty() ? " and " : "");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<Pizza> typedQuery = entityManager.createQuery(queryBuilder.toString(), Pizza.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}

		return typedQuery.getResultList();

	}

}
