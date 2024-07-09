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

create table DetalleCompra(
	codigoDetalleCompra int not null auto_increment,
    cantidad int not null,
    costo double(10,2) not null,
    direccion varchar(150) not null,
    observaciones varchar(100) not null,
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