package sn.ept.git.seminaire.cicd.data;

import org.junit.jupiter.api.Test;

import lombok.AllArgsConstructor;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.util.UUID;

import sn.ept.git.seminaire.cicd.dto.base.TagBaseDTO;
import sn.ept.git.seminaire.cicd.dto.vm.TagVM;

public final class TagVMTestData extends TestData {

    public static TagVM defaultVM() {
        return TagVM
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

    public static TagVM updatedVM() {
        return TagVM
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
    public void testCreateTagVM() {
        TagVM tagVM = TagVM.builder()
                .name("TagName")
                .description("TagDescription")
                .build();

        assertEquals("TagName", tagVM.getName());
        assertEquals("TagDescription", tagVM.getDescription());
    }

    /*@Test
    public void testDefaultConstructor() {
        TagVM tagVM = new TagVM();
        assertNull(tagVM.getId());
        assertNull(tagVM.getCreatedDate());
        assertNull(tagVM.getLastModifiedDate());
        assertEquals(0, tagVM.getVersion());
        assertTrue(tagVM.isEnabled());
        assertFalse(tagVM.isDeleted());
    }
    */
    @Test
    public void testAllArgsConstructor() {
        TagVM tagVM = TagVM.builder()
                .id(UUID.randomUUID())
                .createdDate(Instant.now())
                .lastModifiedDate(Instant.now())
                .version(2)
                .enabled(false)
                .deleted(true)
                .build();

        assertNotNull(tagVM.getId());
        assertNotNull(tagVM.getCreatedDate());
        assertNotNull(tagVM.getLastModifiedDate());
        assertEquals(2, tagVM.getVersion());
        assertFalse(tagVM.isEnabled());
        assertTrue(tagVM.isDeleted());
    }

    @Test
    public void testToString() {
        TagVM vm = TagVM.builder().name("Name").description("Description").build();
        assertNotNull(vm.toString());
    }

    @Test
    public void testEqualsAndHashCode() {
        TagVM vm1 = TagVM.builder().name("Name").description("Description").build();
        TagVM vm2 = TagVM.builder().name("Name").description("Description").build();

        assertTrue(vm1.equals(vm2));
        assertTrue(vm2.equals(vm1));
        assertEquals(vm1.hashCode(), vm2.hashCode());
    }

    @Test
    public void testNotNullFields() {
        TagVM vm = TagVM.builder().name("Name").description("Description").build();
        assertNotNull(vm.getName());
        assertNotNull(vm.getDescription());
    }

    @Test
    public void testNullFields() {
        TagVM vm = TagVM.builder().build();
        assertNull(vm.getName());
        assertNull(vm.getDescription());
    }

    @Test
    public void testEqualsWithNull() {
        TagVM vm = TagVM.builder().name("Name").description("Description").build();
        assertFalse(vm.equals(null));
    }

    @Test
    public void testEqualsWithDifferentClass() {
        TagVM vm = TagVM.builder().name("Name").description("Description").build();
        assertFalse(vm.equals("NotATagVM"));
    }
}



