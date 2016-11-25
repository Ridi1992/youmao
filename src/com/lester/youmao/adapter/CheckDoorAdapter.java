package com.lester.youmao.adapter;

import java.util.ArrayList;

import com.lester.youmao.R;
import com.lester.youmao.entity.CateGory;
import com.lester.youmao.entity.Coupon;
import com.lester.youmao.entity.DoorShop;
import com.lester.youmao.entity.HomeCat;
import com.lester.youmao.me.CouponsListActivity;
import com.sanmi.loader.ImageLoader;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

public class CheckDoorAdapter extends BaseAdapter{
	
	private ArrayList<DoorShop> mList;
	private LayoutInflater mInflater;
	private ViewHolder viewHolder;
	private ImageLoader mImageLoader;
	private Context c;
	
	public CheckDoorAdapter(Context c ,ArrayList<DoorShop> list ){
		mList = list;
		this.c= c;
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
		
		convertView = mInflater.inflate(R.layout.list_item_checkdoor, null);
		
		viewHolder = new ViewHolder();
		
		viewHolder.mCheck = (ImageView) convertView.findViewById(R.id.item_checkdoor_check);
		viewHolder.mName = (TextView) convertView.findViewById(R.id.item_checkdoor_name);
		viewHolder.mContent = (TextView) convertView.findViewById(R.id.item_checkdoor_content);
		viewHolder.Distance = (TextView) convertView.findViewById(R.id.item_checkdoor_distance);
		
		DoorShop doorShop = mList.get(position);
		
		if (doorShop.isCheck()) {
			viewHolder.mCheck.setImageResource(R.drawable.gouxuan_true);
		}else {
			viewHolder.mCheck.setImageResource(R.drawable.gouxuan_false);
		}
		
			viewHolder.mName.setText(doorShop.getShop_name());
			viewHolder.mContent.setText(doorShop.getShop_address());
			viewHolder.Distance.setText("距当前位置 "+doorShop.getDistance()+"km");
		
		return convertView;
	}
	
	class ViewHolder{
		ImageView mCheck;
		TextView mName;
		TextView mContent;
		TextView Distance;
	}

}
