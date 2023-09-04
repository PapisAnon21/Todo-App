package sn.ept.git.seminaire.cicd.exceptions;

import org.junit.jupiter.api.Test;

import sn.ept.git.seminaire.cicd.exceptions.message.ErrorMessage;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ErrorMessageTest {

    @Test
    void createErrorMessageWithAllFields() {
        int expectedStatus = 404;
        Date expectedTimestamp = new Date();
        String expectedMessage = "Not Found";
        String expectedDescription = "The requested resource was not found.";

        ErrorMessage errorMessage = ErrorMessage.of(expectedStatus, expectedTimestamp, expectedMessage, expectedDescription);

        assertNotNull(errorMessage);
        assertEquals(expectedStatus, errorMessage.getStatus());
        assertEquals(expectedTimestamp, errorMessage.getTimestamp());
        assertEquals(expectedMessage, errorMessage.getMessage());
        assertEquals(expectedDescription, errorMessage.getDescription());
    }

    @Test
    void testToString() {
        int status = 500;
        Date timestamp = new Date();
        String message = "Internal Server Error";
        String description = "An internal server error occurred.";
        ErrorMessage errorMessage = ErrorMessage.of(status, timestamp, message, description);

        String expectedToString = "ErrorMessage(status=500, timestamp=" + timestamp + ", message=Internal Server Error, description=An internal server error occurred.)";
        String actualToString = errorMessage.toString();

        assertEquals(expectedToString, actualToString);
    }

    @Test
    public void testConstructorAndGetters() {
        int status = 404;
        Date timestamp = new Date();
        String message = "Not Found";
        String description = "Resource not found.";

        ErrorMessage errorMessage = ErrorMessage.of(status, timestamp, message, description);

        assertEquals(status, errorMessage.getStatus());
        assertEquals(timestamp, errorMessage.getTimestamp());
        assertEquals(message, errorMessage.getMessage());
        assertEquals(description, errorMessage.getDescription());
    }

    /*@Test
    public void testEqualsAndHashCode() {
        ErrorMessage errorMessage1 = ErrorMessage.of(404, new Date(), "Not Found", "Resource not found.");
        ErrorMessage errorMessage2 = ErrorMessage.of(404, new Date(), "Not Found", "Resource not found.");
        ErrorMessage errorMessage3 = ErrorMessage.of(500, new Date(), "Internal Server Error", "Internal server error.");

        assertEquals(errorMessage1, errorMessage2);
        assertEquals(errorMessage1.hashCode(), errorMessage2.hashCode());

        // Objects with different status codes should not be equal
        assertNotEquals(errorMessage1, errorMessage3);
        assertNotEquals(errorMessage1.hashCode(), errorMessage3.hashCode());
    }

     */

    @Test
    public void newtestToString() {
        int status = 404;
        Date timestamp = new Date();
        String message = "Not Found";
        String description = "Resource not found.";

        ErrorMessage errorMessage = ErrorMessage.of(status, timestamp, message, description);

        String expectedToString = "ErrorMessage(status=" + status +
                ", timestamp=" + timestamp +
                ", message=" + message +
                ", description=" + description + ")";

        assertEquals(expectedToString, errorMessage.toString());
    }

    
}
