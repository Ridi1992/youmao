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
	 *�����û�ͷ��
	 */
	public static boolean savehead(Bitmap bm,String user_id){//idΪ�û�Ψһ��ʾ
		try
		{
			//�ж�·���Ƿ���ڣ����������½�·��
			File file1=new File("/sdcard/youmao/head/");
			if(!file1.exists()){
				file1.mkdirs();//���������½�·��
			}
			// ����һ��λ��SD���ϵ��ļ�
//			String imgname="head"+user_id+".jpg";
//			String imgname="head.jpg";
			File file = new File("/sdcard/youmao/head/"+user_id+".jpg");
			if(file.exists()){
				file.delete();
			}
			FileOutputStream outStream = null;
			// ��ָ���ļ���Ӧ�������
			outStream = new FileOutputStream(file);
			// ��λͼ�����ָ���ļ���
			bm.compress(CompressFormat.JPEG, 100, outStream);
			outStream.close();
			System.out.println("ͼƬ����ɹ�");
			return true;
			}
		catch (IOException e)
		{
			e.printStackTrace();
			System.out.println(e.toString()+"�������");
			return false;
		}
	}
	/**
	 *���������û�ͷ��
	 */
//	public Boolean saveuserhead(Bitmap bm,String path){//idΪ�û�Ψһ��ʾ
//		try
//		{
//			//�ж�·���Ƿ���ڣ����������½�·��
//			File file1=new File("/sdcard/tawai/ico/");
//			if(!file1.exists()){
//				file1.mkdirs();//���������½�·��
//			}
//			// ����һ��λ��SD���ϵ��ļ�
////			String imgname="head"+MainActivity.user_id+".jpg";
//			File file = new File("/sdcard/tawai/ico/",path);
//			if(file.exists()){
//				file.delete();
//			}
//			FileOutputStream outStream = null;
//			// ��ָ���ļ���Ӧ�������
//			outStream = new FileOutputStream(file);
//			// ��λͼ�����ָ���ļ���
//			bm.compress(CompressFormat.JPEG, 100, outStream);
//			outStream.close();
//			System.out.println("ͷ�񱣴�ɹ�");
//			return true;
//			}
//		catch (IOException e)
//		{
//			e.printStackTrace();
//			System.out.println(e.toString()+"�������");
//			return false;
//		}
//	}
	/**
	 * �ӱ��ػ�ȡ�û�ͷ�񲢷���
	 */
//	public Bitmap gethead(){
//			String imgname="head"+GetShare.user_id()+".jpg";
//    		String filepath = "/sdcard/tawai/head/"+imgname;//ͼƬ·��
//    		File file = new File(filepath);
//       	 if (file.exists()) {
//       		 	Bitmap bm=BitmapFactory.decodeFile(filepath);
//       			return bm;
//       	 	}else{
//       	 		return null;
//       	 	}
//	}
	/**
	 * �ӱ��ػ�ȡ�����û�ͷ�񲢷���
	 */
//	public Bitmap getuserhead(String imgname){
//			String imgname="head"+MainActivity.user_id+".jpg";
//    		String filepath = "/sdcard/tawai/ico/"+imgname;//ͼƬ·��
//    		File file = new File(filepath);
//       	 if (file.exists()) {
//       		 	Bitmap bm=BitmapFactory.decodeFile(filepath);
//       			return bm;
//       	 	}else{
//       	 		return null;
//       	 	}
//	}
}
