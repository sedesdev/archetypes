#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.dominio.servico;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ${package}.dominio.modelo.EntidadeExemplo;
import ${package}.dominio.repositorio.EntidadeExemploRepository;

public class EntidadeExemploServiceTest {
    
    private EntidadeExemploService entidadeExemploService;
    
    @Mock
    private EntidadeExemploRepository entidadeExemploRepository;
    
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        entidadeExemploService = new EntidadeExemploService(entidadeExemploRepository);
    }

    @Test
    public void testRecuperarTodos() {
        
        EntidadeExemplo elemento = new EntidadeExemplo();
        List<EntidadeExemplo> elementos = new ArrayList<>();
        elementos.add(elemento);
        
        when(entidadeExemploRepository.recuperarTodos()).thenReturn(elementos);
                
        List<EntidadeExemplo> elementos2 = entidadeExemploService.recuperarTodos();
        assertEquals(1, elementos2.size());
        
        verify(entidadeExemploRepository, times(1)).recuperarTodos();
    }

}
