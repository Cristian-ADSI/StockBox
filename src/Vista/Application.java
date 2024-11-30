package Vista;

import Controlador.ClienteDAO;
import Controlador.ProductoDAO;
import Controlador.UsuarioDAO;
import Controlador.VentasDAO;
import Modelos.EntidadCliente;
import Modelos.EntidadDetalleVenta;
import Modelos.EntidadProducto;
import Modelos.EntidadUsuario;
import Modelos.EntidadVenta;
import java.awt.MouseInfo;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

public class Application extends javax.swing.JFrame {

    //==========Instancias de Clases=========//
    EntidadUsuario ev = new EntidadUsuario();
    UsuarioDAO vdao = new UsuarioDAO();
    DefaultTableModel modeloV = new DefaultTableModel();

    EntidadCliente ec = new EntidadCliente();
    ClienteDAO cdao = new ClienteDAO();
    DefaultTableModel modeloC = new DefaultTableModel();

    EntidadProducto ep = new EntidadProducto();
    ProductoDAO pdao = new ProductoDAO();
    DefaultTableModel modeloP = new DefaultTableModel();

    DefaultTableModel modeloDV = new DefaultTableModel();
    EntidadDetalleVenta edv = new EntidadDetalleVenta();
    LogingForm lf = new LogingForm();

    VentasDAO vendao = new VentasDAO();
    //==========Variables para el Panel de Ventas========
    int id_Porducto;
    String id_Usuario;
    int nro = 0;
    float tPagar;
    int cantidad;
    float precio;

    public JTextField getTxtTotal() {
        return txtTotal;
    }

    public void setTxtTotal(JTextField txtTotal) {
        this.txtTotal = txtTotal;
    }

    //==========Variables Para seleccuonasr Filas de las tablas=====//
    int idV;
    int idC;
    int idP;
    //===========Variables para poder arrastrar el Panel============//
    int X;
    int Y;

    //====Metodo Constructor
    public Application() {
        initComponents();
        this.setLocationRelativeTo(null);
        btnClose.setBackground(new java.awt.Color(0, 0, 0, 0));
        btnMinimize.setBackground(new java.awt.Color(0, 0, 0, 0));
        txtRole.setBackground(new java.awt.Color(0, 0, 0, 0));
        ListarV();
        ListarC();
        ListarP();
        GenerarSerie();
        Fecha();
    }
    

    //=========METODOS DE  LOS COLABORADORES===========================/
    void LimpiarTablaV() {
        for (int i = 0; i < modeloV.getRowCount(); i++) {
            modeloV.removeRow(i);
            i = i - 1;
        }
    }

    void ListarV() {
        List<EntidadUsuario> lista = vdao.Read();
        modeloV = (DefaultTableModel) tblColaboradores.getModel();
        Object[] ob = new Object[6];
        for (int i = 0; i < lista.size(); i++) {
            ob[0] = lista.get(i).getIdUsuario();
            ob[1] = lista.get(i).getDNI();
            ob[2] = lista.get(i).getNombre();
            ob[3] = lista.get(i).getTelefono();
            ob[4] = lista.get(i).getStatus();
            ob[5] = lista.get(i).getUsuario();

            modeloV.addRow(ob);
        }
        tblColaboradores.setModel(modeloV);
    }

    void NuevoV() {
        txtCedulaV.setText("");
        txtNombreV.setText("");
        txtTelefonoV.setText("");
        cbxEstadoV.setSelectedIndex(0);
        txtUsuarioV.setText("");
        txtCedulaV.requestFocus();
    }

    void crearV() {
        int resp = JOptionPane.showConfirmDialog(this, "Deseas crear un nuevo registor de Empleado? ?");
        if (resp == 0) {
            String cedula = txtCedulaV.getText();
            String nombre = txtNombreV.getText();
            String telefono = txtTelefonoV.getText();
            String estado = cbxEstadoV.getSelectedItem().toString();
            String usuario = txtUsuarioV.getText();
            Object[] obj = new Object[5];
            obj[0] = cedula;
            obj[1] = nombre;
            obj[2] = telefono;
            obj[3] = estado;
            obj[4] = usuario;
            vdao.Create(obj);
        } else {
            JOptionPane.showMessageDialog(this, "Operacion Cancelada");
        }
    }

    void ActualizarV() {
        int fila = tblColaboradores.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Debes seleccionar un registro de la tabla");
        } else {
            int resp = JOptionPane.showConfirmDialog(this, "Deseas Actualizar el registor del Empleado \n" + txtNombreV.getText() + "?");

            if (resp == 0) {
                String id_vend = String.valueOf(idV);
                String cedula = txtCedulaV.getText();
                String nombre = txtNombreV.getText();
                String direccion = txtTelefonoV.getText();
                String estado = cbxEstadoV.getSelectedItem().toString();
                String usuario = txtUsuarioV.getText();

                Object[] ob = new Object[6];
                ob[0] = cedula;
                ob[1] = nombre;
                ob[2] = direccion;
                ob[3] = estado;
                ob[4] = usuario;
                ob[5] = id_vend;
                vdao.Update(ob);
            } else {
                JOptionPane.showMessageDialog(this, "Operacion Cancelada");
            }
        }
    }

    void EliminarV() {
        int fila = tblColaboradores.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Debes seleccionar un registro de la tabla");
        } else {
            int resp = JOptionPane.showConfirmDialog(this, "Desea Eliminar el Registro del Empleado \n" + txtNombreV.getText() + "?");
            if (resp == 0) {
                vdao.Delete(idV);
                JOptionPane.showMessageDialog(this, "Registro Elimidado Correctamente");
            } else {
                JOptionPane.showMessageDialog(this, "Operacion Cancelada");
            }
        }
    }

    //=================================================================/
    //=========METODOS DE  LOS CLIENTES================================/
    void LimpiarTablaC() {
        for (int i = 0; i < modeloC.getRowCount(); i++) {
            modeloC.removeRow(i);
            i = i - 1;
        }
    }

    void ListarC() {
        List<EntidadCliente> lista = cdao.Read();
        modeloC = (DefaultTableModel) tblCliente.getModel();
        Object[] ob = new Object[5];
        for (int i = 0; i < lista.size(); i++) {
            ob[0] = lista.get(i).getId_Cliente();
            ob[1] = lista.get(i).getCedula();
            ob[2] = lista.get(i).getNombre();
            ob[3] = lista.get(i).getDireccion();
            ob[4] = lista.get(i).getEstado();
            modeloC.addRow(ob);
        }
        tblCliente.setModel(modeloC);
    }

    void NuevoC() {
        txtCedulaC.setText("");
        txtNombreC.setText("");
        txtDireccionC.setText("");
        cbxEstadoC.setSelectedIndex(0);
        txtCedulaC.requestFocus();
    }

    void CrearC() {
        int resp = JOptionPane.showConfirmDialog(this, "Deseas crear un nuevo registro de  Cliente ?");
        if (resp == 0) {
            String cedula = txtCedulaC.getText();
            String nombre = txtNombreC.getText();
            String direccion = txtDireccionC.getText();
            String estado = cbxEstadoC.getSelectedItem().toString();
            Object[] ob = new Object[4];
            ob[0] = cedula;
            ob[1] = nombre;
            ob[2] = direccion;
            ob[3] = estado;
            cdao.Create(ob);
        } else {
            JOptionPane.showMessageDialog(this, "Operacion Cancelada");
        }
    }

    void ActualizarC() {

        int fila = tblCliente.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Debes seleccionar un registro de la tabla");
        } else {
            int resp = JOptionPane.showConfirmDialog(this, "Deseas Actualizar el registor del Cliente \n" + txtNombreC.getText() + "?");
            if (resp == 0) {
                String id_clien = String.valueOf(idC);
                String cedula = txtCedulaC.getText();
                String nombre = txtNombreC.getText();
                String direccion = txtDireccionC.getText();
                String estado = cbxEstadoC.getSelectedItem().toString();

                Object[] ob = new Object[5];
                ob[0] = cedula;
                ob[1] = nombre;
                ob[2] = direccion;
                ob[3] = estado;
                ob[4] = id_clien;
                cdao.Update(ob);
                JOptionPane.showMessageDialog(this, "Registro Actualizado Correctamente");
            } else {
                JOptionPane.showMessageDialog(this, "Operacion Cancelada");
            }
        }
    }

    void EliminarC() {
        int fila = tblCliente.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Debes seleccionar un registro de la tabla");
        } else {
            int resp = JOptionPane.showConfirmDialog(this, "Desea Eliminar el Registro del Cliente \n" + txtNombreC.getText() + "?");
            if (resp == 0) {
                cdao.Delete(idC);
                JOptionPane.showMessageDialog(this, "Registro Elimidado Correctamente");
            } else {
                JOptionPane.showMessageDialog(this, "Operacion Cancelada");
            }
        }
    }

    //=================================================================/
    //=========METODOS DE  LOS PRODUCTOS================================/
    void LimpiarTablaP() {
        for (int i = 0; i < modeloP.getRowCount(); i++) {
            modeloP.removeRow(i);
            i = i - 1;
        }
    }

    void ListarP() {
        List<EntidadProducto> lista = pdao.Read();
        modeloP = (DefaultTableModel) tblProductos.getModel();
        Object[] ob = new Object[5];
        for (int i = 0; i < lista.size(); i++) {
            ob[0] = lista.get(i).getIdProducto();
            ob[1] = lista.get(i).getNombre();
            ob[2] = lista.get(i).getPrecio();
            ob[3] = lista.get(i).getStock();
            ob[4] = lista.get(i).getEstado();
            modeloP.addRow(ob);
        }
        tblProductos.setModel(modeloP);
    }

    void NuevoP() {
        txtNombreP.setText("");
        txtPrecioP.setText("");
        txtStockP.setText("");
        cbxEstadoP.setSelectedIndex(0);
        txtNombreP.requestFocus();
    }

    void CrearP() {
        int resp = JOptionPane.showConfirmDialog(this, "Deseas crear un nuevo registro de  Producto ?");
        if (resp == 0) {
            String nombre = txtNombreP.getText();
            String precio = txtPrecioP.getText();
            String stock = txtStockP.getText();
            String estado = cbxEstadoP.getSelectedItem().toString();
            Object[] ob = new Object[4];
            ob[0] = nombre;
            ob[1] = precio;
            ob[2] = stock;
            ob[3] = estado;
            pdao.Create(ob);
        } else {
            JOptionPane.showMessageDialog(this, "Operacion Cancelada");
        }
    }

    void ActualizarP() {
        int fila = tblProductos.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Debes seleccionar un registro de la tabla");
        } else {
            int resp = JOptionPane.showConfirmDialog(this, "Deseas Actualizar el registor del Producto ?");
            if (resp == 0) {
                String id_prod = String.valueOf(idP);
                String nombre = txtNombreP.getText();
                String precio = txtPrecioP.getText();
                String stock = txtStockP.getText();
                String estado = cbxEstadoP.getSelectedItem().toString();

                Object[] ob = new Object[5];
                ob[0] = nombre;
                ob[1] = precio;
                ob[2] = stock;
                ob[3] = estado;
                ob[4] = id_prod;
                pdao.Update(ob);
                JOptionPane.showMessageDialog(this, "Registro Actualizado Correctamente");
            } else {
                JOptionPane.showMessageDialog(this, "Operacion Cancelada");
            }
        }
    }

    void EliminarP() {
        int fila = tblProductos.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Debes seleccionar un registro de la tabla");
        } else {
            int resp = JOptionPane.showConfirmDialog(this, "Desea Eliminar el Registro del Producto \n" + txtNombreP.getText() + "?");
            if (resp == 0) {
                pdao.Delete(idP);
                JOptionPane.showMessageDialog(this, "Registro Elimidado Correctamente");
            } else {
                JOptionPane.showMessageDialog(this, "Operacion Cancelada");
            }
        }
    }
    //=================================================================/
    //=========METODOS DE  LAS VENTAS================================/

    void BuscarCliente() {
        String cedula = txtCodCliente.getText();
        if (txtCodCliente.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Debe Ingresar el Codigo del Cliente");
        } else {
            ec = cdao.BuscarCliente(cedula);
            if (ec.getCedula() != null) {
                txtCliente.setText(ec.getNombre());
                txtCodProducto.requestFocus();
            } else {
                int resp = JOptionPane.showConfirmDialog(this, "Cliente No Registrado.\nDesea Registrarlo?");
                if (resp == 0) {
                    NuevoClienteForm ncf = new NuevoClienteForm();
                    ncf.setVisible(true);
                }
            }
        }
    }

    void BuscarProducto() {
        if (txtCodProducto.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Debe Ingresar el Codigo del Producto");
        } else {
            id_Porducto = Integer.parseInt(txtCodProducto.getText());
            ep = pdao.searchProduct(id_Porducto);
            if (ep.getIdProducto() != 0) {
                txtProducto.setText(ep.getNombre());
                txtPrecio.setText("$" + ep.getPrecio());
                txtStock.setText(String.valueOf(ep.getStock()));
            } else {
                JOptionPane.showMessageDialog(this, "Producto No Registrado");
                txtProducto.requestFocus();
            }
        }
    }

    void CalcularTotal() {
        tPagar = 0;
        for (int i = 0; i < tblDVentas.getRowCount(); i++) {
            cantidad = Integer.parseInt(tblDVentas.getValueAt(i, 3).toString());
            precio = Float.parseFloat(tblDVentas.getValueAt(i, 4).toString());
            tPagar = tPagar + (cantidad * precio);
        }
        txtTotal.setText("$" + tPagar);

    }

    void Agregar() {
        float total;
        modeloDV = (DefaultTableModel) tblDVentas.getModel();
        nro = nro + 1;
        int Id_Producto = ep.getIdProducto();
        String nombre = ep.getNombre();
        cantidad = Integer.parseInt(spnCantidad.getValue().toString());
        precio = ep.getPrecio();
        total = cantidad * precio;

        ArrayList lista = new ArrayList();
        int stock = Integer.parseInt(txtStock.getText());
        if (stock > 0) {
            if (stock >= cantidad) {
                lista.add(nro);
                lista.add(Id_Producto);
                lista.add(nombre);
                lista.add(cantidad);
                lista.add(precio);
                lista.add(total);

                Object[] obj = new Object[6];
                obj[0] = lista.get(0);
                obj[1] = lista.get(1);
                obj[2] = lista.get(2);
                obj[3] = lista.get(3);
                obj[4] = lista.get(4);
                obj[5] = lista.get(5);
                modeloDV.addRow(obj);
                tblDVentas.setModel(modeloDV);

                CalcularTotal();
            } else {
                JOptionPane.showMessageDialog(this, "No hay Stock suficiente para la venta");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Stock de Producto Agotado");
        }
    }

    void GenerarSerie() {
        String serie = vendao.NumeroSerie();
        if (serie == null) {
            txtNroSerie.setText("0001");
        } else {
            int incremento = Integer.parseInt(serie);
            incremento = incremento + 1;
            txtNroSerie.setText("000" + incremento);
        }
    }

    void Fecha() {
        Calendar calendario = new GregorianCalendar();
        int Y = calendario.get(Calendar.YEAR);
        int M = calendario.get(Calendar.MONTH);
        M = M + 1;
        int D = calendario.get(Calendar.DAY_OF_MONTH);

        TxtFecha.setText("" + Y + "-" + M + "-" + D);
    }

    void GuardarVenta() {

        int cliente = ec.getId_Cliente();
        //Estamos mandando la cedula en vez del id del cliente y por eso arroja error
        int vendedor = Integer.parseInt(txtColaborador.getText());
        String nroSerie = txtNroSerie.getText();
        String fecha = TxtFecha.getText();
        float monto = tPagar;
        String estado = "Efectiva";

        /*EntidadVenta evt = new EntidadVenta();;
        evt.setId_Cliente(cliente);
        evt.setId_Vendedor(vendedor);
        evt.setNro_Venta(nroSerie);
        evt.setFecha(fecha);
        evt.setMonto(monto);
        evt.setEstado(estado);*/
        CobroForm cb = new CobroForm();
        cb.CapVenta(cliente, vendedor, nroSerie, fecha, monto, estado);
    }

    void GuardarDetalleVenta() {
        CobroForm cb = new CobroForm();
        int idVentas = Integer.parseInt(vendao.IdVentas());
        String nroSerie = txtNroSerie.getText();
        for (int i = 0; i < tblDVentas.getRowCount(); i++) {
            int idProducto = Integer.parseInt(tblDVentas.getValueAt(i, 1).toString());
            int cantidad = Integer.parseInt(tblDVentas.getValueAt(i, 3).toString());
            float precio = Float.parseFloat(tblDVentas.getValueAt(i, 4).toString());
            String estado = "Efectiva";

            /*edv.setId_Venta(idVentas);
            edv.setNroSerie(NroSerie);
            edv.setId_Producto(idProducto);
            edv.setCantidad(cantidad);
            edv.setPrecioVenta(precio);
            edv.setEstado(estado)*/
            cb.Cap_Guad_DetVenta(idVentas, nroSerie, idProducto, cantidad, precio, estado);
        }
    }

    void asignarvendedor(String idvendedor, String role, String idUsuario) {
        txtColaborador.setText(String.valueOf(idUsuario));
        id_Usuario = idUsuario;
        txtRole.setText(role);
        if (role.equals("Vendedor")) {
            btnCrearV.setVisible(false);
            btnCrearC.setVisible(false);
            btnCrearP.setVisible(false);

            btnActualizarV.setVisible(false);
            btnActualizarC.setVisible(false);
            btnActualizarP.setVisible(false);

            btnEliminarC.setVisible(false);
            btnEliminarP.setVisible(false);
            btnEliminarV.setVisible(false);

            btnNuevoC.setVisible(false);
            btnNuevoP.setVisible(false);
            btnNuevoV.setVisible(false);

        } else {
        }
    }

    void ActualizarStock() {
        for (int i = 0; i < modeloDV.getRowCount(); i++) {
            id_Porducto = Integer.parseInt(tblDVentas.getValueAt(i, 1).toString());
            cantidad = Integer.parseInt(tblDVentas.getValueAt(i, 3).toString());
            EntidadProducto ep = new EntidadProducto();
            ep = pdao.searchProduct(id_Porducto);
            int stock = ep.getStock() - cantidad;
            pdao.StockUpdate(stock, id_Porducto);
        }
    }

    void LimpiarVenta() {
        modeloDV = (DefaultTableModel) tblDVentas.getModel();
        for (int i = 0; i < modeloDV.getRowCount(); i++) {
            modeloDV.removeRow(i);
            i = i - 1;
        }
        txtCodCliente.setText("");
        txtCodProducto.setText("");
        txtPrecio.setText("");
        spnCantidad.setValue(0);
        txtCliente.setText("");
        txtProducto.setText("");
        txtStock.setText("");
        txtNroSerie.setText("");
        GenerarSerie();
        txtCodCliente.requestFocus();
    }

    void LogOut() {
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        topPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnClose = new javax.swing.JButton();
        btnMinimize = new javax.swing.JButton();
        txtRole = new javax.swing.JTextField();
        btnLogOut = new javax.swing.JButton();
        PanelPrincipal = new javax.swing.JTabbedPane();
        PanelVentas = new javax.swing.JPanel();
        lblCodCliente = new javax.swing.JLabel();
        txtCodCliente = new javax.swing.JTextField();
        lblConProducto = new javax.swing.JLabel();
        txtCodProducto = new javax.swing.JTextField();
        lblPrecio = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        lblCantidad = new javax.swing.JLabel();
        spnCantidad = new javax.swing.JSpinner();
        lblCliente = new javax.swing.JLabel();
        txtCliente = new javax.swing.JTextField();
        lblProducto = new javax.swing.JLabel();
        txtProducto = new javax.swing.JTextField();
        lblStock = new javax.swing.JLabel();
        txtStock = new javax.swing.JTextField();
        TxtFecha = new javax.swing.JTextField();
        lblColaborador = new javax.swing.JLabel();
        txtColaborador = new javax.swing.JTextField();
        btnBuscarclient = new javax.swing.JButton();
        btnBuscarProduc = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        lblNroSerie = new javax.swing.JLabel();
        txtNroSerie = new javax.swing.JTextField();
        lablVentastext = new javax.swing.JLabel();
        bgVentas = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblDVentas = new javax.swing.JTable();
        BottomPanel = new javax.swing.JPanel();
        btnGenVenta = new javax.swing.JButton();
        btnCancelar1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        paneColaboradores = new javax.swing.JPanel();
        lblCedulaV = new javax.swing.JLabel();
        txtCedulaV = new javax.swing.JTextField();
        lblNombreV = new javax.swing.JLabel();
        txtNombreV = new javax.swing.JTextField();
        lblTelefonoV = new javax.swing.JLabel();
        txtTelefonoV = new javax.swing.JTextField();
        lblEstadoV = new javax.swing.JLabel();
        lblUsuarioV = new javax.swing.JLabel();
        txtUsuarioV = new javax.swing.JTextField();
        btnCrearV = new javax.swing.JButton();
        btnActualizarV = new javax.swing.JButton();
        btnEliminarV = new javax.swing.JButton();
        btnNuevoV = new javax.swing.JButton();
        cbxEstadoV = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        bgColaboradores = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblColaboradores = new javax.swing.JTable();
        PanleClietntes = new javax.swing.JPanel();
        ClientesText = new javax.swing.JLabel();
        lblCedulaC = new javax.swing.JLabel();
        txtCedulaC = new javax.swing.JTextField();
        lblNombreC = new javax.swing.JLabel();
        txtNombreC = new javax.swing.JTextField();
        lbDireccionC = new javax.swing.JLabel();
        txtDireccionC = new javax.swing.JTextField();
        lblEstadoC = new javax.swing.JLabel();
        cbxEstadoC = new javax.swing.JComboBox<>();
        btnCrearC = new javax.swing.JButton();
        btnActualizarC = new javax.swing.JButton();
        btnEliminarC = new javax.swing.JButton();
        btnNuevoC = new javax.swing.JButton();
        bgClientes = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCliente = new javax.swing.JTable();
        PanelProductos = new javax.swing.JPanel();
        lblNombreP = new javax.swing.JLabel();
        txtNombreP = new javax.swing.JTextField();
        lblPrecioP = new javax.swing.JLabel();
        txtPrecioP = new javax.swing.JTextField();
        lblStockP = new javax.swing.JLabel();
        txtStockP = new javax.swing.JTextField();
        lblEstadoP = new javax.swing.JLabel();
        cbxEstadoP = new javax.swing.JComboBox<>();
        btnNuevoP = new javax.swing.JButton();
        btnEliminarP = new javax.swing.JButton();
        btnActualizarP = new javax.swing.JButton();
        btnCrearP = new javax.swing.JButton();
        ProductosText = new javax.swing.JLabel();
        bgProductos = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        topPanel.setBackground(new java.awt.Color(51, 51, 51));
        topPanel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                topPanelMouseDragged(evt);
            }
        });
        topPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                topPanelMousePressed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/StockBoxText.png"))); // NOI18N

        btnClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/002-cancel.png"))); // NOI18N
        btnClose.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        btnMinimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/003-minimize.png"))); // NOI18N
        btnMinimize.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMinimize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMinimizeActionPerformed(evt);
            }
        });

        txtRole.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtRole.setForeground(new java.awt.Color(255, 255, 255));
        txtRole.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtRole.setBorder(null);
        txtRole.setEnabled(false);

        btnLogOut.setBackground(new java.awt.Color(0, 153, 204));
        btnLogOut.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnLogOut.setForeground(new java.awt.Color(255, 255, 255));
        btnLogOut.setText("Log Out");
        btnLogOut.setBorder(null);
        btnLogOut.setBorderPainted(false);
        btnLogOut.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogOutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout topPanelLayout = new javax.swing.GroupLayout(topPanel);
        topPanel.setLayout(topPanelLayout);
        topPanelLayout.setHorizontalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, topPanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel2)
                .addGap(165, 165, 165)
                .addComponent(txtRole, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 202, Short.MAX_VALUE)
                .addComponent(btnLogOut, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(btnMinimize, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );
        topPanelLayout.setVerticalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnLogOut, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnClose)
                        .addComponent(btnMinimize)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(topPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 50));

        PanelPrincipal.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        PanelPrincipal.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        PanelVentas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCodCliente.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblCodCliente.setForeground(new java.awt.Color(255, 255, 255));
        lblCodCliente.setText("Cod Cliente");
        PanelVentas.add(lblCodCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        txtCodCliente.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCodCliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PanelVentas.add(txtCodCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 100, 30));

        lblConProducto.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblConProducto.setForeground(new java.awt.Color(255, 255, 255));
        lblConProducto.setText("Cod Producto");
        PanelVentas.add(lblConProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        txtCodProducto.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCodProducto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PanelVentas.add(txtCodProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, 100, 30));

        lblPrecio.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblPrecio.setForeground(new java.awt.Color(255, 255, 255));
        lblPrecio.setText("Precio");
        PanelVentas.add(lblPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        txtPrecio.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtPrecio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPrecio.setEnabled(false);
        PanelVentas.add(txtPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 100, 30));

        lblCantidad.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblCantidad.setForeground(new java.awt.Color(255, 255, 255));
        lblCantidad.setText("Cantidad");
        PanelVentas.add(lblCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        spnCantidad.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        PanelVentas.add(spnCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 160, 100, 30));

        lblCliente.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblCliente.setForeground(new java.awt.Color(255, 255, 255));
        lblCliente.setText("Cliente");
        PanelVentas.add(lblCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, -1, -1));

        txtCliente.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCliente.setEnabled(false);
        PanelVentas.add(txtCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 10, 160, 30));

        lblProducto.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblProducto.setForeground(new java.awt.Color(255, 255, 255));
        lblProducto.setText("Producto");
        PanelVentas.add(lblProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 60, -1, -1));

        txtProducto.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtProducto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtProducto.setEnabled(false);
        PanelVentas.add(txtProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 60, 480, 30));

        lblStock.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblStock.setForeground(new java.awt.Color(255, 255, 255));
        lblStock.setText("Stock");
        PanelVentas.add(lblStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 10, -1, -1));

        txtStock.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtStock.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtStock.setEnabled(false);
        PanelVentas.add(txtStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 10, 100, 30));

        TxtFecha.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        TxtFecha.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TxtFecha.setEnabled(false);
        PanelVentas.add(TxtFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 10, 110, 30));

        lblColaborador.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblColaborador.setForeground(new java.awt.Color(255, 255, 255));
        lblColaborador.setText("Vendedor");
        PanelVentas.add(lblColaborador, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 160, -1, -1));

        txtColaborador.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtColaborador.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtColaborador.setEnabled(false);
        PanelVentas.add(txtColaborador, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 160, 160, 30));

        btnBuscarclient.setBackground(new java.awt.Color(51, 153, 0));
        btnBuscarclient.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        btnBuscarclient.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarclient.setText("Buscar");
        btnBuscarclient.setBorder(null);
        btnBuscarclient.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscarclient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarclientActionPerformed(evt);
            }
        });
        PanelVentas.add(btnBuscarclient, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, 80, 30));

        btnBuscarProduc.setBackground(new java.awt.Color(51, 153, 0));
        btnBuscarProduc.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        btnBuscarProduc.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarProduc.setText("Buscar");
        btnBuscarProduc.setBorder(null);
        btnBuscarProduc.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscarProduc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarProducActionPerformed(evt);
            }
        });
        PanelVentas.add(btnBuscarProduc, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 60, 80, 30));

        btnAgregar.setBackground(new java.awt.Color(212, 172, 13));
        btnAgregar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnAgregar.setForeground(new java.awt.Color(51, 51, 51));
        btnAgregar.setText("Agregar");
        btnAgregar.setBorder(null);
        btnAgregar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        PanelVentas.add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 110, 80, 30));

        lblNroSerie.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblNroSerie.setForeground(new java.awt.Color(255, 255, 255));
        lblNroSerie.setText("N°  Serie");
        PanelVentas.add(lblNroSerie, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 110, 70, 20));

        txtNroSerie.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtNroSerie.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNroSerie.setEnabled(false);
        PanelVentas.add(txtNroSerie, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 110, 160, 30));

        lablVentastext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/VentasText.png"))); // NOI18N
        PanelVentas.add(lablVentastext, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 150, 170, -1));

        bgVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Clientesbg.png"))); // NOI18N
        PanelVentas.add(bgVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        tblDVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Numero", "Codigo", "Producto", "Cantidad", "Vlr Unidad", "Total"
            }
        ));
        jScrollPane4.setViewportView(tblDVentas);

        PanelVentas.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 1000, 230));

        BottomPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        BottomPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnGenVenta.setBackground(new java.awt.Color(51, 153, 0));
        btnGenVenta.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        btnGenVenta.setForeground(new java.awt.Color(255, 255, 255));
        btnGenVenta.setText("Generar Venta");
        btnGenVenta.setBorder(null);
        btnGenVenta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGenVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenVentaActionPerformed(evt);
            }
        });
        BottomPanel.add(btnGenVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 10, 170, 40));

        btnCancelar1.setBackground(new java.awt.Color(255, 51, 51));
        btnCancelar1.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        btnCancelar1.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar1.setText("Refrescar");
        btnCancelar1.setBorder(null);
        btnCancelar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancelar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelar1ActionPerformed(evt);
            }
        });
        BottomPanel.add(btnCancelar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 10, 130, 40));

        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        jLabel3.setText("Total");
        BottomPanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 20, 70, -1));

        txtTotal.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtTotal.setToolTipText("");
        txtTotal.setBorder(null);
        txtTotal.setEnabled(false);
        BottomPanel.add(txtTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 20, 210, 30));

        PanelVentas.add(BottomPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 440, 1010, 70));

        PanelPrincipal.addTab("    Generar Ventas    ", PanelVentas);

        paneColaboradores.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCedulaV.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblCedulaV.setForeground(new java.awt.Color(255, 255, 255));
        lblCedulaV.setText("Identificacion");
        paneColaboradores.add(lblCedulaV, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        txtCedulaV.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCedulaV.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        paneColaboradores.add(txtCedulaV, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 160, 30));

        lblNombreV.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblNombreV.setForeground(new java.awt.Color(255, 255, 255));
        lblNombreV.setText("Nombre");
        paneColaboradores.add(lblNombreV, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        txtNombreV.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtNombreV.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        paneColaboradores.add(txtNombreV, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, 160, 30));

        lblTelefonoV.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblTelefonoV.setForeground(new java.awt.Color(255, 255, 255));
        lblTelefonoV.setText("Telefono");
        paneColaboradores.add(lblTelefonoV, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        txtTelefonoV.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtTelefonoV.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        paneColaboradores.add(txtTelefonoV, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 160, 30));

        lblEstadoV.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblEstadoV.setForeground(new java.awt.Color(255, 255, 255));
        lblEstadoV.setText("Estado");
        paneColaboradores.add(lblEstadoV, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        lblUsuarioV.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblUsuarioV.setForeground(new java.awt.Color(255, 255, 255));
        lblUsuarioV.setText("Usuario");
        paneColaboradores.add(lblUsuarioV, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 10, -1, -1));

        txtUsuarioV.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtUsuarioV.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        paneColaboradores.add(txtUsuarioV, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 10, 170, 30));

        btnCrearV.setBackground(new java.awt.Color(51, 153, 0));
        btnCrearV.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnCrearV.setForeground(new java.awt.Color(255, 255, 255));
        btnCrearV.setText("Crear");
        btnCrearV.setBorder(null);
        btnCrearV.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCrearV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearVActionPerformed(evt);
            }
        });
        paneColaboradores.add(btnCrearV, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 10, 90, 30));

        btnActualizarV.setBackground(new java.awt.Color(212, 172, 13));
        btnActualizarV.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnActualizarV.setForeground(new java.awt.Color(51, 51, 51));
        btnActualizarV.setText("Actualizar");
        btnActualizarV.setBorder(null);
        btnActualizarV.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnActualizarV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarVActionPerformed(evt);
            }
        });
        paneColaboradores.add(btnActualizarV, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 10, 100, 30));

        btnEliminarV.setBackground(new java.awt.Color(255, 51, 51));
        btnEliminarV.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnEliminarV.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminarV.setText("Eliminar");
        btnEliminarV.setBorder(null);
        btnEliminarV.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminarV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarVActionPerformed(evt);
            }
        });
        paneColaboradores.add(btnEliminarV, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 10, 90, 30));

        btnNuevoV.setBackground(new java.awt.Color(102, 102, 102));
        btnNuevoV.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnNuevoV.setForeground(new java.awt.Color(255, 255, 255));
        btnNuevoV.setText("Nuevo");
        btnNuevoV.setBorder(null);
        btnNuevoV.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevoV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoVActionPerformed(evt);
            }
        });
        paneColaboradores.add(btnNuevoV, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 70, 90, 30));

        cbxEstadoV.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Activo", "Inactivo" }));
        paneColaboradores.add(cbxEstadoV, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 160, 160, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ColaboradoresText.png"))); // NOI18N
        paneColaboradores.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 150, 300, -1));

        bgColaboradores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Clientesbg.png"))); // NOI18N
        paneColaboradores.add(bgColaboradores, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, -1));

        tblColaboradores.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblColaboradores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id Colaborador", "Identificacion", "Nombre", "Telefono", "Estado", "Usuario"
            }
        ));
        tblColaboradores.setOpaque(false);
        tblColaboradores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblColaboradoresMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblColaboradores);

        paneColaboradores.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 1000, 320));

        PanelPrincipal.addTab("      Colaboradores      ", paneColaboradores);

        PanleClietntes.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ClientesText.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ClientesText.png"))); // NOI18N
        PanleClietntes.add(ClientesText, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 150, 180, -1));

        lblCedulaC.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblCedulaC.setForeground(new java.awt.Color(255, 255, 255));
        lblCedulaC.setText("Identificacion");
        PanleClietntes.add(lblCedulaC, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        txtCedulaC.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCedulaC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PanleClietntes.add(txtCedulaC, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 160, 30));

        lblNombreC.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblNombreC.setForeground(new java.awt.Color(255, 255, 255));
        lblNombreC.setText("Nombre");
        PanleClietntes.add(lblNombreC, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        txtNombreC.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtNombreC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PanleClietntes.add(txtNombreC, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, 160, 30));

        lbDireccionC.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccionC.setForeground(new java.awt.Color(255, 255, 255));
        lbDireccionC.setText("Direccion");
        PanleClietntes.add(lbDireccionC, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        txtDireccionC.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtDireccionC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PanleClietntes.add(txtDireccionC, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 160, 30));

        lblEstadoC.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblEstadoC.setForeground(new java.awt.Color(255, 255, 255));
        lblEstadoC.setText("Estado");
        PanleClietntes.add(lblEstadoC, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        cbxEstadoC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Activo", "Inactivo" }));
        PanleClietntes.add(cbxEstadoC, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 160, 160, 30));

        btnCrearC.setBackground(new java.awt.Color(51, 153, 0));
        btnCrearC.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnCrearC.setForeground(new java.awt.Color(255, 255, 255));
        btnCrearC.setText("Crear");
        btnCrearC.setBorder(null);
        btnCrearC.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCrearC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearCActionPerformed(evt);
            }
        });
        PanleClietntes.add(btnCrearC, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 10, 90, 30));

        btnActualizarC.setBackground(new java.awt.Color(212, 172, 13));
        btnActualizarC.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnActualizarC.setForeground(new java.awt.Color(51, 51, 51));
        btnActualizarC.setText("Actualizar");
        btnActualizarC.setBorder(null);
        btnActualizarC.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnActualizarC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarCActionPerformed(evt);
            }
        });
        PanleClietntes.add(btnActualizarC, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 10, 100, 30));

        btnEliminarC.setBackground(new java.awt.Color(255, 51, 51));
        btnEliminarC.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnEliminarC.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminarC.setText("Eliminar");
        btnEliminarC.setBorder(null);
        btnEliminarC.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminarC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarCActionPerformed(evt);
            }
        });
        PanleClietntes.add(btnEliminarC, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 10, 90, 30));

        btnNuevoC.setBackground(new java.awt.Color(102, 102, 102));
        btnNuevoC.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnNuevoC.setForeground(new java.awt.Color(255, 255, 255));
        btnNuevoC.setText("Nuevo");
        btnNuevoC.setBorder(null);
        btnNuevoC.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevoC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoCActionPerformed(evt);
            }
        });
        PanleClietntes.add(btnNuevoC, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 70, 90, 30));

        bgClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Clientesbg.png"))); // NOI18N
        PanleClietntes.add(bgClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        tblCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id Cliente", "Identificacion", "Nombre", "Direccion", "Estado"
            }
        ));
        tblCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClienteMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblCliente);

        PanleClietntes.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 1000, 320));

        PanelPrincipal.addTab("    Clientes    ", PanleClietntes);

        PanelProductos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblNombreP.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblNombreP.setForeground(new java.awt.Color(255, 255, 255));
        lblNombreP.setText("Nombre");
        PanelProductos.add(lblNombreP, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        txtNombreP.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtNombreP.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PanelProductos.add(txtNombreP, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 440, 30));

        lblPrecioP.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblPrecioP.setForeground(new java.awt.Color(255, 255, 255));
        lblPrecioP.setText("Precio");
        PanelProductos.add(lblPrecioP, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        txtPrecioP.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtPrecioP.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PanelProductos.add(txtPrecioP, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, 160, 30));

        lblStockP.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblStockP.setForeground(new java.awt.Color(255, 255, 255));
        lblStockP.setText("Stock");
        PanelProductos.add(lblStockP, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        txtStockP.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtStockP.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PanelProductos.add(txtStockP, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 160, 30));

        lblEstadoP.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblEstadoP.setForeground(new java.awt.Color(255, 255, 255));
        lblEstadoP.setText("Estado");
        PanelProductos.add(lblEstadoP, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        cbxEstadoP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Existente", "Agotado" }));
        PanelProductos.add(cbxEstadoP, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 160, 160, 30));

        btnNuevoP.setBackground(new java.awt.Color(102, 102, 102));
        btnNuevoP.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnNuevoP.setForeground(new java.awt.Color(255, 255, 255));
        btnNuevoP.setText("Nuevo");
        btnNuevoP.setBorder(null);
        btnNuevoP.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevoP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoPActionPerformed(evt);
            }
        });
        PanelProductos.add(btnNuevoP, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 70, 90, 30));

        btnEliminarP.setBackground(new java.awt.Color(255, 51, 51));
        btnEliminarP.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnEliminarP.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminarP.setText("Eliminar");
        btnEliminarP.setBorder(null);
        btnEliminarP.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminarP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarPActionPerformed(evt);
            }
        });
        PanelProductos.add(btnEliminarP, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 10, 90, 30));

        btnActualizarP.setBackground(new java.awt.Color(212, 172, 13));
        btnActualizarP.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnActualizarP.setForeground(new java.awt.Color(51, 51, 51));
        btnActualizarP.setText("Actualizar");
        btnActualizarP.setBorder(null);
        btnActualizarP.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnActualizarP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarPActionPerformed(evt);
            }
        });
        PanelProductos.add(btnActualizarP, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 10, 100, 30));

        btnCrearP.setBackground(new java.awt.Color(51, 153, 0));
        btnCrearP.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnCrearP.setForeground(new java.awt.Color(255, 255, 255));
        btnCrearP.setText("Crear");
        btnCrearP.setBorder(null);
        btnCrearP.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCrearP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearPActionPerformed(evt);
            }
        });
        PanelProductos.add(btnCrearP, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 10, 90, 30));

        ProductosText.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ProductosText.png"))); // NOI18N
        PanelProductos.add(ProductosText, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 150, 240, -1));

        bgProductos.setBackground(new java.awt.Color(255, 51, 0));
        bgProductos.setForeground(new java.awt.Color(255, 255, 255));
        bgProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Clientesbg.png"))); // NOI18N
        bgProductos.setText("jLabel3");
        PanelProductos.add(bgProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 10, -1, -1));

        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id Producto", "Nombre", "Precio", "Stock", "Estado"
            }
        ));
        tblProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductosMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblProductos);

        PanelProductos.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 1000, 320));

        PanelPrincipal.addTab("     Stock de Productos    ", PanelProductos);

        getContentPane().add(PanelPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 1000, 550));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed

        int resp = JOptionPane.showConfirmDialog(this, "Desea cerrar la aplicacion?");
        if (resp == 0) {
            System.exit(0);
        }
    }//GEN-LAST:event_btnCloseActionPerformed

    private void topPanelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_topPanelMousePressed
        X = evt.getX();
        Y = evt.getY();
    }//GEN-LAST:event_topPanelMousePressed

    private void topPanelMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_topPanelMouseDragged
        Point point = MouseInfo.getPointerInfo().getLocation();
        setLocation(point.x - X, point.y - Y);
    }//GEN-LAST:event_topPanelMouseDragged

    private void btnMinimizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinimizeActionPerformed
        this.setExtendedState(1);
    }//GEN-LAST:event_btnMinimizeActionPerformed

    private void tblProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductosMouseClicked
        int fila = tblProductos.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Debe Seleccionar Una Fila");
        } else {
            idP = Integer.parseInt(tblProductos.getValueAt(fila, 0).toString());
            String nombre = tblProductos.getValueAt(fila, 1).toString();
            String precio = tblProductos.getValueAt(fila, 2).toString();
            String stock = tblProductos.getValueAt(fila, 3).toString();
            String estado = tblProductos.getValueAt(fila, 4).toString();
            txtNombreP.setText(nombre);
            txtPrecioP.setText(precio);
            txtStockP.setText(stock);
            cbxEstadoP.setSelectedItem(estado);
        }
    }//GEN-LAST:event_tblProductosMouseClicked

    private void btnCrearPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearPActionPerformed
        CrearP();
        LimpiarTablaP();
        ListarP();
        NuevoP();
    }//GEN-LAST:event_btnCrearPActionPerformed

    private void btnActualizarPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarPActionPerformed
        ActualizarP();
        LimpiarTablaP();
        ListarP();
        NuevoP();
    }//GEN-LAST:event_btnActualizarPActionPerformed

    private void btnEliminarPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarPActionPerformed
        EliminarP();
        LimpiarTablaP();
        ListarP();
        NuevoP();
    }//GEN-LAST:event_btnEliminarPActionPerformed

    private void btnNuevoPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoPActionPerformed
        NuevoP();
        LimpiarTablaP();
        ListarP();
    }//GEN-LAST:event_btnNuevoPActionPerformed

    private void tblClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClienteMouseClicked
        int fila = tblCliente.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Debe Seleccionar Una Fila");
        } else {
            idC = Integer.parseInt(tblCliente.getValueAt(fila, 0).toString());
            String cedula = tblCliente.getValueAt(fila, 1).toString();
            String nombre = tblCliente.getValueAt(fila, 2).toString();
            String direccion = tblCliente.getValueAt(fila, 3).toString();
            String estado = tblCliente.getValueAt(fila, 4).toString();
            txtCedulaC.setText(cedula);
            txtNombreC.setText(nombre);
            txtDireccionC.setText(direccion);
            cbxEstadoC.setSelectedItem(estado);
        }
    }//GEN-LAST:event_tblClienteMouseClicked

    private void btnNuevoCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoCActionPerformed
        NuevoC();
        LimpiarTablaC();
        ListarC();
    }//GEN-LAST:event_btnNuevoCActionPerformed

    private void btnEliminarCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarCActionPerformed
        EliminarC();
        LimpiarTablaC();
        ListarC();
        NuevoC();
    }//GEN-LAST:event_btnEliminarCActionPerformed

    private void btnActualizarCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarCActionPerformed
        ActualizarC();
        LimpiarTablaC();
        ListarC();
        NuevoC();
    }//GEN-LAST:event_btnActualizarCActionPerformed

    private void btnCrearCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearCActionPerformed
        CrearC();
        LimpiarTablaC();
        ListarC();
        NuevoC();
    }//GEN-LAST:event_btnCrearCActionPerformed

    private void tblColaboradoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblColaboradoresMouseClicked
        int fila = tblColaboradores.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Debe Seleccionar Una Fila");
        } else {
            idV = Integer.parseInt(tblColaboradores.getValueAt(fila, 0).toString());
            String cedula = tblColaboradores.getValueAt(fila, 1).toString();
            String nombre = tblColaboradores.getValueAt(fila, 2).toString();
            String telefono = tblColaboradores.getValueAt(fila, 3).toString();
            String estado = tblColaboradores.getValueAt(fila, 4).toString();
            String usuario = tblColaboradores.getValueAt(fila, 5).toString();
            txtCedulaV.setText(cedula);
            txtNombreV.setText(nombre);
            txtTelefonoV.setText(telefono);
            cbxEstadoV.setSelectedItem(estado);
            txtUsuarioV.setText(usuario);
        }
    }//GEN-LAST:event_tblColaboradoresMouseClicked

    private void btnNuevoVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoVActionPerformed
        NuevoV();
        LimpiarTablaV();
        ListarV();
    }//GEN-LAST:event_btnNuevoVActionPerformed

    private void btnEliminarVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarVActionPerformed
        EliminarV();
        LimpiarTablaV();
        ListarV();
        NuevoV();
    }//GEN-LAST:event_btnEliminarVActionPerformed

    private void btnActualizarVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarVActionPerformed
        ActualizarV();
        LimpiarTablaV();
        ListarV();
        NuevoV();
    }//GEN-LAST:event_btnActualizarVActionPerformed

    private void btnCrearVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearVActionPerformed
        crearV();
        LimpiarTablaV();
        ListarV();
        NuevoV();
    }//GEN-LAST:event_btnCrearVActionPerformed

    private void btnBuscarclientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarclientActionPerformed
        BuscarCliente();
    }//GEN-LAST:event_btnBuscarclientActionPerformed

    private void btnBuscarProducActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarProducActionPerformed
        BuscarProducto();
    }//GEN-LAST:event_btnBuscarProducActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        Agregar();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnGenVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenVentaActionPerformed
        if (txtTotal.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "El valor de la venta debe ser mayor a cero");
        } else {

            int resp = JOptionPane.showConfirmDialog(this, "Desea Generar la Venta?");
            if (resp == 0) {
                CobroForm cb = new CobroForm();
                cb.CapTotal(tPagar, txtNroSerie.getText());
                GuardarVenta();
                GuardarDetalleVenta();
                ActualizarStock();
                LimpiarTablaP();
                ListarP();
                LimpiarVenta();
                cb.setVisible(true);
                 
            } else {;
                JOptionPane.showMessageDialog(this, "Operacion Cancelada");
            }
        }
    }//GEN-LAST:event_btnGenVentaActionPerformed

    private void btnCancelar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelar1ActionPerformed

        int resp = JOptionPane.showConfirmDialog(this, "Desea limpiar los campos?,");
        if (resp == 0) {
            LimpiarVenta();
        } else {
            JOptionPane.showMessageDialog(this, "Operacion Cancelada");
        }
    }//GEN-LAST:event_btnCancelar1ActionPerformed

    private void btnLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogOutActionPerformed
        int resp = JOptionPane.showConfirmDialog(this, "Desea cerrar la session?");
        if (resp == 0) {
            lf.setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_btnLogOutActionPerformed

    public static void main(String args[]) {

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Application.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Application.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Application.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Application.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Application().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BottomPanel;
    private javax.swing.JLabel ClientesText;
    private javax.swing.JTabbedPane PanelPrincipal;
    private javax.swing.JPanel PanelProductos;
    private javax.swing.JPanel PanelVentas;
    private javax.swing.JPanel PanleClietntes;
    private javax.swing.JLabel ProductosText;
    private javax.swing.JTextField TxtFecha;
    private javax.swing.JLabel bgClientes;
    private javax.swing.JLabel bgColaboradores;
    private javax.swing.JLabel bgProductos;
    private javax.swing.JLabel bgVentas;
    private javax.swing.JButton btnActualizarC;
    private javax.swing.JButton btnActualizarP;
    private javax.swing.JButton btnActualizarV;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscarProduc;
    private javax.swing.JButton btnBuscarclient;
    private javax.swing.JButton btnCancelar1;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnCrearC;
    private javax.swing.JButton btnCrearP;
    private javax.swing.JButton btnCrearV;
    private javax.swing.JButton btnEliminarC;
    private javax.swing.JButton btnEliminarP;
    private javax.swing.JButton btnEliminarV;
    private javax.swing.JButton btnGenVenta;
    private javax.swing.JButton btnLogOut;
    private javax.swing.JButton btnMinimize;
    private javax.swing.JButton btnNuevoC;
    private javax.swing.JButton btnNuevoP;
    private javax.swing.JButton btnNuevoV;
    private javax.swing.JComboBox<String> cbxEstadoC;
    private javax.swing.JComboBox<String> cbxEstadoP;
    private javax.swing.JComboBox<String> cbxEstadoV;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lablVentastext;
    private javax.swing.JLabel lbDireccionC;
    private javax.swing.JLabel lblCantidad;
    private javax.swing.JLabel lblCedulaC;
    private javax.swing.JLabel lblCedulaV;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblCodCliente;
    private javax.swing.JLabel lblColaborador;
    private javax.swing.JLabel lblConProducto;
    private javax.swing.JLabel lblEstadoC;
    private javax.swing.JLabel lblEstadoP;
    private javax.swing.JLabel lblEstadoV;
    private javax.swing.JLabel lblNombreC;
    private javax.swing.JLabel lblNombreP;
    private javax.swing.JLabel lblNombreV;
    private javax.swing.JLabel lblNroSerie;
    private javax.swing.JLabel lblPrecio;
    private javax.swing.JLabel lblPrecioP;
    private javax.swing.JLabel lblProducto;
    private javax.swing.JLabel lblStock;
    private javax.swing.JLabel lblStockP;
    private javax.swing.JLabel lblTelefonoV;
    private javax.swing.JLabel lblUsuarioV;
    private javax.swing.JPanel paneColaboradores;
    private javax.swing.JSpinner spnCantidad;
    private javax.swing.JTable tblCliente;
    private javax.swing.JTable tblColaboradores;
    private javax.swing.JTable tblDVentas;
    private javax.swing.JTable tblProductos;
    private javax.swing.JPanel topPanel;
    private javax.swing.JTextField txtCedulaC;
    private javax.swing.JTextField txtCedulaV;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtCodCliente;
    private javax.swing.JTextField txtCodProducto;
    private javax.swing.JTextField txtColaborador;
    private javax.swing.JTextField txtDireccionC;
    private javax.swing.JTextField txtNombreC;
    private javax.swing.JTextField txtNombreP;
    private javax.swing.JTextField txtNombreV;
    private javax.swing.JTextField txtNroSerie;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtPrecioP;
    private javax.swing.JTextField txtProducto;
    private javax.swing.JTextField txtRole;
    private javax.swing.JTextField txtStock;
    private javax.swing.JTextField txtStockP;
    private javax.swing.JTextField txtTelefonoV;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtUsuarioV;
    // End of variables declaration//GEN-END:variables
}
