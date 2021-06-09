package com.homerours.musiccontrols;

import android.app.Notification;
import android.app.Service;
import android.os.Build;
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

	public void setForeground(Notification notification) {
		this.startForeground(MusicControls.NOTIFICATION_ID, notification);
	}

	public void clearForeground() {
		if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
			return;
		}

		this.stopForeground(STOP_FOREGROUND_DETACH);
	}

	private void removeNotification() {
		NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		notificationManager.cancel(MusicControls.NOTIFICATION_ID);
	}
}
