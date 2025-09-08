-- =====================================================================
-- BORRAR TABLAS SI YA EXISTEN (para reiniciar la BD)
-- =====================================================================
DROP TABLE IF EXISTS VI_ITEMRENTADO;
DROP TABLE IF EXISTS VI_ITEMS;
DROP TABLE IF EXISTS VI_TIPOITEM;
DROP TABLE IF EXISTS VI_CLIENTES;

-- =====================================================================
-- TABLA DE CLIENTES
-- =====================================================================
CREATE TABLE VI_CLIENTES (
    documento BIGINT PRIMARY KEY,
    nombre TEXT NOT NULL,
    telefono TEXT,
    direccion TEXT,
    email TEXT,
    vetado BOOLEAN DEFAULT 0
);

-- =====================================================================
-- TABLA DE TIPO DE ITEM (categorías de ítems)
-- =====================================================================
CREATE TABLE VI_TIPOITEM (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    descripcion TEXT NOT NULL
);

-- =====================================================================
-- TABLA DE ITEMS (películas, videojuegos, etc.)
-- =====================================================================
CREATE TABLE VI_ITEMS (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre TEXT NOT NULL,
    descripcion TEXT,
    fechalanzamiento DATE,
    tarifaxdia REAL,
    formatorenta TEXT,
    genero TEXT,
    TIPOITEM_id INTEGER NOT NULL,
    FOREIGN KEY (TIPOITEM_id) REFERENCES VI_TIPOITEM(id)
);

-- =====================================================================
-- TABLA DE ÍTEMS RENTADOS (relación cliente - ítem)
-- =====================================================================
CREATE TABLE VI_ITEMRENTADO (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    CLIENTES_documento BIGINT NOT NULL,
    ITEMS_id INT NOT NULL,
    fechainiciorenta DATE NOT NULL,
    fechafinrenta DATE,
    FOREIGN KEY (CLIENTES_documento) REFERENCES VI_CLIENTES(documento),
    FOREIGN KEY (ITEMS_id) REFERENCES VI_ITEMS(id)
);

-- =====================================================================
-- DATOS DE PRUEBA
-- =====================================================================

-- Tipos de ítem
INSERT INTO VI_TIPOITEM (descripcion) VALUES ('Película');
INSERT INTO VI_TIPOITEM (descripcion) VALUES ('Videojuego');
INSERT INTO VI_TIPOITEM (descripcion) VALUES ('Serie');

-- Clientes
INSERT INTO VI_CLIENTES (documento, nombre, telefono, direccion, email, vetado)
VALUES (123, 'Juan Pérez', '3001234567', 'Calle 123', 'juan@example.com', 0);

INSERT INTO VI_CLIENTES (documento, nombre, telefono, direccion, email, vetado)
VALUES (456, 'María Gómez', '3019876543', 'Carrera 45', 'maria@example.com', 0);

-- Items
INSERT INTO VI_ITEMS (nombre, descripcion, fechalanzamiento, tarifaxdia, formatorenta, genero, TIPOITEM_id)
VALUES ('Matrix', 'Película de ciencia ficción', '1999-03-31', 5000, 'DVD', 'Acción', 1);

INSERT INTO VI_ITEMS (nombre, descripcion, fechalanzamiento, tarifaxdia, formatorenta, genero, TIPOITEM_id)
VALUES ('FIFA 21', 'Videojuego de fútbol', '2020-10-09', 7000, 'BluRay', 'Deportes', 2);

-- Items rentados
INSERT INTO VI_ITEMRENTADO (CLIENTES_documento, ITEMS_id, fechainiciorenta, fechafinrenta)
VALUES (123, 1, '2025-09-01', '2025-09-05');

INSERT INTO VI_ITEMRENTADO (CLIENTES_documento, ITEMS_id, fechainiciorenta, fechafinrenta)
VALUES (456, 2, '2025-09-02', NULL);