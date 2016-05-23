package aut.bme.hu.eventr.repository;


import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.orm.SugarContext;
import com.orm.SugarDb;
import com.orm.SugarRecord;

import java.util.ArrayList;
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
    public Object update(Class<?> type, Long id, Object obj)
    {
        if (save(obj) != 0L) {
            return obj;
        }
        return null;
    }

    @Override
    public void delete(Object obj)
    {
        SugarRecord.delete(obj);
    }

    @Override
    public void deleteAll(Class<?> type)
    {
        SugarRecord.deleteAll(type);
    }

    @Override
    public Object findById(Class<?> type, Long id)
    {
        return SugarRecord.findById(type, id);
    }

    @Override
    public List<?> find(Class<?> type, String clause) {
        try {
            return SugarRecord.find(type, clause);
        }
        catch (SQLiteException ex)
        {
            Log.e("SugarORM", ex.getMessage());
        }

        return new ArrayList<>();
    }
}
