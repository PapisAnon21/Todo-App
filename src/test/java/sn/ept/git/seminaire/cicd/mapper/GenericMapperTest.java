package sn.ept.git.seminaire.cicd.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sn.ept.git.seminaire.cicd.mappers.generic.GenericMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GenericMapperTest {

    private GenericMapper<Entity, DTO> mapper;

    @BeforeEach
    void setUp() {
        mapper = new GenericMapper<Entity, DTO>() {
            @Override
            public Entity asEntity(DTO dto) {
                return new Entity(dto.getId(), dto.getName());
            }

            @Override
            public DTO asDTO(Entity entity) {
                return new DTO(entity.getId(), entity.getName());
            }
        };
    }

    @Test
    void testAsEntity() {
        DTO dto = new DTO(1L, "Test");
        Entity entity = mapper.asEntity(dto);

        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getName(), entity.getName());
    }

    @Test
    void testAsDTO() {
        Entity entity = new Entity(1L, "Test");
        DTO dto = mapper.asDTO(entity);

        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getName(), dto.getName());
    }

    @Test
    void testAsEntityList() {
        List<DTO> dtoList = new ArrayList<>();
        dtoList.add(new DTO(1L, "Test1"));
        dtoList.add(new DTO(2L, "Test2"));

        List<Entity> entityList = mapper.asEntityList(dtoList);

        assertEquals(2, entityList.size());
        assertEquals(dtoList.get(0).getId(), entityList.get(0).getId());
        assertEquals(dtoList.get(0).getName(), entityList.get(0).getName());
        assertEquals(dtoList.get(1).getId(), entityList.get(1).getId());
        assertEquals(dtoList.get(1).getName(), entityList.get(1).getName());
    }

    @Test
    void testAsDTOList() {
        List<Entity> entityList = new ArrayList<>();
        entityList.add(new Entity(1L, "Test1"));
        entityList.add(new Entity(2L, "Test2"));

        List<DTO> dtoList = mapper.asDTOList(entityList);

        assertEquals(2, dtoList.size());
        assertEquals(entityList.get(0).getId(), dtoList.get(0).getId());
        assertEquals(entityList.get(0).getName(), dtoList.get(0).getName());
        assertEquals(entityList.get(1).getId(), dtoList.get(1).getId());
        assertEquals(entityList.get(1).getName(), dtoList.get(1).getName());
    }
}

class Entity {
    private Long id;
    private String name;

    public Entity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

class DTO {
    private Long id;
    private String name;

    public DTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
