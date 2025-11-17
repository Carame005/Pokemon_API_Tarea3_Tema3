package com.example.pokemon.web

import com.example.pokemon.service.PokemonService
import com.example.pokemon.web.dto.PokemonRequest
import com.example.pokemon.web.dto.PokemonResponse
import com.example.pokemon.web.mapper.PokemonMapper
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/api/pokemon")
class PokemonController(
    private val service: PokemonService
) {
    @GetMapping
    fun list(): List<PokemonResponse> =
        service.list().map(PokemonMapper::toResponse)

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long): PokemonResponse =
        PokemonMapper.toResponse(service.get(id))

    @PostMapping
    fun create(@RequestBody req: PokemonRequest): ResponseEntity<PokemonResponse?> {
        val saved = service.create(req)
        return ResponseEntity.created(URI.create("/api/pokemon/${saved.id}"))
            .body(PokemonMapper.toResponse(saved))
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody req: PokemonRequest): PokemonResponse =
        PokemonMapper.toResponse(service.update(id, req))

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Unit> {
        service.delete(id)
        return ResponseEntity.noContent().build()
    }
}
