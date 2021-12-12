package at.ac.fhstp.sniffer.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class SnifferExceptions extends ResponseStatusException
{
    public SnifferExceptions(String message)
    {
        super(HttpStatus.BAD_REQUEST, message);
    }    
}

