#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import ${package}.webservice.recurso.ElementoDespesaController;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class ElementoDespesaControllerTests {
	@Autowired
	private ElementoDespesaController elementoDespesaController;

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void contextLoads() {
		assertNotNull(elementoDespesaController);
	}

	@Test
	public void buscaEmTodosRetornaUmaLista() throws Exception {

		String resultadoRequisicao = this.restTemplate.getForObject("http://localhost:" + port + "/rest/v1/elementosDespesa",
				String.class);

		assertNotNull(resultadoRequisicao);
	}

}
