package com.lester.youmao.adapter;

import java.util.ArrayList;

import com.lester.youmao.MainActivity;
import com.lester.youmao.R;
import com.lester.youmao.entity.HomeBest;
import com.lester.youmao.entity.HomeCat;
import com.lester.youmao.entity.HomePromote;
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

public class HomeBestAdapter extends BaseAdapter{
	
	private ArrayList<HomeBest> mList;
	private LayoutInflater mInflater;
	private ViewHolder viewHolder;
	private ImageLoader mImageLoader;
	
	public HomeBestAdapter(Context c ,ArrayList<HomeBest> list ){
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
		
		convertView = mInflater.inflate(R.layout.list_item_home, null);
		
		viewHolder = new ViewHolder();
		
		viewHolder.BestImg = (ImageView) convertView.findViewById(R.id.item_homebest_img);
		LinearLayout.LayoutParams Params = (LinearLayout.LayoutParams) viewHolder.BestImg.getLayoutParams();
		Params.width = MainActivity.width/4;
		Params.height = MainActivity.width/4;
		
		viewHolder.BestImg = (ImageView) convertView.findViewById(R.id.item_homebest_img);
		viewHolder.BestName = (TextView) convertView.findViewById(R.id.item_homebest_name);
		viewHolder.BestBrief = (TextView) convertView.findViewById(R.id.item_homebest_brief);
		viewHolder.BestMarketPrice = (TextView) convertView.findViewById(R.id.item_homebest_market);
		viewHolder.BestShopPrice = (TextView) convertView.findViewById(R.id.item_homebest_shop);
		viewHolder.BestSalesnum = (TextView) convertView.findViewById(R.id.item_homebest_salsenum);
		
		HomeBest homeBest = mList.get(position);
		
		viewHolder.BestName.setText(homeBest.getGoods_name());
		viewHolder.BestBrief.setText(homeBest.getGoods_brief());
		if (homeBest.getPromote_price() == 0.00) {
			viewHolder.BestMarketPrice.setText("￥"+homeBest.getMarket_price());
			viewHolder.BestShopPrice.setText("￥"+homeBest.getShop_price());
		}else {
			viewHolder.BestMarketPrice.setText("￥"+homeBest.getShop_price());
			viewHolder.BestShopPrice.setText("￥"+homeBest.getPromote_price());
		}
		viewHolder.BestSalesnum.setText("已售  "+homeBest.getSalesnum());
		
		if (homeBest.getGoods_thumb() != null) {
			mImageLoader.DisplayImage(homeBest.getGoods_thumb(), viewHolder.BestImg);
		} else
			viewHolder.BestImg.setImageResource(R.drawable.ic_launcher);
		
		return convertView;
	}
	
	class ViewHolder{
		ImageView BestImg;
		TextView BestName;
		TextView BestBrief;
		TextView BestMarketPrice;
		TextView BestShopPrice;
		TextView BestSalesnum;
		
	}

}
