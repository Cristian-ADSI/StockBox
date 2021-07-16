package Controlador;

import Modelos.EntidadVendedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class VendedorDAO implements CRUD {

    //Variables e Instancias de las Consultas SQL
    PreparedStatement ps;
    ResultSet rs;

    Conexion cn = new Conexion();
    Connection con;
    /////////////////////////////////////////////

    public EntidadVendedor ValidarVendedor(String cedula, String user) {
        EntidadVendedor ev = new EntidadVendedor();
        String sql = "SELECT * FROM usuarios WHERE Cedula=? AND User_2=? ";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, cedula);
            ps.setString(2, user);
            rs = ps.executeQuery();
            while (rs.next()) {
                ev.setId_Vendedor(rs.getInt(1));
                ev.setCedula(rs.getString(2));
                ev.setEstado(rs.getString(5));
                ev.setUser_2(rs.getString(6));
                ev.setRole(rs.getString(7));
                System.err.println("Estdo de la consulta" + rs.getString(5));
            }
        } catch (SQLException e) {
            System.err.println("Error en la Consulta SQL" + e);
        }
        return ev;
    }

    @Override
    public List Listar() {
        List<EntidadVendedor> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                EntidadVendedor ev = new EntidadVendedor();
                ev.setId_Vendedor(rs.getInt(1));
                ev.setCedula(rs.getString(2));
                ev.setNombre(rs.getString(3));
                ev.setTelefono(rs.getString(4));
                ev.setEstado(rs.getString(5));
                ev.setUser_2(rs.getString(6));
                lista.add(ev);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Eror en el Metodo Listar usuarios");
            System.err.println(e);
        }
        return lista;
    }

    @Override
    public int Crear(Object[] ob) {
        int r = 0;
        String sql = "INSERT INTO usuarios (Cedula, Nombre, Telefono, Estado, User_2) VALUES(?,?,?,?,?)";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ob[0]);
            ps.setObject(2, ob[1]);
            ps.setObject(3, ob[2]);
            ps.setObject(4, ob[3]);
            ps.setObject(5, ob[4]);
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
        String sql = "UPDATE usuarios SET Cedula=?, Nombre=?, Telefono=?, Estado=?, User_2=? WHERE Id_Vendedor=? ";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ob[0]);
            ps.setObject(2, ob[1]);
            ps.setObject(3, ob[2]);
            ps.setObject(4, ob[3]);
            ps.setObject(5, ob[4]);
            ps.setObject(6, ob[5]);
            r = ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registro Actualizado Correctamente"); 
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se Pudo Acutualizar el Registro");
            System.err.println(e);
        }
        return r;
    }

    @Override
    public void Eliminar(int id) {
        String sql ="DELETE FROM usuarios WHERE Id_Vendedor=?";
        try {
            con=cn.Conectar();
            ps=con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en el Metodo Eliminar");
            System.err.println(e);
        }
    }
}
