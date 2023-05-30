package be.vdab.movies.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class SoldOutException extends RuntimeException{
    public SoldOutException(String titel) {
        super(titel + " - de film is niet meer beschikbaar");
    }
}
