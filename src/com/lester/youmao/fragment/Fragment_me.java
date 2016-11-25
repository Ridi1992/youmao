package com.lester.youmao.fragment;

import org.json.JSONException;
import org.json.JSONObject;

import android.R.bool;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.best.view.CircleImageView;
import com.bset.tool.BitMap;
import com.bset.tool.PhotoPickUtil;
import com.bset.tool.PhotoPickUtil.OnPhotoPickedlistener;
import com.bset.tool.Texttool;
import com.bset.tool.Toast;
import com.lester.youmao.LoginActivity;
import com.lester.youmao.MainActivity;
import com.lester.youmao.R;
import com.lester.youmao.Userhead;
import com.lester.youmao.me.AboutActivity;
import com.lester.youmao.me.AddressListActivity;
import com.lester.youmao.me.CouponsListActivity;
import com.lester.youmao.me.FeedBackActivity;
import com.lester.youmao.me.MyCouponsListActivity;
import com.lester.youmao.me.NoticeActivity;
import com.lester.youmao.me.OrderActivity;
import com.lester.youmao.me.OrderBackListActivity;
import com.lester.youmao.me.PointsListActivity;
import com.lester.youmao.me.TuiGuangActivity;
import com.lester.youmao.ui.BitmapUtil;
import com.lester.youmao.ui.CustomImageView;
import com.lester.youmao.ui.PhotoUpload;
import com.lester.youmao.userinfo.mUserInfo;
import com.sanmi.http.Constants;
import com.sanmi.http.PublicRequest;
import com.sanmi.loader.ImageLoader_Circle;

public class Fragment_me extends Fragment implements OnClickListener{

	private TextView mTitle;
	private View view;
	
	private CircleImageView mFace;
	private TextView mUser_name; 
	private TextView mPoint;
	private TextView mNotice;
	private LinearLayout mAwaitPay;
	private LinearLayout mShipping;
	private LinearLayout mFinish;
	private LinearLayout mTuihuan;
	private LinearLayout mCouponList;
	private LinearLayout mMeCoupon;
	private LinearLayout mTuiGuang;
	private LinearLayout mAddress;
	private LinearLayout mAbout;
	private LinearLayout mFanKui;
	private LinearLayout mZhuxiao;
	
	boolean isFirst =  true;
	
	PhotoPickUtil util;
	private String mImagePath;
	
	private boolean updateFace = true;
	private ImageLoader_Circle loader_Circle;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);

		view = inflater.inflate(R.layout.fragment_me, container, false);
		
		updateFace = true;
		loader_Circle = new ImageLoader_Circle(getActivity());
		initViews(view);

		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
		if (mUserInfo.GetUserInfo(getActivity())!= null) {
			Texttool.setText(mUser_name, mUserInfo.GetUserInfo(getActivity()).getUser().getUser_name());
			Log.i("asda", mUserInfo.GetUserInfo(getActivity()).getUser().getFace_img());
			PublicRequest http = new PublicRequest(mHandler);
			http.MePoint(getActivity(), mUserInfo.GetUserInfo(getActivity()).getUser().getUser_id());
			if(updateFace){
				loader_Circle.DisplayImage(mUserInfo.GetUserInfo(getActivity()).getUser().getFace_img(), mFace);
			}
		}else {
			if (isFirst) {
				startActivity(new Intent(getActivity(), LoginActivity.class));
				isFirst = false;
			}
		}
	}

	private void initViews(View v) {
		mTitle = (TextView) v.findViewById(R.id.top_title);
		mTitle.setText("我的");
		mUser_name = (TextView) view.findViewById(R.id.me_user_name);
		mFace = (CircleImageView) view.findViewById(R.id.me_face);
		if (mUserInfo.GetUserInfo(getActivity())!= null) {
			
		}
		mPoint = (TextView) view.findViewById(R.id.me_point);
		mNotice = (TextView) view.findViewById(R.id.me_notice);
		mAwaitPay = (LinearLayout) view.findViewById(R.id.me_awaitpay);
		mShipping = (LinearLayout) view.findViewById(R.id.me_awaitship);
		mFinish = (LinearLayout) view.findViewById(R.id.me_finish);
		mTuihuan = (LinearLayout) view.findViewById(R.id.me_tuihuan);
		mCouponList = (LinearLayout) view.findViewById(R.id.me_couponlist);
		mMeCoupon = (LinearLayout) view.findViewById(R.id.me_mecoupon);
		mTuiGuang = (LinearLayout) view.findViewById(R.id.me_tuiguang);
		mAddress = (LinearLayout) view.findViewById(R.id.me_address);
		mAbout = (LinearLayout) view.findViewById(R.id.me_about);
		mFanKui = (LinearLayout) view.findViewById(R.id.me_fankui);
		mZhuxiao = (LinearLayout) view.findViewById(R.id.me_zhuxiao);
		
		mFace.setOnClickListener(this);
		mPoint.setOnClickListener(this);
		mNotice.setOnClickListener(this);
		mAwaitPay.setOnClickListener(this);
		mShipping.setOnClickListener(this);
		mFinish.setOnClickListener(this);
		mTuihuan.setOnClickListener(this);
		mCouponList.setOnClickListener(this);
		mMeCoupon.setOnClickListener(this);
		mTuiGuang.setOnClickListener(this);
		mAddress.setOnClickListener(this);
		mAbout.setOnClickListener(this);
		mFanKui.setOnClickListener(this);
		mZhuxiao.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.me_face:
			if (mUserInfo.GetUserInfo(getActivity())!= null) {
				util = new PhotoPickUtil(getActivity(),this ,new Face());
				util.doPickPhotoAction(true, 300, 300);
			}else {
				startActivity(new Intent(getActivity(), LoginActivity.class));
			}
			break;
		case R.id.me_point:
			if (mUserInfo.GetUserInfo(getActivity())!= null) {
				startActivity(intent.setClass(getActivity(), PointsListActivity.class));
			}else {
				startActivity(new Intent(getActivity(), LoginActivity.class));
			}
			break;
		case R.id.me_notice:
			if (mUserInfo.GetUserInfo(getActivity())!= null) {
				startActivity(intent.setClass(getActivity(), NoticeActivity.class));
			}else {
				startActivity(new Intent(getActivity(), LoginActivity.class));
			}
			break;
		case R.id.me_awaitpay:
			if (mUserInfo.GetUserInfo(getActivity())!= null) {
				intent.putExtra("order", 1); 
				startActivity(intent.setClass(getActivity(), OrderActivity.class));
			}else {
				startActivity(new Intent(getActivity(), LoginActivity.class));
			}
			break;
		case R.id.me_awaitship:
			if (mUserInfo.GetUserInfo(getActivity())!= null) {
				intent.putExtra("order", 2); 
				startActivity(intent.setClass(getActivity(), OrderActivity.class));
			}else {
				startActivity(new Intent(getActivity(), LoginActivity.class));
			}
			break;
		case R.id.me_finish:
			if (mUserInfo.GetUserInfo(getActivity())!= null) {
				intent.putExtra("order", 3); 
				startActivity(intent.setClass(getActivity(), OrderActivity.class));
			}else {
				startActivity(new Intent(getActivity(), LoginActivity.class));
			}
			break;
		case R.id.me_tuihuan:
			if (mUserInfo.GetUserInfo(getActivity())!= null) {
				startActivity(intent.setClass(getActivity(), OrderBackListActivity.class));
			}else {
				startActivity(new Intent(getActivity(), LoginActivity.class));
			}
			break;
		case R.id.me_couponlist:
			if (mUserInfo.GetUserInfo(getActivity())!= null) {
				startActivity(intent.setClass(getActivity(), CouponsListActivity.class));
			}else {
				startActivity(new Intent(getActivity(), LoginActivity.class));
			}
			break;
		case R.id.me_mecoupon:
			if (mUserInfo.GetUserInfo(getActivity())!= null) {
				startActivity(intent.setClass(getActivity(), MyCouponsListActivity.class));
			}else {
				startActivity(new Intent(getActivity(), LoginActivity.class));
			}
			break;
		case R.id.me_tuiguang:
			if (mUserInfo.GetUserInfo(getActivity())!= null) {
				startActivity(intent.setClass(getActivity(), TuiGuangActivity.class));
			}else {
				startActivity(new Intent(getActivity(), LoginActivity.class));
			}
			break;
		case R.id.me_address: 
			if (mUserInfo.GetUserInfo(getActivity())!= null) {
				startActivity(intent.setClass(getActivity(), AddressListActivity.class));
			}else {
				startActivity(new Intent(getActivity(), LoginActivity.class));
			}
			break;
		case R.id.me_about:
			if (mUserInfo.GetUserInfo(getActivity())!= null) {
				startActivity(intent.setClass(getActivity(), AboutActivity.class));
			}else {
				startActivity(new Intent(getActivity(), LoginActivity.class));
			}
			break;
		case R.id.me_fankui:
			if (mUserInfo.GetUserInfo(getActivity())!= null) {
				startActivity(intent.setClass(getActivity(), FeedBackActivity.class));
			}else {
				startActivity(new Intent(getActivity(), LoginActivity.class));
			}
			break;
		case R.id.me_zhuxiao:
//			startActivity(intent.setClass(getActivity(), LoginActivity.class));
//			UserInfo info=new UserInfo();
//			info.setIslogin(false);
			mUserInfo.SaveUserInfo(getActivity(), null);
			Texttool.setText(mUser_name, "登录/注册");
			Texttool.setText(mPoint, "我的积分");
			mFace.setImageResource(R.drawable.face);
			isFirst=false;
			startActivity(new Intent(getActivity(), LoginActivity.class));
			break;
		}
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
					util.pickResult(requestCode, resultCode, data);
					updateFace = false;
		}
	
	@SuppressLint("SdCardPath")
	class Face implements OnPhotoPickedlistener{
		@Override
		public void photoPicked(String path, Bitmap bm) {
			boolean b = false;
			Bitmap bmp = bm;
			Log.i("path", "path="+path);
			Log.i("bmp", "bmp="+bm);
			if (path != null) {
				bmp=BitmapFactory.decodeFile(path);
				b = Userhead.savehead(bmp, mUserInfo.GetUserInfo(getActivity()).getUser().getUser_id());
			}else if (bmp != null) {
				b = Userhead.savehead(bmp, mUserInfo.GetUserInfo(getActivity()).getUser().getUser_id());
			}
			if (b) {
				mFace.setImageBitmap(bmp);
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						mImagePath = "/sdcard/youmao/head/"+mUserInfo.GetUserInfo(getActivity()).getUser().getUser_id()+".jpg";
						PhotoUpload pu = new PhotoUpload();
						pu.uploadFile(getActivity(), mUserInfo.GetUserInfo(getActivity()).getUser().getUser_id()+".jpg", mImagePath, "http://b.seotech.com.cn/app_api/face.php?act=upload&user_id="+mUserInfo.GetUserInfo(getActivity()).getUser().getUser_id()+"&device=1");
					}
				}).start();
				
			}
		}
	}
	
	Handler mHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			try {
				switch (msg.what) {
				case Constants.ME_POINT:
					JSONObject jsonObj = new JSONObject(msg.obj.toString());
					if (jsonObj.getString("code").equals("1")) {
						String jsonData = jsonObj.getJSONObject("data").getString("total_points");
						if (jsonData != null) {
							mPoint.setText("我的积分( "+jsonData+" )");
						}
					} else {
						Toast.ToastMe(getActivity(),jsonObj.getString("message"));
					}
					break;
				}
			}catch (JSONException e) {
				e.printStackTrace();
			}
		};
	};
}
