package com.example.pokemon.service

import com.example.pokemon.domain.Pokemon
import com.example.pokemon.repository.PokemonRepository
import com.example.pokemon.repository.TipoPokemonRepository
import com.example.pokemon.web.dto.PokemonRequest
import com.example.pokemon.web.mapper.PokemonMapper
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PokemonService(
    private val repo: PokemonRepository,
    private val tipoRepo: TipoPokemonRepository
) {
    @Transactional(readOnly = true)
    fun list(): List<Pokemon> = repo.findAll().sortedBy { it.id }

    @Transactional(readOnly = true)
    fun get(id: Long): Pokemon =
        repo.findById(id).orElseThrow { NotFoundException("Camisa id=$id no encontrada") }

    @Transactional
    fun create(req: PokemonRequest): Pokemon {
        val tipo = tipoRepo.findById(req.tipoId).orElseThrow {
            NotFoundException("Tipo id=${req.tipoId} no encontrado")
        }
        return repo.save(PokemonMapper.toEntity(req, tipo))
    }

    @Transactional
    fun update(id: Long, req: PokemonRequest): Pokemon {
        val current = get(id)
        val tipo = tipoRepo.findById(req.tipoId).orElseThrow {
            NotFoundException("Tipo id=${req.tipoId} no encontrado")
        }
        return repo.save(PokemonMapper.merge(current, req, tipo))
    }

    @Transactional
    fun delete(id: Long) {
        if (!repo.existsById(id)) throw NotFoundException("Camisa id=$id no encontrada")
        repo.deleteById(id)
    }
}
