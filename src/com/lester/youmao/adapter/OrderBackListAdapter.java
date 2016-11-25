package com.lester.youmao.adapter;

import java.util.ArrayList;

import com.bset.tool.Texttool;
import com.lester.youmao.R;
import com.lester.youmao.entity.CateGory;
import com.lester.youmao.entity.Coupon;
import com.lester.youmao.entity.HomeCat;
import com.lester.youmao.entity.Notice;
import com.lester.youmao.entity.OrderBack;
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

public class OrderBackListAdapter extends BaseAdapter{
	
	private ArrayList<OrderBack> mList;
	private LayoutInflater mInflater;
	private ViewHolder viewHolder;
	private Context c;
	
	public OrderBackListAdapter(Context c ,ArrayList<OrderBack> list ){
		mList = list;
		this.c= c;
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
		
		convertView = mInflater.inflate(R.layout.list_item_orderback , null);
		
		viewHolder = new ViewHolder();
				
		viewHolder.OrderSn = (TextView) convertView.findViewById(R.id.item_orderback_ordersn);
		viewHolder.Case = (TextView) convertView.findViewById(R.id.item_orderback_case);
		viewHolder.Reason = (TextView) convertView.findViewById(R.id.item_orderback_reason);
		viewHolder.Mobile = (TextView) convertView.findViewById(R.id.item_orderback_tel);
		viewHolder.Time = (TextView) convertView.findViewById(R.id.item_orderback_tiem);
		viewHolder.Status = (TextView) convertView.findViewById(R.id.item_orderback_status);
		
		OrderBack orderBack = mList.get(position);
		
		Texttool.setText(viewHolder.OrderSn, "订单编号： "+orderBack.getOrder_sn());
		if (orderBack.getCase_id().equals("1")) {
			Texttool.setText(viewHolder.Case, "申请类型： 申请退货");
		}else if (orderBack.getCase_id().equals("2")) {
			Texttool.setText(viewHolder.Case, "申请类型： 申请换货");
		}
		Texttool.setText(viewHolder.Reason, "申请理由： "+orderBack.getReason());
		Texttool.setText(viewHolder.Mobile, "联系电话： "+orderBack.getMobile());
		Texttool.setText(viewHolder.Time, "申请时间： "+orderBack.getAdd_time());
		
		if (orderBack.getStatus().equals("1")) {
			Texttool.setText(viewHolder.Status, "申请状态： 待审核");
		}else if (orderBack.getStatus().equals("2")) {
			Texttool.setText(viewHolder.Status, "申请状态： 已审核");
		}else if (orderBack.getStatus().equals("3")) {
			Texttool.setText(viewHolder.Status, "申请状态： 已拒绝");
		}
		
		return convertView;
	}
	
	class ViewHolder{
		TextView OrderSn;
		TextView Case;
		TextView Reason;
		TextView Mobile;
		TextView Time;
		TextView Status;
	}

}
