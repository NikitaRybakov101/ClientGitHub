package com.example.clientgithub.ui.fragments.recyclerRepositories;

import com.example.clientgithub.dataSource.dataRepositorySource.Owner;

public interface CallBackItem {
    void clickedItem(String nameRepository, Owner owner);
}
