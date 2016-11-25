package com.lester.youmao.adapter;

import java.util.ArrayList;

import com.lester.youmao.MainActivity;
import com.lester.youmao.R;
import com.lester.youmao.entity.GoodsList;
import com.lester.youmao.entity.HomeBest;
import com.lester.youmao.entity.HomeCat;
import com.lester.youmao.entity.HomePromote;
import com.lester.youmao.entity.PointGoods;
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

public class JifenGoodsListAdapter extends BaseAdapter{
	
	private ArrayList<PointGoods> mList;
	private LayoutInflater mInflater;
	private ViewHolder viewHolder;
	private ImageLoader mImageLoader;
	
	public JifenGoodsListAdapter(Context c ,ArrayList<PointGoods> list ){
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
		
		convertView = mInflater.inflate(R.layout.grid_item_jifen, null);
		
		viewHolder = new ViewHolder();
		
		viewHolder.Img = (ImageView) convertView.findViewById(R.id.item_jifen_img);
		LinearLayout.LayoutParams Params = (LinearLayout.LayoutParams) viewHolder.Img.getLayoutParams();
		Params.width = MainActivity.width/4;
		Params.height = MainActivity.width/4;
		
		viewHolder.Name = (TextView) convertView.findViewById(R.id.item_jifen_title);
		viewHolder.points = (TextView) convertView.findViewById(R.id.item_jifen_points);
		viewHolder.price = (TextView) convertView.findViewById(R.id.item_jifen_price);
		
		PointGoods pointGoods = mList.get(position);
		
		viewHolder.Name.setText(pointGoods.getGoods_name()); 
		viewHolder.points.setText(pointGoods.getExchange_integral());
		
		viewHolder.price.setText("￥"+pointGoods.getPrice());
		
		if (pointGoods.getGoods_thumb() != null) {
			mImageLoader.DisplayImage(pointGoods.getGoods_thumb(), viewHolder.Img);
		} else
			viewHolder.Img.setImageResource(R.drawable.ic_launcher);
		
		return convertView;
	}
	
	class ViewHolder{
		ImageView Img;
		TextView Name;
		TextView points;
		TextView price;
		
	}

}
