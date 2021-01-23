package br.com.helper.exception;

import br.com.helper.beans.ValidationMessage;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Getter
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class LocalException extends RuntimeException {

    private final List<ValidationMessage> validationMessages;

    public LocalException(List<ValidationMessage> validationMessages) {
        this.validationMessages = validationMessages;
    }

}
