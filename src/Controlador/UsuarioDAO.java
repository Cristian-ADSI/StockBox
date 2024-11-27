package Controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import Modelos.EntidadUsuario;
import Vista.Mensaje;

public class UsuarioDAO implements CRUD {

    Conexion conexion           = new Conexion();
    Mensaje message             = new Mensaje();

    PreparedStatement prepStatement;
    ResultSet resSet;
    Connection connection = conexion.Conectar();
    

    public EntidadUsuario getUserData(String DNI, String user) {

        EntidadUsuario entUsuario   = new EntidadUsuario();
        String SQLQuery = "SELECT * FROM usuarios WHERE DNI=? AND Usuario=? ";

        try {

            prepStatement = connection.prepareStatement(SQLQuery);
            prepStatement.setString(1, DNI);
            prepStatement.setString(2, user);

            resSet = prepStatement.executeQuery();

            while (resSet.next()) {
                entUsuario.setIdUsuario(resSet.getString(1));
                entUsuario.setDNI(      resSet.getString(2));
                entUsuario.setNombre(   resSet.getString(3));
                entUsuario.setStatus(   resSet.getString(5));
                entUsuario.setUsuario(  resSet.getString(6));
                entUsuario.setRole(     resSet.getString(7));
            }
        } catch (SQLException error) {
            String text = "Error en el metodo UsuarioDAO.getUserData: ";
            message.errorInSQLQuery(text, error);
        }

        return entUsuario;
    }

    @Override
    public List Read() {
        
        EntidadUsuario entUsuario   = new EntidadUsuario();
        List<EntidadUsuario> usersList = new ArrayList<>();
        
        String SQLQuery = "SELECT * FROM usuarios";
        
        try {
            
            prepStatement = connection.prepareStatement(SQLQuery);
            resSet = prepStatement.executeQuery();
            
            while (resSet.next()) {
               
                entUsuario.setIdUsuario(resSet.getString(1));
                entUsuario.setDNI(      resSet.getString(2));
                entUsuario.setNombre(   resSet.getString(3));
                entUsuario.setTelefono( resSet.getString(4));
                entUsuario.setStatus(   resSet.getString(5));
                entUsuario.setUsuario(  resSet.getString(6));
                
                usersList.add(entUsuario);
            }
        } catch (SQLException error) {
            
            String text ="Eror en el Metodo UsuarioDAO.Read usuarios: ";
            message.errorInSQLQuery(text, error);            
        }
        
        return usersList;
    }

    @Override
    public int Create(Object[] ob) {
        
        int queryResult = 0;
        
        String SQLQuery = "INSERT INTO usuarios (Cedula, Nombre, Telefono, Estado, User_2) VALUES(?,?,?,?,?)";
        
        try {
            
            prepStatement = connection.prepareStatement(SQLQuery);
            prepStatement.setObject(1, ob[0]);
            prepStatement.setObject(2, ob[1]);
            prepStatement.setObject(3, ob[2]);
            prepStatement.setObject(4, ob[3]);
            prepStatement.setObject(5, ob[4]);
            
            queryResult = prepStatement.executeUpdate();
            message.userCreatedSuccessfully();
            
        } catch (SQLException error) {
            
            String text ="Eror en el metodo getUserData.Create usuarios: ";

            message.userCreationFailed();
            message.errorInSQLQuery(text, error);
        }
        return queryResult;
    }

    @Override
    public int Update(Object[] ob) {
        int queryResult = 0;
        
        String SQLQuery = "UPDATE usuarios SET Cedula=?, Nombre=?, Telefono=?, Estado=?, User_2=? WHERE Id_Vendedor=? ";
        try {
           
            prepStatement = connection.prepareStatement(SQLQuery);
            prepStatement.setObject(1, ob[0]);
            prepStatement.setObject(2, ob[1]);
            prepStatement.setObject(3, ob[2]);
            prepStatement.setObject(4, ob[3]);
            prepStatement.setObject(5, ob[4]);
            prepStatement.setObject(6, ob[5]);
            
            queryResult = prepStatement.executeUpdate();
            message.userUpdatedSuccessfully();
            
        } catch (SQLException error) {
            
            String text ="Eror en el Metodo UsuarioDAO.Update usuarios: ";
            message.userUpdateFailed();
            message.errorInSQLQuery(text, error);
        }
        
        return queryResult;
    }

    @Override
    public void Eliminar(int id) {
        
        String SQLQuery = "DELETE FROM usuarios WHERE Id_Vendedor=?";
        try {

            prepStatement = connection.prepareStatement(SQLQuery);
            prepStatement.setInt(1, id);
            prepStatement.executeUpdate();
            
            message.userDeletdSuccessfully();
            
        } catch (SQLException error) {
   
            
            String text ="Error en el Metodo UsuarioDAO.Eliminar usuario: ";
            message.userDeleteFailed();
            message.errorInSQLQuery(text, error);
        }
    }
}
