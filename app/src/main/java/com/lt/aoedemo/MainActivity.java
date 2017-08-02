package com.lt.aoedemo;


import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.cmcc.aoe.ds.AoiPushSetting;
import com.cmcc.aoe.sdk.AoiSDK;
import com.cmcc.sso.sdk.auth.AuthnHelper;
import com.cmcc.sso.sdk.auth.TokenListener;
import com.cmcc.sso.sdk.util.SsoSdkConstants;

import org.json.JSONObject;

public class MainActivity extends Activity implements OnClickListener {

    public static String TAG = "AoeDemo";
    public static String APPID = "108100000004";

    private static final String SSO_APPID = "10000502";
    private static final String SSO_APPKEY = "94C928C0ABC26FA2";

    private Button btnInit;
    private Button btnToken;
    private Button btnNotifyOne;
    private Button btnNotifyTwo;
    private Button btnNotifyThree;
    private Button btnNotifyFour;

    private Button btnModifyDns;
    private Button btnModifyPass;
    private Button btnModifyPush;
    private AuthnHelper helper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//		forLogPermission();//棣栧厛鐢宠鏂囦欢鏉冮檺锛侊紒

        this.setTitle("AppId:" + APPID);

        String dir = Environment.getExternalStorageDirectory().getAbsolutePath();
        Log.d("sdcard-rootdir", ",dir=" + dir);

        //娴嬭瘯log
        com.cmcc.aoe.util.Log.showTestInfo("aoedemo", "oncr");
        initView();
        start();

        //娴嬭瘯log
        com.cmcc.aoe.util.Log.showTestInfo("aoedemo", "oncr1111");
        com.cmcc.aoe.util.Log.showTestInfo("aoedemo", "oncreate=end");

        helper = new AuthnHelper(this);

    }

    /**
     * 涓存椂娴嬭瘯鐢ㄣ��
     */
    private void test() {
        //杩欎釜骞挎挱app娌℃湁鏉冮檺
//          String acc = "android.intent.action.DATA_SMS_RECEIVED";
//          sendBroadcast(new Intent(acc));


    }

    private void initView() {
        btnInit = (Button) findViewById(R.id.oninit);
        btnInit.setOnClickListener(this);
        btnToken = (Button) findViewById(R.id.onToken);
        btnToken.setOnClickListener(this);
        btnNotifyOne = (Button) findViewById(R.id.notify_one);
        btnNotifyOne.setOnClickListener(this);
        btnNotifyTwo = (Button) findViewById(R.id.notify_two);
        btnNotifyTwo.setOnClickListener(this);
        btnNotifyThree = (Button) findViewById(R.id.notify_three);
        btnNotifyThree.setOnClickListener(this);
        btnNotifyFour = (Button) findViewById(R.id.notify_four);
        btnNotifyFour.setOnClickListener(this);
        btnModifyDns = (Button) findViewById(R.id.modify_dns);
        btnModifyDns.setOnClickListener(this);
        btnModifyPass = (Button) findViewById(R.id.modify_pass);
        btnModifyPass.setOnClickListener(this);
        btnModifyPush = (Button) findViewById(R.id.modify_push);
        btnModifyPush.setOnClickListener(this);
    }

    private void startInitActivity() {
        Intent intent = new Intent();
        intent.setClass(this, InitResultActivity.class);
        this.startActivity(intent);
    }

    private void notifyOne() {
        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Utils.showNotice(this, R.drawable.ic_launcher, "閫氱煡娑堟伅", "鐐瑰嚮閫氱煡鎵撳紑瀹㈡埛绔�", pIntent);
    }

    private void notifyTwo() {
        Uri uri = Uri.parse("http://www.baidu.com");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Utils.showNotice(this, R.drawable.ic_launcher, "閫氱煡娑堟伅", "鐐瑰嚮閫氱煡鎵撳紑鐧惧害棣栭〉", pIntent);
    }

    private void notifyThree() {
        Intent intent = new Intent();
        intent.setClass(this, DemoMessageAlert.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Utils.showNotice(this, R.drawable.ic_launcher, "閫氱煡娑堟伅", "鐐瑰嚮閫氱煡鎵撳紑涓嬭浇椤甸潰", pIntent);
    }

    private void notifyFour() {
        Intent intent = new Intent();
        intent.setClass(this, FirstActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Utils.showNotice(this, R.drawable.ic_launcher, "閫氱煡娑堟伅", "鐐瑰嚮閫氱煡鎵撳紑FirstActivity", pIntent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.oninit:
                start();
                break;
            case R.id.onToken:
                startInitActivity();
                break;
            case R.id.notify_one:
                Toast.makeText(MainActivity.this, "你好", Toast.LENGTH_SHORT).show();
                helper.getAccessToken(SSO_APPID, SSO_APPKEY, null, SsoSdkConstants.LOGIN_TYPE_DEFAULT, new TokenListener() {

                    @Override
                    public void onGetTokenComplete(JSONObject arg0) {
                        Log.d("sso result", arg0.toString());
                    }

                });
//			notifyOne();
                break;
            case R.id.notify_two:
                notifyTwo();
                break;
            case R.id.notify_three:
                notifyThree();
                break;
            case R.id.notify_four:
                notifyFour();
                break;
            case R.id.modify_dns:
                modifDns();
                break;
            case R.id.modify_pass:
                modifyPass();
                break;
            case R.id.modify_push:
                modifPush();
                break;
            case R.id.Start:
                restart();
                break;
            case R.id.QueryState:
                QueryState();
                break;
            case R.id.Tick:
                syncMessage();
                break;

        }
    }


    public void modifDns() {
        AoiPushSetting.updateDnsIp("192.168.188.137", this);
    }

    public void modifyPass() {
        AoiPushSetting.updateAoiGwPasskey(this, "0000111");
    }

    public void modifPush() {
        AoiPushSetting.cleanAoiInfo(this);
    }

    public void start() {
        AoiSDK.start(getApplicationContext());
        AoiSDK.setDebug(getApplicationContext(), true);//蹇呴』寮哄埗 锛岄伩鍏峧ar榛樿鍊兼湁闂瀵艰嚧www
//		AoiSDK.setDebug(true);


    }

    public void restart() {
        start();
    }

    public void QueryState() {
        AoiSDK.QueryState(this);
    }

    public void syncMessage() {
        AoiSDK.syncMessage(this);
    }


    int REQUEST_EXTERNAL_STORAGE = 1000;

    /**
     *
     */
//	void forLogPermission(){
//		 Context tContext = getApplication();
//
//		 if (Build.VERSION.SDK_INT >= 23) {
//	            int checkCallPhonePermission = ContextCompat.checkSelfPermission(tContext,Manifest.permission.WRITE_EXTERNAL_STORAGE);
//
//	            if(checkCallPhonePermission != PackageManager.PERMISSION_GRANTED){
//
//	            	ActivityCompat.requestPermissions(this
//	                		,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE
//	                		,Manifest.permission.READ_EXTERNAL_STORAGE}
//	                ,REQUEST_EXTERNAL_STORAGE);
//	                return;
//	            }
//		  }else{
//			  ///
//
//		  }
}

//}
