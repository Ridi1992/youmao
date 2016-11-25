package com.lester.youmao.home;

import java.util.ArrayList;

import org.json.JSONObject;

import com.bset.tool.ListUtil;
import com.bset.tool.Texttool;
import com.bset.tool.Toast;
import com.google.gson.reflect.TypeToken;
import com.lester.youmao.LoginActivity;
import com.lester.youmao.MainActivity;
import com.lester.youmao.R;
import com.lester.youmao.ShopCartActivity;
import com.lester.youmao.WebLinkActivity;
import com.lester.youmao.adapter.AttrsAdapter;
import com.lester.youmao.adapter.BannerAdapter;
import com.lester.youmao.adapter.GoodsImgAdapter;
import com.lester.youmao.adapter.GoodsListAdapter;
import com.lester.youmao.entity.Banner;
import com.lester.youmao.entity.GoodsDetail;
import com.lester.youmao.entity.GoodsList;
import com.lester.youmao.entity.Pictures;
import com.lester.youmao.entity.Specification;
import com.lester.youmao.entity.Value;
import com.lester.youmao.fragment.Fragment_home;
import com.lester.youmao.jifen.JifenGoodsDetailActivity;
import com.lester.youmao.me.AddressListActivity;
import com.lester.youmao.me.OrderActivity;
import com.lester.youmao.shopcart.CheckOrderActivity;
import com.lester.youmao.userinfo.mUserInfo;
import com.sanmi.http.Constants;
import com.sanmi.http.JsonUtil;
import com.sanmi.http.PublicRequest;
import com.sanmi.loader.ImageLoader;

import android.animation.LayoutTransition;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AbsoluteLayout;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class GoodsDetailActivity extends Activity implements OnClickListener{
	
	private ImageView mBack;
	private ImageView mCart;
	private TextView mCartNum;
	
	private LayoutInflater inflaterBanner;
	private ImageLoader mImageLoader;
	private FrameLayout mFrame;
	private ViewPager mViewPager;
	private ArrayList<Pictures> BannerList = new ArrayList<Pictures>();
	private View viewBanner;
	private ImageView mImageView;
	private ArrayList<View> mViewList = new ArrayList<View>();
	private GoodsImgAdapter mBannerAdapter;
	private int emun;// 记录图片数量
	private ImageView[] indicator_imgs;// 存放引到图片数组
	private ImageView imgView;
	
	private TextView mName;
	private TextView mBrief;
	private TextView mShopPrice;
	private TextView mMarketPrice;
	private TextView mSalseNum;
	private LinearLayout mAttrs;
	private TextView mCommentNum;
	private LinearLayout mComment;
	private LinearLayout mDesc;
//	private WebView mDescWeb;
	private TextView mToCart;
	private TextView mToBuy;
	
	private PopupWindow mPopup;
	private ImageView mPopImg;
	private ImageView mPopBack;
	private TextView mPopName;
	private TextView mPopPrice;
	private LinearLayout mPopAdd;
	private Button mGoodsMin;
	private TextView mGoodsNum;
	private int mGoodsNumber = 1;
	private Button mGoodsMax;
	private Button mPopToCart;
	private Button mPopToBuy;
	private Button mPopSubmit;
	
	private GoodsDetail mGoodsDetail;
	private ArrayList<Specification> mSpecifications;
	private ArrayList<Value> mAttr;
	private ArrayList<Double> mAttrPrice;
	private double mAllAttrsPrice = 0.0;
	private ArrayList<String> mAttrId;
	private String mAllAttrsId = "";
	
	private int Poptype;//弹出pop的文字类型
	private int Goodstype;//购买类型 1加入购物车  2立即购买
	private String Rectype;//rec_type 0加入购物车  5立即购买
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.goods_detail);
		
		PublicRequest http = new PublicRequest(mHandler);
		http.GoodsDetail(GoodsDetailActivity.this, getIntent().getStringExtra("goods_id"),"1");
//		PublicRequest http2 = new PublicRequest(mHandler);
//		http2.GoodsDesc(GoodsDetailActivity.this, getIntent().getStringExtra("goods_id"));
		if (mUserInfo.GetUserInfo(GoodsDetailActivity.this) == null) {
			
		}else {
			PublicRequest http2 = new PublicRequest(mHandler);
			http2.CartNumber(GoodsDetailActivity.this, mUserInfo.GetUserInfo(GoodsDetailActivity.this).getUser().getUser_id());
		}
		
		inflaterBanner = LayoutInflater.from(this);
		mImageLoader = new ImageLoader(this);
		initView();
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
			if (mUserInfo.GetUserInfo(GoodsDetailActivity.this) == null) {
				
			}else {
				PublicRequest http2 = new PublicRequest(mHandler);
				http2.CartNumber(GoodsDetailActivity.this, mUserInfo.GetUserInfo(GoodsDetailActivity.this).getUser().getUser_id());
			}
		}

	private void initView() {
		mFrame = (FrameLayout) findViewById(R.id.frame);
		LinearLayout.LayoutParams lParams_vp = (LinearLayout.LayoutParams) mFrame.getLayoutParams();
		lParams_vp.width = MainActivity.width;
		lParams_vp.height = MainActivity.width;
		mViewPager = (ViewPager) findViewById(R.id.vp);
		
		mBack = (ImageView) findViewById(R.id.goods_back);
		mCart = (ImageView) findViewById(R.id.goods_cart);
		mCartNum = (TextView) findViewById(R.id.goods_cartnum);
		mBack.setOnClickListener(this);
		mCart.setOnClickListener(this);
		
		mName = (TextView) findViewById(R.id.goods_detail_name);
		mBrief = (TextView) findViewById(R.id.goods_detail_brief);
		mShopPrice = (TextView) findViewById(R.id.goods_detail_shopprice);
		mMarketPrice = (TextView) findViewById(R.id.goods_detail_marketprice);
		mSalseNum = (TextView) findViewById(R.id.goods_detail_salsenum);
		mAttrs = (LinearLayout) findViewById(R.id.goods_detail_attrs);
		mCommentNum = (TextView) findViewById(R.id.goods_detail_commentnum);
		mComment = (LinearLayout) findViewById(R.id.goods_detail_comment);
		mDesc = (LinearLayout) findViewById(R.id.goods_detail_desc);
//		mDescWeb = (WebView) findViewById(R.id.goods_detail_descweb);
		mToCart = (TextView) findViewById(R.id.goods_detail_tocate);
		mToBuy = (TextView) findViewById(R.id.goods_detail_tobuy);
		mAttrs.setOnClickListener(this);
		mComment.setOnClickListener(this);
		mDesc.setOnClickListener(this);
		mToCart.setOnClickListener(this);
		mToBuy.setOnClickListener(this);
		mToCart.setEnabled(false);
		mToBuy.setEnabled(false);
		
	}
	
	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.goods_back:
			finish();
			break;
		case R.id.goods_cart:
			startActivity(intent.setClass(GoodsDetailActivity.this, ShopCartActivity.class));
//			MainActivity.mRadioGroup.check(R.id.rb_main_01);
//			Fragment_home home = new Fragment_home();
//	        FragmentManager fm = getFragmentManager();
//	        FragmentTransaction ft = fm.beginTransaction();
//	        ft.replace(R.id.layout, home);
//	        ft.commit();
			break;
		case R.id.goods_detail_attrs:
			Poptype = 1;
			showPop();
			break;
		case R.id.goods_detail_comment:
			intent.putExtra("goods_id", mGoodsDetail.getId());
			startActivity(intent.setClass(GoodsDetailActivity.this, GoodsCommentsActivity.class));
			break;
		case R.id.goods_detail_desc:
			PublicRequest http2 = new PublicRequest(mHandler);
			http2.GoodsDesc(GoodsDetailActivity.this, getIntent().getStringExtra("goods_id"));
//			mDescWeb.setVisibility(View.VISIBLE);
			break;
		case R.id.goods_detail_tocate:
			Poptype = 2;
			Goodstype = 1;
			showPop();
			break;
		case R.id.goods_detail_tobuy:
			Poptype = 2;
			Goodstype = 2;
			showPop();
			break;
		case R.id.pop_back:
			mPopup.dismiss();
			break;
		case R.id.pop_tocate:
			Goodstype = 1;
			order();
			break;
		case R.id.pop_tobuy:
			Goodstype = 2;
			order();
			break;
		case R.id.pop_submit:
			order();
			break;
		case R.id.goods_count_min:
			if (mGoodsNumber <= 1) {
				
			}else {
				mGoodsNumber = mGoodsNumber-1;
				Texttool.setText(mGoodsNum, mGoodsNumber+"");
			}
			break;
		case R.id.goods_count_max:
			mGoodsNumber = mGoodsNumber+1;
			Texttool.setText(mGoodsNum, mGoodsNumber+"");
			break;
		}
		
	}
	
	private void order() {
		mPopup.dismiss();
		switch (Goodstype) {
		case 1:
			Rectype = "0";
			if (mUserInfo.GetUserInfo(GoodsDetailActivity.this)!= null) {
				PublicRequest http = new PublicRequest(mHandler);
				http.CartCreate(GoodsDetailActivity.this, mUserInfo.GetUserInfo(GoodsDetailActivity.this).getSession().getSid(), mUserInfo.GetUserInfo(GoodsDetailActivity.this).getSession().getUid(), mGoodsDetail.getId(), mGoodsNumber+"", mAllAttrsId,Rectype);
			}else {
				startActivity(new Intent(GoodsDetailActivity.this, LoginActivity.class));
			}
			break;
		case 2:
			Rectype = "5";
//			Toast.ToastMe(this, "ToBuy");
			if (mUserInfo.GetUserInfo(GoodsDetailActivity.this)!= null) {
				PublicRequest http = new PublicRequest(mHandler);
				http.CartCreate(GoodsDetailActivity.this, mUserInfo.GetUserInfo(GoodsDetailActivity.this).getSession().getSid(), mUserInfo.GetUserInfo(GoodsDetailActivity.this).getSession().getUid(), mGoodsDetail.getId(), mGoodsNumber+"", mAllAttrsId,Rectype);
			}else {
				startActivity(new Intent(GoodsDetailActivity.this, LoginActivity.class));
			}
			break;
		}
		
	}

	private void showPop() {
		LayoutInflater inflater = (LayoutInflater) getSystemService(GoodsDetailActivity.LAYOUT_INFLATER_SERVICE);
		View popView = inflater.inflate(R.layout.goods_attr, null);
		
		initPopView(popView);
		
		// 下面是两种方法得到宽度和高度 getWindow().getDecorView().getWidth()
		mPopup = new PopupWindow(popView, LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		// 设置popWindow弹出窗体可点击，这句话必须添加，并且是true
		mPopup.setFocusable(true);
		mPopup.setOutsideTouchable(true);
		// 实例化一个ColorDrawable颜色为半透明
		ColorDrawable dw = new ColorDrawable(0x77000000);
		mPopup.setBackgroundDrawable(dw);
		// 设置popWindow的显示和消失动画
		mPopup.setAnimationStyle(R.style.popwindow_anim_style);
		// 在底部显示
		mPopup.showAtLocation(
				GoodsDetailActivity.this.findViewById(R.id.detail_bottom),
				Gravity.BOTTOM, 0, 0);
		// mPopup.update();
		// mPopup.setBackgroundDrawable(new BitmapDrawable());
		// mPopup.showAsDropDown(anchor);
	}
	
	private void initPopView(View view) {
		mPopImg = (ImageView) view.findViewById(R.id.pop_img);
		mPopBack = (ImageView) view.findViewById(R.id.pop_back);
		mPopBack.setOnClickListener(this);
		mPopName = (TextView) view.findViewById(R.id.pop_name);
		mPopPrice = (TextView) view.findViewById(R.id.pop_price);
		mPopAdd = (LinearLayout) view.findViewById(R.id.pop_add);
		switch (Poptype) {
		case 1:
			mPopToCart = (Button) view.findViewById(R.id.pop_tocate);
			mPopToCart.setVisibility(View.VISIBLE);
			mPopToCart.setOnClickListener(this);
			mPopToBuy = (Button) view.findViewById(R.id.pop_tobuy);
			mPopToBuy.setVisibility(View.VISIBLE);
			mPopToBuy.setOnClickListener(this);
			break;
		case 2:
			mPopSubmit = (Button) view.findViewById(R.id.pop_submit);
			mPopSubmit.setVisibility(View.VISIBLE);
			mPopSubmit.setOnClickListener(this);
			break;
		}
		mGoodsMin = (Button) view.findViewById(R.id.goods_count_min);
		mGoodsNum = (TextView) view.findViewById(R.id.goods_count_num);
		Texttool.setText(mGoodsNum, mGoodsNumber+"");
		mGoodsMax = (Button) view.findViewById(R.id.goods_count_max);
		mGoodsMin.setOnClickListener(this);
		mGoodsMax.setOnClickListener(this);
		
		mImageLoader.DisplayImage(mGoodsDetail.getImg().getThumb(),mPopImg);
		Texttool.setText(mPopName, mGoodsDetail.getGoods_name());
		if (mGoodsDetail.getPromote_price() == 0.00) {
			Texttool.setText(mPopPrice, "￥"+mGoodsDetail.getShop_price());
		}else {
			Texttool.setText(mPopPrice, "￥"+mGoodsDetail.getPromote_price());
		}
		
		mSpecifications = mGoodsDetail.getSpecification();
		mAttrPrice = new ArrayList<Double>();
		mAttrId = new ArrayList<String>();
		
		for (int j = 0; j < mSpecifications.size(); j++) {
			mAttrPrice.add(0.0);
			mAttrId.add("0");
			Specification spec = new Specification();
			spec = mSpecifications.get(j);
			mPopAdd = (LinearLayout) view.findViewById(R.id.pop_add);
//			if (isRemove) {
//				layout.removeAllViews();
//				isRemove = false;
//			}
			View v = LayoutInflater.from(GoodsDetailActivity.this).inflate(R.layout.attrs, null);
			TextView textView = (TextView) v.findViewById(R.id.attr_name);
			textView.setText(spec.getName());
			mAttr = spec.getValue();
			mPopAdd.addView(v);
			GridView gridView = (GridView) v.findViewById(R.id.attr_gridview);
			AttrsAdapter adapter = new AttrsAdapter(GoodsDetailActivity.this, mAttr);
			gridView.setId(j);
			gridView.setAdapter(adapter);
			ListUtil.setGridViewHeightBasedOnChildren2(gridView, 3 , 5);
			gridView.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> arg0,View arg1, int arg2, long arg3) {
					for (int k = 0; k < arg0.getCount(); k++) {
						TextView tv = (TextView) arg0.getChildAt(k).findViewById(R.id.attr_item_price);
						if (k == arg2) {
							tv.setBackgroundResource(R.drawable.bg_yuanjiao_hong);
							tv.setTextColor(getResources().getColor(R.color.baise));
						}else {
							tv.setBackgroundResource(R.drawable.bg_yuanjiao_kong);
							tv.setTextColor(getResources().getColor(R.color.hui_77));
						}
					}
					mAttrPrice.remove(arg0.getId());
					mAttrPrice.add(arg0.getId(), mSpecifications.get(arg0.getId()).getValue().get(arg2).getPrice());
					mAllAttrsPrice = 0.0;
					for (int l = 0; l < mAttrPrice.size(); l++) {
						mAllAttrsPrice = mAllAttrsPrice+mAttrPrice.get(l);
					}
					mAttrId.remove(arg0.getId());
					mAttrId.add(arg0.getId(),mSpecifications.get(arg0.getId()).getValue().get(arg2).getId());
					mAllAttrsId = "";
					for (int m = 0; m < mAttrId.size(); m++) {
						mAllAttrsId = mAllAttrsId+mAttrId.get(m)+",";
					}
					mAllAttrsId = mAllAttrsId.substring(0, mAllAttrsId.length()-1);
					if (mGoodsDetail.getPromote_price() == 0.00) {
						Texttool.setText(mPopPrice, "￥"+(mGoodsDetail.getShop_price()+mAllAttrsPrice));
					}else {
						Texttool.setText(mPopPrice, "￥"+(mGoodsDetail.getPromote_price()+mAllAttrsPrice));
					}
				}
			});
		}
	}

	private void mVierAdapter(ArrayList<Pictures> data) {
		for (int i = 0; i < data.size(); i++) {
			data.get(i).getThumb();
			viewBanner = inflaterBanner.inflate(R.layout.page1, null);
			mImageView = (ImageView) viewBanner.findViewById(R.id.home_banner);
			mImageLoader.DisplayImage(data.get(i).getThumb(),mImageView);
			mViewList.add(viewBanner);
		}
		mBannerAdapter = new GoodsImgAdapter(mViewList, this);
		mViewPager.setAdapter(mBannerAdapter);
		emun = data.size();
		indicator_imgs = new ImageView[emun];
		initIndicator();
	}
	
	private void initIndicator() {
		View v = findViewById(R.id.indicator);// 线性水平布局，负责动态调整导航图标
		if (this != null) {
//			emun = mViewList.size();
			indicator_imgs = new ImageView[emun];
			
			((ViewGroup) v).removeAllViews();
			for (int i = 0; i < emun; i++) {
				imgView = new ImageView(this);
				LinearLayout.LayoutParams params_linear = new LinearLayout.LayoutParams(10, 10);
				params_linear.setMargins(7, 10, 7, 10);
				imgView.setLayoutParams(params_linear);
				indicator_imgs[i] = imgView;
				if (i == 0) { // 初始化第一个为选中状态
					indicator_imgs[i].setBackgroundResource(R.drawable.page_focused);
				} else {
					indicator_imgs[i].setBackgroundResource(R.drawable.page_unfocused);
				}
				((ViewGroup) v).addView(indicator_imgs[i]);
			}
		}
			mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
				@Override
				public void onPageSelected(int arg0) {
					// 改变所有导航的背景图片为：未选中
					for (int i = 0; i < indicator_imgs.length; i++) {
						if (indicator_imgs[i] == null) {
							
						}else {
							indicator_imgs[i].setBackgroundResource(R.drawable.page_unfocused);
						}
					}
					// 改变当前背景图片为：选中
						try {
							indicator_imgs[arg0].setBackgroundResource(R.drawable.page_focused);
						} catch (Exception e) {
							e.printStackTrace();
						}
				}
				@Override
				public void onPageScrolled(int arg0, float arg1, int arg2) {
				}
				@Override
				public void onPageScrollStateChanged(int arg0) {
				}
			});
	}

	Handler mHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			try {
				switch (msg.what) {
				case Constants.GOODS_DETAIL:
					mToCart.setEnabled(true);
					mToBuy.setEnabled(true);
					JSONObject jsonObj = new JSONObject(msg.obj.toString());
					if (jsonObj.getJSONObject("status").getString("succeed").equals("1")) {
						String jsonData = jsonObj.getString("data");
						mGoodsDetail= JsonUtil.instance().fromJson(jsonData,new TypeToken<GoodsDetail>() {}.getType());
						if (mGoodsDetail != null) {
							BannerList = new ArrayList<Pictures>();
							BannerList = mGoodsDetail.getPictures();
							if (BannerList != null) {
								mVierAdapter(BannerList);
							}
							
							Texttool.setText(mName, mGoodsDetail.getGoods_name());
							Texttool.setText(mBrief, mGoodsDetail.getGoods_brief());
							if (mGoodsDetail.getPromote_price() == 0.00) {
								Texttool.setText(mShopPrice, "￥"+mGoodsDetail.getShop_price());
								Texttool.setText(mMarketPrice, "￥"+mGoodsDetail.getMarket_price());
							}else {
								Texttool.setText(mShopPrice, "￥"+mGoodsDetail.getPromote_price());
								Texttool.setText(mMarketPrice, "￥"+mGoodsDetail.getShop_price());
							}
							Texttool.setText(mSalseNum, "已售  "+mGoodsDetail.getSalesnum());
							Texttool.setText(mCommentNum, "用户评价("+mGoodsDetail.getGoods_comment()+")");
						}
					} else {
						Toast.ToastMe(GoodsDetailActivity.this,jsonObj.getString("message"));
					}
					break;
				case Constants.GOODS_DESC:
					JSONObject jsonObj1 = new JSONObject(msg.obj.toString());
					if (jsonObj1.getJSONObject("status").getString("succeed").equals("1")) {
						String jsonData = jsonObj1.getString("data");
						if (jsonData != null) {
							jsonData = jsonData.replace("\\", "");
//							mDescWeb.getSettings().setDefaultTextEncodingName("utf-8");
//							mDescWeb.loadData(jsonData, "text/html", "utf-8");
							Intent intent = new Intent();
							intent.putExtra("desc", jsonData);
							intent.setClass(GoodsDetailActivity.this, GoodsDescActivity.class);
							startActivity(intent);
						}
					} else {
						Toast.ToastMe(GoodsDetailActivity.this,jsonObj1.getString("message"));
					}
					break;
				case Constants.CART_CREATE:
					JSONObject jsonObj11 = new JSONObject(msg.obj.toString());
					if (jsonObj11.getJSONObject("status").getString("succeed").equals("1")) {
						if (Rectype.equals("0")) {
							Toast.ToastMe(GoodsDetailActivity.this,"加入购物车成功，请在右上角查看");
							PublicRequest http2 = new PublicRequest(mHandler);
							http2.CartNumber(GoodsDetailActivity.this, mUserInfo.GetUserInfo(GoodsDetailActivity.this).getUser().getUser_id());
						}else if(Rectype.equals("5")){
//							Toast.ToastMe(GoodsDetailActivity.this,"立即购买加入成功");
							Intent intent = new Intent();
							intent.putExtra("flow_type", 0);
							intent.putExtra("rec_type", Rectype);
							startActivity(intent.setClass(GoodsDetailActivity.this, CheckOrderActivity.class));
						}
					} else {
						Toast.ToastMe(GoodsDetailActivity.this, jsonObj11.getJSONObject("status").getString("error_desc"));
						if (jsonObj11.getJSONObject("status").getString("error_code").equals("100")) {
							startActivity(new Intent(GoodsDetailActivity.this, LoginActivity.class));
						}
					}
					break;
				case Constants.CART_NUMBER:
					JSONObject jsonObj111 = new JSONObject(msg.obj.toString());
					if (jsonObj111.getString("code").equals("1")) {
						String jsonData = jsonObj111 .getJSONObject("data").getString("cart_number");
						if (Integer.parseInt(jsonData) < 1000) {
							Texttool.setText(mCartNum, jsonData);
						}else {
							Texttool.setText(mCartNum, "999+");
						}
					} else {
						Toast.ToastMe(GoodsDetailActivity.this, jsonObj111.getString("message"));
					}
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		};
	};

}
