package ru.vsibi.androidcomponents_writer.ui.screens.service.foreground

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import ru.vsibi.androidcomponents_writer.R

object NotificationUtil {

    fun notificationManager(context: Context) =
        context.getSystemService(Service.NOTIFICATION_SERVICE) as NotificationManager

    @RequiresApi(Build.VERSION_CODES.O)
    fun createNotificationChannel(context: Context) {
        val channel = NotificationChannel(
            CHANNEL_ID,
            CHANNEL,
            NotificationManager.IMPORTANCE_LOW
        )

        notificationManager(context).createNotificationChannel(channel)
    }

    fun buildNotification(context: Context, text: String): Notification {
        return NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentText(text)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()
    }

    private const val CHANNEL_ID = "CHANNEL_ID"
    private const val CHANNEL = "AC-App-FG-Service"
}