package ru.lempo.tmdviewer.core.component

import dagger.Subcomponent
import ru.lempo.tmdviewer.core.module.MovieModule
import ru.lempo.tmdviewer.core.module.MoviesListModule
import ru.lempo.tmdviewer.presentation.MainPresenter
import javax.inject.Scope

/**
 * Author: Oksana Pokrovskaya
 * Email: op@trinitydigital.ru
 */
@MoviesListScope
@Subcomponent(modules = arrayOf(MoviesListModule::class))
interface MoviesListComponent {
    fun plusMovieComponent(movieModule: MovieModule): MovieComponent
    fun inject(mainPresenter: MainPresenter)
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class MoviesListScope