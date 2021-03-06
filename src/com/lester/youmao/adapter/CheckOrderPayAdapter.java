package com.lester.youmao.adapter;

import java.util.ArrayList;

import com.lester.youmao.R;
import com.lester.youmao.entity.PaymentList;
import com.lester.youmao.entity.Regions;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CheckOrderPayAdapter extends BaseAdapter{
	
	private ArrayList<PaymentList> mList;
	private LayoutInflater mInflater;
	
	public CheckOrderPayAdapter (Context c ,ArrayList<PaymentList> list){
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
			convertView = mInflater.inflate(R.layout.list_item_checkpay, null);
		}
		
		TextView name = (TextView) convertView.findViewById(R.id.item_checkpay_name);
		
		PaymentList paymentList = mList.get(position);
		
		name.setText(paymentList.getPay_name());
		
		return convertView;
	}

}
