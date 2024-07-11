/**
 * Created by Dmitry Popov on 11.07.2024.
 */
package ru.vsibi.androidcomponents_writer.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.vsibi.androidcomponents_writer.data.content_provider.SQLiteDatabaseHelper
import ru.vsibi.androidcomponents_writer.data.content_provider.WordsStorage
import ru.vsibi.androidcomponents_writer.data.content_provider.WordsStorageImpl
import ru.vsibi.androidcomponents_writer.data.repository_impl.WordsRepositoryImpl
import ru.vsibi.androidcomponents_writer.domain.repository.WordsRepository
import ru.vsibi.androidcomponents_writer.domain.usecases.WordsUseCase
import ru.vsibi.androidcomponents_writer.ui.screens.content_provider.ContentProviderViewModel

object CommonModule {

    operator fun invoke() = module {
        viewModelOf(::ContentProviderViewModel)

        singleOf(::WordsStorageImpl) bind WordsStorage::class
        singleOf(::WordsRepositoryImpl) bind WordsRepository::class

        factoryOf(::SQLiteDatabaseHelper)

        factoryOf(::WordsUseCase)
    }
}