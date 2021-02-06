package com.gzeinnumer.mylibsavedinstancestate.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gzeinnumer.mylibsavedinstancestate.R;
import com.gzeinnumer.mylibsavedinstancestate.StateUI;
import com.gzeinnumer.mylibsavedinstancestate.StateUIBuilder;
import com.gzeinnumer.mylibsavedinstancestate.activity.MainActivity;

public class HomeFragment extends Fragment {
    public static final String TAG = "State_UI";

    TextView edUsername;
    TextView edPass;
    Button btnLogin, btnCancel;
    StateUI stateUI;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Toast.makeText(requireContext(), "Fragment_onCreate", Toast.LENGTH_SHORT).show();

        edUsername = view.findViewById(R.id.ed_username);
        edPass = view.findViewById(R.id.ed_pass);
        btnLogin = view.findViewById(R.id.btn_login);
        btnCancel = view.findViewById(R.id.btn_cancel);

        stateUI = StateUIBuilder.Build(MainActivity.class, requireContext());

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stateUI.clearState();
                startActivity(new Intent(requireContext(), MainActivity.class));
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().finish();
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        Toast.makeText(requireContext(), "Fragment_onPause", Toast.LENGTH_SHORT).show();
        stateUI.addView("edUsername", edUsername.getText().toString());
        stateUI.addView("edPass", edPass.getText().toString());
        stateUI.saveState();
    }

    @Override
    public void onResume() {
        super.onResume();
        Toast.makeText(requireContext(), "Fragment_onResume", Toast.LENGTH_SHORT).show();
        edUsername.setText(stateUI.getValue("edUsername"));
        edPass.setText(stateUI.getValue("edPass"));
    }
}