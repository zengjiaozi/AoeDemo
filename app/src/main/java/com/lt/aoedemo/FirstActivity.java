package com.lt.aoedemo;

import android.app.Activity;
import android.os.Bundle;

public class FirstActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_first);
		this.setTitle("AppId:"+MainActivity.APPID);
	}
	
}
