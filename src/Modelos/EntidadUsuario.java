
package Modelos;
    
public class EntidadUsuario {
    String IdUsuario;
    String DNI;
    String Nombre;
    String Telefono;
    String Estado;
    String Usuario;
    String Role;

   

    public EntidadUsuario() {
    }

    public EntidadUsuario(String IdUsuario, String DNI, String Nombre, String Telefono, String Estado, String Usuario, String Role) {
        this.IdUsuario  = IdUsuario;
        this.DNI        = DNI;
        this.Nombre     = Nombre;
        this.Telefono   = Telefono;
        this.Estado     = Estado;
        this.Usuario    = Usuario;
        this.Role       = Role;
    }

    public String getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(String Id_Vendedor) {
        this.IdUsuario = Id_Vendedor;
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

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public String getStatus() {
        return Estado;
    }

    public void setStatus(String status) {
        this.Estado = status;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String user) {
        this.Usuario = user;
    }
    
     public String getRole() {
        return Role;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }
    
    
}
