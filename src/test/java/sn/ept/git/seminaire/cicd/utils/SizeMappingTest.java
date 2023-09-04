package sn.ept.git.seminaire.cicd.utils;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SizeMappingTest {

    @Test
    void testNameSizeMapping() {
        assertEquals(2, SizeMapping.Name.MIN);
        assertEquals(50, SizeMapping.Name.MAX);
    }

    @Test
    void testEmailSizeMapping() {
        assertEquals(5, SizeMapping.Email.MIN);
        assertEquals(50, SizeMapping.Email.MAX);
    }

    @Test
    void testPhoneSizeMapping() {
        assertEquals(5, SizeMapping.Phone.MIN);
        assertEquals(20, SizeMapping.Phone.MAX);
    }

    // Ajoutez d'autres tests similaires pour les autres constantes
}
