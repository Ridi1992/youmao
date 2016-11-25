package com.lester.youmao.adapter;

import java.util.ArrayList;

import com.lester.youmao.MainActivity;
import com.lester.youmao.R;
import com.lester.youmao.entity.CateGory;
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

public class Cate3Adapter extends BaseAdapter{
	
	private ArrayList<CateGory> mList;
	private LayoutInflater mInflater;
	private ViewHolder viewHolder;
	private ImageLoader mImageLoader;
	
	public Cate3Adapter(Context c ,ArrayList<CateGory> list ){
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
		
		convertView = mInflater.inflate(R.layout.grid_item_cate, null);
		
		viewHolder = new ViewHolder();
		
		viewHolder.CatImg = (ImageView) convertView.findViewById(R.id.cate3_img);
		LinearLayout.LayoutParams Params = (LinearLayout.LayoutParams) viewHolder.CatImg.getLayoutParams();
		Params.width = MainActivity.width/6;
		Params.height = MainActivity.width/6;
		
		viewHolder.CatName = (TextView) convertView.findViewById(R.id.cate3_name);
		
		CateGory cateGory = mList.get(position);
		
		viewHolder.CatName.setText(cateGory.getCat_name());
		if (cateGory.getCat_img() != null) {
			mImageLoader.DisplayImage(cateGory.getCat_img(), viewHolder.CatImg);
		} else
			viewHolder.CatImg.setImageResource(R.drawable.ic_launcher);
		
		return convertView;
	}
	
	class ViewHolder{
		
		ImageView CatImg;
		TextView CatName;
		
	}

}
