package com.example.keepalive

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.os.SystemClock

/**
 * Created by shiji-xc on 2017/11/14.
 */
class CancelNoticeService : Service(){

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    @SuppressLint("ObsoleteSdkInt")
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN_MR2){
            val builder = Notification.Builder(this)
            builder.setSmallIcon(R.drawable.ic_launcher_background)
            startForeground(DaemonService.NOTICE_ID,builder.build())
        }
        Thread(Runnable {
            kotlin.run {
                SystemClock.sleep(1000)
                stopForeground(true)
                val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                manager.cancel(DaemonService.NOTICE_ID)
                stopSelf()
            }
        }).start()
        return super.onStartCommand(intent, flags, startId)
    }
}