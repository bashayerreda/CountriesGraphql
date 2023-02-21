package com.example.countriesappgraphql.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.countriesappgraphql.domain.model.DetailedCountry
import com.example.countriesappgraphql.domain.model.SimpleCountry


@Composable
fun MainScreen(modifier: Modifier = Modifier ,viewModelState: CountriesViewModel.CountriesState,onClickItem : (code : String) -> Unit = {},
               dismiss : () -> Unit = {}){
    Box(modifier = Modifier.fillMaxSize()) {
        if(viewModelState.loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
                ) {
                items(viewModelState.countriesList){ listOfCountries ->
                    CountryItem(simpleCountry = listOfCountries, modifier = modifier
                        .fillMaxSize()
                        .clickable {
                            onClickItem(listOfCountries.code)
                        })
                    if (viewModelState.oneCountry !=null)
                    {
                       DetailedCountryDialog(detailedCountry = viewModelState.oneCountry, onDismiss = dismiss,
                           modifier = Modifier
                               .clip(RoundedCornerShape(5.dp))
                               .background(Color.White)
                               .padding(16.dp)
                           )
                    }



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
@Composable
fun DetailedCountryDialog(modifier: Modifier = Modifier, detailedCountry: DetailedCountry, onDismiss : () -> Unit = {}) {
       val loadedLanguages = remember(detailedCountry.languages) {
                 detailedCountry.languages.joinToString()
    }
          Dialog(onDismissRequest = { onDismiss()}) {
          Column(modifier.fillMaxWidth()) {
              Row() {
                  Text(text =  detailedCountry.emoji, fontSize = 30.sp )
                  Spacer(modifier = Modifier.width(16.dp))
                  Text(text = detailedCountry.name, fontSize = 24.sp)
              }
              Text(text = "Continent: " + detailedCountry.continent)
              Spacer(modifier = Modifier.height(8.dp))
              Text(text = "Currency: " + detailedCountry.currency)
              Spacer(modifier = Modifier.height(8.dp))
              Text(text = "Capital: " + detailedCountry.capital)
              Spacer(modifier = Modifier.height(8.dp))
              Text(text = "Language (s): " + loadedLanguages)
              
              
          }


          }
}

