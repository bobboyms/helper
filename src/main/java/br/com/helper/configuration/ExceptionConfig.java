package br.com.helper.configuration;

import br.com.helper.beans.ValidationMessage;
import br.com.helper.exception.LocalException;
import br.com.helper.exception.ObjectNotFoundException;
import br.com.helper.exception.ValidationException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionConfig {

    private final MessageSource messageSource;

    @Autowired
    public ExceptionConfig(MessageSource messageSource) {
        this.messageSource = messageSource;
    }


    @ExceptionHandler({
            ValidationException.class
    })
    public ResponseEntity errorValidationException(Exception ex) {
        return ResponseEntity.badRequest()
                .body(((LocalException)ex).getValidationMessages());
    }

    @ExceptionHandler({
            ObjectNotFoundException.class
    })
    @ResponseBody()
    public ResponseEntity errorObjectNotFoundException(Exception ex) {
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler({
            MethodArgumentNotValidException.class
    })
    public ResponseEntity errorMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        List<ValidationMessage> validationMessages = fieldErrors.stream().map(fieldError -> {
            String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            return new ValidationMessage(message);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationMessages);
    }

    @ExceptionHandler({
            InvalidFormatException.class
    })
    public ResponseEntity errorInvalidFormatException(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Arrays.asList(new ValidationMessage("Value not accepted")));
    }



}
