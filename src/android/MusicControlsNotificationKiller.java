package com.homerours.musiccontrols;

import android.app.Service;
import android.os.IBinder;
import android.app.NotificationManager;
import android.content.Intent;

public class MusicControlsNotificationKiller extends Service {
	private final IBinder mBinder = new KillBinder(this);

	@Override
	public IBinder onBind(Intent intent) {
		return this.mBinder;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		return Service.START_STICKY;
	}

	@Override
	public void onCreate() {
		this.removeNotification();
	}

	@Override
	public void onDestroy() {
		this.removeNotification();
	}

	@Override
	public void onTaskRemoved(Intent rootIntent) {
		this.removeNotification();
		this.stopSelf();
	}

	private void removeNotification() {
		NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		notificationManager.cancel(MusicControls.NOTIFICATION_ID);
	}
}
