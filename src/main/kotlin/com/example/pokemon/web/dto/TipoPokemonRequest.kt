package com.example.pokemon.web.dto

import jakarta.validation.constraints.NotBlank

data class TipoPokemonRequest(
    @field:NotBlank val nombre: String,
    val descripcion: String? = null
)