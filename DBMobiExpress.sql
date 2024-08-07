drop database if exists DBMobiExpress;
create database DBMobiExpress;

use DBMobiExpress;

create table Clientes(
	codigoCliente int not null auto_increment,
    NITCliente varchar(10) not null,
    nombresCliente varchar(50) not null,
    apellidosCliente varchar(50) not null,
    direccionCliente varchar(150) not null,
    telefonoCliente varchar(8) not null,
    primary key PK_codigoCliente (codigoCliente)
);

create table Proveedores(
	codigoProveedor int not null auto_increment,
    NITProveedor varchar(10) not null,
    nombreProveedor varchar(60) not null,
    apellidoProveedor varchar(60) not null,
    razonSocial varchar(60) not null,
    contactoPrincipal varchar(100) not null,
    paginaWeb varchar(50) not null,
    primary key PK_codigoProveedor (codigoProveedor)
);

create table Compras(
	numeroCompra int not null auto_increment,
    descripcion varchar(45) not null,
    montoTotal double(10,2) not null,
    fechaDeCompra date not null,
    estado varchar(20) not null,
    primary key PK_numeroCompra (numeroCompra)
);

create table CargoEmpleado(
	codigoCargoEmpleado int not null auto_increment,
    nombreCargo varchar(45) not null,
    salario double(10,2) not null,
    descripcionCargo varchar(45) not null,
    jornada varchar(10) not null,
    primary key PK_codigoCargoEmpleado (codigoCargoEmpleado)
);

create table CategoriaProducto(
	codigoCategoriaProducto int not null auto_increment,
    categoria varchar(45) not null,
    tipoDeProducto varchar(45) not null,
    color varchar(25) not null,
    marca varchar(25) not null,
    primary key PK_codigoCategoriaProducto (codigoCategoriaProducto)
);

create table Producto(
	codigoProducto int not null auto_increment,
    producto varchar(45) not null,
    descripcion varchar(45) not null,
    costoRenta double(10,2) not null,
    existencia int not null,
    tamanio varchar(100) not null,
    imgProducto blob not null,
    codigoCategoriaProducto int not null,
    primary key PK_codigoProducto (codigoProducto),
    constraint FK_Producto_CategoriaProducto foreign key (codigoCategoriaProducto) 
		references CategoriaProducto(codigoCategoriaProducto)
);
create table DetalleCompra(
	codigoDetalleCompra int not null auto_increment,
    cantidad int not null,
    costo double(10,2) not null,
    direccion varchar(150) not null,
    subTotal double(100,2) not null,
    fechaReception date not null,
    codigoProveedor int not null,
    codigoProducto int not null,
    numeroCompra int not null,
    primary key PK_codigoDetalleCompra (codigoDetalleCompra),
	constraint FK_DetalleCompra_Proveedores foreign key (codigoProveedor) 
		references Proveedores(codigoProveedor),
	constraint FK_DetalleCompra_Producto foreign key (codigoProducto) 
		references Producto(codigoProducto),
	constraint FK_DetalleCompra_Compras foreign key (numeroCompra) 
		references Compras(numeroCompra)
);

create table Empleados(
	codigoEmpleado int not null auto_increment,
    DPIEmpleado varchar(13) not null,
    nombresEmpleado varchar(50) not null,
    apellidosEmpleado varchar(50) not null,
    telefonoEmpleado varchar(8) not null,
    codigoCargoEmpleado int not null,
    usuario varchar(50) not null,
    passwor varchar(50) not null,
    imgUsuario blob not null,
    primary key PK_codigoEmpleado (codigoEmpleado),
    constraint FK_Empleados_CargoEmpleado foreign key (codigoCargoEmpleado) 
		references CargoEmpleado(codigoCargoEmpleado)
);

create table Pedidos(
	numeroPedido int not null auto_increment,
    direccion varchar(150) not null,
    montoTotal double(10,2) not null,
	fechaDeEntrega date not null,
    fechaDeRetorno date not null,
    codigoCliente int not null,
    codigoEmpleado int not null,
    primary key PK_numeroPedido (numeroPedido),
    constraint FK_Pedidos_Clientes foreign key (codigoCliente) 
		references Clientes(codigoCliente),
	constraint FK_Pedidos_Empleados foreign key (codigoEmpleado) 
		references Empleados(codigoEmpleado)
);

create table DetallePedido(
	codigoDetallePedido int not null auto_increment,
    precioRenta double(10,2) not null,
    cantidad int not null,
    subTotal double(10,2),
    descuento int not null,
    codigoProducto int not null,
    numeroPedido int not null,
    primary key PK_codigoDetallePedido (codigoDetallePedido),
	constraint FK_DetallePedido_Producto foreign key (codigoProducto) 
		references Producto(codigoProducto),
	constraint FK_DetallePedido_Pedidos foreign key (numeroPedido) 
		references Pedidos(numeroPedido)
);

insert into Clientes(NITCliente,nombresCliente,apellidosCliente,direccionCliente,telefonoCliente)
	values('1234567890','Ricardo Alberto','Martin Quiche','Guatemala, Guatemala','12345678');
insert into CargoEmpleado(nombreCargo, salario, descripcionCargo, jornada)
	values('Jefe','10000','Manda a todos los de la empresa','indefinida');
insert into Empleados(DPIEmpleado, nombresEmpleado, apellidosEmpleado, telefonoEmpleado, codigoCargoEmpleado, usuario, passwor, imgUsuario)
	values('1234567890123','Pedro Armas','Chang','12345678',1,'parmas','admin','data:image/png;base64,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');
  
-- Tabla Clientes
INSERT INTO Clientes (NITCliente, nombresCliente, apellidosCliente, direccionCliente, telefonoCliente)
VALUES
('1234567892', 'Sebastian Alberto', 'Veliz Martinez', 'Guatemala, Guatemala', '12345678');

-- Tabla Proveedores
INSERT INTO Proveedores (NITProveedor, nombreProveedor, apellidoProveedor, razonSocial, contactoPrincipal, paginaWeb)
VALUES
('9876543210', 'Luis Alberto', 'Martinez Garcia', 'Martinez & Asociados', 'Luis Martinez', 'www.martinezasociados.com');

-- Tabla Compras
INSERT INTO Compras (descripcion, montoTotal, fechaDeCompra, estado)
VALUES
('Compra de Sillas', 430.00, '2024-08-01', 'Pendiente');

-- Tabla CargoEmpleado
INSERT INTO CargoEmpleado (nombreCargo, salario, descripcionCargo, jornada)
VALUES
('Repartidor', 1500.00, 'Va dejando los pedidos', 'Completo');

-- Tabla CategoriaProducto
INSERT INTO CategoriaProducto (categoria, tipoDeProducto, color, marca)
VALUES
('Mobiliario', 'Silla', 'Besh', 'Guateplast');

-- Tabla Producto
INSERT INTO Producto (producto, descripcion, costoRenta, existencia, tamanio, imgProducto, codigoCategoriaProducto)
VALUES
('Silla', 'Silla mediana para fiestas', 3.00, 70, '2m', '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',1);
-- Tabla DetalleCompra
INSERT INTO DetalleCompra (cantidad, costo, direccion, subTotal, fechaReception, codigoProveedor, codigoProducto, numeroCompra)
VALUES
(70, 43.00, 'Sucursal Central', 430.00, '2024-08-02', 1, 1, 1);

-- Tabla Empleados
INSERT INTO Empleados (DPIEmpleado, nombresEmpleado, apellidosEmpleado, telefonoEmpleado, codigoCargoEmpleado, usuario, passwor, imgUsuario)
VALUES
('1234567890123', 'Denis Alfredo', 'Vela Velasquez', '12345678', 1, 'dvela', 'admin', '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');


-- Tabla Pedidos
INSERT INTO Pedidos (direccion, montoTotal, fechaDeEntrega, fechaDeRetorno, codigoCliente, codigoEmpleado)
VALUES
('Guatemala, Guatemala', 200.00, '2024-08-10', '2024-08-15', 1, 1);

-- Tabla DetallePedido
INSERT INTO DetallePedido (precioRenta, cantidad, subTotal, descuento, codigoProducto, numeroPedido)
VALUES
(3.00, 20, 60.00, 10, 1, 1);