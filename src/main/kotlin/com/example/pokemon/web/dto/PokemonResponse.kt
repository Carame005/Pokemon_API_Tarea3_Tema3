package com.example.pokemon.web.dto

class PokemonResponse (
    val id: Long,
    val nombre: String,
    val tipo1 : String,
    val tipo2 : String?,
    val descripcion : String,
    val imagenUrl : String? = null,
    val lat: Double,
    val lng: Double,
    val tipo : TipoPokemonResponse
)