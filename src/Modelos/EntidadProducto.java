
package Modelos;


public class EntidadProducto {
    int IdProducto;
    String Nombre;
    float Precio;
    int Stock;
    String Estado;

    public EntidadProducto() {
    }

    public EntidadProducto(int Id_Producto, String Nombre, float Precio, int Stock, String Estado) {
        this.IdProducto = Id_Producto;
        this.Nombre = Nombre;
        this.Precio = Precio;
        this.Stock = Stock;
        this.Estado = Estado;
    }

    public int getIdProducto() {
        return IdProducto;
    }

    public void setIdProducto(int Id_Producto) {
        this.IdProducto = Id_Producto;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public float getPrecio() {
        return Precio;
    }

    public void setPrecio(float Precio) {
        this.Precio = Precio;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int Stock) {
        this.Stock = Stock;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }
    
}
