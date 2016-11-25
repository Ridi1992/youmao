package com.lester.youmao;

import org.json.JSONObject;

import com.bset.tool.Texttool;
import com.bset.tool.Toast;
import com.google.gson.reflect.TypeToken;
import com.lester.youmao.entity.GetCode;
import com.sanmi.http.Constants;
import com.sanmi.http.JsonUtil;
import com.sanmi.http.PublicRequest;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ForgetActivity extends Activity implements OnClickListener{
	
	private TextView mTitle;
	private ImageView mBack;
	
	private EditText mMobile;
	private EditText mCode;
	private EditText mNewPassword;
	private EditText mNewPassword2;
	private Button mGetCode;
	private Button mSubmit;
	
	private String mobilec;
	private int mobile_codec;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.forget_password);
		
		initViews();
	}
	
	private void initViews() {
		mTitle = (TextView) findViewById(R.id.top_title);
		mTitle.setText("忘记密码");
		mBack = (ImageView) findViewById(R.id.top_back);
		mBack.setImageResource(R.drawable.back);
		mBack.setOnClickListener(this);
		
		mMobile = (EditText) findViewById(R.id.forget_tel);
		mCode = (EditText) findViewById(R.id.forget_code);
		mNewPassword = (EditText) findViewById(R.id.forget_newpassword);
		mNewPassword2 = (EditText) findViewById(R.id.forget_newpassword2);
		mGetCode = (Button) findViewById(R.id.forget_getcode);
		mGetCode.setOnClickListener(this);
		mSubmit = (Button) findViewById(R.id.forget_submit);
		mSubmit.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.top_back:
			finish();
			break;
		case R.id.forget_getcode:
			if (!Texttool.Havecontent(mMobile)) {
				Toast.ToastMe(ForgetActivity.this, "请输入手机号");
			}else {
				PublicRequest http = new PublicRequest(mHandler);
				http.ForgetSend(ForgetActivity.this, Texttool.Gettext(mMobile));
			}
			break;
		case R.id.forget_submit:
			if (!Texttool.Havecontent(mMobile)) {
				Toast.ToastMe(ForgetActivity.this, "请输入手机号");
			}else if (!Texttool.Havecontent(mCode)) {
				Toast.ToastMe(ForgetActivity.this, "请输入验证码");
			}else if (!Texttool.Havecontent(mNewPassword)) {
				Toast.ToastMe(ForgetActivity.this, "请输入新密码");
			}else if (!Texttool.Havecontent(mNewPassword2)) {
				Toast.ToastMe(ForgetActivity.this, "请确认新密码");
			}else if (!Texttool.Gettext(mNewPassword).equals(Texttool.Gettext(mNewPassword2))) {
				Toast.ToastMe(ForgetActivity.this, "两次密码输入不一致，请确认");
			}else {
				PublicRequest http = new PublicRequest(mHandler);
				http.ForgetCheck(ForgetActivity.this, Texttool.Gettext(mMobile), mobilec, Texttool.Gettext(mCode), mobile_codec+"", Texttool.Gettext(mNewPassword));
			}
			break;
		}
	}
	
	Handler mHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			try {
				switch (msg.what) {
				case Constants.PASSWORD_SEND:
					JSONObject jsonObj = new JSONObject(msg.obj.toString());
					if (jsonObj.getString("code").equals("1")) {
						String jsonData = jsonObj.getString("data");
						GetCode data = JsonUtil.instance().fromJson(jsonData,new TypeToken<GetCode>() {
								}.getType());
						if (data != null) {
							mobilec = data.getMobile();
							mobile_codec = data.getMobile_code();
						}
					} else {
						Toast.ToastMe(ForgetActivity.this,jsonObj.getString("message"));
					}
					break;
				case Constants.PASSWORD_CHECK:
					JSONObject jsonObj1 = new JSONObject(msg.obj.toString());
					if (jsonObj1.getString("code").equals("1")) {
						Toast.ToastMe(ForgetActivity.this,jsonObj1.getString("message"));
						finish();
					} else {
						Toast.ToastMe(ForgetActivity.this,jsonObj1.getString("message"));
					}
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		};
	};
	
}
