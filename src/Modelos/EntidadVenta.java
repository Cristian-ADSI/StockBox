package Modelos;

public class EntidadVenta {

    
    private String  idCliente;
    private String  idUsuario;
    private int     IdVenta;
    private String  Fecha;
    private float   Monto;
    private String  Estado;

    public EntidadVenta() {
    }

    public EntidadVenta(String idCliente, String idUsuario, int IdVenta,String Fecha, float Monto, String Estado) {
        this.idCliente  = idCliente;
        this.idUsuario  = idUsuario;
        this.IdVenta    = IdVenta;
        this.Fecha      = Fecha;
        this.Monto      = Monto;
        this.Estado     = Estado;
    }

    public int getIdVenta() {
        return IdVenta;
    }

    public void setIdVenta(int IdVenta) {
        this.IdVenta = IdVenta;
    }

  

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
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
