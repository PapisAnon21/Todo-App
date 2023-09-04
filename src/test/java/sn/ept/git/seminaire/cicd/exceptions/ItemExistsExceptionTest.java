package sn.ept.git.seminaire.cicd.exceptions;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemExistsExceptionTest {

    @Test
    void testDefaultConstructor() {
        ItemExistsException exception = new ItemExistsException();
        assertEquals(ItemExistsException.DEFAUL_MESSAGE, exception.getMessage());
    }


    @Test
    void testConstructorWithMessage() {
        String customMessage = "Custom conflict message";
        ItemExistsException exception = new ItemExistsException(customMessage);
        assertEquals(customMessage, exception.getMessage());
    }

    @Test
    void testConstructorWithMessageAndCause() {
        String customMessage = "Custom conflict message";
        Throwable cause = new RuntimeException("Cause of conflict");
        ItemExistsException exception = new ItemExistsException(customMessage, cause);
        assertEquals(customMessage, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        Throwable cause = new RuntimeException("Cause of conflict");
        ItemExistsException exception = new ItemExistsException(cause);
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testFormatMethod() {
        String template = "Template message: %s";
        String argument = "Argument";
        String expectedMessage = "Template message: Argument";
        String formattedMessage = ItemExistsException.format(template, argument);
        assertEquals(expectedMessage, formattedMessage);
    }
}
