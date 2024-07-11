package ru.vsibi.androidcomponents_reader.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.vsibi.androidcomponents_reader.data.content_resolver.ReaderContentResolver
import ru.vsibi.androidcomponents_reader.data.repository_impl.WordsRepositoryImpl
import ru.vsibi.androidcomponents_reader.domain.repository.WordsRepository
import ru.vsibi.androidcomponents_reader.domain.usecases.WordsUseCase
import ru.vsibi.androidcomponents_reader.ui.screens.content_resolver.ContentResolverViewModel

object CommonModule {

    operator fun invoke() = module {
        singleOf(::ReaderContentResolver)

        viewModelOf(::ContentResolverViewModel)

        singleOf(::WordsRepositoryImpl) bind WordsRepository::class

        factoryOf(::WordsUseCase)
    }

}