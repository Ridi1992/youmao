package com.lester.youmao.jifen;

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
import com.lester.youmao.home.GoodsCommentsActivity;
import com.lester.youmao.home.GoodsDescActivity;
import com.lester.youmao.home.GoodsDetailActivity;
import com.lester.youmao.me.AddressListActivity;
import com.lester.youmao.shopcart.CheckOrderActivity;
import com.lester.youmao.userinfo.mUserInfo;
import com.sanmi.http.Constants;
import com.sanmi.http.JsonUtil;
import com.sanmi.http.PublicRequest;
import com.sanmi.loader.ImageLoader;

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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class JifenGoodsDetailActivity extends Activity implements OnClickListener{
	
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
	private TextView mCommentNum;
	private LinearLayout mComment;
	private LinearLayout mDesc;
//	private WebView mDescWeb;
	private TextView mToBuy;
	
	private PopupWindow mPopup;
	private ImageView mPopImg;
	private ImageView mPopBack;
	private TextView mPopName;
	private TextView mPopPrice;
	private Button mGoodsMin;
	private TextView mGoodsNum;
	private int mGoodsNumber = 1;
	private Button mGoodsMax;
	private Button mPopSubmit;
	
	private GoodsDetail mGoodsDetail;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.jifen_goods_detail);
		
		PublicRequest http = new PublicRequest(mHandler);
		http.GoodsDetail(JifenGoodsDetailActivity.this, getIntent().getStringExtra("goods_id"),"2");
//		PublicRequest http2 = new PublicRequest(mHandler);
//		http2.GoodsDesc(JifenGoodsDetailActivity.this, getIntent().getStringExtra("goods_id"));
//		PublicRequest http3 = new PublicRequest(mHandler);
//		http3.CartNumber(JifenGoodsDetailActivity.this, mUserInfo.GetUserInfo(JifenGoodsDetailActivity.this).getUser().getUser_id());
		
		inflaterBanner = LayoutInflater.from(this);
		mImageLoader = new ImageLoader(this);
		initView();
	}

	private void initView() {
		mFrame = (FrameLayout) findViewById(R.id.frame);
		LinearLayout.LayoutParams lParams_vp = (LinearLayout.LayoutParams) mFrame.getLayoutParams();
		lParams_vp.width = MainActivity.width;
		lParams_vp.height = MainActivity.width;
		mViewPager = (ViewPager) findViewById(R.id.vp);
		
		mBack = (ImageView) findViewById(R.id.goods_back);
		mCart = (ImageView) findViewById(R.id.goods_cart);
		mCart.setVisibility(View.GONE);
		mCartNum = (TextView) findViewById(R.id.goods_cartnum);
		mCartNum.setVisibility(View.GONE);
		mBack.setOnClickListener(this);
		
		mName = (TextView) findViewById(R.id.goods_detail_name);
		mBrief = (TextView) findViewById(R.id.goods_detail_brief);
		mShopPrice = (TextView) findViewById(R.id.goods_detail_shopprice);
		mMarketPrice = (TextView) findViewById(R.id.goods_detail_marketprice);
		mSalseNum = (TextView) findViewById(R.id.goods_detail_salsenum);
		mComment = (LinearLayout) findViewById(R.id.goods_detail_comment);
		mCommentNum = (TextView) findViewById(R.id.goods_detail_commentnum);
		mDesc = (LinearLayout) findViewById(R.id.goods_detail_desc);
//		mDescWeb = (WebView) findViewById(R.id.goods_detail_descweb);
		mToBuy = (TextView) findViewById(R.id.goods_detail_tobuy);
		mDesc.setOnClickListener(this);
		mComment.setOnClickListener(this);
		mToBuy.setOnClickListener(this);
		
	}
	

	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.goods_back:
			finish();
			break;
		case R.id.goods_detail_comment:
			startActivity(intent.setClass(JifenGoodsDetailActivity.this, GoodsCommentsActivity.class));
			break;
		case R.id.goods_detail_desc:
			PublicRequest http2 = new PublicRequest(mHandler);
			http2.GoodsDesc(JifenGoodsDetailActivity.this, getIntent().getStringExtra("goods_id"));
//			mDescWeb.setVisibility(View.VISIBLE);
			break;
		case R.id.goods_detail_tobuy:
			showPop();
			break;
		case R.id.pop_back:
			mPopup.dismiss();
			break;
		case R.id.pop_submit:
			order();
			break;
//		case R.id.goods_count_min:
//			if (mGoodsNumber <= 1) {
//				
//			}else {
//				mGoodsNumber = mGoodsNumber-1;
//				Texttool.setText(mGoodsNum, mGoodsNumber+"");
//			}
//			break;
//		case R.id.goods_count_max:
//			mGoodsNumber = mGoodsNumber+1;
//			Texttool.setText(mGoodsNum, mGoodsNumber+"");
//			break;
		}
		
	}
	
	private void order() {
		mPopup.dismiss();
		if (mUserInfo.GetUserInfo(JifenGoodsDetailActivity.this)!= null) {
			PublicRequest http = new PublicRequest(mHandler);
			http.JifenCart(JifenGoodsDetailActivity.this, mGoodsDetail.getId(), mUserInfo.GetUserInfo(JifenGoodsDetailActivity.this).getUser().getUser_id(), mGoodsNumber+"");
		}else {
			startActivity(new Intent(JifenGoodsDetailActivity.this, LoginActivity.class));
		}
	}

	private void showPop() {
		LayoutInflater inflater = (LayoutInflater) getSystemService(JifenGoodsDetailActivity.LAYOUT_INFLATER_SERVICE);
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
				JifenGoodsDetailActivity.this.findViewById(R.id.detail_bottom),
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
		mPopSubmit = (Button) view.findViewById(R.id.pop_submit);
		mPopSubmit.setVisibility(View.VISIBLE);
		mPopSubmit.setOnClickListener(this);
		mGoodsMin = (Button) view.findViewById(R.id.goods_count_min);
		mGoodsNum = (TextView) view.findViewById(R.id.goods_count_num);
		mGoodsMax = (Button) view.findViewById(R.id.goods_count_max);
		mGoodsMin.setVisibility(View.GONE);
		mGoodsMax.setVisibility(View.GONE);
		
		mImageLoader.DisplayImage(mGoodsDetail.getImg().getThumb(),mPopImg);
		Texttool.setText(mPopName, mGoodsDetail.getGoods_name());
		Texttool.setText(mPopPrice, "积分 ："+mGoodsDetail.getExchange_integral());
		Texttool.setText(mGoodsNum, "数量 ×  "+mGoodsNumber+"");
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
								Texttool.setText(mShopPrice, "积分 ："+mGoodsDetail.getExchange_integral());
								Texttool.setText(mMarketPrice, "￥"+mGoodsDetail.getShop_price());
							}else {
								Texttool.setText(mShopPrice, "积分 ："+mGoodsDetail.getExchange_integral());
								Texttool.setText(mMarketPrice, "￥"+mGoodsDetail.getPromote_price());
							}
							Texttool.setText(mSalseNum, "已售  "+mGoodsDetail.getSalesnum());
							Texttool.setText(mCommentNum, "用户评价("+mGoodsDetail.getGoods_comment()+")");
						}
					} else {
						Toast.ToastMe(JifenGoodsDetailActivity.this,jsonObj.getString("message"));
					}
					break;
				case Constants.GOODS_DESC:
					JSONObject jsonObj1 = new JSONObject(msg.obj.toString());
					if (jsonObj1.getJSONObject("status").getString("succeed").equals("1")) {
						String jsonData = jsonObj1.getString("data");
						if (jsonData != null) {
							jsonData = jsonData.replace("\\", "");

							Intent intent = new Intent();
							intent.putExtra("desc", jsonData);
							intent.setClass(JifenGoodsDetailActivity.this, GoodsDescActivity.class);
							startActivity(intent);
							
//							mDescWeb.getSettings().setDefaultTextEncodingName("utf-8");
//							mDescWeb.loadData(jsonData, "text/html", "utf-8");
						}
					} else {
						Toast.ToastMe(JifenGoodsDetailActivity.this,jsonObj1.getString("message"));
					}
					break;
				case Constants.JIFEN_TOCART:
					JSONObject jsonObj11 = new JSONObject(msg.obj.toString());
					if (jsonObj11.getString("code").equals("1")) {
						Toast.ToastMe(JifenGoodsDetailActivity.this, jsonObj11.getString("message"));
						Intent intent = new Intent();
						intent.putExtra("flow_type", 4);
						intent.putExtra("rec_type", "0");
						startActivity(intent.setClass(JifenGoodsDetailActivity.this, CheckOrderActivity.class));
					} else {
						Toast.ToastMe(JifenGoodsDetailActivity.this, jsonObj11.getString("message"));
					}
					break;
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		};
	};

}
