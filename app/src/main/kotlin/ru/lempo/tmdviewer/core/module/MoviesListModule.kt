package ru.lempo.tmdviewer.core.module

import dagger.Module
import dagger.Provides
import ru.lempo.tmdviewer.core.component.MoviesListScope
import ru.lempo.tmdviewer.interactor.MoviesListInteractor

/**
 * Author: Oksana Pokrovskaya
 * Email: op@trinitydigital.ru
 */
@Module
class MoviesListModule {
    @Provides
    @MoviesListScope
    fun provideMoviesListInteractor() = MoviesListInteractor()
}