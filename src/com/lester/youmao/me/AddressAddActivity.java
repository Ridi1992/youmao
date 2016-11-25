package com.lester.youmao.me;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import com.bset.tool.Texttool;
import com.bset.tool.Toast;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.lester.youmao.LoginActivity;
import com.lester.youmao.MainActivity;
import com.lester.youmao.R;
import com.lester.youmao.adapter.NoticeAdapter;
import com.lester.youmao.adapter.RegionAdapter;
import com.lester.youmao.entity.Notice;
import com.lester.youmao.entity.Regions;
import com.lester.youmao.userinfo.UserInfo;
import com.lester.youmao.userinfo.mUserInfo;
import com.sanmi.http.Constants;
import com.sanmi.http.JsonUtil;
import com.sanmi.http.PublicRequest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class AddressAddActivity extends Activity{
	
	private TextView mTitle;
	private ImageView mBack;
	
	private Spinner mSpinner;
	private EditText mAddressDetail;
	private EditText mAddressName;
	private EditText mAddressTel;
	private Button mAddressSave;
	private ImageView mDelete;
	
	private ArrayList<Regions> list_district_name = new ArrayList<Regions>();
	private RegionAdapter mAdapter;
	private String district_id;
	
	private String address_id;
	private String district_name;
	private String name;
	private String address;
	private String mobile;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.me_address_add);
		
		if (getIntent().getIntExtra("type", 0) == 2) {
			address_id = getIntent().getStringExtra("id");
			district_name = getIntent().getStringExtra("district");
			name = getIntent().getStringExtra("name");
			address = getIntent().getStringExtra("address");
			mobile = getIntent().getStringExtra("mobile");
		}else if (getIntent().getIntExtra("type", 0) == 1) {
			
		}
		
		PublicRequest http = new PublicRequest(mHandler);
		http.Region(AddressAddActivity.this, "53");
		
		initView();
	}

	private void initView() {
		mTitle = (TextView) findViewById(R.id.top_title);
		mBack = (ImageView) findViewById(R.id.top_back);
		mBack.setImageResource(R.drawable.back);
		mBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		mSpinner = (Spinner) findViewById(R.id.address_district_name);
		mSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				district_id = list_district_name.get(arg2).getId();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
		mAddressDetail = (EditText) findViewById(R.id.address_detail);
		mAddressName = (EditText) findViewById(R.id.address_name);
		mAddressTel = (EditText) findViewById(R.id.address_tel);
		if (getIntent().getIntExtra("type", 0) == 2) {
			Texttool.setText(mTitle, "修改收货地址");
			Texttool.setText(mAddressName,name);
			Texttool.setText(mAddressDetail, address);
			Texttool.setText(mAddressTel, mobile);
		}else if (getIntent().getIntExtra("type", 0) == 1) {
			Texttool.setText(mTitle, "添加收货地址");
		}
		mAddressSave = (Button) findViewById(R.id.address_save);
		mAddressSave.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (Add()) {
					if (getIntent().getIntExtra("type", 0) == 2) {
						PublicRequest http = new PublicRequest(mHandler);
						http.AddressUpdate(AddressAddActivity.this, mUserInfo.GetUserInfo(AddressAddActivity.this).getSession().getSid(), mUserInfo.GetUserInfo(AddressAddActivity.this).getSession().getUid(), address_id, Texttool.Gettext(mAddressDetail), Texttool.Gettext(mAddressName), Texttool.Gettext(mAddressTel),district_id);
					}else if (getIntent().getIntExtra("type", 0) == 1) {
						PublicRequest http = new PublicRequest(mHandler);
						http.AddressAdd(AddressAddActivity.this, mUserInfo.GetUserInfo(AddressAddActivity.this).getSession().getSid(), mUserInfo.GetUserInfo(AddressAddActivity.this).getSession().getUid(), Texttool.Gettext(mAddressDetail), Texttool.Gettext(mAddressName), Texttool.Gettext(mAddressTel),district_id);
					}
				}
			}
		});
		mDelete = (ImageView) findViewById(R.id.address_delete);
		if (getIntent().getIntExtra("type", 0) == 2) {
			mDelete.setVisibility(View.VISIBLE);
		}else if (getIntent().getIntExtra("type", 0) == 1) {
			mDelete.setVisibility(View.GONE);
		}
		mDelete.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				PublicRequest http = new PublicRequest(mHandler);
				http.AddressDelete(AddressAddActivity.this, mUserInfo.GetUserInfo(AddressAddActivity.this).getSession().getSid(), mUserInfo.GetUserInfo(AddressAddActivity.this).getSession().getUid(), address_id);
			}
		});
	}
	
	private boolean Add() {
		if (!Texttool.Havecontent(mAddressDetail)) {
			Toast.ToastMe(AddressAddActivity.this, "请完善详细地址");
			return false;
		}else if (!Texttool.Havecontent(mAddressName)) {
			Toast.ToastMe(AddressAddActivity.this, "请完善收货人姓名");
			return false;
		}else if (!Texttool.Havecontent(mAddressTel)) {
			Toast.ToastMe(AddressAddActivity.this, "请完善收货人电话");
			return false;
		}else {
			return true;
		}
	}
	
	Handler mHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			try {
				switch (msg.what) {
				case Constants.ADDRESS_ADD:
					JSONObject jsonObj = new JSONObject(msg.obj.toString());
					if(jsonObj.getJSONObject("status").getString("succeed").equals("1")) {
						Toast.ToastMe(AddressAddActivity.this, "添加地址成功");
						finish();
					}else {
						Toast.ToastMe(AddressAddActivity.this, jsonObj.getJSONObject("status").getString("error_desc"));
					}
					break;
				case Constants.ADDRESS_UPDATE:
					JSONObject jsonObj1 = new JSONObject(msg.obj.toString());
					if(jsonObj1.getJSONObject("status").getString("succeed").equals("1")) {
						Toast.ToastMe(AddressAddActivity.this, "修改地址成功");
					}else {
						Toast.ToastMe(AddressAddActivity.this, jsonObj1.getJSONObject("status").getString("error_desc"));
					}
					break;
				case Constants.ADDRESS_DELETE:
					JSONObject jsonObj11 = new JSONObject(msg.obj.toString());
					if(jsonObj11.getJSONObject("status").getString("succeed").equals("1")) {
						Toast.ToastMe(AddressAddActivity.this, "删除地址成功");
						finish();
					}else {
						Toast.ToastMe(AddressAddActivity.this, jsonObj11.getJSONObject("status").getString("error_desc"));
					}
					break;
				case Constants.REGION:
					JSONObject jsonObjRegion = new JSONObject(msg.obj.toString());
					if(jsonObjRegion.getJSONObject("status").getString("succeed").equals("1")) {
						String jsonData = jsonObjRegion.getJSONObject("data").getString("regions");
						list_district_name = JsonUtil.instance().fromJson(jsonData,new TypeToken<ArrayList<Regions>>() {}.getType());
						if (list_district_name != null) {
							if (getIntent().getIntExtra("type", 0) == 2) {
								for (int i = 0; i < list_district_name.size(); i++) {
									Regions regions = list_district_name.get(i);
									if (regions.getName().equals(district_name)) {
										district_id = regions.getId();
										list_district_name.remove(i);
										list_district_name.add(0, regions);
									}
								}
								mAdapter = new RegionAdapter(AddressAddActivity.this, list_district_name);
								mSpinner.setAdapter(mAdapter);
							}else if (getIntent().getIntExtra("type", 0) == 1) {
								mAdapter = new RegionAdapter(AddressAddActivity.this, list_district_name);
								mSpinner.setAdapter(mAdapter);
								district_id = list_district_name.get(0).getId();
							}
						}
					}else {
						Toast.ToastMe(AddressAddActivity.this, jsonObjRegion.getJSONObject("status").getString("error_desc"));
					}
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
