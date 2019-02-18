#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.dominio.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ${groupId}.base.dominio.modelo.Entidade;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa o modelo de domínio.
 * @author SEDES
 *
 */
@Entity
@Table(name="SUBELEMENTO", schema="ADMGESTOC")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubElementoDespesa implements Entidade {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SQ_SUBELEMENTO")
	@SequenceGenerator(name="SQ_SUBELEMENTO", sequenceName="SQ_SUBELEMENTO")
	@Column(name="SQ_SUBELEMENTO")
	private Long id;
	
	@Column(name="NM_SUBELEMENTO")
	private String nome;
	
	@Column(name="TP_ORIGEM_DADOS")
    private Integer tipoOrigemDados;
	
	@Column(name="NR_CODIGO")
    private String codigo;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="SQ_ELEMENTO")
    private ElementoDespesa elementoDespesa;
}


