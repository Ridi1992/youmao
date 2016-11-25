package com.lester.youmao.jifen;


import com.bset.tool.Texttool;
import com.lester.youmao.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class JifenRuleActivity extends Activity{
	
	private TextView mTitle;
	private ImageView mBack;
	private TextView mContent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_rule);
		
		initView();
	}

	private void initView() {
		mTitle = (TextView) findViewById(R.id.top_title);
		mTitle.setText("积分规则");
		mBack = (ImageView) findViewById(R.id.top_back);
		mBack.setImageResource(R.drawable.back);
		mBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		mContent = (TextView) findViewById(R.id.register_rule);
		Texttool.setText(mContent, "积分规则\n\n"
+"积分计算：\n（1）会员在商城消费1元=1个积分（积分消费除外），单笔计算精确到1元，不足1元的地方可忽略不计；会员获得积分后，可以在商城对指定商品进行抵现使用或者兑换指定商品。\n（2）积分计算不包含邮费，如：商品价为100元，邮费20元，消费所得积分为100个分。\n"
+"积分兑换政策：\n"
+"会员可进入积分商城，用所拥有的积分进行兑换相应的商品。积分只可兑换商品，但是不包含邮费（除特别包邮兑换商品除外）。\n"
+"积分抵现政策：\n"
+"（1）积分抵现规则：会员积分账户每10分=0.5元。\n"
+"（2）积分使用抵扣规则：\n"
+"①.积分只可以抵扣商品本身的价格，不可用于抵扣邮费。\n"
+"②.用积分抵扣过的商品在购买之后，不生成新的积分。");
	}
	
}
