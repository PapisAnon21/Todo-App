package sn.ept.git.seminaire.cicd.utils;


import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ResponseUtilTest {

    @Test
    void wrapOrNotFoundWithOptionalDataShouldReturnResponseEntityWithData() {
        Optional<String> maybeResponse = Optional.of("Data");
        ResponseEntity<String> responseEntity = ResponseUtil.wrapOrNotFound(maybeResponse);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Data", responseEntity.getBody());
    }

    @Test
    void wrapOrNotFoundWithEmptyOptionalShouldThrowResponseStatusException() {
        Optional<String> maybeResponse = Optional.empty();
        assertThrows(ResponseStatusException.class,
                () -> ResponseUtil.wrapOrNotFound(maybeResponse));
    }

    @Test
    void wrapOrNotFoundWithOptionalDataAndStatusShouldReturnResponseEntityWithDataAndStatus() {
        Optional<String> maybeResponse = Optional.of("Data");
        ResponseEntity<String> responseEntity = ResponseUtil.wrapOrNotFound(maybeResponse, HttpStatus.CREATED);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("Data", responseEntity.getBody());
    }

    @Test
    void wrapOrNotFoundWithOptionalDataAndStatusAndReasonShouldReturnResponseEntityWithDataStatusAndReason() {
        Optional<String> maybeResponse = Optional.of("Data");
        ResponseEntity<String> responseEntity = ResponseUtil.wrapOrNotFound(maybeResponse, HttpStatus.CREATED, "Reason");
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("Data", responseEntity.getBody());
    }

    @Test
    void wrapOrNotFoundWithOptionalDataAndHeaderAndStatusShouldReturnResponseEntityWithDataHeaderAndStatus() {
        Optional<String> maybeResponse = Optional.of("Data");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Header", "Value");
        ResponseEntity<String> responseEntity = ResponseUtil.wrapOrNotFound(maybeResponse, headers, HttpStatus.CREATED);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("Data", responseEntity.getBody());
        assertTrue(responseEntity.getHeaders().containsKey("Header"));
        assertEquals("Value", responseEntity.getHeaders().getFirst("Header"));
    }

    /*
    @Test
    void wrapOrNotFoundWithEmptyOptionalAndReasonShouldThrowResponseStatusExceptionWithReason() {
        Optional<String> maybeResponse = Optional.empty();
        assertThrows(ResponseStatusException.class,
                () -> ResponseUtil.wrapOrNotFound(maybeResponse, "Reason"));
    }
    */
}
