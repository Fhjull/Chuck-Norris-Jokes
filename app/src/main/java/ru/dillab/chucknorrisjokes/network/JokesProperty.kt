package ru.dillab.chucknorrisjokes.network

import com.squareup.moshi.Json

/**
 * JSON data example we get with http://api.icndb.com/jokes/random/3 call
 * { "type": "success", "value": [ { "id": 1, "joke": "Joke 1" }, { "id": 5, "joke": "Joke 5" },
 * { "id": 9, "joke": "Joke 9" } ] }
 */

data class ResponseProperty(
    val type: String,
    @Json(name = "value") val listWithJokes: List<JokesProperty>
)

data class JokesProperty(
    val id: Int,
    val joke: String
)

