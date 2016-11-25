package com.lester.youmao.adapter;

import java.util.ArrayList;

import com.lester.youmao.R;
import com.lester.youmao.entity.Regions;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class RegionAdapter extends BaseAdapter{
	
	private ArrayList<Regions> mRegions;
	private LayoutInflater mInflater;
	
	public RegionAdapter (Context c ,ArrayList<Regions> list){
		mRegions = list;
		mInflater = LayoutInflater.from(c);
		
	}

	@Override
	public int getCount() {
		if (mRegions != null) {
			return mRegions.size();
		}
		return 0;
	}

	@Override
	public Object getItem(int position) {
		if (mRegions != null) {
			return mRegions.get(position);
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.list_item_city, null);
		}
		
		TextView name = (TextView) convertView.findViewById(R.id.city_item_name);
		
		Regions regions = mRegions.get(position);
		
		name.setText(regions.getName());
		
		
		
		return convertView;
	}

}
