
package modelo;
/*
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
*/
public class Empleado {
    private int codigoEmpleado;
    private String NITEmpleado;
    private String nombresEmpleado;
    private String apellidosEmpleado;
    private String telefonoEmpleado;
    private int codigoCargoEmpleado;
    private String usuario;
    private String passwor;

    public Empleado() {
    }

    public Empleado(int codigoEmpleado, String NITEmpleado, String nombresEmpleado, String apellidosEmpleado, String telefonoEmpleado, int codigoCargoEmpleado, String usuario, String passwor) {
        this.codigoEmpleado = codigoEmpleado;
        this.NITEmpleado = NITEmpleado;
        this.nombresEmpleado = nombresEmpleado;
        this.apellidosEmpleado = apellidosEmpleado;
        this.telefonoEmpleado = telefonoEmpleado;
        this.codigoCargoEmpleado = codigoCargoEmpleado;
        this.usuario = usuario;
        this.passwor = passwor;
    }

    public int getCodigoEmpleado() {
        return codigoEmpleado;
    }

    public void setCodigoEmpleado(int codigoEmpleado) {
        this.codigoEmpleado = codigoEmpleado;
    }

    public String getNITEmpleado() {
        return NITEmpleado;
    }

    public void setNITEmpleado(String NITEmpleado) {
        this.NITEmpleado = NITEmpleado;
    }

    public String getNombresEmpleado() {
        return nombresEmpleado;
    }

    public void setNombresEmpleado(String nombresEmpleado) {
        this.nombresEmpleado = nombresEmpleado;
    }

    public String getApellidosEmpleado() {
        return apellidosEmpleado;
    }

    public void setApellidosEmpleado(String apellidosEmpleado) {
        this.apellidosEmpleado = apellidosEmpleado;
    }

    public String getTelefonoEmpleado() {
        return telefonoEmpleado;
    }

    public void setTelefonoEmpleado(String telefonoEmpleado) {
        this.telefonoEmpleado = telefonoEmpleado;
    }

    public int getCodigoCargoEmpleado() {
        return codigoCargoEmpleado;
    }

    public void setCodigoCargoEmpleado(int codigoCargoEmpleado) {
        this.codigoCargoEmpleado = codigoCargoEmpleado;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPasswor() {
        return passwor;
    }

    public void setPasswor(String passwor) {
        this.passwor = passwor;
    }
    
    
}
