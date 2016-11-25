package com.lester.youmao.adapter;

import java.util.ArrayList;

import com.lester.youmao.R;
import com.lester.youmao.entity.HomeCat;
import com.sanmi.loader.ImageLoader;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

public class HomeCatAdapter extends BaseAdapter{
	
	private ArrayList<HomeCat> mList;
	private LayoutInflater mInflater;
	private ViewHolder viewHolder;
	private ImageLoader mImageLoader;
	
	public HomeCatAdapter(Context c ,ArrayList<HomeCat> list ){
		mList = list;
		mInflater = LayoutInflater.from(c);
		mImageLoader = new ImageLoader(c);
	}

	@Override
	public int getCount() {
		if (mList != null) {
			return mList.size();
		}
		return 0;
	}

	@Override
	public Object getItem(int position) {
		if (mList != null) {
			return mList.get(position);
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		convertView = mInflater.inflate(R.layout.grid_item_hometype, null);
		
		viewHolder = new ViewHolder();
		
		viewHolder.CatImg =  (ImageView) convertView.findViewById(R.id.item_hometype_img);
		viewHolder.CatName =  (TextView) convertView.findViewById(R.id.item_hometype_name);
		
		HomeCat homeCat = mList.get(position);
		if (position != mList.size()-1) {
			viewHolder.CatName.setText(homeCat.getCat_name());
			if (homeCat.getCat_img() != null) {
				mImageLoader.DisplayImage(homeCat.getCat_img(), viewHolder.CatImg);
			} else
				viewHolder.CatImg.setImageResource(R.drawable.ic_launcher);
		}else {
			viewHolder.CatName.setText("更多");
			viewHolder.CatImg.setImageResource(R.drawable.more);
		}
		
		
		return convertView;
	}
	
	class ViewHolder{
		ImageView CatImg;
		TextView CatName;
		
	}

}
