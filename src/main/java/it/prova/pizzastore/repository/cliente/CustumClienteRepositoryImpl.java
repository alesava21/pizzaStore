package it.prova.pizzastore.repository.cliente;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;

import it.prova.pizzastore.model.Cliente;

public class CustumClienteRepositoryImpl implements CustumClienteRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Cliente> findByExample(Cliente example) {
		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder("select c from Cliente c where c.id = c.id ");

		if (StringUtils.isNotEmpty(example.nome())) {
			whereClauses.add(" c.nome  like :nome ");
			paramaterMap.put("nome", "%" + example.nome() + "%");
		}

		if (StringUtils.isNotEmpty(example.cognome())) {
			whereClauses.add(" c.cognome  like :cognome ");
			paramaterMap.put("cognome", "%" + example.cognome() + "%");
		}

		if (StringUtils.isNotEmpty(example.indirizzo())) {
			whereClauses.add(" c.indirizzo  like :indirizzo ");
			paramaterMap.put("indirizzo", "%" + example.indirizzo() + "%");
		}
		
		queryBuilder.append(!whereClauses.isEmpty() ? " and " : "");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<Cliente> typedQuery = entityManager.createQuery(queryBuilder.toString(), Cliente.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}

		return typedQuery.getResultList();

	}

}
