package aut.bme.hu.eventr.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import java.io.Serializable;

import javax.inject.Inject;

import aut.bme.hu.eventr.EventRApplication;
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
        EventRApplication.injector.inject(this);
        setContentView(R.layout.activity_schedule_event);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker);
        assert timePicker != null;
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute)
            {
                scheduleEventPresenter.onDateChanged(hourOfDay, minute);
            }
        });

        final EditText editText = (EditText) findViewById(R.id.titleEdit);
        assert editText != null;
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count){}
            @Override
            public void afterTextChanged(Editable s)
            {
                scheduleEventPresenter.onTitleChanged(s.toString());
            }
        });

        Button doneButton = (Button) findViewById(R.id.doneButton);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                scheduleEventPresenter.onDone();
            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            EventModel eventToEdit = (EventModel)extras.getSerializable("event_to_edit");
            if (eventToEdit != null) {
                Button removeButton = (Button) findViewById(R.id.removeButton);
                removeButton.setVisibility(View.VISIBLE);
                removeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        scheduleEventPresenter.onRemove();
                    }
                });

                scheduleEventPresenter.setupEdit(eventToEdit);

                final Long eventDate = eventToEdit.getDate().getTime();
                final Long dayBegin = eventDate - eventDate % (3600L * 24L * 1000L);
                final Long millisInDay = eventToEdit.getDate().getTime() - dayBegin;
                final Long hours = (millisInDay / (3600L * 1000L));
                timePicker.setCurrentHour(hours.intValue());

                final Long minutes = (millisInDay / (60L * 1000L)) - hours * 60L;
                timePicker.setCurrentMinute(minutes.intValue());

                editText.setText(eventToEdit.getTitle());
            }
        }
        else {
            Button removeButton = (Button) findViewById(R.id.removeButton);
            removeButton.setVisibility(View.INVISIBLE);
            scheduleEventPresenter.setupCreate();
        }

        scheduleEventPresenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        scheduleEventPresenter.detachView();
    }

    @Override
    public void leaveScreen()
    {
        finish();
    }
}
