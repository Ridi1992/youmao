package com.lester.youmao.adapter;

import java.util.ArrayList;

import com.best.view.CircleImageView;
import com.bset.tool.Texttool;
import com.lester.youmao.R;
import com.lester.youmao.adapter.CheckDoorAdapter.ViewHolder;
import com.lester.youmao.entity.GoodsComment;
import com.lester.youmao.entity.Value;
import com.sanmi.loader.ImageLoader_Circle;

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

public class CommentAdapter extends BaseAdapter {

	private ArrayList<GoodsComment> mList;
	private LayoutInflater mInflater;
	private ViewHolder viewHolder;
	private ImageLoader_Circle loader_Circle;

	public CommentAdapter(Context c, ArrayList<GoodsComment> list) {
		mList = list;
		mInflater = LayoutInflater.from(c);
		loader_Circle = new ImageLoader_Circle(c);
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
		convertView = mInflater.inflate(R.layout.list_item_comment, null);

		viewHolder = new ViewHolder();
		
		viewHolder.face = (CircleImageView) convertView.findViewById(R.id.item_comment_face);
		viewHolder.name = (TextView) convertView.findViewById(R.id.item_comment_name);
		viewHolder.time = (TextView) convertView.findViewById(R.id.item_comment_time);
		viewHolder.content = (TextView) convertView.findViewById(R.id.item_comment_comtent);

		GoodsComment comment = mList.get(position);
		
		loader_Circle.DisplayImage(comment.getFace_img(), viewHolder.face);
		Texttool.setText(viewHolder.name, comment.getAuthor());
		Texttool.setText(viewHolder.time, comment.getCreate());
		Texttool.setText(viewHolder.content, comment.getContent());

		return convertView;
	}

	class ViewHolder {
		CircleImageView face;
		TextView name;
		TextView time;
		TextView content;
	}

}
