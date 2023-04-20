package com.example.clientgithub.ui.fragments;

import static com.example.clientgithub.sharedPreference.SharedPreference.TOKEN_KEY;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clientgithub.R;
import com.example.clientgithub.dataSource.commitsSource.Commit;
import com.example.clientgithub.databinding.FragmentViewCommitsBinding;
import com.example.clientgithub.sharedPreference.SharedPreference;
import com.example.clientgithub.ui.fragments.recyclerCommits.AdapterRecyclerCommits;
import com.example.clientgithub.ui.viewModel.ViewModelFragmentCommitsView;
import com.example.clientgithub.ui.viewModel.ViewModelFragmentRepositoryView;
import com.example.clientgithub.ui.viewModel.dataSourse.StateData;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class FragmentViewCommits extends Fragment {
    private static final float CARD_ELEVATION = 0f;
    public static final String KEY_REPOSITORY = "KEY_REPOSITORY";
    public static final String KEY_OWNER = "KEY_OWNER";
    private FragmentViewCommitsBinding binding;

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private ViewModelFragmentCommitsView viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentViewCommitsBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViewModel();
        initView();
        arrowBack();

        String token = SharedPreference.loadToken(TOKEN_KEY,requireActivity());

        if(getArguments() != null) {
            String nameRepository = getArguments().getString(KEY_REPOSITORY);
            String owner = getArguments().getString(KEY_OWNER);

            initHeader(nameRepository);

            viewModel.getListCommits(owner,token,nameRepository);
        }
    }

    private void initViewModel() {
        AndroidSupportInjection.inject(this);
        viewModel = new ViewModelProvider(this,viewModelFactory).get(ViewModelFragmentCommitsView.class);

        viewModel.getLiveData().observe(getViewLifecycleOwner(), this::render);
    }

    private void render(StateData appState) {

        if (appState instanceof StateData.Loading) {
            binding.loadingView.setVisibility(View.VISIBLE);
        }

        if (appState instanceof StateData.SuccessCommits) {
            List<Commit> commitsList = ((StateData.SuccessCommits) appState).getData();

            initListRepositories(commitsList);
            binding.loadingView.setVisibility(View.GONE);
        }

        if (appState instanceof StateData.Error) {
            String mess = ((StateData.Error) appState).getErrorMess();

            Toast.makeText(getContext(),mess,Toast.LENGTH_LONG).show();
            binding.loadingView.setVisibility(View.GONE);
        }
    }

    private void initHeader(String nameRepository) {
        binding.nameRepository.setText(requireContext().getString(R.string.item_repository,nameRepository));
    }

    private void arrowBack() {
        binding.imageArrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateUp();
            }
        });
    }

    private void navigateUp() {
        NavHostFragment.findNavController(this).navigateUp();
    }

    private void initView() {
        binding.cardListCommits.setElevation(CARD_ELEVATION);
    }

    private void initListRepositories(List<Commit> commitsList) {
        binding.recycler.setLayoutManager(new LinearLayoutManager(requireContext(), RecyclerView.VERTICAL,false));
        binding.recycler.setAdapter(new AdapterRecyclerCommits(commitsList));
    }
}
