package com.lester.youmao;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.util.Log;

import java.io.EOFException;
import java.net.SocketTimeoutException;
import java.util.Map;


public class WebServiceVisitor  {
    private static final int TIME_OUT = 30000;
    private static final String NAME_SPACE = "http://android.googlepages.com/";
    //公网
    private static final String END_POINT = "http://222.76.203.162:8890/xsfs/services/Service";
    private static final String SOAP_ACTION_HOST = "http://222.76.203.162:8890/";


	public static String callWebService(String methodName,Map<String,Object> propertyMap) {
		// 命名空间
		String nameSpace = NAME_SPACE;
		// 调用的方法名称

		// EndPoint
		String endPoint = END_POINT;
		// SOAP Action
		String soapAction = SOAP_ACTION_HOST + methodName;
		// 指定WebService的命名空间和调用的方法名
		SoapObject rpc = new SoapObject(nameSpace, methodName);
		// 设置需调用WebService接口需要传入的两个参数mobileCode、userId
		for(String key : propertyMap.keySet()){
			rpc.addProperty(key, propertyMap.get(key));
		}
		// 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER10);

		envelope.bodyOut = rpc;
		// 设置是否调用的是dotNet开发的WebService
		envelope.dotNet = false;
		// 等价于envelope.bodyOut = rpc;
		envelope.setOutputSoapObject(rpc);
		HttpTransportSE transport = new HttpTransportSE(endPoint,TIME_OUT);
		try {
            // 调用WebService
            transport.call(soapAction, envelope);

        }catch (SocketTimeoutException e){
            e.printStackTrace();
            return null;
        }catch (EOFException e){
            e.printStackTrace();
            try {
                transport.call(soapAction, envelope);
            } catch (Exception e1) {
                e1.printStackTrace();
                return null;
            }
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        SoapObject object = null;
        try{
            // 获取返回的数据
//            Log.i("out:%s", envelope.bodyOut.toString());
//            Log.i("in:%s", envelope.bodyIn.toString());
            object = (SoapObject) envelope.bodyIn;
        }catch(Exception ex){
            return "error";
        }
        // 获取返回的结果
        String result = object==null? null : object.getProperty(0).toString();
		return result;
		
	}
}
