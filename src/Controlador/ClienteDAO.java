package Controlador;

import Modelos.EntidadCliente;
import Vista.Mensaje;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ClienteDAO implements CRUD {

    Mensaje message = new Mensaje();
    Conexion conexion = new Conexion();

    Connection connection = conexion.Conectar();
    PreparedStatement prepStatement;
    ResultSet resSet;

    public EntidadCliente getClienteData(String DNI) {

        EntidadCliente entCliente = new EntidadCliente();
        String SQLQuery = "SELECT * FROM clientes WHERE DNI=?";

        try {
            prepStatement = connection.prepareStatement(SQLQuery);
            prepStatement.setString(1, DNI);

            resSet = prepStatement.executeQuery();

            while (resSet.next()) {
                entCliente.setIdCliente(resSet.getInt(1));
                entCliente.setDNI(resSet.getString(2));
                entCliente.setNombre(resSet.getString(3));
                entCliente.setDireccion(resSet.getString(4));
                entCliente.setEstado(resSet.getString(5));
            }

        } catch (SQLException error) {

            String text = "Error en el metodo ClienteDAO.searchCustomer: ";
            message.errorInSQLQuery(text, error);
            message.clienteSearchFailed();
        }

        return entCliente;
    }

    public DefaultTableModel listClientesOnTable(JTable tableUsuarios) {

        DefaultTableModel tableModelUsuario = new DefaultTableModel();

        List<EntidadCliente> listClentes = this.read();
        tableModelUsuario = (DefaultTableModel) tableUsuarios.getModel();
        Object[] obj = new Object[5];

        for (int i = 0; i < listClentes.size(); i++) {
            obj[0] = listClentes.get(i).getIdCliente();
            obj[1] = listClentes.get(i).getDNI();
            obj[2] = listClentes.get(i).getNombre();
            obj[3] = listClentes.get(i).getDireccion();
            obj[4] = listClentes.get(i).getEstado();

            tableModelUsuario.addRow(obj);
        }

        return tableModelUsuario;

    }

    @Override
    public List read() {

        List<EntidadCliente> customersList = new ArrayList<>();
        String SQLQuery = "select * from `clientes`";

        try {
            prepStatement = connection.prepareStatement(SQLQuery);
            resSet = prepStatement.executeQuery();

            while (resSet.next()) {
                EntidadCliente entCliente = new EntidadCliente();

                entCliente.setIdCliente(resSet.getInt(1));
                entCliente.setDNI(resSet.getString(2));
                entCliente.setNombre(resSet.getString(3));
                entCliente.setDireccion(resSet.getString(4));
                entCliente.setEstado(resSet.getString(5));

                customersList.add(entCliente);
            }

        } catch (SQLException error) {

            String text = "Error en el metodo ClienteDAO.Read: ";
            message.errorInSQLQuery(text, error);
            message.clienteReadFailed();
        }

        return customersList;
    }

    public void createCliente(Map formData) {
        int resp = this.message.clientesCreateConfirmation();

        if (resp == 0) {
            this.create(formData);
        } else {
            this.message.operationCanceled();
        }
    }

    @Override
    public boolean create(Map formData) {

        int result = 0;
        String SQLQuery = "INSERT INTO clientes(DNI, Nombre, Direccion, Estado) VALUES(?,?,?,?)";

        try {
            prepStatement = connection.prepareStatement(SQLQuery);
            prepStatement.setObject(1, formData.get("DNI"));
            prepStatement.setObject(2, formData.get("nombre"));
            prepStatement.setObject(3, formData.get("direccion"));
            prepStatement.setObject(4, formData.get("estado"));

            result = prepStatement.executeUpdate();

            message.clienteCreatedSuccessfully();

        } catch (SQLException error) {

            String text = "Error en el metodo ClienteDAO.Create: ";
            message.errorInSQLQuery(text, error);
            message.ClienteCreateFailed();
        }

        return true;
    }

    public void updateCliente(int selectedRow, Map formData) {
        int queryResult = 0;
        int confirmation = 1;

        if (selectedRow == -1) {
            message.noSelectedRow();
        } else {
            confirmation = message.clienteUpdateConfirmation();
        }

        if (confirmation == 0) {
            queryResult = this.update(formData);
        } else {
            message.operationCanceled();
        }

        if (queryResult == 1) {
            message.clienteUpdateSuccessfully();
        }
    }

    @Override
    public int update(Map formData) {

        int result = 0;
        String SQLQuery = "update `clientes` set DNI=?, Nombre=?, Direccion=?, Estado=? where IdCliente=?";

        try {
            prepStatement = connection.prepareStatement(SQLQuery);
            prepStatement.setObject(1, formData.get("DNI"));
            prepStatement.setObject(2, formData.get("nombre"));
            prepStatement.setObject(3, formData.get("direccion"));
            prepStatement.setObject(4, formData.get("estado"));
            prepStatement.setObject(5, formData.get("idCliente"));

            result = prepStatement.executeUpdate();

        } catch (SQLException error) {

            String text = "Error en el metodo ClienteDAO.Update: ";
            message.errorInSQLQuery(text, error);
            message.clienteUpdateFailed();
        }

        return result;
    }

    public void deleteCliente(int selectedRow, int tableRowIdCliente) {

        int queryResult = 0;
        int confirmation = 1;

        if (selectedRow == -1) {
            message.noSelectedRow();
        } else {
            confirmation = message.clienteDeleteConfirmation();
        }

        if (confirmation == 0 && selectedRow > -1) {
            queryResult = this.delete(tableRowIdCliente);
        } else {
            message.operationCanceled();
        }

        if (queryResult == 1) {
            message.clienteDeletedSuccessfully();
        }
    }

    @Override
    public int delete(int idCliente) {

        String SQLQuery = "delete from `clientes` where IdCliente=?";

        try {
            prepStatement = connection.prepareStatement(SQLQuery);
            prepStatement.setInt(1, idCliente);

            prepStatement.executeUpdate();

        } catch (SQLException error) {

            String text = "Error en el metodo ClienteDAO.Delete: ";
            message.errorInSQLQuery(text, error);
            message.clienteDeleteFailed();
        }

        return 1;

    }
}
