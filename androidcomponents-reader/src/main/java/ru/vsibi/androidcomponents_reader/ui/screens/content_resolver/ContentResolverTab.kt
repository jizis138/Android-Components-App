package ru.vsibi.androidcomponents_reader.ui.screens.content_resolver

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions

object ContentResolverTab : Tab {

    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(Icons.Default.Warning)

            return remember {
                TabOptions(
                    index = 1u,
                    title = "Content Resolver",
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        ContentResolverContent()
    }
}
