/**
 * Created by Dmitry Popov on 11.07.2024.
 */
package ru.vsibi.androidcomponents_reader.ui.screens.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.TabDisposable
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import ru.vsibi.androidcomponents_common.util.TabNavigationItem
import ru.vsibi.androidcomponents_reader.ui.screens.activity.ActivityTab
import ru.vsibi.androidcomponents_reader.ui.screens.broadcast_receiver.BroadcastReceiverTab
import ru.vsibi.androidcomponents_reader.ui.screens.content_resolver.ContentResolverTab
import ru.vsibi.androidcomponents_reader.ui.screens.service.ServiceTab

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent(modifier: Modifier = Modifier) {
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.Black,
            darkIcons = false
        )
    }

    TabNavigator(
        ActivityTab,
        tabDisposable = {
            TabDisposable(
                navigator = it,
                tabs = listOf(ActivityTab, ContentResolverTab, BroadcastReceiverTab, ServiceTab)
            )
        }
    ) { tabNavigator ->

        Scaffold(
            topBar = {
                TopAppBar(
                    modifier = Modifier.statusBarsPadding(),
                    title = {
                        Text(
                            text = tabNavigator.current.options.title
                        )
                    }
                )
            },
            content = { innerPaddings ->
                Box(Modifier.padding(innerPaddings)) {
                    CurrentTab()
                }
            },
            bottomBar = {
                BottomAppBar(
                    modifier = Modifier
                        .navigationBarsPadding()
                ) {
                    TabNavigationItem(ActivityTab)
                    TabNavigationItem(ContentResolverTab)
                    TabNavigationItem(BroadcastReceiverTab)
                    TabNavigationItem(ServiceTab)
                }
            }
        )
    }

}

