package com.lester.youmao.me;

import com.bset.tool.Texttool;
import com.lester.youmao.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class NoticeDetailActivity extends Activity{
	
	private TextView mTitle;
	private ImageView mBack;
	
	private TextView mNoticeTitle;
	private TextView mName;
	private TextView mAddTime;
	private TextView mContent;
	
	private String NoticeT;
	private String NoticeN;
	private String NoticeA;
	private String NoticeC;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.me_notice_detail);
		
		NoticeT = getIntent().getStringExtra("title");
		NoticeN = getIntent().getStringExtra("name");
		NoticeA = getIntent().getStringExtra("time");
		NoticeC = getIntent().getStringExtra("content");
		
		initView();
	}
		
		private void initView() {
			mTitle = (TextView) findViewById(R.id.top_title);
			mTitle.setText("消息通知");
			mBack = (ImageView) findViewById(R.id.top_back);
			mBack.setImageResource(R.drawable.back);
			mBack.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					finish();
				}
			});
			mNoticeTitle = (TextView) findViewById(R.id.notice_detail_title);
			mName = (TextView) findViewById(R.id.notice_detail_name);
			mAddTime = (TextView) findViewById(R.id.notice_detail_time);
			mContent = (TextView) findViewById(R.id.notice_detail_content);
			
			Texttool.setText(mNoticeTitle, NoticeT);
			Texttool.setText(mName, NoticeN);
			Texttool.setText(mAddTime, NoticeA);
			Texttool.setText(mContent, NoticeC);
	}

}
