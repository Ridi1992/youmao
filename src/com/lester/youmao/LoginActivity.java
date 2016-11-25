package com.lester.youmao;

import java.util.HashMap;
import org.json.JSONObject;
import com.bset.tool.Texttool;
import com.bset.tool.Toast;
import com.google.gson.reflect.TypeToken;
import com.lester.youmao.userinfo.UserInfo;
import com.lester.youmao.userinfo.mUserInfo;
import com.sanmi.http.Constants;
import com.sanmi.http.JsonUtil;
import com.sanmi.http.PublicRequest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings.Secure;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class LoginActivity extends Activity implements OnClickListener{
	
	private ImageView mBack;
	private EditText mPhone;
	private EditText mPassWord;
	private TextView mRegister;
	private TextView mForget;
	private Button mSubmit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		initViews();
	}

	private void initViews() {
		mBack = (ImageView) findViewById(R.id.back);
		mPhone = (EditText) findViewById(R.id.login_tel);
		mPassWord = (EditText) findViewById(R.id.login_password);
		mRegister = (TextView) findViewById(R.id.login_register);
		mForget = (TextView) findViewById(R.id.login_forget);
		mSubmit = (Button) findViewById(R.id.login_submit);
		
		mBack.setOnClickListener(this);
		mRegister.setOnClickListener(this);
		mForget.setOnClickListener(this);
		mSubmit.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.back:
			this.finish();
			break;
		case R.id.login_register:
			startActivity(intent.setClass(LoginActivity.this, RegisterActivity.class));
			break;
		case R.id.login_forget:
			startActivity(intent.setClass(LoginActivity.this, ForgetActivity.class));
			break;
		case R.id.login_submit:
			if (!Texttool.Havecontent(mPhone)) {
				Toast.ToastMe(LoginActivity.this, "请输入手机号");
			}else if(!Texttool.Havecontent(mPassWord)){
				Toast.ToastMe(LoginActivity.this, "请输入密码");
			}else {
				PublicRequest http=new PublicRequest(handler);
				http.Login(LoginActivity.this, Texttool.Gettext(mPhone), Texttool.Gettext(mPassWord));
			}
			break;
		}
	}
	
	@SuppressLint("HandlerLeak")
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			try {
				switch (msg.what) {
				case Constants.LOGIN:
					JSONObject jsonObj = new JSONObject(msg.obj.toString());
					if(jsonObj.getJSONObject("status").getString("succeed").equals("1")) {
						String jsonData = jsonObj.getString("data");
						UserInfo data=JsonUtil.instance().fromJson(jsonData, new TypeToken<UserInfo>(){}.getType());
						if(data!=null){
							data.setIslogin(true);
							mUserInfo.SaveUserInfo(LoginActivity.this, data);
							Toast.ToastMe(LoginActivity.this, "登录成功");
							startActivity(new Intent(LoginActivity.this, MainActivity.class));
							
							finish();
						}
					}else {
						Toast.ToastMe(getApplicationContext(), jsonObj.getJSONObject("status").getString("error_desc"));
					}
					break;
				case 404:
					Toast.ToastMe(getApplicationContext(), msg.obj.toString());
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		};
	};

}
