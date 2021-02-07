package com.gzeinnumer.mylibsavedinstancestate.image;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.gzeinnumer.mylibsavedinstancestate.MenuActivity;
import com.gzeinnumer.mylibsavedinstancestate.R;
import com.gzeinnumer.mylibsavedinstancestate.StateUI;
import com.gzeinnumer.mylibsavedinstancestate.StateUIBuilder;
import com.gzeinnumer.mylibsavedinstancestate.databinding.ActivityImageBinding;
import com.gzeinnumer.mylibsavedinstancestate.utils.CustomToast;

public class ImageActivity extends AppCompatActivity {
    StateUI stateUI;
    private ActivityImageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityImageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toast.makeText(getApplicationContext(), "Image_onCreate", Toast.LENGTH_SHORT).show();

        stateUI = StateUIBuilder.Build(ImageActivity.class, getApplicationContext());

        binding.btnLoadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadImage();
            }
        });

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

    private void loadImage() {
        String imgUrl = "https://avatars3.githubusercontent.com/u/45892408?s=460&u=94158c6479290600dcc39bc0a52c74e4971320fc&v=4";
        Glide.with(this).load(imgUrl).error(R.mipmap.ic_launcher).into(binding.img);
    }

    @Override
    protected void onPause() {
        super.onPause();
        new CustomToast(getApplicationContext(), "Image_onPause : Data Save To State");

        try {
            stateUI.addView("binding.img", (BitmapDrawable) binding.img.getDrawable());
        } catch (Exception e) {
            e.printStackTrace();
        }
        stateUI.saveState();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (stateUI.getState()) {
            new CustomToast(getApplicationContext(), "Image_onResume : Data Loaded From State");

            Bitmap bitmap = stateUI.getValueBitmap("binding.img");
            if (bitmap != null) {
                binding.img.setImageBitmap(bitmap);
            }
        }
    }
}