#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.dominio.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ${package}.dominio.modelo.ElementoDespesa;


/**
 * Interface que fará acesso aos dados.
 * Deve estender apenas interfaces
 * @author SEDES
 */
public interface ElementoDespesaRepository extends JpaRepository<ElementoDespesa, Long>, ElementoDespesaRepositoryCustom  {
	
	// Exemplo de consulta personaliza por apenas query
	@Query(value = "SELECT e FROM ElementoDespesa e")
	List<ElementoDespesa> recuperarTodos();

	@Query(value = "SELECT e FROM ElementoDespesa e where lower(e.nome) like '%' || lower(:nome) || '%'")
    List<ElementoDespesa> recuperarPeloNome(@Param("nome") String nome);

}
