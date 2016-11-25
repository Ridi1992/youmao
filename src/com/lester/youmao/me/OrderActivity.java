package com.lester.youmao.me;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import com.bset.tool.Toast;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.lester.youmao.LodingDialog;
import com.lester.youmao.LoginActivity;
import com.lester.youmao.R;
import com.lester.youmao.adapter.OrderAdapter;
import com.lester.youmao.entity.Notice;
import com.lester.youmao.entity.Order;
import com.lester.youmao.home.GoodsListActivity;
import com.lester.youmao.userinfo.mUserInfo;
import com.sanmi.http.Constants;
import com.sanmi.http.JsonUtil;
import com.sanmi.http.PublicRequest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class OrderActivity extends Activity{
	
	private TextView mTitle;
	private ImageView mBack;
	
	private PullToRefreshListView mRefreshListView;
	private ArrayList<Order> mList = new ArrayList<Order>();
	private OrderAdapter mAdapter;
	
	private int order_type_id = 0;//1待付款  2代收货  3待评价
	private String order_type = "";
	private int page = 1;
	
	private LodingDialog dialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.me_order);
		
		order_type_id = getIntent().getIntExtra("order", 0);
		
		initView();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		switch (order_type_id) {
		case 1:
			order_type = "await_pay";
			break;
		case 2:
			order_type = "shipped";
			break;
		case 3:
			order_type = "finished";
			break;
		}
		mList = new ArrayList<Order>();
		sendHttp();
	}
	
	private void initView() {
		mTitle = (TextView) findViewById(R.id.top_title);
		switch (order_type_id) {
		case 1:
			mTitle.setText("待付款");
			break;
		case 2:
			mTitle.setText("待收货");
			break;
		case 3:
			mTitle.setText("已完成");
			break;
		}
		mBack = (ImageView) findViewById(R.id.top_back);
		mBack.setImageResource(R.drawable.back);
		mBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		mRefreshListView = (PullToRefreshListView) findViewById(R.id.order_lv);
		mAdapter = new OrderAdapter(OrderActivity.this, mList, order_type_id, mHandler);
		mRefreshListView.setAdapter(mAdapter);
		mRefreshListView.setOnRefreshListener(new OnRefreshListener<ListView>() {
			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				page = page + 1;
				sendHttp();
			}
		});
	}
	
	private void sendHttp(){
		dialog=LodingDialog.DialogFactor(OrderActivity.this, "正在加载", false);
		PublicRequest http = new PublicRequest(mHandler);
		http.OrderList(OrderActivity.this, mUserInfo.GetUserInfo(OrderActivity.this).getSession().getSid(), mUserInfo.GetUserInfo(OrderActivity.this).getSession().getSid(), page+"", order_type);
	}

	Handler mHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			try {
				if(dialog!=null){
					dialog.dismiss();
				}
				switch (msg.what) {
				case Constants.ORDER_LIST:
					JSONObject jsonObj = new JSONObject(msg.obj.toString());
					if (jsonObj.getJSONObject("status").getString("succeed").equals("1")) {
						String jsonData = jsonObj.getString("data");
						ArrayList<Order> mList2 = new ArrayList<Order>();
						mList2 = JsonUtil.instance().fromJson(jsonData,new TypeToken<ArrayList<Order>>() {}.getType());
						if (mList2 != null) {
							mList.addAll(mList2);
							mAdapter = new OrderAdapter(OrderActivity.this, mList, order_type_id, mHandler);
							mRefreshListView.setAdapter(mAdapter);
							if (mList.size() < 100) {
								mRefreshListView.setPullToRefreshEnabled(false);
								if (page != 1) {
									Toast.ToastMe(OrderActivity.this, "没有更多了");
								}
							}                                                             
						}
					} else {
						Toast.ToastMe(OrderActivity.this,jsonObj.getJSONObject("status").getString("error_desc"));
						if (jsonObj.getJSONObject("status").getString("error_code").equals("100")) {
							startActivity(new Intent(OrderActivity.this, LoginActivity.class));
						}
					}
					break;
				case Constants.ORDER_AFFIRM:
					JSONObject jsonObj1 = new JSONObject(msg.obj.toString());
					if (jsonObj1.getJSONObject("status").getString("succeed").equals("1")) {
						Toast.ToastMe(OrderActivity.this,"确认收货成功");
						mList = new ArrayList<Order>();
						sendHttp();
					} else {
						Toast.ToastMe(OrderActivity.this,jsonObj1.getJSONObject("status").getString("error_desc"));
					}
					break;
				}
			}catch (JSONException e) {
				e.printStackTrace();
			}
		};
	};
}
