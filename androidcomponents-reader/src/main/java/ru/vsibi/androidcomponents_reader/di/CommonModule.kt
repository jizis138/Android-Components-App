package ru.vsibi.androidcomponents_reader.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.vsibi.androidcomponents_reader.data.broadcast_receiver.WordsObserver
import ru.vsibi.androidcomponents_reader.data.content_resolver.ReaderContentResolver
import ru.vsibi.androidcomponents_reader.data.repository_impl.WordsRepositoryImpl
import ru.vsibi.androidcomponents_reader.domain.repository.WordsRepository
import ru.vsibi.androidcomponents_reader.domain.usecases.WordsUseCase
import ru.vsibi.androidcomponents_reader.ui.screens.broadcast_receiver.BroadcastReceiverViewModel
import ru.vsibi.androidcomponents_reader.ui.screens.content_resolver.ContentResolverViewModel
import ru.vsibi.androidcomponents_reader.ui.screens.service.ServiceReader
import ru.vsibi.androidcomponents_reader.ui.screens.service.ServiceViewModel

object CommonModule {

    operator fun invoke() = module {
        singleOf(::ReaderContentResolver)
        singleOf(::WordsObserver)


        viewModelOf(::ContentResolverViewModel)

        viewModelOf(::BroadcastReceiverViewModel)

        viewModelOf(::ServiceViewModel)

        singleOf(::ServiceReader)

        singleOf(::WordsRepositoryImpl) bind WordsRepository::class

        factoryOf(::WordsUseCase)
    }

}