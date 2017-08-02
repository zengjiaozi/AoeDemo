package com.lt.aoedemo;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build;


public class Utils {
	
	private final static String DEMO_SP_FILENAME 	= "local";
	private final static String DEMO_SP_KEY 		= "token";
	

	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	public static void showNotice(Context context, int icon,
			CharSequence topic, CharSequence content, PendingIntent intent) {
		NotificationManager noManager = (NotificationManager) context
				.getApplicationContext().getSystemService(
						Context.NOTIFICATION_SERVICE);
		Notification notification = null;
		try {
			if (android.os.Build.VERSION.SDK_INT >= 16) {
				notification = new Notification.Builder(context)
						.setSmallIcon(icon).setAutoCancel(true)
						.setContentTitle(topic).setContentText(content)
						.setContentIntent(intent)
						.setWhen(System.currentTimeMillis()).build();
			} else if (android.os.Build.VERSION.SDK_INT < 11) {
				// notification = new Notification(icon, "",
				// System.currentTimeMillis());
				// notification.setLatestEventInfo(context, topic,content,
				// intent);
				// notification.flags |= Notification.FLAG_AUTO_CANCEL;
			} else if (android.os.Build.VERSION.SDK_INT < 16) {
				Notification.Builder builder = new Notification.Builder(context)
						.setAutoCancel(true).setContentTitle(topic)
						.setContentText(content).setContentIntent(intent)
						.setSmallIcon(icon).setWhen(System.currentTimeMillis());
				notification = builder.getNotification();
			}
			noManager.notify(Integer.valueOf((int) (Math.random() * 1000)),
					notification);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getToken(Context context){
		SharedPreferences sp = context.getSharedPreferences(DEMO_SP_FILENAME, Context.MODE_PRIVATE);
		return sp.getString(DEMO_SP_KEY, "");
	}
	
	public static boolean putToken(Context context, String token) {
		SharedPreferences sp = context.getSharedPreferences(DEMO_SP_FILENAME, Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putString(DEMO_SP_KEY, token);
		return editor.commit();
	}

	}
