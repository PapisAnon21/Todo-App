package sn.ept.git.seminaire.cicd.models;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import sn.ept.git.seminaire.cicd.models.Tag;
import sn.ept.git.seminaire.cicd.repositories.TagRepository;

import java.time.Instant;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class TagTest {

    @Autowired
    private TagRepository tagRepository;

    @Test
    void testTagCrudOperations() {
        Tag tag = Tag.builder()
                .name("Test Tag")
                .description("Description of test tag")
                .build();

        assertNull(tag.getId()); // Tag ID should be null before saving

        Tag savedTag = tagRepository.save(tag);
        assertNotNull(savedTag.getId()); // Tag ID should not be null after saving

        Optional<Tag> fetchedTag = tagRepository.findById(savedTag.getId());
        assertTrue(fetchedTag.isPresent()); // Tag should be present in the repository
        assertEquals(savedTag.getId(), fetchedTag.get().getId());

        tagRepository.deleteById(savedTag.getId());
        assertFalse(tagRepository.existsById(savedTag.getId())); // Tag should not exist after deletion
    }

    @Test
    public void testFromId() {
        // Créer une instance de Tag avec un ID
        UUID id = UUID.randomUUID();
        Tag tag = Tag.fromId(id);

        // Vérifier que l'ID de l'instance créée est égal à l'ID initial
        assertEquals(id, tag.getId());
    }

    @Test
    public void testGettersAndSetters() {
        // Créer une instance de Tag
        Tag tag = new Tag();

        // Définir les valeurs des propriétés
        String name = "Tag Name";
        String description = "Tag Description";
        tag.setName(name);
        tag.setDescription(description);

        // Vérifier que les valeurs sont correctement récupérées via les getters
        assertEquals(name, tag.getName());
        assertEquals(description, tag.getDescription());
    }

    @Test
    public void testMetamodelAttributes() {
        assertEquals("createdDate", BaseEntity_.CREATED_DATE);
        assertEquals("deleted", BaseEntity_.DELETED);
        assertEquals("lastModifiedDate", BaseEntity_.LAST_MODIFIED_DATE);
        assertEquals("id", BaseEntity_.ID);
        assertEquals("version", BaseEntity_.VERSION);
        assertEquals("enabled", BaseEntity_.ENABLED);
    }
    @Test
    public void testMetamodelAttributes1() {
        assertEquals("name", Tag_.NAME);
        assertEquals("description", Tag_.DESCRIPTION);
        assertEquals("todos", Tag_.TODOS);
    }


    @Test
    public void testDefaultConstructor() {
        Tag tag = new Tag();


        assertThat(tag).isNotNull();
        assertNull(tag.getId());
        assertNull(tag.getName());
        assertNull(tag.getDescription());
    }


    @Test
    void testFromIdnew() {
        // Créer un UUID pour le test
        UUID id = UUID.randomUUID();

        // Appeler la méthode fromId pour créer une instance de Tag
        Tag tag = Tag.fromId(id);

        // Vérifier que l'objet Tag a été créé avec le bon ID
        assertNotNull(tag);
        assertEquals(id, tag.getId());
        assertNull(tag.getName());
        assertNull(tag.getDescription());
    }

    @Test
    public void testDataAnnotationGeneratesGetterSetterEqualsHashCodeToString() {
        // Créez deux objets Tag identiques
        UUID id = UUID.randomUUID();
        String name = "Test Name";
        String description = "Test Description";

        Tag tag1 = Tag.builder()
                .id(id)
                .name(name)
                .description(description)
                .createdDate(Instant.now())
                .lastModifiedDate(Instant.now())
                .version(1)
                .enabled(true)
                .deleted(false)
                .build();

        Tag tag2 = Tag.builder()
                .id(id)
                .name(name)
                .description(description)
                .createdDate(Instant.now())
                .lastModifiedDate(Instant.now())
                .version(1)
                .enabled(true)
                .deleted(false)
                .build();

        // Test equals et hashCode
        assertThat(tag1).isEqualTo(tag2);
        assertThat(tag1.hashCode()).isEqualTo(tag2.hashCode());

        // Test toString
        assertThat(tag1.toString()).isEqualTo(tag2.toString());

        // Test des getters et setters
        UUID newId = UUID.randomUUID();
        String newName = "New Name";
        String newDescription = "New Description";

        tag1.setId(newId);
        tag1.setName(newName);
        tag1.setDescription(newDescription);

        assertThat(tag1.getId()).isEqualTo(newId);
        assertThat(tag1.getName()).isEqualTo(newName);
        assertThat(tag1.getDescription()).isEqualTo(newDescription);
    }

    @Test
    public void testAllArgsConstructor() {
        UUID id = UUID.randomUUID();
        String name = "Test Name";
        String description = "Test Description";
        Instant createdDate = Instant.now();
        Instant lastModifiedDate = Instant.now();
        int version = 1;
        boolean enabled = true;
        boolean deleted = false;

        Tag tag = Tag.builder()
                .id(id)
                .name(name)
                .description(description)
                .createdDate(createdDate)
                .lastModifiedDate(lastModifiedDate)
                .version(version)
                .enabled(enabled)
                .deleted(deleted)
                .build();

        assertNotNull(tag);

        // Vérification des valeurs après initialisation
        assertThat(tag.getId()).isEqualTo(id);
        assertThat(tag.getName()).isEqualTo(name);
        assertThat(tag.getDescription()).isEqualTo(description);
        assertThat(tag.getCreatedDate()).isEqualTo(createdDate);
        assertThat(tag.getLastModifiedDate()).isEqualTo(lastModifiedDate);
        assertThat(tag.getVersion()).isEqualTo(version);
        assertThat(tag.isEnabled()).isEqualTo(enabled);
        assertThat(tag.isDeleted()).isEqualTo(deleted);
    }

    @Test
    public void testAllArgsConstructorWithNullValues() {
        Tag tag = Tag.builder()
                .id(null)
                .name(null)
                .description(null)
                .createdDate(null)
                .lastModifiedDate(null)
                .version(0)
                .enabled(false)
                .deleted(false)
                .build();


        assertNotNull(tag.toString());
        assertThat(tag).isNotNull();
        assertNull(tag.getId());
        assertNull(tag.getName());
        assertNull(tag.getDescription());
        assertNull(tag.getCreatedDate());
        assertNull(tag.getLastModifiedDate());
        assertEquals(0, tag.getVersion());
        assertFalse(tag.isEnabled());
        assertFalse(tag.isDeleted());
    }

    @Test
    public void testCollections() {
        Tag tag = new Tag();
        Set<Todo> todos = new HashSet<>();
        Todo todo1 = new Todo();
        Todo todo2 = new Todo();

        todos.add(todo1);
        tag.setTodos(todos);

        assertThat(tag.getTodos()).containsExactlyInAnyOrder(todo1);
        assertThat(tag.getTodos()).containsExactlyInAnyOrder(todo2);

        assertEquals(todos, tag.getTodos());
    }

}
