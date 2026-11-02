BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "cliente" (
	"idcliente"	INTEGER,
	"nombre"	TEXT,
	"apellidos"	TEXT,
	"dni"	TEXT,
	"email"	TEXT,
	"genero"	TEXT,
	PRIMARY KEY("idcliente")
);
CREATE TABLE IF NOT EXISTS "habitacion" (
	"idhabitacion"	INTEGER,
	"numero"	TEXT NOT NULL UNIQUE,
	"numeroPiso"	TEXT,
	"numeroHabitacion"	TEXT,
	"tipo"	TEXT NOT NULL,
	"precio"	REAL NOT NULL,
	"estado"	TEXT DEFAULT 'Disponible',
	PRIMARY KEY("idhabitacion" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "reserva" (
	"idreserva"	INTEGER,
	"clienteId"	INTEGER NOT NULL,
	"habitacionId"	INTEGER NOT NULL,
	"fecha_reserva"	DATE NOT NULL,
	"fecha_ingreso"	DATE NOT NULL,
	"fecha_salida"	DATE NOT NULL,
	"estado"	TEXT DEFAULT 'Activa',
	"total"	REAL NOT NULL,
	"usuarioId"	INTEGER,
	PRIMARY KEY("idreserva" AUTOINCREMENT),
	FOREIGN KEY("clienteId") REFERENCES "cliente"("idcliente"),
	FOREIGN KEY("habitacionId") REFERENCES "habitacion"("idhabitacion"),
	FOREIGN KEY("usuarioId") REFERENCES "usuario"("idusuario")
);
CREATE TABLE IF NOT EXISTS "usuario" (
	"idusuario"	INTEGER,
	"user"	TEXT,
	"password"	TEXT,
	"tipoUser"	TEXT,
	"nombre"	TEXT,
	"apellidos"	TEXT,
	"dni"	TEXT,
	PRIMARY KEY("idusuario")
);
COMMIT;
