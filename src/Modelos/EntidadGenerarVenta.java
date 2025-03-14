
package Modelos;

public class EntidadGenerarVenta {

    int     IdVenta;
    int     IdCliente;
    int     IdUsuario;
    String  NroSerie;
    String  Fecha;
    float   Monto;
    String  Estado;

    public EntidadGenerarVenta() {
    }

    public EntidadGenerarVenta(int IdVenta, int IdCliente, int IdUsuario, String NroSerie, String Fecha, float Monto, String Estado) {
        this.IdVenta    = IdVenta;
        this.IdCliente  = IdCliente;
        this.IdUsuario = IdUsuario;
        this.NroSerie   = NroSerie;
        this.Fecha      = Fecha;
        this.Monto      = Monto;
        this.Estado     = Estado;
    }

    public int getIdVenta() {
        return IdVenta;
    }

    public void setIdVenta(int Id_Venta) {
        this.IdVenta = Id_Venta;
    }

    public int getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(int Id_Cliente) {
        this.IdCliente = Id_Cliente;
    }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int Id_Vendedor) {
        this.IdUsuario = Id_Vendedor;
    }

    public String getNroSerie() {
        return NroSerie;
    }

    public void setNroSerie(String Nro_Venta) {
        this.NroSerie = Nro_Venta;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public float getMonto() {
        return Monto;
    }

    public void setMonto(float Monto) {
        this.Monto = Monto;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

}
