package com.lester.youmao.adapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.lester.youmao.MainActivity;
import com.lester.youmao.R;
import com.lester.youmao.entity.HomeCat;
import com.lester.youmao.entity.HomePromote;
import com.sanmi.loader.ImageLoader;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
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

public class HomePromoteAdapter extends BaseAdapter{
	
	private ArrayList<HomePromote> mList;
	private LayoutInflater mInflater;
	private ViewHolder viewHolder = new ViewHolder();;
	private ImageLoader mImageLoader;
	
	private SimpleDateFormat simpleDateFormat;

	private Handler mHandler1 = new Handler();
	public HomePromoteAdapter(Context c ,ArrayList<HomePromote> list){
		mList = list;
		mInflater = LayoutInflater.from(c);
		mImageLoader = new ImageLoader(c);
		simpleDateFormat = new SimpleDateFormat("dd天HH:mm:ss");
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
		
		convertView = mInflater.inflate(R.layout.grid_item_homepromote, null);
		
		LinearLayout layout =  (LinearLayout) convertView.findViewById(R.id.item_homepromote_line);
		LinearLayout.LayoutParams Params = (LinearLayout.LayoutParams) layout.getLayoutParams();
		Params.width = MainActivity.width/4;
		Params.height = MainActivity.width/4;
		
		viewHolder.PromoteImg =  (ImageView) convertView.findViewById(R.id.item_homepromote_img);
		viewHolder.PromoteName =  (TextView) convertView.findViewById(R.id.item_homepromote_title);
		viewHolder.PromotePrice =  (TextView) convertView.findViewById(R.id.item_homepromote_price);
		viewHolder.PromoteShopPrice =  (TextView) convertView.findViewById(R.id.item_homepromote_shop);
		viewHolder.promote_time2 = (TextView) convertView.findViewById(R.id.item_homepromote_shi);
		
		HomePromote homePromote = mList.get(position);
		
		viewHolder.PromoteName.setText(homePromote.getGoods_name());
		viewHolder.PromotePrice.setText("￥"+homePromote.getPromote_price());
		viewHolder.PromoteShopPrice.setText("￥"+homePromote.getShop_price());
		if (homePromote.getGoods_thumb() != null) {
			mImageLoader.DisplayImage(homePromote.getGoods_thumb(), viewHolder.PromoteImg);
		} else
			viewHolder.PromoteImg.setImageResource(R.drawable.ic_launcher);
		
		if (homePromote.getPromote_end_date() != 0) {
			long v = (homePromote.getPromote_end_date()*1000);
			Date date = new Date(v);
			viewHolder.promote_time2.setText(simpleDateFormat.format(date));
		}else {
			viewHolder.promote_time2.setText("时间已到");
		}
		
		
		return convertView;
	}
	
	class ViewHolder{
		ImageView PromoteImg;
		TextView PromoteName;
		TextView PromotePrice;
		TextView PromoteShopPrice;
		TextView promote_time2;
	}
	
}
