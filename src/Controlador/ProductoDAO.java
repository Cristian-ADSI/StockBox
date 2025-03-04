package Controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Vista.Mensaje;
import Modelos.EntidadProducto;
import java.util.Map;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ProductoDAO implements CRUD {

    Conexion conexion = new Conexion();
    Mensaje message = new Mensaje();

    Connection connection = conexion.Conectar();
    PreparedStatement prepStatement;
    ResultSet resSet;

    public EntidadProducto getProductoData(String idProducto) {

        EntidadProducto entProduct = new EntidadProducto();
        String SQLQuery = "SELECT * FROM productos WHERE IdProducto=?";

        try {

            prepStatement = connection.prepareStatement(SQLQuery);
            prepStatement.setString(1, idProducto);

            resSet = prepStatement.executeQuery();

            while (resSet.next()) {

                entProduct.setIdProducto(resSet.getInt(1));
                entProduct.setNombre(resSet.getString(2));
                entProduct.setPrecio(resSet.getFloat(3));
                entProduct.setStock(resSet.getInt(4));
                entProduct.setEstado(resSet.getString(5));
            }
        } catch (SQLException error) {

            String text = "Error en el Metodo ProductoDAO.searchProduct";
            message.errorInSQLQuery(text, error);
            message.searchProductFailed();
        }

        return entProduct;
    }

    public void StockUpdate(int stock, int idProduct) {

        String SQLQuery = "UPDATE productos SET Stock=? where IdProducto =?";

        try {
            prepStatement = connection.prepareStatement(SQLQuery);
            prepStatement.setInt(1, stock);
            prepStatement.setInt(2, idProduct);

            prepStatement.executeUpdate();

        } catch (SQLException error) {

            String text = "Error en el metodo ProductoDAO.StockUpdate";
            message.errorInSQLQuery(text, error);
            message.stockUpdateFailed();
        }
    }

    public DefaultTableModel listProductosOnTable(JTable tableProducto) {
        DefaultTableModel tableModelProducto = new DefaultTableModel();

        List<EntidadProducto> listProductos = this.read();
        tableModelProducto = (DefaultTableModel) tableProducto.getModel();
        Object[] ob = new Object[5];

        for (int i = 0; i < listProductos.size(); i++) {
            ob[0] = listProductos.get(i).getIdProducto();
            ob[1] = listProductos.get(i).getNombre();
            ob[2] = listProductos.get(i).getPrecio();
            ob[3] = listProductos.get(i).getStock();
            ob[4] = listProductos.get(i).getEstado();

            tableModelProducto.addRow(ob);
        }

        return tableModelProducto;
    }

    @Override
    public List read() {

        List<EntidadProducto> productList = new ArrayList<>();
        String SQLQuery = "SELECT * FROM productos";

        try {
            prepStatement = connection.prepareStatement(SQLQuery);
            resSet = prepStatement.executeQuery();

            while (resSet.next()) {
                EntidadProducto entProduct = new EntidadProducto();

                entProduct.setIdProducto(resSet.getInt(1));
                entProduct.setNombre(resSet.getString(2));
                entProduct.setPrecio(resSet.getFloat(3));
                entProduct.setStock(resSet.getInt(4));
                entProduct.setEstado(resSet.getString(5));

                productList.add(entProduct);
            }
        } catch (SQLException error) {

            String text = "Error en el Metodo ProductoDAO.Read";
            message.errorInSQLQuery(text, error);
            message.productReadFailed();
        }
        return productList;
    }

    public void createProducto(Map formData) {

        boolean queryResult = false;
        int resp = this.message.productoCreateConfirmation();

        if (resp == 0) {
            queryResult = this.create(formData);
        } else {
            this.message.operationCanceled();
        }

        if (queryResult) {
            message.productoCreateSuccessfully();
        }

    }

    @Override
    public boolean create(Map formData) {

        boolean result = false;
        String SQLQuery = "insert into `productos` (Nombre, Precio, Stock, Estado)VALUES(?,?,?,?)";

        try {
            prepStatement = connection.prepareStatement(SQLQuery);

            prepStatement.setObject(1, formData.get("nombre"));
            prepStatement.setObject(2, formData.get("precio"));
            prepStatement.setObject(3, formData.get("stock"));
            prepStatement.setObject(4, formData.get("estado"));

            prepStatement.executeUpdate();
            result = true;

        } catch (SQLException error) {

            result = false;
            String text = "Error en el metodo ProductoDAO.Create";

            message.errorInSQLQuery(text, error);
            message.productoCreateFailed();
        }

        return result;
    }

    public void updateProducto(int selectedRow, Map formData) {
        int queryResult = 0;
        int confirmation = 1;

        if (selectedRow == -1) {
            message.noSelectedRow();
        } else {
            confirmation = message.productoUpdateConfirmation();
        }

        if (confirmation == 0) {
            queryResult = this.update(formData);
        } else {
            message.operationCanceled();
        }

        if (queryResult == 1) {
            message.productoUpdatedSuccessfully();
        }
    }

    @Override
    public int update(Map formData) {

        int result = 0;
        String SQLQuery = "update `productos` set Nombre=?, Precio=?, Stock=?, Estado=? where IdProducto=?";

        try {
            prepStatement = connection.prepareStatement(SQLQuery);
            prepStatement.setObject(1, formData.get("nombre"));
            prepStatement.setObject(2, formData.get("precio"));
            prepStatement.setObject(3, formData.get("stock"));
            prepStatement.setObject(4, formData.get("estado"));
            prepStatement.setObject(5, formData.get("idProducto"));

            result = prepStatement.executeUpdate();

        } catch (SQLException error) {

            String text = "Error en el metodo ProductoDAO.Update";
            message.errorInSQLQuery(text, error);
            message.productoUpdateFailed();
        }

        return result;
    }

    public void deleteProducto(int selectedRow, int tableRowIdProducto) {

        int queryResult = 0;
        int confirmation = 1;

        if (selectedRow == -1) {
            message.noSelectedRow();
        } else {
            confirmation = message.productoDeleteConfirmation();
        }

        if (confirmation == 0 && selectedRow > -1) {
            queryResult = this.delete(tableRowIdProducto);
        } else {
            message.operationCanceled();
        }

        if (queryResult == 1) {
            message.productoDeletedSuccessfully();
        }

    }

    @Override
    public int delete(int idProducto) {

        int queryResult = 0;
        String SQLQuery = "DELETE FROM productos WHERE IdProducto=?";

        try {
            prepStatement = connection.prepareStatement(SQLQuery);
            prepStatement.setInt(1, idProducto);

            queryResult = prepStatement.executeUpdate();
        } catch (SQLException error) {

            String text = "Error en el metodo ProductoDAO.Delete";
            message.errorInSQLQuery(text, error);
            message.productDeleteFailed();
        }

        return queryResult;
    }

}
