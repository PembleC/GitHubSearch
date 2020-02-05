package com.example.android.githubsearch;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.githubsearch.data.GitHubRepo;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GitHubSearchAdapter extends RecyclerView.Adapter<GitHubSearchAdapter.SearchResultViewHolder> {
    private ArrayList<GitHubRepo> mSearchResultsList;

    public void updateSearchResults(ArrayList<GitHubRepo> searchResultsList) {
        mSearchResultsList = searchResultsList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mSearchResultsList != null) {
            return mSearchResultsList.size();
        } else {
            return 0;
        }
    }

    @NonNull
    @Override
    public SearchResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.search_result_item, parent, false);
        return new SearchResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchResultViewHolder holder, int position) {
        holder.bind(mSearchResultsList.get(position));
    }

    class SearchResultViewHolder extends RecyclerView.ViewHolder {
        private TextView mSearchResultTV;

        SearchResultViewHolder(View itemView) {
            super(itemView);
            mSearchResultTV = itemView.findViewById(R.id.tv_search_result);
        }

        void bind(GitHubRepo repo) {
            mSearchResultTV.setText(repo.full_name);
        }
    }
}
