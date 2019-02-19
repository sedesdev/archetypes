#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.webservice.recurso;

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

import ${package}.base.infra.spring.email.EmailService;
import ${package}.base.infra.spring.webservice.exception.ItemNaoEncontradoException;
import ${package}.${artifactId}.dominio.modelo.EntidadeExemplo;
import ${package}.${artifactId}.dominio.servico.EntidadeExemploService;
import ${package}.${artifactId}.webservice.info.EntidadeExemploInfo;
import ${package}.${artifactId}.webservice.info.EntidadesExemploInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Controlador para acesso às entidades exemplo via API REST. Cada método
 * representa uma operação que pode ser feita
 * 
 * @author SEDES
 *
 */
@Api(value = "API Entidade Exemplo", produces = "application/json")
@RestController
@RequestMapping("/rest/v1/entidadesExemplo")
public class EntidadeExemploController {

	private EntidadeExemploService entidadeExemploService;

	@Autowired
	public EmailService emailService;

	// Injeção de dependência no Spring
	// É uma alternativa ao uso do Autowired
	public EntidadeExemploController(EntidadeExemploService entidadeExemploService) {
		this.entidadeExemploService = entidadeExemploService;
	}

	@ApiOperation(value = "Criação de entidade exemplo", response = EntidadeExemploInfo.class, 
	        produces = "application/json")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Entidade exemplo criada", response = EntidadeExemploInfo.class),
			@ApiResponse(code = 401, message = "Acesso não autorizado"),
			@ApiResponse(code = 403, message = "Proibido acesso ao recurso"),
			@ApiResponse(code = 404, message = "Entidade exemplo não encontrado"),
			@ApiResponse(code = 500, message = "Erro interno no servidor") })
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public EntidadeExemploInfo salvar(
			@ApiParam(value = "Dados da entidade exemplo a ser criado") 
			@RequestBody EntidadeExemploInfo entidadeExemploInfo) throws ItemNaoEncontradoException {

		EntidadeExemplo entidadeExemplo = entidadeExemploService.salvar(entidadeExemploInfo);

		EntidadeExemploInfo elementoInfo = new EntidadeExemploInfo(entidadeExemplo,
				ControllerLinkBuilder.methodOn(this.getClass()).recuperar(entidadeExemplo.getId()));

		emailService.enviarMensagemSimples("sedes@tre-se.jus.br", "sedes@tre-se.jus.br", "CRIAÇÃO DE USUÁRIO",
				"Usuário criado com sucesso");

		return elementoInfo;
	}

	@ApiOperation(value = "Retorna uma entidade exemplo pelo ID", response = EntidadeExemploInfo.class, 
	        produces = "application/json")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Entidade exemplo retornada", response = EntidadeExemploInfo.class),
			@ApiResponse(code = 401, message = "Acesso não autorizado"),
			@ApiResponse(code = 403, message = "Proibido acesso ao recurso"),
			@ApiResponse(code = 404, message = "Entidade exemplo não encontrado"),
			@ApiResponse(code = 500, message = "Erro interno no servidor") })
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public EntidadeExemploInfo recuperar(
			@ApiParam(value = "Identificador da entidade exemplo") @PathVariable("id") Long id)
			throws ItemNaoEncontradoException {

		return new EntidadeExemploInfo(entidadeExemploService.recuperarPelo(id),
				ControllerLinkBuilder.methodOn(this.getClass()).recuperar(id));
	}

	@ApiOperation(value = "Atualiza uma entidade exemplo identificada pelo ID", 
	        notes = "Um cliente atualiza um elemento", response = EntidadeExemploInfo.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Entidade exemplo atualizada", response = EntidadeExemploInfo.class),
			@ApiResponse(code = 401, message = "Acesso não autorizado"),
			@ApiResponse(code = 403, message = "Proibido acesso ao recurso"),
			@ApiResponse(code = 404, message = "Entidade exemplo não encontrado para atualizar"),
			@ApiResponse(code = 500, message = "Erro interno no servidor") })
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizar(@ApiParam(value = "Identificador da entidade exemplo") @PathVariable("id") Long id,
			@ApiParam(value = "Dados da entidade exemplo a serem atualizados", 
			type = "EntidadeExemplo") @RequestBody EntidadeExemploInfo entidadeExemplo)
			throws ItemNaoEncontradoException {

		EntidadeExemplo elemento = entidadeExemploService.recuperarPelo(id);
		elemento.setNome(entidadeExemplo.getNome());
		entidadeExemploService.salvar(elemento);
	}

	@ApiOperation(value = "Consulta todas as entidades exemplo ou por parte do nome", 
	        response = EntidadeExemploInfo.class, produces = "application/json")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Lista de entidades exemplo retornada", 
			        response = EntidadesExemploInfo.class),
			@ApiResponse(code = 401, message = "Acesso não autorizado"),
			@ApiResponse(code = 403, message = "Proibido acesso ao recurso"),
			@ApiResponse(code = 404, message = "Entidade exemplo não encontrada"),
			@ApiResponse(code = 500, message = "Erro interno no servidor") })
	@GetMapping("")
	@ResponseStatus(HttpStatus.OK)
	public EntidadesExemploInfo consulta(
			@ApiParam(name = "nome", value = "Nome ou parte dele para busca da(s) entidade(s)", required = false) 
			@RequestParam("nome") Optional<String> nome)
			throws ItemNaoEncontradoException {
	    
		List<EntidadeExemplo> entidadesExemplo = null;
		EntidadesExemploInfo metodo = null;

		if (nome.isPresent()) {
			entidadesExemplo = entidadeExemploService.recuperarPeloNome(nome.get());
			metodo = ControllerLinkBuilder.methodOn(this.getClass()).consulta(nome);
		} else {
			entidadesExemplo = entidadeExemploService.recuperarTodos();
			metodo = ControllerLinkBuilder.methodOn(this.getClass()).consulta(null);
		}

		return new EntidadesExemploInfo(entidadesExemplo, metodo);
	}

	@ApiOperation(value = "Remove uma entidade exemplo identificada pelo ID", response = EntidadeExemploInfo.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Entidade exemplo removida", response = EntidadeExemploInfo.class),
			@ApiResponse(code = 401, message = "Acesso não autorizado"),
			@ApiResponse(code = 403, message = "Proibido acesso ao recurso"),
			@ApiResponse(code = 404, message = "Entidade exemplo não encontrada para remover"),
			@ApiResponse(code = 500, message = "Erro interno no servidor") })
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@ApiParam(value = "Identificador da entidade exemplo") @PathVariable("id") Long id)
			throws ItemNaoEncontradoException {

		if (entidadeExemploService.validaSePodeExcluir(EntidadeExemplo.builder().id(id).build())) {
			entidadeExemploService.apagarPelo(id);
		}
	}
}