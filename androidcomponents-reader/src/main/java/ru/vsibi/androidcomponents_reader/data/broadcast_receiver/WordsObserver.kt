/**
 * Created by Dmitry Popov on 12.07.2024.
 */
package ru.vsibi.androidcomponents_reader.data.broadcast_receiver

import android.content.Context
import android.content.IntentFilter
import android.os.Build
import kotlinx.coroutines.flow.Flow
import ru.vsibi.androidcomponents_common.broadcast_receiver.BroadcastConst

class WordsObserver (private val context : Context) {

    private val receiver = WordsBroadcastReceiver()

    fun subscribe() : Flow<String> {
        val intentFilter = IntentFilter(BroadcastConst.WORD_BROADCAST)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.registerReceiver(receiver, intentFilter, Context.RECEIVER_EXPORTED)
        } else{
            context.registerReceiver(receiver, intentFilter)
        }

        return receiver.flow
    }

    fun unsubscribe(){
        context.unregisterReceiver(receiver)
    }

}