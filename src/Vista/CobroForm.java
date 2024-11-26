/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.VentasDAO;
import Modelos.EntidadDetalleVenta;
import Modelos.EntidadUsuario;
import Modelos.EntidadVenta;
import java.awt.MouseInfo;
import java.awt.Point;
import javax.swing.JOptionPane;

/**
 *
 * @author Mr. Robot
 */
public class CobroForm extends javax.swing.JFrame {

    EntidadVenta evt = new EntidadVenta();
    EntidadDetalleVenta edv = new EntidadDetalleVenta();
    VentasDAO venDAO = new VentasDAO();

    int X;
    int Y;
    float pago = 0;
    float totalPag = 0;
    String listapago = "";

    //====================================
    int idCliente;
    int idVendedor;
    String nroSerie="";
    String fecha;
    float monto;
    String estado;
    //=====================================
    int idVenta;
    int idVenta2;
    int idProducto;
    int cantidad;
    float precio;
    String estadoD;

    Application pf = new Application();

    public CobroForm() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    public void CapVenta(int idClient, int idVend, String NRS, String fec, float mon, String est) {
        nroSerie = NRS;
        txtNroSerie.setText(NRS);
        
        evt.setId_Cliente(idClient);
        evt.setId_Vendedor(idVend);
        evt.setNro_Venta(NRS);
        evt.setFecha(fec);
        evt.setMonto(mon);
        evt.setEstado(est);    
        venDAO.GuardarVenta(evt);
    }

    public void GuardarVenta() {
        evt.setId_Cliente(idCliente);
        evt.setId_Vendedor(idVendedor);
        evt.setNro_Venta(nroSerie);
        evt.setFecha(fecha);
        evt.setMonto(monto);
        evt.setEstado(estado);
        venDAO.GuardarVenta(evt);
    }

    public void Cap_Guad_DetVenta(int idVent, String NRS, int idProduct, int cant, float preci, String est) {
        idVenta = idVent;
        idVenta2 = idVent;
        nroSerie = NRS;
        idProducto = idProduct;
        cantidad = cant;
        precio = preci;
        estadoD = est;

        edv.setId_Venta(idVenta);
        edv.setNroSerie(nroSerie);
        edv.setId_Producto(idProducto);
        edv.setCantidad(cantidad);
        edv.setPrecioVenta(precio);
        edv.setEstado(estadoD);
        
        venDAO.GuardarDetalleVenta(edv);
    }

    public void CapTotal(float total, String serie) {
        totalPag = total;
        txtTotalPagar.setText("$" + totalPag);
        
        nroSerie = serie;
        txtNroSerie.setText(nroSerie);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        topPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnCincomil = new javax.swing.JButton();
        btnDosmil = new javax.swing.JButton();
        btnDiezmil = new javax.swing.JButton();
        btnCincuentamil = new javax.swing.JButton();
        btnMil = new javax.swing.JButton();
        btnVeintemil = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtOtroValor = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtTotalPagar = new javax.swing.JTextField();
        txtCambio = new javax.swing.JTextField();
        btnCalcular = new javax.swing.JButton();
        btnCobrar = new javax.swing.JButton();
        btnLimpiarCobro = new javax.swing.JButton();
        btnCancelarCobro = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txaTotales = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        txtNroSerie = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

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

        javax.swing.GroupLayout topPanelLayout = new javax.swing.GroupLayout(topPanel);
        topPanel.setLayout(topPanelLayout);
        topPanelLayout.setHorizontalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, topPanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        topPanelLayout.setVerticalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnCincomil.setBackground(new java.awt.Color(0, 153, 204));
        btnCincomil.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        btnCincomil.setForeground(new java.awt.Color(255, 255, 255));
        btnCincomil.setText("$5000");
        btnCincomil.setBorder(null);
        btnCincomil.setBorderPainted(false);
        btnCincomil.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCincomil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCincomilActionPerformed(evt);
            }
        });

        btnDosmil.setBackground(new java.awt.Color(0, 153, 204));
        btnDosmil.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        btnDosmil.setForeground(new java.awt.Color(255, 255, 255));
        btnDosmil.setText("$2000");
        btnDosmil.setBorder(null);
        btnDosmil.setBorderPainted(false);
        btnDosmil.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDosmil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDosmilActionPerformed(evt);
            }
        });

        btnDiezmil.setBackground(new java.awt.Color(255, 153, 51));
        btnDiezmil.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        btnDiezmil.setForeground(new java.awt.Color(255, 255, 255));
        btnDiezmil.setText("$10000");
        btnDiezmil.setBorder(null);
        btnDiezmil.setBorderPainted(false);
        btnDiezmil.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDiezmil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDiezmilActionPerformed(evt);
            }
        });

        btnCincuentamil.setBackground(new java.awt.Color(255, 153, 51));
        btnCincuentamil.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        btnCincuentamil.setForeground(new java.awt.Color(255, 255, 255));
        btnCincuentamil.setText("$50000");
        btnCincuentamil.setBorder(null);
        btnCincuentamil.setBorderPainted(false);
        btnCincuentamil.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCincuentamil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCincuentamilActionPerformed(evt);
            }
        });

        btnMil.setBackground(new java.awt.Color(0, 153, 204));
        btnMil.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        btnMil.setForeground(new java.awt.Color(255, 255, 255));
        btnMil.setText("$1000");
        btnMil.setBorder(null);
        btnMil.setBorderPainted(false);
        btnMil.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMilActionPerformed(evt);
            }
        });

        btnVeintemil.setBackground(new java.awt.Color(255, 153, 51));
        btnVeintemil.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        btnVeintemil.setForeground(new java.awt.Color(255, 255, 255));
        btnVeintemil.setText("$20000");
        btnVeintemil.setBorder(null);
        btnVeintemil.setBorderPainted(false);
        btnVeintemil.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVeintemil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVeintemilActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(255, 51, 0));
        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 153, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("Total a Pagar");

        jLabel3.setBackground(new java.awt.Color(255, 51, 0));
        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 153, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Otro Valor");

        txtOtroValor.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        txtOtroValor.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setBackground(new java.awt.Color(255, 51, 0));
        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 153, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Cambio");

        txtTotalPagar.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        txtTotalPagar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtTotalPagar.setEnabled(false);

        txtCambio.setEditable(false);
        txtCambio.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        txtCambio.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtCambio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCambioActionPerformed(evt);
            }
        });

        btnCalcular.setBackground(new java.awt.Color(212, 172, 13));
        btnCalcular.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        btnCalcular.setForeground(new java.awt.Color(51, 51, 51));
        btnCalcular.setText("Calcular");
        btnCalcular.setBorder(null);
        btnCalcular.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCalcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalcularActionPerformed(evt);
            }
        });

        btnCobrar.setBackground(new java.awt.Color(51, 153, 0));
        btnCobrar.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        btnCobrar.setForeground(new java.awt.Color(255, 255, 255));
        btnCobrar.setText("Cobrar");
        btnCobrar.setBorder(null);
        btnCobrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCobrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCobrarActionPerformed(evt);
            }
        });

        btnLimpiarCobro.setBackground(new java.awt.Color(0, 102, 153));
        btnLimpiarCobro.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        btnLimpiarCobro.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpiarCobro.setText("Limpiar Cobro");
        btnLimpiarCobro.setBorder(null);
        btnLimpiarCobro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLimpiarCobro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarCobroActionPerformed(evt);
            }
        });

        btnCancelarCobro.setBackground(new java.awt.Color(255, 51, 51));
        btnCancelarCobro.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        btnCancelarCobro.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelarCobro.setText("Cancelar Venta");
        btnCancelarCobro.setBorder(null);
        btnCancelarCobro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancelarCobro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarCobroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnLimpiarCobro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCalcular, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCincomil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnMil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnVeintemil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 13, Short.MAX_VALUE)))
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnDiezmil, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCincuentamil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtOtroValor)
                    .addComponent(txtTotalPagar)
                    .addComponent(btnDosmil, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCancelarCobro, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCobrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCambio))
                .addGap(23, 23, 23))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMil)
                    .addComponent(btnDosmil))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCincomil)
                    .addComponent(btnDiezmil))
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVeintemil)
                    .addComponent(btnCincuentamil))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtOtroValor))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(txtTotalPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(txtCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCalcular, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                    .addComponent(btnCobrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnLimpiarCobro, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelarCobro, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        txaTotales.setColumns(20);
        txaTotales.setRows(5);
        jScrollPane1.setViewportView(txaTotales);

        jLabel5.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Nr° Serie");
        jLabel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtNroSerie.setEditable(false);
        txtNroSerie.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNroSerie, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
            .addComponent(topPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(topPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(txtNroSerie, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void topPanelMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_topPanelMouseDragged
        Point point = MouseInfo.getPointerInfo().getLocation();
        setLocation(point.x - X, point.y - Y);
    }//GEN-LAST:event_topPanelMouseDragged

    private void topPanelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_topPanelMousePressed
        X = evt.getX();
        Y = evt.getY();
    }//GEN-LAST:event_topPanelMousePressed

    private void txtCambioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCambioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCambioActionPerformed

    private void btnCalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalcularActionPerformed
        pago = pago + Float.parseFloat(txtOtroValor.getText());
        txtCambio.setText("$" + (pago - totalPag));
        listapago = listapago + "\n$" + txtOtroValor.getText();
        txaTotales.setText(listapago);
    }//GEN-LAST:event_btnCalcularActionPerformed

    private void btnCobrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCobrarActionPerformed
        int resp = JOptionPane.showConfirmDialog(this, "Desea Realizar el Cobro??");
        if (resp == 0) {
            JOptionPane.showMessageDialog(this, "Venta Generada Exitosamente");
            dispose();      
        } else {
            JOptionPane.showMessageDialog(this, "Operacion Cancelada");
        }
    }//GEN-LAST:event_btnCobrarActionPerformed

    private void btnLimpiarCobroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarCobroActionPerformed
        pago = 0;
        txtOtroValor.setText("");
        txtCambio.setText("");
        listapago="";
    }//GEN-LAST:event_btnLimpiarCobroActionPerformed

    private void btnCancelarCobroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarCobroActionPerformed
        int resp = JOptionPane.showConfirmDialog(this, "Desea Cancelar la Venta?");
        if (resp == 0) {
            
            venDAO.CancelarVenta(txtNroSerie.getText());
            venDAO.CancelarDetalleVenta(txtNroSerie.getText());
            JOptionPane.showMessageDialog(this, "Venta Cancelada Exitosamente");
            
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Operacion Cancelada");
        }
    }//GEN-LAST:event_btnCancelarCobroActionPerformed

    private void btnMilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMilActionPerformed
        pago = pago + 1000f;
        txtCambio.setText("$" + (pago - totalPag));
        listapago = listapago + "\n$1000";
        txaTotales.setText(listapago);
    }//GEN-LAST:event_btnMilActionPerformed

    private void btnDosmilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDosmilActionPerformed
        pago = pago + 2000f;
        txtCambio.setText("$" + (pago - totalPag));
        listapago = listapago + "\n$2000";
        txaTotales.setText(listapago);
    }//GEN-LAST:event_btnDosmilActionPerformed

    private void btnCincomilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCincomilActionPerformed
        pago = pago + 5000f;
        txtCambio.setText("$" + (pago - totalPag));
        listapago = listapago + "\n$5000";
        txaTotales.setText(listapago);
    }//GEN-LAST:event_btnCincomilActionPerformed

    private void btnDiezmilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDiezmilActionPerformed
        pago = pago + 10000f;
        txtCambio.setText("$" + (pago - totalPag));
        listapago = listapago + "\n$10000";
        txaTotales.setText(listapago);
    }//GEN-LAST:event_btnDiezmilActionPerformed

    private void btnVeintemilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVeintemilActionPerformed
        pago = pago + 20000f;
        txtCambio.setText("$" + (pago - totalPag));
        listapago = listapago + "\n$20000";
        txaTotales.setText(listapago);
    }//GEN-LAST:event_btnVeintemilActionPerformed

    private void btnCincuentamilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCincuentamilActionPerformed
        pago = pago + 50000f;
        txtCambio.setText("$" + (pago - totalPag));
        listapago = listapago + "\n$50000";
        txaTotales.setText(listapago);
    }//GEN-LAST:event_btnCincuentamilActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CobroForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CobroForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CobroForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CobroForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CobroForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCalcular;
    private javax.swing.JButton btnCancelarCobro;
    private javax.swing.JButton btnCincomil;
    private javax.swing.JButton btnCincuentamil;
    private javax.swing.JButton btnCobrar;
    private javax.swing.JButton btnDiezmil;
    private javax.swing.JButton btnDosmil;
    private javax.swing.JButton btnLimpiarCobro;
    private javax.swing.JButton btnMil;
    private javax.swing.JButton btnVeintemil;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel topPanel;
    private javax.swing.JTextArea txaTotales;
    private javax.swing.JTextField txtCambio;
    private javax.swing.JTextField txtNroSerie;
    private javax.swing.JTextField txtOtroValor;
    private javax.swing.JTextField txtTotalPagar;
    // End of variables declaration//GEN-END:variables
}
