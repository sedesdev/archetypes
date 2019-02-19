#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.dominio.modelo;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class EntidadeExemploTest {

    EntidadeExemplo entidadeExemplo;

    @Before
    public void setup() {
        entidadeExemplo = new EntidadeExemplo();
    }
    
    @Test
    public void testGetId() {
        Long id = 11L;
        entidadeExemplo.setId(id);
        assertEquals(id, entidadeExemplo.getId());
    }

    @Test
    public void testGetNome() {
    }

}
