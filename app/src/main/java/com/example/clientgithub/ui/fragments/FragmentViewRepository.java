package com.example.clientgithub.ui.fragments;

import static com.example.clientgithub.sharedPreference.SharedPreference.TOKEN_KEY;
import static com.example.clientgithub.ui.fragments.FragmentViewCommits.KEY_OWNER;
import static com.example.clientgithub.ui.fragments.FragmentViewCommits.KEY_REPOSITORY;

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

import com.bumptech.glide.Glide;
import com.example.clientgithub.R;
import com.example.clientgithub.dataSource.dataRepositorySource.Owner;
import com.example.clientgithub.dataSource.dataRepositorySource.Repository;
import com.example.clientgithub.dataSource.dataUserSource.User;
import com.example.clientgithub.databinding.FragmentViewRepositoryBinding;
import com.example.clientgithub.sharedPreference.SharedPreference;
import com.example.clientgithub.ui.fragments.recyclerRepositories.AdapterRecyclerRepositories;
import com.example.clientgithub.ui.fragments.recyclerRepositories.CallBackItem;
import com.example.clientgithub.ui.viewModel.ViewModelFragmentRepositoryView;
import com.example.clientgithub.ui.viewModel.dataSourse.StateData;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class FragmentViewRepository extends Fragment implements CallBackItem {
    private static final float CARD_ELEVATION = 0f;
    private FragmentViewRepositoryBinding binding;
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private ViewModelFragmentRepositoryView viewModel;
    private String token = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentViewRepositoryBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        token = SharedPreference.loadToken(TOKEN_KEY,requireActivity());

        initViewModel();
        initView();

        getUser();
        getListRepository();
    }

    private void initViewModel() {
        AndroidSupportInjection.inject(this);
        viewModel = new ViewModelProvider(this,viewModelFactory).get(ViewModelFragmentRepositoryView.class);

        viewModel.getLiveData().observe(getViewLifecycleOwner(), this::render);
    }

    private void render(StateData appState) {

        if (appState instanceof StateData.Loading) {
            binding.loadingView.setVisibility(View.VISIBLE);
            binding.loadingViewUser.setVisibility(View.VISIBLE);
        }

        if (appState instanceof StateData.SuccessRepository) {
            List<Repository> repositoryList = ((StateData.SuccessRepository) appState).getData();

            initListRepositories(repositoryList);
            binding.loadingView.setVisibility(View.GONE);
        }

        if (appState instanceof StateData.SuccessUser) {
            User user = ((StateData.SuccessUser) appState).getData();

            createHeaderUser(user);
            binding.loadingViewUser.setVisibility(View.GONE);
        }

        if (appState instanceof StateData.Error) {
            String mess = ((StateData.Error) appState).getErrorMess();

            Toast.makeText(getContext(),mess,Toast.LENGTH_LONG).show();
            binding.loadingView.setVisibility(View.GONE);
            binding.loadingViewUser.setVisibility(View.GONE);
        }
    }

    private void getUser() {
        if(!token.isEmpty()) {
            viewModel.getUser(token);
        }
    }

    private void getListRepository() {
        if(!token.isEmpty()) {
            viewModel.getListRepository(token);
        }
    }

    private void initView() {
        binding.cardListRepository.setElevation(CARD_ELEVATION);
    }

    private void initListRepositories(List<Repository> repositoryList) {
        binding.recycler.setLayoutManager(new LinearLayoutManager(requireContext(), RecyclerView.VERTICAL,false));
        binding.recycler.setAdapter(new AdapterRecyclerRepositories(this,repositoryList));
    }

    private void createHeaderUser(User user) {
        String url = user.getAvatar_url();

        Glide
                .with(binding.imageOwner.getContext())
                .load(url).placeholder(R.drawable.ic_launcher_background)
                .into(binding.imageOwner);

        binding.nameOwner.setText(user.getLogin());
    }

    @Override
    public void clickedItem(String nameRepository, Owner owner) {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_REPOSITORY,nameRepository);
        bundle.putString(KEY_OWNER,owner.getLogin());

        NavHostFragment.findNavController(this).navigate(R.id.action_fragmentViewRepository_to_fragmentViewCommits,bundle);
    }
}
