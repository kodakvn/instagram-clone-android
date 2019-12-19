package com.example.instagramclone.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.instagramclone.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.ArrayList;

public class GridImageAdapter extends ArrayAdapter<String> {

	private Context mContext;
	private LayoutInflater mInflater;
	private int layoutResource;
	private String mAppend;
	private ArrayList<String> urls;

	public GridImageAdapter(Context mContext, int layoutResource, String mAppend, ArrayList<String> urls) {
		super(mContext, layoutResource, urls);
		this.mContext = mContext;
		this.mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.layoutResource = layoutResource;
		this.mAppend = mAppend;
		this.urls = urls;
	}

	@NonNull
	@Override
	public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
		final ViewHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(layoutResource, parent,false);
			holder = new ViewHolder();
			holder.mProgressBar = (ProgressBar) convertView.findViewById(R.id.gridImageProgressBar);
			holder.image = (SquareImageView) convertView.findViewById(R.id.gridImageView);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		String url = getItem(position);
		ImageLoader loader = ImageLoader.getInstance();
		loader.displayImage(mAppend + url, holder.image, new ImageLoadingListener() {
			@Override public void onLoadingStarted(String imageUri, View view) {
				if (holder.mProgressBar != null) {
					holder.mProgressBar.setVisibility(View.VISIBLE);
				}
			}

			@Override
			public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
				if (holder.mProgressBar != null) {
					holder.mProgressBar.setVisibility(View.GONE);
				}
			}

			@Override
			public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
				if (holder.mProgressBar != null) {
					holder.mProgressBar.setVisibility(View.GONE);
				}
			}

			@Override public void onLoadingCancelled(String imageUri, View view) {
				if (holder.mProgressBar != null) {
					holder.mProgressBar.setVisibility(View.GONE);
				}
			}
		});

		return convertView;
	}

	private static class ViewHolder {
		SquareImageView image;
		ProgressBar	mProgressBar;
	}
}
