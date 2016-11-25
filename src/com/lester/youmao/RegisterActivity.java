package com.lester.youmao;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;

import com.bset.tool.Texttool;
import com.bset.tool.Toast;
import com.google.gson.reflect.TypeToken;
import com.lester.youmao.entity.GetCode;
import com.sanmi.http.Constants;
import com.sanmi.http.JsonUtil;
import com.sanmi.http.PublicRequest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings.Secure;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class RegisterActivity extends Activity implements OnClickListener {

	private ImageView mBack;
	private EditText mPhone;
	private EditText mCode;
	private EditText mPassWord;
	private EditText mPassWord2;
	private TextView mGetCode;
	private Button mSubmit;
	private CheckBox mRule;
	private TextView mRuletv;

	private String mobilec;
	private int mobile_codec;

	private final String PHONE = "^(((13[0-9])|(15([0-9]))|(18[0-9])|(17[0-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$";

	private static int s = 60;
	private Handler mHandlerAll = new Handler() {
	};// 全局handler

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);

		initView();
	}

	private void initView() {
		mBack = (ImageView) findViewById(R.id.back);
		mPhone = (EditText) findViewById(R.id.register_tel);
		mCode = (EditText) findViewById(R.id.register_code);
		mPassWord = (EditText) findViewById(R.id.register_pwd);
		mPassWord2 = (EditText) findViewById(R.id.register_pwd2);
		mGetCode = (TextView) findViewById(R.id.register_getcode);
		mSubmit = (Button) findViewById(R.id.register_submit);
		mBack.setOnClickListener(this);
		mGetCode.setOnClickListener(this);
		mSubmit.setOnClickListener(this);
		mRule = (CheckBox) findViewById(R.id.register_rule);
		mRuletv = (TextView) findViewById(R.id.register_tv);
		mRuletv.setOnClickListener(this);

	}

	/*
	 * 同步信息到新生活卡
	 */
	private String[] sparray ;
	private void updataToNL(final String AppId, final String Password,
			final String code) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				String id = Secure.getString(getContentResolver(),
						Secure.ANDROID_ID);
				HashMap<String, Object> propertyMap = new HashMap<String, Object>();
				propertyMap.put("msg", "5026|" + AppId + "||" + Password + "|"
						+ id + "|01|");
				String result = WebServiceVisitor.callWebService("bizProcess",
						propertyMap);
//				Log.i("aaaaa", "==" + result);
				String splace = result.replace("|", ",");
//				System.out.println("s=" + splace);
				sparray = new String[] {};
				sparray = splace.split(",");
//				for (int i = 0; i < sparray.length; i++) {
//					System.out.println("sparray["+i+"]=" + sparray[i]);
//				}
				
//				System.out.println("key=" + key);
				
				runOnUiThread(new Runnable() {
					public void run() {
						if (sparray[2].equals("000000")) {//成功
								getRegister(AppId, code, mobilec, mobile_codec + "",
									Password,sparray[3]);
						}else if (sparray[2].equals("000001")) {//已经注册过
								Toast.ToastMe(RegisterActivity.this,
									"你已经注册过了");
						}else {//注册失败999999
								Toast.ToastMe(RegisterActivity.this,
									"注册失败");
						}
					}
				});
			}
		}).start();
	}

	Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			try {
				switch (msg.what) {
				case Constants.GETCODE:
					JSONObject jsonObj = new JSONObject(msg.obj.toString());
					if (jsonObj.getString("code").equals("1")) {
						String jsonData = jsonObj.getString("data");
						GetCode data = JsonUtil.instance().fromJson(jsonData,
								new TypeToken<GetCode>() {
								}.getType());
						if (data != null) {
							mobilec = data.getMobile();
							mobile_codec = data.getMobile_code();
						}
					} else {
						Toast.ToastMe(RegisterActivity.this,
								jsonObj.getString("message"));
					}
					break;
				case Constants.REGISTER:
					JSONObject jsonObj2 = new JSONObject(msg.obj.toString());
					if (jsonObj2.getString("code").equals("1")) {
						Toast.ToastMe(RegisterActivity.this,
								jsonObj2.getString("message"));
						finish();
					} else {
						Toast.ToastMe(RegisterActivity.this,
								jsonObj2.getString("message"));
					}
					break;
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		};
	};

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.back:
			this.finish();
			break;
		case R.id.register_tv:
			startActivity(new Intent(RegisterActivity.this,
					RegisterRuleActivity.class));
			break;
		case R.id.register_getcode:
			PublicRequest http = new PublicRequest(mHandler);
			String phoneNumber = Texttool.Gettext(RegisterActivity.this,
					R.id.register_tel);
			Pattern regex = Pattern.compile(PHONE);
			Matcher matcher = regex.matcher(phoneNumber);
			boolean flagPhone = matcher.matches();
			if (flagPhone) {
				new Thread(new ClassCut()).start();// 开启倒计时
				http.getCode(RegisterActivity.this, phoneNumber);
			} else {
				Toast.ToastMe(RegisterActivity.this, "请输入正确手机号");
			}
			break;
		case R.id.register_submit:
			if (yanzheng()) {
				if (mRule.isChecked()) {
					v.setClickable(false);
					updataToNL(Texttool.Gettext(RegisterActivity.this,
							R.id.register_tel), Texttool.Gettext(
							RegisterActivity.this, R.id.register_pwd),
							Texttool.Gettext(RegisterActivity.this,
									R.id.register_code));
				} else {
					Toast.ToastMe(RegisterActivity.this, "请同意注册规则");
				}
			} else {
				Toast.ToastMe(RegisterActivity.this, "请完善信息");
			}
			break;
		}
	}

	private void getRegister(String mobile, String mobile_code, String mobilec,
			String mobile_codec, String password, String key) {
		PublicRequest http = new PublicRequest(mHandler);
		http.Register(RegisterActivity.this, mobile, mobile_code, mobilec,
				mobile_codec, password,key);
	}

	public boolean yanzheng() {
		String phoneNumber = mPhone.getText().toString();
		String yanZhen = mCode.getText().toString();
		String mPassword = mPassWord.getText().toString();
		String mPassword2 = mPassWord2.getText().toString();
		if (phoneNumber.isEmpty() || yanZhen.isEmpty() || mPassword.isEmpty()
				|| mPassword2.isEmpty()) {
			return false;
		}
		if (!mPassword.equals(mPassword2)) {
			Toast.ToastMe(RegisterActivity.this, "两次输入密码不一致");
			return false;
		}
		return true;
	}

	class ClassCut implements Runnable {// 倒计时逻辑子线程
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (s > 0) {// 整个倒计时执行的循环
				s--;
				mHandlerAll.post(new Runnable() {// 通过它在UI主线程中修改显示的剩余时间
							@Override
							public void run() {
								// TODO Auto-generated method stub
								mGetCode.setText(" " + s + "秒");// 显示剩余时间
								mGetCode.setBackgroundColor(getResources()
										.getColor(R.color.hui_66));
								mGetCode.setClickable(false);
							}
						});
				try {
					Thread.sleep(1000);// 线程休眠一秒钟 这个就是倒计时的间隔时间
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			// 下面是倒计时结束逻辑
			mHandlerAll.post(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					mGetCode.setText("获取验证码");// 一轮倒计时结束 修改剩余时间为一分钟
					mGetCode.setBackgroundColor(getResources().getColor(
							R.color.second_color));
					mGetCode.setClickable(true);
					// Toast.makeText(MainActivity.this, "倒计时完成",
					// Toast.LENGTH_LONG).show();//提示倒计时完成
				}
			});
			s = 60;// 修改倒计时剩余时间变量为60秒
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		s = 60;
	}

}
