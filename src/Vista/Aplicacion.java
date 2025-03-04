package Vista;

import Controlador.ClienteDAO;
import Controlador.ProductoDAO;
import Controlador.UsuarioDAO;
import Controlador.GenerarVentaDAO;
import Controlador.VentasDAO;
import Modelos.EntidadCliente;
import Modelos.EntidadDetalleVenta;
import Modelos.EntidadGenerarVenta;
import Modelos.EntidadProducto;
import Vista.Mensaje;

import Modelos.EntidadVenta;
import java.awt.MouseInfo;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

public class Aplicacion extends javax.swing.JFrame {

    //==========Instancias de Clases y  Entidades=========//
    LogingForm loginForm = new LogingForm();

    UsuarioDAO usuarioDAO = new UsuarioDAO();
    DefaultTableModel tableModelUsuario = new DefaultTableModel();

    EntidadCliente entdClientes = new EntidadCliente();
    ClienteDAO clientesDAO = new ClienteDAO();
    DefaultTableModel tableModelClientes = new DefaultTableModel();

    EntidadProducto entdProducto = new EntidadProducto();
    ProductoDAO productoDAO = new ProductoDAO();
    DefaultTableModel tableModelProducto = new DefaultTableModel();

    EntidadGenerarVenta entGenVenta = new EntidadGenerarVenta();
    GenerarVentaDAO genVentaDAO = new GenerarVentaDAO();

    VentasDAO ventasDAO = new VentasDAO();
    DefaultTableModel tableModelVentas = new DefaultTableModel();

    DefaultTableModel tableModelDetalleVenta = new DefaultTableModel();

    Mensaje mensaje = new Mensaje();

    //========== Variables Panel Generar Venta ========
    private String idUsuario;
    private int indexProducto;
    private int idProducto;
    private String nombreProducto;
    private int cantidadProducto;
    private float precioProducto;
    private float totalProducto;

    private int stockProducto;
    private float totalVenta;

    //==========ID's -  Filas de las tablas=====//
    private int tableRowIdUsuario;
    private int tableRowIdClente;
    private int tableRowIdProducto;
    private int idVenta;

    //===========V ariables para poder arrastrar el Panel============//
    int XWindowsPosition;
    int YWindowsPosition;

    public Aplicacion() {
        initComponents();
        this.setLocationRelativeTo(null);

        btnClose.setBackground(new java.awt.Color(0, 0, 0, 0));
        btnMinimize.setBackground(new java.awt.Color(0, 0, 0, 0));
        txtRoleUsuario.setBackground(new java.awt.Color(0, 0, 0, 0));

        this.listUsuarios();
        this.listClientes();
        this.listProductos();
        this.listSales();

        getMaxSerialNumber();
        getCurrentDate();

        txtDNIClienteGenVenta.setText("456214578");
    }

    //=========METODOS PANEL DE COLABORADORES===================/
    private void cleanTableUsuarios() {
        for (int i = 0; i < tableModelUsuario.getRowCount(); i++) {
            tableModelUsuario.removeRow(i);
            i = i - 1;
        }
    }

    private void cleanFormUsuarios() {
        txtDNIUsuario.setText("");
        txtNombreUsuario.setText("");
        txtTelefonoUsuario.setText("");
        cbxEstadoUsuario.setSelectedIndex(0);
        txtusuarioUsuario.setText("");
        txtDNIUsuario.requestFocus();
    }

    private void listUsuarios() {

        tableModelUsuario = usuarioDAO.listUsuariosOnTable(tblUsuarios);
        tblUsuarios.setModel(tableModelUsuario);
    }

    private void createUsuario() {

        Map<String, String> formData = new HashMap<String, String>();

        formData.put("DNI", txtDNIUsuario.getText());
        formData.put("nombre", txtNombreUsuario.getText());
        formData.put("telefono", txtTelefonoUsuario.getText());
        formData.put("estado", cbxEstadoUsuario.getSelectedItem().toString());
        formData.put("usuario", txtusuarioUsuario.getText());

        usuarioDAO.createUsuario(formData);
    }

    private void updateUsuario() {

        int selectedRow = tblUsuarios.getSelectedRow();
        Map<String, String> formData = new HashMap<String, String>();

        formData.put("idUsuario", String.valueOf(tableRowIdUsuario));
        formData.put("DNI", txtDNIUsuario.getText());
        formData.put("nombre", txtNombreUsuario.getText());
        formData.put("telefono", txtTelefonoUsuario.getText());
        formData.put("estado", cbxEstadoUsuario.getSelectedItem().toString());
        formData.put("usuario", txtusuarioUsuario.getText());

        usuarioDAO.updateUsuario(selectedRow, formData);

    }

    private void deleteUsuario() {
        int selectedRow = tblUsuarios.getSelectedRow();

        usuarioDAO.deleteUsuario(selectedRow, tableRowIdUsuario);
    }

    //========= METODOS PANEL CLIENTES==========================/
    public void cleanTableClientes() {
        for (int i = 0; i < tableModelClientes.getRowCount(); i++) {
            tableModelClientes.removeRow(i);
            i = i - 1;
        }
    }

    public void cleanFormClientes() {
        txtDNICliente.setText("");
        txtNombreCliente.setText("");
        txtDireccionCliente.setText("");
        cbxEstadoCliente.setSelectedIndex(0);
        txtDNICliente.requestFocus();
    }

    public void listClientes() {

        tableModelClientes = clientesDAO.listClientesOnTable(tblClientes);
        tblClientes.setModel(tableModelClientes);
    }

    private void createCliente() {

        Map<String, String> formData = new HashMap<String, String>();

        formData.put("DNI", txtDNICliente.getText());
        formData.put("nombre", txtNombreCliente.getText());
        formData.put("direccion", txtDireccionCliente.getText());
        formData.put("estado", cbxEstadoCliente.getSelectedItem().toString());

        clientesDAO.createCliente(formData);
    }

    private void updateClente() {

        int selectedRow = tblClientes.getSelectedRow();
        Map<String, String> formData = new HashMap<String, String>();

        formData.put("idCliente", String.valueOf(tableRowIdClente));
        formData.put("DNI", txtDNICliente.getText());
        formData.put("nombre", txtNombreCliente.getText());
        formData.put("direccion", txtDireccionCliente.getText());
        formData.put("estado", cbxEstadoCliente.getSelectedItem().toString());

        clientesDAO.updateCliente(selectedRow, formData);
    }

    private void deleteCliente() {

        int selectedRow = tblClientes.getSelectedRow();
        clientesDAO.deleteCliente(selectedRow, tableRowIdClente);
    }

    //======= METODOS DE  LOS PRODUCTOS=========================/
    private void cleanTableProductos() {
        for (int i = 0; i < tableModelProducto.getRowCount(); i++) {
            tableModelProducto.removeRow(i);
            i = i - 1;
        }
    }

    private void cleanFormProductos() {
        txtNombreProducto.setText("");
        txtPrecioProducto.setText("");
        txtStockProducto.setText("");
        cbxEstadoProducto.setSelectedIndex(0);
        txtNombreProducto.requestFocus();
    }

    private void listProductos() {

        tableModelProducto = productoDAO.listProductosOnTable(tblProductos);
        tblProductos.setModel(tableModelProducto);
    }

    private void createProducto() {

        Map<String, String> formData = new HashMap<String, String>();

        formData.put("nombre", txtNombreProducto.getText());
        formData.put("precio", txtPrecioProducto.getText());
        formData.put("stock", txtStockProducto.getText());
        formData.put("estado", cbxEstadoProducto.getSelectedItem().toString());

        productoDAO.createProducto(formData);
    }

    private void updateProducto() {
        int selectedRow = tblProductos.getSelectedRow();
        Map<String, String> formData = new HashMap<String, String>();

        formData.put("idProducto", String.valueOf(tableRowIdProducto));
        formData.put("nombre", txtNombreProducto.getText());
        formData.put("precio", txtPrecioProducto.getText());
        formData.put("stock", txtStockProducto.getText());
        formData.put("estado", cbxEstadoProducto.getSelectedItem().toString());

        productoDAO.updateProducto(selectedRow, formData);

    }

    private void deleteProducto() {

        int selectedRow = tblProductos.getSelectedRow();
        productoDAO.deleteProducto(selectedRow, tableRowIdProducto);

    }

    //========= METODOS DE  LAS VENTAS==========================/
    private void getMaxSerialNumber() {
        String maxSerialNomber = genVentaDAO.getMaxSerialNumber();

        if (maxSerialNomber == null) {
            txtNroSerie.setText("1");

        } else {

            int newSerialNumber = Integer.parseInt(maxSerialNomber) + 1;
            txtNroSerie.setText("" + newSerialNumber);
        }
    }

    private void getCurrentDate() {
        Calendar calendario = new GregorianCalendar();

        int year = calendario.get(Calendar.YEAR);
        int month = calendario.get(Calendar.MONTH);

        month = month + 1;

        int day = calendario.get(Calendar.DAY_OF_MONTH);

        TxtFecha.setText(year + "-" + month + "-" + day);
    }

    private void getClienteData() {

        String DNI = txtDNIClienteGenVenta.getText();

        if (DNI.equals("")) {
            mensaje.clienteDNIEmpty();
        } else {
            entdClientes = clientesDAO.getClienteData(DNI);
        }

        this.validateClienteData(DNI);

        this.cleanTableClientes();
        this.listClientes();
    }

    private void validateClienteData(String DNI) {

        int createConfirmation = 1;

        if (entdClientes.getDNI() != null) {

            txtNombreClienteGenVenta.setText(entdClientes.getNombre());
            txtIdProducto.requestFocus();

        } else if (!DNI.equals("")) {
            createConfirmation = mensaje.clienteRegisterConfirmation();

        }

        if (createConfirmation == 0) {

            NewClienteFrom newClienteForm = new NewClienteFrom();
            newClienteForm.setVisible(true);
        }
    }

    private void getProductData() {

        String idProducto = txtIdProducto.getText();

        if (idProducto.equals("")) {

            mensaje.productoEmptyId();
        } else {

            entdProducto = productoDAO.getProductoData(idProducto);
        }

        this.validateProductoData();

        txtProductoGenVenta.requestFocus();
    }

    private void validateProductoData() {

        if (entdProducto.getIdProducto() != 0) {
            txtProductoGenVenta.setText(entdProducto.getNombre());
            txtPrecioGenVenta.setText("$" + entdProducto.getPrecio());
            txtStockGenVenta.setText(String.valueOf(entdProducto.getStock()));
        } else {
            mensaje.productoNotFound();
        }
    }

    private void addProducto() {

        tableModelDetalleVenta = (DefaultTableModel) tblDetalleVentas.getModel();

        this.setValuesProducto();

        if (cantidadProducto == 0) {
            mensaje.productcountInZero();
        }

        if (stockProducto >= cantidadProducto && cantidadProducto > 0) {

            Object[] producto = new Object[6];
            producto[0] = indexProducto;
            producto[1] = idProducto;
            producto[2] = nombreProducto;
            producto[3] = cantidadProducto;
            producto[4] = precioProducto;
            producto[5] = totalProducto;

            tableModelDetalleVenta.addRow(producto);

            tblDetalleVentas.setModel(tableModelDetalleVenta);

            calculateTotal();
        } else if (stockProducto < cantidadProducto) {

            mensaje.notEnoughStock();
        }
    }

    private void setValuesProducto() {
        this.indexProducto = this.indexProducto + 1;
        this.idProducto = entdProducto.getIdProducto();
        this.nombreProducto = entdProducto.getNombre();
        this.cantidadProducto = Integer.parseInt(spnCantidadProducto.getValue().toString());
        this.precioProducto = entdProducto.getPrecio();
        this.totalProducto = cantidadProducto * precioProducto;

        stockProducto = entdProducto.getStock();
    }

    private void calculateTotal() {

        int cantidad = 0;
        float precio = 0;
        totalVenta = 0;

        for (int i = 0; i < tblDetalleVentas.getRowCount(); i++) {

            cantidad = Integer.parseInt(tblDetalleVentas.getValueAt(i, 3).toString());
            precio = Float.parseFloat(tblDetalleVentas.getValueAt(i, 4).toString());
            totalVenta = totalVenta + (cantidad * precio);
        }

        txtTotalVenta.setText("$" + totalVenta);
    }

    public void setTxtTotalVenta(JTextField txtTotalVenta) {
        this.txtTotalVenta = txtTotalVenta;
    }

    public JTextField getTxtTotalVenta() {
        return txtTotalVenta;
    }

    private int saveVenta() {

        entGenVenta.setIdCliente(entdClientes.getIdCliente());
        entGenVenta.setIdUsuario(Integer.parseInt(this.idUsuario));
        entGenVenta.setNroSerie(txtNroSerie.getText());
        entGenVenta.setFecha(TxtFecha.getText());
        entGenVenta.setMonto(this.totalVenta);
        entGenVenta.setEstado("Efectiva");

        return genVentaDAO.saveSale(entGenVenta);
    }

    private int saveDetalleVenta() {

        int result = 0;

        int idVenta = Integer.parseInt(genVentaDAO.getMaxIdVenta());
        String nroSerie = txtNroSerie.getText();

        for (int i = 0; i < tblDetalleVentas.getRowCount(); i++) {
            EntidadDetalleVenta entDetalleVenta = new EntidadDetalleVenta();

            entDetalleVenta.setIdVenta(idVenta);
            entDetalleVenta.setNroSerie(nroSerie);
            entDetalleVenta.setIdProducto(Integer.parseInt(tblDetalleVentas.getValueAt(i, 1).toString()));
            entDetalleVenta.setCantidad(Integer.parseInt(tblDetalleVentas.getValueAt(i, 3).toString()));
            entDetalleVenta.setPrecioVenta(Float.parseFloat(tblDetalleVentas.getValueAt(i, 4).toString()));
            entDetalleVenta.setEstado("Efectiva");

            result = genVentaDAO.saveDetalleVenta(entDetalleVenta);
        }

        return result;
    }

    void setUserRole(String DNI, String role, String idUsuario) {

        this.idUsuario = idUsuario;
        txtIdUsuario.setText(idUsuario);
        txtRoleUsuario.setText(role);

        if (role.equals("Vendedor")) {
            btnCreateUsuario.setVisible(false);
            btnCreateCliente.setVisible(false);
            btnCreateProducto.setVisible(false);

            btnUpdateUsuario.setVisible(false);
            btnUpdateCliente.setVisible(false);
            btnUpdateProducto.setVisible(false);

            btnDeleteCliente.setVisible(false);
            btnDeleteProducto.setVisible(false);
            btnDeleteUsuario.setVisible(false);

            btnNewUsuario.setVisible(false);
            btnNewCliente.setVisible(false);
            btnNewProducto.setVisible(false);
        }
    }

    void updateStock() {
//        for (int i = 0; i < tableModelDetalleVenta.getRowCount(); i++) {
//            idProducto = Integer.parseInt(tblDetalleVentas.getValueAt(i, 1).toString());
//            cantidadProducto = Integer.parseInt(tblDetalleVentas.getValueAt(i, 3).toString());
//            EntidadProducto ep = new EntidadProducto();
//            ep = productoDAO.getProductoData(idProducto);
//            int stock = ep.getStock() - cantidadProducto;
//            productoDAO.StockUpdate(stock, idProducto);
//        }
    }

    void cleanGenerarVentasForm() {
        tableModelDetalleVenta = (DefaultTableModel) tblDetalleVentas.getModel();
        for (int i = 0; i < tableModelDetalleVenta.getRowCount(); i++) {
            tableModelDetalleVenta.removeRow(i);
            i = i - 1;
        }
        txtDNIClienteGenVenta.setText("");
        txtIdProducto.setText("");
        txtPrecioGenVenta.setText("");
        spnCantidadProducto.setValue(0);
        txtNombreClienteGenVenta.setText("");
        txtProductoGenVenta.setText("");
        txtStockGenVenta.setText("");
        txtNroSerie.setText("");
        getMaxSerialNumber();
        txtDNIClienteGenVenta.requestFocus();
    }

    //=================================================================/
    //========= METODOS DE  LAS VENTAS================================/
    public void listSales() {
        ArrayList<EntidadVenta> saleList = VentasDAO.readSales();
        tableModelVentas = (DefaultTableModel) tblVentas.getModel();
        Object[] obbjVentas = new Object[6];

        for (int i = 0; i < saleList.size(); i++) {
            obbjVentas[0] = "000" + saleList.get(i).getIdVenta();
            obbjVentas[1] = "$" + saleList.get(i).getMonto();
            obbjVentas[2] = saleList.get(i).getFecha();
            obbjVentas[3] = saleList.get(i).getEstado();
            obbjVentas[4] = saleList.get(i).getIdCliente();
            obbjVentas[5] = saleList.get(i).getIdUsuario();

            tableModelVentas.addRow(obbjVentas);
        }
        tblVentas.setModel(tableModelVentas);
    }

    public void listProductsDetails(int SaleId) {
        String productsDetail = "";
        ArrayList<EntidadDetalleVenta> listSaleDetails = VentasDAO.searchSaleDetails(SaleId);

        for (int i = 0; i < listSaleDetails.size(); i++) {
            productsDetail = productsDetail + listSaleDetails.get(i).getNombreProducto() + " - $" + listSaleDetails.get(i).getPrecioVenta() + "\n";
        }

        this.txtaProductList_ventas.setText(productsDetail);

    }

    //=================================================================/
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        topPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnClose = new javax.swing.JButton();
        btnMinimize = new javax.swing.JButton();
        txtRoleUsuario = new javax.swing.JTextField();
        btnLogOut = new javax.swing.JButton();
        PanelPrincipal = new javax.swing.JTabbedPane();
        PanelGenerarVenta = new javax.swing.JPanel();
        lblCodCliente = new javax.swing.JLabel();
        txtDNIClienteGenVenta = new javax.swing.JTextField();
        lblConProducto = new javax.swing.JLabel();
        txtIdProducto = new javax.swing.JTextField();
        lblPrecio = new javax.swing.JLabel();
        txtPrecioGenVenta = new javax.swing.JTextField();
        lblCantidad = new javax.swing.JLabel();
        spnCantidadProducto = new javax.swing.JSpinner();
        lblCliente = new javax.swing.JLabel();
        txtNombreClienteGenVenta = new javax.swing.JTextField();
        lblProducto = new javax.swing.JLabel();
        txtProductoGenVenta = new javax.swing.JTextField();
        lblStock = new javax.swing.JLabel();
        txtStockGenVenta = new javax.swing.JTextField();
        TxtFecha = new javax.swing.JTextField();
        lblColaborador = new javax.swing.JLabel();
        txtIdUsuario = new javax.swing.JTextField();
        btnBuscarclient = new javax.swing.JButton();
        btnBuscarProduc = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        lblNroSerie = new javax.swing.JLabel();
        txtNroSerie = new javax.swing.JTextField();
        lablVentastext = new javax.swing.JLabel();
        bgVentas = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblDetalleVentas = new javax.swing.JTable();
        BottomPanel = new javax.swing.JPanel();
        btnGenVenta = new javax.swing.JButton();
        btnCancelar1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtTotalVenta = new javax.swing.JTextField();
        paneColaboradores = new javax.swing.JPanel();
        lblCedulaV = new javax.swing.JLabel();
        txtDNIUsuario = new javax.swing.JTextField();
        lblNombreV = new javax.swing.JLabel();
        txtNombreUsuario = new javax.swing.JTextField();
        lblTelefonoV = new javax.swing.JLabel();
        txtTelefonoUsuario = new javax.swing.JTextField();
        lblEstadoV = new javax.swing.JLabel();
        lblUsuarioV = new javax.swing.JLabel();
        txtusuarioUsuario = new javax.swing.JTextField();
        btnCreateUsuario = new javax.swing.JButton();
        btnUpdateUsuario = new javax.swing.JButton();
        btnDeleteUsuario = new javax.swing.JButton();
        btnNewUsuario = new javax.swing.JButton();
        cbxEstadoUsuario = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        bgColaboradores = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUsuarios = new javax.swing.JTable();
        PanleClietntes = new javax.swing.JPanel();
        ClientesText = new javax.swing.JLabel();
        lblCedulaC = new javax.swing.JLabel();
        txtDNICliente = new javax.swing.JTextField();
        lblNombreC = new javax.swing.JLabel();
        txtNombreCliente = new javax.swing.JTextField();
        lbDireccionC = new javax.swing.JLabel();
        txtDireccionCliente = new javax.swing.JTextField();
        lblEstadoC = new javax.swing.JLabel();
        cbxEstadoCliente = new javax.swing.JComboBox<>();
        btnCreateCliente = new javax.swing.JButton();
        btnUpdateCliente = new javax.swing.JButton();
        btnDeleteCliente = new javax.swing.JButton();
        btnNewCliente = new javax.swing.JButton();
        btnReloadTblCliente = new javax.swing.JButton();
        bgClientes = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        PanelProductos = new javax.swing.JPanel();
        lblNombreP = new javax.swing.JLabel();
        txtNombreProducto = new javax.swing.JTextField();
        lblPrecioP = new javax.swing.JLabel();
        txtPrecioProducto = new javax.swing.JTextField();
        lblStockP = new javax.swing.JLabel();
        txtStockProducto = new javax.swing.JTextField();
        lblEstadoP = new javax.swing.JLabel();
        cbxEstadoProducto = new javax.swing.JComboBox<>();
        btnNewProducto = new javax.swing.JButton();
        btnDeleteProducto = new javax.swing.JButton();
        btnUpdateProducto = new javax.swing.JButton();
        btnCreateProducto = new javax.swing.JButton();
        ProductosText = new javax.swing.JLabel();
        bgProductos = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();
        PanelVentas = new javax.swing.JPanel();
        txtClientName_ventas = new javax.swing.JTextField();
        lblClientName_Ventas = new javax.swing.JLabel();
        txtUserName_ventas = new javax.swing.JTextField();
        lblUserName_ventas = new javax.swing.JLabel();
        txtDate_ventas = new javax.swing.JTextField();
        lbdate_ventas = new javax.swing.JLabel();
        txtMount_ventas = new javax.swing.JTextField();
        lblMount_ventas = new javax.swing.JLabel();
        lblStatus_ventas = new javax.swing.JLabel();
        txtStatus_ventas = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtaProductList_ventas = new javax.swing.JTextArea();
        bgProductos1 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblVentas = new javax.swing.JTable();

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

        btnClose.setBackground(new java.awt.Color(0, 0, 0));
        btnClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/002-cancel.png"))); // NOI18N
        btnClose.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnClose.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        btnMinimize.setBackground(new java.awt.Color(0, 0, 0));
        btnMinimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/003-minimize.png"))); // NOI18N
        btnMinimize.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnMinimize.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMinimize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMinimizeActionPerformed(evt);
            }
        });

        txtRoleUsuario.setBackground(new java.awt.Color(0, 0, 0));
        txtRoleUsuario.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        txtRoleUsuario.setForeground(new java.awt.Color(255, 255, 255));
        txtRoleUsuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtRoleUsuario.setBorder(null);
        txtRoleUsuario.setEnabled(false);

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
                .addComponent(txtRoleUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnClose, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(topPanelLayout.createSequentialGroup()
                        .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnLogOut, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRoleUsuario, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(5, 5, 5))
                    .addComponent(btnMinimize, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        getContentPane().add(topPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 50));

        PanelPrincipal.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        PanelPrincipal.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        PanelPrincipal.setPreferredSize(new java.awt.Dimension(1000, 550));

        PanelGenerarVenta.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCodCliente.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblCodCliente.setForeground(new java.awt.Color(255, 255, 255));
        lblCodCliente.setText("Cod Cliente");
        PanelGenerarVenta.add(lblCodCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        txtDNIClienteGenVenta.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtDNIClienteGenVenta.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PanelGenerarVenta.add(txtDNIClienteGenVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 100, 30));

        lblConProducto.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblConProducto.setForeground(new java.awt.Color(255, 255, 255));
        lblConProducto.setText("Cod Producto");
        PanelGenerarVenta.add(lblConProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        txtIdProducto.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtIdProducto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PanelGenerarVenta.add(txtIdProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, 100, 30));

        lblPrecio.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblPrecio.setForeground(new java.awt.Color(255, 255, 255));
        lblPrecio.setText("Precio");
        PanelGenerarVenta.add(lblPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        txtPrecioGenVenta.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtPrecioGenVenta.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPrecioGenVenta.setEnabled(false);
        PanelGenerarVenta.add(txtPrecioGenVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 100, 30));

        lblCantidad.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblCantidad.setForeground(new java.awt.Color(255, 255, 255));
        lblCantidad.setText("Cantidad");
        PanelGenerarVenta.add(lblCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        spnCantidadProducto.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        PanelGenerarVenta.add(spnCantidadProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 160, 100, 30));

        lblCliente.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblCliente.setForeground(new java.awt.Color(255, 255, 255));
        lblCliente.setText("Cliente");
        PanelGenerarVenta.add(lblCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, -1, -1));

        txtNombreClienteGenVenta.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtNombreClienteGenVenta.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNombreClienteGenVenta.setEnabled(false);
        PanelGenerarVenta.add(txtNombreClienteGenVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 10, 160, 30));

        lblProducto.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblProducto.setForeground(new java.awt.Color(255, 255, 255));
        lblProducto.setText("Producto");
        PanelGenerarVenta.add(lblProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 60, -1, -1));

        txtProductoGenVenta.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtProductoGenVenta.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtProductoGenVenta.setEnabled(false);
        PanelGenerarVenta.add(txtProductoGenVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 60, 480, 30));

        lblStock.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblStock.setForeground(new java.awt.Color(255, 255, 255));
        lblStock.setText("Stock");
        PanelGenerarVenta.add(lblStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 10, -1, -1));

        txtStockGenVenta.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtStockGenVenta.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtStockGenVenta.setEnabled(false);
        PanelGenerarVenta.add(txtStockGenVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 10, 100, 30));

        TxtFecha.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        TxtFecha.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TxtFecha.setEnabled(false);
        PanelGenerarVenta.add(TxtFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 10, 110, 30));

        lblColaborador.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblColaborador.setForeground(new java.awt.Color(255, 255, 255));
        lblColaborador.setText("Vendedor");
        PanelGenerarVenta.add(lblColaborador, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 160, -1, -1));

        txtIdUsuario.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtIdUsuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIdUsuario.setEnabled(false);
        PanelGenerarVenta.add(txtIdUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 160, 160, 30));

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
        PanelGenerarVenta.add(btnBuscarclient, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, 80, 30));

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
        PanelGenerarVenta.add(btnBuscarProduc, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 60, 80, 30));

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
        PanelGenerarVenta.add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 110, 80, 30));

        lblNroSerie.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblNroSerie.setForeground(new java.awt.Color(255, 255, 255));
        lblNroSerie.setText("N°  Serie");
        PanelGenerarVenta.add(lblNroSerie, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 110, 70, 20));

        txtNroSerie.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtNroSerie.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNroSerie.setEnabled(false);
        PanelGenerarVenta.add(txtNroSerie, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 110, 160, 30));

        lablVentastext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/VentasText.png"))); // NOI18N
        PanelGenerarVenta.add(lablVentastext, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 150, 170, -1));

        bgVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Clientesbg.png"))); // NOI18N
        PanelGenerarVenta.add(bgVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        tblDetalleVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Numero", "Codigo", "Producto", "Cantidad", "Valor Unidad", "Total"
            }
        ));
        jScrollPane4.setViewportView(tblDetalleVentas);

        PanelGenerarVenta.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 1000, 230));

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

        txtTotalVenta.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        txtTotalVenta.setToolTipText("");
        txtTotalVenta.setBorder(null);
        txtTotalVenta.setEnabled(false);
        BottomPanel.add(txtTotalVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 20, 210, 30));

        PanelGenerarVenta.add(BottomPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 440, 1010, 70));

        PanelPrincipal.addTab("    Generar Ventas  ", PanelGenerarVenta);

        paneColaboradores.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCedulaV.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblCedulaV.setForeground(new java.awt.Color(255, 255, 255));
        lblCedulaV.setText("Identificacion");
        paneColaboradores.add(lblCedulaV, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        txtDNIUsuario.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtDNIUsuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        paneColaboradores.add(txtDNIUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 160, 30));

        lblNombreV.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblNombreV.setForeground(new java.awt.Color(255, 255, 255));
        lblNombreV.setText("Nombre");
        paneColaboradores.add(lblNombreV, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        txtNombreUsuario.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtNombreUsuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        paneColaboradores.add(txtNombreUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, 160, 30));

        lblTelefonoV.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblTelefonoV.setForeground(new java.awt.Color(255, 255, 255));
        lblTelefonoV.setText("Telefono");
        paneColaboradores.add(lblTelefonoV, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        txtTelefonoUsuario.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtTelefonoUsuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        paneColaboradores.add(txtTelefonoUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 160, 30));

        lblEstadoV.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblEstadoV.setForeground(new java.awt.Color(255, 255, 255));
        lblEstadoV.setText("Estado");
        paneColaboradores.add(lblEstadoV, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        lblUsuarioV.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblUsuarioV.setForeground(new java.awt.Color(255, 255, 255));
        lblUsuarioV.setText("Usuario");
        paneColaboradores.add(lblUsuarioV, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 10, -1, -1));

        txtusuarioUsuario.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtusuarioUsuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        paneColaboradores.add(txtusuarioUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 10, 170, 30));

        btnCreateUsuario.setBackground(new java.awt.Color(51, 153, 0));
        btnCreateUsuario.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnCreateUsuario.setForeground(new java.awt.Color(255, 255, 255));
        btnCreateUsuario.setText("Crear");
        btnCreateUsuario.setBorder(null);
        btnCreateUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCreateUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateUsuarioActionPerformed(evt);
            }
        });
        paneColaboradores.add(btnCreateUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 10, 90, 30));

        btnUpdateUsuario.setBackground(new java.awt.Color(212, 172, 13));
        btnUpdateUsuario.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnUpdateUsuario.setForeground(new java.awt.Color(51, 51, 51));
        btnUpdateUsuario.setText("Actualizar");
        btnUpdateUsuario.setBorder(null);
        btnUpdateUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUpdateUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateUsuarioActionPerformed(evt);
            }
        });
        paneColaboradores.add(btnUpdateUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 10, 100, 30));

        btnDeleteUsuario.setBackground(new java.awt.Color(255, 51, 51));
        btnDeleteUsuario.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnDeleteUsuario.setForeground(new java.awt.Color(255, 255, 255));
        btnDeleteUsuario.setText("Eliminar");
        btnDeleteUsuario.setBorder(null);
        btnDeleteUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDeleteUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteUsuarioActionPerformed(evt);
            }
        });
        paneColaboradores.add(btnDeleteUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 10, 90, 30));

        btnNewUsuario.setBackground(new java.awt.Color(102, 102, 102));
        btnNewUsuario.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnNewUsuario.setForeground(new java.awt.Color(255, 255, 255));
        btnNewUsuario.setText("Nuevo");
        btnNewUsuario.setBorder(null);
        btnNewUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNewUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewUsuarioActionPerformed(evt);
            }
        });
        paneColaboradores.add(btnNewUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 70, 90, 30));

        cbxEstadoUsuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Activo", "Inactivo" }));
        paneColaboradores.add(cbxEstadoUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 160, 160, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ColaboradoresText.png"))); // NOI18N
        paneColaboradores.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 150, 300, -1));

        bgColaboradores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Clientesbg.png"))); // NOI18N
        paneColaboradores.add(bgColaboradores, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, -1));

        tblUsuarios.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id Colaborador", "Identificacion", "Nombre", "Telefono", "Estado", "Usuario"
            }
        ));
        tblUsuarios.setOpaque(false);
        tblUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUsuariosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblUsuarios);

        paneColaboradores.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 1000, 320));

        PanelPrincipal.addTab("      Colaboradores      ", paneColaboradores);

        PanleClietntes.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ClientesText.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ClientesText.png"))); // NOI18N
        PanleClietntes.add(ClientesText, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 150, 180, -1));

        lblCedulaC.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblCedulaC.setForeground(new java.awt.Color(255, 255, 255));
        lblCedulaC.setText("Identificacion");
        PanleClietntes.add(lblCedulaC, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        txtDNICliente.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtDNICliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PanleClietntes.add(txtDNICliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 160, 30));

        lblNombreC.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblNombreC.setForeground(new java.awt.Color(255, 255, 255));
        lblNombreC.setText("Nombre");
        PanleClietntes.add(lblNombreC, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        txtNombreCliente.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtNombreCliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PanleClietntes.add(txtNombreCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, 160, 30));

        lbDireccionC.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccionC.setForeground(new java.awt.Color(255, 255, 255));
        lbDireccionC.setText("Direccion");
        PanleClietntes.add(lbDireccionC, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        txtDireccionCliente.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtDireccionCliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PanleClietntes.add(txtDireccionCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 160, 30));

        lblEstadoC.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblEstadoC.setForeground(new java.awt.Color(255, 255, 255));
        lblEstadoC.setText("Estado");
        PanleClietntes.add(lblEstadoC, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        cbxEstadoCliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Activo", "Inactivo" }));
        PanleClietntes.add(cbxEstadoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 160, 160, 30));

        btnCreateCliente.setBackground(new java.awt.Color(51, 153, 0));
        btnCreateCliente.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnCreateCliente.setForeground(new java.awt.Color(255, 255, 255));
        btnCreateCliente.setText("Crear");
        btnCreateCliente.setBorder(null);
        btnCreateCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCreateCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateClienteActionPerformed(evt);
            }
        });
        PanleClietntes.add(btnCreateCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 10, 90, 30));

        btnUpdateCliente.setBackground(new java.awt.Color(212, 172, 13));
        btnUpdateCliente.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnUpdateCliente.setForeground(new java.awt.Color(51, 51, 51));
        btnUpdateCliente.setText("Actualizar");
        btnUpdateCliente.setBorder(null);
        btnUpdateCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUpdateCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateClienteActionPerformed(evt);
            }
        });
        PanleClietntes.add(btnUpdateCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 10, 100, 30));

        btnDeleteCliente.setBackground(new java.awt.Color(255, 51, 51));
        btnDeleteCliente.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnDeleteCliente.setForeground(new java.awt.Color(255, 255, 255));
        btnDeleteCliente.setText("Eliminar");
        btnDeleteCliente.setBorder(null);
        btnDeleteCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDeleteCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteClienteActionPerformed(evt);
            }
        });
        PanleClietntes.add(btnDeleteCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 10, 90, 30));

        btnNewCliente.setBackground(new java.awt.Color(102, 102, 102));
        btnNewCliente.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnNewCliente.setForeground(new java.awt.Color(255, 255, 255));
        btnNewCliente.setText("Nuevo");
        btnNewCliente.setBorder(null);
        btnNewCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNewCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewClienteActionPerformed(evt);
            }
        });
        PanleClietntes.add(btnNewCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 60, 90, 30));

        btnReloadTblCliente.setBackground(new java.awt.Color(255, 102, 51));
        btnReloadTblCliente.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnReloadTblCliente.setForeground(new java.awt.Color(255, 255, 255));
        btnReloadTblCliente.setText("Refrescar");
        btnReloadTblCliente.setBorder(null);
        btnReloadTblCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReloadTblCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReloadTblClienteActionPerformed(evt);
            }
        });
        PanleClietntes.add(btnReloadTblCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 110, 90, 30));

        bgClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Clientesbg.png"))); // NOI18N
        PanleClietntes.add(bgClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id Cliente", "Identificacion", "Nombre", "Direccion", "Estado"
            }
        ));
        tblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblClientes);

        PanleClietntes.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 1000, 320));

        PanelPrincipal.addTab("    Clientes    ", PanleClietntes);

        PanelProductos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblNombreP.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblNombreP.setForeground(new java.awt.Color(255, 255, 255));
        lblNombreP.setText("Nombre");
        PanelProductos.add(lblNombreP, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        txtNombreProducto.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtNombreProducto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PanelProductos.add(txtNombreProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 440, 30));

        lblPrecioP.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblPrecioP.setForeground(new java.awt.Color(255, 255, 255));
        lblPrecioP.setText("Precio");
        PanelProductos.add(lblPrecioP, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        txtPrecioProducto.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtPrecioProducto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PanelProductos.add(txtPrecioProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, 160, 30));

        lblStockP.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblStockP.setForeground(new java.awt.Color(255, 255, 255));
        lblStockP.setText("Stock");
        PanelProductos.add(lblStockP, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        txtStockProducto.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtStockProducto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PanelProductos.add(txtStockProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 160, 30));

        lblEstadoP.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblEstadoP.setForeground(new java.awt.Color(255, 255, 255));
        lblEstadoP.setText("Estado");
        PanelProductos.add(lblEstadoP, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        cbxEstadoProducto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Existente", "Agotado" }));
        PanelProductos.add(cbxEstadoProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 160, 160, 30));

        btnNewProducto.setBackground(new java.awt.Color(102, 102, 102));
        btnNewProducto.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnNewProducto.setForeground(new java.awt.Color(255, 255, 255));
        btnNewProducto.setText("Nuevo");
        btnNewProducto.setBorder(null);
        btnNewProducto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNewProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewProductoActionPerformed(evt);
            }
        });
        PanelProductos.add(btnNewProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 70, 90, 30));

        btnDeleteProducto.setBackground(new java.awt.Color(255, 51, 51));
        btnDeleteProducto.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnDeleteProducto.setForeground(new java.awt.Color(255, 255, 255));
        btnDeleteProducto.setText("Eliminar");
        btnDeleteProducto.setBorder(null);
        btnDeleteProducto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDeleteProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteProductoActionPerformed(evt);
            }
        });
        PanelProductos.add(btnDeleteProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 10, 90, 30));

        btnUpdateProducto.setBackground(new java.awt.Color(212, 172, 13));
        btnUpdateProducto.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnUpdateProducto.setForeground(new java.awt.Color(51, 51, 51));
        btnUpdateProducto.setText("Actualizar");
        btnUpdateProducto.setBorder(null);
        btnUpdateProducto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUpdateProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateProductoActionPerformed(evt);
            }
        });
        PanelProductos.add(btnUpdateProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 10, 100, 30));

        btnCreateProducto.setBackground(new java.awt.Color(51, 153, 0));
        btnCreateProducto.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnCreateProducto.setForeground(new java.awt.Color(255, 255, 255));
        btnCreateProducto.setText("Crear");
        btnCreateProducto.setBorder(null);
        btnCreateProducto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCreateProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateProductoActionPerformed(evt);
            }
        });
        PanelProductos.add(btnCreateProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 10, 90, 30));

        ProductosText.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ProductosText.png"))); // NOI18N
        PanelProductos.add(ProductosText, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 150, 240, -1));

        bgProductos.setBackground(new java.awt.Color(255, 51, 0));
        bgProductos.setForeground(new java.awt.Color(255, 255, 255));
        bgProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Clientesbg.png"))); // NOI18N
        PanelProductos.add(bgProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 200));

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

        PanelVentas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtClientName_ventas.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtClientName_ventas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PanelVentas.add(txtClientName_ventas, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 160, 30));

        lblClientName_Ventas.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblClientName_Ventas.setForeground(new java.awt.Color(255, 255, 255));
        lblClientName_Ventas.setText("Cliente");
        PanelVentas.add(lblClientName_Ventas, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        txtUserName_ventas.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtUserName_ventas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PanelVentas.add(txtUserName_ventas, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, 160, 30));

        lblUserName_ventas.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblUserName_ventas.setForeground(new java.awt.Color(255, 255, 255));
        lblUserName_ventas.setText("Vendedor");
        PanelVentas.add(lblUserName_ventas, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        txtDate_ventas.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtDate_ventas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PanelVentas.add(txtDate_ventas, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 160, 30));

        lbdate_ventas.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbdate_ventas.setForeground(new java.awt.Color(255, 255, 255));
        lbdate_ventas.setText("Fecha");
        PanelVentas.add(lbdate_ventas, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        txtMount_ventas.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtMount_ventas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PanelVentas.add(txtMount_ventas, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 160, 160, 30));

        lblMount_ventas.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblMount_ventas.setForeground(new java.awt.Color(255, 255, 255));
        lblMount_ventas.setText("Monto Total");
        PanelVentas.add(lblMount_ventas, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        lblStatus_ventas.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblStatus_ventas.setForeground(new java.awt.Color(255, 255, 255));
        lblStatus_ventas.setText("Estado");
        PanelVentas.add(lblStatus_ventas, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 10, -1, -1));

        txtStatus_ventas.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtStatus_ventas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PanelVentas.add(txtStatus_ventas, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 10, 160, 30));

        txtaProductList_ventas.setColumns(20);
        txtaProductList_ventas.setRows(5);
        jScrollPane5.setViewportView(txtaProductList_ventas);

        PanelVentas.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 10, 360, 170));

        bgProductos1.setBackground(new java.awt.Color(255, 51, 0));
        bgProductos1.setForeground(new java.awt.Color(255, 255, 255));
        bgProductos1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Clientesbg.png"))); // NOI18N
        PanelVentas.add(bgProductos1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, -1));

        tblVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No Venta", "Monto ", "Fecha", "Estado", "Cliente", "Vendedor"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblVentasMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblVentas);
        if (tblVentas.getColumnModel().getColumnCount() > 0) {
            tblVentas.getColumnModel().getColumn(3).setResizable(false);
        }

        PanelVentas.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 1000, 320));

        PanelPrincipal.addTab("Ventas", PanelVentas);

        getContentPane().add(PanelPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 1000, 550));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void topPanelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_topPanelMousePressed
        XWindowsPosition = evt.getX();
        YWindowsPosition = evt.getY();
    }//GEN-LAST:event_topPanelMousePressed

    private void topPanelMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_topPanelMouseDragged
        Point point = MouseInfo.getPointerInfo().getLocation();
        setLocation(point.x - XWindowsPosition, point.y - YWindowsPosition);
    }//GEN-LAST:event_topPanelMouseDragged

    private void btnMinimizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinimizeActionPerformed
        this.setExtendedState(1);
    }//GEN-LAST:event_btnMinimizeActionPerformed

    private void tblProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductosMouseClicked
        int fila = tblProductos.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Debe Seleccionar Una Fila");
        } else {
            tableRowIdProducto = Integer.parseInt(tblProductos.getValueAt(fila, 0).toString());
            String nombre = tblProductos.getValueAt(fila, 1).toString();
            String precio = tblProductos.getValueAt(fila, 2).toString();
            String stock = tblProductos.getValueAt(fila, 3).toString();
            String estado = tblProductos.getValueAt(fila, 4).toString();
            txtNombreProducto.setText(nombre);
            txtPrecioProducto.setText(precio);
            txtStockProducto.setText(stock);
            cbxEstadoProducto.setSelectedItem(estado);
        }
    }//GEN-LAST:event_tblProductosMouseClicked

    private void btnCreateProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateProductoActionPerformed
        createProducto();
        cleanTableProductos();
        listProductos();
        cleanFormProductos();
    }//GEN-LAST:event_btnCreateProductoActionPerformed

    private void btnUpdateProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateProductoActionPerformed
        updateProducto();
        cleanTableProductos();
        listProductos();
        cleanFormProductos();
    }//GEN-LAST:event_btnUpdateProductoActionPerformed

    private void btnDeleteProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteProductoActionPerformed
        deleteProducto();
        cleanTableProductos();
        listProductos();
        cleanFormProductos();
    }//GEN-LAST:event_btnDeleteProductoActionPerformed

    private void btnNewProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewProductoActionPerformed
        cleanFormProductos();
        cleanTableProductos();
        listProductos();
    }//GEN-LAST:event_btnNewProductoActionPerformed

    private void tblClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientesMouseClicked
        int fila = tblClientes.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Debe Seleccionar Una Fila");
        } else {
            tableRowIdClente = Integer.parseInt(tblClientes.getValueAt(fila, 0).toString());
            String cedula = tblClientes.getValueAt(fila, 1).toString();
            String nombre = tblClientes.getValueAt(fila, 2).toString();
            String direccion = tblClientes.getValueAt(fila, 3).toString();
            String estado = tblClientes.getValueAt(fila, 4).toString();
            txtDNICliente.setText(cedula);
            txtNombreCliente.setText(nombre);
            txtDireccionCliente.setText(direccion);
            cbxEstadoCliente.setSelectedItem(estado);
        }
    }//GEN-LAST:event_tblClientesMouseClicked

    private void btnNewClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewClienteActionPerformed
        cleanFormClientes();
        cleanTableClientes();
        listClientes();
    }//GEN-LAST:event_btnNewClienteActionPerformed

    private void btnDeleteClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteClienteActionPerformed
        this.deleteCliente();
        this.cleanTableClientes();
        this.listClientes();
        this.cleanFormClientes();
    }//GEN-LAST:event_btnDeleteClienteActionPerformed

    private void btnUpdateClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateClienteActionPerformed
        this.updateClente();
        this.cleanTableClientes();
        this.listClientes();
        this.cleanFormClientes();
    }//GEN-LAST:event_btnUpdateClienteActionPerformed

    private void btnCreateClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateClienteActionPerformed
        this.createCliente();
        this.cleanTableClientes();
        this.listClientes();
        this.cleanFormClientes();
    }//GEN-LAST:event_btnCreateClienteActionPerformed

    private void tblUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUsuariosMouseClicked
        int fila = tblUsuarios.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Debe Seleccionar Una Fila");
        } else {
            tableRowIdUsuario = Integer.parseInt(tblUsuarios.getValueAt(fila, 0).toString());
            txtDNIUsuario.setText(tblUsuarios.getValueAt(fila, 1).toString());
            txtNombreUsuario.setText(tblUsuarios.getValueAt(fila, 2).toString());
            txtTelefonoUsuario.setText(tblUsuarios.getValueAt(fila, 3).toString());
            cbxEstadoUsuario.setSelectedItem(tblUsuarios.getValueAt(fila, 4).toString());
            txtusuarioUsuario.setText(tblUsuarios.getValueAt(fila, 5).toString());
        }
    }//GEN-LAST:event_tblUsuariosMouseClicked

    private void btnNewUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewUsuarioActionPerformed
        cleanFormUsuarios();
        cleanTableUsuarios();
        listUsuarios();
    }//GEN-LAST:event_btnNewUsuarioActionPerformed

    private void btnDeleteUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteUsuarioActionPerformed
        deleteUsuario();
        cleanTableUsuarios();
        listUsuarios();
        cleanFormUsuarios();
    }//GEN-LAST:event_btnDeleteUsuarioActionPerformed

    private void btnUpdateUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateUsuarioActionPerformed
        updateUsuario();
        cleanTableUsuarios();
        listUsuarios();
        cleanFormUsuarios();
    }//GEN-LAST:event_btnUpdateUsuarioActionPerformed

    private void btnCreateUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateUsuarioActionPerformed
        createUsuario();
        cleanTableUsuarios();
        listUsuarios();
        cleanFormUsuarios();
    }//GEN-LAST:event_btnCreateUsuarioActionPerformed

    private void btnBuscarclientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarclientActionPerformed
        getClienteData();
    }//GEN-LAST:event_btnBuscarclientActionPerformed

    private void btnBuscarProducActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarProducActionPerformed
        getProductData();
        spnCantidadProducto.setValue(1);

    }//GEN-LAST:event_btnBuscarProducActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        addProducto();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnGenVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenVentaActionPerformed

        boolean totalAmountEmpty = txtTotalVenta.getText().equals("");
        String nroSerie = txtNroSerie.getText();

        int ventaConfirmation = 1;
        int saveVentaResult = 0;
        int saveDetalleVentaResult = 0;

        if (totalAmountEmpty) {
            mensaje.totalCostInZero();
        } else {
            ventaConfirmation = mensaje.ventaConfirmation();
        }

        if (ventaConfirmation == 0 && totalAmountEmpty == false) {
            saveVentaResult = this.saveVenta();
            saveDetalleVentaResult = this.saveDetalleVenta();
        } else {
            mensaje.operationCanceled();
        }

        if (saveVentaResult == 1 && saveDetalleVentaResult == 1) {
            CobroForm cobroFrom = new CobroForm();
            updateStock();
            cleanTableProductos();
            listProductos();
            cleanGenerarVentasForm();
            
            cobroFrom.SetTotal(this.totalVenta,nroSerie );
            cobroFrom.setVisible(true);
            
            
        }
        
        if (saveVentaResult == 0 && saveDetalleVentaResult == 0 && ventaConfirmation == 0 && totalAmountEmpty == false) {
            mensaje.ventaSaveFailed();
        }

    }//GEN-LAST:event_btnGenVentaActionPerformed

    private void btnCancelar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelar1ActionPerformed

        int resp = JOptionPane.showConfirmDialog(this, "Desea limpiar los campos?,");
        if (resp == 0) {
            cleanGenerarVentasForm();
        } else {
            JOptionPane.showMessageDialog(this, "Operacion Cancelada");
        }
    }//GEN-LAST:event_btnCancelar1ActionPerformed

    private void btnLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogOutActionPerformed
        int resp = JOptionPane.showConfirmDialog(this, "Desea cerrar la session?");
        if (resp == 0) {
            loginForm.setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_btnLogOutActionPerformed

    private void tblVentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVentasMouseClicked
        int saleRow = tblVentas.getSelectedRow();

        if (saleRow == -1) {
            JOptionPane.showMessageDialog(this, "Debe Seleccionar Una Fila");
        } else {
            this.idVenta = Integer.parseInt(tblVentas.getValueAt(saleRow, 0).toString());
            this.listProductsDetails(this.idVenta);

            txtMount_ventas.setText(tblVentas.getValueAt(saleRow, 1).toString());
            txtDate_ventas.setText(tblVentas.getValueAt(saleRow, 2).toString());
            txtStatus_ventas.setText(tblVentas.getValueAt(saleRow, 3).toString());
            txtClientName_ventas.setText(tblVentas.getValueAt(saleRow, 4).toString());
            txtUserName_ventas.setText(tblVentas.getValueAt(saleRow, 5).toString());
        }
    }//GEN-LAST:event_tblVentasMouseClicked

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed

        int resp = JOptionPane.showConfirmDialog(this, "Desea cerrar la aplicacion?");
        if (resp == 0) {
            System.exit(0);
        }
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnReloadTblClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReloadTblClienteActionPerformed
        this.cleanTableClientes();
        this.listClientes();
    }//GEN-LAST:event_btnReloadTblClienteActionPerformed

    public static void main(String args[]) {

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Aplicacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Aplicacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Aplicacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Aplicacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Aplicacion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BottomPanel;
    private javax.swing.JLabel ClientesText;
    private javax.swing.JPanel PanelGenerarVenta;
    private javax.swing.JTabbedPane PanelPrincipal;
    private javax.swing.JPanel PanelProductos;
    private javax.swing.JPanel PanelVentas;
    private javax.swing.JPanel PanleClietntes;
    private javax.swing.JLabel ProductosText;
    private javax.swing.JTextField TxtFecha;
    private javax.swing.JLabel bgClientes;
    private javax.swing.JLabel bgColaboradores;
    private javax.swing.JLabel bgProductos;
    private javax.swing.JLabel bgProductos1;
    private javax.swing.JLabel bgVentas;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscarProduc;
    private javax.swing.JButton btnBuscarclient;
    private javax.swing.JButton btnCancelar1;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnCreateCliente;
    private javax.swing.JButton btnCreateProducto;
    private javax.swing.JButton btnCreateUsuario;
    private javax.swing.JButton btnDeleteCliente;
    private javax.swing.JButton btnDeleteProducto;
    private javax.swing.JButton btnDeleteUsuario;
    private javax.swing.JButton btnGenVenta;
    private javax.swing.JButton btnLogOut;
    private javax.swing.JButton btnMinimize;
    private javax.swing.JButton btnNewCliente;
    private javax.swing.JButton btnNewProducto;
    private javax.swing.JButton btnNewUsuario;
    private javax.swing.JButton btnReloadTblCliente;
    private javax.swing.JButton btnUpdateCliente;
    private javax.swing.JButton btnUpdateProducto;
    private javax.swing.JButton btnUpdateUsuario;
    private javax.swing.JComboBox<String> cbxEstadoCliente;
    private javax.swing.JComboBox<String> cbxEstadoProducto;
    private javax.swing.JComboBox<String> cbxEstadoUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JLabel lablVentastext;
    private javax.swing.JLabel lbDireccionC;
    private javax.swing.JLabel lbdate_ventas;
    private javax.swing.JLabel lblCantidad;
    private javax.swing.JLabel lblCedulaC;
    private javax.swing.JLabel lblCedulaV;
    private javax.swing.JLabel lblClientName_Ventas;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblCodCliente;
    private javax.swing.JLabel lblColaborador;
    private javax.swing.JLabel lblConProducto;
    private javax.swing.JLabel lblEstadoC;
    private javax.swing.JLabel lblEstadoP;
    private javax.swing.JLabel lblEstadoV;
    private javax.swing.JLabel lblMount_ventas;
    private javax.swing.JLabel lblNombreC;
    private javax.swing.JLabel lblNombreP;
    private javax.swing.JLabel lblNombreV;
    private javax.swing.JLabel lblNroSerie;
    private javax.swing.JLabel lblPrecio;
    private javax.swing.JLabel lblPrecioP;
    private javax.swing.JLabel lblProducto;
    private javax.swing.JLabel lblStatus_ventas;
    private javax.swing.JLabel lblStock;
    private javax.swing.JLabel lblStockP;
    private javax.swing.JLabel lblTelefonoV;
    private javax.swing.JLabel lblUserName_ventas;
    private javax.swing.JLabel lblUsuarioV;
    private javax.swing.JPanel paneColaboradores;
    private javax.swing.JSpinner spnCantidadProducto;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTable tblDetalleVentas;
    private javax.swing.JTable tblProductos;
    private javax.swing.JTable tblUsuarios;
    private javax.swing.JTable tblVentas;
    private javax.swing.JPanel topPanel;
    private javax.swing.JTextField txtClientName_ventas;
    private javax.swing.JTextField txtDNICliente;
    private javax.swing.JTextField txtDNIClienteGenVenta;
    private javax.swing.JTextField txtDNIUsuario;
    private javax.swing.JTextField txtDate_ventas;
    private javax.swing.JTextField txtDireccionCliente;
    private javax.swing.JTextField txtIdProducto;
    private javax.swing.JTextField txtIdUsuario;
    private javax.swing.JTextField txtMount_ventas;
    private javax.swing.JTextField txtNombreCliente;
    private javax.swing.JTextField txtNombreClienteGenVenta;
    private javax.swing.JTextField txtNombreProducto;
    private javax.swing.JTextField txtNombreUsuario;
    private javax.swing.JTextField txtNroSerie;
    private javax.swing.JTextField txtPrecioGenVenta;
    private javax.swing.JTextField txtPrecioProducto;
    private javax.swing.JTextField txtProductoGenVenta;
    private javax.swing.JTextField txtRoleUsuario;
    private javax.swing.JTextField txtStatus_ventas;
    private javax.swing.JTextField txtStockGenVenta;
    private javax.swing.JTextField txtStockProducto;
    private javax.swing.JTextField txtTelefonoUsuario;
    private javax.swing.JTextField txtTotalVenta;
    private javax.swing.JTextField txtUserName_ventas;
    private javax.swing.JTextArea txtaProductList_ventas;
    private javax.swing.JTextField txtusuarioUsuario;
    // End of variables declaration//GEN-END:variables
}
