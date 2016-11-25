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
import com.lester.youmao.R;
import com.lester.youmao.adapter.OrderAdapter;
import com.lester.youmao.adapter.OrderBackListAdapter;
import com.lester.youmao.entity.Order;
import com.lester.youmao.entity.OrderBack;
import com.lester.youmao.home.GoodsListActivity;
import com.lester.youmao.userinfo.mUserInfo;
import com.sanmi.http.Constants;
import com.sanmi.http.JsonUtil;
import com.sanmi.http.PublicRequest;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class OrderBackListActivity extends Activity{
	
	private TextView mTitle;
	private ImageView mBack;
	
	private PullToRefreshListView mRefreshListView;
	private ArrayList<OrderBack> mList = new ArrayList<OrderBack>();
	private OrderBackListAdapter mAdapter;
	
	private LodingDialog dialog;
	
	private int page = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.me_orderback);
		
		sendHttp();
		
		initView();
		
	}
	
	private void initView() {
		mTitle = (TextView) findViewById(R.id.top_title);
		mTitle.setText("退换货");
		mBack = (ImageView) findViewById(R.id.top_back);
		mBack.setImageResource(R.drawable.back);
		mBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		mRefreshListView = (PullToRefreshListView) findViewById(R.id.orderback_lv);
		mAdapter = new OrderBackListAdapter(OrderBackListActivity.this, mList);
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
		dialog=LodingDialog.DialogFactor(OrderBackListActivity.this, "正在加载", false);
		PublicRequest http = new PublicRequest(mHandler);
		http.OrderBackList(OrderBackListActivity.this, mUserInfo.GetUserInfo(OrderBackListActivity.this).getUser().getUser_id(), page+"");
		
	}

	Handler mHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			try {
				if(dialog!=null){
					dialog.dismiss();
				}
				switch (msg.what) {
				case Constants.ORDER_BACK_LIST:
					JSONObject jsonObj = new JSONObject(msg.obj.toString());
					if (jsonObj.getString("code").equals("1")) {
						String jsonData = jsonObj.getString("data");
						ArrayList<OrderBack> mList2 = new ArrayList<OrderBack>();
						mList2 = JsonUtil.instance().fromJson(jsonData,new TypeToken<ArrayList<OrderBack>>() {}.getType());
						if (mList2 != null) {
							mList.addAll(mList2);
							mAdapter = new OrderBackListAdapter(OrderBackListActivity.this, mList);
							mRefreshListView.setAdapter(mAdapter);
							if (mList.size() < 30) {
								mRefreshListView.setPullToRefreshEnabled(false);
								if (page != 1) {
									Toast.ToastMe(OrderBackListActivity.this, "没有更多了");
								}
							}
						}
					} else {
						Toast.ToastMe(OrderBackListActivity.this,jsonObj.getString("message"));
					}
					break;
				}
			}catch (JSONException e) {
				e.printStackTrace();
			}
		};
	};

}
