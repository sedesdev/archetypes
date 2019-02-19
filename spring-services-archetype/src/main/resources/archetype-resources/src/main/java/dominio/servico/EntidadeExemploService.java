#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.dominio.servico;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ${groupId}.base.infra.spring.webservice.exception.ItemNaoEncontradoException;
import ${package}.dominio.modelo.EntidadeExemplo;
import ${package}.dominio.repositorio.EntidadeExemploRepository;
import ${package}.webservice.info.EntidadeExemploInfo;
import lombok.extern.slf4j.Slf4j;

/**
 * Classe que indica as regras de negócio Acessa os repositórios
 * 
 * @author SEDES
 *
 */
@Component
@Slf4j
public class EntidadeExemploService {

    private EntidadeExemploRepository repositorioEntidadeExemplo;

    public EntidadeExemploService(EntidadeExemploRepository repositorioEntidadeExemplo) {
        this.repositorioEntidadeExemplo = repositorioEntidadeExemplo;
    }

    public boolean validaSePodeExcluir(EntidadeExemplo elemento) {
        log.info("Validando exclusao de entidade exemplo");
        return elemento.getId() > 200;
    }

    @Transactional
    public EntidadeExemplo salvar(EntidadeExemplo entidadeExemplo) {
        return repositorioEntidadeExemplo
                .save(EntidadeExemplo.builder().nome(entidadeExemplo.getNome()).tipoOrigemDados(1).build());
    }

    @Transactional
    public EntidadeExemplo salvar(EntidadeExemploInfo entidadeExemplo) {
        return repositorioEntidadeExemplo
                .save(EntidadeExemplo.builder().nome(entidadeExemplo.getNome()).tipoOrigemDados(1).build());
    }

    @Transactional
    public void apagarPelo(Long id) throws ItemNaoEncontradoException {
        EntidadeExemplo elemento = recuperarPelo(id);
        repositorioEntidadeExemplo.delete(elemento);
    }

    public List<EntidadeExemplo> recuperarTodos() {
        return repositorioEntidadeExemplo.recuperarTodos();
    }

	public EntidadeExemplo recuperarPelo(Long id) throws ItemNaoEncontradoException {
        return repositorioEntidadeExemplo.findById(id)
                .orElseThrow(() -> new ItemNaoEncontradoException("Entidade exemplo não encontrado"));
    }

    public List<EntidadeExemplo> recuperarPeloNome(String nome)  throws ItemNaoEncontradoException {
        return repositorioEntidadeExemplo.recuperarPeloNome(nome);
    }
}
