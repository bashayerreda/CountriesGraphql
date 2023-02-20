package com.example.countriesappgraphql.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countriesappgraphql.domain.model.DetailedCountry
import com.example.countriesappgraphql.domain.model.SimpleCountry
import com.example.countriesappgraphql.domain.usecases.CountriesUseCases
import com.example.countriesappgraphql.domain.usecases.CountryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(private val countriesUseCases: CountriesUseCases , private val countryUseCase: CountryUseCase) :ViewModel() {

    private val _stateOfCountries = MutableStateFlow(CountriesState())
      val stateOfCountries = _stateOfCountries.asStateFlow()

    init {
        viewModelScope.launch {
            _stateOfCountries.update { it.copy(
                loading = true
            )

            }
            _stateOfCountries.update { it.copy(
               countriesList = countriesUseCases.executeData(),
                loading = false
            ) }

        }
    }

    fun selectCountry(code : String){
        viewModelScope.launch {
            _stateOfCountries.update { it.copy(
                oneCountry = countryUseCase.executeData(code)
            ) }
        }
    }

    fun dismissDialog(){
        viewModelScope.launch {
            _stateOfCountries.update { it.copy(
                oneCountry = null
            ) }
        }
    }


    data class CountriesState(
        val loading : Boolean = false,
        val countriesList : List<SimpleCountry> = listOf(),
       val oneCountry : DetailedCountry? = null
    )
}