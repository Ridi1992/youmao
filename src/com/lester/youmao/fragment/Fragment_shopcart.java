package com.lester.youmao.fragment;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import com.bset.tool.Texttool;
import com.bset.tool.Toast;
import com.google.gson.reflect.TypeToken;
import com.lester.youmao.LodingDialog;
import com.lester.youmao.LoginActivity;
import com.lester.youmao.MainActivity;
import com.lester.youmao.R;
import com.lester.youmao.adapter.ShopListAdapter;
import com.lester.youmao.entity.Banner;
import com.lester.youmao.entity.CateGory;
import com.lester.youmao.entity.HomePromote;
import com.lester.youmao.entity.ShopGoods;
import com.lester.youmao.entity.ShopCart;
import com.lester.youmao.home.CategoryActivity;
import com.lester.youmao.shopcart.CheckOrderActivity;
import com.lester.youmao.userinfo.mUserInfo;
import com.sanmi.http.Constants;
import com.sanmi.http.JsonUtil;
import com.sanmi.http.PublicRequest;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Fragment_shopcart extends Fragment{

	private TextView mTitle;
	
	private LinearLayout mLinearLayout;
	private RelativeLayout mRelativeLayout;
	private TextView mGuangGuang;
	
	private ListView mListView;
	private ArrayList<ShopGoods> mList = new ArrayList<ShopGoods>();
	private ShopListAdapter mAdapter;
	private TextView mTotalFee;
	private Button mSubmit;

	private View view;
	private LodingDialog dialog;
	private boolean isFirst = true;
	private ShopCart shopList;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);

		view = inflater.inflate(R.layout.fragment_shopcart, container, false);
		
		isFirst = true;
		initViews(view);

		return view;
	} 
	 
	@Override
	public void onStart() {
		super.onStart();
		if (mUserInfo.GetUserInfo(getActivity())!= null) {
			dialog=LodingDialog.DialogFactor(getActivity(), "正在加载", false);
			mRelativeLayout.setVisibility(View.GONE);
			mLinearLayout.setVisibility(View.GONE);
			PublicRequest http = new PublicRequest(mHandler);
			http.CartList(getActivity().getApplicationContext(), mUserInfo.GetUserInfo(getActivity()).getSession().getSid(), mUserInfo.GetUserInfo(getActivity()).getSession().getUid());
		}else {
			
			 if (isFirst) {
				startActivity(new Intent(getActivity(), LoginActivity.class));
				mRelativeLayout.setVisibility(View.VISIBLE);
				mLinearLayout.setVisibility(View.GONE);
				isFirst = false;
			}
		}	
	}
	
	@Override
	public void onStop() {
		super.onStop();
		if(dialog!=null){
			dialog.dismiss();
		}
	}

	private void initViews(View v) {
		mTitle = (TextView) v.findViewById(R.id.top_title);
		mTitle.setText("购物车");
		
		mLinearLayout = (LinearLayout) v.findViewById(R.id.shopcart_layout);
		mRelativeLayout = (RelativeLayout) v.findViewById(R.id.me_shopcart_none);
		mGuangGuang = (TextView) v.findViewById(R.id.me_shopcart_none_tv);
		mGuangGuang.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				MainActivity.mRadioGroup.check(R.id.rb_main_01);
				Fragment_home home = new Fragment_home();
		        FragmentManager fm = getFragmentManager();
		        FragmentTransaction ft = fm.beginTransaction();
		        ft.replace(R.id.layout, home);
		        ft.commit();
			}
		});
		
		mTotalFee = (TextView) v.findViewById(R.id.shopcart_fee);
		mSubmit = (Button) v.findViewById(R.id.shopcart_submit);
		mSubmit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
//				MainActivity.mRadioGroup.check(R.id.rb_main_01);
//				Fragment_home home = new Fragment_home();
//		        FragmentManager fm = getFragmentManager();
//		        FragmentTransaction ft = fm.beginTransaction();
//		        ft.replace(R.id.layout, home);
//		        ft.commit();
				if (mUserInfo.GetUserInfo(getActivity())!= null) {
					Intent intent = new Intent();
					intent.putExtra("flow_type", 0);
					intent.putExtra("rec_type", "0");
					intent.putExtra("totalfee", shopList.getTotal().getGoods_amount());
					startActivity(intent.setClass(getActivity(), CheckOrderActivity.class));
				}else {
					startActivity(new Intent(getActivity(), LoginActivity.class));
				}
			}
		});
		mListView = (ListView) v.findViewById(R.id.shopcart_lv);
		mAdapter = new ShopListAdapter(getActivity(), mList,mHandler);
		mListView.setAdapter(mAdapter);
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
								mRelativeLayout.setVisibility(View.GONE);
								mLinearLayout.setVisibility(View.VISIBLE);
								mList = shopList.getGoods_list();
								mAdapter = new ShopListAdapter(getActivity(), mList,mHandler);
								mListView.setAdapter(mAdapter);
							}else if (shopList.getGoods_list().size() == 0) {
								mRelativeLayout.setVisibility(View.VISIBLE);
								mLinearLayout.setVisibility(View.GONE);
								mList.clear();
								mAdapter.notifyDataSetChanged();
							}
						}
					} else {
						if (isFirst) {
							Intent intent = new Intent();
							intent.setClass(getActivity(), LoginActivity.class);
							startActivity(intent);
							Toast.ToastMe(getActivity().getApplication(),jsonObj.getJSONObject("status").getString("error_desc"));
							isFirst = false;
						}
					}
					break;
				case Constants.CART_DELETE:
					JSONObject jsonObj1 = new JSONObject(msg.obj.toString());
					if (jsonObj1.getJSONObject("status").getString("succeed").equals("1")) {
						Toast.ToastMe(getActivity(),"删除成功");
						PublicRequest http = new PublicRequest(mHandler);
						http.CartList(getActivity(), mUserInfo.GetUserInfo(getActivity()).getSession().getSid(), mUserInfo.GetUserInfo(getActivity()).getSession().getUid());
					} else {
						Toast.ToastMe(getActivity(),jsonObj1.getJSONObject("status").getString("error_desc"));
					}
					break;
				case Constants.CART_UPDATE:
					JSONObject jsonObj11 = new JSONObject(msg.obj.toString());
					if (jsonObj11.getJSONObject("status").getString("succeed").equals("1")) {
						Toast.ToastMe(getActivity(),"修改成功");
						PublicRequest http = new PublicRequest(mHandler);
						http.CartList(getActivity(), mUserInfo.GetUserInfo(getActivity()).getSession().getSid(), mUserInfo.GetUserInfo(getActivity()).getSession().getUid());
					} else {
						Toast.ToastMe(getActivity(),jsonObj11.getJSONObject("status").getString("error_desc"));
					}
					break;
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		};
	};
	
}
