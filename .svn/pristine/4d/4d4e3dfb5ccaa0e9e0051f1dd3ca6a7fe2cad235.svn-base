package com.lester.youmao.adapter;

import java.util.ArrayList;

import com.bset.tool.Texttool;
import com.lester.youmao.R;
import com.lester.youmao.entity.CateGory;
import com.lester.youmao.entity.Coupon;
import com.lester.youmao.entity.HomeCat;
import com.lester.youmao.entity.Notice;
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

public class NoticeAdapter extends BaseAdapter{
	
	private ArrayList<Notice> mList;
	private LayoutInflater mInflater;
	private ViewHolder viewHolder;
	private Context c;
	
	public NoticeAdapter(Context c ,ArrayList<Notice> list ){
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
		
		convertView = mInflater.inflate(R.layout.list_item_notice , null);
		
		viewHolder = new ViewHolder();
				
		viewHolder.Title = (TextView) convertView.findViewById(R.id.item_notice_title);
		
		Notice notice = mList.get(position);
		
		Texttool.setText(viewHolder.Title, notice.getTitle());
		
		return convertView;
	}
	
	class ViewHolder{
		TextView Title;
	}

}
