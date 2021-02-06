package com.gzeinnumer.mylibsavedinstancestate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.gzeinnumer.mylibsavedinstancestate.activity.MainActivity;
import com.gzeinnumer.mylibsavedinstancestate.fragment.FragmentTestActivity;
import com.gzeinnumer.mylibsavedinstancestate.image.ImageActivity;
import com.gzeinnumer.mylibsavedinstancestate.recyclerView.RecyclerViewActivity;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        findViewById(R.id.btn_example_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        findViewById(R.id.btn_example_fragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), FragmentTestActivity.class));
            }
        });

        findViewById(R.id.btn_example_imageview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ImageActivity.class));
            }
        });

        findViewById(R.id.btn_example_recyclerview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RecyclerViewActivity.class));
            }
        });
    }
}