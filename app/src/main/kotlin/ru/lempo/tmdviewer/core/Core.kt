package ru.lempo.tmdviewer.core

import android.databinding.DataBindingUtil
import android.support.multidex.MultiDexApplication
import io.realm.Realm
import io.realm.RealmConfiguration
import ru.lempo.tmdviewer.core.component.*
import ru.lempo.tmdviewer.core.module.*

/**
 * Author: Oksana Pokrovskaya
 * Email: op@trinitydigital.ru
 */
class Core : MultiDexApplication() {

    companion object {
        lateinit var instance: Core
    }

    lateinit var coreComponent: CoreComponent
    var moviesListComponent: MoviesListComponent? = null
    var movieComponent: MovieComponent? = null

    override fun onCreate() {
        super.onCreate()
        instance = this
        coreComponent = createCoreComponent()

        Realm.init(this)
        RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build()
    }

    private fun createCoreComponent(): CoreComponent =
            DaggerCoreComponent.builder()
                    .networkModule(NetworkModule())
                    .configurationModule(ConfigurationModule(this))
                    .build()

    fun plusMoviesListComponent(): MoviesListComponent {
        if (moviesListComponent == null) {
            moviesListComponent = coreComponent.plusMoviesListComponent(MoviesListModule())
        }
        return moviesListComponent!!
    }

    fun clearMoviesListComponent() {
        moviesListComponent = null
    }

    fun plusMovieComponent(): MovieComponent {
        if (movieComponent == null) {
            movieComponent = moviesListComponent!!.plusMovieComponent(MovieModule())
        }
        return movieComponent!!
    }

    fun clearMovieComponent() {
        movieComponent = null
    }
}