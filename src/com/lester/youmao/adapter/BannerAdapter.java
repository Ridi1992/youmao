package com.lester.youmao.adapter;

import java.util.ArrayList;

import com.lester.youmao.fragment.Fragment_home;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class BannerAdapter extends PagerAdapter {
	private int count;
	private ArrayList<View> mViewList;
	private Fragment_home mContextHome;
	
	public BannerAdapter(ArrayList<View> list,Fragment_home c,ArrayList<String> links){
		mViewList = list;
		this.count=mViewList.size();
		mContextHome = c;
	}

	@Override
	public int getCount() {
		if (mViewList != null) {
			return mViewList.size();
		}
		return 0;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}
	
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		
		View view = mViewList.get(position % count);
		container.addView(view);
		
		mContextHome.ToGoodsDetail(view);
		
		return view;
	}
	
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		View view = mViewList.get(position%count);
		
		container.removeView(view);
		
	}
	

}
