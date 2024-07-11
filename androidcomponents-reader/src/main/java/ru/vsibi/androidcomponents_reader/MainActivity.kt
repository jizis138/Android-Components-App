package ru.vsibi.androidcomponents_reader

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.navigator.Navigator
import org.koin.compose.KoinContext
import ru.vsibi.androidcomponents_reader.data.content_resolver.ReaderContentResolver
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