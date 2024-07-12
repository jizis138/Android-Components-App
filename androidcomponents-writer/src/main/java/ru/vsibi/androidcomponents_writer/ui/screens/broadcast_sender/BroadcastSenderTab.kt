package ru.vsibi.androidcomponents_writer.ui.screens.broadcast_sender

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions

object BroadcastSenderTab : Tab {

    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(Icons.Default.MailOutline)

            return remember {
                TabOptions(
                    index = 1u,
                    title = "Broadcast Sender",
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        BroadcastSenderContent()
    }
}
