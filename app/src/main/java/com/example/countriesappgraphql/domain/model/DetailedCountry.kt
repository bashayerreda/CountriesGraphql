package com.example.countriesappgraphql.domain.model

data class DetailedCountry(val name:String,
                           val emoji : String,
                           val code : String,
                           val capital :String,
                           val currency: String,
                           val languages: List<String>,
                           val continent: String)