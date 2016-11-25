package com.lester.youmao.home;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONException;
import org.json.JSONObject;

import com.bset.tool.ListUtil;
import com.bset.tool.Toast;
import com.google.gson.reflect.TypeToken;
import com.lester.youmao.MainActivity;
import com.lester.youmao.R;
import com.lester.youmao.adapter.HomeBestAdapter;
import com.lester.youmao.adapter.HomePromoteAdapter;
import com.lester.youmao.entity.HomePromote;
import com.sanmi.http.Constants;
import com.sanmi.http.JsonUtil;
import com.sanmi.http.PublicRequest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PromoteMoreActivity extends Activity implements OnItemClickListener{
	
	private TextView mTitle;
	private ImageView mBack;
	
	private GridView mGridView;
	private HomePromoteAdapter mAdapter;
	private ArrayList<HomePromote> mList;
	
	private Timer timer = new Timer();
	private TimerTask timerTask;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.promote_more);
		
		PublicRequest http = new PublicRequest(mHandler);
		http.HomePromoteMore(PromoteMoreActivity.this);
		
		initView();
	}

	private void initView() {
		mTitle = (TextView) findViewById(R.id.top_title);
		mTitle.setText("限时秒杀");
		mBack = (ImageView) findViewById(R.id.top_back);
		mBack.setImageResource(R.drawable.back);
		mBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		ImageView mImageView = (ImageView) findViewById(R.id.promote_more_img);
		LinearLayout.LayoutParams Params = (LinearLayout.LayoutParams) mImageView.getLayoutParams();
		Params.width = MainActivity.width;
		Params.height = MainActivity.width*2/5;
		
		mGridView = (GridView) findViewById(R.id.promote_more_grid);
		mAdapter = new HomePromoteAdapter(PromoteMoreActivity.this, mList);
		mGridView.setAdapter(mAdapter);
		mGridView.setOnItemClickListener(this);
		
	}
	

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		Intent intent = new Intent();
		intent.putExtra("goods_id", mList.get(arg2).getGoods_id());
		intent.setClass(PromoteMoreActivity.this, GoodsDetailActivity.class);
		startActivity(intent);
		
	}
	
	Handler mHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			try {
				switch (msg.what) {
				case Constants.HOME_PROMOTE_MORE:
					JSONObject jsonObj = new JSONObject(msg.obj.toString());
					if (jsonObj.getString("code").equals("1")) {
						String jsonData = jsonObj.getString("data");
						mList = JsonUtil.instance().fromJson(jsonData,new TypeToken<ArrayList<HomePromote>>() {}.getType());
						if (mList != null) {
							mAdapter = new HomePromoteAdapter(PromoteMoreActivity.this, mList);
							mGridView.setAdapter(mAdapter);
							timerTask = new TimerTask() {
								 
								@Override
								public void run() {
								Message message = Message.obtain();
								message.what = 1000;
								mHandler.sendMessage(message);
								}
								};
								timer.schedule(timerTask, 1000,1000);
						}
					} else {
						Toast.ToastMe(PromoteMoreActivity.this,jsonObj.getString("message"));
					}
					break;
				case 1000:
						for (int i = 0; i < mList.size(); i++) {
							if(mList.get(i).getPromote_end_date() != 0){
								mList.get(i).setPromote_end_date(mList.get(i).getPromote_end_date()-1);//这是服务器当前时间，以此时间为基数进行倒时计，因为服务器时间以秒为单位返回，所以我们每隔1秒，把当前服务器时间+1，也可以把结束时间-1
							}else{
								mList.get(i).setPromote_end_date(0);
							}
							mAdapter.notifyDataSetChanged();
						}
					}
			}  catch (JSONException e) {
				e.printStackTrace();
			}
		};
	};
	
	@Override
	protected void onDestroy() {
	if (null != timer) {
	timer.cancel();
	}
	super.onDestroy();
	}
	
}
