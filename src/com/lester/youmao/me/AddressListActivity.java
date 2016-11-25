package com.lester.youmao.me;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import com.bset.tool.Toast;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.lester.youmao.LoginActivity;
import com.lester.youmao.R;
import com.lester.youmao.adapter.AddressAdapter;
import com.lester.youmao.adapter.PointsAdapter;
import com.lester.youmao.entity.Address;
import com.lester.youmao.entity.Points;
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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class AddressListActivity extends Activity{
	
	private TextView mTitle;
	private ImageView mBack;
	
	private ListView mListView;
	private ArrayList<Address> mList;
	private AddressAdapter mAdapter;
	private Button mAddressAdd;
	
	private String action = "";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.me_address_list);
		
		if (getIntent().getAction() == null) {
			action = "";
		}else {
			action = getIntent().getAction();
		}
		
		initView();
	}

	private void initView() {
		mTitle = (TextView) findViewById(R.id.top_title);
		mTitle.setText("收货地址");
		mBack = (ImageView) findViewById(R.id.top_back);
		mBack.setImageResource(R.drawable.back);
		mBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		mListView = (ListView) findViewById(R.id.adderss_lv);
		mListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
				if (action != null && action.equals("set_address")) {
					PublicRequest http = new PublicRequest(mHandler);
					http.AddressDefault(AddressListActivity.this, mUserInfo.GetUserInfo(AddressListActivity.this).getSession().getSid(), mUserInfo.GetUserInfo(AddressListActivity.this).getSession().getSid(), mList.get(arg2).getId());
					Intent data = new Intent();
					data.putExtra("province", mList.get(arg2).getProvince_name());
					data.putExtra("city", mList.get(arg2).getCity_name());
					data.putExtra("district", mList.get(arg2).getDistrict_name());
					data.putExtra("name", mList.get(arg2).getConsignee());
					data.putExtra("address", mList.get(arg2).getAddress());
					setResult(RESULT_OK, data);
					finish();
				}else {
					Intent intent = new Intent();
					intent.putExtra("type", 2);
					intent.putExtra("id", mList.get(arg2).getId());
					intent.putExtra("district", mList.get(arg2).getDistrict_name());
					intent.putExtra("name", mList.get(arg2).getConsignee());
					intent.putExtra("address", mList.get(arg2).getAddress());
					intent.putExtra("mobile", mList.get(arg2).getMobile());
					intent.setClass(AddressListActivity.this, AddressAddActivity.class);
					startActivity(intent);
				}
			}
		});
		mAddressAdd = (Button) findViewById(R.id.adderss_add);
		mAddressAdd.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("type", 1);
				startActivity(intent.setClass(AddressListActivity.this, AddressAddActivity.class));
			}
		});
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		if (mUserInfo.GetUserInfo(AddressListActivity.this) != null) {
			PublicRequest http = new PublicRequest(mHandler);
			http.AddressList(AddressListActivity.this, mUserInfo.GetUserInfo(AddressListActivity.this).getSession().getSid(), mUserInfo.GetUserInfo(AddressListActivity.this).getSession().getUid());
		}else {
			startActivity(new Intent(AddressListActivity.this, LoginActivity.class));
		}
		
	}
	
	Handler mHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			try {
				switch (msg.what) {
				case Constants.ADDRESS_LIST:
					JSONObject jsonObj = new JSONObject(msg.obj.toString());
					if (jsonObj.getJSONObject("status").getString("succeed").equals("1")) {
						String jsonData = jsonObj.getString("data");
						mList = JsonUtil.instance().fromJson(jsonData,new TypeToken<ArrayList<Address>>() {}.getType());
						if (mList != null) {
							for (int i = 0; i < mList.size(); i++) {
								if (mList.get(i).getDefault_address() == 1) {
									mList.get(i).setDefault_id(true);
								}
							}
							mAdapter = new AddressAdapter(AddressListActivity.this, mList, mHandler,action);
							mListView.setAdapter(mAdapter);
						}
					} else {
						Toast.ToastMe(AddressListActivity.this, jsonObj.getJSONObject("status").getString("error_desc"));
					}
					break;
				case Constants.ADDRESS_DEFAULT:
					JSONObject jsonObj1 = new JSONObject(msg.obj.toString());
					if(jsonObj1.getJSONObject("status").getString("succeed").equals("1")) {
						PublicRequest http = new PublicRequest(mHandler);
						http.AddressList(AddressListActivity.this, mUserInfo.GetUserInfo(AddressListActivity.this).getSession().getSid(), mUserInfo.GetUserInfo(AddressListActivity.this).getSession().getUid());
						Toast.ToastMe(AddressListActivity.this, "设置地址成功");
					}else {
						Toast.ToastMe(AddressListActivity.this, jsonObj1.getJSONObject("status").getString("error_desc"));
					}
					break;
				case 404:
					Toast.ToastMe(AddressListActivity.this, msg.obj.toString());
					break;
				}
			} catch (JsonSyntaxException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			}
		};
	};

}
