package com.lester.youmao.adapter;

import java.util.ArrayList;

import com.bset.tool.ListUtil;
import com.bset.tool.Texttool;
import com.bset.tool.Toast;
import com.lester.youmao.R;
import com.lester.youmao.entity.Order;
import com.lester.youmao.entity.OrderGoods;
import com.lester.youmao.me.OrderBackActivity;
import com.lester.youmao.shopcart.CheckOrderActivity;
import com.lester.youmao.userinfo.mUserInfo;
import com.sanmi.http.PublicRequest;
import com.weixin.paydemo.PayActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class OrderAdapter extends BaseAdapter {
	private ArrayList<Order> mList ;
	private LayoutInflater mInflater;
	private Context c;
	private int type;
	private ViewHolder viewHolder;
	private Handler mHandler;

	private ArrayList<OrderGoods> list2 = new ArrayList<OrderGoods>();

	public OrderAdapter(Context c, ArrayList<Order> list, int type, Handler handler) {
		mList = list;
		mInflater = LayoutInflater.from(c);
		this.c = c;
		this.type = type;
		mHandler = handler;
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
		convertView = mInflater.inflate(R.layout.list_item_order, null);

		viewHolder = new ViewHolder();
		
		viewHolder.OrderSn = (TextView) convertView.findViewById(R.id.order_sn);
		viewHolder.List = (ListView) convertView.findViewById(R.id.order_lv);
		viewHolder.TotalFee = (TextView) convertView.findViewById(R.id.order_fee);
		viewHolder.Btn01 = (Button) convertView.findViewById(R.id.order_btn01);
		viewHolder.Btn02 = (Button) convertView.findViewById(R.id.order_btn02);
		viewHolder.Btn02.setOnClickListener(new BtnClick(position));

		Order order = mList.get(position);

		list2 = order.getGoods_list();
		
		Texttool.setText(viewHolder.OrderSn, "订单编号： "+order.getOrder_sn());
		Order2Adapter adapter = new Order2Adapter(c, list2, type, order.getOrder_type());
		viewHolder.List.setAdapter(adapter);
		ListUtil.setListViewHeightBasedOnChildren(viewHolder.List, 0);
		
		Log.i("asdasd", order.getOrder_info().getOrder_amount()+"");
		
		Texttool.setText(viewHolder.TotalFee, order.getTotal_fee()+"(快递 "+order.getFormated_shipping_fee()+")");
		switch (type) {
		case 1:
			Texttool.setText(viewHolder.Btn02, "立即支付");
			break;
		case 2:
			Texttool.setText(viewHolder.Btn02, "确认收货");
			break;
		case 3:
			if (order.getOrder_type().equals("1")) {
				if (order.getOrder_back_status().equals("0")) {
					Texttool.setText(viewHolder.Btn02, "申请退换货");
					viewHolder.Btn02.setEnabled(true);
				}else if (order.getOrder_back_status().equals("1")) {
					Texttool.setText(viewHolder.Btn02, "待审核");
					viewHolder.Btn02.setEnabled(false);
				}else if (order.getOrder_back_status().equals("2")) {
					Texttool.setText(viewHolder.Btn02, "已审核");
					viewHolder.Btn02.setEnabled(false);
				}else if (order.getOrder_back_status().equals("3")) {
					Texttool.setText(viewHolder.Btn02, "已拒绝退换");
					viewHolder.Btn02.setEnabled(false);
				}
			}else if (order.getOrder_type().equals("2")) {
				Texttool.setText(viewHolder.Btn02, "积分商品不可退换");
				viewHolder.Btn02.setEnabled(false);
			}else {
				Texttool.setText(viewHolder.Btn02, "申请退换货");
				viewHolder.Btn02.setEnabled(true);
			}
			break;
		}
		
		return convertView;
	}

	class ViewHolder {
		TextView OrderSn;
		ListView List;
		TextView TotalFee;
		Button Btn01;
		Button Btn02;
	}
	
	class BtnClick implements OnClickListener{
		
		private int i;
		
		public BtnClick (int position){
			i = position;
		}

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.order_btn02:
				switch (type) {
				case 1:
					if (mList.get(i).getOrder_info().getPay_code().equals("wx_new_jspay")) {
						Intent intent = new Intent();
						intent.putExtra("name", mList.get(i).getOrder_info().getSubject());
						intent.putExtra("amount", mList.get(i).getOrder_info().getOrder_amount());
						intent.putExtra("order_sn", mList.get(i).getOrder_info().getOrder_sn());
						intent.setClass(c, PayActivity.class);
						c.startActivity(intent);
					}
//					Toast.ToastMe(c, "去支付");
//					mList.get(i).getOrder_info().getSubject();
//					mList.get(i).getOrder_info().getOrder_amount();
//					mList.get(i).getOrder_info().getOrder_sn();
					break;
				case 2:
//					Toast.ToastMe(c, "hhhh="+i);
					PublicRequest http = new PublicRequest(mHandler);
					http.OrderAffirm(c, mUserInfo.GetUserInfo((Activity)c).getSession().getSid(), mUserInfo.GetUserInfo((Activity)c).getSession().getUid(), mList.get(i).getOrder_id());
					break;
				case 3:
					Intent intent= new Intent();
					intent.putExtra("goodslist", mList.get(i).getGoods_list());
					intent.putExtra("order_id", mList.get(i).getOrder_id());
					intent.setClass(c, OrderBackActivity.class);
					c.startActivity(intent);
					break;
				}
				break;
			}
		}
	}

}
