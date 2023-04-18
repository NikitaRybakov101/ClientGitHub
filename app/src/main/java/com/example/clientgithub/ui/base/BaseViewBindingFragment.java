package com.example.clientgithub.ui.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class BaseViewBindingFragment<VB> extends Fragment {
  /*  private VB binding;


    public BaseViewBindingFragment( (LayoutInflater, ViewGroup, Boolean) -> VB inflate) {

    }


    public VB getBinding() {
        return binding ?: throw IllegalStateException("Trying to access binding");
    }
    //val binding get() = _binding ?: throw IllegalStateException("Trying to access binding")


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = inflate(inflater, container, false)

        return binding.root
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);

        binding = inflate(inflater, container, false);
        return binding
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        uiScope.cancel()
    }*/
}
