package com.lester.youmao.me;

import org.json.JSONException;
import org.json.JSONObject;

import com.bset.tool.Texttool;
import com.bset.tool.Toast;
import com.lester.youmao.R;
import com.sanmi.http.Constants;
import com.sanmi.http.PublicRequest;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class FeedBackActivity extends Activity{
	
	private TextView mTitle;
	private ImageView mBack;
	
	private EditText mName;
	private EditText mPone;
	private EditText mContent;
	private	Button mSubmit;
	private TextView mKefu;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.me_fankui);
		
		initView();
	}

	private void initView() {
		mTitle = (TextView) findViewById(R.id.top_title);
		mTitle.setText("反馈");
		mBack = (ImageView) findViewById(R.id.top_back);
		mBack.setImageResource(R.drawable.back);
		mBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		mName = (EditText) findViewById(R.id.fankui_name);
		mPone = (EditText) findViewById(R.id.fankui_tel);
		mContent = (EditText) findViewById(R.id.fankui_content);
		mSubmit = (Button) findViewById(R.id.fankui_submit);
		mSubmit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (yanzheng()) {
					PublicRequest http = new PublicRequest(mHandler);
					http.FeedBack(FeedBackActivity.this, Texttool.Gettext(mName), Texttool.Gettext(mPone), Texttool.Gettext(mContent));
				}
			}
		});
		mKefu = (TextView) findViewById(R.id.fankui_tv);
		Texttool.setText(mKefu, "客服电话：4008-636-018\n"
+"微信订阅号：zhimingshangye\n"
+"客服服务中心工作时间：周一至周六9:00-18:00（春节法定假日除外）\n"
+"如果您对盛榕商城的产品有任何疑问，或对我们的服务有任何意见或建议，"
+" 欢迎您直接与我们联络，我们将竭诚为您服务。");
	}
	
	private boolean yanzheng() {
		if (!Texttool.Havecontent(mName)) {
			Toast.ToastMe(FeedBackActivity.this, "请输入姓名");
			return false;
		}else if (!Texttool.Havecontent(mPone)) {
			Toast.ToastMe(FeedBackActivity.this, "请输入电话");
			return false;
		}else if (!Texttool.Havecontent(mContent)) {
			Toast.ToastMe(FeedBackActivity.this, "请输入反馈内容");
			return false;
		}
		return true;
	}
	
	Handler mHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			try {
				switch (msg.what) {
				case Constants.ME_FEEDBACK:
					JSONObject jsonObj2 = new JSONObject(msg.obj.toString());
					if (jsonObj2.getString("code").equals("1")) {
						Toast.ToastMe(FeedBackActivity.this,jsonObj2.getString("message"));
						finish();
					} else {
						Toast.ToastMe(FeedBackActivity.this,jsonObj2.getString("message"));
					}
					break;
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		};
	};
}
