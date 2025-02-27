package ru.vsibi.androidcomponents_writer.ui.screens.content_provider

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions

object ContentProviderTab : Tab {

    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(Icons.Default.Share)

            return remember {
                TabOptions(
                    index = 1u,
                    title = "Content Provider",
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        ContentProviderContent()
    }
}
