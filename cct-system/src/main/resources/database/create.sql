CREATE TABLE usuario (
  idUsuario SERIAL,
  nombre VARCHAR NOT NULL,
  email VARCHAR NOT NULL,
  PRIMARY KEY(idUsuario)
);

CREATE TABLE socio (
  idSocio SERIAL,
  nombre VARCHAR NOT NULL,
  telefono VARCHAR NOT NULL,
  email VARCHAR NOT NULL,
  direccion VARCHAR NOT NULL,
  PRIMARY KEY(idSocio)
);

CREATE TABLE cliente (
  idCliente SERIAL,
  nombre VARCHAR NOT NULL,
  email VARCHAR NOT NULL,
  telefono VARCHAR NOT NULL,
  PRIMARY KEY(idCliente)
);
/*
CREATE TABLE item (
  idItem SERIAL,
  nombre VARCHAR NOT NULL,
  descripcion VARCHAR NOT NULL,
  precio FLOAT NOT NULL,
  PRIMARY KEY(idItem)
);*/

CREATE TABLE item (
  idItem SERIAL,
  nombre VARCHAR NULL,
  estado VARCHAR NULL,
  descripcion VARCHAR NULL,
  precio FLOAT NULL,
  idcotizacion INTEGER NOT NULL,
  PRIMARY KEY(idItem)
);

CREATE TABLE inventario (
  idInventario SERIAL,
  idItem INTEGER NOT NULL,
  fechaCompra DATE NOT NULL,
  estado VARCHAR NOT NULL,
  PRIMARY KEY(idInventario),
  FOREIGN KEY (idItem) REFERENCES item(idItem)
);

CREATE TABLE propuesta (
  idPropuesta SERIAL,
  idSocio INTEGER NOT NULL,
  descripcion VARCHAR NOT NULL,
  costo FLOAT NOT NULL,
  fechaInicio DATE NOT NULL,
  fechaFin DATE NOT NULL,
  PRIMARY KEY(idPropuesta),
  FOREIGN KEY (idSocio) REFERENCES socio(idSocio)
);

CREATE TABLE servicio (
  idServicio SERIAL,
  nombre VARCHAR NOT NULL,
  descripcion VARCHAR NOT NULL,
  PRIMARY KEY(idServicio)
);

CREATE TABLE plan_venta (
  idPlanVenta SERIAL,
  idUsuario INTEGER NOT NULL,
  idCliente INTEGER NOT NULL,
  nombre VARCHAR NOT NULL,
  descripcion VARCHAR NOT NULL,
  fechaCreacion DATE NOT NULL,
  estado VARCHAR NOT NULL,
  PRIMARY KEY(idPlanVenta),
  FOREIGN KEY (idUsuario) REFERENCES usuario(idUsuario),
  FOREIGN KEY (idCliente) REFERENCES cliente(idCliente)
);

CREATE TABLE reporte (
  idReporte SERIAL,
  idUsuario INTEGER NOT NULL,
  tipo VARCHAR NOT NULL,
  estado VARCHAR NOT NULL,
  url VARCHAR NOT NULL,
  PRIMARY KEY(idReporte),
  FOREIGN KEY (idUsuario) REFERENCES usuario(idUsuario)
);

CREATE TABLE servicio_has_item (
  idServicio INTEGER NOT NULL,
  idItem INTEGER NOT NULL,
  PRIMARY KEY(idServicio, idItem),
  FOREIGN KEY (idServicio) REFERENCES servicio(idServicio),
  FOREIGN KEY (idItem) REFERENCES item(idItem)
);

CREATE TABLE plan_venta_has_inventario (
  idPlanVenta INTEGER NOT NULL,
  idInventario INTEGER NOT NULL,
  PRIMARY KEY(idPlanVenta, idInventario),
  FOREIGN KEY (idPlanVenta) REFERENCES plan_venta(idPlanVenta),
  FOREIGN KEY (idInventario) REFERENCES inventario(idInventario)
);

CREATE TABLE plan_venta_has_item (
  idPlanVenta INTEGER NOT NULL,
  idItem INTEGER NOT NULL,
  PRIMARY KEY(idPlanVenta, idItem),
  FOREIGN KEY (idPlanVenta) REFERENCES plan_venta(idPlanVenta),
  FOREIGN KEY (idItem) REFERENCES item(idItem)
);

CREATE TABLE plan_venta_has_servicio (
  idPlanVenta INTEGER NOT NULL,
  idServicio INTEGER NOT NULL,
  PRIMARY KEY(idPlanVenta, idServicio),
  FOREIGN KEY (idPlanVenta) REFERENCES plan_venta(idPlanVenta),
  FOREIGN KEY (idServicio) REFERENCES servicio(idServicio)
);

CREATE TABLE plan_venta_has_propuesta (
  idPlanVenta INTEGER NOT NULL,
  idPropuesta INTEGER NOT NULL,
  PRIMARY KEY(idPlanVenta, idPropuesta),
  FOREIGN KEY (idPlanVenta) REFERENCES plan_venta(idPlanVenta),
  FOREIGN KEY (idPropuesta) REFERENCES propuesta(idPropuesta)
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
  FOREIGN KEY (id_servicio) REFERENCES servicio(idServicio),
  FOREIGN KEY (id_cliente) REFERENCES cliente(idCliente)
);
  
ALTER TABLE item ADD CONSTRAINT fk_item_cotizacion FOREIGN KEY (idcotizacion) REFERENCES cotizacion(id_cotizacion) ON DELETE CASCADE ON UPDATE CASCADE;