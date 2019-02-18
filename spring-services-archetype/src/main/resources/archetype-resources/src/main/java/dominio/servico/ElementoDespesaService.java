#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.dominio.servico;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ${groupId}.base.infra.spring.webservice.exception.ItemNaoEncontradoException;
import ${package}.dominio.modelo.ElementoDespesa;
import ${package}.dominio.repositorio.ElementoDespesaRepository;
import ${package}.webservice.info.ElementoDespesaInfo;
import lombok.extern.slf4j.Slf4j;

/**
 * Classe que indica as regras de negócio Acessa os repositorios
 * 
 * @author SEDES
 *
 */
@Component
@Slf4j
public class ElementoDespesaService {

    private ElementoDespesaRepository repositorioElementoDespesa;

    public ElementoDespesaService(ElementoDespesaRepository repositorioElementoDespesa) {
        this.repositorioElementoDespesa = repositorioElementoDespesa;
    }

    public boolean validaSePodeExcluir(ElementoDespesa elemento) {
        log.info("Validando exclusao de elemento de despesa");
        return elemento.getId() > 200;
    }

    @Transactional
    public ElementoDespesa salvar(ElementoDespesa elementoDespesa) {
        return repositorioElementoDespesa
                .save(ElementoDespesa.builder().nome(elementoDespesa.getNome()).tipoOrigemDados(1).build());
    }

    @Transactional
    public ElementoDespesa salvar(ElementoDespesaInfo elementoDespesa) {
        return repositorioElementoDespesa
                .save(ElementoDespesa.builder().nome(elementoDespesa.getNome()).tipoOrigemDados(1).build());
    }

    @Transactional
    public void apagarPelo(Long id) throws ItemNaoEncontradoException {
        ElementoDespesa elemento = recuperarPelo(id);
        repositorioElementoDespesa.delete(elemento);
    }

    public List<ElementoDespesa> recuperarTodosMostrandoElementoMaterial() {
        List<ElementoDespesa> elementoMaterial = repositorioElementoDespesa.recuperarElementoDespesaPor("MATERIAL");
        log.info(elementoMaterial != null ? elementoMaterial.get(0).getNome()
                : "------------> ELEMENTO MATERIAL INEXISTENTE");

        return repositorioElementoDespesa.recuperarTodos();
    }

	public ElementoDespesa recuperarPelo(Long id) throws ItemNaoEncontradoException {
        return repositorioElementoDespesa.findById(id)
                .orElseThrow(() -> new ItemNaoEncontradoException("Elemento de despesa não encontrado"));
    }

    public List<ElementoDespesa> recuperarMostrandoElementoMaterial(String nome)  throws ItemNaoEncontradoException {
        List<ElementoDespesa> elementoMaterial = repositorioElementoDespesa.recuperarElementoDespesaPor("MATERIAL");
        log.info(elementoMaterial != null ? elementoMaterial.get(0).getNome()
                : "------------> ELEMENTO MATERIAL INEXISTENTE");

        return repositorioElementoDespesa.recuperarPeloNome(nome);
    }
}
