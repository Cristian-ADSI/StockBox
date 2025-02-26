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
    
    int result = 0;

    public int cancelSale(String NroSerie) {
        String SQLQuery = "UPDATE ventas  SET Estado='Cancelada' WHERE NroSerie=?";

        try {
            prepStatement = connection.prepareStatement(SQLQuery);
            prepStatement.setString(1, NroSerie);
            result        = prepStatement.executeUpdate();
            message.saleCanceledSuccessfully();

        } catch (SQLException error) {
            
            String text = "Error en el metodo VentasDAO.cancelSale";
            message.errorInSQLQuery(text, error);
            message.saleCanceleFailed();
        }
        
        return result;
    }

    public int cancelSaleDetail(String NroSerie) {
        
        String SQLQuery = "UPDATE  detalle_ventas  SET Estado='Cancelada' WHERE NroSerie=?";
        
        try {
            prepStatement = connection.prepareStatement(SQLQuery);
            prepStatement.setString(1, NroSerie);
            result        = prepStatement.executeUpdate();
            message.saleCanceledSuccessfully();
            
        } catch (SQLException error) {
            
            String text = "Error en el metodo VentasDAO.cancelSaleDetail";
            message.errorInSQLQuery(text, error);
            message.saleCanceleFailed();
        }
        
        return result;
    }

    public String getMaxIdSales() {
        
        String idSales  = "";
        String SQLQuery = "SELECT MAX(IdVenta) FROM ventas";
        
        try {
            connection    = conexion.Conectar();
            prepStatement = connection.prepareStatement(SQLQuery);
            resSet        = prepStatement.executeQuery();
            
            while (resSet.next()) {
                idSales = resSet.getString(1);
            }
            
        } catch (SQLException error) {
            
            String text = "Error en el metodo VentasDAO.getMaxIdSales";
            message.errorInSQLQuery(text, error);
            message.getMaxSaleIdFailed();
        }
        
        return idSales;
    }

    public int saveSale(EntidadGenerarVenta sale) {
        
        String SQLQuery = "INSERT INTO ventas (Cliente, Vendedor,NroSerie, FechaVenta, Monto, Estado)VALUES(?,?,?,?,?,?)";
        
        try {
            prepStatement = connection.prepareStatement(SQLQuery);
            
            prepStatement.setInt(1,     sale.getIdCliente());
            prepStatement.setInt(2,     sale.getIdVendedor());
            prepStatement.setString(3,  sale.getNroSerie());
            prepStatement.setString(4,  sale.getFecha());
            prepStatement.setFloat(5,   sale.getMonto());
            prepStatement.setString(6,  sale.getEstado());
            
            result = prepStatement.executeUpdate();
            
        } catch (SQLException error) {
            String text = "Error en el metodo VentasDAO.saveSale";
            message.errorInSQLQuery(text, error);
            message.saleSaveFailed();
        }
        
        return result;
    }

    public int saveSaleDetail(EntidadDetalleVenta detalleVenta) {
        
        String SQLQuery = "INSERT INTO detalle_ventas(IdVenta, NroSerie, Producto, Cantidad, PrecioUnidad, Estado) value(?,?,?,?,?,?)";
        
        try {
            prepStatement = connection.prepareStatement(SQLQuery);
            
            prepStatement.setInt(1, detalleVenta.getIdVenta());
            prepStatement.setString(2, detalleVenta.getNroSerie());
            prepStatement.setInt(3, detalleVenta.getIdProducto());
            prepStatement.setInt(4, detalleVenta.getCantidad());
            prepStatement.setFloat(5, detalleVenta.getPrecioVenta());
            prepStatement.setString(6, detalleVenta.getEstado());
            
            prepStatement.executeUpdate();
            
        } catch (SQLException error) {
            
            String text = "Error en el metodo VentasDAO.saveSaleDetail";
            message.errorInSQLQuery(text, error);
            message.saleSaveDetailFailed();
        }
        return result;
    }

    public String getMaxSerialNumber() {
        
        String NroSerie = "";
        String SQLQuery = "select MAX(Serie) from `ventas`";
        
        try {
            prepStatement = connection.prepareStatement(SQLQuery);
            resSet = prepStatement.executeQuery();
            
            while (resSet.next()) {
                NroSerie = resSet.getString(1);
            }
            
        } catch (SQLException error) {
            String text = "Error en el metodo VentasDAO.getMaxSeriealNumber";
            message.errorInSQLQuery(text, error);
            message.getMaxSerialNumberFailed();
        }
        
        return NroSerie;
    }
}
