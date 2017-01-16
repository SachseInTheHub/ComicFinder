package com.sachse.comicfinder.landing;

import com.sachse.comicfinder.R;
import com.sachse.comicfinder.model.Character;
import com.squareup.picasso.Picasso;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LandingAdapter extends RecyclerView.Adapter<LandingAdapter.ViewHolder> {

    private ArrayList<Character> characters;

    public LandingAdapter(List<Character> characters) {
        this.characters = new ArrayList<>();
        this.characters.addAll(characters);
    }

    @Override public LandingAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_landing_adapter, parent, false);

        return new ViewHolder(view);
    }

    @Override public void onBindViewHolder(LandingAdapter.ViewHolder holder, int position) {
        holder.setCharacterName(characters.get(position).getName());
        holder.setCharacterImage(characters.get(position).getMediumUrl());
    }

    @Override public int getItemCount() {
        return characters.size();
    }

    public void addCharacter(Character character) {
        this.characters.add(character);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imageview_character) ImageView characterImage;
        @BindView(R.id.textview_character_name) TextView characterName;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        void setCharacterImage(String imagePath) {
            Picasso.with(characterImage.getContext())
                    .load(imagePath)
                    .into(characterImage);
        }

        void setCharacterName(String name) {
            characterName.setText(name);
        }
    }
}
