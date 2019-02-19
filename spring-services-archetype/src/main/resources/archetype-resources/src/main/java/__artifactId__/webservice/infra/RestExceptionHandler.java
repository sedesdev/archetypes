#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.webservice.infra;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import ${package}.base.infra.spring.webservice.exception.RestException;
import ${package}.base.infra.spring.webservice.info.ErroInfo;


/**
 * Tratamento de exceções que especifica o código de Status adequado
 * 
 * @author SEDES
 */
@RestControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler({ RestException.class })
	public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {

		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		String message = "";
		if (ex instanceof RestException) {
			ResponseStatus[] annotationsByType = ((RestException) ex).getClass()
					.getAnnotationsByType(ResponseStatus.class);
			status = annotationsByType[0].code();
			message = ex.getMessage();
		}
		ErroInfo erroInfo = new ErroInfo(status, message, "");
		return new ResponseEntity<>(erroInfo, new HttpHeaders(), erroInfo.getStatus());
	}
}