#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.dominio.modelo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ${groupId}.base.dominio.modelo.Entidade;
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
@Table(name = "ELEMENTO", schema = "ADMGESTOC")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ElementoDespesa implements Entidade {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_ELEMENTO")
    @SequenceGenerator(name = "SQ_ELEMENTO", sequenceName = "SQ_ELEMENTO")
    @Column(name = "SQ_ELEMENTO")
    private Long id;

    @Column(name = "NM_ELEMENTO")
    private String nome;

    @Column(name = "TP_ORIGEM_DADOS")
    private Integer tipoOrigemDados;

    @OneToMany(mappedBy = "elementoDespesa", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<SubElementoDespesa> subElementos;
}
