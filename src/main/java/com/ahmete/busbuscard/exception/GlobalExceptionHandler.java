package com.ahmete.busbuscard.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.lang.model.type.ErrorType;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	
	@ExceptionHandler(RuntimeException.class)
	public  ResponseEntity<ErrorMessage> runtimeExceptionHandler(RuntimeException exception){
		return createResponseEntity(EErrorType.INTERNAL_SERVER_ERROR,HttpStatus.INTERNAL_SERVER_ERROR,null);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public  ResponseEntity<ErrorMessage> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception){
		List<String> fieldErrors = new ArrayList<>();
		exception.getBindingResult().getFieldErrors()
				.forEach(fieldError -> {
					fieldErrors.add("Değişken adı...: "+fieldError.getField()+" - Hata Detayı....: "+fieldError.getDefaultMessage());
				});
		return createResponseEntity(EErrorType.VALIDATION_ERROR,HttpStatus.BAD_REQUEST,fieldErrors);
	}

	@ExceptionHandler(BusbusCardException.class)
	@ResponseBody
	public ResponseEntity<ErrorMessage> busbusCardExceptionHandler(BusbusCardException e) {


		return createResponseEntity(e.getErrorType(), e.getErrorType().httpStatus, null);
	}
	
	private ResponseEntity<ErrorMessage> createResponseEntity(EErrorType eErrorType, HttpStatus httpStatus,
	                                                          List<String> fields) {
		log.error("TÜM HATALARIN GEÇTİĞİ  NOKTA....: "+eErrorType.getMessage()+fields);
		return new ResponseEntity<>(ErrorMessage.builder()
				                            .fields(fields)
				                            .success(false)
				                            .message(eErrorType.getMessage())
				                            .code(eErrorType.getCode())
		                                        .build(), httpStatus);
		
	}
}