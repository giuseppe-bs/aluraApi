package br.com.giuseppebs.aluraApi.infra;

import br.com.giuseppebs.aluraApi.domain.ValidacaoException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ApiExceptions {

    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity<String> tratarErroRegraDeNegocio(ValidacaoException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> notFound(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ValidationData>> notValid(MethodArgumentNotValidException ex){
        var erros = ex.getFieldErrors();
        return ResponseEntity
                .badRequest()
                .body(erros.stream().map(ValidationData::new).toList());
    }

    public record ValidationData(String campo, String mensagem) {
        public ValidationData(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
}
