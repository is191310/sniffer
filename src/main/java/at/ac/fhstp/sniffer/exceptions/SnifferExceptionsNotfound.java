package at.ac.fhstp.sniffer.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class SnifferExceptionsNotfound extends ResponseStatusException
{
    public SnifferExceptionsNotfound(String message)
    {
        super(HttpStatus.NOT_FOUND, message);
    }
}
