package com.example.countriesappgraphql.data.mapper

import com.example.CountriesQuery
import com.example.CountryQuery
import com.example.countriesappgraphql.domain.model.DetailedCountry
import com.example.countriesappgraphql.domain.model.SimpleCountry

fun CountriesQuery.Country.toSimpleCountry() : SimpleCountry {
    return SimpleCountry(
        name = name,
        code = code,
        emoji = emoji,
        capital = capital ?: "No Capital here"
    )
}
fun CountryQuery.Country.toDetailedCountry() : DetailedCountry {
    return DetailedCountry(name=name, emoji = emoji,code=code,capital=capital ?: "No Capital here",currency=currency ?: "No currency",languages=languages.mapNotNull { it.name },continent=continent.name)
}