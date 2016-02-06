package com.example.sayem.remindme;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity {

    EditText personIDEditText;
    EditText passwordEditText;
    Button signupButton;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initializeAll();
    }

    private void initializeAll(){

        personIDEditText = (EditText) findViewById(R.id.personIDEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        signupButton = (Button) findViewById(R.id.signupButton);
        loginButton = (Button) findViewById(R.id.loginButton);

        signupButton.setText("Signup");
        loginButton.setText("Login");

    }
}
