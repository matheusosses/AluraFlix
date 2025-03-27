package matheusosses.aluraflix.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
}
