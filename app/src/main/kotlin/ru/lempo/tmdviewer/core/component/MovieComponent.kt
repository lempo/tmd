package ru.lempo.tmdviewer.core.component

import dagger.Subcomponent
import ru.lempo.tmdviewer.core.module.MovieModule
import ru.lempo.tmdviewer.presentation.MoviePresenter
import javax.inject.Scope

/**
 * Author: Oksana Pokrovskaya
 * Email: op@trinitydigital.ru
 */
@MovieScope
@Subcomponent(modules = arrayOf(MovieModule::class))
interface MovieComponent {
    fun inject(moviePresenter: MoviePresenter)
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class MovieScope