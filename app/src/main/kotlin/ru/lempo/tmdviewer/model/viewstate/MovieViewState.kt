package ru.lempo.tmdviewer.model.viewstate

import ru.lempo.tmdviewer.model.wrapper.MovieWrapper

/**
 * Author: Oksana Pokrovskaya
 * Email: op@trinitydigital.ru
 */
interface MovieViewState {
    fun isLoading() = this is Loading

    class Loading : MovieViewState

    class Movie(val movie: MovieWrapper) : MovieViewState

    class Error(val error: Throwable) : MovieViewState
}