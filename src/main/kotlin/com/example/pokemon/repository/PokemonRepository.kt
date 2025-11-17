package com.example.pokemon.repository

import com.example.pokemon.domain.Pokemon
import org.springframework.data.jpa.repository.JpaRepository

interface PokemonRepository : JpaRepository<Pokemon, Long>