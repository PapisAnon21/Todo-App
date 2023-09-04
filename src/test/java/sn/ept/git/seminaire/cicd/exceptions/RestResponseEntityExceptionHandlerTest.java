package sn.ept.git.seminaire.cicd.exceptions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.context.request.WebRequest;
import sn.ept.git.seminaire.cicd.exceptions.*;
import sn.ept.git.seminaire.cicd.exceptions.message.ErrorMessage;
import sn.ept.git.seminaire.cicd.exceptions.handler.RestResponseEntityExceptionHandler;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class RestResponseEntityExceptionHandlerTest {

    @InjectMocks
    private RestResponseEntityExceptionHandler exceptionHandler;

    @Mock
    private WebRequest webRequest;

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    @Test
    void testNotFound() {
        ItemNotFoundException exception = new ItemNotFoundException("Item not found");
        when(webRequest.getDescription(false)).thenReturn("Description");

        ResponseEntity<ErrorMessage> responseEntity = exceptionHandler.notFound(exception, webRequest);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals(HttpStatus.NOT_FOUND.value(), responseEntity.getBody().getStatus());
        assertEquals("Item not found", responseEntity.getBody().getMessage());
        assertEquals("Description", responseEntity.getBody().getDescription());
    }

    @Test
    void testConflict() {
        ItemExistsException exception = new ItemExistsException("Item already exists");
        when(webRequest.getDescription(false)).thenReturn("Description");

        ResponseEntity<ErrorMessage> responseEntity = exceptionHandler.conflict(exception, webRequest);

        assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
        assertEquals(HttpStatus.CONFLICT.value(), responseEntity.getBody().getStatus());
        assertEquals("Item already exists", responseEntity.getBody().getMessage());
        assertEquals("Description", responseEntity.getBody().getDescription());
    }

    @Test
    void testBadRequest() {
        InvalidException exception = new InvalidException("Invalid input");
        when(webRequest.getDescription(false)).thenReturn("Description");

        ResponseEntity<ErrorMessage> responseEntity = exceptionHandler.badRequest(exception, webRequest);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getBody().getStatus());
        assertEquals("Invalid input", responseEntity.getBody().getMessage());
        assertEquals("Description", responseEntity.getBody().getDescription());
    }



    @Test
    void testInternalError() {
        Exception exception = new Exception("Internal error");
        when(webRequest.getDescription(false)).thenReturn("Description");

        ResponseEntity<ErrorMessage> responseEntity = exceptionHandler.internalError(exception, webRequest);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), responseEntity.getBody().getStatus());
        assertEquals("Internal error", responseEntity.getBody().getMessage());
        assertEquals("Description", responseEntity.getBody().getDescription());
    }
}
