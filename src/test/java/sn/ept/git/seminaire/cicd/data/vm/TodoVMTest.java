package sn.ept.git.seminaire.cicd.data.vm;

import org.junit.jupiter.api.Test;
import sn.ept.git.seminaire.cicd.dto.vm.TodoVM;

import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TodoVMTest {

    @Test
    void testGettersAndSetters() {
        TodoVM todoVM = new TodoVM();

        // Test setters
        todoVM.setId(UUID.randomUUID());
        todoVM.setTitle("Test Title");
        todoVM.setDescription("Test Description");
        todoVM.setCompleted(true);
        todoVM.setTags(Set.of(UUID.randomUUID()));

        // Test getters
        assertEquals(todoVM.getId(), todoVM.getId());
        assertEquals("Test Title", todoVM.getTitle());
        assertEquals("Test Description", todoVM.getDescription());
        assertTrue(todoVM.isCompleted());
        assertEquals(1, todoVM.getTags().size());
    }

    @Test
    void testEqualsAndHashCode() {
        TodoVM todoVM1 = new TodoVM();
        TodoVM todoVM2 = new TodoVM();

        assertEquals(todoVM1, todoVM2);
        assertEquals(todoVM1.hashCode(), todoVM2.hashCode());
    }

    @Test
    void testToString() {
        TodoVM todoVM = new TodoVM();
        todoVM.setTitle("Test Title");
        todoVM.setDescription("Test Description");

        String expectedToString = "TodoVM(tags=null)";
        assertEquals(expectedToString, todoVM.toString());
    }


}

