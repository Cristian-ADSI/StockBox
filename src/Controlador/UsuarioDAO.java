package Controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import Modelos.EntidadUsuario;
import Vista.Mensaje;
import java.util.Map;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class UsuarioDAO implements CRUD {

    Conexion conexion = new Conexion();
    Mensaje message = new Mensaje();

    PreparedStatement prepStatement;
    ResultSet resSet;
    Connection connection = conexion.Conectar();

    public EntidadUsuario getUsuarioData(String DNI, String user) {

        EntidadUsuario entUsuario = new EntidadUsuario();
        String SQLQuery = "SELECT * FROM usuarios WHERE DNI=? AND Usuario=? ";

        try {

            prepStatement = connection.prepareStatement(SQLQuery);
            prepStatement.setString(1, DNI);
            prepStatement.setString(2, user);

            resSet = prepStatement.executeQuery();

            while (resSet.next()) {
                entUsuario.setIdUsuario(resSet.getString(1));
                entUsuario.setDNI(resSet.getString(2));
                entUsuario.setNombre(resSet.getString(3));
                entUsuario.setStatus(resSet.getString(5));
                entUsuario.setUsuario(resSet.getString(6));
                entUsuario.setRole(resSet.getString(8));
            }
        } catch (SQLException error) {
            String text = "Error en el metodo UsuarioDAO.getUserData: ";
            message.errorInSQLQuery(text, error);
        }

        return entUsuario;
    }

    public DefaultTableModel listUsuariosOnTable(JTable tableUsuarios) {
        DefaultTableModel tableModelUsuario = new DefaultTableModel();

        List<EntidadUsuario> listUsuarios = this.read();
        tableModelUsuario = (DefaultTableModel) tableUsuarios.getModel();
        Object[] ob = new Object[6];

        for (int i = 0; i < listUsuarios.size(); i++) {
            ob[0] = listUsuarios.get(i).getIdUsuario();
            ob[1] = listUsuarios.get(i).getDNI();
            ob[2] = listUsuarios.get(i).getNombre();
            ob[3] = listUsuarios.get(i).getTelefono();
            ob[4] = listUsuarios.get(i).getStatus();
            ob[5] = listUsuarios.get(i).getUsuario();

            tableModelUsuario.addRow(ob);
        }

        return tableModelUsuario;
    }

    @Override
    public List read() {

        List<EntidadUsuario> usersList = new ArrayList<>();

        String SQLQuery = "select * from usuarios";

        try {

            prepStatement = connection.prepareStatement(SQLQuery);
            resSet = prepStatement.executeQuery();

            while (resSet.next()) {
                EntidadUsuario entUsuario = new EntidadUsuario();

                entUsuario.setIdUsuario(resSet.getString(1));
                entUsuario.setDNI(resSet.getString(2));
                entUsuario.setNombre(resSet.getString(3));
                entUsuario.setTelefono(resSet.getString(4));
                entUsuario.setStatus(resSet.getString(5));
                entUsuario.setUsuario(resSet.getString(6));

                usersList.add(entUsuario);
            }
        } catch (SQLException error) {

            String text = "Eror en el Metodo UsuarioDAO.Read usuarios: ";
            message.errorInSQLQuery(text, error);
        }

        return usersList;
    }

    public void createUsuario(Map formData) {

        boolean queryResult = false;

        int confirmation = this.message.usuarioCreateConfirmation();

        if (confirmation == 0) {

            queryResult = this.create(formData);
        } else {
            this.message.operationCanceled();
        }

        if (queryResult) {
            message.usuarioCreatedSuccessfully();
        }
    }

    @Override
    public boolean create(Map formData) {

        boolean queryResult;

        String SQLQuery = "insert into usuarios (DNI, Nombre, Telefono, Estado, Usuario) values(?,?,?,?,?)";

        try {
            prepStatement = connection.prepareStatement(SQLQuery);
            prepStatement.setObject(1, formData.get("DNI"));
            prepStatement.setObject(2, formData.get("nombre"));
            prepStatement.setObject(3, formData.get("telefono"));
            prepStatement.setObject(4, formData.get("estado"));
            prepStatement.setObject(5, formData.get("usuario"));

            prepStatement.executeUpdate();
            queryResult = true;

        } catch (SQLException error) {
            queryResult = false;

            String text = "Eror en el metodo getUserData.Create: ";
            message.usuarioCreateFailed();
            message.errorInSQLQuery(text, error);
        }

        return queryResult;
    }

    public void updateUsuario(int selectedRow, Map formData) {
        int queryResult = 0;
        int confirmation = 1;

        if (selectedRow == -1) {
            message.noSelectedRow();
        } else {
            confirmation = message.usuarioUpdateConfirmation();
        }

        if (confirmation == 0) {
            queryResult = this.update(formData);
        } else {
            message.operationCanceled();
        }

        if (queryResult == 1) {
            message.usuarioUpdatedSuccessfully();
        }
    }

    @Override
    public int update(Map formData) {

        int queryResult = 0;
        String SQLQuery = "update `usuarios` set DNI=?, Nombre=?, Telefono=?, Estado=?, Usuario=? where IdUsuario=? ";

        try {

            prepStatement = connection.prepareStatement(SQLQuery);
            prepStatement.setObject(1, formData.get("DNI"));
            prepStatement.setObject(2, formData.get("nombre"));
            prepStatement.setObject(3, formData.get("telefono"));
            prepStatement.setObject(4, formData.get("estado"));
            prepStatement.setObject(5, formData.get("usuario"));
            prepStatement.setObject(6, formData.get("idUsuario"));

            queryResult = prepStatement.executeUpdate();

        } catch (SQLException error) {

            String text = "Eror en el Metodo UsuarioDAO.Update: ";
            message.usuarioUpdateFailed();
            message.errorInSQLQuery(text, error);
        }

        return queryResult;
    }

    public void deleteUsuario(int selectedRow, int tableRowIdUsuario) {

        int queryResult = 0;
        int confirmation = 1;

        if (selectedRow == -1) {
            message.noSelectedRow();
        } else {
            confirmation = message.usuarioDeleteConfirmation();
        }

        if (confirmation == 0 && selectedRow > -1) {
            queryResult = this.delete(tableRowIdUsuario);
        } else {
            message.operationCanceled();
        }

        if (queryResult == 1) {
            message.usuarioDeletedSuccessfully();
        }

    }

    @Override
    public int delete(int tableRowIdUsuario) {

        int queryResult = 0;

        String SQLQuery = "delete from `usuarios` where IdUsuario = ?";
        try {

            prepStatement = connection.prepareStatement(SQLQuery);
            prepStatement.setInt(1, tableRowIdUsuario);
            queryResult = prepStatement.executeUpdate();

        } catch (SQLException error) {

            String text = "Error en el Metodo UsuarioDAO.delete usuario: ";
            message.usuarioDeleteFailed();
            message.errorInSQLQuery(text, error);
        }

        return queryResult;
    }

}
