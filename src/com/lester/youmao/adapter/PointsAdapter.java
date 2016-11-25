package com.lester.youmao.adapter;

import java.util.ArrayList;

import com.bset.tool.Texttool;
import com.lester.youmao.R;
import com.lester.youmao.entity.CateGory;
import com.lester.youmao.entity.Coupon;
import com.lester.youmao.entity.HomeCat;
import com.lester.youmao.entity.Notice;
import com.lester.youmao.entity.Points;
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

public class PointsAdapter extends BaseAdapter{
	
	private ArrayList<Points> mList;
	private LayoutInflater mInflater;
	private ViewHolder viewHolder;
	private Context c;
	
	public PointsAdapter(Context c ,ArrayList<Points> list ){
		mList = list;
		this.c= c;
		mInflater = LayoutInflater.from(c);
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
		
		convertView = mInflater.inflate(R.layout.list_item_jifen , null);
		
		viewHolder = new ViewHolder();
				
		viewHolder.Img = (ImageView) convertView.findViewById(R.id.item_points_img);
		viewHolder.Title = (TextView) convertView.findViewById(R.id.item_points_title);
		viewHolder.Desc = (TextView) convertView.findViewById(R.id.item_points_desc);
		
		Points points = mList.get(position);
		
		switch (points.getType()) {
		case 1:
			viewHolder.Img.setImageResource(R.drawable.jifen01);
			Texttool.setText(viewHolder.Title, "获取积分("+points.getPay_points()+")");
			break;
		case 2:
			viewHolder.Img.setImageResource(R.drawable.jifen02);
			Texttool.setText(viewHolder.Title, "消费积分("+points.getPay_points()+")");
			break;
		}
		Texttool.setText(viewHolder.Desc, points.getChange_time()+points.getChange_desc());
		
		return convertView;
	}
	
	class ViewHolder{
		ImageView Img;
		TextView Title;
		TextView Desc;
	}

}
