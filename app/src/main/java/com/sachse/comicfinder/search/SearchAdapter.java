package com.sachse.comicfinder.search;

import com.sachse.comicfinder.R;
import com.sachse.comicfinder.model.Character;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Character> searchCharacters = new ArrayList<>();

    @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_search, parent, false);

        return new SearchViewHolder(view);
    }

    @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((SearchViewHolder) holder).createView(searchCharacters.get(position));
    }

    @Override public int getItemCount() {
        return searchCharacters.size();
    }

    public void setSearchAdapter(List<Character> characters) {
        searchCharacters.addAll(characters);
        notifyDataSetChanged();
    }

    class SearchViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textview_search_name) TextView searchNameView;

        public SearchViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void createView(Character character) {
            searchNameView.setText(character.getName());
        }
    }
}
