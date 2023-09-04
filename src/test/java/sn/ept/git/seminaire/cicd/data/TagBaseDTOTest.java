package sn.ept.git.seminaire.cicd.data;

import org.junit.jupiter.api.Test;

import sn.ept.git.seminaire.cicd.dto.base.BaseDTO;
import sn.ept.git.seminaire.cicd.dto.base.TagBaseDTO;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Instant;
import java.util.UUID;

public class TagBaseDTOTest {

    @Test
    public void testCreateTagBaseDTO() {
        TagBaseDTO tagBaseDTO = TagBaseDTO.builder()
                .name("TagName")
                .description("TagDescription")
                .build();

        assertEquals("TagName", tagBaseDTO.getName());
        assertEquals("TagDescription", tagBaseDTO.getDescription());
    }

    // Ajoutez d'autres tests ici

    @Test
    public void testConstructorsAndGettersSetters() {
        // Créer une instance de TagBaseDTO en utilisant le constructeur par défaut
        TagBaseDTO tagBaseDTO = new TagBaseDTO();

        // Définir les valeurs des propriétés
        String name = "Tag Name";
        String description = "Tag Description";

        tagBaseDTO.setName(name);
        tagBaseDTO.setDescription(description);

        // Vérifier que les valeurs sont correctement récupérées via les getters
        assertEquals(name, tagBaseDTO.getName());
        assertEquals(description, tagBaseDTO.getDescription());

        // Créer une nouvelle instance de TagBaseDTO en utilisant le constructeur avec des arguments
        TagBaseDTO tagBaseDTO2 = new TagBaseDTO(name, description);

        // Vérifier que les valeurs sont correctement récupérées via les getters
        assertEquals(name, tagBaseDTO2.getName());
        assertEquals(description, tagBaseDTO2.getDescription());
    }

    @Test
    public void testBaseDTO() {
        // Créez une instance de BaseDTO en utilisant la méthode générée automatiquement par Lombok
        BaseDTO baseDTO = BaseDTO.of(null, null, null, 0, false, false);

        // Vérifiez que les valeurs par défaut sont correctes
        assertEquals(null, baseDTO.getId());
        assertEquals(null, baseDTO.getCreatedDate());
        assertEquals(null, baseDTO.getLastModifiedDate());
        assertEquals(0, baseDTO.getVersion());
        assertFalse(baseDTO.isEnabled());
        assertFalse(baseDTO.isDeleted());

        // Modifiez certaines valeurs
        UUID id = UUID.randomUUID();
        Instant createdDate = Instant.now();
        Instant lastModifiedDate = Instant.now();
        int version = 1;
        boolean enabled = true;
        boolean deleted = true;

        baseDTO.setId(id);
        baseDTO.setCreatedDate(createdDate);
        baseDTO.setLastModifiedDate(lastModifiedDate);
        baseDTO.setVersion(version);
        baseDTO.setEnabled(enabled);
        baseDTO.setDeleted(deleted);

        // Vérifiez que les valeurs ont été correctement définies
        assertEquals(id, baseDTO.getId());
        assertEquals(createdDate, baseDTO.getCreatedDate());
        assertEquals(lastModifiedDate, baseDTO.getLastModifiedDate());
        assertEquals(version, baseDTO.getVersion());
        assertTrue(baseDTO.isEnabled());
        assertTrue(baseDTO.isDeleted());
    }

    @Test
    public void testEquals() {
        TagBaseDTO dto1 = TagBaseDTO.builder().name("Name").description("Description").build();
        TagBaseDTO dto2 = TagBaseDTO.builder().name("Name").description("Description").build();
        assertTrue(dto1.equals(dto2));
    }

    @Test
    public void testHashCode() {
        TagBaseDTO dto1 = TagBaseDTO.builder().name("Name").description("Description").build();
        TagBaseDTO dto2 = TagBaseDTO.builder().name("Name").description("Description").build();
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    public void testToString() {
        TagBaseDTO dto = TagBaseDTO.builder().name("Name").description("Description").build();
        String expectedToString = "TagBaseDTO(name=Name, description=Description)";
        assertEquals(expectedToString, dto.toString());
    }

    @Test
    public void testGettersAndSetters() {
        TagBaseDTO dto = TagBaseDTO.builder().name("Name").description("Description").build();
        assertEquals("Name", dto.getName());
        assertEquals("Description", dto.getDescription());

        dto.setName("NewName");
        dto.setDescription("NewDescription");

        assertEquals("NewName", dto.getName());
        assertEquals("NewDescription", dto.getDescription());
    }

    @Test
    public void testEqualsAndHashCode() {
        TagBaseDTO dto1 = TagBaseDTO.builder().name("Name").description("Description").build();
        TagBaseDTO dto2 = TagBaseDTO.builder().name("Name").description("Description").build();
        TagBaseDTO dto3 = TagBaseDTO.builder().name("DifferentName").description("Description").build();

        assertTrue(dto1.equals(dto2));
        assertTrue(dto2.equals(dto1));
        assertFalse(dto1.equals(dto3));
        assertFalse(dto2.equals(dto3));

        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertNotEquals(dto1.hashCode(), dto3.hashCode());
        assertNotEquals(dto2.hashCode(), dto3.hashCode());
    }

    @Test
    public void testNotNullFields() {
        TagBaseDTO dto = TagBaseDTO.builder().name("Name").description("Description").build();
        assertNotNull(dto.getName());
        assertNotNull(dto.getDescription());
    }

    @Test
    public void testNullFields() {
        TagBaseDTO dto = TagBaseDTO.builder().build();
        assertNull(dto.getName());
        assertNull(dto.getDescription());
    }

    @Test
    public void testEqualsWithNull() {
        TagBaseDTO dto = TagBaseDTO.builder().name("Name").description("Description").build();
        assertFalse(dto.equals(null));
    }

    @Test
    public void testEqualsWithDifferentClass() {
        TagBaseDTO dto = TagBaseDTO.builder().name("Name").description("Description").build();
        assertFalse(dto.equals("NotATagBaseDTO"));
    }
}
