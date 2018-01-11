package ru.lempo.tmdviewer.presentation

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import ru.lempo.tmdviewer.core.Core
import ru.lempo.tmdviewer.interactor.MovieInteractor
import ru.lempo.tmdviewer.model.viewstate.MovieViewState
import javax.inject.Inject

/**
 * Author: Oksana Pokrovskaya
 * Email: op@trinitydigital.ru
 */
@StateStrategyType(AddToEndSingleStrategy::class)
interface MovieView : MvpView {
    fun render(movieViewState: MovieViewState)
    fun shareMovie()
}

@InjectViewState
class MoviePresenter(movieId: Int) : MvpPresenter<MovieView>() {

    @Inject
    lateinit var movieInteractor: MovieInteractor

    init {
        Core.instance.plusMovieComponent().inject(this)
        movieInteractor.getMovie(movieId).subscribe {
            viewState.render(it)
        }
    }

    fun shareMovie() = viewState.shareMovie()
}