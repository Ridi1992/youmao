package com.lester.youmao;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import cn.sharesdk.framework.ShareSDK;

public class WelcomActivity extends Activity {

	public static String code;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.welcome);

		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				SharedPreferences shared = getSharedPreferences("user", 0);
				boolean b = shared.getBoolean("welcome", false);
				if (b) {
					Intent intent = new Intent();
					intent.setClass(WelcomActivity.this, MainActivity.class);
					startActivity(intent);
					finish();
				} else {
					Editor editor = shared.edit();
					editor.putBoolean("welcome", true);
					editor.commit();
					Intent intent = new Intent();
					intent.setClass(WelcomActivity.this, WZActivity.class);
					startActivity(intent);
					finish();
				}
			}
		}, 3000);

	}

}
