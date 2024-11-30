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

public class ProductoDAO implements CRUD {

    Conexion conexion           = new Conexion();
    Mensaje message             = new Mensaje();

    Connection connection       = conexion.Conectar();
    PreparedStatement prepStatement;
    ResultSet resSet;


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

    public EntidadProducto searchProduct(int idProducto) {

        EntidadProducto entProduct  = new EntidadProducto();
        String SQLQuery             = "SELECT * FROM productos WHERE IdProducto=?";

        try {

            prepStatement = connection.prepareStatement(SQLQuery);
            prepStatement.setInt(1, idProducto);

            resSet = prepStatement.executeQuery();

            while (resSet.next()) {
                
                entProduct.setIdProducto(resSet.getInt  (1));
                entProduct.setNombre(resSet.getString   (2));
                entProduct.setPrecio(resSet.getFloat    (3));
                entProduct.setStock(resSet.getInt       (4));
                entProduct.setEstado(resSet.getString   (5));
            }
        } catch (SQLException error) {
        
            String text = "Eror en el Metodo ProductoDAO.searchProduct";
            message.errorInSQLQuery(text, error);
            message.searchProductFailed();
        }

        return entProduct;
    }

    @Override
    public List Read() {

        EntidadProducto entProduct          = new EntidadProducto();
        List<EntidadProducto> productList   = new ArrayList<>();
        String SQLQuery                     = "SELECT * FROM productos";
        
        try {
            prepStatement = connection.prepareStatement(SQLQuery);
            resSet = prepStatement.executeQuery();
            
            while (resSet.next()) {
                
                entProduct.setIdProducto(resSet.getInt  (1));
                entProduct.setNombre(resSet.getString   (2));
                entProduct.setPrecio(resSet.getFloat    (3));
                entProduct.setStock(resSet.getInt       (4));
                entProduct.setEstado(resSet.getString   (5));
                
                productList.add(entProduct);
            }
        } catch (SQLException error) {
            
            String text = "Eror en el Metodo ProductoDAO.Read";
            message.errorInSQLQuery(text, error);
            message.productReadFailed();
        }
        return productList;
    }

    @Override
    public int Create(Object[] ob) {
        
        int result      = 0;
        String SQLQuery = "INSERT INTO productos (Nombre, Precio,Stock,Estado)VALUES(?,?,?,?)";

        try {
            prepStatement = connection.prepareStatement(SQLQuery);
            
            prepStatement.setObject(1, ob[0]);
            prepStatement.setObject(2, ob[1]);
            prepStatement.setObject(3, ob[2]);
            prepStatement.setObject(4, ob[3]);
            
            result = prepStatement.executeUpdate();
            
            message.productCreatedSuccessfully();
            
        } catch (SQLException error) {
            
            String text = "Error en el metodo ProductoDAO.Create";
            message.errorInSQLQuery(text, error);
            message.productCreateFailed();
            
        }
        return result;
    }

    @Override
    public int Update(Object[] ob) {
        
        int result      = 0;
        String SQLQuery = "UPDATE productos SET Nombre=?, Precio=?, Stock=?, Estado=? WHERE IdProducto=?";
        
        try {        
            prepStatement = connection.prepareStatement(SQLQuery);
            prepStatement.setObject(1, ob[0]);
            prepStatement.setObject(2, ob[1]);
            prepStatement.setObject(3, ob[2]);
            prepStatement.setObject(4, ob[3]);
            prepStatement.setObject(5, ob[4]);
            
            result = prepStatement.executeUpdate();
            message.productUpdatedSuccessfully();
            
        } catch (SQLException error) {
      
            String text = "Error en el metodo ProductoDAO.Update";
            message.errorInSQLQuery(text, error);
            message.productUpdateFailed();           
        }
        
        return result;
    }

    @Override
    public void Eliminar(int idProducto) {
        
        String SQLQuery = "DELETE FROM productos WHERE IdProducto=?";
        
        try {
            prepStatement = connection.prepareStatement(SQLQuery);
            prepStatement.setInt(1, idProducto);
            
            prepStatement.executeUpdate();
            message.productDeletedSuccessfully();
        } catch (SQLException error) {
            
            String text = "Error en el metodo ProductoDAO.Delete";
            message.errorInSQLQuery(text, error);
            message.productDeleteFailed();
        }
    }

}
