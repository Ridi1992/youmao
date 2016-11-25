package com.lester.youmao;

import com.lester.youmao.fragment.Fragment_jifen;
import com.lester.youmao.fragment.Fragment_home;
import com.lester.youmao.fragment.Fragment_me;
import com.lester.youmao.fragment.Fragment_shopcart;
import com.lester.youmao.userinfo.UserInfo;
import com.lester.youmao.userinfo.mUserInfo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.WindowManager;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainActivity extends FragmentActivity {
	
	public static RadioGroup mRadioGroup;
	private FragmentManager mManager;
	
	private Fragment mF1;//首页
	private Fragment mF2;//积分商城
	private Fragment mF3;//购物车
	private Fragment mF4;//我的
	
	public WindowManager wm;
	public static int width;
	public static int height;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		wm = this.getWindowManager();
		width = wm.getDefaultDisplay().getWidth();
		height = wm.getDefaultDisplay().getHeight();
		
		initDate();
		initViews();
		
	}
	
	private void initDate() {

		mManager = getSupportFragmentManager();

		mF1 = new Fragment_home();
		mF2 = new Fragment_jifen();
		mF3 = new Fragment_shopcart();
		mF4 = new Fragment_me();
		
	}

	private void initViews() {
		FragmentTransaction transaction = mManager.beginTransaction();
		transaction.replace(R.id.layout, mF1);
		transaction.commit();
		
		mRadioGroup = (RadioGroup) findViewById(R.id.radiogroup);
		mRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				FragmentTransaction transaction = mManager.beginTransaction();
				switch (checkedId) {
				case R.id.rb_main_01:
					transaction.replace(R.id.layout, mF1);
					break;
				case R.id.rb_main_02:
					transaction.replace(R.id.layout, mF2);
					break;
				case R.id.rb_main_03:
					transaction.replace(R.id.layout, mF3);
					break;
				case R.id.rb_main_04:
					transaction.replace(R.id.layout, mF4);
					break;
				}
				transaction.commit();
			}
		});
		
	}

	
}

