package Vista;

import Controlador.VendedorDAO;
import Modelos.EntidadVendedor;
import java.awt.Color;
import javax.swing.JOptionPane;
import java.awt.MouseInfo;
import java.awt.Point;

public class LogingForm extends javax.swing.JFrame {

    VendedorDAO vdao = new VendedorDAO();
    EntidadVendedor ev = new EntidadVendedor();
    private int X;
    private int Y;

    public LogingForm() {
        initComponents();
        pnlTopPanel.setBackground(new java.awt.Color(0, 0, 0, 0));
        btnClose.setBackground(new java.awt.Color(0, 0, 0, 0));
        btnMinimize.setBackground(new java.awt.Color(0, 0, 0, 0));
        txtUser.setBackground(new java.awt.Color(0, 0, 0, 4));
        txtPassword.setBackground(new java.awt.Color(0, 0, 0, 1));
        txtUser.setText("Empleado_1");
        txtPassword.setText("123456");
        this.setLocationRelativeTo(null);
    }

    public void Validar() {
        String cedula = txtPassword.getText();
        String user = txtUser.getText();
        String role = "";
        String estado = "";
        int Id_Usuario;
        if (txtUser.getText().equals("") || txtPassword.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "No debe Dejar Campos Vacios");
            txtUser.requestFocus();
        } else {
            ev = vdao.ValidarVendedor(cedula, user);
            Id_Usuario = ev.getId_Vendedor();
            estado = ev.getEstado();
            role = ev.getRole();
            if (ev.getUser_2() != null && ev.getCedula() != null) {
                if (estado.equals("Inactivo") || role.equals("No asignado")) {
                    JOptionPane.showMessageDialog(this, "El usuario que intenta usar se encuentra inactivo o no ha sido asignado\n"
                            + "para mas informacion pongase en contacto con el administrdor del sistema");
                    JOptionPane.showMessageDialog(this, "role " + role + "\nEstado " + estado);
                } else {

                    PrincipalForm p = new PrincipalForm();
                    JOptionPane.showMessageDialog(this, "Login Exitoso \nAcceso como " + role);

                    p.setVisible(true);
                    p.asignarvendedor(cedula, role,Id_Usuario);
                    dispose();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Debe Ingresar Usuarios Validos");
                txtUser.requestFocus();
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Login = new javax.swing.JLabel();
        Key = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        txtUser = new javax.swing.JTextField();
        User = new javax.swing.JLabel();
        btnConect = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();
        btnMinimize = new javax.swing.JButton();
        pnlTopPanel = new javax.swing.JPanel();
        bgLogin = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Login.setFont(new java.awt.Font("Century Gothic", 0, 40)); // NOI18N
        Login.setForeground(new java.awt.Color(255, 255, 255));
        Login.setText("Bienvenidos al Login");
        jPanel1.add(Login, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 400, 60));

        Key.setFont(new java.awt.Font("Century Gothic", 0, 30)); // NOI18N
        Key.setForeground(new java.awt.Color(255, 255, 255));
        Key.setText("Clave");
        jPanel1.add(Key, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, -1, -1));

        txtPassword.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtPassword.setForeground(new java.awt.Color(255, 255, 255));
        txtPassword.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPassword.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        jPanel1.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 280, 240, 30));

        txtUser.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        txtUser.setForeground(new java.awt.Color(255, 255, 255));
        txtUser.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtUser.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        jPanel1.add(txtUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 180, 240, 30));

        User.setFont(new java.awt.Font("Century Gothic", 0, 30)); // NOI18N
        User.setForeground(new java.awt.Color(255, 255, 255));
        User.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        User.setText("usuario");
        jPanel1.add(User, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, -1, -1));

        btnConect.setBackground(new java.awt.Color(51, 153, 255));
        btnConect.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        btnConect.setForeground(new java.awt.Color(255, 255, 255));
        btnConect.setText("Conectar");
        btnConect.setBorder(null);
        btnConect.setBorderPainted(false);
        btnConect.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnConect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConectActionPerformed(evt);
            }
        });
        jPanel1.add(btnConect, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 370, 240, 50));

        btnClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/002-cancel.png"))); // NOI18N
        btnClose.setBorder(null);
        btnClose.setBorderPainted(false);
        btnClose.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });
        jPanel1.add(btnClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 20, 30, 30));

        btnMinimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/003-minimize.png"))); // NOI18N
        btnMinimize.setBorder(null);
        btnMinimize.setBorderPainted(false);
        btnMinimize.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMinimize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMinimizeActionPerformed(evt);
            }
        });
        jPanel1.add(btnMinimize, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 20, 30, 30));

        pnlTopPanel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                pnlTopPanelMouseDragged(evt);
            }
        });
        pnlTopPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlTopPanelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout pnlTopPanelLayout = new javax.swing.GroupLayout(pnlTopPanel);
        pnlTopPanel.setLayout(pnlTopPanelLayout);
        pnlTopPanelLayout.setHorizontalGroup(
            pnlTopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 310, Short.MAX_VALUE)
        );
        pnlTopPanelLayout.setVerticalGroup(
            pnlTopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        jPanel1.add(pnlTopPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 310, 60));

        bgLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/bgLogin_2.png"))); // NOI18N
        jPanel1.add(bgLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 451, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConectActionPerformed
        Validar();
    }//GEN-LAST:event_btnConectActionPerformed

    private void btnMinimizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinimizeActionPerformed
        this.setExtendedState(1);
    }//GEN-LAST:event_btnMinimizeActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnCloseActionPerformed

    private void pnlTopPanelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTopPanelMousePressed
        X = evt.getX();
        Y = evt.getY();
    }//GEN-LAST:event_pnlTopPanelMousePressed

    private void pnlTopPanelMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTopPanelMouseDragged
        Point point = MouseInfo.getPointerInfo().getLocation();
        setLocation(point.x - X, point.y - Y);

    }//GEN-LAST:event_pnlTopPanelMouseDragged

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
            java.util.logging.Logger.getLogger(LogingForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LogingForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LogingForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LogingForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LogingForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Key;
    private javax.swing.JLabel Login;
    private javax.swing.JLabel User;
    private javax.swing.JLabel bgLogin;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnConect;
    private javax.swing.JButton btnMinimize;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel pnlTopPanel;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
}
