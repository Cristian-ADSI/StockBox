
package Modelos;


public class EntidadDetalleVenta {
    int Id_DetalleVenta;
    int Id_Venta;
    String NroSerie;
    int  Id_Producto;
    int Cantidad;
    float PrecioVenta;
    String Estado;
    

    public EntidadDetalleVenta() {
    }

    public EntidadDetalleVenta(int Id_DetalleVenta, int Id_Venta, int Id_Producto, int Cantidad, float PrecioVenta) {
        this.Id_DetalleVenta = Id_DetalleVenta;
        this.Id_Venta = Id_Venta;
        this.NroSerie = NroSerie;
        this.Id_Producto = Id_Producto;
        this.Cantidad = Cantidad;
        this.PrecioVenta = PrecioVenta;
    }

    public int getId_DetalleVenta() {
        return Id_DetalleVenta;
    }

    public void setId_DetalleVenta(int Id_DetalleVenta) {
        this.Id_DetalleVenta = Id_DetalleVenta;
    }

    public int getId_Venta() {
        return Id_Venta;
    }

    public void setId_Venta(int Id_Venta) {
        this.Id_Venta = Id_Venta;
    }

    public int getId_Producto() {
        return Id_Producto;
    }

    public void setId_Producto(int Id_Producto) {
        this.Id_Producto = Id_Producto;
    }

    public String getNroSerie() {
        return NroSerie;
    }

    public void setNroSerie(String NroSerie) {
        this.NroSerie = NroSerie;
    }
    
    

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int Cantidad) {
        this.Cantidad = Cantidad;
    }

    public float getPrecioVenta() {
        return PrecioVenta;
    }

    public void setPrecioVenta(float PrecioVenta) {
        this.PrecioVenta = PrecioVenta;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }
    
    
    
}
