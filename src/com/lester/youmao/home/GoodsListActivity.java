package com.lester.youmao.home;

import java.util.ArrayList;

import org.json.JSONObject;

import com.bset.tool.ListUtil;
import com.bset.tool.Texttool;
import com.bset.tool.Toast;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lester.youmao.LodingDialog;
import com.lester.youmao.MainActivity;
import com.lester.youmao.R;
import com.lester.youmao.adapter.GoodsListAdapter;
import com.lester.youmao.entity.CateGory;
import com.lester.youmao.entity.GoodsList;
import com.lester.youmao.me.OrderActivity;
import com.sanmi.http.Constants;
import com.sanmi.http.JsonUtil;
import com.sanmi.http.PublicRequest;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager.LayoutParams;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

public class GoodsListActivity extends Activity implements OnClickListener, OnItemClickListener{
	
	private EditText mTitle;
	private ImageView mBack;
	private TextView mRight;

	private LinearLayout mCate;
	private TextView mName;
	private LinearLayout mPrice;
	private TextView mPriceName;
	private ImageView mPriceImg;
	private LinearLayout mSalse;
	private TextView mSalseName;
	
	private PullToRefreshListView mRefreshListView;
	private ArrayList<GoodsList> mList = new ArrayList<GoodsList>();
	private GoodsListAdapter mAdapter;
	
	private ArrayList<CateGory> mCateList;
	private String cat_id;
	private String sort = "0";//1价格低到高  2价格高到低 3销量高到低
	private int page = 1;
	private String keyword = "";
	private LodingDialog dialog;
	private PopupWindow popupWindow = new PopupWindow();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.goods_list);
		
		cat_id = getIntent().getStringExtra("cat_id");
		sendHttp();
		
		mCateList = (ArrayList<CateGory>) getIntent().getSerializableExtra("son");
//		intent.putExtra("son", cateGory.getSon());
//		intent.putExtra("cat_id", cateGory.getSon().get(arg2).getCat_id());
//		intent.putExtra("cat_name", cateGory.getSon().get(arg2).getCat_name());
//		intent.putExtra("title", cateGory.getCat_name()); 
		
		initView();
	}

	private void initView() {
		mTitle = (EditText) findViewById(R.id.top_title);
		mBack = (ImageView) findViewById(R.id.top_back);
		mBack.setImageResource(R.drawable.back);
		mRight = (TextView) findViewById(R.id.top_rigth);
		mBack.setOnClickListener(this);
		mRight.setOnClickListener(this);
		
		mCate = (LinearLayout) findViewById(R.id.goods_list_fl);
		mName = (TextView) findViewById(R.id.goods_list_name);
		Texttool.setText(mName, getIntent().getStringExtra("cat_name"));
		mPrice = (LinearLayout) findViewById(R.id.goods_list_jg);
		mPriceName = (TextView) findViewById(R.id.goods_list_price);
		mPriceImg = (ImageView) findViewById(R.id.goods_list_img2);
		mSalse = (LinearLayout) findViewById(R.id.goods_list_xl);
		mSalseName = (TextView) findViewById(R.id.goods_list_salse);
		mCate.setOnClickListener(this);
		mPrice.setOnClickListener(this);
		mSalse.setOnClickListener(this);
		
		mRefreshListView = (PullToRefreshListView) findViewById(R.id.goods_list_lv);
		mRefreshListView.setMode(Mode.PULL_FROM_END);
		mAdapter = new GoodsListAdapter(GoodsListActivity.this, mList);
		mRefreshListView.setAdapter(mAdapter);
		mRefreshListView.setOnItemClickListener(this);
		mRefreshListView.setOnRefreshListener(new OnRefreshListener<ListView>() {
			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				page = page + 1;
				sendHttp();
			}
		});
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.top_back:
			finish();
			break;
		case R.id.top_rigth:
			sort.equals("0");
			if (!mTitle.getText().equals("")) {
				keyword = mTitle.getText().toString().trim();
				sort = "0";
				mPriceName.setTextColor(getResources().getColor(R.color.hui_33));
				mPriceImg.setImageResource(R.drawable.shang);
				mSalseName.setTextColor(getResources().getColor(R.color.hui_33));
				
				mList = new ArrayList<GoodsList>();
				sendHttp();
			}else {
				Toast.ToastMe(GoodsListActivity.this, "还没有输入搜索内容");
			}
			break;
		case R.id.goods_list_fl:
				initPopWindow();
			break;
		case R.id.goods_list_jg:
			if (sort.equals("0") ||sort.equals("2") ||sort.equals("3")) {
				sort = "1";
				mPriceImg.setImageResource(R.drawable.shang);
				mPriceName.setTextColor(getResources().getColor(R.color.second_color));
				mSalseName.setTextColor(getResources().getColor(R.color.hui_33));
				mList = new ArrayList<GoodsList>();
				sendHttp();
			}else {
				sort = "2";
				mPriceName.setTextColor(getResources().getColor(R.color.second_color));
				mSalseName.setTextColor(getResources().getColor(R.color.hui_33));
				mPriceImg.setImageResource(R.drawable.xia);
				mList = new ArrayList<GoodsList>();
				sendHttp();
			}
			break;
		case R.id.goods_list_xl:
			if (!sort.equals("3")) {
				sort = "3";
				mPriceName.setTextColor(getResources().getColor(R.color.hui_33));
				mSalseName.setTextColor(getResources().getColor(R.color.second_color));
				mPriceImg.setImageResource(R.drawable.shang);
				mList = new ArrayList<GoodsList>();
				sendHttp();
			}
			break;
		}
	}

	/**
	 * 新建一个popupWindow弹出框 popupWindow是一个阻塞式的弹出框，这就意味着在我们退出这个弹出框之前，程序会一直等待，
	 * 这和AlertDialog不同哦，AlertDialog是非阻塞式弹出框，AlertDialog弹出的时候，后台可是还可以做其他事情的哦。
	 */
	private void initPopWindow() {
		
		Log.i("aaa", "33333");
		// 加载popupWindow的布局文件
		View contentView_pop = LayoutInflater.from(getApplicationContext())
				.inflate(R.layout.pop_list, null);
		// 设置popupWindow的背景颜色
		contentView_pop.setBackgroundResource(R.color.hui_ea);
		// 声明一个弹出框
		popupWindow = new PopupWindow(
				findViewById(R.id.fenleilayout), MainActivity.width/3, LayoutParams.WRAP_CONTENT);
		// 为弹出框设定自定义的布局
		popupWindow.setContentView(contentView_pop);
		
		final ListView mListView = (ListView) contentView_pop.findViewById(R.id.pop_listView);
		
		BaseAdapter adapter = new BaseAdapter() {
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				convertView = LayoutInflater.from(GoodsListActivity.this).inflate(R.layout.pop_list_item, null);
				TextView tvTitle = (TextView) convertView.findViewById(R.id.pop_list_item_TextView);
				tvTitle.setText(mCateList.get(position).getCat_name());
				return convertView;
			}
			@Override
			public long getItemId(int position) {
				return position;
			}
			@Override
			public Object getItem(int position) {
				return mCateList.get(position);
			}
			@Override
			public int getCount() {
				return mCateList.size();
			}
		};
		mListView.setAdapter(adapter);
		mListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				mName.setText(mCateList.get(arg2).getCat_name());
				cat_id = mCateList.get(arg2).getCat_id();
				sort = "0";
				mPriceName.setTextColor(getResources().getColor(R.color.hui_33));
				mSalseName.setTextColor(getResources().getColor(R.color.hui_33));
				mList = new ArrayList<GoodsList>();
				sendHttp();
				popupWindow.dismiss();
			}
		});
		// 设定当你点击editText时，弹出的输入框是啥样子的。这里设置默认为数字输入哦，这时候你会发现你输入非数字的东西是不行的哦
		// editText.setInputType(InputType.TYPE_CLASS_NUMBER);
		/*
		 * 这个popupWindow.setFocusable(true);非常重要，如果不在弹出之前加上这条语句，你会很悲剧的发现，你是无法在
		 * editText中输入任何东西的
		 * 。该方法可以设定popupWindow获取焦点的能力。当设置为true时，系统会捕获到焦点给popupWindow
		 * 上的组件。默认为false哦.该方法一定要在弹出对话框之前进行调用。
		 */
		popupWindow.setFocusable(true);
		popupWindow.setOutsideTouchable(false);
		popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
		/*
		 * popupWindow.showAsDropDown（View view）弹出对话框，位置在紧挨着view组件
		 * showAsDropDown(View anchor, int xoff, int yoff)弹出对话框，位置在紧挨着view组件，x y
		 * 代表着偏移量 showAtLocation(View parent, int gravity, int x, int y)弹出对话框
		 * parent 父布局 gravity 依靠父布局的位置如Gravity.CENTER x y 坐标值
		 */
		popupWindow.showAsDropDown(mCate);
		}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		Intent intent = new Intent();
		intent.putExtra("goods_id", mList.get(arg2-1).getGoods_id());
		intent.setClass(GoodsListActivity.this, GoodsDetailActivity.class);
		startActivity(intent);
	}
	
	private void sendHttp() {
		dialog=LodingDialog.DialogFactor(GoodsListActivity.this, "正在加载", false);
		PublicRequest http = new PublicRequest(mHandler);
		http.GoodsList(GoodsListActivity.this, cat_id, page+"",sort,keyword);
	}

	Handler mHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			try {
				if(dialog!=null){
					dialog.dismiss();
				}
				switch (msg.what) {
				case Constants.GOODS_LIST:
					mRefreshListView.onRefreshComplete();
					JSONObject jsonObj = new JSONObject(msg.obj.toString());
					if (jsonObj.getString("code").equals("1")) {
						String jsonData = jsonObj.getString("data");
						ArrayList<GoodsList> mList2 = new ArrayList<GoodsList>();
						mList2 = JsonUtil.instance().fromJson(jsonData,new TypeToken<ArrayList<GoodsList>>() {}.getType());
						if (mList2 != null) {
							mList.addAll(mList2);
							mAdapter = new GoodsListAdapter(GoodsListActivity.this, mList);
							mRefreshListView.setAdapter(mAdapter);
							if (mList.size() < 30) {
								mRefreshListView.setPullToRefreshEnabled(false);
								if (page != 1) {
									Toast.ToastMe(GoodsListActivity.this, "没有更多了");
								}
							}
						}
					} else {
						mAdapter = new GoodsListAdapter(GoodsListActivity.this, mList);
						mRefreshListView.setAdapter(mAdapter);
						Toast.ToastMe(GoodsListActivity.this,jsonObj.getString("message"));
					}
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		};
	};

}
