/**
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

  @Entity
@Table(name = "tipos_pokemon")
data class TipoPokemon(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false, unique = true, length = 100)
    val nombre: String,

    val descripcion: String? = null
)
 */

-- Insertar datos en la tabla tipos_pokemon
INSERT INTO tipos_pokemon (id ,nombre, descripcion) VALUES
(1,'Fuego', 'Pokémon de tipo fuego, fuertes contra planta y hielo.'),
(2,'Agua', 'Pokémon de tipo agua, fuertes contra fuego y tierra.'),
(3,'Planta', 'Pokémon de tipo planta, fuertes contra agua y tierra.'),
(4,'Eléctrico', 'Pokémon de tipo eléctrico, fuertes contra agua y volador.')
    ON CONFLICT (id) DO NOTHING;

-- Insertar datos en la tabla pokemon
INSERT INTO pokemon (nombre, tipo1, tipo2, descripcion, imagen_url, lat, lng, tipo_id) VALUES
('Charmander', 'Fuego', NULL, 'Pokémon lagarto de fuego.', 'https://assets.pokemon.com/assets/cms2/img/pokedex/full/004.png', 40.7128, -74.0060, (SELECT id FROM tipos_pokemon WHERE nombre = 'Fuego')),
('Squirtle', 'Agua', NULL, 'Pokémon tortuga de agua.', 'https://assets.pokemon.com/assets/cms2/img/pokedex/full/007.png', 34.0522, -118.2437, (SELECT id FROM tipos_pokemon WHERE nombre = 'Agua')),
('Bulbasaur', 'Planta', 'Veneno', 'Pokémon semilla.', 'https://assets.pokemon.com/assets/cms2/img/pokedex/full/001.png', 51.5074, 0.1278, (SELECT id FROM tipos_pokemon WHERE nombre = 'Planta')),
('Pikachu', 'Eléctrico', NULL, 'Pokémon ratón eléctrico.', 'https://assets.pokemon.com/assets/cms2/img/pokedex/full/025.png', 35.6895, 139.6917, (SELECT id FROM tipos_pokemon WHERE nombre = 'Eléctrico'))
ON CONFLICT DO NOTHING;