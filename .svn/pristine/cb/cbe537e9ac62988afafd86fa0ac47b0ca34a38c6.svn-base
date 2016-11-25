package com.lester.youmao.adapter;

import java.util.ArrayList;

import com.lester.youmao.R;
import com.lester.youmao.entity.CateGory;
import com.lester.youmao.entity.Coupon;
import com.lester.youmao.entity.HomeCat;
import com.lester.youmao.me.CouponsListActivity;
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

public class CheckCouponAdapter extends BaseAdapter{
	
	private ArrayList<Coupon> mList;
	private LayoutInflater mInflater;
	private ViewHolder viewHolder;
	private ImageLoader mImageLoader;
	private Context c;
	
	public CheckCouponAdapter(Context c ,ArrayList<Coupon> list ){
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
		
		convertView = mInflater.inflate(R.layout.list_item_checkcoupon, null);
		
		viewHolder = new ViewHolder();
		
		viewHolder.mCheck = (ImageView) convertView.findViewById(R.id.item_checkcoupon_check);
		viewHolder.mName = (TextView) convertView.findViewById(R.id.item_checkcoupon_name);
		viewHolder.mContent = (TextView) convertView.findViewById(R.id.item_checkcoupon_content);
		
		Coupon coupon = mList.get(position);
		
		if (coupon.isCheck()) {
			viewHolder.mCheck.setImageResource(R.drawable.gouxuan_true);
		}else {
			viewHolder.mCheck.setImageResource(R.drawable.gouxuan_false);
		}
		
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
		
		return convertView;
	}
	
	class ViewHolder{
		ImageView mCheck;
		TextView mName;
		TextView mContent;
	}

}
