/**
 * Created by Dmitry Popov on 12.07.2024.
 */
package ru.vsibi.androidcomponents_reader.ui.screens.service

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel

@Composable
fun ServiceContent(modifier: Modifier = Modifier) {

    val vm = koinViewModel<ServiceViewModel>()

    val viewState = vm.viewState.collectAsState()

    var boundChecked by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = boundChecked) {
        if (boundChecked) {
            vm.bindService()
        } else {
            vm.unbindService()
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Bound Service")

        Row(
            modifier = Modifier.padding(24.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(text = "Стоп")
            Switch(checked = boundChecked, onCheckedChange = { boundChecked = !boundChecked })
            Text(text = "Пуск")
        }

        if(boundChecked){
            Text(modifier = Modifier.padding(24.dp), text = viewState.value.word)

            Text(text = "Проверить текущее значение")

            Button(onClick = {
                vm.onCheckClicked()
            }) {
                Text("Check")
            }

            Text(text = "Тик + 1")

            Button(onClick = {
                vm.onTickClicked()
            }) {
                Text("Tick")
            }
        }
    }

}