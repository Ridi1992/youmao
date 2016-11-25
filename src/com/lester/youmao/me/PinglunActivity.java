package com.lester.youmao.me;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONObject;

import com.bset.tool.Texttool;
import com.bset.tool.Toast;
import com.lester.youmao.R;
import com.lester.youmao.userinfo.mUserInfo;
import com.sanmi.http.Constants;
import com.sanmi.http.PublicRequest;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class PinglunActivity extends Activity implements OnClickListener{
	private TextView mTitle;
	private ImageView mBack;
	
	private EditText mMessage;
	private Button mSubmit;
	
	private String goods_id;
	private String order_id;
//	private RatingBar mRatingBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.comment);
		
		Intent intent = getIntent();
		goods_id = intent.getStringExtra("goods_id");
		order_id = intent.getStringExtra("order_id");
		
		initViews();
	}
	
	private void initViews() {
		mTitle = (TextView) findViewById(R.id.top_title);
		mTitle.setText("商品评论");
		mBack = (ImageView) findViewById(R.id.top_back);
		mBack.setImageResource(R.drawable.back);
		mBack.setOnClickListener(this);
		mMessage = (EditText) findViewById(R.id.ordercomment_content);
		mSubmit = (Button) findViewById(R.id.ordercomment_submit);
		mSubmit.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.top_back:
			finish();
			break;
		case R.id.ordercomment_submit:
			if (!Texttool.Havecontent(mMessage)) {
				Toast.ToastMe(PinglunActivity.this, "请输入评价内容");
			}else {
				PublicRequest http = new PublicRequest(mHandler);
				http.OrderComment(PinglunActivity.this, mUserInfo.GetUserInfo(PinglunActivity.this).getUser().getUser_id(), goods_id, Texttool.Gettext(mMessage), order_id);
			}
			break;
		}
	}

	private Handler mHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			try {
				switch (msg.what) {
				case Constants.ORDER_COMMENT:
					JSONObject jsonObj = new JSONObject(msg.obj.toString());
					if (jsonObj.getString("code").equals("1")) {
						Toast.ToastMe(PinglunActivity.this,jsonObj.getString("message"));
						finish();
					} else {
						Toast.ToastMe(PinglunActivity.this,jsonObj.getString("message"));
					}
					break;
				case 404:
					Toast.ToastMe(PinglunActivity.this, msg.obj.toString());
					break;
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	};

}
