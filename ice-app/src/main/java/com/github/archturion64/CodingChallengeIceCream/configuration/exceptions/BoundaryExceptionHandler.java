package com.github.archturion64.CodingChallengeIceCream.configuration.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class BoundaryExceptionHandler extends ResponseEntityExceptionHandler {

    Logger log = LoggerFactory.getLogger(BoundaryExceptionHandler.class);

    @ExceptionHandler(IceCreamMappingException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ProblemDetail handleMappingException(IceCreamMappingException ex, WebRequest request) {
        log.error("Exception: '" + ex.getMessage() + "' at: " + request.getDescription(false));

        ProblemDetail body = ProblemDetail
                .forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
        body.setType(URI.create(((ServletWebRequest)request).getRequest().getRequestURI()));
        body.setTitle("Input mapping failed");
        return body;
    }

    @ExceptionHandler(IceCreamAlreadyExistsException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ProblemDetail handleDuplicateNameException(IceCreamAlreadyExistsException ex, WebRequest request) {
        log.error("Exception: '" + ex.getMessage() + "' at: " + request.getDescription(false));
        ProblemDetail body = ProblemDetail
                .forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
        body.setType(URI.create(((ServletWebRequest)request).getRequest().getRequestURI()));
        body.setTitle("Already exists");
        return body;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ProblemDetail handleAllOtherExceptions(Exception ex, WebRequest request) {
        log.error("Exception: '" + ex.getMessage() + "' at: " + request.getDescription(false));

        ProblemDetail body = ProblemDetail
                .forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage());
        body.setType(URI.create(((ServletWebRequest)request).getRequest().getRequestURI()));
        body.setTitle("Unexpected error");
        return body;
    }
}
