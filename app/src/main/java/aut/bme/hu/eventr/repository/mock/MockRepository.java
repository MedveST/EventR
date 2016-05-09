package aut.bme.hu.eventr.repository.mock;

import java.lang.reflect.Field;
import java.util.HashMap;

import aut.bme.hu.eventr.EventRApplication;
import aut.bme.hu.eventr.repository.Repository;

public class MockRepository implements Repository {
    private Long nextId = 0L;
    private HashMap<Long, Object> database;

    public MockRepository()
    {
        EventRApplication.injector.inject(this);
    }

    @Override
    public Long save(Object obj)
    {
        database.put(nextId, obj);
        nextId += 1;
        return nextId - 1;
    }

    @Override
    public void delete(Object obj)
    {
        try {
            Class<?> type = obj.getClass();
            Field field = type.getDeclaredField("id");
            field.setAccessible(true);
            Long id = (Long) field.get(obj);
            database.remove(id);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object findById(Class<?> type, Long id)
    {
        return database.get(id);
    }
}
