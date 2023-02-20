package com.example.countriesappgraphql.data.remote

import com.apollographql.apollo3.ApolloClient
import com.example.CountriesQuery
import com.example.CountryQuery
import com.example.countriesappgraphql.data.mapper.toDetailedCountry
import com.example.countriesappgraphql.data.mapper.toSimpleCountry
import com.example.countriesappgraphql.domain.model.DetailedCountry
import com.example.countriesappgraphql.domain.model.SimpleCountry
import com.example.countriesappgraphql.domain.repository.CountryRepository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val apolloClient : ApolloClient) : CountryRepository {
    override suspend fun getCountry(code: String): DetailedCountry? {
      return apolloClient
          .query(CountryQuery(code))
          .execute()
          .data
          ?.country
          ?.toDetailedCountry()

    }

    override suspend fun getCountries(): List<SimpleCountry> {
         return apolloClient
             .query(CountriesQuery())
             .execute()
             .data
             ?.countries
             ?.map { it.toSimpleCountry()}
             ?: emptyList()


    }
}