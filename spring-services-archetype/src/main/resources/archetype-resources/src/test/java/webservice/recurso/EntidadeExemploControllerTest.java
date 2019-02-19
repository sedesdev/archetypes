#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.webservice.recurso;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ${groupId}.base.infra.spring.webservice.exception.ItemNaoEncontradoException;
import ${package}.dominio.modelo.EntidadeExemplo;
import ${package}.dominio.servico.EntidadeExemploService;
import ${package}.webservice.info.EntidadesExemploInfo;

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
