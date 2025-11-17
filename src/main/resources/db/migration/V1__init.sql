CREATE TABLE tipos_pokemon (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL UNIQUE,
    descripcion VARCHAR(255)
);

CREATE TABLE pokemon (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    tipo1 VARCHAR(255) NOT NULL,
    tipo2 VARCHAR(255),
    descripcion TEXT,
    imagen_url VARCHAR(255),
    lat DOUBLE NOT NULL,
    lng DOUBLE NOT NULL,
    tipo_id BIGINT NOT NULL,
    FOREIGN KEY (tipo_id) REFERENCES tipos_pokemon(id)
);


CREATE INDEX IF NOT EXISTS idx_pokemon_tipo ON pokemon(tipo_id);



