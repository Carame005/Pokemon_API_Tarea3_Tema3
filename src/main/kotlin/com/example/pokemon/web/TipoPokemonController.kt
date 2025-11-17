package com.example.pokemon.web


import com.example.pokemon.service.TipoPokemonService
import com.example.pokemon.web.dto.TipoPokemonRequest
import com.example.pokemon.web.dto.TipoPokemonResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/api/tipos")
class TipoPokemonController(
    private val service: TipoPokemonService
) {
    @GetMapping
    fun list(): List<TipoPokemonResponse> =
        service.list().map { TipoPokemonResponse(it.id!!, it.nombre, it.descripcion) }

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long): TipoPokemonResponse =
        service.get(id).let { TipoPokemonResponse(it.id!!, it.nombre, it.descripcion) }

    @PostMapping
    fun create(@RequestBody req: TipoPokemonRequest): ResponseEntity<TipoPokemonResponse> {
        val saved = service.create(req)
        val body = TipoPokemonResponse(saved.id!!, saved.nombre, saved.descripcion)
        return ResponseEntity.created(URI.create("/api/tipos/${saved.id}")).body(body)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody req: TipoPokemonRequest): TipoPokemonResponse {
        val updated = service.update(id, req)
        return TipoPokemonResponse(updated.id!!, updated.nombre, updated.descripcion)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Unit> {
        service.delete(id)
        return ResponseEntity.noContent().build()
    }
}
