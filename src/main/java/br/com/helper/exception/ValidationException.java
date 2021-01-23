package br.com.helper.exception;

import br.com.helper.beans.ValidationMessage;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Getter
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ValidationException extends LocalException {

    public ValidationException(List<ValidationMessage> validationMessages) {
       super(validationMessages);
    }

}
