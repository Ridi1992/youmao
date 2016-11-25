package com.lester.youmao;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebView.HitTestResult;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class WebLinkActivity extends Activity implements OnClickListener {

	private TextView mTitle;
	private ImageView mBack;

	private WebView mWeb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.web_link);

		initTop();

		Intent intent = getIntent();
		String url = intent.getStringExtra("web");
		if (!url.isEmpty()) {
			initView(url);
		} else {
			Toast.makeText(WebLinkActivity.this, "没有链接", Toast.LENGTH_LONG).show();
		}
	}

	private void initTop() {
		mTitle = (TextView) findViewById(R.id.top_title);
		mBack = (ImageView) findViewById(R.id.top_back);
		if (getIntent().getStringExtra("title") != null) {
			mTitle.setText(getIntent().getStringExtra("title"));
		}else {
			mTitle.setText("");
		}
		mBack.setImageResource(R.drawable.back);
		mBack.setOnClickListener(this);

	}

	private void initView(String url) {

		mWeb = (WebView) findViewById(R.id.web_taobao);
		mWeb.getSettings().setJavaScriptEnabled(true);
		mWeb.getSettings().setSupportZoom(true);
		mWeb.getSettings().setBuiltInZoomControls(true);

		mWeb.setWebViewClient(new WebViewClient() {

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// TODO Auto-generated method stub
				view.loadUrl(url);
				return true;
			}
		});

		mWeb.loadUrl(url);

		mWeb.setWebViewClient(new WebViewClient() {
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}

			@SuppressLint("NewApi")
			@Override
			public WebResourceResponse shouldInterceptRequest(WebView view,String url) {
				if (url.startsWith("http") || url.startsWith("https")) {
					return super.shouldInterceptRequest(view, url);
				} else {
					Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
					startActivity(in);
					return null;
				}
			}
		});
	}

//	class NewsClient extends WebViewClient {
//
//		@Override
//		public boolean shouldOverrideUrlLoading(WebView view, String url) {
//			HitTestResult hit = mWeb.getHitTestResult();
//			int hitType = hit.getType();
//			if (hitType == HitTestResult.SRC_ANCHOR_TYPE) {
//				return true;
//			} else if (hitType == 0) {
//				return true;
//			} else {
//				return false;
//			}
//			// view.loadUrl(url);
//			// return true;
//		}
//
//	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.top_back:
			finish();
			break;
		}
	}

}
