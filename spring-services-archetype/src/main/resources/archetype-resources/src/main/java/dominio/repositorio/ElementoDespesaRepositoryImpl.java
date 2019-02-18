#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.dominio.repositorio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ${package}.dominio.modelo.ElementoDespesa;

/**
 * Representa a implementacao de um repositorio Customizado.
 * Deve seguir a nomenclatura com o sufixo "Impl"
 * @author SEDES
 */
@Repository
@Transactional(readOnly = true)
public class ElementoDespesaRepositoryImpl implements ElementoDespesaRepositoryCustom {

	@PersistenceContext
	EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<ElementoDespesa> recuperarElementoDespesaPor(String nome) {
		Query query = entityManager.createNativeQuery(
				"SELECT ed.* FROM ADMGESTOC.ELEMENTO ed " +
		        "WHERE ed.NM_ELEMENTO LIKE ?",
				ElementoDespesa.class);
		query.setParameter(1, nome + "%");
		return (List<ElementoDespesa>) query.getResultList();
	}

}
