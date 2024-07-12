package ru.vsibi.androidcomponents_reader.ui.screens.broadcast_receiver

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions

object BroadcastReceiverTab : Tab {

    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(Icons.Default.MailOutline)

            return remember {
                TabOptions(
                    index = 1u,
                    title = "Broadcast Receiver",
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        BroadcastReceiverContent()
    }
}
