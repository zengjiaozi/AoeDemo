package com.lt.aoedemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.cmcc.aoe.ds.AoiPushSetting;
import com.cmcc.aoe.sdk.AoiSDK;

public class InitResultActivity extends Activity {

	private TextView tvResult;
	
	EditText tvResult_edit ;
	
	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.activity_init);
		this.setTitle("AppId:"+MainActivity.APPID);
		initView();
		
		//20170112bug修改 ，避免null引起异常。
		aoi = AoiSDK.getInstance();
		
	}

	private AoiSDK aoi ;
	private AoeCallback cb;

	private void initView() {
		tvResult_edit = (EditText) findViewById(R.id.init_token_edit);
		tvResult = (TextView) findViewById(R.id.init_result);
		
		String token = Utils.getToken(this);
		if(!TextUtils.isEmpty(token)){
			android.util.Log.d("aoedem", "tok::"+token);
			tvResult.setText(token);
			tvResult_edit.setText(token);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onNewIntent(Intent intent) {
		// TODO Auto-generated method stub
		super.onNewIntent(intent);
		setIntent(intent);
		processExtraData();
	}
	
	private void processExtraData() {
		Intent intent = getIntent();
		if (intent != null && tvResult != null) {
			Bundle bundle = intent.getExtras();
			String token = "" + bundle.getString("token");
			
			android.util.Log.d("aoedem", " processExtraData tok111::"+token);
			tvResult.setText(token);
			tvResult_edit.setText(token);//
			Utils.putToken(this, token);
		}
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch(item.getItemId()) {
		case R.id.action_clean:
			AoiPushSetting.delete(this);
			AoiPushSetting.delAOIGwInfo(this);
			break;
		case R.id.action_post:
			aoi.postData("Hello World".getBytes());
			break;
		}
		return super.onMenuItemSelected(featureId, item);
	}

}
