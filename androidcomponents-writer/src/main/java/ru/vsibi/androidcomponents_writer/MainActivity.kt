package ru.vsibi.androidcomponents_writer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import cafe.adriel.voyager.navigator.Navigator
import org.koin.compose.KoinContext
import org.koin.core.context.stopKoin
import ru.vsibi.androidcomponents_writer.ui.screens.main.MainContent
import ru.vsibi.androidcomponents_writer.ui.screens.main.MainScreen
import ru.vsibi.androidcomponents_writer.ui.theme.AndroidComponentsAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            KoinContext {
                AndroidComponentsAppTheme {
                    Navigator(screen = MainScreen())
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stopKoin()
    }
}
