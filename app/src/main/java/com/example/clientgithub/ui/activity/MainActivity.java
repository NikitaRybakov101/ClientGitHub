package com.example.clientgithub.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.clientgithub.R;
import com.example.clientgithub.databinding.ActivityMainBinding;
import com.example.clientgithub.ui.fragments.FragmentViewRepository;
import com.example.clientgithub.ui.spashFragment.SplashFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}