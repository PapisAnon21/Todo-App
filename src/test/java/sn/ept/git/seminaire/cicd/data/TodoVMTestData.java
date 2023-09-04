package sn.ept.git.seminaire.cicd.data;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.util.HashSet;
import java.util.UUID;

import java.util.Set;


import org.junit.jupiter.api.Test;
import sn.ept.git.seminaire.cicd.dto.vm.TodoVM;

public final class TodoVMTestData extends TestData {

    public static TodoVM defaultVM() {
        return TodoVM
                .builder()
                .id(Default.id)
                .createdDate(Default.createdDate)
                .lastModifiedDate(Default.lastModifiedDate)
                .version(Default.version)
                .deleted(Default.deleted)
                .enabled(Default.enabled)
                .title(Default.title)
                .description(Default.description)
                .build();
    }
    public static TodoVM updatedVM() {
        return TodoVM
                .builder()
                .id(Default.id)
                .createdDate(Update.createdDate)
                .lastModifiedDate(Update.lastModifiedDate)
                .version(Update.version)
                .deleted(Update.deleted)
                .enabled(Update.enabled)
                .title(Update.title)
                .description(Update.description)
                .build();
    }

        @Test
        public void testCreateTodoVM() {
            TodoVM todoVM = TodoVM.builder()
                    .title("TodoTitle")
                    .description("TodoDescription")
                    .completed(true)
                    .build();

            assertEquals("TodoTitle", todoVM.getTitle());
            assertEquals("TodoDescription", todoVM.getDescription());
            assertTrue(todoVM.isCompleted());
        }


    @Test
    public void testAllArgsConstructor() {
        Set<UUID> tags = new HashSet<>();
        tags.add(UUID.randomUUID());

        TodoVM todoVM = TodoVM.builder()
                .id(UUID.randomUUID())
                .createdDate(Instant.now())
                .lastModifiedDate(Instant.now())
                .version(2)
                .enabled(false)
                .deleted(true)
                .title("Sample Title")
                .description("Sample Description")
                .completed(true)
                .tags(tags)
                .build();

        assertNotNull(todoVM.getId());
        assertNotNull(todoVM.getCreatedDate());
        assertNotNull(todoVM.getLastModifiedDate());
        assertEquals(2, todoVM.getVersion());
        assertFalse(todoVM.isEnabled());
        assertTrue(todoVM.isDeleted());
        assertEquals("Sample Title", todoVM.getTitle());
        assertEquals("Sample Description", todoVM.getDescription());
        assertTrue(todoVM.isCompleted());
        assertEquals(tags, todoVM.getTags());
    }
}
