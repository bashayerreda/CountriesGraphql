package com.example.countriesappgraphql

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.countriesappgraphql.presentation.CountriesViewModel
import com.example.countriesappgraphql.presentation.MainScreen
import com.example.countriesappgraphql.ui.theme.GraphQlCountriesAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GraphQlCountriesAppTheme {
                val viewModel = hiltViewModel<CountriesViewModel>()
                val state by viewModel.stateOfCountries.collectAsState()
                MainScreen(viewModelState = state, modifier = Modifier.fillMaxSize().padding(8.dp), onClickItem = viewModel::selectCountry, dismiss = { viewModel.dismissDialog()

                })
            }
        }
    }
}