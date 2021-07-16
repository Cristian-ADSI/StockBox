package Controlador;

import Modelos.EntidadCliente;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ClienteDAO implements CRUD {

    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    
    public EntidadCliente BuscarCliente(String cedula){
        EntidadCliente ec = new EntidadCliente();
        String sql="SELECT * FROM clientes WHERE Cedula=?";
        try {
            con=cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, cedula);
            rs=ps.executeQuery();
            while (rs.next()) {  
                ec.setId_Cliente(rs.getInt(1));
                ec.setCedula(rs.getString(2));
                ec.setNombre(rs.getString(3));
                ec.setDireccion(rs.getString(4));
                ec.setEstado(rs.getString(5));       
            }         
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Eror en el Metodo Buscar Cliente");
            System.err.println(e);
        }
        return ec;
    }
    
    @Override
    public List Listar() {
        List<EntidadCliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM clientes";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                EntidadCliente ec = new EntidadCliente();
                ec.setId_Cliente(rs.getInt(1));
                ec.setCedula(rs.getString(2));
                ec.setNombre(rs.getString(3));
                ec.setDireccion(rs.getString(4));
                ec.setEstado(rs.getString(5));
                lista.add(ec);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Eror en el Metodo Listar");
            System.err.println(e);
        }
        return lista;
    }

    @Override
    public int Crear(Object[] ob) {
        int r = 0;
        String sql = "INSERT INTO clientes(Cedula, Nombre, Direccion, Estado) VALUES(?,?,?,?)";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ob[0]);
            ps.setObject(2, ob[1]);
            ps.setObject(3, ob[2]);
            ps.setObject(4, ob[3]);
            r = ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registro Creado Correctamente");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo crear el registro \n Verifique la longitud de los campos y "
                    + "que no tenca campos vacios");
            System.err.println(e);
        }
        return r;
    }

    @Override
    public int Actualizar(Object[] ob) {
        int r = 0;
        String sql = "UPDATE clientes SET Cedula=?, Nombre=?, Direccion=?, Estado=? WHERE Id_Cliente=?";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ob[0]);
            ps.setObject(2, ob[1]);
            ps.setObject(3, ob[2]);
            ps.setObject(4, ob[3]);
            ps.setObject(5, ob[4]);
            r = ps.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se Pudo Acutualizar el Registro");
            System.err.println(e);
        }
        return r;
    }

    @Override
    public void Eliminar(int id) {
        String sql = "DELETE FROM Clientes WHERE Id_Cliente=?";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en el Metodo Eliminar");
            System.err.println(e);
        }

    }
}
