package com.lester.youmao.me;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import com.bset.tool.ListUtil;
import com.bset.tool.Texttool;
import com.bset.tool.Toast;
import com.lester.youmao.R;
import com.lester.youmao.adapter.OrderBackAdapter;
import com.lester.youmao.entity.OrderGoods;
import com.lester.youmao.userinfo.mUserInfo;
import com.sanmi.http.Constants;
import com.sanmi.http.PublicRequest;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class OrderBackActivity extends Activity implements OnClickListener, OnItemClickListener{
	
	private TextView mTitle;
	private ImageView mBack;
	
	private ListView mListView;
	private ArrayList<OrderGoods> mList = new ArrayList<OrderGoods>();
	private OrderBackAdapter mAdapter;
	
	private LinearLayout mLine101;
	private LinearLayout mLine102;
	private ImageView mImage101;
	private ImageView mImage102;
	private LinearLayout mLine201;
	private LinearLayout mLine202;
	private ImageView mImage201;
	private ImageView mImage202;
	private EditText mReason;
	private EditText mMobile;
	private EditText mBankNumber;
	private EditText mBankPlace;
	private Button mSubmit;
	
	private ArrayList<String> goodsid_List = new ArrayList<String>();
	private String goods_id = "";	
	private int case_id = 0;//1退  2换 
	private int pay_id = 0;//1支  2卡 
	private String order_id;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order_back);
		
		order_id = getIntent().getStringExtra("order_id");
		mList = (ArrayList<OrderGoods>) getIntent().getSerializableExtra("goodslist");
		
		initView();
		
	}
	
	private void initView() {
		mTitle = (TextView) findViewById(R.id.top_title);
		mTitle.setText("退换货申请");
		mBack = (ImageView) findViewById(R.id.top_back);
		mBack.setImageResource(R.drawable.back);
		mBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		mListView = (ListView) findViewById(R.id.orderback_goods_lv);
		mAdapter = new OrderBackAdapter(OrderBackActivity.this, mList);
		mListView.setAdapter(mAdapter);
		ListUtil.setListViewHeightBasedOnChildren(mListView, 0);
		mListView.setOnItemClickListener(this);
		
		mLine101 = (LinearLayout) findViewById(R.id.orderback_line101);
		mLine102 = (LinearLayout) findViewById(R.id.orderback_line102);
		mLine101.setOnClickListener(this);
		mLine102.setOnClickListener(this);
		mImage101 = (ImageView) findViewById(R.id.orderback_line101_img);
		mImage102 = (ImageView) findViewById(R.id.orderback_line102_img);
		
		mLine201 = (LinearLayout) findViewById(R.id.orderback_line201);
		mLine202 = (LinearLayout) findViewById(R.id.orderback_line202);
		mLine201.setOnClickListener(this);
		mLine202.setOnClickListener(this);
		mImage201 = (ImageView) findViewById(R.id.orderback_line201_img);
		mImage202 = (ImageView) findViewById(R.id.orderback_line202_img);
		
		mReason = (EditText) findViewById(R.id.orderback_reason);
		mMobile = (EditText) findViewById(R.id.orderback_tel);
		mBankNumber = (EditText) findViewById(R.id.orderback_banknumber);
		mBankPlace = (EditText) findViewById(R.id.orderback_bankplace);
		mSubmit = (Button) findViewById(R.id.orderback_submit);
		mSubmit.setOnClickListener(this);
	}
	

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.orderback_line101:
			case_id = 1;
			mImage101.setImageResource(R.drawable.gouxuan_true);
			mImage102.setImageResource(R.drawable.gouxuan_false);
			break;
		case R.id.orderback_line102:
			case_id = 2;
			mImage101.setImageResource(R.drawable.gouxuan_false);
			mImage102.setImageResource(R.drawable.gouxuan_true);
			break;
		case R.id.orderback_line201:
			pay_id = 1;
			mImage201.setImageResource(R.drawable.gouxuan_true);
			mImage202.setImageResource(R.drawable.gouxuan_false);
			mBankPlace.setVisibility(View.GONE);
			break;
		case R.id.orderback_line202:
			pay_id = 2;
			mImage201.setImageResource(R.drawable.gouxuan_false);
			mImage202.setImageResource(R.drawable.gouxuan_true);
			mBankPlace.setVisibility(View.VISIBLE);
			break;
		case R.id.orderback_submit:
			if (goods_id.equals("")) {
				Toast.ToastMe(OrderBackActivity.this, "请选择退换商品");
			}else if (case_id == 0) {
				Toast.ToastMe(OrderBackActivity.this, "请选择退换类型");
			}else if (mReason.getText().toString().trim().equals("")) {
				Toast.ToastMe(OrderBackActivity.this, "请填写退换原因");
			}else if (mMobile.getText().toString().trim().equals("")) {
				Toast.ToastMe(OrderBackActivity.this, "请填写系电话");
			}else if (pay_id == 0) {
				Toast.ToastMe(OrderBackActivity.this, "请选择退换账户");
			}else if (mBankNumber.getText().toString().trim().equals("")) {
				Toast.ToastMe(OrderBackActivity.this, "请填写退换账户");
			}else {
				if (pay_id == 1) {
					PublicRequest http = new PublicRequest(mHandler);
					http.OrderBackAdd(OrderBackActivity.this, mUserInfo.GetUserInfo(OrderBackActivity.this).getUser().getUser_id(), order_id, goods_id, case_id+"", mReason.getText().toString().trim(), mMobile.getText().toString().trim(), pay_id+"", mBankNumber.getText().toString().trim(), mBankPlace.getText().toString().trim());
				}else if (pay_id == 2) {
					if (mBankPlace.getText().toString().trim().equals("")) {
						Toast.ToastMe(OrderBackActivity.this, "请填写开户行");
					}else {
						PublicRequest http = new PublicRequest(mHandler);
						http.OrderBackAdd(OrderBackActivity.this, mUserInfo.GetUserInfo(OrderBackActivity.this).getUser().getUser_id(), order_id, goods_id, case_id+"", mReason.getText().toString().trim(), mMobile.getText().toString().trim(), pay_id+"", mBankNumber.getText().toString().trim(), mBankPlace.getText().toString().trim());
					}
				}else {
					Toast.ToastMe(OrderBackActivity.this, "请选择退换类型");
				}
			}
			break;
		}
	}


	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		String id = mList.get(arg2).getGoods_id();
		if (goodsid_List.size() >0) {
			for (int i = 0; i < goodsid_List.size(); i++) {
				if (goodsid_List.get(i).equals(id)) {
					goodsid_List.remove(i);
					mList.get(arg2).setCheck(false);
				}else {
					goodsid_List.add(id);
					mList.get(arg2).setCheck(true);
				}
			}
		}else {
			goodsid_List.add(id);
			mList.get(arg2).setCheck(true);
		}
		
		if (goodsid_List.size()>0) {
			for (int i = 0; i < goodsid_List.size(); i++) {
				goods_id = goods_id+goodsid_List.get(i)+",";
			}
			goods_id = goods_id.substring(0, goods_id.length()-1);
		}else {
			goods_id = "";
		}
		
		mAdapter.notifyDataSetChanged();
	}


	Handler mHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			try {
				switch (msg.what) {
				case Constants.ORDER_BACK:
					JSONObject jsonObj = new JSONObject(msg.obj.toString());
					if (jsonObj.getString("code").equals("1")) {
						Toast.ToastMe(OrderBackActivity.this,jsonObj.getString("message"));
						finish();
					} else {
						Toast.ToastMe(OrderBackActivity.this,jsonObj.getString("message"));
					}
					break;
				}
			}catch (JSONException e) {
				e.printStackTrace();
			}
		};
	};

}
