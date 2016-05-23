package aut.bme.hu.eventr.view;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import aut.bme.hu.eventr.EventRApplication;
import aut.bme.hu.eventr.R;
import aut.bme.hu.eventr.model.EventModel;
import aut.bme.hu.eventr.presenter.UpcomingPresenter;

public class UpcomingActivity extends AppCompatActivity implements UpcomingView {

    @Inject
    UpcomingPresenter upcomingPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        Drawable drawable = getResources().getDrawable(R.drawable.ic_more_vert_black_24dp);
        toolbar.setOverflowIcon(drawable);

        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(UpcomingActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });

        EventRApplication.injector.inject(this);
        upcomingPresenter.attachView(this);

        upcomingPresenter.setupEventList();
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        upcomingPresenter.setupEventList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_upcoming, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intent = new Intent(UpcomingActivity.this, SettingsActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        upcomingPresenter.detachView();
    }

    @Override
    public void fillList(List<EventModel> events)
    {
        final ListView listView = (ListView)findViewById(R.id.listView);
        EventAdapter adapter = new EventAdapter(getApplicationContext(), R.id.listView, events);
        listView.setAdapter( adapter );

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                EventModel item = ((EventModel)listView.getAdapter().getItem(position));

                Bundle bundle = new Bundle();
                bundle.putSerializable("event_to_edit", item);

                Intent intent = new Intent(UpcomingActivity.this, ScheduleEventActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
