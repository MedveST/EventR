package aut.bme.hu.eventr.repository;

public interface Repository {

    Long save(Object obj);

    void delete(Object obj);

    Object findById(Class<?> type, Long id);
}
