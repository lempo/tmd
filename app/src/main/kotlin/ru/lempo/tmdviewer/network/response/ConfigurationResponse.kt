package ru.lempo.tmdviewer.network.response

/**
 * Author: Oksana Pokrovskaya
 * Email: op@trinitydigital.ru
 */
class ConfigurationResponse(
        val images: ImagesConfiguration
)

class ImagesConfiguration(
        val base_url: String,
        val poster_sizes: List<String>
)