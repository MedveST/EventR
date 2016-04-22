package aut.bme.hu.eventr.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import javax.inject.Inject;

import aut.bme.hu.eventr.R;
import aut.bme.hu.eventr.presenter.LoginPresenter;

public class LoginActivity extends AppCompatActivity implements LoginView {

    @Inject
    LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        findViewById(R.id.loginButton).setOnClickListener(new Button.OnClickListener() {
                                                              @Override
                                                              public void onClick(View v) {
                                                                  EditText emailEditText = (EditText) findViewById(R.id.loginEditEMail);
                                                                  EditText passEditText = (EditText) findViewById(R.id.loginEditPassword);

                                                                  loginPresenter.loginOrSignUp(emailEditText.getText().toString(), passEditText.getText().toString());
                                                              }
                                                          }
        );
    }

    @Override
    public void leaveScreen() {
        // TODO
    }
}
