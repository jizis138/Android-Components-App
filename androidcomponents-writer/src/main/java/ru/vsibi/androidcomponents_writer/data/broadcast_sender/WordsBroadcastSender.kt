/**
 * Created by Dmitry Popov on 12.07.2024.
 */
package ru.vsibi.androidcomponents_writer.data.broadcast_sender

import android.content.Context
import android.content.Intent
import ru.vsibi.androidcomponents_common.broadcast_receiver.BroadcastConst

class WordsBroadcastSender(private val context: Context) {

    fun sendBroadcast(key: String, value: String) {

        val intent = Intent()

        intent.setAction(BroadcastConst.WORD_BROADCAST)

        intent.putExtra(key, value)

        context.sendBroadcast(intent)
    }

}