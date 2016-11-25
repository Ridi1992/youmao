package com.lester.youmao.adapter;

import java.util.ArrayList;

import com.lester.youmao.R;
import com.lester.youmao.entity.Value;

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

public class AttrsAdapter extends BaseAdapter{
	
	private ArrayList<Value> mList;
	private LayoutInflater mInflater;
	private ViewHolder viewHolder;
	
	public AttrsAdapter(Context c, ArrayList<Value> list){
		mList = list;
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
			viewHolder = null;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.grid_item_attr, null);
			viewHolder.name = (TextView) convertView.findViewById(R.id.attr_item_price);
			convertView.setTag(viewHolder);
		}else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		Value value = mList.get(position);
		viewHolder.name.setText(value.getLabel());
		
		return convertView;
	}
	
	class ViewHolder {
		TextView name;
	}

}
