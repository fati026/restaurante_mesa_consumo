SELECT id, id_mesa, id_cliente, estado, total, fecha_creacion, fecha_cierre
	FROM public.consumo;CREATE TYPE estado_mesa AS ENUM ('libre', 'ocupada', 'reservada');

-- 2. Gestión de Mesas
CREATE TABLE mesas (
    id SERIAL PRIMARY KEY,
    numero_mesa INT NOT NULL UNIQUE,
    area VARCHAR(50),           -- Ejemplo: zona de comedor, terraza, etc.
    capacidad INT NOT NULL,     -- Número de personas que caben en la mesa.
    estado estado_mesa NOT NULL DEFAULT 'libre',  -- Estado de la mesa.
    id_cliente INT,             -- Clave foránea que relaciona la mesa con un cliente.
    CONSTRAINT fk_cliente FOREIGN KEY (id_cliente) REFERENCES clientes(id)  -- Relación con la tabla clientes.
);

-- 3. Gestión de Reservas
CREATE TABLE reservas (
    id SERIAL PRIMARY KEY,                   -- Clave primaria
    id_mesa INT REFERENCES mesas(id),        -- Clave foránea hacia mesas
	id_cliente INT REFERENCES clientes(id), -- Relación con la tabla clientes
    fecha_hora TIMESTAMP NOT NULL,           -- Fecha y hora de la reserva
    cantidad_personas INT NOT NULL,          -- Cantidad de personas en la reserva
    contacto VARCHAR(100) NOT NULL           -- Información de contacto del cliente
	estado VARCHAR(100) NOT NULL
);


-- 4. Control de Stock
--Tabla de Stock de Productos
CREATE TABLE productos_stock (
    id_producto INT PRIMARY KEY REFERENCES producto(id), -- Clave primaria y foránea hacia producto
    stock_actual INT DEFAULT 0,              -- Cantidad actual en stock
    stock_minimo INT DEFAULT 0               -- Nivel mínimo de stock
);

-- Tabla de Consumos de Stock
CREATE TABLE consumos_stock (
    id SERIAL PRIMARY KEY,                   -- Clave primaria
    id_producto INT REFERENCES producto(id), -- Clave foránea hacia producto
	id_detalle_consumo INT REFERENCES detalle_consumo(id), -- Relación con detalle_consumo
    cantidad INT NOT NULL,                   -- Cantidad consumida
    fecha_hora TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP -- Fecha y hora del consumo
);
-- 5. Gestión de Empleados

--  Tabla de Empleados
CREATE TABLE empleados (
    id SERIAL PRIMARY KEY,                   -- Clave primaria
    nombre VARCHAR(100) NOT NULL,            -- Nombre del empleado
    apellido VARCHAR(100) NOT NULL,          -- Apellido del empleado
    rol VARCHAR(50) NOT NULL,                -- Rol del empleado
    area_asignada VARCHAR(50)                -- Área asignada al empleado
);

-- Tabla de Consumo por Empleado
CREATE TABLE consumo_empleados (
    id SERIAL PRIMARY KEY,                   -- Clave primaria
	id_mesa INT REFERENCES empleados(id),
    id_empleado INT REFERENCES empleados(id), -- Clave foránea hacia empleados
    monto DECIMAL(10, 2) NOT NULL,           -- Monto de la venta
    fecha_hora TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP -- Fecha y hora de la venta
);

-- 6. Métodos de Pago
CREATE TABLE metodos_pago (
    id SERIAL PRIMARY KEY,                   -- Clave primaria
    nombre_metodo VARCHAR(50) NOT NULL       -- Nombre del método de pago
);
-- Tabla de Pagos
CREATE TABLE pagos (
    id SERIAL PRIMARY KEY,                   -- Clave primaria
    id_metodo INT REFERENCES metodos_pago(id), -- Clave foránea hacia métodos de pago
	id_consumo INT REFERENCES consumo(id),   -- Relación con la tabla consumo
    id_cliente INT REFERENCES clientes(id),  -- Relación con la tabla clientes
    monto DECIMAL(10, 2) NOT NULL,           -- Monto del pago
    fecha_hora TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP -- Fecha y hora del pago
); 

-- 7. Sistema de Propinas
CREATE TABLE propinas (
    id SERIAL PRIMARY KEY,                   -- Clave primaria
    id_empleado INT REFERENCES empleados(id), -- Clave foránea hacia empleados
    monto DECIMAL(10, 2) NOT NULL,           -- Monto de la propina
    fecha_hora TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP -- Fecha y hora de la propina
);
CREATE TABLE distribucion_propinas (
    id SERIAL PRIMARY KEY,                   -- Clave primaria
    id_propina INT REFERENCES propinas(id),  -- Relación con la tabla propinas
    id_empleado INT REFERENCES empleados(id), -- Relación con la tabla empleados
    monto DECIMAL(10, 2) NOT NULL            -- Monto asignado al empleado
);

--8. Impresión de Comandas
CREATE TABLE ordenes_comanda (
    id SERIAL PRIMARY KEY,                   -- Clave primaria
    id_consumo INT REFERENCES consumo(id),   -- Clave foránea hacia consumo
    id_producto INT REFERENCES producto(id), -- Clave foránea hacia producto
    cantidad INT NOT NULL,                   -- Cantidad solicitada
    fecha_hora TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP -- Fecha y hora de la impresión
);
-- 10. Integración con Sistemas Externos
CREATE TABLE integracion_sistemas (
    id SERIAL PRIMARY KEY,                   -- Clave primaria
    sistema_externo VARCHAR(100) NOT NULL,   -- Nombre del sistema externo
    evento VARCHAR(255) NOT NULL,            -- Descripción del evento
    fecha_hora TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP -- Fecha y hora del evento
);
-- 11. Configuración del sistema
CREATE TABLE configuracion (
    id SERIAL PRIMARY KEY,                   -- Clave primaria
    parametro VARCHAR(100) NOT NULL UNIQUE,  -- Nombre del parámetro
    valor TEXT NOT NULL                      -- Valor del parámetro
);


DROP TABLE IF EXISTS public.consumo;

CREATE TABLE IF NOT EXISTS public.consumo
(
    id integer NOT NULL DEFAULT nextval('consumo_id_seq'::regclass),
    id_mesa integer,
    id_cliente integer,
	id_empleado integer,
    estado character varying(50) COLLATE pg_catalog."default" NOT NULL,
    total numeric(10,2) NOT NULL,
    fecha_creacion timestamp without time zone NOT NULL DEFAULT CURRENT_TIMESTAMP,
    fecha_cierre timestamp without time zone,
    CONSTRAINT consumo_pkey PRIMARY KEY (id),
    CONSTRAINT consumo_id_cliente_fkey FOREIGN KEY (id_cliente)
        REFERENCES public.clientes (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT consumo_id_mesa_fkey FOREIGN KEY (id_mesa)
        REFERENCES public.mesas (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
	CONSTRAINT consumo_id_empleado_fkey FOREIGN KEY (id_empleado)
        REFERENCES public.empleados (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT consumo_estado_check CHECK (estado::text = ANY (ARRAY['abierto'::character varying, 'cerrado'::character varying]::text[]))
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.consumo
    OWNER to postgres;
	