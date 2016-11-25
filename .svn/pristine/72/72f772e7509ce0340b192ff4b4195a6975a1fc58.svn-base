package com.lester.youmao.home;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import com.bset.tool.Toast;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.lester.youmao.R;
import com.lester.youmao.adapter.Cate1Adapter;
import com.lester.youmao.adapter.Cate2Adapter;
import com.lester.youmao.adapter.CommentAdapter;
import com.lester.youmao.entity.CateGory;
import com.lester.youmao.entity.GoodsComment;
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

public class GoodsCommentsActivity extends Activity{
	
	private TextView mTitle;
	private ImageView mBack;
	
	private PullToRefreshListView mRefreshListView;
	private ArrayList<GoodsComment> mList = new ArrayList<GoodsComment>();
	private CommentAdapter mAdapter;
	
	private int page = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.goods_comment);
		
		sendHttp();
		
		initView();
	}

	private void initView() {
		mTitle = (TextView) findViewById(R.id.top_title);
		mTitle.setText("用户评论");
		mBack = (ImageView) findViewById(R.id.top_back);
		mBack.setImageResource(R.drawable.back);
		mBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		mRefreshListView = (PullToRefreshListView) findViewById(R.id.comments_list);
		mAdapter = new CommentAdapter(GoodsCommentsActivity.this, mList);
		mRefreshListView.setAdapter(mAdapter);
		mRefreshListView.setOnRefreshListener(new OnRefreshListener<ListView>() {
			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				page = page + 1;
				sendHttp();
			}
		});
	}
	
	private void sendHttp(){
		PublicRequest http = new PublicRequest(mHandler);
		http.GoodsComment(GoodsCommentsActivity.this, getIntent().getStringExtra("goods_id"), page+"");
	}
	
	Handler mHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			try {
				switch (msg.what) {
				case Constants.GOODS_COMMENT:
					JSONObject jsonObj = new JSONObject(msg.obj.toString());
					if (jsonObj.getJSONObject("status").getString("succeed").equals("1")) {
						String jsonData = jsonObj.getString("data");
						ArrayList<GoodsComment> mList2 = new ArrayList<GoodsComment>();
						mList2 = JsonUtil.instance().fromJson(jsonData,new TypeToken<ArrayList<GoodsComment>>() {}.getType());
						if (mList2 != null) {
							mList.addAll(mList2);
							mAdapter = new CommentAdapter(GoodsCommentsActivity.this, mList);
							mRefreshListView.setAdapter(mAdapter);
							if (mList.size() < 30) {
								mRefreshListView.setPullToRefreshEnabled(false);
								Toast.ToastMe(GoodsCommentsActivity.this, "没有更多了");
							}
						}
					} else {
						Toast.ToastMe(GoodsCommentsActivity.this,jsonObj.getJSONObject("status").getString("error_desc"));
					}
					break;
				}
			}catch (JSONException e) {
				e.printStackTrace();
			}
		};
	};
	
}
