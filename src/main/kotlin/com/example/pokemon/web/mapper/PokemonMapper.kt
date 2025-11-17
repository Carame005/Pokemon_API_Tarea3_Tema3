package com.example.pokemon.web.mapper

import com.example.pokemon.domain.Pokemon
import com.example.pokemon.domain.TipoPokemon
import com.example.pokemon.web.dto.PokemonRequest
import com.example.pokemon.web.dto.PokemonResponse
import com.example.pokemon.web.dto.TipoPokemonResponse

object PokemonMapper {
    fun toEntity(req: PokemonRequest, tipo: TipoPokemon) = Pokemon(
        nombre = req.nombre,
        tipo1 = req.tipo1,
        tipo2 = req.tipo2,
        descripcion = req.descripcion,
        imagenUrl = req.imagenUrl,
        lat = req.lat,
        lng = req.lng,
        tipo = tipo
    )

    fun toResponse(p: Pokemon) = PokemonResponse(
        id = p.id!!,
        nombre = p.nombre,
        tipo1 = p.tipo1,
        tipo2 = p.tipo2,
        descripcion = p.descripcion,
        imagenUrl = p.imagenUrl,
        lat = p.lat,
        lng = p.lng,
        tipo = TipoPokemonResponse(
            id = p.tipo.id!!,
            nombre = p.tipo.nombre,
            descripcion = p.tipo.descripcion
        )
    )

    fun merge(entity: Pokemon, req: PokemonRequest, tipo: TipoPokemon) = entity.copy(
        nombre = req.nombre,
        tipo1 = req.tipo1,
        tipo2 = req.tipo2,
        descripcion = req.descripcion,
        imagenUrl = req.imagenUrl,
        lat = req.lat,
        lng = req.lng,
        tipo = tipo
    )
}
