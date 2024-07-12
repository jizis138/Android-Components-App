/**
 * Created by Dmitry Popov on 12.07.2024.
 */
package ru.vsibi.androidcomponents_reader.ui.screens.service

import ru.vsibi.androidcomponents_common.util.BaseViewModel

class ServiceViewModel(private val serviceReader: ServiceReader) : BaseViewModel<ServiceState>() {

    override fun createInitialState(): ServiceState {
        return ServiceState("")
    }

    fun bindService() {
        serviceReader.bindToService()
    }

    fun unbindService() {
        try {
            serviceReader.unbind()
        } catch (e: IllegalArgumentException) {
            println("cauth $e")
        }
    }

    fun onCheckClicked() {
        updateState { state ->
            state.copy(word = serviceReader.writerBound?.word ?: "Нет соединения с сервисом Writer")
        }
    }

    var counter = 0

    fun onTickClicked() {
        serviceReader.writerBound?.sendWord("tick ${++counter}")
    }

}