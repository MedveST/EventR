package aut.bme.hu.eventr.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.Calendar;
import java.util.List;

import aut.bme.hu.eventr.R;
import aut.bme.hu.eventr.presenter.CalendarPresenter;

public class CalendarActivity extends AppCompatActivity implements CalendarView {

    CalendarPresenter calendarPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        calendarPresenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        calendarPresenter.detachView();
    }

    @Override
    public void selectDay() {
        // TODO
    }

    @Override
    public void navigateToMonth() {
        // TODO
    }

    @Override
    public void highlightDays(List<Long> dates) {
        // TODO
    }

    @Override
    public void unhighlightDay(Long date) {
        // TODO
    }
}
