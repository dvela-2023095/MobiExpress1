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
