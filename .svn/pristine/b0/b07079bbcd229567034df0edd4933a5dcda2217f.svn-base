package com.lester.youmao.me;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import com.bset.tool.Toast;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.lester.youmao.R;
import com.lester.youmao.adapter.NoticeAdapter;
import com.lester.youmao.adapter.PointsAdapter;
import com.lester.youmao.entity.Notice;
import com.lester.youmao.entity.Points;
import com.lester.youmao.userinfo.mUserInfo;
import com.sanmi.http.Constants;
import com.sanmi.http.JsonUtil;
import com.sanmi.http.PublicRequest;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class PointsListActivity extends Activity{
	
	private TextView mTitle;
	private ImageView mBack;
	
	private ListView mListView;
	private ArrayList<Points> mList;
	private PointsAdapter mAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.me_pointlist);
		
		PublicRequest http = new PublicRequest(mHandler);
		http.PointList(PointsListActivity.this,mUserInfo.GetUserInfo(PointsListActivity.this).getUser().getUser_id());
		
		initView();
	}
	
	private void initView() {
		mTitle = (TextView) findViewById(R.id.top_title);
		mTitle.setText("积分详情");
		mBack = (ImageView) findViewById(R.id.top_back);
		mBack.setImageResource(R.drawable.back);
		mBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		mListView = (ListView) findViewById(R.id.points_lv);
	}

	Handler mHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			try {
				switch (msg.what) {
				case Constants.POINTS_LIST:
					JSONObject jsonObj = new JSONObject(msg.obj.toString());
					if (jsonObj.getString("code").equals("1")) {
						String jsonData = jsonObj.getString("data");
						mList = JsonUtil.instance().fromJson(jsonData,new TypeToken<ArrayList<Points>>() {}.getType());
						if (mList != null) {
							mAdapter = new PointsAdapter(PointsListActivity.this, mList);
							mListView.setAdapter(mAdapter);
						}
					} else {
						Toast.ToastMe(PointsListActivity.this,jsonObj.getString("message"));
					}
					break;
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
		};
	};

}
