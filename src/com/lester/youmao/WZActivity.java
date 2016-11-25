package com.lester.youmao;

import java.util.ArrayList;

import com.lester.youmao.adapter.WZAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

public class WZActivity extends Activity{
	
	private ViewPager mViewPager;
	private WZAdapter mAdapter;
	private ArrayList<View> mViewList;
	private Button mButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.wz_main);
		
		mViewPager = (ViewPager) findViewById(R.id.vp);
		initData();
		
	}

	private void initData() {
		mViewList = new ArrayList<View>();
		LayoutInflater inflater = getLayoutInflater();
		View view1 = inflater.inflate(R.layout.wz_page1, null);
		View view2 = inflater.inflate(R.layout.wz_page2, null);
		View view3 = inflater.inflate(R.layout.wz_page3, null);
//		View view4 = inflater.inflate(R.layout.wz_page4, null);
		mButton = (Button) view3.findViewById(R.id.wz_btn);
		mButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(WZActivity.this, MainActivity.class);
				startActivity(intent);
				finish();
			}
		});
		mViewList.add(view1);
		mViewList.add(view2);
		mViewList.add(view3);
//		mViewList.add(view4);
		
		mAdapter = new WZAdapter(mViewList);
		mViewPager.setAdapter(mAdapter);
		
		
		
	}

}
