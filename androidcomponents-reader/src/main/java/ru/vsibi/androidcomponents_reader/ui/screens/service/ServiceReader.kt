/**
 * Created by Dmitry Popov on 12.07.2024.
 */
package ru.vsibi.androidcomponents_reader.ui.screens.service

import android.app.Activity.BIND_AUTO_CREATE
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log
import ru.vsibi.androidcomponents_writer.WriterBound

class ServiceReader(private val context : Context) {

    var writerBound : WriterBound? = null

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            writerBound = WriterBound.Stub.asInterface(service)
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            writerBound = null
        }
    }


    fun bindToService() {
        try {
            val intent = Intent("writerbound.AIDL")
            val explicitIntent = convertImplicitIntentToExplicitIntent(context, intent)
            if (explicitIntent != null) {
                context.bindService(explicitIntent, serviceConnection, BIND_AUTO_CREATE)
            }
        } catch (e: Exception) {
            Log.i("bindToFiscalService", "e: $e")
        }
    }

    fun unbind(){
        context.unbindService(serviceConnection)
    }

    private fun convertImplicitIntentToExplicitIntent(ct: Context, implicitIntent: Intent): Intent? {
        val pm = ct.packageManager
        val resolveInfoList = pm.queryIntentServices(implicitIntent, 0)
        if (resolveInfoList.size != 1) {
            return null
        }
        val serviceInfo = resolveInfoList[0]
        val component = ComponentName(serviceInfo.serviceInfo.packageName, serviceInfo.serviceInfo.name)
        val explicitIntent = Intent(implicitIntent)
        explicitIntent.component = component
        return explicitIntent
    }
}