#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.dominio.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ${package}.base.dominio.modelo.Entidade;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa o modelo de domínio.
 * 
 * @author SEDES
 *
 */
@Entity
@Table(name = "NOME_TABELA", schema = "NOME_ESQUEMA")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EntidadeExemplo implements Entidade {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_TABELA")
    @SequenceGenerator(name = "SQ_TABELA", sequenceName = "SQ_TABELA")
    @Column(name = "SQ_ID")
    private Long id;
	
    @Column(name = "NM_ELEMENTO")
    private String nome;
}
