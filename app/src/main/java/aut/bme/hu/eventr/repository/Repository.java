package aut.bme.hu.eventr.repository;

import java.util.List;

public interface Repository {

    Long save(Object obj);

    Object update(Class<?> type, Long id, Object obj);

    void delete(Object obj);

    void deleteAll(Class<?> type);

    Object findById(Class<?> type, Long id);

    List<?> find(Class<?> type, String clause);
}
