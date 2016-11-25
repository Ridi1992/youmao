package com.lester.youmao.adapter;

import java.util.ArrayList;

import com.lester.youmao.MainActivity;
import com.lester.youmao.R;
import com.lester.youmao.entity.OrderGoods;
import com.lester.youmao.me.PinglunActivity;
import com.sanmi.loader.ImageLoader;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;

public class OrderBackAdapter extends BaseAdapter{
	private ArrayList<OrderGoods> mList;
	private LayoutInflater mInflater;
	private ImageLoader mImageLoader;
	private Context c;

	public OrderBackAdapter(Context c,ArrayList<OrderGoods> list){
		mList = list;
		this.c = c;
		if (c != null) {
			mInflater = LayoutInflater.from(c);
			mImageLoader = new ImageLoader(c);
		}
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
			convertView = mInflater.inflate(R.layout.list_item_orderbackgoods, null);
		}
			ImageView check = (ImageView) convertView.findViewById(R.id.orderback_check);
			TextView tvName = (TextView) convertView.findViewById(R.id.ordergoods_name);
			ImageView ivImg = (ImageView) convertView.findViewById(R.id.ordergoods_img);
			LayoutParams lParams = (LayoutParams) ivImg.getLayoutParams();
			lParams.width = MainActivity.width/4;
			lParams.height = MainActivity.width/4;
			TextView tvPrice = (TextView) convertView.findViewById(R.id.ordergoods_price);
			TextView tvAttrs = (TextView) convertView.findViewById(R.id.ordergoods_attr);
			TextView tvNum = (TextView) convertView.findViewById(R.id.ordergoods_number);
			TextView button = (TextView) convertView.findViewById(R.id.ordergoods_comment);
			
			OrderGoods order2 = mList.get(position);
			
			if (order2.isCheck()) {
				check.setImageResource(R.drawable.gouxuan_true);
			}else {
				check.setImageResource(R.drawable.gouxuan_false);
			}
			
			tvName.setText(order2.getName());
			String attr = "";
			for (int i = 0; i < order2.getGoods_spec().size(); i++) {
				attr = attr+ order2.getGoods_spec().get(i)+"\t";
				Log.i("asda", "aa="+order2.getGoods_spec().get(i));
			}
			tvAttrs.setText(attr);
			
			button.setVisibility(View.GONE);
			tvPrice.setText("总价  "+order2.getSubtotal());
			tvNum.setText("× "+order2.getGoods_number());
			
			if (order2.getImg().getThumb() != null) {
				mImageLoader.DisplayImage(order2.getImg().getThumb(), ivImg);
			} else
				ivImg.setImageResource(R.drawable.no_img);
			
			
		return convertView;
	}

}
