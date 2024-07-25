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
    tamanio varchar(10) not null,
    codigoCategoriaProducto int not null,
    primary key PK_codigoProducto (codigoProducto),
    constraint FK_Producto_CategoriaProducto foreign key (codigoCategoriaProducto) 
		references CategoriaProducto(codigoCategoriaProducto)
);
create table DetalleCompra(
	codigoDetalleCompra int not null auto_increment,
    canticodigoProductodad int not null,
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

create table Empleados(
	codigoEmpleado int not null auto_increment,
    NITEmpleado varchar(10) not null,
    nombresEmpleado varchar(50) not null,
    apellidosEmpleado varchar(50) not null,
    telefonoEmpleado varchar(8) not null,
    codigoCargoEmpleado int not null,
    usuario varchar(50) not null,
    passwor varchar(50) not null,
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
insert into Empleados(NITEmpleado, nombresEmpleado, apellidosEmpleado, telefonoEmpleado, codigoCargoEmpleado, usuario, passwor)
	values('1234567890','Denis Alfredo','Vela Velasquez','12345678',1,'dvela2023095','admin');
    

