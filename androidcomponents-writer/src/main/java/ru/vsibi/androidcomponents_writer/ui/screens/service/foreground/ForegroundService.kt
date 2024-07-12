/**
 * Created by Dmitry Popov on 12.07.2024.
 */
package ru.vsibi.androidcomponents_writer.ui.screens.service.foreground

import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder

class ForegroundService : Service() {

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationUtil.createNotificationChannel(applicationContext)
        }

        val notification = NotificationUtil.buildNotification(
            context = applicationContext,
            text = "Foreground Service Запущен"
        )

        startForeground(1, notification)

        return START_STICKY
    }
}