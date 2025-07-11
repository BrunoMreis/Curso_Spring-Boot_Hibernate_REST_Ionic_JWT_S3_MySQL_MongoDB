package com.curso.bruno.resource.exeption;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.curso.bruno.services.exeption.AuthorizationException;
import com.curso.bruno.services.exeption.DataIntegrityException;
import com.curso.bruno.services.exeption.FileException;
import com.curso.bruno.services.exeption.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExeptionHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
		StandardError err = new StandardError(System.currentTimeMillis(),HttpStatus.NOT_FOUND.value(),"Não encontatrdo", e.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
		
	}
	
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError> dateIntegrity(DataIntegrityException e, HttpServletRequest request){
		StandardError err = new StandardError(System.currentTimeMillis(),HttpStatus.BAD_REQUEST.value(),"Integridade de dados", e.getMessage(),request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
		
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request){
		ValidationError err = new ValidationError(System.currentTimeMillis(), HttpStatus.UNPROCESSABLE_ENTITY.value(), "Erro de Validação", e.getMessage(), request.getRequestURI());
		
		for (FieldError x : e.getBindingResult().getFieldErrors()) {
			err.addErrors(x.getField(), x.getDefaultMessage());
		}
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);
		
	}
	
	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<StandardError> authorization(AuthorizationException e, HttpServletRequest request){
		StandardError err = new StandardError(System.currentTimeMillis(),HttpStatus.FORBIDDEN.value(),"Acesso Negado", e.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
		
		
	}	
		@ExceptionHandler(FileException.class)
		public ResponseEntity<StandardError> file(FileException e, HttpServletRequest request){
			StandardError err = new StandardError(System.currentTimeMillis(),HttpStatus.BAD_REQUEST.value(),"Erro de Arquivo", e.getMessage(), request.getRequestURI());
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
		
		@ExceptionHandler(AmazonServiceException.class)
		public ResponseEntity<StandardError> amazonServiceException(AmazonServiceException e, HttpServletRequest request){
			
			HttpStatus code = HttpStatus.valueOf(e.getErrorCode());
			StandardError err = new StandardError(System.currentTimeMillis(), code.value(), "Erro Amazon Service", e.getMessage(), request.getRequestURI());
			return ResponseEntity.status(code).body(err);
		}
		@ExceptionHandler(AmazonClientException.class)
		public ResponseEntity<StandardError> amazonClientException(AmazonClientException e, HttpServletRequest request){
			
			StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Erro Amazon Client", e.getMessage(), request.getRequestURI());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
		}
		@ExceptionHandler(AmazonS3Exception.class)
		public ResponseEntity<StandardError> amazonS3Exception(AmazonS3Exception e, HttpServletRequest request){
			
			StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Erro Amazon S3", e.getMessage(), request.getRequestURI());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
		}
}
