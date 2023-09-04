package sn.ept.git.seminaire.cicd.demo;

import org.junit.jupiter.api.Test;

import sn.ept.git.seminaire.cicd.demo.exception.BadPhoneException;

import static org.junit.jupiter.api.Assertions.*;

public class BadPhoneExceptionTest {

    @Test
    public void testDefaultConstructor() {
        BadPhoneException exception = new BadPhoneException();
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    public void testMessageConstructor() {
        String message = "Custom exception message";
        BadPhoneException exception = new BadPhoneException(message);
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    public void testMessageAndCauseConstructor() {
        String message = "Custom exception message";
        Throwable cause = new RuntimeException("Test cause");
        BadPhoneException exception = new BadPhoneException(message, cause);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    public void testCauseConstructor() {
        Throwable cause = new RuntimeException("Test cause");
        BadPhoneException exception = new BadPhoneException(cause);
        //assertNull(exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    public void testFullConstructor() {
        String message = "Custom exception message";
        Throwable cause = new RuntimeException("Test cause");
        BadPhoneException exception = new BadPhoneException(message, cause, true, true);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
        //assertTrue(exception.getSuppressed());
        assertTrue(exception.getStackTrace().length > 0);
    }
}
