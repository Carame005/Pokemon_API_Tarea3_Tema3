package com.example.pokemon.repository

import com.example.pokemon.domain.TipoPokemon
import org.springframework.data.jpa.repository.JpaRepository

interface TipoPokemonRepository : JpaRepository<TipoPokemon, Long> {
    fun existsByNombreIgnoreCase(nombre: String): Boolean
}
