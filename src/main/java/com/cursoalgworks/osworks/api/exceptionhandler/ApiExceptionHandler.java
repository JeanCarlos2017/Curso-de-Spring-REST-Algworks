package com.cursoalgworks.osworks.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;




@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	 @Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		var problema = new Problema();
		//descobrindo os campos que tem problemas 
		var campos = new ArrayList<Problema.Campo>();
		
		for (ObjectError error: ex.getBindingResult().getAllErrors()) {
			String nome = ((FieldError) error).getField();
			String mensagem = error.getDefaultMessage();
			
			campos.add(new Problema.Campo(nome, mensagem));
		}
		problema.setStatus(status.value());
		problema.setTitulo("Erros presentes  na requisição");
		problema.setDataHora(LocalDateTime.now());
		problema.setCampos(campos);
		
		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	}

}
