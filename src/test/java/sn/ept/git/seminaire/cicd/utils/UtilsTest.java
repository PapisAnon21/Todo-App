package sn.ept.git.seminaire.cicd.utils;

import org.junit.jupiter.api.Test;

import com.mysql.cj.util.TestUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    @Test
    void testContains() {
        Utils.TestBaseEntity entity1 = new Utils.TestBaseEntity();
        entity1.setId(UUID.randomUUID());

        Utils.TestBaseEntity entity2 = new Utils.TestBaseEntity();
        entity2.setId(UUID.randomUUID());

        Set<Utils.TestBaseEntity> entitySet = new HashSet<>(Arrays.asList(entity1, entity2));

        assertTrue(Utils.contains(entitySet, entity1.getId()));
        assertFalse(Utils.contains(entitySet, UUID.randomUUID()));
    }

    // ... Autres tests


}
