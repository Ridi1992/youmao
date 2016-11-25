package com.lester.youmao.me;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bset.tool.Toast;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lester.youmao.R;
import com.lester.youmao.adapter.NoticeAdapter;
import com.lester.youmao.entity.Notice;
import com.lester.youmao.userinfo.mUserInfo;
import com.sanmi.http.Constants;
import com.sanmi.http.JsonUtil;
import com.sanmi.http.PublicRequest;

public class NoticeActivity extends Activity{
	
	private TextView mTitle;
	private ImageView mBack;
	
	private ListView mListView;
	private ArrayList<Notice> mList;
	private NoticeAdapter mAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.me_notice);
		
		PublicRequest http = new PublicRequest(mHandler);
		http.MeNotice(NoticeActivity.this, mUserInfo.GetUserInfo(NoticeActivity.this).getUser().getUser_id());
		
		initView();

	}
	
	private void initView() {
		mTitle = (TextView) findViewById(R.id.top_title);
		mTitle.setText("消息通知");
		mBack = (ImageView) findViewById(R.id.top_back);
		mBack.setImageResource(R.drawable.back);
		mBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		mListView = (ListView) findViewById(R.id.notice_lv);
		mListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = new Intent();
				intent.putExtra("title", mList.get(arg2).getTitle());
				intent.putExtra("name", mList.get(arg2).getUser_name());
				intent.putExtra("time", mList.get(arg2).getAdd_time());
				intent.putExtra("content", mList.get(arg2).getContent());
				startActivity(intent.setClass(NoticeActivity.this, NoticeDetailActivity.class));
			}
		});
	}

	Handler mHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			try {
				switch (msg.what) {
				case Constants.ME_NOTICE:
					JSONObject jsonObj = new JSONObject(msg.obj.toString());
					if (jsonObj.getString("code").equals("1")) {
						String jsonData = jsonObj.getString("data");
						mList = JsonUtil.instance().fromJson(jsonData,new TypeToken<ArrayList<Notice>>() {}.getType());
						if (mList != null) {
							mAdapter = new NoticeAdapter(NoticeActivity.this, mList);
							mListView.setAdapter(mAdapter);
						}
					} else {
						Toast.ToastMe(NoticeActivity.this,jsonObj.getString("message"));
					}
					break;
				}
			}catch (JSONException e) {
				e.printStackTrace();
			}
		};
	};

}
