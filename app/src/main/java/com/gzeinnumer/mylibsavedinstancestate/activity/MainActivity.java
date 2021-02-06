package com.gzeinnumer.mylibsavedinstancestate.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gzeinnumer.mylibsavedinstancestate.MenuActivity;
import com.gzeinnumer.mylibsavedinstancestate.StateUI;
import com.gzeinnumer.mylibsavedinstancestate.StateUIBuilder;
import com.gzeinnumer.mylibsavedinstancestate.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "State_UI";

    StateUI stateUI;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toast.makeText(this, "Activity_onCreate", Toast.LENGTH_SHORT).show();

        stateUI = StateUIBuilder.Build(MainActivity.class, getApplicationContext());

        binding.btnClearBack.setOnClickListener(v -> {
            stateUI.clearState();
            startActivity(new Intent(getApplicationContext(), MenuActivity.class));
        });

        binding.btnSaveBack.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), MenuActivity.class));
            finish();
        });

        binding.btnSaveClose.setOnClickListener(v -> {
            finishAffinity();
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "Activity_onPause", Toast.LENGTH_SHORT).show();
        stateUI.addView("binding.edUsername", binding.edUsername.getText().toString());
        stateUI.addView("binding.edPass", binding.edPass.getText().toString());
        stateUI.saveState();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "Activity_onResume", Toast.LENGTH_SHORT).show();
        binding.edUsername.setText(stateUI.getValue("binding.edUsername"));
        binding.edPass.setText(stateUI.getValue("binding.edPass"));
    }
}