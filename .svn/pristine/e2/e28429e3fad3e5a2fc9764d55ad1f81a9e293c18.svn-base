package com.lester.youmao.adapter;

import java.util.ArrayList;

import com.lester.youmao.R;
import com.lester.youmao.entity.Regions;
import com.lester.youmao.entity.ShippingList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CheckOrderShippingAdapter extends BaseAdapter{
	
	private ArrayList<ShippingList> mList;
	private LayoutInflater mInflater;
	
	public CheckOrderShippingAdapter (Context c ,ArrayList<ShippingList> list){
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
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.list_item_checkshipping, null);
		}
		
		TextView name = (TextView) convertView.findViewById(R.id.item_checkshipping_name);
		
		ShippingList shippingList = mList.get(position);
		
		name.setText(shippingList.getShipping_name()+"  ￥"+shippingList.getShipping_fee());
		
		
		
		return convertView;
	}

}
