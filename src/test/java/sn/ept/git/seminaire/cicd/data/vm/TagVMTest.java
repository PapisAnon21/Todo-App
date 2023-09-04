package sn.ept.git.seminaire.cicd.data.vm;

import org.junit.jupiter.api.Test;
import sn.ept.git.seminaire.cicd.dto.vm.TagVM;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;



public class TagVMTest {

    @Test
    void dataAnnotationShouldGenerateGetterAndSetterMethods() {
        TagVM tagVM = new TagVM();

        tagVM.setName("Tag Name");
        tagVM.setDescription("Tag Description");

        assertEquals("Tag Name", tagVM.getName());
        assertEquals("Tag Description", tagVM.getDescription());
    }

    @Test
    void testGettersAndSetters() {
        TagVM tagVM = new TagVM();

        // Utilisez les setters pour définir les valeurs
        UUID uuid = UUID.randomUUID();
        tagVM.setId(uuid);
        tagVM.setName("TagName");

        // Utilisez les getters pour obtenir les valeurs
        assertEquals(uuid, tagVM.getId());
        assertEquals("TagName", tagVM.getName());
    }

    @Test
    void testEqualsAndHashCode() {
        UUID uuid = UUID.randomUUID();

        TagVM tagVM1 = new TagVM();
        tagVM1.setId(uuid);
        tagVM1.setName("TagName");

        TagVM tagVM2 = new TagVM();
        tagVM2.setId(uuid);
        tagVM2.setName("TagName");

        // Les instances avec les mêmes valeurs devraient être égales
        assertEquals(tagVM1, tagVM2);
        assertEquals(tagVM1.hashCode(), tagVM2.hashCode());
    }

    @Test
    void testToString() {
        UUID uuid = UUID.randomUUID();

        TagVM tagVM = new TagVM();
        tagVM.setId(uuid);
        tagVM.setName("TagName");

        String expectedToString = "TagVM()";
        assertEquals(expectedToString, tagVM.toString());
    }

}
