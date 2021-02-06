package com.gzeinnumer.mylibsavedinstancestate.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gzeinnumer.mylibsavedinstancestate.R;
import com.gzeinnumer.mylibsavedinstancestate.StateUI;
import com.gzeinnumer.mylibsavedinstancestate.StateUIBuilder;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "State_UI";

    TextView edUsername;
    TextView edPass;
    Button btnLogin, btnCancel;
    StateUI stateUI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "Main_onCreate", Toast.LENGTH_SHORT).show();

        edUsername = findViewById(R.id.ed_username);
        edPass = findViewById(R.id.ed_pass);
        btnLogin = findViewById(R.id.btn_login);
        btnCancel = findViewById(R.id.btn_cancel);

        stateUI = StateUIBuilder.Build(MainActivity.class, getApplicationContext());

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stateUI.clearState();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "Main_onPause", Toast.LENGTH_SHORT).show();
        stateUI.addView("edUsername", edUsername.getText().toString());
        stateUI.addView("edPass", edPass.getText().toString());
        stateUI.saveState();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "Main_onResume", Toast.LENGTH_SHORT).show();
        edUsername.setText(stateUI.getValue("edUsername"));
        edPass.setText(stateUI.getValue("edPass"));
    }
}