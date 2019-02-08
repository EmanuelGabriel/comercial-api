package com.emanuel.comercial.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class OportunidadeInexistenteOuInativaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

}
