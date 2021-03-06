package com.lester.youmao.me;

import java.util.ArrayList;

import org.json.JSONObject;

import com.bset.tool.Toast;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lester.youmao.LodingDialog;
import com.lester.youmao.R;
import com.lester.youmao.adapter.MyCouponAdapter;
import com.lester.youmao.entity.Coupon;
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
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class MyCouponsListActivity extends Activity implements OnCheckedChangeListener{
	
	private TextView mTitle;
	private ImageView mBack;
	
	private RadioGroup mRadioGroup;
	private String type = "1";
	
	private PullToRefreshListView mRefreshListView;
	private ArrayList<Coupon> mList = new ArrayList<Coupon>();
	private MyCouponAdapter mAdapter;
	
	private LodingDialog dialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.me_mycoupon);
		
		sendHttp();
		initView();
		
	}

	private void sendHttp() {
		dialog=LodingDialog.DialogFactor(MyCouponsListActivity.this, "正在加载", false);
		PublicRequest http = new PublicRequest(mHandler);
		http.MyCouponList(MyCouponsListActivity.this, mUserInfo.GetUserInfo(MyCouponsListActivity.this).getUser().getUser_id(), type);
	}

	@SuppressWarnings("unchecked")
	private void initView() {
		mTitle = (TextView) findViewById(R.id.top_title);
		mTitle.setText("我的优惠券");
		mBack = (ImageView) findViewById(R.id.top_back);
		mBack.setImageResource(R.drawable.back);
		mBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		mRadioGroup = (RadioGroup) findViewById(R.id.coupon_list_rg);
		mRadioGroup.setOnCheckedChangeListener(this);
		mRefreshListView = (PullToRefreshListView) findViewById(R.id.coupon_list_lv);
		mRefreshListView.setMode(Mode.PULL_FROM_END);
		mRefreshListView.setOnRefreshListener(new OnRefreshListener2() {
			@Override
			public void onPullDownToRefresh(PullToRefreshBase refreshView) {
				mRefreshListView.onRefreshComplete();
			}

			@Override
			public void onPullUpToRefresh(PullToRefreshBase refreshView) {
				sendHttp();
			}
			
		});
		mAdapter = new MyCouponAdapter(MyCouponsListActivity.this, mList);
		mRefreshListView.setAdapter(mAdapter);
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkdeId) {
		
		switch (checkdeId) {
		case R.id.coupon_list_tongyong:
			type = "1";
			sendHttp();
			break;
		case R.id.coupon_list_song:
			type = "2";
			sendHttp();
			break;
		case R.id.coupon_list_zhe:
			type = "3";
			sendHttp();
			break;
		}
	}
	
	public void ReceiveCoupon(int position){
		PublicRequest http = new PublicRequest(mHandler);
		http.CouponReceive(MyCouponsListActivity.this, mUserInfo.GetUserInfo(MyCouponsListActivity.this).getUser().getUser_id(), mList.get(position).getId());
	}
	
	Handler mHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			try {
				if(dialog!=null){
					dialog.dismiss();
				}
				mRefreshListView.onRefreshComplete();
				switch (msg.what) {
				case Constants.MYCOUPON_LIST:
					JSONObject jsonObj = new JSONObject(msg.obj.toString());
					if (jsonObj.getString("code").equals("1")) {
						String jsonData = jsonObj.getString("data");
						mList = JsonUtil.instance().fromJson(jsonData,new TypeToken<ArrayList<Coupon>>() {}.getType());
						if (mList != null) {
							mAdapter = new MyCouponAdapter(MyCouponsListActivity.this, mList);
							mRefreshListView.setAdapter(mAdapter);
						}
					} else {
						mList.clear();
						mAdapter.notifyDataSetChanged();
						Toast.ToastMe(MyCouponsListActivity.this,jsonObj.getString("message"));
					}
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		};
	};

}
