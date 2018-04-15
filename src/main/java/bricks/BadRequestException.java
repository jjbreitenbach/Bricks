package bricks;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(code=HttpStatus.BAD_REQUEST)
public class BadRequestException extends Exception {

    public BadRequestException(String message) {
        super(message);
    }
}

