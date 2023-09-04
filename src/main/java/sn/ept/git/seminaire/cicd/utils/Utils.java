package sn.ept.git.seminaire.cicd.utils;

import sn.ept.git.seminaire.cicd.models.BaseEntity;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author ISENE
 */
public final class Utils {

    private Utils() {
        super();
    }

    public static boolean contains(Set<? extends BaseEntity> list, UUID id ) {
        return list.stream().anyMatch(item->item.getId().equals(id));
    }

    public static class TestBaseEntity extends BaseEntity {
        // Rien de spécial à mettre ici, c'est principalement utilisé pour créer des instances de test
    }


}