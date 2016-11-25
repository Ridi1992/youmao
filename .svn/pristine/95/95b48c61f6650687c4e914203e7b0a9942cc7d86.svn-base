/**   
 * @Title: LodingDialog.java
 * @Package com.dream.framework.utils.dialog
 * @Description:
 * @author wuguozhi
 * @date 2014骞2鏈6鏃涓嬪崍3:43:32 
 */
package com.lester.youmao;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @ClassName: LodingDialog
 * @Description:
 * @author wuguozhi
 * @date 2014骞2鏈6鏃涓嬪崍3:43:32
 */
public class LodingDialog extends Dialog {

	private Context context = null;

	public LodingDialog(Context context) {
		super(context);
		setOwnerActivity((Activity) context);
		this.context = context;
	}

	public LodingDialog(Context context, int theme) {
		super(context, theme);
		this.context = context;
	}

	public static LodingDialog DialogFactor(final Context context,
			String strMessage, boolean bl) {
		LodingDialog lodingDialog = new LodingDialog(context,
				R.style.LodingDialog);
		lodingDialog.setContentView(R.layout.loadingdialog);
		TextView tvMsg = (TextView) lodingDialog
				.findViewById(R.id.loading_title);
		lodingDialog.setCanceledOnTouchOutside(false);
		if (tvMsg != null) {
			tvMsg.setText(strMessage);
		}
		
//		 lodingDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
//		 @Override
//		 public boolean onKey(DialogInterface dialog, int keyCode,
//		 KeyEvent event) {
//		 if (keyCode == KeyEvent.KEYCODE_BACK) {
//			 return true;
//		 }
//		 return false;
//		 }
//		 });
		lodingDialog.show();
		return lodingDialog;
	}
}
