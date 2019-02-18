#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.webservice.recurso;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ${groupId}.base.infra.spring.email.EmailService;
import ${groupId}.base.infra.spring.webservice.exception.ItemNaoEncontradoException;
import ${package}.dominio.modelo.ElementoDespesa;
import ${package}.dominio.servico.ElementoDespesaService;
import ${package}.webservice.info.ElementoDespesaInfo;
import ${package}.webservice.info.ElementosDespesaInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Controlador para acesso aos Elementos de Despesa via API REST. Cada método
 * representa uma operação que pode ser feita
 * 
 * @author SEDES
 *
 */
@Api(value = "API Elemento Despesa", produces = "application/json")
@RestController
@RequestMapping("/rest/v1/elementosDespesa")
public class ElementoDespesaController {

	private ElementoDespesaService elementoDespesaService;

	@Autowired
	public EmailService emailService;

	// Injecao de dependencia no Spring
	// É uma alternativa ao uso do Autowired
	public ElementoDespesaController(ElementoDespesaService elementoDespesaService) {
		this.elementoDespesaService = elementoDespesaService;
	}

	@ApiOperation(value = "Criação de elemento de despesa", response = ElementoDespesaInfo.class, 
	        produces = "application/json")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Elemento de despesa criado", response = ElementoDespesaInfo.class),
			@ApiResponse(code = 401, message = "Acesso não autorizado"),
			@ApiResponse(code = 403, message = "Proibido acesso ao recurso"),
			@ApiResponse(code = 404, message = "Elemento de despesa não encontrado"),
			@ApiResponse(code = 500, message = "Erro interno no servidor") })
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public ElementoDespesaInfo salvar(
			@ApiParam(value = "Dados do elemento de despesa a ser criado") 
			@RequestBody ElementoDespesaInfo elementoDespesaInfo) throws ItemNaoEncontradoException {

		ElementoDespesa elementoDespesa = elementoDespesaService.salvar(elementoDespesaInfo);

		ElementoDespesaInfo elementoInfo = new ElementoDespesaInfo(elementoDespesa,
				ControllerLinkBuilder.methodOn(this.getClass()).recuperar(elementoDespesa.getId()));

		emailService.enviarMensagemSimples("sedes@tre-se.jus.br", "rcardosom@gmail.com", "CRIAÇÃO DE USUÁRIO",
				"Usuário criado com sucesso");

		return elementoInfo;
	}

	@ApiOperation(value = "Retorna um elemento de despesa pelo ID", response = ElementoDespesaInfo.class, 
	        produces = "application/json")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Elemento de despesa retornado", response = ElementoDespesaInfo.class),
			@ApiResponse(code = 401, message = "Acesso não autorizado"),
			@ApiResponse(code = 403, message = "Proibido acesso ao recurso"),
			@ApiResponse(code = 404, message = "Elemento de despesa não encontrado"),
			@ApiResponse(code = 500, message = "Erro interno no servidor") })
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ElementoDespesaInfo recuperar(
			@ApiParam(value = "Identificador do elemento de despesa") @PathVariable("id") Long id)
			throws ItemNaoEncontradoException {

		return new ElementoDespesaInfo(elementoDespesaService.recuperarPelo(id),
				ControllerLinkBuilder.methodOn(this.getClass()).recuperar(id));
	}

	@ApiOperation(value = "Atualiza um elemento de despesa identificado pelo ID", 
	        notes = "Um cliente atualiza um elemento", response = ElementoDespesaInfo.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Elemento de despesa atualizado", response = ElementoDespesaInfo.class),
			@ApiResponse(code = 401, message = "Acesso não autorizado"),
			@ApiResponse(code = 403, message = "Proibido acesso ao recurso"),
			@ApiResponse(code = 404, message = "Elemento de despesa não encontrado para atualizar"),
			@ApiResponse(code = 500, message = "Erro interno no servidor") })
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizar(@ApiParam(value = "Identificador do elemento de despesa") @PathVariable("id") Long id,
			@ApiParam(value = "Dados do elemento de despesa a serem atualizados", 
			type = "ElementoDespesa") @RequestBody ElementoDespesaInfo elementoDespesa)
			throws ItemNaoEncontradoException {

		ElementoDespesa elemento = elementoDespesaService.recuperarPelo(id);
		elemento.setNome(elementoDespesa.getNome());
		elementoDespesaService.salvar(elemento);
	}

	@ApiOperation(value = "Consulta todos os elementos de despesa ou por parte do nome", 
	        response = ElementoDespesaInfo.class, produces = "application/json")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Lista de elementos de despesa retornado", 
			        response = ElementosDespesaInfo.class),
			@ApiResponse(code = 401, message = "Acesso não autorizado"),
			@ApiResponse(code = 403, message = "Proibido acesso ao recurso"),
			@ApiResponse(code = 404, message = "Elemento de despesa não encontrado"),
			@ApiResponse(code = 500, message = "Erro interno no servidor") })
	@GetMapping("")
	@ResponseStatus(HttpStatus.OK)
	public ElementosDespesaInfo consulta(
			@ApiParam(name = "nome", value = "Nome ou parte dele para busca do(s) elemento(s)", required = false) 
			@RequestParam("nome") Optional<String> nome)
			throws ItemNaoEncontradoException {
	    
		List<ElementoDespesa> elementosDespesa = null;
		ElementosDespesaInfo metodo = null;

		if (nome.isPresent()) {
			elementosDespesa = elementoDespesaService.recuperarMostrandoElementoMaterial(nome.get());
			metodo = ControllerLinkBuilder.methodOn(this.getClass()).consulta(nome);
		} else {
			elementosDespesa = elementoDespesaService.recuperarTodosMostrandoElementoMaterial();
			metodo = ControllerLinkBuilder.methodOn(this.getClass()).consulta(null);
		}

		return new ElementosDespesaInfo(elementosDespesa, metodo);
	}

	@ApiOperation(value = "Remove um elemento de despesa identificado pelo ID", response = ElementoDespesaInfo.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Elemento de despesa removido", response = ElementoDespesaInfo.class),
			@ApiResponse(code = 401, message = "Acesso não autorizado"),
			@ApiResponse(code = 403, message = "Proibido acesso ao recurso"),
			@ApiResponse(code = 404, message = "Elemento de despesa não encontrado para remover"),
			@ApiResponse(code = 500, message = "Erro interno no servidor") })
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@ApiParam(value = "Identificador do elemento de despesa") @PathVariable("id") Long id)
			throws ItemNaoEncontradoException {

		if (elementoDespesaService.validaSePodeExcluir(ElementoDespesa.builder().id(id).build())) {
			elementoDespesaService.apagarPelo(id);
		}
	}
}