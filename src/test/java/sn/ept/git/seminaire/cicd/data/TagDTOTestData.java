package sn.ept.git.seminaire.cicd.data;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import sn.ept.git.seminaire.cicd.dto.TagDTO;
import sn.ept.git.seminaire.cicd.dto.base.TagBaseDTO;

public final class TagDTOTestData extends TestData {

    public static TagDTO defaultDTO() {
        return TagDTO
                .builder()
                .id(Default.id)
                .createdDate(Default.createdDate)
                .lastModifiedDate(Default.lastModifiedDate)
                .version(Default.version)
                .deleted(Default.deleted)
                .enabled(Default.enabled)
                .name(Default.name)
                .description(Default.description)
                .build();
    }

    public static TagDTO updatedDTO() {
        return TagDTO
                .builder()
                .id(Default.id)
                .createdDate(Update.createdDate)
                .lastModifiedDate(Update.lastModifiedDate)
                .version(Update.version)
                .deleted(Update.deleted)
                .enabled(Update.enabled)
                .name(Update.name)
                .description(Update.description)
                .build();
    }

    @Test
    public void testDefaultDTO() {
        TagDTO tagDTO = TagDTOTestData.defaultDTO();

        // Assurez-vous que les valeurs dans tagDTO correspondent aux valeurs par défaut dans TagDTOTestData.Default
        assertEquals(TagDTOTestData.Default.id, tagDTO.getId());
        assertEquals(TagDTOTestData.Default.createdDate, tagDTO.getCreatedDate());

        // Ajoutez d'autres assertions pour les autres champs
    }

    /*@Test
    public void testDefaultConstructor() {
        TagBaseDTO tagBaseDTO = new TagBaseDTO();
        assertNull(tagBaseDTO.getId());
        assertNull(tagBaseDTO.getCreatedDate());
        assertNull(tagBaseDTO.getLastModifiedDate());
        assertEquals(0, tagBaseDTO.getVersion());
        assertTrue(tagBaseDTO.isEnabled());
        assertFalse(tagBaseDTO.isDeleted());
    }*/

    @Test
    public void testAllArgsConstructor() {
        TagBaseDTO tagBaseDTO = TagBaseDTO.builder()
                .id(UUID.randomUUID())
                .createdDate(Instant.now())
                .lastModifiedDate(Instant.now())
                .version(2)
                .enabled(false)
                .deleted(true)
                .build();

        assertNotNull(tagBaseDTO.getId());
        assertNotNull(tagBaseDTO.getCreatedDate());
        assertNotNull(tagBaseDTO.getLastModifiedDate());
        assertEquals(2, tagBaseDTO.getVersion());
        assertFalse(tagBaseDTO.isEnabled());
        assertTrue(tagBaseDTO.isDeleted());
    }
}


