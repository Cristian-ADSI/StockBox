package Controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Modelos.EntidadGenerarVenta;
import Modelos.EntidadDetalleVenta;
import Vista.Mensaje;

public class GenerarVentaDAO {

    Conexion conexion = new Conexion();
    Mensaje message   = new Mensaje();

    Connection connection = conexion.Conectar();
    PreparedStatement prepStatement;
    ResultSet resSet;
    
    int result;

    public int cancelSale(int NroSerie) {
        String SQLQuery = "update `ventas`  set Estado='Cancelada' where NroSerie=?";

        try {
            prepStatement = connection.prepareStatement(SQLQuery);
            prepStatement.setInt(1, NroSerie);
            result        = prepStatement.executeUpdate();

        } catch (SQLException error) {
            
            String text = "Error en el metodo VentasDAO.cancelSale";
            message.errorInSQLQuery(text, error);
        }
        
        return result;
    }

    public int cancelSaleDetail(String NroSerie) {
        
        String SQLQuery = "update  detalle_ventas  set Estado='Cancelada' where NroSerie=?";
        
        try {
            prepStatement = connection.prepareStatement(SQLQuery);
            prepStatement.setString(1, NroSerie);
            result        = prepStatement.executeUpdate();
            
        } catch (SQLException error) {
            
            String text = "Error en el metodo VentasDAO.cancelSaleDetail";
            message.errorInSQLQuery(text, error);
        }
        
        return result;
    }

    public String getMaxIdVenta() {
        
        String IdVenta  = "";
        String SQLQuery = "select MAX(IdVenta) from `ventas`";
        
        try {
            connection    = conexion.Conectar();
            prepStatement = connection.prepareStatement(SQLQuery);
            resSet        = prepStatement.executeQuery();
            
            while (resSet.next()) {
                IdVenta = resSet.getString(1);
            }
            
        } catch (SQLException error) {
            
            String text = "Error en el metodo VentasDAO.getMaxIdSales";
            message.errorInSQLQuery(text, error);
            message.getMaxSaleIdFailed();
        }
        
        return IdVenta;
    }

    public int saveSale(EntidadGenerarVenta sale) {
        
        this.result = 0;  
        String SQLQuery = "insert into `ventas` (IdCliente, idUsuario, NroSerie, FechaVenta, Monto, Estado) values (?,?,?,?,?,?)";
        
        try {
            prepStatement = connection.prepareStatement(SQLQuery);
            
            prepStatement.setInt(1,     sale.getIdCliente());
            prepStatement.setInt(2,     sale.getIdUsuario());
            prepStatement.setString(3,  sale.getNroSerie());
            prepStatement.setString(4,  sale.getFecha());
            prepStatement.setFloat(5,   sale.getMonto());
            prepStatement.setString(6,  sale.getEstado());
            
            result = prepStatement.executeUpdate();
            
        } catch (SQLException error) {
            String text = "Error en el metodo GenerarVentasDAO.saveSale";
            message.errorInSQLQuery(text, error);
        }
        
        return result;
    }

    public int saveDetalleVenta(EntidadDetalleVenta detalleVenta) {
        this.result = 0;
        String SQLQuery = "insert into detalle_ventas(IdVenta, NroSerie, Producto, Cantidad, PrecioUnidad, Estado) value(?,?,?,?,?,?)";
        
        try {
            prepStatement = connection.prepareStatement(SQLQuery);
            
            prepStatement.setInt(1, detalleVenta.getIdVenta());
            prepStatement.setString(2, detalleVenta.getNroSerie());
            prepStatement.setInt(3, detalleVenta.getIdProducto());
            prepStatement.setInt(4, detalleVenta.getCantidad());
            prepStatement.setFloat(5, detalleVenta.getPrecioVenta());
            prepStatement.setString(6, detalleVenta.getEstado());
            
            this.result = prepStatement.executeUpdate();
            
        } catch (SQLException error) {
            
            String text = "Error en el metodo GenerarVentasDAO.saveSaleDetail";
            message.errorInSQLQuery(text, error);
        }
        return result;
    }

    public String getMaxSerialNumber() {
        
        String NroSerie = "";
        String SQLQuery = "select MAX(NroSerie) from `ventas`";
        
        try {
            prepStatement = connection.prepareStatement(SQLQuery);
            resSet = prepStatement.executeQuery();
            
            while (resSet.next()) {
                NroSerie = resSet.getString(1);
            }
            
        } catch (SQLException error) {
            String text = "Error en el metodo GenerarVentasDAO.getMaxSeriealNumber";
            message.errorInSQLQuery(text, error);
            message.getMaxSerialNumberFailed();
        }
        
        return NroSerie;
    }
}
