package com.lester.youmao;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import com.bset.tool.Texttool;
import com.bset.tool.Toast;
import com.google.gson.reflect.TypeToken;
import com.lester.youmao.adapter.ShopListAdapter;
import com.lester.youmao.entity.ShopCart;
import com.lester.youmao.entity.ShopGoods;
import com.lester.youmao.shopcart.CheckOrderActivity;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ShopCartActivity extends Activity{
	
	private TextView mTitle;
	private ImageView mBack;
	
	private ListView mListView;
	private ArrayList<ShopGoods> mList;
	private ShopListAdapter mAdapter;
	private TextView mTotalFee;
	private Button mSubmit;

	private View view;
	private LodingDialog dialog;
	private ShopCart shopList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shopcart);
		
		if (mUserInfo.GetUserInfo(ShopCartActivity.this)!= null) {
			dialog=LodingDialog.DialogFactor(ShopCartActivity.this, "正在加载", false);
			PublicRequest http = new PublicRequest(mHandler);
			http.CartList(ShopCartActivity.this, mUserInfo.GetUserInfo(ShopCartActivity.this).getSession().getSid(), mUserInfo.GetUserInfo(ShopCartActivity.this).getSession().getUid());
		}else {
			startActivity(new Intent(ShopCartActivity.this, LoginActivity.class));
		}
		
		initViews();
		
	}
	
	private void initViews() {
		mTitle = (TextView) findViewById(R.id.top_title);
		mTitle.setText("购物车");
		mBack = (ImageView) findViewById(R.id.top_back);
		mBack.setImageResource(R.drawable.back);
		mBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		mTotalFee = (TextView) findViewById(R.id.shopcart_fee);
		mSubmit = (Button) findViewById(R.id.shopcart_submit);
		mSubmit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
//				MainActivity.mRadioGroup.check(R.id.rb_main_01);
//				Fragment_home home = new Fragment_home();
//		        FragmentManager fm = getFragmentManager();
//		        FragmentTransaction ft = fm.beginTransaction();
//		        ft.replace(R.id.layout, home);
//		        ft.commit();
				if (mUserInfo.GetUserInfo(ShopCartActivity.this)!= null) {
					Intent intent = new Intent();
					intent.putExtra("flow_type", 0);
					intent.putExtra("rec_type", "0");
					intent.putExtra("totalfee", shopList.getTotal().getGoods_amount());
					startActivity(intent.setClass(ShopCartActivity.this, CheckOrderActivity.class));
				}else {
					startActivity(new Intent(ShopCartActivity.this, LoginActivity.class));
				}
			}
		});
		mListView = (ListView) findViewById(R.id.shopcart_lv);
	}
	
	Handler mHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			try {
				if(dialog!=null){
					dialog.dismiss();
				}
				switch (msg.what) {
				case Constants.CART_LIST:
					JSONObject jsonObj = new JSONObject(msg.obj.toString());
					if (jsonObj.getJSONObject("status").getString("succeed").equals("1")) {
						String jsonData = jsonObj.getString("data");
						shopList = JsonUtil.instance().fromJson(jsonData,new TypeToken<ShopCart>() {}.getType());
						if (shopList != null) {
							Texttool.setText(mTotalFee, shopList.getTotal().getGoods_price());
							if (shopList.getGoods_list().size() > 0) {
								mList = shopList.getGoods_list();
								mAdapter = new ShopListAdapter(ShopCartActivity.this, mList,mHandler);
								mListView.setAdapter(mAdapter);
							}else if (shopList.getGoods_list().size() == 0) {
								mList = new ArrayList<ShopGoods>();
								mAdapter = new ShopListAdapter(ShopCartActivity.this, mList,mHandler);
								mListView.setAdapter(mAdapter);
							}
						}
					} else {
						Intent intent = new Intent();
						intent.setClass(ShopCartActivity.this, LoginActivity.class);
						startActivity(intent);
						Toast.ToastMe(ShopCartActivity.this.getApplication(),jsonObj.getJSONObject("status").getString("error_desc"));
					}
					break;
				case Constants.CART_DELETE:
					JSONObject jsonObj1 = new JSONObject(msg.obj.toString());
					if (jsonObj1.getJSONObject("status").getString("succeed").equals("1")) {
						Toast.ToastMe(ShopCartActivity.this,"删除成功");
						PublicRequest http = new PublicRequest(mHandler);
						http.CartList(ShopCartActivity.this, mUserInfo.GetUserInfo(ShopCartActivity.this).getSession().getSid(), mUserInfo.GetUserInfo(ShopCartActivity.this).getSession().getUid());
					} else {
						Toast.ToastMe(ShopCartActivity.this,jsonObj1.getJSONObject("status").getString("error_desc"));
					}
					break;
				case Constants.CART_UPDATE:
					JSONObject jsonObj11 = new JSONObject(msg.obj.toString());
					if (jsonObj11.getJSONObject("status").getString("succeed").equals("1")) {
						Toast.ToastMe(ShopCartActivity.this,"修改成功");
						PublicRequest http = new PublicRequest(mHandler);
						http.CartList(ShopCartActivity.this, mUserInfo.GetUserInfo(ShopCartActivity.this).getSession().getSid(), mUserInfo.GetUserInfo(ShopCartActivity.this).getSession().getUid());
					} else {
						Toast.ToastMe(ShopCartActivity.this,jsonObj11.getJSONObject("status").getString("error_desc"));
					}
					break;
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		};
	};

}
