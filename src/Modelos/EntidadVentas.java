package Modelos;

public class EntidadVentas {

    
    private String Cliente;
    private String Vendedor;
    private int IdVenta;
    private String Fecha;
    private float Monto;
    private String Estado;

    public EntidadVentas() {
    }

    public EntidadVentas(String Cliente, String Vendedor, int IdVenta,String Fecha, float Monto, String Estado) {
        this.Cliente = Cliente;
        this.Vendedor = Vendedor;
        this.IdVenta = IdVenta;
        this.Fecha = Fecha;
        this.Monto = Monto;
        this.Estado = Estado;
    }

    public int getIdVenta() {
        return IdVenta;
    }

    public void setIdVenta(int IdVenta) {
        this.IdVenta = IdVenta;
    }

  

    public String getCliente() {
        return Cliente;
    }

    public void setCliente(String Cliente) {
        this.Cliente = Cliente;
    }

    public String getVendedor() {
        return Vendedor;
    }

    public void setVendedor(String Vendedor) {
        this.Vendedor = Vendedor;
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
