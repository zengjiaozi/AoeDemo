package com.lt.aoedemo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class DemoMessageAlert extends Activity implements OnClickListener {

	private Button btnYes;
	private Button btnNo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.alert_message);
		this.setTitle("AppId:"+MainActivity.APPID);
		init();
	}

	private void init() {
		btnYes = (Button) findViewById(R.id.confirm);
		btnYes.setOnClickListener(this);
		btnNo = (Button) findViewById(R.id.cancle);
		btnNo.setOnClickListener(this);
	}

	@SuppressLint("NewApi")
	private void downLoad() {
		this.finish();
		String url = "http://b.hiphotos.baidu.com/image/pic/item/58ee3d6d55fbb2fb9ab837054c4a20a44623dc12.jpg";
		String title = "��ֽͼƬ����";
		DownloadManager downloadManager = (DownloadManager)this.getSystemService(Context.DOWNLOAD_SERVICE);  
		Uri resource = Uri.parse(url);   
		DownloadManager.Request request = new DownloadManager.Request(resource);
		if (title != null) {
			request.setTitle(title);
		}
		downloadManager.enqueue(request); 
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.confirm:
			downLoad();
			break;
		case R.id.cancle:
			this.finish();
			break;
		}
	}


}
