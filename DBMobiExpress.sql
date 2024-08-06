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
    usuario varchar(50) not null,
    passwor varchar(50) not null,
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

insert into Proveedores (NITProveedor, nombreProveedor, apellidoProveedor, razonSocial, contactoPrincipal, paginaWeb)
values('0123456789','César','Colorado','Razon social','Contacto','Pagina web');

create table Compras(
	numeroCompra int not null auto_increment,
    descripcion varchar(45) not null,
    montoTotal double(10,2) not null,
    fechaDeCompra date not null,
    estado varchar(20) not null,
    primary key PK_numeroCompra (numeroCompra)
);

insert into Compras (descripcion, montoTotal, fechaDeCompra, estado) 
values('descripcion', 100.20, '2024-05-29', 'estado');

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

insert into CategoriaProducto(categoria, tipoDeProducto, color, marca)
values('Electrónica', 'Laptop', 'Negro', 'HP');

create table Producto(
	codigoProducto int not null auto_increment,
    producto varchar(45) not null,
    descripcion varchar(45) not null,
    costoRenta double(10,2) not null,
    existencia int not null,
    tamanio varchar(10) not null,
    codigoCategoriaProducto int not null,
    primary key PK_codigoProducto (codigoProducto),
    constraint FK_Producto_CategoriaProducto foreign key (codigoCategoriaProducto) 
		references CategoriaProducto(codigoCategoriaProducto)
);

insert into Producto(producto, descripcion, costoRenta, existencia, tamanio, codigoCategoriaProducto)
values('Laptop HP', 'Laptop HP 15.6"', 15.00, 20, 'Mediano', 1);

create table DetalleCompra(
	codigoDetalleCompra int not null auto_increment,
    cantidad int not null,
    costo double(10,2) not null,
    direccion varchar(150) not null,
    subTotal double(10,2) not null,
    fechaRecepcion date not null,
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

insert into DetalleCompra(cantidad, costo, direccion, subTotal, fechaRecepcion, codigoProveedor, codigoProducto, numeroCompra)
values(10, 150.00, 'Guatemala, Guatemala', 1500.00, '2024-06-01', 1, 1, 1);

select * from Detall
eCompra;

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
    descuento varchar(10) not null,
    codigoProducto int not null,
    numeroPedido int not null,
    primary key PK_codigoDetallePedido (codigoDetallePedido),
	constraint FK_DetallePedido_Producto foreign key (codigoProducto) 
		references Producto(codigoProducto),
	constraint FK_DetallePedido_Pedidos foreign key (numeroPedido) 
		references Pedidos(numeroPedido)
);

insert into Clientes(NITCliente,nombresCliente,apellidosCliente,direccionCliente,telefonoCliente,usuario,passwor)
	values('1234567890','Denis Alfredo','Vela Velasquez','Guatemala, Guatemala','12345678','dvela2023095','admin');
insert into CargoEmpleado(nombreCargo, salario, descripcionCargo, jornada)
	values('Jefe','10000','Manda a todos los de la empresa','indefinida');
insert into Empleados(DPIEmpleado, nombresEmpleado, apellidosEmpleado, telefonoEmpleado, codigoCargoEmpleado, usuario, passwor, imgUsuario)
	values('1234567890123','Denis Alfredo','Vela Velasquez','12345678',1,'dvela','admin','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');
    
select * from Empleados where usuario = "dvela"
	and passwor = "admin";
    
    Select codigoDetalleCompra,codigoProveedor, codigoProducto, numeroCompra,costo,cantidad,subTotal,direccion,fechaRecepcion from DetalleCompra;