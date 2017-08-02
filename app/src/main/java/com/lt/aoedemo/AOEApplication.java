package com.lt.aoedemo;

import android.app.Application;

public class AOEApplication extends Application{

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		CrashHandler crashHandler = CrashHandler.getInstance();  
		crashHandler.init(this);  
	}
	

}
