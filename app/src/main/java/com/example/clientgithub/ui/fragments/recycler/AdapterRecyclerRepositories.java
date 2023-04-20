package com.example.clientgithub.ui.fragments.recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.clientgithub.R;
import com.example.clientgithub.dataSource.dataRepositorySource.Repository;
import com.example.clientgithub.databinding.RepositoryItemsBinding;
import java.util.List;

public class AdapterRecyclerRepositories extends RecyclerView.Adapter<AdapterRecyclerRepositories.ViewHolderRepositoryView> {

    private final List<Repository> repositories;
    private final CallBackItem callBackItem;

    public AdapterRecyclerRepositories(CallBackItem callBackItem, List<Repository> repositoryList) {
        this.repositories = repositoryList;
        this.callBackItem = callBackItem;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public AdapterRecyclerRepositories.ViewHolderRepositoryView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RepositoryItemsBinding binding = RepositoryItemsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolderRepositoryView(binding.getRoot(), binding, callBackItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderRepositoryView holder, int position) {
        holder.bin(repositories.get(position));
    }

    @Override
    public int getItemCount() {
        return repositories.size();
    }

    public static class ViewHolderRepositoryView extends RecyclerView.ViewHolder {
        private final RepositoryItemsBinding binding;
        private final CallBackItem callBackItem;

        public ViewHolderRepositoryView(@NonNull View itemView, RepositoryItemsBinding binding,CallBackItem callBackItem) {
            super(itemView);
            this.binding = binding;
            this.callBackItem = callBackItem;
        }

        private void clickedItem(Repository repository) {
            binding.itemsLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callBackItem.clickedItem(repository.getNameRepository());
                }
            });
        }

        private void bin(Repository repository) {
            Context context = binding.itemsLayout.getContext();
            clickedItem(repository);

            String url = repository.getOwner().getAvatarUrl();

            Glide
                    .with(binding.imageOwner.getContext())
                    .load(url).placeholder(R.drawable.ic_launcher_background)
                    .into(binding.imageOwner);

            binding.author.setText(context.getString(R.string.item_author,repository.getOwner().getLogin()));
            binding.nameRepository.setText(context.getString(R.string.item_repository,repository.getNameRepository()));
            binding.description.setText(context.getString(R.string.item_description, repository.getDescription()));
            binding.visibility.setText(context.getString(R.string.item_visibility,repository.getVisibility()));
            binding.forkCount.setText(context.getString(R.string.item_fork,repository.getForks_count()));
            binding.watches.setText(context.getString(R.string.item_watches,repository.getWatchers_count()));
        }
    }
}
