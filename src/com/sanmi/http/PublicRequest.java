package com.sanmi.http;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;

/**
 * @author Best
 *
 */
public class PublicRequest{
	private static PublicRequest instance;
	private Handler handler;
	public PublicRequest(Handler handler2) {
		this.handler=handler2;
	}
	public static  PublicRequest getInstance(Handler handler){
		instance=new PublicRequest(handler);
		return instance;
	}
	/**
	 * 获取验证码
	 * @params context
	 * @params username
	 */
	public void getCode(Context context,String username){
		String[][] params=new String[1][];
		params[0]=new String[]{"mobile",username};
		HttpUtilPHP.invokePost(context,myHandler, Constants.GETCODE,HttpURL.URL+HttpURL.GETCODE, params);
	}
	
	/**
	 * 登录
	 * @params context
	 * @params username
	 * @params password
	 */
	public void Login(Context context,String username,String password){
		String[][] params=new String[2][];
		params[0]=new String[]{"name",username};
		params[1]=new String[]{"password",password};
		HttpUtilPHP.invokePost(context,myHandler, Constants.LOGIN, HttpURL.URL+HttpURL.LOGIN, params);
	}
	
	/**
	 * 注册 
	 */
	public void Register(Context context,String mobile,String mobile_code,String mobilec,String mobile_codec,String password,String key){
		String[][] params=new String[6][];
		params[0]=new String[]{"mobile",mobile};
		params[1]=new String[]{"mobile_code",mobile_code};
		params[2]=new String[]{"mobilec",mobilec};
		params[3]=new String[]{"mobile_codec",mobile_codec};
		params[4]=new String[]{"password",password};
		params[5]=new String[]{"key",key};
		HttpUtilPHP.invokePost(context,myHandler, Constants.REGISTER,HttpURL.URL+HttpURL.REGISTER, params);
	}
	/**
	 * 轮播图
	 */
	public void Banner(Context context){
		String[][] params=new String[0][];
		HttpUtilPHP.invokePost(context,myHandler, Constants.BANNER,HttpURL.URL+HttpURL.BANNER, params);
	}
	/**
	 * 首页分类
	 */
	public void HomeCat(Context context){
		String[][] params=new String[0][];
		HttpUtilPHP.invokePost(context,myHandler, Constants.HOME_CAT,HttpURL.URL+HttpURL.HOME_CAT, params);
	}
	/**
	 * 首页秒杀
	 */
	public void HomePromote(Context context){
		String[][] params=new String[1][];
		params[0]=new String[]{"type","is_promote"};
		HttpUtilPHP.invokePost(context,myHandler, Constants.HOME_PROMOTE,HttpURL.URL+HttpURL.HOME_PROMOTE, params);
	}
	/**
	 * 首页推荐
	 */
	public void HomeBest(Context context){
		String[][] params=new String[1][];
		params[0]=new String[]{"type","is_best"};
		HttpUtilPHP.invokePost(context,myHandler, Constants.HOME_BEST,HttpURL.URL+HttpURL.HOME_BEST, params);
	}
	/**
	 * 秒杀更多
	 */
	public void HomePromoteMore(Context context){
		String[][] params=new String[1][];
		params[0] = new String[]{"type","is_promote"};
		HttpUtilPHP.invokePost(context,myHandler, Constants.HOME_PROMOTE_MORE,HttpURL.URL+HttpURL.HOME_PROMOTE_MORE, params);
	}
	/**
	 * 我的积分
	 */
	public void MePoint(Context context,String user_id){
		String[][] params=new String[1][];
		params[0] = new String[]{"user_id",user_id};
		HttpUtilPHP.invokePost(context,myHandler, Constants.ME_POINT,HttpURL.URL+HttpURL.ME_POINT, params);
	}
	/**
	 * 我的消息
	 */
	public void MeNotice(Context context,String user_id){
		String[][] params=new String[1][];
		params[0] = new String[]{"user_id",user_id};
		HttpUtilPHP.invokePost(context,myHandler, Constants.ME_NOTICE,HttpURL.URL+HttpURL.ME_NOTICE, params);
	}
	/**
	 * 商品分类
	 */
	public void CatAll(Context context){
		String[][] params=new String[0][];
		HttpUtilPHP.invokePost(context,myHandler, Constants.CAT_ALL,HttpURL.URL+HttpURL.CAT_ALL, params);
	}
	/**
	 * 优惠券列表
	 */
	public void CouponList(Context context,String user_id,String type){
		String[][] params=new String[2][];
		params [0]=new String[]{"user_id",user_id};
		params [1]=new String[]{"type",type};
		HttpUtilPHP.invokePost(context,myHandler, Constants.COUPON_LIST,HttpURL.URL+HttpURL.COUPON_LIST, params);
	}
	/**
	 * 优惠券领取
	 */
	public void CouponReceive(Context context,String user_id,String type_id){
		String[][] params=new String[2][];
		params [0]=new String[]{"user_id",user_id};
		params [1]=new String[]{"type_id",type_id};
		HttpUtilPHP.invokePost(context,myHandler, Constants.COUPON_RECEIVE,HttpURL.URL+HttpURL.COUPON_RECEIVE, params);
	}
	/**
	 * 反馈
	 */
	public void FeedBack(Context context,String user_name,String phone,String content){
		String[][] params=new String[3][];
		params [0]=new String[]{"user_name",user_name};
		params [1]=new String[]{"phone",phone};
		params [2]=new String[]{"content",content};
		HttpUtilPHP.invokePost(context,myHandler, Constants.ME_FEEDBACK,HttpURL.URL+HttpURL.ME_FEEDBACK, params);
	}
	/**
	 * 商品列表
	 */
	public void GoodsList(Context context,String cat_id,String page,String sort,String keyword){
		String[][] params=new String[5][];
		params [0]=new String[]{"cat_id",cat_id};
		params [1]=new String[]{"page",page};
		params [2]=new String[]{"pagenum","30"};
		params [3]=new String[]{"sort",sort};
		params [4]=new String[]{"keyword",keyword};
		HttpUtilPHP.invokePost(context,myHandler, Constants.GOODS_LIST,HttpURL.URL+HttpURL.GOODS_LIST, params);
	}
	/**
	 * 商品详情
	 */
	public void GoodsDetail(Context context,String goods_id,String type){
		String[][] params=new String[2][];
		params [0]=new String[]{"goods_id",goods_id};
		params [1]=new String[]{"type",type};
		HttpUtilPHP.invokePost(context,myHandler, Constants.GOODS_DETAIL,HttpURL.URL+HttpURL.GOODS_DETAIL, params);
	}
	/**
	 * 商品图片介绍
	 */
	public void GoodsDesc(Context context,String goods_id){
		String[][] params=new String[1][];
		params [0]=new String[]{"goods_id",goods_id};
		HttpUtilPHP.invokePost(context,myHandler, Constants.GOODS_DESC,HttpURL.URL+HttpURL.GOODS_DESC, params);
	}
	/**
	 * 加入购物车
	 */
	public void CartCreate(Context context,String sid,String uid,String goods_id,String number,String spec,String rec_type){
		String[][] params=new String[6][];
		params [0]=new String[]{"session[sid]",sid};
		params [1]=new String[]{"session[uid]",uid};
		params [2]=new String[]{"goods_id",goods_id};
		params [3]=new String[]{"number",number};
		params [4]=new String[]{"spec",spec};
		params [5]=new String[]{"rec_type",rec_type};
		HttpUtilPHP.invokePost(context,myHandler, Constants.CART_CREATE,HttpURL.URL+HttpURL.CART_CREATE, params);
	}
	/**
	 * 购物车列表
	 */
	public void CartList(Context context,String sid,String uid){
		String[][] params=new String[2][];
		params [0]=new String[]{"session[sid]",sid};
		params [1]=new String[]{"session[uid]",uid};
		HttpUtilPHP.invokePost(context,myHandler, Constants.CART_LIST,HttpURL.URL+HttpURL.CART_LIST, params);
	}
	/**
	 * 更新购物车
	 */
	public void CartUpdate(Context context,String sid,String uid,String rec_id,String new_number) {
		String[][] params = new String[4][];
		params[0] = new String[] { "session[sid]", sid };
		params[1] = new String[] { "session[uid]", uid };
		params[2] = new String[] { "rec_id", rec_id };
		params[3] = new String[] { "new_number", new_number };
		HttpUtilPHP.invokePost(context,myHandler, Constants.CART_UPDATE, HttpURL.URL+HttpURL.CART_UPDATE, params);
	}
	/**
	 * 删除购物车商品
	 */
	public void CartDelete(Context context,String sid,String uid,String rec_id) {
		String[][] params = new String[3][];
		params[0] = new String[] { "session[sid]", sid };
		params[1] = new String[] { "session[uid]", uid };
		params[2] = new String[] { "rec_id", rec_id };
		HttpUtilPHP.invokePost(context,myHandler, Constants.CART_DELETE, HttpURL.URL+HttpURL.CART_DELETE, params);
	}
	/**
	 * 我的积分列表
	 */
	public void PointList(Context context,String user_id) {
		String[][] params=new String[1][];
		params[0] = new String[]{"user_id",user_id};
		HttpUtilPHP.invokePost(context,myHandler, Constants.POINTS_LIST, HttpURL.URL+HttpURL.POINTS_LIST, params);
	}
	/**
	 * 收货地址列表
	 */
	public void AddressList(Context context,String sid, String uid) {
		String[][] params = new String[2][];
		params[0] = new String[] { "session[sid]", sid };
		params[1] = new String[] { "session[uid]", uid };
		HttpUtilPHP.invokePost(context,myHandler, Constants.ADDRESS_LIST, HttpURL.URL+HttpURL.ADDRESS_LIST, params);
	}
	/**
	 * 收货地址添加
	 */
	public void AddressAdd(Context context,String sid, String uid,String address,String name,String tel, String district_id) {
		String[][] params = new String[14][];
		params[0] = new String[] { "session[sid]", sid };
		params[1] = new String[] { "session[uid]", uid };
		params[2] = new String[] { "address[consignee]", name };
		params[3] = new String[] { "address[email]", "" };
		params[4] = new String[] { "address[country]", "1" };
		params[5] = new String[] { "address[province]", "4" };
		params[6] = new String[] { "address[city]", "53" };
		params[7] = new String[] { "address[district]", district_id };
		params[8] = new String[] { "address[address]", address };
		params[9] = new String[] { "address[zipcode]", "" };
		params[10] = new String[] { "address[tel]", tel };
		params[11] = new String[] { "address[mobile]", tel };
		params[12] = new String[] { "address[sign_building]", "" };
		params[13] = new String[] { "address[best_time]", "" };
		HttpUtilPHP.invokePost(context,myHandler, Constants.ADDRESS_ADD, HttpURL.URL+HttpURL.ADDRESS_ADD, params);
	}
	/**
	 * 收货地址修改
	 */
	public void AddressUpdate(Context context,String sid, String uid,String address_id,String address,String name,String tel, String district_id) {
		String[][] params = new String[15][];
		params[0] = new String[] { "session[sid]", sid };
		params[1] = new String[] { "session[uid]", uid };
		params[2] = new String[] { "address_id", address_id };
		params[3] = new String[] { "address[consignee]", name };
		params[4] = new String[] { "address[email]", "" };
		params[5] = new String[] { "address[country]", "1" };
		params[6] = new String[] { "address[province]", "4" };
		params[7] = new String[] { "address[city]", "53" };
		params[8] = new String[] { "address[district]", district_id };
		params[9] = new String[] { "address[address]", address };
		params[10] = new String[] { "address[zipcode]", "" };
		params[11] = new String[] { "address[tel]", tel };
		params[12] = new String[] { "address[mobile]", tel };
		params[13] = new String[] { "address[sign_building]", "" };
		params[14] = new String[] { "address[best_time]", "" };
		HttpUtilPHP.invokePost(context,myHandler, Constants.ADDRESS_UPDATE, HttpURL.URL+HttpURL.ADDRESS_UPDATE, params);
	}
	/**
	 * 收货地址删除
	 */
	public void AddressDelete(Context context,String sid, String uid,String address_id) {
		String[][] params = new String[3][];
		params[0] = new String[] { "session[sid]", sid };
		params[1] = new String[] { "session[uid]", uid };
		params[2] = new String[] { "address_id", address_id };
		HttpUtilPHP.invokePost(context,myHandler, Constants.ADDRESS_DELETE, HttpURL.URL+HttpURL.ADDRESS_DELETE, params);
	}
	/**
	 * 收货地址设置默认
	 */
	public void AddressDefault(Context context,String sid, String uid,String address_id) {
		String[][] params = new String[3][];
		params[0] = new String[] { "session[sid]", sid };
		params[1] = new String[] { "session[uid]", uid };
		params[2] = new String[] { "address_id", address_id };
		HttpUtilPHP.invokePost(context,myHandler, Constants.ADDRESS_DEFAULT, HttpURL.URL+HttpURL.ADDRESS_DEFAULT, params);
	}
	/**
	 * 选择区域
	 */
	public void Region(Context context,String parent_id) {
		String[][] params = new String[1][];
		params[0] = new String[] { "parent_id", parent_id };
		HttpUtilPHP.invokePost(context,myHandler, Constants.REGION, HttpURL.URL+HttpURL.REGION, params);
	}
	/**
	 * 检查订单
	 */
	public void CheckOrder(Context context,String sid, String uid,String longitude,String latitude,String flow_type,String rec_type) {
		String[][] params = new String[6][];
		params[0] = new String[] { "session[sid]", sid };
		params[1] = new String[] { "session[uid]", uid };
		params[2] = new String[] { "longitude", longitude };
		params[3] = new String[] { "latitude", latitude };
		params[4] = new String[] { "flow_type", flow_type };
		params[5] = new String[] { "rec_type", rec_type };
		HttpUtilPHP.invokePost(context,myHandler, Constants.CHECK_ORDER, HttpURL.URL+HttpURL.CHECK_ORDER, params);
	}
	/**
	 * 提交订单
	 */
	public void FlowDone(Context context,String sid, String uid,String pay_id,String shipping_id,String coupons_id,String shop_id,String flow_type, String rec_type) {
		String[][] params = new String[8][];
		params[0] = new String[] { "session[sid]", sid };
		params[1] = new String[] { "session[uid]", uid };
		params[2] = new String[] { "pay_id", pay_id };
		params[3] = new String[] { "shipping_id", shipping_id };
		params[4] = new String[] { "coupons_id", coupons_id };
		params[5] = new String[] { "shop_id", shop_id };
		params[6] = new String[] { "flow_type", flow_type };
		params[7] = new String[] { "rec_type", rec_type };
		HttpUtilPHP.invokePost(context,myHandler, Constants.FLOW_DONE, HttpURL.URL+HttpURL.FLOW_DONE, params);
	}
	/**
	 * 商品搜索
	 */
	public void Search(Context context,String keywords,String page) {
		String[][] params = new String[3][];
		params[0] = new String[] { "keyword", keywords };
		params[1] = new String[] { "page", page };
		params[2] = new String[] { "pagenum", "50" };
		HttpUtilPHP.invokePost(context,myHandler, Constants.SEARCH, HttpURL.URL+HttpURL.SEARCH, params);
	}
	/**
	 * 修改密码验证
	 */
	public void ForgetSend(Context context,String mobile) {
		String[][] params = new String[1][];
		params[0] = new String[] { "mobile", mobile };
		HttpUtilPHP.invokePost(context,myHandler, Constants.PASSWORD_SEND, HttpURL.URL+HttpURL.PASSWORD_SEND, params);
	}
	/**
	 * 修改密码修改
	 */
	public void ForgetCheck(Context context,String mobile,String mobilec,String mobile_code,String mobile_codec,String new_password) {
		String[][] params = new String[5][];
		params[0] = new String[] { "mobile", mobile };
		params[1] = new String[] { "mobilec", mobilec };
		params[2] = new String[] { "mobile_code", mobile_code };
		params[3] = new String[] { "mobile_codec", mobile_codec };
		params[4] = new String[] { "new_password", new_password };
		HttpUtilPHP.invokePost(context,myHandler, Constants.PASSWORD_CHECK, HttpURL.URL+HttpURL.PASSWORD_CHECK, params);
	}
	/**
	 * 我的优惠券列表
	 */
	public void MyCouponList(Context context,String user_id,String type){
		String[][] params=new String[2][];
		params [0]=new String[]{"user_id",user_id};
		params [1]=new String[]{"type",type};
		HttpUtilPHP.invokePost(context,myHandler, Constants.MYCOUPON_LIST,HttpURL.URL+HttpURL.MYCOUPON_LIST, params);
	}
	/**
	 * 积分商城列表
	 */
	public void JifenGoodsList(Context context,String page){
		String[][] params=new String[2][];
		params[0] = new String[] { "page", page };
		params[1] = new String[] { "pagenum", "100" };
		HttpUtilPHP.invokePost(context,myHandler, Constants.JIFEN_SHOP,HttpURL.URL+HttpURL.JIFEN_SHOP, params);
	}
	/**
	 * 积分商城加入购物车
	 */
	public void JifenCart(Context context,String goods_id,String user_id,String number){
		String[][] params=new String[3][];
		params[0] = new String[] { "goods_id", goods_id };
		params[1] = new String[] { "user_id", user_id };
		params[2] = new String[] { "number", number };
		HttpUtilPHP.invokePost(context,myHandler, Constants.JIFEN_TOCART,HttpURL.URL+HttpURL.JIFEN_TOCART, params);
	}
	/**
	 * 订单列表
	 */
	public void OrderList(Context context,String sid,String uid,String page, String type){
		String[][] params=new String[5][];
		params[0] = new String[] { "session[sid]", sid };
		params[1] = new String[] { "session[uid]", uid };
		params[2] = new String[] { "pagination[page]", page };
		params[3] = new String[] { "pagination[count]", "100" };
		params[4] = new String[] { "type", type };
		HttpUtilPHP.invokePost(context,myHandler, Constants.ORDER_LIST,HttpURL.URL+HttpURL.ORDER_LIST, params);
	}
	/**
	 * 确认收货
	 */
	public void OrderAffirm(Context context,String sid,String uid,String order_id){
		String[][] params=new String[3][];
		params[0] = new String[] { "session[sid]", sid };
		params[1] = new String[] { "session[uid]", uid };
		params[2] = new String[] { "order_id", order_id };
		HttpUtilPHP.invokePost(context,myHandler, Constants.ORDER_AFFIRM,HttpURL.URL+HttpURL.ORDER_AFFIRM, params);
	}
	/**
	 * 订单商品评价
	 */
	public void OrderComment(Context context,String user_id,String goods_id,String content,String order_id){
		String[][] params=new String[4][];
		params[0] = new String[] { "user_id", user_id };
		params[1] = new String[] { "goods_id", goods_id };
		params[2] = new String[] { "content", content };
		params[3] = new String[] { "order_id", order_id };
		HttpUtilPHP.invokePost(context,myHandler, Constants.ORDER_COMMENT,HttpURL.URL+HttpURL.ORDER_COMMENT, params);
	}
	/**
	 * 商品评论列表
	 */
	public void GoodsComment(Context context,String goods_id,String page){
		String[][] params=new String[3][];
		params[0] = new String[] { "goods_id", goods_id };
		params[1] = new String[] { "pagination[page]", page };
		params[2] = new String[] { "pagination[count]", "150" };
		HttpUtilPHP.invokePost(context,myHandler, Constants.GOODS_COMMENT,HttpURL.URL+HttpURL.GOODS_COMMENT, params);
	}
	/**
	 * 退换货列表
	 */
	public void OrderBackList(Context context,String user_id,String page){
		String[][] params=new String[3][];
		params[0] = new String[] { "user_id", user_id };
		params[1] = new String[] { "page", page };
		params[2] = new String[] { "pagenum", "40" };
		HttpUtilPHP.invokePost(context,myHandler, Constants.ORDER_BACK_LIST,HttpURL.URL+HttpURL.ORDER_BACK_LIST, params);
	}
	/**
	 * 退换货申请
	 */
	public void OrderBackAdd(Context context,String user_id,String order_id,String goods,String case_id,String reason,String mobile,String pay_id,String bank_number, String bank_place){
		String[][] params=new String[9][];
		params[0] = new String[] { "user_id", user_id };
		params[1] = new String[] { "order_id", order_id };
		params[2] = new String[] { "goods", goods };
		params[3] = new String[] { "case", case_id };
		params[4] = new String[] { "reason", reason };
		params[5] = new String[] { "mobile", mobile };
		params[6] = new String[] { "pay_id", pay_id };
		params[7] = new String[] { "bank_number", bank_number };
		params[8] = new String[] { "bank_place", bank_place };
		HttpUtilPHP.invokePost(context,myHandler, Constants.ORDER_BACK,HttpURL.URL+HttpURL.ORDER_BACK, params);
	}
	/**
	 * 购物车数量
	 */
	public void CartNumber(Context context,String user_id){
		String[][] params=new String[1][];
		params[0] = new String[] { "user_id", user_id };
		HttpUtilPHP.invokePost(context,myHandler, Constants.CART_NUMBER,HttpURL.URL+HttpURL.CART_NUMBER, params);
	}
	
	@SuppressLint("HandlerLeak")
	private Handler myHandler=new Handler(){
		public void handleMessage(android.os.Message msg) {
			handler.handleMessage(handler.obtainMessage(msg.what, msg.obj));
		};
	};
	
}
