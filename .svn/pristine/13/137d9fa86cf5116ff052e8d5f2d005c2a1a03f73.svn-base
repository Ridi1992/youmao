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

public class Order2Adapter extends BaseAdapter{
	private ArrayList<OrderGoods> mList;
	private LayoutInflater mInflater;
	private ImageLoader mImageLoader;
	private Context c;
	private int type;
	private String order_type;

	public Order2Adapter(Context c,ArrayList<OrderGoods> list, int type , String order_type){
		mList = list;
		this.c = c;
		this.type = type;
		this.order_type = order_type;
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
			convertView = mInflater.inflate(R.layout.list_item_ordergoods, null);
		}
			
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
			
			tvName.setText(order2.getName());
			String attr = "";
			for (int i = 0; i < order2.getGoods_spec().size(); i++) {
				attr = attr+ order2.getGoods_spec().get(i)+"\t";
				Log.i("asda", "aa="+order2.getGoods_spec().get(i));
			}
			tvAttrs.setText(attr);
			
			if (type == 3) {
				button.setVisibility(View.VISIBLE);
				if (order2.getIs_comment().equals("0")) {
					button.setText("评价");
					button.setEnabled(true);
					button.setOnClickListener(new toPinglun(position));
				}else if (order2.getIs_comment().equals("1")) {
					button.setText("已评价");
					button.setEnabled(false);
				}else {
					button.setVisibility(View.GONE);
				}
			}else {
				button.setVisibility(View.GONE);
			}
			
			if (order_type.equals("1")) {
				tvPrice.setText("总价  "+order2.getSubtotal());
			}else if(order_type.equals("2")){
				tvPrice.setText("总积分  "+order2.getOrder_integral());
			}
			tvNum.setText("× "+order2.getGoods_number());
			
//			if (shipping_status.equals("1") || shipping_status.equals("2")) {
//				if (order2.getComment_status().equals("0")) {
//					button.setVisibility(View.VISIBLE);
//					button.setOnClickListener(new toPinglun(position));
//				}else {
//					button.setVisibility(View.GONE);
//				}
//			}
			
			if (order2.getImg().getThumb() != null) {
				mImageLoader.DisplayImage(order2.getImg().getThumb(), ivImg);
			} else
				ivImg.setImageResource(R.drawable.no_img);
			
			
		return convertView;
	}
	
	class toPinglun implements OnClickListener{
		
		private int i;

		public toPinglun(int i) {
			this.i = i;
		}
		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.ordergoods_comment:
				Intent intent = new Intent();
				intent.putExtra("order_id", mList.get(i).getOrder_id());
				intent.putExtra("goods_id", mList.get(i).getGoods_id());
				intent.setClass(c, PinglunActivity.class);
				c.startActivity(intent);
				break;
			}
		}    
    }  

}
