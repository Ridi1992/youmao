package com.lester.youmao.me;

import com.bset.tool.Texttool;
import com.lester.youmao.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class AboutActivity extends Activity{
	
	private TextView mTitle;
	private ImageView mBack;
	
	private TextView Content;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.me_about);
		
		initView();
	}

	private void initView() {
		mTitle = (TextView) findViewById(R.id.top_title);
		mTitle.setText("关于我们");
		mBack = (ImageView) findViewById(R.id.top_back);
		mBack.setImageResource(R.drawable.back);
		mBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		Content = (TextView) findViewById(R.id.about_content);
		Texttool.setText(Content, "福州盛榕（联合）电子商务有限公司（以下简称“悠贸电商”）是福州智名商业管理有限公司旗下的重要分公司之一。在智名商业管理有限公司的带领下悠贸电商成为对外宣传的重要窗口，展示实体项目的坚实阵地，更致力于开拓网络无形市场，努力营造有形市场与无形网络融合发展、共同繁荣的新格局。\n与线下实体平台经过不断探索和发展，悠贸电商成为了打造市场品牌、提升企业形象的助推器，更自主开发囊括了信息功能、支付功能、物流功能、交易功能的网上综合购物平台。\n悠贸电商打造独立运营、物流统一配送的网络大平台，集传统交易、电子交易、展览展示、咨询互动为一体，实现了虚拟经济和实体经济、线上电子商务平台相融合，以积极加快推动信息技术的运用，开辟了一条更为快捷、有效的营销途径。\n悠贸电商把有品牌、有优势、有服务的商品汇聚到网上，让顾客能够足不出户就能买到方便、实惠和安心的商品，满足顾客对生活的更多需求。销售平台目前主要覆盖福州、厦门、泉州地区，通过专业的技术团队、24小时客服、线上线下商业联动，统一的物流配送来确保消费者的购物速度和权益，给消费者带来不一样的购物体验，尽享网上购物的便捷与安心。");
	}
	
}
