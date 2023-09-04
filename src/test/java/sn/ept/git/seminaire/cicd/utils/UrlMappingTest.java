package sn.ept.git.seminaire.cicd.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UrlMappingTest {

    @Test
    void testTodoUrls() {
        assertEquals("/api/todos", UrlMapping.Todo.BASE);
        assertEquals("/api/todos/{id}", UrlMapping.Todo.FIND_BY_ID);
        // Ajoutez d'autres assertions pour les autres constantes Todo
    }

    @Test
    void testTagUrls() {
        assertEquals("/api/tags", UrlMapping.Tag.BASE);
        assertEquals("/api/tags/{id}", UrlMapping.Tag.FIND_BY_ID);
        // Ajoutez d'autres assertions pour les autres constantes Tag
    }

    @Test
    void testDefaultUrls() {
        assertEquals("/_health", UrlMapping.Default.HEALTH);
        assertEquals("/", UrlMapping.Default.INDEX);
    }
}
