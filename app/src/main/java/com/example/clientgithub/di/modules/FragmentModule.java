package com.example.clientgithub.di.modules;


import com.example.clientgithub.ui.fragments.FragmentAuthentication;
import com.example.clientgithub.ui.fragments.FragmentViewCommits;
import com.example.clientgithub.ui.fragments.FragmentViewRepository;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
interface FragmentModule {

    @ContributesAndroidInjector
    FragmentViewRepository contributeMainFragment();

    @ContributesAndroidInjector
    FragmentAuthentication contributeFragmentAuthentication();

    @ContributesAndroidInjector
    FragmentViewCommits contributeFragmentViewCommits();
}