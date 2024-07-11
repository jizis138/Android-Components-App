/**
 * Created by Dmitry Popov on 11.07.2024.
 */
package ru.vsibi.androidcomponents_writer.ui.screens.content_provider

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.window.Dialog
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import org.koin.compose.koinInject
import ru.vsibi.androidcomponents_common.util.dataOrNull

@Composable
fun ContentProviderContent(modifier: Modifier = Modifier) {

    val vm = koinInject<ContentProviderViewModel>()

    var openWordDialog by remember { mutableStateOf(false) }

    val viewState = vm.viewState.collectAsState()

    Scaffold(
        topBar = {

        },
        content = { paddingValues ->
            Box(modifier = modifier.padding(paddingValues)) {

                LazyColumn {
                    items(viewState.value.words.dataOrNull ?: listOf()) {
                        Text(text = it)
                    }
                }

            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    openWordDialog = true
                }
            ) {
                Icon(imageVector = Icons.Filled.Add, "")
            }
        }
    )

    if (openWordDialog) {
        WordDialog(
            onAcceptWord = { word ->
                vm.addWord(word)
                openWordDialog = false
            }, onDismissRequest = {
                openWordDialog = false
            })
    }
}

@Composable
fun WordDialog(onAcceptWord: (word: String) -> Unit, onDismissRequest: () -> Unit) {
    var showHint by remember { mutableStateOf(true) }
    var textState by remember { mutableStateOf(TextFieldValue()) }

    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {

            Box {
                BasicTextField(
                    modifier = Modifier
                        .background(Color.Transparent)
                        .padding(24.dp)
                        .padding(top = 16.dp)
                        .onFocusEvent {
                            showHint = !it.isFocused
                        },
                    value = textState,
                    textStyle = TextStyle(
                        fontSize = 16.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Start,
                    ),
                    onValueChange = {
                        textState = it
                    },
                    decorationBox = { innerTextField ->
                        Row(
                            Modifier
                                .fillMaxWidth()
                        ) {
                            if (showHint) {
                                Text(
                                    text = "Напишите какое нибудь слово",
                                    fontSize = 14.sp,
                                    color = Color.Gray
                                )
                            }

                            innerTextField()
                        }
                    },
                    keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
                    cursorBrush = SolidColor(White)
                )
                Row(
                    modifier = Modifier.fillMaxSize().padding(16.dp),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(modifier = Modifier.padding(4.dp), onClick = { onDismissRequest() }) {
                        Text(text = "Отмена")
                    }
                    Button(modifier = Modifier.padding(4.dp), onClick = { onAcceptWord(textState.text) }) {
                        Text(text = "Ок")
                    }
                }
            }

        }
    }
}
