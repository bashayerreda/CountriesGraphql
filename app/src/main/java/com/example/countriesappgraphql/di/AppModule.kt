package com.example.countriesappgraphql.di

import androidx.compose.ui.unit.Constraints
import com.apollographql.apollo3.ApolloClient
import com.example.countriesappgraphql.data.remote.RepositoryImpl
import com.example.countriesappgraphql.domain.repository.CountryRepository
import com.example.countriesappgraphql.domain.usecases.CountriesUseCases
import com.example.countriesappgraphql.domain.usecases.CountryUseCase
import com.example.countriesappgraphql.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideApolloClient(): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl(Constants.Server_url)
            .build()
    }

    @Provides
    @Singleton
    fun provideCountryRepo(apolloClient : ApolloClient): CountryRepository {
        return RepositoryImpl(apolloClient)
    }
   /* @Provides
    @Singleton
    fun provideCountriesUseCase(countryRepository: CountryRepository): CountriesUseCases {
        return CountriesUseCases(countryRepository)
    }
    @Provides
    @Singleton
    fun provideCountryUseCase(countryRepository: CountryRepository): CountryUseCase {
        return CountryUseCase(countryRepository)
    }
*/

}