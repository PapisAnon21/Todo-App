package sn.ept.git.seminaire.cicd.models;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class BaseEntityTest {

    @Test
    public void toStringShouldReturnExpectedString() {
        BaseEntity entity = BaseEntity.builder()
                .id(UUID.randomUUID())
                .createdDate(Instant.now())
                .lastModifiedDate(Instant.now())
                .version(1)
                .enabled(true)
                .deleted(false)
                .build();

        String expectedString = "BaseEntity(id=" + entity.getId() +
                ", createdDate=" + entity.getCreatedDate() +
                ", lastModifiedDate=" + entity.getLastModifiedDate() +
                ", version=" + entity.getVersion() +
                ", enabled=" + entity.isEnabled() +
                ", deleted=" + entity.isDeleted() + ")";

        assertThat(entity.toString()).isEqualTo(expectedString);
    }
    /*
    @Test
    public void dataAnnotationShouldGenerateGetterSetterEqualsHashCodeToString() {
        UUID fixedUUID = UUID.fromString("a7a341f8-6355-4d73-b2ca-0eee9c72ee2e");

        BaseEntity entity1 = BaseEntity.builder()
                .id(fixedUUID)
                .createdDate(Instant.now())
                .lastModifiedDate(Instant.now())
                .version(1)
                .enabled(true)
                .deleted(false)
                .build();

        BaseEntity entity2 = BaseEntity.builder()
                .id(fixedUUID)
                .createdDate(Instant.now())
                .lastModifiedDate(Instant.now())
                .version(1)
                .enabled(true)
                .deleted(false)
                .build();

        assertThat(entity1).isEqualTo(entity2);
        assertThat(entity1.hashCode()).isEqualTo(entity2.hashCode());

        entity2.setEnabled(false);
        assertThat(entity1).isNotEqualTo(entity2);
        assertThat(entity1.hashCode()).isNotEqualTo(entity2.hashCode());
    }

     */

    @Test
    public void testDefaultConstructor() {
        BaseEntity entity = new BaseEntity();
        assertThat(entity.getId()).isNull();
        assertThat(entity.getCreatedDate()).isNotNull();
        assertThat(entity.getLastModifiedDate()).isNotNull();
        assertThat(entity.getVersion()).isEqualTo(0);
        assertThat(entity.isEnabled()).isTrue();
        assertThat(entity.isDeleted()).isFalse();
    }

    @Test
    public void testSetters() {
        UUID fixedUUID = UUID.fromString("a7a341f8-6355-4d73-b2ca-0eee9c72ee2e");
        Instant newCreatedDate = Instant.parse("2023-08-28T00:00:00.00Z");
        Instant newLastModifiedDate = Instant.parse("2023-08-29T00:00:00.00Z");


        BaseEntity entity = BaseEntity.builder()
                .id(fixedUUID)
                .createdDate(Instant.now())
                .lastModifiedDate(Instant.now())
                .version(1)
                .enabled(true)
                .deleted(false)
                .build();

        entity.setCreatedDate(newCreatedDate);
        assertThat(entity.getCreatedDate()).isEqualTo(newCreatedDate);

        entity.setLastModifiedDate(newLastModifiedDate);
        assertThat(entity.getLastModifiedDate()).isEqualTo(newLastModifiedDate);

        entity.setVersion(2);
        assertThat(entity.getVersion()).isEqualTo(2);

        entity.setEnabled(false);
        assertThat(entity.isEnabled()).isFalse();

        entity.setDeleted(true);
        assertThat(entity.isDeleted()).isTrue();
    }

    @Test
    public void testBuilder() {
        UUID fixedUUID = UUID.fromString("a7a341f8-6355-4d73-b2ca-0eee9c72ee2e");
        Instant newCreatedDate = Instant.parse("2023-08-28T00:00:00.00Z");

        BaseEntity entity = BaseEntity.builder()
                .id(fixedUUID)
                .createdDate(newCreatedDate)
                .version(2)
                .enabled(false)
                .build();

        assertThat(entity.getId()).isEqualTo(fixedUUID);
        assertThat(entity.getCreatedDate()).isEqualTo(newCreatedDate);
        assertThat(entity.getVersion()).isEqualTo(2);
        assertThat(entity.isEnabled()).isFalse();
        assertThat(entity.isDeleted()).isFalse(); // Par défaut, le champ deleted est à false
    }



}
