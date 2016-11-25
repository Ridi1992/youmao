package com.lester.youmao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.lester.youmao.userinfo.mUserInfo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;


public class Userhead {

	/**
	 *保存用户头像
	 */
	public static boolean savehead(Bitmap bm,String user_id){//id为用户唯一标示
		try
		{
			//判断路径是否存在，不存在则新建路径
			File file1=new File("/sdcard/youmao/head/");
			if(!file1.exists()){
				file1.mkdirs();//不存在则新建路径
			}
			// 创建一个位于SD卡上的文件
//			String imgname="head"+user_id+".jpg";
//			String imgname="head.jpg";
			File file = new File("/sdcard/youmao/head/"+user_id+".jpg");
			if(file.exists()){
				file.delete();
			}
			FileOutputStream outStream = null;
			// 打开指定文件对应的输出流
			outStream = new FileOutputStream(file);
			// 把位图输出到指定文件中
			bm.compress(CompressFormat.JPEG, 100, outStream);
			outStream.close();
			System.out.println("图片保存成功");
			return true;
			}
		catch (IOException e)
		{
			e.printStackTrace();
			System.out.println(e.toString()+"保存错误");
			return false;
		}
	}
	/**
	 *保存其他用户头像
	 */
//	public Boolean saveuserhead(Bitmap bm,String path){//id为用户唯一标示
//		try
//		{
//			//判断路径是否存在，不存在则新建路径
//			File file1=new File("/sdcard/tawai/ico/");
//			if(!file1.exists()){
//				file1.mkdirs();//不存在则新建路径
//			}
//			// 创建一个位于SD卡上的文件
////			String imgname="head"+MainActivity.user_id+".jpg";
//			File file = new File("/sdcard/tawai/ico/",path);
//			if(file.exists()){
//				file.delete();
//			}
//			FileOutputStream outStream = null;
//			// 打开指定文件对应的输出流
//			outStream = new FileOutputStream(file);
//			// 把位图输出到指定文件中
//			bm.compress(CompressFormat.JPEG, 100, outStream);
//			outStream.close();
//			System.out.println("头像保存成功");
//			return true;
//			}
//		catch (IOException e)
//		{
//			e.printStackTrace();
//			System.out.println(e.toString()+"保存错误");
//			return false;
//		}
//	}
	/**
	 * 从本地获取用户头像并返回
	 */
//	public Bitmap gethead(){
//			String imgname="head"+GetShare.user_id()+".jpg";
//    		String filepath = "/sdcard/tawai/head/"+imgname;//图片路径
//    		File file = new File(filepath);
//       	 if (file.exists()) {
//       		 	Bitmap bm=BitmapFactory.decodeFile(filepath);
//       			return bm;
//       	 	}else{
//       	 		return null;
//       	 	}
//	}
	/**
	 * 从本地获取其他用户头像并返回
	 */
//	public Bitmap getuserhead(String imgname){
//			String imgname="head"+MainActivity.user_id+".jpg";
//    		String filepath = "/sdcard/tawai/ico/"+imgname;//图片路径
//    		File file = new File(filepath);
//       	 if (file.exists()) {
//       		 	Bitmap bm=BitmapFactory.decodeFile(filepath);
//       			return bm;
//       	 	}else{
//       	 		return null;
//       	 	}
//	}
}
