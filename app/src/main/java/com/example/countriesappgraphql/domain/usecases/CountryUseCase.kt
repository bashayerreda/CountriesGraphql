package com.example.countriesappgraphql.domain.usecases

import com.example.countriesappgraphql.domain.model.DetailedCountry
import com.example.countriesappgraphql.domain.model.SimpleCountry
import com.example.countriesappgraphql.domain.repository.CountryRepository
import javax.inject.Inject

class CountryUseCase @Inject constructor(private val countryRepo : CountryRepository) {
    suspend fun executeData(code : String) : DetailedCountry? {
        return countryRepo.getCountry(code)
    }

}