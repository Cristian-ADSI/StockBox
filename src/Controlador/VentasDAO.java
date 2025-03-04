package Controlador;

import Modelos.EntidadDetalleVenta;
import Modelos.EntidadVenta;
import Vista.Mensaje;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author crist
 */
public class VentasDAO {

    private static Mensaje message = new Mensaje();
    private static Conexion conexion = new Conexion();

    private static Connection connection = conexion.Conectar();
    private static PreparedStatement prepStatement;
    private static ResultSet resSet;

    public static ArrayList readSales() {

        ArrayList<EntidadVenta> salesList = new ArrayList<>();

        String SQLQuery = "select * from `view_ventas`";

        try {
            prepStatement = connection.prepareStatement(SQLQuery);
            resSet = prepStatement.executeQuery();

            while (resSet.next()) {
                EntidadVenta entVentas = new EntidadVenta();

                entVentas.setIdCliente(resSet.getString(1));
                entVentas.setIdUsuario(resSet.getString(2));
                entVentas.setIdVenta(resSet.getInt(3));
                entVentas.setFecha(resSet.getString(4));
                entVentas.setMonto(resSet.getFloat(5));
                entVentas.setEstado(resSet.getString(6));

                salesList.add(entVentas);
            }

        } catch (SQLException error) {

            String text = "Error en el metodo VentasDAO.readSales: ";
            message.errorInSQLQuery(text, error);
        }
        return salesList;
    }

    public static ArrayList searchSaleDetails(int saleId) {
        ArrayList<EntidadDetalleVenta> saleDetailList = new ArrayList<>();
        String SQLQuery = "select * from `view_detalle_ventas`where IdVenta = ?";

        try {
            prepStatement = connection.prepareStatement(SQLQuery);
            prepStatement.setInt(1, saleId);
            resSet = prepStatement.executeQuery();

            while (resSet.next()) {
                EntidadDetalleVenta entDetalleVenta = new EntidadDetalleVenta();

                entDetalleVenta.setIdDetalleVenta(resSet.getInt(1));
                entDetalleVenta.setIdVenta(resSet.getInt(2));
                entDetalleVenta.setNroSerie(resSet.getString(3));
                entDetalleVenta.setIdProducto(resSet.getInt(4));
                entDetalleVenta.setNombreProducto(resSet.getString(5));
                entDetalleVenta.setCantidad(resSet.getInt(6));
                entDetalleVenta.setPrecioVenta(resSet.getFloat(7));
                entDetalleVenta.setEstado(resSet.getString(8));

                saleDetailList.add(entDetalleVenta);
            }

        } catch (SQLException error) {

            String text = "Error en el metodo VentasDAO.searchSaleDetails: ";
            message.errorInSQLQuery(text, error);
        }

        return saleDetailList;
    }
}
