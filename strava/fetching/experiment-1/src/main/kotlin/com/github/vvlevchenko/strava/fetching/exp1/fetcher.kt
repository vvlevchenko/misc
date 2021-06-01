package com.github.vvlevchenko.strava.fetching.exp1

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.*
import io.swagger.annotations.SecurityDefinition
import io.swagger.client.ApiClient
import io.swagger.client.api.AthletesApi
import io.swagger.client.api.GearsApi

fun main() {
    ApiClient().let {
        it.setAccessToken("")
        it.setDebugging(true)
        AthletesApi(it).apply {
            loggedInAthlete
        }
        GearsApi(it).apply {

        }
    }
}