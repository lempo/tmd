package ru.lempo.tmdviewer.network.response

import ru.lempo.tmdviewer.model.realm.Movie

/**
 * Author: Oksana Pokrovskaya
 * Email: op@trinitydigital.ru
 */
class MoviesResponse(
        val page: Int,
        val total_pages: Int,
        val results: List<Movie>
)