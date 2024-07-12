/**
 * Created by Dmitry Popov on 11.07.2024.
 */
package ru.vsibi.androidcomponents_writer.ui.screens.service

import android.Manifest
import android.content.Intent
import android.os.Build
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import ru.vsibi.androidcomponents_writer.ui.screens.service.background.BackgroundService
import ru.vsibi.androidcomponents_writer.ui.screens.service.bound.BoundService
import ru.vsibi.androidcomponents_writer.ui.screens.service.foreground.ForegroundService

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ServiceContent(modifier: Modifier = Modifier) {

    var foregroundChecked by remember {
        mutableStateOf(false)
    }

    var backgroundChecked by remember {
        mutableStateOf(false)
    }

    val serviceRunning by remember {
        mutableStateOf(if (BoundService.serviceRunning) "запущен" else "не запущен")
    }

    val context = LocalContext.current

    val notificationPermissionState = rememberPermissionState(
        Manifest.permission.POST_NOTIFICATIONS
    )
    LaunchedEffect(notificationPermissionState.status) {
        if (!notificationPermissionState.status.isGranted) {
            notificationPermissionState.launchPermissionRequest()
        }
    }
    LaunchedEffect(key1 = foregroundChecked) {
        val intent = Intent(context, ForegroundService::class.java)
        if (foregroundChecked) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(intent)
            } else {
                context.startService(intent)
            }
        } else {
            context.stopService(intent)
        }
    }
    LaunchedEffect(key1 = backgroundChecked) {
        val intent = Intent(context, BackgroundService::class.java)
        if (backgroundChecked) {
            context.startService(intent)
        } else {
            context.stopService(intent)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(text = "Foreground Service")

        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(16.dp)) {

            Text(text = "Стоп")

            Switch(checked = foregroundChecked, onCheckedChange = { foregroundChecked = !foregroundChecked })

            Text(text = "Пуск")

        }

        Text(text = "Background Service")

        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(16.dp)) {

            Text(text = "Стоп")

            Switch(checked = backgroundChecked, onCheckedChange = { backgroundChecked = !backgroundChecked })

            Text(text = "Пуск")

        }

        Text(text = "Bound Service $serviceRunning")


        Text(text = "Кнопка запуска в Reader-клиенте")

    }

}