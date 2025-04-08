package matheusosses.aluraflix.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> noSuchElementException (NoSuchElementException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sem resultado para o id fornecido");
    }

    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity<String> validacaoException (ValidacaoException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> validException(MethodArgumentNotValidException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Dados inválidos para a requisição");
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> dataIntegrityViolationException (DataIntegrityViolationException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não há categoria com esse id");
    }

    @ExceptionHandler(CadastroCategoriaException.class)
    public ResponseEntity<String> cadastroCategoriaException (CadastroCategoriaException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<String> handlerMethodValidationException (HandlerMethodValidationException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O campo search não pode ser nulo ou vazio");
    }
}
