package aut.bme.hu.eventr.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.io.Serializable;

import javax.inject.Inject;

import aut.bme.hu.eventr.R;
import aut.bme.hu.eventr.model.EventModel;
import aut.bme.hu.eventr.presenter.ScheduleEventPresenter;

public class ScheduleEventActivity extends AppCompatActivity implements ScheduleEventView {

    @Inject
    ScheduleEventPresenter scheduleEventPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_event);
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

        Bundle extras = getIntent().getExtras();
        assert extras != null;

        Serializable eventToEdit = extras.getSerializable("event_to_edit");
        if (eventToEdit == null)
        {
            // TODO from UI elements
            //scheduleEventPresenter.setupCreate();
        }
        else
        {
            scheduleEventPresenter.setupEdit((EventModel) eventToEdit);
        }

    }

}
