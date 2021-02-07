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
import com.gzeinnumer.mylibsavedinstancestate.databinding.FragmentHomeBinding;
import com.gzeinnumer.mylibsavedinstancestate.utils.CustomToast;

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

        stateUI = StateUIBuilder.Build(HomeFragment.class, requireContext());

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
        new CustomToast(requireContext(), "Fragment_onPause : Data Save To State");
        stateUI.addView("binding.edUsername", binding.edUsername.getText().toString());
        stateUI.addView("binding.edPass", binding.edPass.getText().toString());
        stateUI.saveState();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (stateUI.getState()) {
            new CustomToast(requireContext(), "Fragment_onResume : Data Loaded From State");
            String userName = stateUI.getValue("binding.edUsername");
            binding.edUsername.setText(userName);
            String pass = stateUI.getValue("binding.edPass");
            binding.edPass.setText(pass);
        }
    }
}