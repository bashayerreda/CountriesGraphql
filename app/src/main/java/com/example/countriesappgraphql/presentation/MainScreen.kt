package com.example.countriesappgraphql.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.countriesappgraphql.domain.model.DetailedCountry
import com.example.countriesappgraphql.domain.model.SimpleCountry


@Composable
fun MainScreen(modifier: Modifier = Modifier ,viewModelState: CountriesViewModel.CountriesState,onDialogClick : () -> Unit = {},
               onDismiss : () -> Unit = {}
               ){
    Box(modifier = Modifier.fillMaxSize()) {
        if(viewModelState.loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize()) {
                items(viewModelState.countriesList){ listOfCountries ->
                    CountryItem(simpleCountry = listOfCountries, modifier = modifier.fillMaxSize())

                }
            }

        }
    }

}
@Composable
fun CountryItem(simpleCountry: SimpleCountry,modifier: Modifier = Modifier, ){
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(text =  simpleCountry.emoji, fontSize = 30.sp )
        Spacer(modifier = Modifier.width(16.dp))
        Column() {
            Text(text = simpleCountry.name, fontSize = 24.sp)
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = simpleCountry.capital)
        }
    }

}

