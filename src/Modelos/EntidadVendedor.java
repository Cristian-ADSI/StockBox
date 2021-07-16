
package Modelos;
    
public class EntidadVendedor {
    int Id_Vendedor;
    String Cedula;
    String Nombre;
    String Telefono;
    String Estado;
    String User_2;
    String Role;

   

    public EntidadVendedor() {
    }

    public EntidadVendedor(int Id_Vendedor, String Cedula, String Nombre, String Telefono, String Estado, String User_2) {
        this.Id_Vendedor = Id_Vendedor;
        this.Cedula = Cedula;
        this.Nombre = Nombre;
        this.Telefono = Telefono;
        this.Estado = Estado;
        this.User_2 = User_2;
        this.Role= Role;
    }

    public int getId_Vendedor() {
        return Id_Vendedor;
    }

    public void setId_Vendedor(int Id_Vendedor) {
        this.Id_Vendedor = Id_Vendedor;
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

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public String getUser_2() {
        return User_2;
    }

    public void setUser_2(String User_2) {
        this.User_2 = User_2;
    }
    
     public String getRole() {
        return Role;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }
    
    
}
