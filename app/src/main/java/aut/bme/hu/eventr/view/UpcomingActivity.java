package aut.bme.hu.eventr.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import javax.inject.Inject;

import aut.bme.hu.eventr.R;
import aut.bme.hu.eventr.presenter.LoginPresenter;
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

        upcomingPresenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        upcomingPresenter.detachView();
    }
}
