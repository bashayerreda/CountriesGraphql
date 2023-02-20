package com.example.countriesappgraphql.domain.usecases

import com.example.countriesappgraphql.domain.model.SimpleCountry
import com.example.countriesappgraphql.domain.repository.CountryRepository
import javax.inject.Inject

class CountriesUseCases @Inject constructor(private val countryRepo : CountryRepository) {
    suspend fun executeData() : List<SimpleCountry> {
        return countryRepo.getCountries().sortedBy { it.name }
    }

}