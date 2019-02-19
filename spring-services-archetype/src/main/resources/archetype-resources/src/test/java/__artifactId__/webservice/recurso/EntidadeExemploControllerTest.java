#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.webservice.recurso;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ${package}.base.infra.spring.webservice.exception.ItemNaoEncontradoException;
import ${package}.${artifactId}.dominio.modelo.EntidadeExemplo;
import ${package}.${artifactId}.dominio.servico.EntidadeExemploService;
import ${package}.${artifactId}.webservice.info.EntidadesExemploInfo;

public class EntidadeExemploControllerTest {

    @Mock
    private EntidadeExemploService entidadeExemploService;
    
    private EntidadeExemploController entidadeExemploController;
    
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        entidadeExemploController = new EntidadeExemploController(entidadeExemploService);
    }
    
    @Test
    public void testConsulta() throws ItemNaoEncontradoException {
        
        List<EntidadeExemplo> elementos = new ArrayList<>();
        elementos.add(new EntidadeExemplo());
        
        when(entidadeExemploService.recuperarPeloNome("nomeelemento")).thenReturn(elementos);
        EntidadesExemploInfo entidadesExemploInfo = entidadeExemploController.consulta(Optional.of("nomeelemento"));
        
        assertEquals(1, entidadesExemploInfo.getList().size());
    }

}
