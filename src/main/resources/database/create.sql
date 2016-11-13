CREATE TABLE usuario (
  id_usuario SERIAL,
  nombre VARCHAR NOT NULL,
  email VARCHAR NOT NULL,
  email VARCHAR NOT NULL,
  username VARCHAR NOT NULL,
  password VARCHAR NOT NULL,
  private_key VARCHAR NOT NULL,
  public_key VARCHAR NOT NULL,
  PRIMARY KEY(id_usuario)
);

CREATE TABLE socio (
  id_socio SERIAL,
  nombre VARCHAR NOT NULL,
  telefono VARCHAR NOT NULL,
  email VARCHAR NOT NULL,
  direccion VARCHAR NOT NULL,
  PRIMARY KEY(id_socio)
);

CREATE TABLE cliente (
  id_cliente SERIAL,
  nombre VARCHAR NOT NULL,
  email VARCHAR NOT NULL,
  telefono VARCHAR NOT NULL,
  PRIMARY KEY(id_cliente)
);

CREATE TABLE propuesta (
  id_propuesta SERIAL,
  id_socio INTEGER NOT NULL,
  descripcion VARCHAR NOT NULL,
  costo FLOAT NOT NULL,
  fecha_inicio DATE NOT NULL,
  fecha_fin DATE NOT NULL,
  PRIMARY KEY(id_propuesta),
  FOREIGN KEY (id_socio) REFERENCES socio(id_socio)
);

CREATE TABLE servicio (
  id_servicio SERIAL,
  nombre VARCHAR NOT NULL,
  descripcion VARCHAR NOT NULL,
  PRIMARY KEY(id_servicio)
);

CREATE TABLE plan_venta (
  id_plan_venta SERIAL,
  id_usuario INTEGER NOT NULL,
  id_cliente INTEGER NOT NULL,
  nombre VARCHAR NOT NULL,
  descripcion VARCHAR NOT NULL,
  fecha_creacion DATE NOT NULL,
  estado VARCHAR NOT NULL,
  PRIMARY KEY(id_plan_venta),
  FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario),
  FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente)
);

CREATE TABLE reporte (
  id_reporte SERIAL,
  id_usuario INTEGER NOT NULL,
  tipo VARCHAR NOT NULL,
  estado VARCHAR NOT NULL,
  url VARCHAR NULL,
  PRIMARY KEY(id_reporte),
  FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

CREATE TABLE cotizacion (
  id_cotizacion INTEGER NOT NULL,
  id_servicio INTEGER NOT NULL,
  id_cliente INTEGER NOT NULL,
  fecha_creacion DATE NOT NULL,
  cantidad_item INTEGER NOT NULL,
  estado VARCHAR NULL,
  valor_item INTEGER NOT NULL,
  total INTEGER NOT NULL,
  PRIMARY KEY(id_cotizacion),
  FOREIGN KEY (id_servicio) REFERENCES servicio(id_servicio),
  FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente)
);

CREATE TABLE item (
  id_item SERIAL,
  nombre VARCHAR NULL,
  estado VARCHAR NULL,
  descripcion VARCHAR NULL,
  precio FLOAT NULL,
  PRIMARY KEY(id_item)
);

CREATE TABLE inventario (
  id_inventario SERIAL,
  id_item INTEGER NOT NULL,
  fecha_compra DATE NOT NULL,
  estado VARCHAR NOT NULL,
  PRIMARY KEY(id_inventario),
  FOREIGN KEY (id_item) REFERENCES item(id_item)
);

CREATE TABLE servicio_has_item (
  id_servicio INTEGER NOT NULL,
  id_item INTEGER NOT NULL,
  PRIMARY KEY(id_servicio, id_item),
  FOREIGN KEY (id_servicio) REFERENCES servicio(id_servicio),
  FOREIGN KEY (id_item) REFERENCES item(id_item)
);

CREATE TABLE plan_venta_has_inventario (
  id_plan_venta INTEGER NOT NULL,
  id_inventario INTEGER NOT NULL,
  PRIMARY KEY(id_plan_venta, id_inventario),
  FOREIGN KEY (id_plan_venta) REFERENCES plan_venta(id_plan_venta),
  FOREIGN KEY (id_inventario) REFERENCES inventario(id_inventario)
);

CREATE TABLE plan_venta_has_item (
  id_plan_venta INTEGER NOT NULL,
  id_item INTEGER NOT NULL,
  PRIMARY KEY(id_plan_venta, id_item),
  FOREIGN KEY (id_plan_venta) REFERENCES plan_venta(id_plan_venta),
  FOREIGN KEY (id_item) REFERENCES item(id_item)
);

CREATE TABLE plan_venta_has_servicio (
  id_plan_venta INTEGER NOT NULL,
  id_servicio INTEGER NOT NULL,
  PRIMARY KEY(id_plan_venta, id_servicio),
  FOREIGN KEY (id_plan_venta) REFERENCES plan_venta(id_plan_venta),
  FOREIGN KEY (id_servicio) REFERENCES servicio(id_servicio)
);

CREATE TABLE plan_venta_has_propuesta (
  id_plan_venta INTEGER NOT NULL,
  id_propuesta INTEGER NOT NULL,
  PRIMARY KEY(id_plan_venta, id_propuesta),
  FOREIGN KEY (id_plan_venta) REFERENCES plan_venta(id_plan_venta),
  FOREIGN KEY (id_propuesta) REFERENCES propuesta(id_propuesta)
);

CREATE TABLE cotizacion_has_item (
  id_cotizacion INTEGER NOT NULL,
  id_item INTEGER NOT NULL,
  PRIMARY KEY(id_cotizacion, id_item),
  FOREIGN KEY (id_cotizacion) REFERENCES cotizacion(id_cotizacion),
  FOREIGN KEY (id_item) REFERENCES item(id_item)
);