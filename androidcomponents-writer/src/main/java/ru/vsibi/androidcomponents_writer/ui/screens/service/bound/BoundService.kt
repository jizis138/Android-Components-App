/**
 * Created by Dmitry Popov on 12.07.2024.
 */
package ru.vsibi.androidcomponents_writer.ui.screens.service.bound

import android.app.Service
import android.content.Intent
import android.content.pm.ServiceInfo.FOREGROUND_SERVICE_TYPE_SHORT_SERVICE
import android.os.Build
import android.os.IBinder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.vsibi.androidcomponents_writer.WriterBound
import ru.vsibi.androidcomponents_writer.ui.screens.service.foreground.NotificationUtil

class BoundService : Service() {

    companion object {
        var serviceRunning = false
    }

    private val scope = CoroutineScope(Dispatchers.IO + Job())

    private var ticker = 0

    private var word = "word"

    private val binder: IBinder = object : WriterBound.Stub() {

        override fun sendWord(word: String?) {
            if (word != null) {
                this@BoundService.word = word
            }
        }

        override fun getWord(): String {
            return "${this@BoundService.word} - $ticker"
        }

    }

    override fun onBind(intent: Intent?): IBinder {
        serviceRunning = true

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationUtil.createNotificationChannel(applicationContext)
        }

        scope.launch {
            repeat(1000) {

                delay(1000)

                NotificationUtil.notificationManager(applicationContext).notify(
                    2, NotificationUtil.buildNotification(
                        context = applicationContext,
                        text = "${this@BoundService.word} - $ticker"
                    )
                )
                ticker++
            }
        }
        return binder
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val notification = NotificationUtil.buildNotification(
            context = applicationContext,
            text = "Bound Service Запущен"
        )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            startForeground(2, notification, FOREGROUND_SERVICE_TYPE_SHORT_SERVICE)
        } else {
            startForeground(2, notification)
        }
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceRunning = false
        scope.cancel()
    }

}