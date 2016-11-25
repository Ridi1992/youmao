package com.lester.youmao.adapter;

import java.util.ArrayList;

import com.bset.tool.ListUtil;
import com.bset.tool.Toast;
import com.lester.youmao.R;
import com.lester.youmao.entity.CateGory;
import com.lester.youmao.entity.HomeCat;
import com.lester.youmao.home.GoodsListActivity;
import com.sanmi.loader.ImageLoader;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

public class Cate2Adapter extends BaseAdapter{
	
	private ArrayList<CateGory> mList;
	private LayoutInflater mInflater;
	private ViewHolder viewHolder;
	private ImageLoader mImageLoader;
	private Context c;
	
	public Cate2Adapter(Context c ,ArrayList<CateGory> list ){
		mList = list;
		this.c = c;
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
		
		convertView = mInflater.inflate(R.layout.list_item_cate2, null);
		
		viewHolder = new ViewHolder();
		
		viewHolder.CatName = (TextView) convertView.findViewById(R.id.cate2_name);
		viewHolder.CatGrid = (GridView) convertView.findViewById(R.id.cate2_son);
		
		final CateGory cateGory = mList.get(position);
		
		viewHolder.CatName.setText(cateGory.getCat_name());
		Cate3Adapter adapter = new Cate3Adapter(c, cateGory.getSon());
		viewHolder.CatGrid.setAdapter(adapter);
		ListUtil.setGridViewHeightBasedOnChildren2(viewHolder.CatGrid, 3, 0);
		
		viewHolder.CatGrid.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = new Intent();
				intent.putExtra("son", cateGory.getSon());
				intent.putExtra("cat_id", cateGory.getSon().get(arg2).getCat_id());
				intent.putExtra("cat_name", cateGory.getSon().get(arg2).getCat_name());
				intent.putExtra("title", cateGory.getCat_name());
				intent.setClass(c, GoodsListActivity.class);
				c.startActivity(intent);
				
			}
		});
		
		return convertView;
	}
	
	class ViewHolder{
		TextView CatName;
		GridView CatGrid;
	}

}
