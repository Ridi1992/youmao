package com.lester.youmao;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import android.content.Context;

public class Shares {
	public static void showShareIncome(Context c) {
		ShareSDK.initSDK(c);
		OnekeyShare oks = new OnekeyShare();
		// 关闭sso授权
		oks.disableSSOWhenAuthorize();
		// 分享时Notification的图标和文字 2.5.9以后的版本不调用此方法
		// oks.setNotification(R.drawable.ic_launcher,
		// getString(R.string.app_name));
		// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
		oks.setTitle("悠贸商城");
		// titleUrl是标题的网络链接，仅在人人网和QQ空间使用
		oks.setTitleUrl("http://a.app.qq.com/o/simple.jsp?pkgname=com.lester.youmao");
		// text是分享文本，所有平台都需要这个字段
		oks.setText("悠贸商城把有品牌、有优势、有服务的商品汇聚到网上，让顾客能够足不出户就能买到方便、实惠和安心的商品，满足顾客对生活的更多需求。打造独立运营、物流统一配送的网络大平台，集传统交易、电子交易、展览展示、咨询互动为一体，实现了虚拟经济和实体经济、线上电子商务平台相融合，以积极加快推动信息技术的运用，开辟了一条更为快捷、有效的营销途径。");
		// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
		oks.setImageUrl("http://b.seotech.com.cn/app/logo.png");
		// 确保SDcard下面存在此张图片
//		oks.setImageUrl(imageUrl);
		// url仅在微信（包括好友和朋友圈）中使用
		oks.setUrl("http://a.app.qq.com/o/simple.jsp?pkgname=com.lester.youmao#weixin.qq.com#wechat_redirect");
//		oks.setUrl("http://nongzi.sanmitech.com/code/index.html");
		// comment是我对这条分享的评论，仅在人人网和QQ空间使用
		oks.setComment("悠贸商城");
		// site是分享此内容的网站名称，仅在QQ空间使用
		oks.setSite("悠贸商城");
		// siteUrl是分享此内容的网站地址，仅在QQ空间使用
//		oks.setSiteUrl("http://www.zhg518.com");
		// 启动分享GUI
		oks.show(c);
	}
	
	
}
