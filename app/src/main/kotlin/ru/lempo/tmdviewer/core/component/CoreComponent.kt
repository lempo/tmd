package ru.lempo.tmdviewer.core.component

import dagger.Component
import ru.lempo.tmdviewer.core.module.ConfigurationModule
import ru.lempo.tmdviewer.core.module.MoviesListModule
import ru.lempo.tmdviewer.core.module.NetworkModule
import ru.lempo.tmdviewer.interactor.MovieInteractor
import ru.lempo.tmdviewer.interactor.MoviesListInteractor
import ru.lempo.tmdviewer.model.wrapper.MovieWrapper
import javax.inject.Singleton

/**
 * Author: Oksana Pokrovskaya
 * Email: op@trinitydigital.ru
 */
@Singleton
@Component(modules = arrayOf(NetworkModule::class, ConfigurationModule::class))
interface CoreComponent {
    fun plusMoviesListComponent(moviesListModule: MoviesListModule): MoviesListComponent
    fun inject(moviesListInteractor: MoviesListInteractor)
    fun inject(movieInteractor: MovieInteractor)
    fun inject(movieWrapper: MovieWrapper)
}