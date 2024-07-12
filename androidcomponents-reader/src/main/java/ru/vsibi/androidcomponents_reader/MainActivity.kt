package ru.vsibi.androidcomponents_reader

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import cafe.adriel.voyager.navigator.Navigator
import org.koin.compose.KoinContext
import ru.vsibi.androidcomponents_reader.ui.screens.main.MainScreen
import ru.vsibi.androidcomponents_reader.ui.theme.AndroidComponentsAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AndroidComponentsAppTheme {
                KoinContext {
                    Navigator(screen = MainScreen())
                }
            }
        }
    }

}