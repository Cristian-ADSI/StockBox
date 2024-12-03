
package Modelos;


public class EntidadCliente {
    int IdCliente;
    String DNI;
    String Nombre;
    String Direccion;
    String Estado;

    public EntidadCliente() {
    }

    public EntidadCliente(int IdCliente, String DNI, String Nombre, String Direccion, String Estado) {
        this.IdCliente = IdCliente;
        this.DNI = DNI;
        this.Nombre = Nombre;
        this.Direccion = Direccion;
        this.Estado = Estado;
    }

    public int getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(int IdCliente) {
        this.IdCliente = IdCliente;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
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
