package com.sachse.comicfinder.ui.views;

import com.sachse.comicfinder.R;
import com.sachse.comicfinder.model.Character;
import com.squareup.picasso.Picasso;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    private Context mContext;
    private List<Character> mCharacters;

    public CharacterAdapter(final Context context, final List<Character> characters) {
        super();
        mContext = context;
        mCharacters = characters;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.v_comic, parent, false);
            return new ViewHolder(v);
        } else if (viewType == TYPE_HEADER) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.v_result_header, parent, false);
            return new ViewHolderHeader(v);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            final ViewHolder view = ((ViewHolder) holder);

            final java.lang.String resourcePath = mCharacters.get(position).getImage().getMediumUrl();
            view.mTextView.setText(mCharacters.get(position).getName());
            Picasso.with(mContext.getApplicationContext()).load(resourcePath).into(view.mImageView);
        }
    }

    @Override
    public int getItemCount() {
        return mCharacters.size();
    }

    @Override
    public long getItemId(final int position) {
        return 1;
    }

    @Override
    public int getItemViewType(final int position) {
        if (isPositionHeader(position)) {
            return TYPE_HEADER;
        }

        return TYPE_ITEM;
    }

    private boolean isPositionHeader(final int position) {
        return position == 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextView;
        public ImageView mImageView;

        public ViewHolder(final View v) {
            super(v);
            mImageView = (ImageView) v.findViewById(R.id.comic_cover_iv);
            mTextView = (TextView) v.findViewById(R.id.comic_title_tv);
        }
    }

    static class ViewHolderHeader extends RecyclerView.ViewHolder {

        public TextView mTitleTV;
        public TextView mDescriptionTV;
        public ImageView mHeroIV;

        public ViewHolderHeader(final View v) {
            super(v);
            mTitleTV = (TextView) v.findViewById(R.id.result_title_tv);
            mDescriptionTV = (TextView) v.findViewById(R.id.result_description_tv);
            mHeroIV = (ImageView) v.findViewById(R.id.result_hero_iv);
        }
    }
}
