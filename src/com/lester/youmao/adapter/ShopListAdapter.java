package com.lester.youmao.adapter;

import java.util.ArrayList;

import com.bset.tool.Texttool;
import com.bset.tool.Toast;
import com.lester.youmao.MainActivity;
import com.lester.youmao.R;
import com.lester.youmao.entity.GoodsList;
import com.lester.youmao.entity.HomeBest;
import com.lester.youmao.entity.HomeCat;
import com.lester.youmao.entity.HomePromote;
import com.lester.youmao.entity.ShopCart;
import com.lester.youmao.entity.ShopGoods;
import com.lester.youmao.userinfo.mUserInfo;
import com.sanmi.http.PublicRequest;
import com.sanmi.loader.ImageLoader;

import android.app.Activity;
import android.app.Dialog;
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

public class ShopListAdapter extends BaseAdapter{
	
	private ArrayList<ShopGoods> mList;
	private LayoutInflater mInflater;
	private ViewHolder viewHolder;
	private ImageLoader mImageLoader;
	private Context c;
	private Handler handler;
	private int[] GoodsNums;
	
	public ShopListAdapter(Context c ,ArrayList<ShopGoods> list, Handler handler){
		mList = list;
		this.c = c;
		this.handler = handler;
		mInflater = LayoutInflater.from(c);
		mImageLoader = new ImageLoader(c);
		GoodsNums = new int[mList.size()];
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
		
		convertView = mInflater.inflate(R.layout.list_item_shopcart, null);
		
		viewHolder = new ViewHolder();
		
		viewHolder.shopCartDelete = (ImageView) convertView.findViewById(R.id.item_shopcart_delete);
		viewHolder.shopCartDelete.setOnClickListener(new ItemClick(position));
		viewHolder.shopCartImg = (ImageView) convertView.findViewById(R.id.item_shopcart_img);
		LinearLayout.LayoutParams Params = (LinearLayout.LayoutParams) viewHolder.shopCartImg.getLayoutParams();
		Params.width = MainActivity.width/4;
		Params.height = MainActivity.width/4;
		viewHolder.shopCartName = (TextView) convertView.findViewById(R.id.item_shopcart_name);
		viewHolder.shopCartAttr = (TextView) convertView.findViewById(R.id.item_shopcart_attr);
		viewHolder.shopCartPrice = (TextView) convertView.findViewById(R.id.item_shopcart_price);
		viewHolder.shopCartNum = (TextView) convertView.findViewById(R.id.goods_count_num);
		viewHolder.shopCartMin = (Button) convertView.findViewById(R.id.goods_count_min);
		viewHolder.shopCartMax = (Button) convertView.findViewById(R.id.goods_count_max);
		viewHolder.shopCartMin.setOnClickListener(new ItemClick(position));
		viewHolder.shopCartMax.setOnClickListener(new ItemClick(position));
		
		ShopGoods shopGoods = mList.get(position);
		
		Texttool.setText(viewHolder.shopCartName, shopGoods.getGoods_name());
		Texttool.setText(viewHolder.shopCartPrice, shopGoods.getSubtotal());
		String attr = "";
		for (int i = 0; i < shopGoods.getGoods_attr().size(); i++) {
			attr = attr + shopGoods.getGoods_attr().get(i).getValue();
		}
		Texttool.setText(viewHolder.shopCartAttr, attr);
		Texttool.setText(viewHolder.shopCartNum, shopGoods.getGoods_number()+"");
		GoodsNums[position] = shopGoods.getGoods_number();
		
		if (shopGoods.getImg().getThumb() != null) {
			mImageLoader.DisplayImage(shopGoods.getImg().getThumb(), viewHolder.shopCartImg);
		} else
			viewHolder.shopCartImg.setImageResource(R.drawable.ic_launcher);
		
		return convertView;
	}
	
	class ViewHolder{
		ImageView shopCartDelete;
		ImageView shopCartImg;
		TextView shopCartName;
		TextView shopCartAttr;
		TextView shopCartPrice;
		Button shopCartMin;
		Button shopCartMax;
		TextView shopCartNum;
	}
	
	class ItemClick implements OnClickListener{
		
		private int i;
		
		public ItemClick(int i) {
			this.i = i;
		}

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.item_shopcart_delete:
 				DialogShow();
				break;
			case R.id.goods_count_min:
				if (GoodsNums[i] > 1) {
					PublicRequest http = new PublicRequest(handler);
					http.CartUpdate(c, mUserInfo.GetUserInfo((Activity)c).getSession().getSid(), mUserInfo.GetUserInfo((Activity)c).getSession().getUid(), mList.get(i).getRec_id(),GoodsNums[i]-1+"");
					GoodsNums[i] = GoodsNums[i]-1;
				}else {
					
				}
				break;
			case R.id.goods_count_max:
				PublicRequest http = new PublicRequest(handler);
				http.CartUpdate(c, mUserInfo.GetUserInfo((Activity)c).getSession().getSid(), mUserInfo.GetUserInfo((Activity)c).getSession().getUid(), mList.get(i).getRec_id(),GoodsNums[i]+1+"");
				break;
			}
			
		}

		private void DialogShow() {
			final Dialog lodingDialog = new Dialog(c,R.style.MyDialog);
			View  v = mInflater.inflate(R.layout.dialog_shopcart, null);
			lodingDialog.setContentView(v);
			lodingDialog.show();
			TextView btnSure = (TextView) v.findViewById(R.id.dialog_true);
			TextView btnClear = (TextView) v.findViewById(R.id.dialog_false);
			btnSure.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					PublicRequest http = new PublicRequest(handler);
					http.CartDelete(c, mUserInfo.GetUserInfo((Activity)c).getSession().getSid(), mUserInfo.GetUserInfo((Activity)c).getSession().getUid(), mList.get(i).getRec_id());
					lodingDialog.dismiss();
				}
			});
			btnClear.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					lodingDialog.dismiss();
				}
			});
		}
		
	}

}
