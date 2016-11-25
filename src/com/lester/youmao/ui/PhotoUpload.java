package com.lester.youmao.ui;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import com.google.gson.JsonObject;
import com.lester.youmao.userinfo.UserInfo;
import com.lester.youmao.userinfo.mUserInfo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PhotoUpload{  
    private String newName ="82.jpg";  
    private String uploadFile ="/sdcard/nongzi/head/82.jpg";
    private String actionUrl ="http://b.seotech.com.cn/app_api/face.php?act=upload&user_id=4&device=1";
    private Activity a;
    
      public void uploadFile(Activity a ,String name ,String file,String path){  
    	 this.a = a ;
    	 newName = name;
    	 uploadFile = file;
    	 actionUrl = path;
    	  
        String end ="\r\n";  
        String twoHyphens ="--";  
        String boundary ="*****";  
        try  
        {  
          URL url =new URL(actionUrl);  
          HttpURLConnection con=(HttpURLConnection)url.openConnection();  
          /* 允许Input、Output，不使用Cache */  
          con.setDoInput(true);  
          con.setDoOutput(true);  
          con.setUseCaches(false);  
          /* 设置传送的method=POST */  
          con.setRequestMethod("POST");  
          /* setRequestProperty */  
          con.setRequestProperty("Connection", "Keep-Alive");  
          con.setRequestProperty("Charset", "UTF-8");  
          con.setRequestProperty("Content-Type",  
                             "multipart/form-data;boundary="+boundary);  
          /* 设置DataOutputStream */  
          DataOutputStream ds =  
            new DataOutputStream(con.getOutputStream());  
          ds.writeBytes(twoHyphens + boundary + end);  
          ds.writeBytes("Content-Disposition: form-data; "+  "name=\"face\";filename=\""+ newName +"\""+ end);  
          ds.writeBytes(end);    
          /* 取得文件的FileInputStream */  
          FileInputStream fStream =new FileInputStream(uploadFile);  
          /* 设置每次写入1024bytes */  
          int bufferSize =1024;  
          byte[] buffer =new byte[bufferSize];  
          int length =-1;  
          /* 从文件读取数据至缓冲区 */  
          while((length = fStream.read(buffer)) !=-1)  
          {  
            /* 将资料写入DataOutputStream中 */  
            ds.write(buffer, 0, length);  
          }  
          ds.writeBytes(end);  
          ds.writeBytes(twoHyphens + boundary + twoHyphens + end);  
          /* close streams */  
          fStream.close();  
          ds.flush();  
          /* 取得Response内容 */  
          InputStream is = con.getInputStream();  
          int ch;  
          StringBuffer b =new StringBuffer();  
          while( ( ch = is.read() ) !=-1 )  
          {  
            b.append( (char)ch );  
          }  
          /* 将Response显示于Dialog */  
          Log.i("bbbbbbbb","上传成功"+b.toString().trim());  
          
          JSONObject jsonObj = new JSONObject(b.toString().trim());
          String face = jsonObj.getJSONObject("data").getString("face");
          UserInfo data = mUserInfo.GetUserInfo(a);
          data.getUser().setFace_img(face);
          mUserInfo.SaveUserInfo(a, data);
          
          /* 关闭DataOutputStream */  
          ds.close();  
        }  
        catch(Exception e)  
        {  
        	Log.i("bbbbbbbb","上传失败"+e);  
        }  
      }  
    }  