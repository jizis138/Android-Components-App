/**
 * Created by Dmitry Popov on 11.07.2024.
 */
package ru.vsibi.androidcomponents_writer.ui.screens.broadcast_sender

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.compose.koinInject

@Composable
fun BroadcastSenderContent(modifier: Modifier = Modifier) {

    val vm = koinInject<BroadcastSenderViewModel>()

    var showHint by remember { mutableStateOf(true) }
    var textState by remember { mutableStateOf(TextFieldValue()) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        BasicTextField(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent)
                .padding(24.dp)
                .padding(top = 16.dp)
                .onFocusEvent {
                    showHint = !it.isFocused
                },
            value = textState,
            textStyle = TextStyle(
                fontSize = 16.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
            ),
            onValueChange = {
                textState = it
            },
            decorationBox = { innerTextField ->
                Row(
                    Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    if (showHint) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "Напишите какое нибудь слово",
                            fontSize = 14.sp,
                            color = Color.Gray,
                            textAlign = TextAlign.Center
                        )
                    }

                    innerTextField()
                }
            },
            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
            cursorBrush = SolidColor(White)
        )

        Button(modifier = Modifier.padding(4.dp), onClick = {
            vm.sendWord(textState.text)
            textState = TextFieldValue("")
        }) {
            Text(text = "Отправить")
        }

    }

}