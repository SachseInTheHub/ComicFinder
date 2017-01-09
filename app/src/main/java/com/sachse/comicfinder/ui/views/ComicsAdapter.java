package com.sachse.comicfinder.ui.views;

import com.sachse.comicfinder.R;
import com.sachse.comicfinder.api.models.comic.Comic;
import com.sachse.comicfinder.model.Character;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ComicsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    private List<Comic> mComics;
    private Context mContext;
    private Character mCharacter;

    public ComicsAdapter(final Context context, final Character character, final List<Comic> comics) {
        super();
        mContext = context;
        mCharacter = character;
        mComics = comics;
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
        } else if (holder instanceof ViewHolderHeader) {
            final ViewHolderHeader view = ((ViewHolderHeader) holder);
        }
    }

    @Override
    public int getItemCount() {
        return mComics.size();
    }

    @Override
    public long getItemId(final int position) {
        return mComics.get(position).id;
    }

    @Override
    public int getItemViewType(final int position) {
        if (isPositionHeader(position)) {
            return TYPE_HEADER;
        }

        return TYPE_ITEM;
    }

    public void setComics(final List<Comic> comics) {
        mComics = comics;
    }

    private boolean isPositionHeader(final int position) {
        return position == 0;
    }

    private static class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTextView;
        ImageView mImageView;

        ViewHolder(final View v) {
            super(v);
            mImageView = (ImageView) v.findViewById(R.id.comic_cover_iv);
            mTextView = (TextView) v.findViewById(R.id.comic_title_tv);
        }
    }

    private static class ViewHolderHeader extends RecyclerView.ViewHolder {

        TextView mTitleTV;
        TextView mDescriptionTV;
        ImageView mHeroIV;

        ViewHolderHeader(final View v) {
            super(v);
            mTitleTV = (TextView) v.findViewById(R.id.result_title_tv);
            mDescriptionTV = (TextView) v.findViewById(R.id.result_description_tv);
            mHeroIV = (ImageView) v.findViewById(R.id.result_hero_iv);
        }
    }
}
