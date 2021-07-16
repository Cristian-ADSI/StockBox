package Controlador;

import Modelos.EntidadProducto;
import java.sql.Connection;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ProductoDAO implements CRUD {

    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    int r;
    
    public int ActualizarStock(int stock, int idproducto){
        String sql ="UPDATE productos SET Stock=? where Id_Producto =?";
        try {
            con=cn.Conectar();
            ps=con.prepareStatement(sql);
            ps.setInt(1, stock);
            ps.setInt(2, idproducto);
            ps.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo ACtualizar el Stock");
            System.err.println(e);
        }
        return r;
    }

    public EntidadProducto BuscarProducto(int Id_Producto) {
        EntidadProducto ep = new EntidadProducto();
        String sql = "SELECT * FROM productos WHERE Id_Producto=?";
        try {
            con=cn.Conectar();
            ps=con.prepareStatement(sql);
            ps.setInt(1, Id_Producto);
            rs=ps.executeQuery();
            while (rs.next()) {
                ep.setId_Producto(rs.getInt(1));
                ep.setNombre(rs.getString(2));
                ep.setPrecio(rs.getFloat(3));
                ep.setStock(rs.getInt(4));
                ep.setEstado(rs.getString(5));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Eror en el Metodo Buscar Cliente");
            System.err.println(e);
        }
        return ep;
    }

    @Override
    public List Listar() {
        List<EntidadProducto> lista = new ArrayList<>();
        String sql = "SELECT * FROM productos";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                EntidadProducto ep = new EntidadProducto();
                ep.setId_Producto(rs.getInt(1));
                ep.setNombre(rs.getString(2));
                ep.setPrecio(rs.getFloat(3));
                ep.setStock(rs.getInt(4));
                ep.setEstado(rs.getString(5));
                lista.add(ep);
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
        String sql = "INSERT INTO productos (Nombre, Precio,Stock,Estado)VALUES(?,?,?,?)";

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
            JOptionPane.showMessageDialog(null, "No se pudo crear el registro verificar \n La longitud de los campos\n"
                    + "Que no tenca campos vacios \n El tipo dato ingresado");
            System.err.println(e);
        }
        return r;
    }

    @Override
    public int Actualizar(Object[] ob) {
        int r = 0;
        String sql = "UPDATE productos SET Nombre=?, Precio=?, Stock=?, Estado=? WHERE Id_Producto=?";
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
        String sql = "DELETE FROM productos WHERE Id_Producto=?";
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
