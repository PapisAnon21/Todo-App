package sn.ept.git.seminaire.cicd.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemNotFoundExceptionTest {

    @Test
    void testDefaultConstructor() {
        ItemNotFoundException exception = new ItemNotFoundException();
        assertEquals(ItemNotFoundException.DEFAUL_MESSAGE, exception.getMessage());
    }

    @Test
    void testConstructorWithMessage() {
        String customMessage = "Custom not found message";
        ItemNotFoundException exception = new ItemNotFoundException(customMessage);
        assertEquals(customMessage, exception.getMessage());
    }

    @Test
    void testConstructorWithMessageAndCause() {
        String customMessage = "Custom not found message";
        Throwable cause = new RuntimeException("Cause of not found");
        ItemNotFoundException exception = new ItemNotFoundException(customMessage, cause);
        assertEquals(customMessage, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        Throwable cause = new RuntimeException("Cause of not found");
        ItemNotFoundException exception = new ItemNotFoundException(cause);
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testFormatMethod() {
        String template = "Template message: %s";
        String argument = "Argument";
        String expectedMessage = "Template message: Argument";
        String formattedMessage = ItemNotFoundException.format(template, argument);
        assertEquals(expectedMessage, formattedMessage);
    }
}
