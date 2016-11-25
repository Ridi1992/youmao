package com.lester.youmao.adapter;

import java.util.ArrayList;

import com.lester.youmao.MainActivity;
import com.lester.youmao.R;
import com.lester.youmao.entity.GoodsList;
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

public class GoodsListAdapter extends BaseAdapter{
	
	private ArrayList<GoodsList> mList;
	private LayoutInflater mInflater;
	private ViewHolder viewHolder;
	private ImageLoader mImageLoader;
	
	public GoodsListAdapter(Context c ,ArrayList<GoodsList> list ){
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
		
		convertView = mInflater.inflate(R.layout.list_item_goods, null);
		
		viewHolder = new ViewHolder();
		
		viewHolder.BestImg = (ImageView) convertView.findViewById(R.id.item_goodslist_img);
		LinearLayout.LayoutParams Params = (LinearLayout.LayoutParams) viewHolder.BestImg.getLayoutParams();
		Params.width = MainActivity.width/4;
		Params.height = MainActivity.width/4;
		
		viewHolder.BestName = (TextView) convertView.findViewById(R.id.item_goodslist_name);
		viewHolder.BestBrief = (TextView) convertView.findViewById(R.id.item_goodslist_brief);
		viewHolder.BestShopPrice = (TextView) convertView.findViewById(R.id.item_goodslist_shop);
		viewHolder.BestMarketPrice = (TextView) convertView.findViewById(R.id.item_goodslist_market);
		
		GoodsList goodsList = mList.get(position);
		
		viewHolder.BestName.setText(goodsList.getGoods_name());
		viewHolder.BestBrief.setText(goodsList.getGoods_brief());
		
		if (goodsList.getPromote_price() == 0.00) {
			viewHolder.BestShopPrice.setText("￥"+goodsList.getShop_price());
			viewHolder.BestMarketPrice.setText("￥"+goodsList.getMarket_price());
		}else {
			viewHolder.BestShopPrice.setText("￥"+goodsList.getPromote_price());
			viewHolder.BestMarketPrice.setText("￥"+goodsList.getShop_price());
		}
		
		if (goodsList.getGoods_thumb() != null) {
			mImageLoader.DisplayImage(goodsList.getGoods_thumb(), viewHolder.BestImg);
		} else
			viewHolder.BestImg.setImageResource(R.drawable.ic_launcher);
		
		return convertView;
	}
	
	class ViewHolder{
		ImageView BestImg;
		TextView BestName;
		TextView BestBrief;
		TextView BestShopPrice;
		TextView BestMarketPrice;
		
	}

}
