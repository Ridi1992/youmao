package com.lester.youmao.adapter;

import java.util.ArrayList;

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

public class Cate1Adapter extends BaseAdapter{
	
	private ArrayList<CateGory> mList;
	private LayoutInflater mInflater;
	private ViewHolder viewHolder;
	
	public Cate1Adapter(Context c ,ArrayList<CateGory> list ){
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
		
		convertView = mInflater.inflate(R.layout.list_item_cate1, null);
		
		viewHolder = new ViewHolder();
		
		viewHolder.CatName = (TextView) convertView.findViewById(R.id.cate1_name);
		
		CateGory cateGory = mList.get(position);
		
		viewHolder.CatName.setText(cateGory.getCat_name());
		
		if(mList.get(position).isCheck()){
			convertView.setBackgroundColor(mInflater.getContext().getResources().getColor(R.color.baise));
		}else{
			convertView.setBackgroundColor(mInflater.getContext().getResources().getColor(R.color.hui_ea));
		}
		
		return convertView;
	}
	
	class ViewHolder{
		
		TextView CatName;
		
	}

}
