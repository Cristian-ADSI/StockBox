
package Modelos;


public class EntidadDetalleVenta {
    int     IdDetalleVenta;
    int     IdVenta;
    String  NroSerie;
    int     IdProducto;
    String  NombreProducto;
    int     Cantidad;
    float   PrecioVenta;
    String  Estado;
    

    public EntidadDetalleVenta() {
    }

    public EntidadDetalleVenta(int IdDetalleVenta, int IdVenta,String NroSerie , int IdProducto, String NombreProducto, int Cantidad, float PrecioVenta) {
        this.IdDetalleVenta = IdDetalleVenta;
        this.IdVenta        = IdVenta;
        this.NroSerie       = NroSerie;
        this.IdProducto     = IdProducto;
        this.NombreProducto = NombreProducto;
        this.Cantidad       = Cantidad;
        this.PrecioVenta    = PrecioVenta;
    }

    public int getIdDetalleVenta() {
        return IdDetalleVenta;
    }

    public void setIdDetalleVenta(int Id_DetalleVenta) {
        this.IdDetalleVenta = Id_DetalleVenta;
    }

    public int getIdVenta() {
        return IdVenta;
    }

    public void setIdVenta(int IdVenta) {
        this.IdVenta = IdVenta;
    }

    public int getIdProducto() {
        return IdProducto;
    }

    public void setIdProducto(int IdProducto) {
        this.IdProducto = IdProducto;
    }

    public String getNombreProducto() {
        return NombreProducto;
    }

    public void setNombreProducto(String NombreProducto) {
        this.NombreProducto = NombreProducto;
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
