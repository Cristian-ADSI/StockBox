package Controlador;

import Modelos.EntidadVenta;
import Modelos.EntidadDetalleVenta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class VentasDAO {

    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    int r = 0;
    
    public int CancelarVenta(String Serie){
        String sql = "UPDATE ventas  SET Estado='Cancelada' WHERE Serie=?";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, Serie);
            r = ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Venta Cancelada Correctamente"); 
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se Pudo Cancelar la Venta");
            System.err.println(e);
        }       
        return r;
    }
    
    public int CancelarDetalleVenta(String NroSerie){  
        String sql = "UPDATE  detalle_ventas  SET Estado='Cancelada' WHERE NroSerie=?";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, NroSerie);
            r = ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Venta Cancelada Correctamente"); 
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se Pudo Cancelar la Venta");
            System.err.println(e);
        }
        return r;
    }

    public String IdVentas() {
        String idVentas = "";
        String sql = "SELECT MAX(Id_Venta) FROM ventas";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                idVentas = rs.getString(1);
            }
        } catch (Exception e) {
        }
        return idVentas;
    }

    public int GuardarVenta(EntidadVenta venta) {
        EntidadVenta ev = new EntidadVenta();
        String sql = "INSERT INTO ventas (Cliente, Vendedor,Serie, Fecha_Venta, Monto, Estado)VALUES(?,?,?,?,?,?)";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, venta.getId_Cliente());
            ps.setInt(2, venta.getId_Vendedor());
            ps.setString(3, venta.getNro_Venta());
            ps.setString(4, venta.getFecha());
            ps.setFloat(5, venta.getMonto());
            ps.setString(6, venta.getEstado());
            r = ps.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en el metodo GuardarVenta ");
            System.err.println("Error" + e + "\n");
        }
        return r;
    }

    public int GuardarDetalleVenta(EntidadDetalleVenta detalleVenta) {
        String sql = "INSERT INTO detalle_ventas(Id_Venta, NroSerie, Producto, Cantidad, PrecUnidad, Estado) value(?,?,?,?,?,?)";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, detalleVenta.getId_Venta());
            ps.setString(2, detalleVenta.getNroSerie());
            ps.setInt(3, detalleVenta.getId_Producto());
            ps.setInt(4, detalleVenta.getCantidad());
            ps.setFloat(5, detalleVenta.getPrecioVenta());
            ps.setString(6, detalleVenta.getEstado());
            ps.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en el metodo Guardar Detalle Venta");
            System.err.println("Error" + e + "\n");
        }
        return r;
    }

    public String NumeroSerie() {
        String serie = "";
        String sql = "SELECT MAX(Serie) FROM ventas";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                serie = rs.getString(1);
            }
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, "Error en el metodo Generar Numero de serie ");
            System.err.println("Error" + e + "\n");
        }
        return serie;
    }
}
