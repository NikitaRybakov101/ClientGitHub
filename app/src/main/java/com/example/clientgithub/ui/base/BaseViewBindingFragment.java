package com.example.clientgithub.ui.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

import io.reactivex.rxjava3.functions.Function3;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

public abstract class BaseViewBindingFragment<VB extends ViewBinding> extends Fragment {
    private VB _binding;

    private final Function3<LayoutInflater, ViewGroup, Boolean, VB> inflate;

    public BaseViewBindingFragment(Function3<LayoutInflater, ViewGroup, Boolean, VB> inflate) {
        this.inflate = inflate;
    }

    public VB getBinding() {
        return _binding;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       // _binding = inflate.apply(inflater, container, false);
        return getBinding().getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        _binding = null;
    }
}
