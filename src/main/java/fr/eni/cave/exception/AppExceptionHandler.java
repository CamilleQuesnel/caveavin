package fr.eni.cave.exception;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Locale;

@AllArgsConstructor
@ControllerAdvice
public class AppExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity capturerExceptionHandler(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity capturerExceptionArgumentHandler(MethodArgumentNotValidException ex, Locale locale) {
        final String titre = messageSource.getMessage("notvalidexception", null, locale);


        String msg = ex.getFieldErrors().stream()
                .map(e -> "\n\t-" + e.getDefaultMessage())
                .reduce(titre, String::concat);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
    }


}
