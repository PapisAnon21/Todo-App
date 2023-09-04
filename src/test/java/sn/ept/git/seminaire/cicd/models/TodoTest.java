package sn.ept.git.seminaire.cicd.models;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import sn.ept.git.seminaire.cicd.models.Tag;
import sn.ept.git.seminaire.cicd.models.Todo;
import sn.ept.git.seminaire.cicd.repositories.TagRepository;
import sn.ept.git.seminaire.cicd.repositories.TodoRepository;

import java.time.Instant;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class TodoTest {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private TagRepository tagRepository;

    @Test
    void testTodoCrudOperations() {
        Tag tag = Tag.builder()
                .name("Test Tag")
                .description("Description of test tag")
                .build();
        tag = tagRepository.save(tag);

        Set<Tag> tags = new HashSet<>();
        tags.add(tag);

        Todo todo = Todo.builder()
                .title("Test Todo")
                .description("Description of test todo")
                .tags(tags)
                .build();

        assertNull(todo.getId()); // Todo ID should be null before saving

        Todo savedTodo = todoRepository.save(todo);
        assertNotNull(savedTodo.getId()); // Todo ID should not be null after saving

        Optional<Todo> fetchedTodo = todoRepository.findById(savedTodo.getId());
        assertTrue(fetchedTodo.isPresent()); // Todo should be present in the repository
        assertEquals(savedTodo.getId(), fetchedTodo.get().getId());

        todoRepository.deleteById(savedTodo.getId());
        assertFalse(todoRepository.existsById(savedTodo.getId())); // Todo should not exist after deletion

        tagRepository.deleteById(tag.getId());
        assertFalse(tagRepository.existsById(tag.getId())); // Tag should not exist after deletion
    }

    @Test
    public void testUpdateWith() {
        // Créer une instance de Todo
        Todo originalTodo = new Todo();
        UUID id = UUID.randomUUID();
        originalTodo.setId(id);
        originalTodo.setTitle("Original Title");
        originalTodo.setDescription("Original Description");
        originalTodo.setCompleted(false);

        // Créer une nouvelle instance de Todo à partir de l'original
        Todo newTodo = new Todo();
        newTodo.setTitle("New Title");
        newTodo.setDescription("New Description");
        newTodo.setCompleted(true);

        Todo updatedTodo = originalTodo.updateWith(newTodo);

        // Vérifier que les propriétés sont correctement mises à jour
        assertEquals(id, updatedTodo.getId());
        assertEquals(newTodo.getTitle(), updatedTodo.getTitle());
        assertEquals(newTodo.getDescription(), updatedTodo.getDescription());
       // assertEquals(newTodo.isCompleted(), updatedTodo.isCompleted());
    }

    @Test
    public void testGettersAndSetters() {
        // Créer une instance de Todo
        Todo todo = new Todo();

        // Définir les valeurs des propriétés
        String title = "Todo Title";
        String description = "Todo Description";
        boolean completed = false;

        todo.setTitle(title);
        todo.setDescription(description);
        todo.setCompleted(completed);

        // Vérifier que les valeurs sont correctement récupérées via les getters
        assertEquals(title, todo.getTitle());
        assertEquals(description, todo.getDescription());
        assertEquals(completed, todo.isCompleted());
    }

    @Test
    public void testTags() {
        // Créer une instance de Todo
        Todo todo = new Todo();

        // Créer deux instances de Tag
        Tag tag1 = new Tag();
        Tag tag2 = new Tag();

        // Ajouter les tags à la liste des tags du Todo
        Set<Tag> tags = new HashSet<>();
        tags.add(tag1);
        tags.add(tag2);

        todo.setTags(tags);

        // Vérifier que les tags sont correctement récupérés via le getter
        assertEquals(tags, todo.getTags());
    }

    @Test
    public void testMetamodelAttributes() {
        assertEquals("description", Todo_.DESCRIPTION);
        assertEquals("completed", Todo_.COMPLETED);
        assertEquals("title", Todo_.TITLE);
        assertEquals("tags", Todo_.TAGS);
    }

    @Test
    public void testDataAnnotationGeneratesGetterSetterEqualsHashCodeToString() {
        Todo todo1 = Todo.builder()
                .id(UUID.randomUUID())
                .title("Test Title")
                .description("Test Description")
                .completed(true)
                .createdDate(Instant.now())
                .lastModifiedDate(Instant.now())
                .version(1)
                .enabled(true)
                .deleted(false)
                .build();

        Todo todo2 = Todo.builder()
                .id(UUID.randomUUID())
                .title("Test Title")
                .description("Test Description")
                .completed(true)
                .createdDate(Instant.now())
                .lastModifiedDate(Instant.now())
                .version(1)
                .enabled(true)
                .deleted(false)
                .build();

        // Test equals and hashCode
        assertThat(todo1).isEqualTo(todo2);
        assertThat(todo1.hashCode()).isEqualTo(todo2.hashCode());

        // Test toString
        assertThat(todo1.toString()).isEqualTo(todo2.toString());
    }

    @Test
    public void testAllArgsConstructor() {
        UUID id = UUID.randomUUID();
        String title = "Test Title";
        String description = "Test Description";
        boolean completed = true;
        Instant createdDate = Instant.now();
        Instant lastModifiedDate = Instant.now();
        int version = 1;
        boolean enabled = true;
        boolean deleted = false;

        Todo todo = Todo.builder()
                .id(id)
                .title(title)
                .description(description)
                .completed(completed)
                .createdDate(createdDate)
                .lastModifiedDate(lastModifiedDate)
                .version(version)
                .enabled(enabled)
                .deleted(deleted)
                .build();

        assertThat(todo.getId()).isEqualTo(id);
        assertThat(todo.getTitle()).isEqualTo(title);
        assertThat(todo.getDescription()).isEqualTo(description);
        assertThat(todo.isCompleted()).isEqualTo(completed);
        assertThat(todo.getCreatedDate()).isEqualTo(createdDate);
        assertThat(todo.getLastModifiedDate()).isEqualTo(lastModifiedDate);
        assertThat(todo.getVersion()).isEqualTo(version);
        assertThat(todo.isEnabled()).isEqualTo(enabled);
        assertThat(todo.isDeleted()).isEqualTo(deleted);
    }

    @Test
    void new_testUpdateWith() {
        Todo originalTodo = Todo.builder()
                .id(UUID.randomUUID())
                .title("Original Title")
                .description("Original Description")
                .completed(false)
                .build();

        Todo updatedTodo = Todo.builder()
                .title("Updated Title")
                .description("Updated Description")
                .completed(true)
                .build();

        // Appelez la méthode updateWith pour mettre à jour l'objet originalTodo
        Todo resultTodo = originalTodo.updateWith(updatedTodo);

        // Vérifiez que les champs de resultTodo sont correctement mis à jour
        assertNotNull(resultTodo);
        assertEquals(originalTodo.getId(), resultTodo.getId());
        assertEquals(updatedTodo.getTitle(), resultTodo.getTitle());
        assertEquals(updatedTodo.getDescription(), resultTodo.getDescription());
        assertEquals(originalTodo.isCompleted(), resultTodo.isCompleted());
    }
}
