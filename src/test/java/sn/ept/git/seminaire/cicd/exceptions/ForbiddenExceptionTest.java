package sn.ept.git.seminaire.cicd.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ForbiddenExceptionTest {

    @Test
    void testDefaultConstructor() {
        ForbiddenException exception = new ForbiddenException();
        assertEquals(ForbiddenException.DEFAULT_MSG, exception.getMessage());
    }


    @Test
    void testConstructorWithCause() {
        Throwable cause = new RuntimeException("Cause of forbidden");
        ForbiddenException exception = new ForbiddenException(cause);
        assertEquals(cause, exception.getCause());
    }
}
