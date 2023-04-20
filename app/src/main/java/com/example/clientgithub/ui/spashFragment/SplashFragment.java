package com.example.clientgithub.ui.spashFragment;

import static com.example.clientgithub.sharedPreference.SharedPreference.TOKEN_KEY;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.clientgithub.R;
import com.example.clientgithub.databinding.FragmentSplashBinding;
import com.example.clientgithub.sharedPreference.SharedPreference;
import com.example.clientgithub.ui.fragments.FragmentAuthentication;
import com.example.clientgithub.ui.fragments.FragmentViewRepository;

public class SplashFragment extends Fragment {

    private FragmentSplashBinding binding;
    private static final long TIME_ANIMATION_MILLIS = 6000L;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSplashBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {

                String token = SharedPreference.loadToken(TOKEN_KEY,requireActivity());

                if(!token.isEmpty()) {
                    navigateToFragmentViewRepository();
                } else  {
                    navigateToFragmentAuthentication();
                }
            }
        }, TIME_ANIMATION_MILLIS);

        initLoadingSplash();
    }

    private void navigateToFragmentAuthentication() {
        NavHostFragment.findNavController(this).navigate(R.id.action_splashScreenFragment_to_fragmentAuthentication);
    }

    private void navigateToFragmentViewRepository() {
        NavHostFragment.findNavController(this).navigate(R.id.action_splashScreenFragment_to_fragmentViewRepository);
    }

    private void initLoadingSplash() {
        binding.nameApp.setText(getString(R.string.app_name));
    }
}
