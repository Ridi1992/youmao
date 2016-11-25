package com.lester.youmao.adapter;

import java.util.ArrayList;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class WZAdapter extends PagerAdapter {
	
	private ArrayList<View> mViewList;
	
	public WZAdapter(ArrayList<View> list){
		mViewList = list;
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
		
		int index = position%mViewList.size();
		View view = mViewList.get(index);
		container.addView(view);
		
		return view;
	}
	
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		View view = mViewList.get(position);
		
		container.removeView(view);
		
	}
	

}
