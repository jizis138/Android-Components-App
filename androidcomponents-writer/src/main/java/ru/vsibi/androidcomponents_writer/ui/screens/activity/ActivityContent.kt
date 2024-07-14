/**
 * Created by Dmitry Popov on 11.07.2024.
 */
package ru.vsibi.androidcomponents_writer.ui.screens.activity

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import ru.vsibi.androidcomponents_writer.ui.screens.activity.numbers_task.Number1Activity
import ru.vsibi.androidcomponents_writer.ui.screens.activity.words_task.Word1Activity

@Composable
fun ActivityContent(modifier: Modifier = Modifier) {

    val context = LocalContext.current

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(onClick = {
            val intent = Intent(context, Number1Activity::class.java)

            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

            context.startActivity(intent)
        }) {
            Text(text = "Numbers Task")
        }

        Button(onClick = {
            val intent = Intent(context, Word1Activity::class.java)

            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

            context.startActivity(intent)
        }) {
            Text(text = "Words Task")
        }

    }

}