package com.example.keepalive

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Build.VERSION_CODES.JELLY_BEAN_MR2
import android.os.IBinder

/**
 * Created by shiji-xc on 2017/11/14.
 */

class DaemonService : Service() {

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    companion object {
        private val Tag = "DaemonService"
        val NOTICE_ID = 100
    }

    @SuppressLint("ObsoleteSdkInt")
    override fun onCreate() {
        super.onCreate()
        if(Build.VERSION.SDK_INT >= JELLY_BEAN_MR2){
            val builder = Notification.Builder(this)
            builder.setSmallIcon(R.drawable.ic_launcher_background)
            builder.setContentTitle("KeepAppAlive")
            builder.setContentText("DaemonService is running...")
            startForeground(NOTICE_ID,builder.build())
            startService(Intent(this,CancelNoticeService::class.java))
        }else{
            startForeground(NOTICE_ID, Notification())
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // 如果Service被终止
        // 当资源允许情况下，重启service
        return START_STICKY
    }

    @SuppressLint("ObsoleteSdkInt")
    override fun onDestroy() {
        super.onDestroy()
        if(Build.VERSION.SDK_INT >= JELLY_BEAN_MR2){
            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            manager.cancel(NOTICE_ID)
        }
        val intent = Intent(applicationContext, DaemonService::class.java)
        startService(intent)
    }
}
