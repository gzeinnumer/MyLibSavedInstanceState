package com.gzeinnumer.mylibsavedinstancestate.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.gzeinnumer.mylibsavedinstancestate.MenuActivity;
import com.gzeinnumer.mylibsavedinstancestate.StateUI;
import com.gzeinnumer.mylibsavedinstancestate.StateUIBuilder;
import com.gzeinnumer.mylibsavedinstancestate.activity.MainActivity;
import com.gzeinnumer.mylibsavedinstancestate.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {
    public static final String TAG = "State_UI";

    private FragmentHomeBinding binding;

    StateUI stateUI;

    public HomeFragment() {
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toast.makeText(requireContext(), "Fragment_onViewCreated", Toast.LENGTH_SHORT).show();

        stateUI = StateUIBuilder.Build(MainActivity.class, requireContext());

        binding.btnClearBack.setOnClickListener(v -> {
            stateUI.clearState();
            startActivity(new Intent(requireContext(), MenuActivity.class));
        });

        binding.btnSaveBack.setOnClickListener(v -> {
            startActivity(new Intent(requireContext(), MenuActivity.class));
            requireActivity().finish();
        });

        binding.btnSaveClose.setOnClickListener(v -> {
            requireActivity().finishAffinity();
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        Toast.makeText(requireContext(), "Fragment_onPause", Toast.LENGTH_SHORT).show();
        stateUI.addView("binding.edUsername", binding.edUsername.getText().toString());
        stateUI.addView("binding.edPass", binding.edPass.getText().toString());
        stateUI.saveState();
    }

    @Override
    public void onResume() {
        super.onResume();
        Toast.makeText(requireContext(), "Fragment_onResume", Toast.LENGTH_SHORT).show();
        binding.edUsername.setText(stateUI.getValue("binding.edUsername"));
        binding.edPass.setText(stateUI.getValue("binding.edPass"));
    }
}