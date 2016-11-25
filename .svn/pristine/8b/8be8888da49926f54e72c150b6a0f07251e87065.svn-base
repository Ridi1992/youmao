package com.lester.youmao.adapter;

import java.util.ArrayList;

import com.lester.youmao.R;
import com.lester.youmao.entity.CateGory;
import com.lester.youmao.entity.Coupon;
import com.lester.youmao.entity.HomeCat;
import com.lester.youmao.me.CouponsListActivity;
import com.lester.youmao.me.MyCouponsListActivity;
import com.sanmi.loader.ImageLoader;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

public class MyCouponAdapter extends BaseAdapter{
	
	private ArrayList<Coupon> mList;
	private LayoutInflater mInflater;
	private ViewHolder viewHolder;
	private ImageLoader mImageLoader;
	private MyCouponsListActivity c;
	
	public MyCouponAdapter(MyCouponsListActivity c ,ArrayList<Coupon> list ){
		mList = list;
		this.c= c;
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
		
		convertView = mInflater.inflate(R.layout.list_item_coupon, null);
		
		viewHolder = new ViewHolder();
		
		viewHolder.mName = (TextView) convertView.findViewById(R.id.item_coupon_name);
		viewHolder.mContent = (TextView) convertView.findViewById(R.id.item_coupon_content);
		viewHolder.mLingQu = (Button) convertView.findViewById(R.id.item_coupon_ling);
		viewHolder.mLingQu.setVisibility(View.GONE);
		
		Coupon coupon = mList.get(position);
		
		if (coupon.getType_id().equals("1")) {
			viewHolder.mName.setText(coupon.getType_money()+"元抵用券");
		}else if (coupon.getType_id().equals("2")) {
			viewHolder.mName.setText(coupon.getType_money()+"元抵用券");
			viewHolder.mContent.setVisibility(View.VISIBLE);
			viewHolder.mContent.setText("满"+coupon.getUse_amount()+"元可用");
		}else if (coupon.getType_id().equals("3")) {
			viewHolder.mName.setText(coupon.getCoupons_discount()/10+"折折扣券");
			viewHolder.mContent.setVisibility(View.VISIBLE);
			viewHolder.mContent.setText("满"+coupon.getBuy_number()+"件可用");
		}
		viewHolder.mLingQu.setText(coupon.getCoupons_status());
		
		return convertView;
	}
	
	class ViewHolder{
		TextView mName;
		TextView mContent;
		Button mLingQu;
	}

}
