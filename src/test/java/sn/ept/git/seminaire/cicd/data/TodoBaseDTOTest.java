package sn.ept.git.seminaire.cicd.data;

import org.junit.jupiter.api.Test;

import sn.ept.git.seminaire.cicd.dto.base.TodoBaseDTO;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TodoBaseDTOTest {

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @Test
    public void testValidation() {
        TodoBaseDTO validTodo = TodoBaseDTO.builder()
                .title("Valid Title")
                .description("Valid Description")
                .completed(true)
                .build();

        TodoBaseDTO invalidTodo = TodoBaseDTO.builder()
                .title("") // Blank title, which is invalid
                .description("Valid Description")
                .completed(true)
                .build();

        assertTrue(validator.validate(validTodo).isEmpty()); // Valid TodoBaseDTO should have no validation errors
        assertFalse(validator.validate(invalidTodo).isEmpty()); // Invalid TodoBaseDTO should have validation errors
    }

    @Test
    public void testConstructorAndGetters() {
        // Créez une instance de TodoBaseDTO en utilisant le constructeur généré
        TodoBaseDTO dto = TodoBaseDTO.builder()
                .title("Test Title")
                .description("Test Description")
                .completed(true)
                .build();

        // Vérifiez que les valeurs des propriétés correspondent à ce que vous avez défini
        assertEquals("Test Title", dto.getTitle());
        assertEquals("Test Description", dto.getDescription());
        assertTrue(dto.isCompleted());
    }

    @Test
    public void testEqualsAndHashCode() {
        TodoBaseDTO dto1 = TodoBaseDTO.builder()
                .title("Test Title")
                .description("Test Description")
                .completed(true)
                .build();

        TodoBaseDTO dto2 = TodoBaseDTO.builder()
                .title("Test Title")
                .description("Test Description")
                .completed(true)
                .build();

        // Vérifiez que les deux instances sont égales
        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    public void testToString() {
        TodoBaseDTO dto = TodoBaseDTO.builder()
                .title("Test Title")
                .description("Test Description")
                .completed(true)
                .build();

        // Vérifiez que la méthode toString() renvoie la représentation attendue de l'objet
        assertEquals("TodoBaseDTO(title=Test Title, description=Test Description, completed=true)", dto.toString());
    }
}
