package ph.kodego.alfaro.vismarjay.pokemonapi.api

import ph.kodego.alfaro.vismarjay.pokemonapi.models.PokemonInfoResponse
import ph.kodego.alfaro.vismarjay.pokemonapi.models.PokemonListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonAPI {
    @GET("pokemon/")
    fun getList(
        @Query("offset") startIndex:Int,
        @Query("limit") limit:Int)
            : Call<PokemonListResponse>

    @GET("pokemon/{pokemonId}/")
    fun getPokemonInfo(
        @Path("pokemonId") pokemonID:Int)
            : Call<PokemonInfoResponse>
}