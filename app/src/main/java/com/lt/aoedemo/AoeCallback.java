package com.lt.aoedemo;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.cmcc.aoe.sdk.AoiCallback;
import com.cmcc.aoe.sdk.AoiError;


/**
 * app 自定义实现 {@link  AoiCallback}
 * 用于接收aoesdk传递的消息 
 * 
 *自定义实现默认构造函数需支持，sdk需要使用。
 *
 */
public class AoeCallback extends AoiCallback {

	private static final String TAG = "AoeCallback";
	
	private Context mContext;
	
	public AoeCallback () {
		
	}
	
	private TextView textToken = null;
	
	public void setTextView(TextView view) {
		textToken = view;
	}
	
	@Override
	public void onPostData(int result, byte[] data) {
		if(AoiError.ERROR == result){
			android.util.Log.d(TAG,"fail====");
			
			if( null != data){
				String temp = new String(data);
				if (mContext != null) {
					Toast.makeText(mContext, TAG + " post:"+temp, Toast.LENGTH_LONG).show();

				}
			}
			
		}else if(AoiError.SUCCEED == result){
			android.util.Log.d(TAG,"success====");
			
			String temp = new String(data);
			if (mContext != null) {
				Toast.makeText(mContext, TAG + " post:"+temp, Toast.LENGTH_LONG).show();

			}
		}else{
			///不会
		}
		
	}

    @Override
	public void onUnregister(int error) {
    	
    }

	@Override
	public void setContext(Context context) {
		mContext = context;
	}

	@Override
	public void onInit(int result, String token) {
		
		if(AoiError.SUCCEED == result){
			if (token != null) {
//				Toast.makeText(mContext, TAG + " token:"+token, Toast.LENGTH_LONG).show();
				String localToken = Utils.getToken(mContext);
				if (!TextUtils.isEmpty(localToken) && !TextUtils.isEmpty(token) && localToken.equals(token)) {
					return;
				}
				Utils.putToken(mContext, token);
				Intent intent = new Intent();
				intent.putExtra("token", token);
				intent.setClass(mContext.getApplicationContext(),
						InitResultActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				mContext.getApplicationContext().startActivity(intent);
			}else{
				//不应该出现
			}
		}else if(AoiError.ERROR == result){
			//告诉app建立通道失败，app可以考虑做处理
			
		}else{
			///不应该出现
		}
		
	}

	/**
	 * 接收sdk的传递的通知
	 */
	@Override
	public void onNotifyData(int result, byte[] data) {
		
		if(AoiError.SUCCEED == result){
			//data应该nonull
			String temp = new String(data);
			if (mContext != null) {
				Intent intent = new Intent();
				intent.setClass(mContext, MainActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
				PendingIntent pIntent = PendingIntent.getActivity(mContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
			    Utils.showNotice(mContext, R.drawable.ic_launcher, "自定义消息", temp, pIntent);
			}else{
				//?
			}
		}else if(AoiError.ERROR == result){
//			log
			
		}else{
			//不会
		}
		
	}

	@Override
	public void onState(int state) {
		// TODO Auto-generated method stub
		Log.i(TAG, "On State");
		if (mContext != null) {
			Toast.makeText(mContext, TAG + " state:"+state, Toast.LENGTH_LONG).show();
		}
	}

    @Override
    public void onFindToken(int arg0, String arg1) {
        // TODO Auto-generated method stub
        
    }

}
