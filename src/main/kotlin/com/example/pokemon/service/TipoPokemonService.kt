package com.example.pokemon.service

import com.example.pokemon.domain.TipoPokemon
import com.example.pokemon.repository.TipoPokemonRepository
import com.example.pokemon.web.dto.TipoPokemonRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TipoPokemonService(
    private val repo: TipoPokemonRepository
) {
    @Transactional(readOnly = true)
    fun list(): List<TipoPokemon> = repo.findAll().sortedBy { it.id }

    @Transactional(readOnly = true)
    fun get(id: Long): TipoPokemon =
        repo.findById(id).orElseThrow { NotFoundException("Tipo id=$id no encontrado") }

    @Transactional
    fun create(req: TipoPokemonRequest): TipoPokemon {
        if (repo.existsByNombreIgnoreCase(req.nombre))
            throw IllegalArgumentException("Ya existe un tipo con nombre '${req.nombre}'")
        return repo.save(TipoPokemon(nombre = req.nombre.trim(), descripcion = req.descripcion))
    }

    @Transactional
    fun update(id: Long, req: TipoPokemonRequest): TipoPokemon {
        val current = get(id)
        return repo.save(current.copy(nombre = req.nombre.trim(), descripcion = req.descripcion))
    }

    @Transactional
    fun delete(id: Long) {
        if (!repo.existsById(id)) throw NotFoundException("Tipo id=$id no encontrado")
        repo.deleteById(id)
    }
}
