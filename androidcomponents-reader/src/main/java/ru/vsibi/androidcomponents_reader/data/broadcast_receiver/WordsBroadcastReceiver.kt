/**
 * Created by Dmitry Popov on 12.07.2024.
 */
package ru.vsibi.androidcomponents_reader.data.broadcast_receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import ru.vsibi.androidcomponents_common.broadcast_receiver.BroadcastConst

class WordsBroadcastReceiver() : BroadcastReceiver() {

    private var mutableFlow = flowOf<String>()

    val flow: Flow<String> get() = mutableFlow

    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.let {
            if (intent.action == BroadcastConst.WORD_BROADCAST) {
                intent.getStringExtra("word")?.let { word ->
                    mutableFlow = flowOf(word)
                }
            }
        }
    }

}