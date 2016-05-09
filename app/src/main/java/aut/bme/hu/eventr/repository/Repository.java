package aut.bme.hu.eventr.repository;

import java.util.List;

public interface Repository {

    Long save(Object obj);

    void delete(Object obj);

    Object findById(Class<?> type, Long id);

    List<?> find(Class<?> type, String clause);
}
