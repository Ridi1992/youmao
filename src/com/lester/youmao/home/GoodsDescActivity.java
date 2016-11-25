package com.lester.youmao.home;

import com.lester.youmao.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

public class GoodsDescActivity extends Activity{
	
	private TextView mTitle;
	private ImageView mBack;
	private WebView mDescWeb;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.goods_desc);
		
		String data = getIntent().getStringExtra("desc");
		Log.i("adasda", data);
		
		mTitle = (TextView) findViewById(R.id.top_title);
		mTitle.setText("商品详情");
		mBack = (ImageView) findViewById(R.id.top_back);
		mBack.setImageResource(R.drawable.back);
		mBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		mDescWeb = (WebView) findViewById(R.id.goods_detail_descweb);
		
		mDescWeb.getSettings().setDefaultTextEncodingName("utf-8");
		mDescWeb.loadData(data, "text/html", "utf-8");
	}

}
