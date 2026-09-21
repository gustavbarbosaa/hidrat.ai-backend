package br.com.gustavobarbosa.hidrat_ai.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ProblemDetail tratarRecursoNaoEncontrado(RecursoNaoEncontradoException exception) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setTitle("Recurso não encontrado.");
        problemDetail.setDetail(exception.getMessage());

        return problemDetail;
    }
}
