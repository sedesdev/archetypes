#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.dominio.repositorio;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import ${package}.AppDesenv;
import ${package}.dominio.modelo.EntidadeExemplo;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ContextConfiguration(classes = { AppDesenv.class })
public class EntidadeExemploRepositoryTest {

    @Autowired
    EntidadeExemploRepository entidadeExemploRepository;

    @Before
    public void setup() {

    }

    @Test
    public void test() {
        List<EntidadeExemplo> elementos = entidadeExemploRepository.findAll();
        assertTrue(!elementos.isEmpty());
    }

}
