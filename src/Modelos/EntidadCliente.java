
package Modelos;


public class EntidadCliente {
    int Id_Cliente;
    String Cedula;
    String Nombre;
    String Direccion;
    String Estado;

    public EntidadCliente() {
    }

    public EntidadCliente(int Id_Cliente, String Cedula, String Nombre, String Direccion, String Estado) {
        this.Id_Cliente = Id_Cliente;
        this.Cedula = Cedula;
        this.Nombre = Nombre;
        this.Direccion = Direccion;
        this.Estado = Estado;
    }

    public int getId_Cliente() {
        return Id_Cliente;
    }

    public void setId_Cliente(int Id_Cliente) {
        this.Id_Cliente = Id_Cliente;
    }

    public String getCedula() {
        return Cedula;
    }

    public void setCedula(String Cedula) {
        this.Cedula = Cedula;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }
    
}
