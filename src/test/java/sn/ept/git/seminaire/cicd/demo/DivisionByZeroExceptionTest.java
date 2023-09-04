package sn.ept.git.seminaire.cicd.demo;

import org.junit.jupiter.api.Test;

import sn.ept.git.seminaire.cicd.demo.exception.DivisionByZeroException;

import static org.junit.jupiter.api.Assertions.*;

public class DivisionByZeroExceptionTest {

    @Test
    public void testDefaultConstructor() {
        DivisionByZeroException exception = new DivisionByZeroException();
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    public void testMessageConstructor() {
        String message = "Custom exception message";
        DivisionByZeroException exception = new DivisionByZeroException(message);
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    public void testMessageAndCauseConstructor() {
        String message = "Custom exception message";
        Throwable cause = new RuntimeException("Test cause");
        DivisionByZeroException exception = new DivisionByZeroException(message, cause);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    public void testCauseConstructor() {
        Throwable cause = new RuntimeException("Test cause");
        DivisionByZeroException exception = new DivisionByZeroException(cause);
        //assertNull(exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    public void testFullConstructor() {
        String message = "Custom exception message";
        Throwable cause = new RuntimeException("Test cause");
        DivisionByZeroException exception = new DivisionByZeroException(message, cause, true, true);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
        //assertTrue(exception.getSuppression());
        assertTrue(exception.getStackTrace().length > 0);
    }
}

