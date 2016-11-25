package com.lester.youmao.adapter;

import java.util.ArrayList;

import com.bset.tool.Texttool;
import com.lester.youmao.R;
import com.lester.youmao.entity.Address;
import com.lester.youmao.entity.CateGory;
import com.lester.youmao.entity.Coupon;
import com.lester.youmao.entity.HomeCat;
import com.lester.youmao.entity.Notice;
import com.lester.youmao.me.AddressListActivity;
import com.lester.youmao.me.CouponsListActivity;
import com.lester.youmao.userinfo.mUserInfo;
import com.sanmi.http.PublicRequest;
import com.sanmi.loader.ImageLoader;

import android.content.Context;
import android.os.Handler;
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

public class AddressAdapter extends BaseAdapter{
	
	private ArrayList<Address> mList;
	private LayoutInflater mInflater;
	private ViewHolder viewHolder;
	private AddressListActivity c;
	private Handler mHandler;
	private String action;
	
	public AddressAdapter(AddressListActivity c ,ArrayList<Address> list, Handler handler, String action){
		mList = list;
		this.c= c;
		mInflater = LayoutInflater.from(c);
		mHandler = handler;
		this.action = action;
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
		
		convertView = mInflater.inflate(R.layout.list_item_address , null);
		
		viewHolder = new ViewHolder();
				
		
		viewHolder.Default_line = (LinearLayout) convertView.findViewById(R.id.item_address_line);
		viewHolder.Default_img = (ImageView) convertView.findViewById(R.id.item_address_default);
		viewHolder.Name = (TextView) convertView.findViewById(R.id.item_address_name);
		viewHolder.Address = (TextView) convertView.findViewById(R.id.item_address_address);
		viewHolder.Mobile = (TextView) convertView.findViewById(R.id.item_address_tel);
		if (action.equals("")) {
			viewHolder.Default_line.setOnClickListener(new SetDefault(position));
		}
		
		Address address = mList.get(position);
		
		if (address.isDefault_id()) {
			viewHolder.Default_img.setImageResource(R.drawable.gouxuan_true);
		}else {
			viewHolder.Default_img.setImageResource(R.drawable.gouxuan_false);
		}
		Texttool.setText(viewHolder.Name, address.getConsignee());
		Texttool.setText(viewHolder.Address, address.getProvince_name()+address.getCity_name()+address.getDistrict_name()+address.getAddress());
		Texttool.setText(viewHolder.Mobile, address.getMobile());
		
		return convertView;
	}
	
	class ViewHolder{
		LinearLayout Default_line;
		ImageView Default_img;
		TextView Name;
		TextView Address;
		TextView Mobile;
	}
	
	class SetDefault implements OnClickListener{
		private int i;
		SetDefault(int i){
			this.i = i;
		}
		@Override
		public void onClick(View v) {
			PublicRequest http = new PublicRequest(mHandler);
			http.AddressDefault(c, mUserInfo.GetUserInfo(c).getSession().getSid(), mUserInfo.GetUserInfo(c).getSession().getSid(), mList.get(i).getId());
		}
	}

}
