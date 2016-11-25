package com.lester.youmao.home;

import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;
import com.bset.tool.Toast;
import com.google.gson.reflect.TypeToken;
import com.lester.youmao.R;
import com.lester.youmao.adapter.Cate1Adapter;
import com.lester.youmao.adapter.Cate2Adapter;
import com.lester.youmao.entity.CateGory;
import com.sanmi.http.Constants;
import com.sanmi.http.JsonUtil;
import com.sanmi.http.PublicRequest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class CategoryActivity extends Activity implements OnItemClickListener{
	
	private TextView mTitle;
	private ImageView mBack;
	
	private ListView mCate1ListView;
	private ArrayList<CateGory> mCate1List = new ArrayList<CateGory>();
	private Cate1Adapter mCate1Adapter;
	
	private ListView mCate2ListView;
	private ArrayList<CateGory> mCate2List = new ArrayList<CateGory>();
	private Cate2Adapter mCate2Adapter;
	
	private String mCat_id;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.category);
		
		if (getIntent().getStringExtra("cat_id") != null) {
			mCat_id = getIntent().getStringExtra("cat_id");
		}
		
		PublicRequest http = new PublicRequest(mHandler);
		http.CatAll(CategoryActivity.this);
		
		initView();
	}

	private void initView() {
		mTitle = (TextView) findViewById(R.id.top_title);
		mTitle.setText("商品分类");
		mBack = (ImageView) findViewById(R.id.top_back);
		mBack.setImageResource(R.drawable.back);
		mBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		mCate1ListView = (ListView) findViewById(R.id.cate_1);
		mCate1Adapter = new Cate1Adapter(CategoryActivity.this, mCate1List);
		mCate1ListView.setAdapter(mCate1Adapter);
		mCate1ListView.setOnItemClickListener(this);
		mCate2ListView = (ListView) findViewById(R.id.cate_2);
		mCate2Adapter = new Cate2Adapter(CategoryActivity.this, mCate2List);
		mCate2ListView.setAdapter(mCate2Adapter);
	}
	
	@SuppressLint("ResourceAsColor")
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		for(int i=0;i<mCate1List.size();i++){
			mCate1List.get(i).setCheck(false);
		}
		mCate1List.get(position).setCheck(true);
		mCate1Adapter.notifyDataSetChanged();
		
		mCate2List = mCate1List.get(position).getSon();
		mCate2Adapter = new Cate2Adapter(CategoryActivity.this, mCate2List);
		mCate2ListView.setAdapter(mCate2Adapter);
	}

	
	Handler mHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			try {
				switch (msg.what) {
				case Constants.CAT_ALL:
					JSONObject jsonObj = new JSONObject(msg.obj.toString());
					if (jsonObj.getString("code").equals("1")) {
						String jsonData = jsonObj.getString("data");
						mCate1List = JsonUtil.instance().fromJson(jsonData,new TypeToken<ArrayList<CateGory>>() {}.getType());
						if (mCate1List != null) {
							if (mCat_id != null && !mCat_id.equals("")) {
								for (int i = 0; i < mCate1List.size(); i++) {
									if (mCate1List.get(i).getCat_id().equals(mCat_id)) {
										mCate1List.get(i).setCheck(true);
										mCate2List = mCate1List.get(i).getSon();
									}
								}
							}else {
								mCate1List.get(0).setCheck(true);
								mCate2List = mCate1List.get(0).getSon();
							}
								
							mCate2Adapter = new Cate2Adapter(CategoryActivity.this, mCate2List);
							mCate2ListView.setAdapter(mCate2Adapter);
							mCate1Adapter = new Cate1Adapter(CategoryActivity.this, mCate1List);
							mCate1ListView.setAdapter(mCate1Adapter);
						}
					} else {
						Toast.ToastMe(CategoryActivity.this,jsonObj.getString("message"));
					}
					break;
				}
			}catch (JSONException e) {
				e.printStackTrace();
			}
		};
	};

}
