package com.example.clientgithub.ui.fragments;

import android.content.Intent;
import android.media.tv.TvInputInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.clientgithub.R;
import com.example.clientgithub.dataSource.authenticationSource.Code;
import com.example.clientgithub.dataSource.authenticationSource.Token;
import com.example.clientgithub.databinding.FragmentAuthenticationBinding;
import com.example.clientgithub.sharedPreference.SharedPreference;
import com.example.clientgithub.ui.viewModel.FragmentViewModelAuthentication;
import com.example.clientgithub.ui.viewModel.dataSourse.StateData;

public class FragmentAuthentication extends Fragment {
    private FragmentAuthenticationBinding binding;

    private final FragmentViewModelAuthentication viewModel = new FragmentViewModelAuthentication();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAuthenticationBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViewModel();
        buttonAuthentication();
    }

    private void initViewModel() {
        viewModel.getLiveData().observe(getViewLifecycleOwner(), this::render);
    }

    private void render(StateData appState) {

        if (appState instanceof StateData.Loading) {
            binding.loadingView.setVisibility(View.VISIBLE);
        }

        if (appState instanceof StateData.SuccessCode) {
            Code code = ((StateData.SuccessCode) appState).getData();

            setCode(code);
            binding.loadingView.setVisibility(View.GONE);
        }

        if (appState instanceof StateData.SuccessToken) {
            Token token = ((StateData.SuccessToken) appState).getData();

            loginWithToken(token);
        }

        if (appState instanceof StateData.Error) {
            String mess = ((StateData.Error) appState).getErrorMess();

            Toast.makeText(getContext(),mess,Toast.LENGTH_LONG).show();
            binding.loadingView.setVisibility(View.GONE);
        }
    }

    private void buttonAuthentication() {
        binding.getCodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.getCode("57ee66f8686ae7a3b69c");
            }
        });
    }

    private void setCode(Code code) {
        binding.textCode.setText(code.getUserCode());
        binding.toGitHub.setText(R.string.web_to_github);

        binding.toGitHub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tohWebGitHub(code.getVerificationUri());

                binding.loginButton.setVisibility(View.VISIBLE);
            }
        });

        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.getToken("57ee66f8686ae7a3b69c",code.getDevice_code(),"urn:ietf:params:oauth:grant-type:device_code");
            }
        });
    }

    private void tohWebGitHub(String url) {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    private void loginWithToken(Token token) {
        SharedPreference.saveToken(token.getAccess_token(),"KEY",requireActivity());

        getParentFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new FragmentViewRepository())
                .commit();
    }

}
