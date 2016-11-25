package com.lester.youmao.shopcart;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import com.bset.tool.ListUtil;
import com.bset.tool.Texttool;
import com.bset.tool.Toast;
import com.google.gson.reflect.TypeToken;
import com.lester.youmao.MyApplication;
import com.lester.youmao.MyApplication.myLocation;
import com.lester.youmao.R;
import com.lester.youmao.adapter.CheckCouponAdapter;
import com.lester.youmao.adapter.CheckDoorAdapter;
import com.lester.youmao.adapter.CheckGoodsAdapter;
import com.lester.youmao.adapter.CheckOrderPayAdapter;
import com.lester.youmao.adapter.CheckOrderShippingAdapter;
import com.lester.youmao.entity.CheckOrder;
import com.lester.youmao.entity.Coupon;
import com.lester.youmao.entity.DoorShop;
import com.lester.youmao.entity.FlowDone;
import com.lester.youmao.entity.GoodsDetail;
import com.lester.youmao.entity.OrderList;
import com.lester.youmao.entity.PaymentList;
import com.lester.youmao.entity.ShippingList;
import com.lester.youmao.me.AddressListActivity;
import com.lester.youmao.userinfo.mUserInfo;
import com.sanmi.http.Constants;
import com.sanmi.http.JsonUtil;
import com.sanmi.http.PublicRequest;
import com.weixin.paydemo.PayActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class CheckOrderActivity extends Activity implements OnClickListener, OnItemClickListener{
	
	private TextView mTitle;
	private ImageView mBack;
	
	private TextView mAddressName;
	private TextView mAddressDetail;
	private LinearLayout mAddressMore;
	
	private ListView mGoodsListView;
	private LinearLayout mCouponLine;
	private ListView mCouponListView;
	private LinearLayout mShipping;
	private TextView mShippingName;
	private View DoorshopView;
	private LinearLayout mDoorshopLine;
	private TextView mDoorshopName;
	private TextView mDoorshopContent;
	private TextView mDoorshopDistance;
	private ListView mDoorshopListView;
	private LinearLayout mPaymentLine;
	private LinearLayout mPayment;
	private TextView mPaymentName;
	private ArrayList<OrderList> mGoodsList = new ArrayList<OrderList>();
	private CheckGoodsAdapter mGoodsAdapter;
	private ArrayList<Coupon> mCouponList = new ArrayList<Coupon>();
	private CheckCouponAdapter mCouponAdapter;
	private ArrayList<ShippingList> mShippingList = new ArrayList<ShippingList>();
	private ArrayList<DoorShop> mDoorshopList = new ArrayList<DoorShop>();
	private CheckDoorAdapter mDoorAdapter;
	private ArrayList<PaymentList> mPaymentList = new ArrayList<PaymentList>();
	
	private TextView mAllPrice;
	private Button mFlowDone;
	
	private double mGoodsPrice = 0.00;
	private double mCouponPrice = 0.00;
	private double mShippingPrice = 0.00;
	private String mCouponPrice_id = "";
	private String mShippingPrice_id = "";
	private String mPaymentPrice_id = "";
	private String mDoorShop_id = "";
	
	private CheckOrder checkOrder;
	private int dialogShow;
	private int couponType = 0;//0减价格  1打折
	private int flow_type = 0;
	private String rectype = "";
	
	private boolean isFast = true;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order_checkorder);
		
		flow_type = getIntent().getIntExtra("flow_type", 0);
//		Toast.ToastMe(this, flow_type+"=====a");
		rectype = getIntent().getStringExtra("rec_type");
		
		initView();
	}
	
	@Override
	protected void onStart() {
		super.onStart();
			((MyApplication)getApplication()).getmyLocation(new myLocation() {
				@Override
				public void result(double nlatitude, double nlontitude, boolean success) {
					if (success) {
						//TODO
					}else {
						Toast.ToastMe(CheckOrderActivity.this, "定位失败，上门取货为默认门店");
					}
					mGoodsPrice = 0.00;
					mCouponPrice = 0.00;
					mShippingPrice = 0.00;
					PublicRequest http = new PublicRequest(mHandler);
					http.CheckOrder(CheckOrderActivity.this, mUserInfo.GetUserInfo(CheckOrderActivity.this).getSession().getSid(), mUserInfo.GetUserInfo(CheckOrderActivity.this).getSession().getUid(), nlontitude+"", nlatitude+"",flow_type+"",rectype);
//					Toast.ToastMe(CheckOrderActivity.this, "aaa"+nlatitude+"aaa"+nlontitude);
				}
			});
	}	
	
	private void initView() {
		mTitle = (TextView) findViewById(R.id.top_title);
		mTitle.setText("检查订单");
		mBack = (ImageView) findViewById(R.id.top_back);
		mBack.setImageResource(R.drawable.back);
		mBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		mAddressName = (TextView) findViewById(R.id.checkorder_address_name);
		mAddressDetail = (TextView) findViewById(R.id.checkorder_address_address);
		mAddressMore = (LinearLayout) findViewById(R.id.checkorder_moreaddress);
		mAddressMore.setOnClickListener(this);
		mGoodsListView = (ListView) findViewById(R.id.checkorder_goods_lv);
		mCouponLine = (LinearLayout) findViewById(R.id.checkorder_coupon_line);
		mCouponListView = (ListView) findViewById(R.id.checkorder_coupon_lv);
		mCouponListView.setOnItemClickListener(this);
		mPaymentLine = (LinearLayout) findViewById(R.id.checkorder_payment_line);
		if (flow_type == 4) {
			mPaymentLine.setVisibility(View.GONE);
		}
		mShipping = (LinearLayout) findViewById(R.id.checkorder_shipping);
		mShipping.setOnClickListener(this);
		mShippingName = (TextView) findViewById(R.id.checkorder_shipping_name);
		DoorshopView = findViewById(R.id.checkorder_view);
		mDoorshopLine = (LinearLayout) findViewById(R.id.checkorder_doorshop_line);
		mDoorshopLine.setOnClickListener(this);
		mDoorshopName = (TextView) findViewById(R.id.item_checkdoor_name);
		mDoorshopContent = (TextView) findViewById(R.id.item_checkdoor_content);
		mDoorshopDistance = (TextView) findViewById(R.id.item_checkdoor_distance);
		mDoorshopListView = (ListView) findViewById(R.id.checkorder_doorshop_lv);
		mDoorshopListView.setOnItemClickListener(this);
		mPayment = (LinearLayout) findViewById(R.id.checkorder_payment);
		mPayment.setOnClickListener(this);
		mPaymentName = (TextView) findViewById(R.id.checkorder_payment_name);
		mAllPrice = (TextView) findViewById(R.id.checkorder_allprice);
		mFlowDone = (Button) findViewById(R.id.checkorder_flowdown);
		mFlowDone.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.checkorder_shipping:
			dialogShow = 0;
			DialogShow();
			break;
		case R.id.checkorder_payment:
			dialogShow = 1;
			DialogShow();
			break;
		case R.id.checkorder_moreaddress:
			Intent intent = new Intent();
			intent.setAction("set_address");
			intent.setClass(CheckOrderActivity.this, AddressListActivity.class);
			startActivityForResult(intent, 1001);
			break;
		case R.id.checkorder_doorshop_line:
			mDoorshopLine.setVisibility(View.GONE);
			mDoorshopListView.setVisibility(View.VISIBLE);
			break;
		case R.id.checkorder_flowdown:
			if (mShippingPrice_id.equals("")) {
				Toast.ToastMe(CheckOrderActivity.this, "请选择配送方式");
			}else if (mPaymentPrice_id.equals("") && flow_type != 4) {
				Toast.ToastMe(CheckOrderActivity.this, "请选择支付方式");
			}else {
				PublicRequest http = new PublicRequest(mHandler);
				http.FlowDone(CheckOrderActivity.this, mUserInfo.GetUserInfo(CheckOrderActivity.this).getSession().getSid(), mUserInfo.GetUserInfo(CheckOrderActivity.this).getSession().getUid(), mPaymentPrice_id, mShippingPrice_id, mCouponPrice_id, mDoorShop_id,flow_type+"",rectype);
			}
			break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		switch (arg0.getId()) {
		case R.id.checkorder_coupon_lv:
			if (mCouponPrice_id.equals(mCouponList.get(arg2).getCoupons_id())) {
				for(int i=0;i<mCouponList.size();i++){
					mCouponList.get(i).setCheck(false);
				}
				mCouponAdapter.notifyDataSetChanged();
				mCouponPrice_id = "";
				couponType = 0;
				mCouponPrice = 0.00;
			}else {
				for(int i=0;i<mCouponList.size();i++){
					mCouponList.get(i).setCheck(false);
				}
				mCouponList.get(arg2).setCheck(true);
				mCouponAdapter.notifyDataSetChanged();
				mCouponPrice_id = mCouponList.get(arg2).getCoupons_id();
				if (mCouponList.get(arg2).getType_id().equals("1")) {
					couponType = 0;
					mCouponPrice = Double.parseDouble(mCouponList.get(arg2).getType_money());
				}else if (mCouponList.get(arg2).getType_id().equals("2")) {
					couponType = 0;
					mCouponPrice = Double.parseDouble(mCouponList.get(arg2).getType_money());
				}else if (mCouponList.get(arg2).getType_id().equals("3")) {
					couponType = 1;
					mCouponPrice = mCouponList.get(arg2).getCoupons_discount();
				}
			}
			if (flow_type == 0) {
				GetPrice(mGoodsPrice, mCouponPrice, mShippingPrice);
			}else if (flow_type == 4) {
				
			}
			break;
		case R.id.checkorder_doorshop_lv:
			for(int i=0;i<mDoorshopList.size();i++){
				mDoorshopList.get(i).setCheck(false);
			}
			mDoorshopList.get(arg2).setCheck(true);
			mDoorAdapter.notifyDataSetChanged();
			mDoorShop_id = mDoorshopList.get(arg2).getShop_id();
//			Toast.ToastMe(this, mDoorShop_id+"===");
			Texttool.setText(mDoorshopName, mDoorshopList.get(arg2).getShop_name());
			Texttool.setText(mDoorshopContent, mDoorshopList.get(arg2).getShop_address());
			Texttool.setText(mDoorshopDistance, "距当前位置 "+mDoorshopList.get(0).getDistance()+"km");
			mDoorshopLine.setVisibility(View.VISIBLE);
			mDoorshopListView.setVisibility(View.GONE);
			break;
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			switch (requestCode) {
			case 1001:
				Texttool.setText(mAddressName, "收货人："+data.getStringExtra("name"));
				Texttool.setText(mAddressDetail, "收货地址："+data.getStringExtra("province")+data.getStringExtra("city")+data.getStringExtra("district")+data.getStringExtra("address"));
				break;
			}
		}
	}

	private void DialogShow() {
		final Dialog lodingDialog = new Dialog(CheckOrderActivity.this,R.style.MyDialog);
		View  v = LayoutInflater.from(CheckOrderActivity.this).inflate(R.layout.dialog_checkorder, null);
		lodingDialog.setContentView(v);
		lodingDialog.show();
		
		GridView gridView = (GridView) v.findViewById(R.id.order_pay);
		switch (dialogShow) {//0配送方式  1 支付方式
		case 0:
			CheckOrderShippingAdapter adapter = new CheckOrderShippingAdapter(this, mShippingList);
			gridView.setAdapter(adapter);
			break;
		case 1:
			CheckOrderPayAdapter adapter2 = new CheckOrderPayAdapter(this, mPaymentList);
			gridView.setAdapter(adapter2);
			break;
		}
		gridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				switch (dialogShow) {
				case 0:
//					Toast.ToastMe(CheckOrderActivity.this, mShippingList.get(arg2).getShipping_id());
					mShippingList.get(arg2).getShipping_id();
					mShippingPrice_id = mShippingList.get(arg2).getShipping_id();
					mShippingPrice = mShippingList.get(arg2).getShipping_fee();
					if (flow_type == 0) {
						GetPrice(mGoodsPrice, mCouponPrice, mShippingPrice);
					}else if (flow_type == 4) {
						
					}
					if (mShippingList.get(arg2).getShipping_code().equals("cac")) {
						DoorshopView.setVisibility(View.VISIBLE);
//						mDoorshopListView.setVisibility(View.VISIBLE);
						mDoorshopList = mShippingList.get(arg2).getDoorshop();
						
						mDoorshopLine.setVisibility(View.VISIBLE);
						Texttool.setText(mDoorshopName, mDoorshopList.get(0).getShop_name());
						Texttool.setText(mDoorshopContent, mDoorshopList.get(0).getShop_address());
						Texttool.setText(mDoorshopDistance, "距当前位置 "+mDoorshopList.get(0).getDistance()+"km");
						
						mDoorshopList.get(0).setCheck(true);
						mDoorShop_id = mDoorshopList.get(0).getShop_id();
						mDoorAdapter = new CheckDoorAdapter(CheckOrderActivity.this, mDoorshopList);
						mDoorshopListView.setAdapter(mDoorAdapter);
						ListUtil.setListViewHeightBasedOnChildren(mDoorshopListView, 5);
					}else {
						mDoorShop_id = "";
						DoorshopView.setVisibility(View.GONE);
						mDoorshopListView.setVisibility(View.GONE);
						mDoorshopLine.setVisibility(View.GONE);
					}
					Texttool.setText(mShippingName, mShippingList.get(arg2).getShipping_name()+"   ￥"+mShippingList.get(arg2).getShipping_fee());
					lodingDialog.dismiss();
					break;
				case 1:
//					Toast.ToastMe(CheckOrderActivity.this, mPaymentPrice_id = mPaymentList.get(arg2).getPay_id());
					mPaymentPrice_id = mPaymentList.get(arg2).getPay_id();
					lodingDialog.dismiss();
					Texttool.setText(mPaymentName, mPaymentList.get(arg2).getPay_name());
					break;
				}
			}
		});
	}
	
	private void GetPrice(double goods, double coupon , double shipping){ 
		double all = 0.00;
		switch (couponType) {
		case 0:
			all = ((goods-coupon)<0?0.00:(goods-coupon))+shipping;
			break;
		case 1:
			all = (goods*coupon/100)+shipping;
			break;
		}
		Texttool.setText(mAllPrice, "总价 ： "+all+"￥");	
	}

	Handler mHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			try {
				switch (msg.what) {
				case Constants.CHECK_ORDER:
					JSONObject jsonObj = new JSONObject(msg.obj.toString());
					//10001 配送方式
					if (jsonObj.getJSONObject("status").getString("succeed").equals("1")) {
						String jsonData = jsonObj.getString("data");
						checkOrder = JsonUtil.instance().fromJson(jsonData,new TypeToken<CheckOrder>() {}.getType());
						Texttool.setText(mAddressName, "收货人："+checkOrder.getConsignee().getConsignee());
						Texttool.setText(mAddressDetail, "收货地址："+checkOrder.getConsignee().getProvince_name()+checkOrder.getConsignee().getCity_name()+checkOrder.getConsignee().getDistrict_name()+checkOrder.getConsignee().getAddress());
						
						mGoodsList = checkOrder.getGoods_list();
						mGoodsAdapter = new CheckGoodsAdapter(CheckOrderActivity.this, mGoodsList,flow_type);
						mGoodsListView.setAdapter(mGoodsAdapter);
						ListUtil.setListViewHeightBasedOnChildren(mGoodsListView, 0);
						if (flow_type == 0) {
							if (rectype.equals("5")) {
								for (int i = 0; i < mGoodsList.size(); i++) {
									mGoodsPrice += mGoodsList.get(i).getSubtotal();
								}
							}else if (rectype.equals("0")){
								mGoodsPrice = Double.parseDouble(getIntent().getStringExtra("totalfee"));
							}
							GetPrice(mGoodsPrice, mCouponPrice, mShippingPrice);
						}else if (flow_type == 4) {
							Texttool.setText(mAllPrice, "总积分 ： "+ mGoodsList.get(0).getExchange_integral());	
						}
						
						mCouponList = checkOrder.getCoupons_list();
						if (mCouponList.size()>0) {
							mCouponAdapter = new CheckCouponAdapter(CheckOrderActivity.this, mCouponList);
							mCouponListView.setAdapter(mCouponAdapter);
							ListUtil.setListViewHeightBasedOnChildren(mCouponListView, 0);
						}else {
							mCouponLine.setVisibility(View.GONE);
						}
						
						mShippingList = checkOrder.getShipping_list();
						mPaymentList = checkOrder.getPayment_list();
					} else {
						if (jsonObj.getJSONObject("status").getString("error_code").equals("10001")) {
							if (isFast) {
								startActivity(new Intent(CheckOrderActivity.this, AddressListActivity.class));
								isFast = false;
							}
						}else {
							Toast.ToastMe(CheckOrderActivity.this,jsonObj.getJSONObject("status").getString("error_desc"));
						}
					}
					break;
				case Constants.FLOW_DONE:
					JSONObject jsonObj1 = new JSONObject(msg.obj.toString());
					//10001 配送方式
					if (jsonObj1.getJSONObject("status").getString("succeed").equals("1")) {
						if (flow_type == 0) {
							Intent intent = new Intent();
							String jsonData = jsonObj1.getString("data");
							FlowDone flowDone = JsonUtil.instance().fromJson(jsonData,new TypeToken<FlowDone>() {}.getType());
							if (flowDone.getOrder_info().getPay_code().equals("wx_new_jspay")) {
								intent.putExtra("name", flowDone.getOrder_info().getSubject());
								intent.putExtra("amount", flowDone.getOrder_info().getOrder_amount());
								intent.putExtra("order_sn", flowDone.getOrder_info().getOrder_sn());
								intent.setClass(CheckOrderActivity.this, PayActivity.class);
								startActivity(intent);
								finish();
							}else if (flowDone.getOrder_info().getPay_code().equals("alipay")) {
//								Toast.ToastMe(CheckOrderActivity.this,"去支付bao");
								finish();
							}
						}else if (flow_type == 4) {
							Toast.ToastMe(CheckOrderActivity.this,"商品兑换成功");
							finish();
						}
					} else {
						Toast.ToastMe(CheckOrderActivity.this,jsonObj1.getJSONObject("status").getString("error_desc"));
					}
					break;
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		};
	};

}
