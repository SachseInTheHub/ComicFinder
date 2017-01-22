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
import rx.Observable;
import rx.subjects.PublishSubject;

public class LandingAdapter extends RecyclerView.Adapter<LandingAdapter.ViewHolder> {

    private ArrayList<Character> characters;
    private PublishSubject<LandingAdapter.ViewHolder> publishSubject = PublishSubject.create();

    public void setCharacters(List<Character> characters) {
        this.characters = new ArrayList<>();
        this.characters.addAll(characters);
    }

    @Override public LandingAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_landing_adapter, parent, false);

        return new ViewHolder(view);
    }

    @Override public void onBindViewHolder(LandingAdapter.ViewHolder holder, int position) {
        holder.setCharacterId(position);
        holder.setCharacterName(characters.get(position).getName());
        holder.setCharacterImage(characters.get(position).getMediumUrl());
        holder.itemView.setOnClickListener(view -> publishSubject.onNext(holder));
    }

    @Override public int getItemCount() {
        return characters.size();
    }

    public void addCharacter(Character character) {
        this.characters.add(character);
    }

    public Observable<LandingAdapter.ViewHolder> onItemClicked(){
        return publishSubject.asObservable();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private int id;

        @BindView(R.id.imageview_character) ImageView characterImage;
        @BindView(R.id.textview_character_name) TextView characterName;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        void setCharacterId(int id) {
            this.id = id;
        }

        void setCharacterImage(String imagePath) {
            Picasso.with(characterImage.getContext())
                    .load(imagePath)
                    .into(characterImage);
        }

        void setCharacterName(String name) {
            characterName.setText(name);
        }

        public String getCharacterName() {
            return characterName.getText().toString();
        }
    }
}
