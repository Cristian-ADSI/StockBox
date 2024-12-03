package Controlador;

import Modelos.EntidadCliente;
import Vista.Mensaje;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ClienteDAO implements CRUD {
    
    Mensaje message   = new Mensaje();
    Conexion conexion = new Conexion();

    Connection connection = conexion.Conectar();
    PreparedStatement prepStatement;
    ResultSet resSet;
    
    public EntidadCliente searchCustomer(String DNI){
        
        EntidadCliente entCliente = new EntidadCliente();
        String SQLQuery="SELECT * FROM clientes WHERE DNI=?";
        
        try {
            prepStatement = connection.prepareStatement(SQLQuery);
            prepStatement.setString(1, DNI);
            
            resSet=prepStatement.executeQuery();
            
            while (resSet.next()) {  
                entCliente.setIdCliente(   resSet.getInt(1));
                entCliente.setDNI(       resSet.getString(2));
                entCliente.setNombre(       resSet.getString(3));
                entCliente.setDireccion(    resSet.getString(4));
                entCliente.setEstado(       resSet.getString(5));       
            }
            
        } catch (SQLException error) {
            
            String text = "Error en el metodo ClienteDAO.searchCustomer: ";
            message.errorInSQLQuery(text, error);
            message.searchCustomerFailed();
        }
        
        return entCliente;
    }
    
    @Override
    public List Read() {
        
        List<EntidadCliente> customersList = new ArrayList<>();
        EntidadCliente entCliente = new EntidadCliente();
        String SQLQuery = "SELECT * FROM clientes";
        
        try {          
            prepStatement = connection.prepareStatement(SQLQuery);
            resSet = prepStatement.executeQuery();
            
            while (resSet.next()) {
                
                entCliente.setIdCliente(   resSet.getInt(1));
                entCliente.setDNI(       resSet.getString(2));
                entCliente.setNombre(       resSet.getString(3));
                entCliente.setDireccion(    resSet.getString(4));
                entCliente.setEstado(       resSet.getString(5));
                
                customersList.add(entCliente);
            }
            
        } catch (SQLException error) {
            
            String text = "Error en el metodo ClienteDAO.Read: ";
            message.errorInSQLQuery(text, error);
            message.readCustomersFailed();
        }
        return customersList;
    }

    @Override
    public int Create(Object[] ob) {
        
        int result = 0;
        String SQLQuery = "INSERT INTO clientes(DNI, Nombre, Direccion, Estado) VALUES(?,?,?,?)";
        
        try {
            prepStatement = connection.prepareStatement(SQLQuery);
            prepStatement.setObject(1, ob[0]);
            prepStatement.setObject(2, ob[1]);
            prepStatement.setObject(3, ob[2]);
            prepStatement.setObject(4, ob[3]);
            
            result = prepStatement.executeUpdate();
            
            message.createCustomerSuccess();
            
        } catch (SQLException error) {
            
            String text = "Error en el metodo ClienteDAO.Create: ";
            message.errorInSQLQuery(text, error);
            message.createCustomerFailed();
        }
        
        return result;
    }

    @Override
    public int Update(Object[] ob) {
        
        int result = 0;
        String SQLQuery = "UPDATE clientes SET DNI=?, Nombre=?, Direccion=?, Estado=? WHERE IdCliente=?";
        
        try {
            prepStatement = connection.prepareStatement(SQLQuery);
            prepStatement.setObject(1, ob[0]);
            prepStatement.setObject(2, ob[1]);
            prepStatement.setObject(3, ob[2]);
            prepStatement.setObject(4, ob[3]);
            prepStatement.setObject(5, ob[4]);
            
            result = prepStatement.executeUpdate();
            message.updateCustomerSuccess();
            
        } catch (SQLException error) {
            
            String text = "Error en el metodo ClienteDAO.Update: ";
            message.errorInSQLQuery(text, error);
            message.updateCustomerFailed();
        }
        
        return result;
    }

    @Override
    public void Delete(int idCliente) {
        
        String SQLQuery = "DELETE FROM Clientes WHERE Id_Cliente=?";
        
        try {
            prepStatement = connection.prepareStatement(SQLQuery);
            prepStatement.setInt(1, idCliente);
            
            prepStatement.executeUpdate();
            message.deleteCustomerSuccess();
            
        } catch (SQLException error) {
            
            String text = "Error en el metodo ClienteDAO.Delete: ";
            message.errorInSQLQuery(text, error);
            message.deleteCustomerFailed();
        }

    }
}
