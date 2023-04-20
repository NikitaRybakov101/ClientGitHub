package com.example.clientgithub.ui.fragments.recyclerCommits;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.clientgithub.R;
import com.example.clientgithub.dataSource.commitsSource.Commit;
import com.example.clientgithub.dataSource.dataRepositorySource.Repository;
import com.example.clientgithub.databinding.CommitItemBinding;
import com.example.clientgithub.databinding.RepositoryItemsBinding;

import java.util.List;

public class AdapterRecyclerCommits extends RecyclerView.Adapter<AdapterRecyclerCommits.ViewHolderCommits> {

    private final List<Commit> repositories;

    public AdapterRecyclerCommits(List<Commit> repositoryList) {
        this.repositories = repositoryList;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public ViewHolderCommits onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CommitItemBinding binding = CommitItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolderCommits(binding.getRoot(), binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCommits holder, int position) {
        holder.bin(repositories.get(position));
    }

    @Override
    public int getItemCount() {
        return repositories.size();
    }

    public static class ViewHolderCommits extends RecyclerView.ViewHolder {
        private final CommitItemBinding binding;
        public ViewHolderCommits(@NonNull View itemView, CommitItemBinding binding) {
            super(itemView);
            this.binding = binding;
        }
        private void bin(Commit commit) {
            Context context = binding.commitMessage.getContext();

            binding.commitMessage.setText(context.getString(R.string.commit_mess,commit.getMessage()));
            binding.author.setText(context.getString(R.string.author,commit.getName()));
            binding.date.setText(context.getString(R.string.date,commit.getDate()));
            binding.sha.setText(context.getString(R.string.sha,commit.getSha()));
        }
    }
}
