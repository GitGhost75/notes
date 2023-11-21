package de.kiemle.notes;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class NotesExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleException(RuntimeException re, WebRequest webRequest) {
        ErrorResponse.Builder builder = ErrorResponse.builder(re, HttpStatus.INTERNAL_SERVER_ERROR, re.getMessage());
        ProblemDetail pd = builder.build().updateAndGetBody(null, LocaleContextHolder.getLocale());
        return new ResponseEntity<>(pd, HttpHeaders.EMPTY, HttpStatus.BAD_REQUEST);
    }

}

