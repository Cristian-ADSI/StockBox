/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;


public class EntidadVenta {
    int Id_Venta;
    int Id_Cliente;
    int Id_Vendedor;
    String Nro_Venta;
    String Fecha;
    float Monto;
    String Estado;

    public EntidadVenta() {
    }

    public EntidadVenta(int Id_Venta, int Id_Cliente, int Id_Vendedor, String Nro_Venta, String Fecha, float Monto, String Estado) {
        this.Id_Venta = Id_Venta;
        this.Id_Cliente = Id_Cliente;
        this.Id_Vendedor = Id_Vendedor;
        this.Nro_Venta = Nro_Venta;
        this.Fecha = Fecha;
        this.Monto = Monto;
        this.Estado = Estado;
    }

    public int getId_Venta() {
        return Id_Venta;
    }

    public void setId_Venta(int Id_Venta) {
        this.Id_Venta = Id_Venta;
    }

    public int getId_Cliente() {
        return Id_Cliente;
    }

    public void setId_Cliente(int Id_Cliente) {
        this.Id_Cliente = Id_Cliente;
    }

    public int getId_Vendedor() {
        return Id_Vendedor;
    }

    public void setId_Vendedor(int Id_Vendedor) {
        this.Id_Vendedor = Id_Vendedor;
    }

    public String getNro_Venta() {
        return Nro_Venta;
    }

    public void setNro_Venta(String Nro_Venta) {
        this.Nro_Venta = Nro_Venta;
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
