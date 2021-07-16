
package Vista;

import Controlador.ClienteDAO;
import java.awt.MouseInfo;
import java.awt.Point;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
public class NuevoClienteForm extends javax.swing.JFrame {

    
    public NuevoClienteForm() {
        initComponents();
        this.setLocationRelativeTo(null);
        btnClose.setBackground(new java.awt.Color(0, 0, 0, 0));
        btnMinimize.setBackground(new java.awt.Color(0, 0, 0, 0));
    }
    
    ClienteDAO cdao = new ClienteDAO();
    
    int X;
    int Y;
    
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
            cdao.Crear(ob);
        } else {
            JOptionPane.showMessageDialog(this, "Operacion Cancelada");
        }
        this.setVisible(false);
        this.dispose();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnCrearC = new javax.swing.JButton();
        topPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnClose = new javax.swing.JButton();
        btnMinimize = new javax.swing.JButton();
        txtCedulaC = new javax.swing.JTextField();
        txtNombreC = new javax.swing.JTextField();
        txtDireccionC = new javax.swing.JTextField();
        cbxEstadoC = new javax.swing.JComboBox<>();
        lblCedulaC = new javax.swing.JLabel();
        lblNombreC = new javax.swing.JLabel();
        lbDireccionC = new javax.swing.JLabel();
        lblEstadoC = new javax.swing.JLabel();
        bgVentas1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        getContentPane().add(btnCrearC, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 210, 100, 30));

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

        javax.swing.GroupLayout topPanelLayout = new javax.swing.GroupLayout(topPanel);
        topPanel.setLayout(topPanelLayout);
        topPanelLayout.setHorizontalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, topPanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 143, Short.MAX_VALUE)
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
                    .addComponent(btnClose)
                    .addComponent(btnMinimize)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(topPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, 50));

        txtCedulaC.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCedulaC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(txtCedulaC, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 60, 160, 30));

        txtNombreC.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtNombreC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(txtNombreC, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 110, 160, 30));

        txtDireccionC.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtDireccionC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(txtDireccionC, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, 160, 30));

        cbxEstadoC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Activo", "Inactivo" }));
        getContentPane().add(cbxEstadoC, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 210, 160, 30));

        lblCedulaC.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblCedulaC.setForeground(new java.awt.Color(255, 255, 255));
        lblCedulaC.setText("Identificacion");
        getContentPane().add(lblCedulaC, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, -1));

        lblNombreC.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblNombreC.setForeground(new java.awt.Color(255, 255, 255));
        lblNombreC.setText("Nombre");
        getContentPane().add(lblNombreC, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, -1));

        lbDireccionC.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccionC.setForeground(new java.awt.Color(255, 255, 255));
        lbDireccionC.setText("Direccion");
        getContentPane().add(lbDireccionC, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, -1, -1));

        lblEstadoC.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblEstadoC.setForeground(new java.awt.Color(255, 255, 255));
        lblEstadoC.setText("Estado");
        getContentPane().add(lblEstadoC, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, -1, -1));

        bgVentas1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Clientesbg.png"))); // NOI18N
        getContentPane().add(bgVentas1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 500, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
       this.dispose();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnMinimizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinimizeActionPerformed
        this.setExtendedState(1);
    }//GEN-LAST:event_btnMinimizeActionPerformed

    private void topPanelMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_topPanelMouseDragged
        Point point = MouseInfo.getPointerInfo().getLocation();
        setLocation(point.x - X, point.y - Y);
    }//GEN-LAST:event_topPanelMouseDragged

    private void topPanelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_topPanelMousePressed
        X = evt.getX();
        Y = evt.getY();
    }//GEN-LAST:event_topPanelMousePressed

    private void btnCrearCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearCActionPerformed
        PrincipalForm pf = new PrincipalForm();
        CrearC();
        pf.LimpiarTablaC();
        pf.ListarC();      
    }//GEN-LAST:event_btnCrearCActionPerformed

   
    public static void main(String args[]) {
       
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NuevoClienteForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NuevoClienteForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NuevoClienteForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NuevoClienteForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NuevoClienteForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bgVentas1;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnCrearC;
    private javax.swing.JButton btnMinimize;
    private javax.swing.JComboBox<String> cbxEstadoC;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lbDireccionC;
    private javax.swing.JLabel lblCedulaC;
    private javax.swing.JLabel lblEstadoC;
    private javax.swing.JLabel lblNombreC;
    private javax.swing.JPanel topPanel;
    private javax.swing.JTextField txtCedulaC;
    private javax.swing.JTextField txtDireccionC;
    private javax.swing.JTextField txtNombreC;
    // End of variables declaration//GEN-END:variables
}
