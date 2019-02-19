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

import ${package}.dominio.modelo.EntidadeExemplo;

/**
 * Representa a implementação de um repositorio Customizado.
 * Deve seguir a nomenclatura com o sufixo "Impl"
 * @author SEDES
 */
@Repository
@Transactional(readOnly = true)
public class EntidadeExemploRepositoryImpl implements EntidadeExemploRepositoryCustom {

	@PersistenceContext
	EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<EntidadeExemplo> recuperarEntidadeExemploPor(String nome) {
		Query query = entityManager.createNativeQuery(
				"SELECT ed.* FROM NOME_ESQUEMA.NOME_TABELA ed " +
		        "WHERE ed.NM_ELEMENTO LIKE ?",
				EntidadeExemplo.class);
		query.setParameter(1, nome + "%");
		return (List<EntidadeExemplo>) query.getResultList();
	}

}
