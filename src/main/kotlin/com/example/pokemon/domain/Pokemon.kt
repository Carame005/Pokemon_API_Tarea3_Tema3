package com.example.pokemon.domain

import jakarta.persistence.*

@Entity
@Table(name = "pokemon")
data class Pokemon (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val nombre: String,
    val tipo1 : String,
    val tipo2 : String?,
    val descripcion : String,

    @Column(name = "imagen_url")
    val imagenUrl : String? = null,

    val lat: Double,
    val lng: Double,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tipo_id")
    val tipo: TipoPokemon
)