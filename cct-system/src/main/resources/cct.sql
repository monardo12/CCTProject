CREATE TABLE usuario (
  idUsuario SERIAL,
  nombre VARCHAR NULL,
  email VARCHAR NULL,
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
  nombre VARCHAR NULL,
  email VARCHAR NULL,
  telefono INTEGER NULL,
  PRIMARY KEY(idCliente)
);

CREATE TABLE item (
  idItem SERIAL,
  nombre VARCHAR NULL,
  descripcion VARCHAR NULL,
  precio FLOAT NULL,
  PRIMARY KEY(idItem)
);

CREATE TABLE propuesta (
  idPropuesta SERIAL,
  idSocio INTEGER NOT NULL,
  descripcion VARCHAR NULL,
  costo INTEGER NULL,
  fechaInicio DATE NULL,
  fechaFin DATE NULL,
  PRIMARY KEY(idPropuesta),
  FOREIGN KEY (idSocio) REFERENCES socio(idSocio)
);

CREATE TABLE servicio (
  idServicio SERIAL,
  nombre VARCHAR NOT NULL,
  descripcion VARCHAR NOT NULL,
  PRIMARY KEY(idServicio)
);

CREATE TABLE unidad_item (
  idUnidadItem SERIAL,
  idItem INTEGER NOT NULL,
  fechaCompra DATE NOT NULL,
  estado VARCHAR NOT NULL,
  PRIMARY KEY(idUnidadItem),
  FOREIGN KEY (idItem) REFERENCES item(idItem)
);

CREATE TABLE plan_venta (
  idPlanVenta SERIAL,
  idUsuario INTEGER NOT NULL,
  idPropuesta INTEGER NOT NULL,
  idCliente INTEGER NOT NULL,
  nombre VARCHAR NOT NULL,
  descripcion VARCHAR NOT NULL,
  fechaCreacion DATE NOT NULL,
  estado VARCHAR NOT NULL,
  PRIMARY KEY(idPlanVenta),
  FOREIGN KEY (idUsuario) REFERENCES usuario(idUsuario),
  FOREIGN KEY (idCliente) REFERENCES cliente(idCliente),
  FOREIGN KEY (idPropuesta) REFERENCES propuesta(idPropuesta)
);

CREATE TABLE plan_venta_item (
  idPlanVentaItem SERIAL,
  idPlanVenta INTEGER NOT NULL,
  idItem INTEGER NOT NULL,
  PRIMARY KEY(idPlanVentaItem),
  FOREIGN KEY (idPlanVenta) REFERENCES plan_venta(idPlanVenta),
  FOREIGN KEY (idItem) REFERENCES item(idItem)
);

CREATE TABLE servicio_item (
  idServicioItem SERIAL,
  idItem INTEGER NOT NULL,
  idServicio INTEGER NOT NULL,
  PRIMARY KEY(idServicioItem),
  FOREIGN KEY (idItem) REFERENCES item(idItem),
  FOREIGN KEY (idServicio) REFERENCES servicio(idServicio)
);

CREATE TABLE servicio_plan_venta (
  idServicioPlanVenta SERIAL,
  idPlanVenta INTEGER NOT NULL,
  idServicio INTEGER NOT NULL,
  PRIMARY KEY(idServicioPlanVenta),
  FOREIGN KEY (idPlanVenta) REFERENCES plan_venta(idPlanVenta),
  FOREIGN KEY (idServicio) REFERENCES servicio(idServicio)
);
