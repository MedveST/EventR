package aut.bme.hu.eventr.repository.mock;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import aut.bme.hu.eventr.EventRApplication;
import aut.bme.hu.eventr.repository.Repository;

public class MockRepository implements Repository {
    private Long nextId = 0L;
    private HashMap<Long, Object> database = new HashMap<>();

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
    public Object update(Class<?> type, Long id, Object obj)
    {
        Object storedObj = findById(type, id);
        if (storedObj != null) {
            storedObj = obj;
            return storedObj;
        }
        return null;
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

    public void deleteAll(Class<?> type)
    {
        Collection<Object> objs = database.values();
        List<Object> toRemove = new ArrayList<Object>();
        for (Object obj : objs)
        {
            if (type.isInstance(obj))
            {
                toRemove.add(obj);
            }
        }

        objs.removeAll(toRemove);
    }

    @Override
    public Object findById(Class<?> type, Long id)
    {
        return database.get(id);
    }

    // very simple query parsing for one string equals clause
    @Override
    public List<?> find(Class<?> type, String clause) {
        int equalsIndex = clause.indexOf('=');
        String fieldName = clause.substring(0, equalsIndex);
        String fieldValue = clause.substring(equalsIndex + 1);

        try {
            for (Object obj : database.values()) {
                if (obj.getClass() == type) {
                    Field field = type.getDeclaredField(fieldName);
                    field.setAccessible(true);
                    String objectFieldValue = (String)field.get(obj);
                    if (objectFieldValue == fieldValue) {
                        List<Object> result = new ArrayList<Object>();
                        result.add(obj);
                        return result;
                    }
                }
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
