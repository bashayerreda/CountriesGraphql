package com.example.countriesappgraphql.domain.repository

import com.example.countriesappgraphql.domain.model.DetailedCountry
import com.example.countriesappgraphql.domain.model.SimpleCountry

interface CountryRepository {
    suspend fun getCountry(code : String) : DetailedCountry?
    suspend fun getCountries() : List<SimpleCountry>
}