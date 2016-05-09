package aut.bme.hu.eventr.repository;


import com.orm.SugarRecord;

import java.util.List;

import aut.bme.hu.eventr.EventRApplication;

public class SugarORMRepository implements Repository{

    public SugarORMRepository()
    {
        EventRApplication.injector.inject(this);
    }

    @Override
    public Long save(Object obj)
    {
        return SugarRecord.save(obj);
    }

    @Override
    public void delete(Object obj)
    {
        SugarRecord.delete(obj);
    }

    @Override
    public Object findById(Class<?> type, Long id)
    {
        return SugarRecord.findById(type, id);
    }

    @Override
    public List<?> find(Class<?> type, String clause) {
        return SugarRecord.find(type, clause);
    }
}
