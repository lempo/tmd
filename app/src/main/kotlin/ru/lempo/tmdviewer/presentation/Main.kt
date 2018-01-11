package ru.lempo.tmdviewer.presentation

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import ru.lempo.tmdviewer.core.Core
import ru.lempo.tmdviewer.model.viewstate.MoviesListViewState
import ru.lempo.tmdviewer.interactor.MoviesListInteractor
import ru.lempo.tmdviewer.interactor.MoviesListInteractor.Companion.CURRENT
import ru.lempo.tmdviewer.interactor.MoviesListInteractor.Companion.POPULAR
import ru.lempo.tmdviewer.interactor.MoviesListInteractor.Companion.TOP_RATED
import javax.inject.Inject

/**
 * Author: Oksana Pokrovskaya
 * Email: op@trinitydigital.ru
 */
@StateStrategyType(AddToEndSingleStrategy::class)
interface MainView : MvpView {
    fun render(moviesListViewState: MoviesListViewState)
    fun goToMovie(movieId: Int)
}

@InjectViewState
class MainPresenter : MvpPresenter<MainView>() {

    @Inject
    lateinit var moviesListInteractor: MoviesListInteractor

    init {
        Core.instance.plusMoviesListComponent().inject(this)
        loadPopularMovies()
    }

    fun loadPopularMovies() = moviesListInteractor.getMoviesList(POPULAR).subscribe {
        viewState.render(it)
    }

    fun loadTopRatedMovies() = moviesListInteractor.getMoviesList(TOP_RATED).subscribe {
        viewState.render(it)
    }

    fun loadMoreMovies() = moviesListInteractor.getNextPage()?.subscribe {
        viewState.render(it)
    }

    fun refreshMovies() = moviesListInteractor.getMoviesList(CURRENT).subscribe {
        viewState.render(it)
    }

    fun goToMovie(movieId: Int) = viewState.goToMovie(movieId)
}