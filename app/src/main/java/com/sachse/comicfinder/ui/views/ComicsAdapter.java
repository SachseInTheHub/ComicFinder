package com.sachse.comicfinder.ui.views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sachse.comicfinder.R;
import com.sachse.comicfinder.api.models.Comic;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ComicsAdapter extends RecyclerView.Adapter<ComicsAdapter.ViewHolder> {

	private List<Comic> mComics;
	private Context mContext;

	public ComicsAdapter(Context context, List<Comic> comics){
		super();
		mContext = context;
		mComics = comics;
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {
		public TextView mTextView;
		public ImageView mImageView;

		public ViewHolder(View v) {
			super(v);
			mImageView = (ImageView) v.findViewById(R.id.comic_cover_iv);
			mTextView = (TextView) v.findViewById(R.id.comic_title_tv);
		}
	}

	@Override
	public ComicsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.v_comic, parent, false);

		return new ViewHolder(v);
	}

	@Override
	public void onBindViewHolder(ComicsAdapter.ViewHolder holder, int position) {
		holder.mTextView.setText(mComics.get(position).title);
		final String resourcePath = mComics.get(position).images.get(0).getResourcePath();
		Picasso.with(mContext.getApplicationContext()).load(resourcePath).into(holder.mImageView);
	}

	@Override
	public int getItemCount() {
		return mComics.size();
	}

	@Override
	public long getItemId(int position) {
		return mComics.get(position).id;
	}
}
