package sn.ept.git.seminaire.cicd.data;

import sn.ept.git.seminaire.cicd.dto.TodoDTO;
import sn.ept.git.seminaire.cicd.dto.base.TodoBaseDTO;
import sn.ept.git.seminaire.cicd.models.Todo;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.ConstraintViolation;
import java.util.Set;

import org.junit.Test;

public final class TodoDTOTestData extends TestData {

    
    public static TodoDTO defaultDTO() {
        return TodoDTO
                .builder()
                .id(Default.id)
                .createdDate(Default.createdDate)
                .lastModifiedDate(Default.lastModifiedDate)
                .version(Default.version)
                .deleted(Default.deleted)
                .enabled(Default.enabled)
                .title(Default.title)
                .description(Default.description)
                .tags(Stream.of(TagDTOTestData.defaultDTO()).collect(Collectors.toSet()))
                .build();
    }

    
    public static TodoDTO updatedDTO() {
        return TodoDTO
                .builder()
                .id(Default.id)
                .createdDate(Update.createdDate)
                .lastModifiedDate(Update.lastModifiedDate)
                .version(Update.version)
                .deleted(Update.deleted)
                .enabled(Update.enabled)
                .title(Update.title)
                .description(Update.description)
                .tags(Stream.of(TagDTOTestData.updatedDTO()).collect(Collectors.toSet()))
                .build();
    }

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @Test
    public void testDefaultConstructor() {
        TodoBaseDTO todoBaseDTO = new TodoBaseDTO();
        assertNull(todoBaseDTO.getId());
        assertNull(todoBaseDTO.getCreatedDate());
        assertNull(todoBaseDTO.getLastModifiedDate());
        assertEquals(0, todoBaseDTO.getVersion());
        assertTrue(todoBaseDTO.isEnabled());
        assertFalse(todoBaseDTO.isDeleted());
        assertNull(todoBaseDTO.getTitle());
        assertNull(todoBaseDTO.getDescription());
        assertFalse(todoBaseDTO.isCompleted());
    }

    @Test
    public void testAllArgsConstructor() {
        TodoBaseDTO todoBaseDTO = TodoBaseDTO.builder()
                .id(UUID.randomUUID())
                .createdDate(Instant.now())
                .lastModifiedDate(Instant.now())
                .version(2)
                .enabled(false)
                .deleted(true)
                .title("Sample Title")
                .description("Sample Description")
                .completed(true)
                .build();

        assertNotNull(todoBaseDTO.getId());
        assertNotNull(todoBaseDTO.getCreatedDate());
        assertNotNull(todoBaseDTO.getLastModifiedDate());
        assertEquals(2, todoBaseDTO.getVersion());
        assertFalse(todoBaseDTO.isEnabled());
        assertTrue(todoBaseDTO.isDeleted());
        assertEquals("Sample Title", todoBaseDTO.getTitle());
        assertEquals("Sample Description", todoBaseDTO.getDescription());
        assertTrue(todoBaseDTO.isCompleted());
    }

    @Test
    public void testValidation() {
        TodoBaseDTO todoBaseDTO = TodoBaseDTO.builder()
                .title("") // Invalid, blank
                .description("Valid Description")
                .completed(false)
                .build();

        Set<ConstraintViolation<TodoBaseDTO>> violations = validator.validate(todoBaseDTO);
        assertEquals(1, violations.size());
        ConstraintViolation<TodoBaseDTO> violation = violations.iterator().next();
        assertEquals("NotBlank", violation.getMessage());
        assertEquals("title", violation.getPropertyPath().toString());
    }

    @Test
    public void testConstructorsAndGettersSetters() {
        // Créer une instance de TodoBaseDTO en utilisant le constructeur par défaut
        TodoBaseDTO todoBaseDTO = new TodoBaseDTO();

        // Définir les valeurs des propriétés
        String title = "Todo Title";
        String description = "Todo Description";
        boolean completed = true;

        todoBaseDTO.setTitle(title);
        todoBaseDTO.setDescription(description);
        todoBaseDTO.setCompleted(completed);

        // Vérifier que les valeurs sont correctement récupérées via les getters
        assertEquals(title, todoBaseDTO.getTitle());
        assertEquals(description, todoBaseDTO.getDescription());
        assertEquals(completed, todoBaseDTO.isCompleted());

        // Créer une nouvelle instance de TodoBaseDTO en utilisant le constructeur avec des arguments
        TodoBaseDTO todoBaseDTO2 = new TodoBaseDTO(title, description, completed);

        // Vérifier que les valeurs sont correctement récupérées via les getters
        assertEquals(title, todoBaseDTO2.getTitle());
        assertEquals(description, todoBaseDTO2.getDescription());
        assertEquals(completed, todoBaseDTO2.isCompleted());
    }

    @Test
    public void testToString() {
        Todo todo = Todo.builder().title("Test Title").description("Test Description").completed(true).build();
        assertNotNull(todo.toString());
    }

    @Test
    public void testEqualsAndHashCode() {
        Todo todo1 = Todo.builder().title("Test Title").description("Test Description").completed(true).build();
        Todo todo2 = Todo.builder().title("Test Title").description("Test Description").completed(true).build();

        assertTrue(todo1.equals(todo2));
        assertTrue(todo2.equals(todo1));
        assertEquals(todo1.hashCode(), todo2.hashCode());
    }
}
