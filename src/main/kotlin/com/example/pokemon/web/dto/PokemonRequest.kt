package com.example.pokemon.web.dto

import jakarta.validation.constraints.NotBlank

data class PokemonRequest(
    @field:NotBlank val nombre: String,
    val tipo1 : String,
    val tipo2 : String?,
    val descripcion : String,
    val imagenUrl : String? = null,
    val lat: Double,
    val lng: Double,
    val tipoId: Long   // FK al tipo
)
