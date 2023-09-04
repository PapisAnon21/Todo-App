package sn.ept.git.seminaire.cicd.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class InvalidExceptionTest {

    @Test
    void createInvalidExceptionWithMessage() {
        String expectedMessage = "This is an invalid exception.";

        InvalidException invalidException = new InvalidException(expectedMessage);

        assertNotNull(invalidException);
        assertEquals(expectedMessage, invalidException.getMessage());
    }
}
