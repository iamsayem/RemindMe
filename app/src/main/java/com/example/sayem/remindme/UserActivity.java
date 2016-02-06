package com.example.sayem.remindme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class UserActivity extends Activity {

    ImageView iconView;
    TextView appNameTextView;
    Button guestUserButton;
    Button registeredUserButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        initializeAll();

        registeredUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });

        guestUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivity.this, CategoryList.class);
                startActivity(intent);
            }
        });
    }

    private void initializeAll(){
        iconView = (ImageView) findViewById(R.id.iconView);
        appNameTextView = (TextView) findViewById(R.id.appNameTextView);
        guestUserButton = (Button) findViewById(R.id.guestUserButton);
        registeredUserButton = (Button) findViewById(R.id.registeredUserButton);

        appNameTextView.setText("Remind Me");
        guestUserButton.setText("Guest User");
        registeredUserButton.setText("Registered User");
    }

}