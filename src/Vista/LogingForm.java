package Vista;

import Controlador.UsuarioDAO;
import Modelos.EntidadUsuario;
import java.awt.MouseInfo;
import java.awt.Point;

public class LogingForm extends javax.swing.JFrame {
    
    UsuarioDAO usrDAO           = new UsuarioDAO();
    EntidadUsuario entUsuario   = new EntidadUsuario();
    Mensaje message             = new Mensaje();
    
    private int X;
    private int Y;
    
    public LogingForm() {
        initComponents();
        setConfigurations();
    }
    
    public void login() {
        String passwordInput  = txtPassword.getText();
        String userInput = txtUser.getText();
        
        boolean invalidUser;
        boolean invalidStatus;
        
        if (this.checktemptyFields(passwordInput, userInput)) {
            
            message.emptyFields(this);
            txtUser.requestFocus();
        } else {

            //Get User Data from DB
            String[] userData   = getUserData(passwordInput, userInput);
            String idUsuario       = userData[0];
            String DNI          = userData[1];
            String estado       = userData[4];
            String usuario         = userData[5];
            String role         = userData[6];
            

            //Check User data
            invalidUser     = checkInvalidUser(DNI, usuario);
            invalidStatus   = checkInvalidStatus(estado, role);
            
            if (invalidUser) {
                
                message.invalidUSer(this);
                txtUser.requestFocus();
            } else if (invalidStatus) {
                
                message.invalidStatus(this, estado, role);
                txtUser.requestFocus();
            } else {
                
                Aplicacion App = new Aplicacion();
                message.LoginSuccesfully(this, role);
                
                App.setVisible(true);
                App.setUserRole(DNI, role, idUsuario);
                dispose();
            }
        }
    }
    
    private void setConfigurations() {
        
        pnlTopPanel.setBackground(new java.awt.Color(0, 0, 0, 0));
        btnClose.setBackground(new java.awt.Color(0, 0, 0, 0));
        btnMinimize.setBackground(new java.awt.Color(0, 0, 0, 0));
        txtUser.setBackground(new java.awt.Color(0, 0, 0, 4));
        txtPassword.setBackground(new java.awt.Color(0, 0, 0, 1));
        txtUser.setText("luis.rodriguez");
        txtPassword.setText("34567890C");
        this.setLocationRelativeTo(null);
    }
    
    private String[] getUserData(String passwordInput, String userInput) {
        
        entUsuario = usrDAO.getUsuarioData(passwordInput, userInput);
        
        String[] userData = {
            entUsuario.getIdUsuario() + "", //Posicion 0
            entUsuario.getDNI(),//Posicion 1
            entUsuario.getNombre(),//Posicion 2
            entUsuario.getTelefono(),//Posicion 3
            entUsuario.getStatus(), //Posicion 4
            entUsuario.getUsuario(), //Posicion 5
            entUsuario.getRole() //Posicion 6
        };
        
        return userData;
    }
    
    private boolean checktemptyFields(String password, String user) {
        
        boolean EmptyFields = false;
        if (password.isEmpty() || user.isEmpty()) {
            EmptyFields = true;
        }
        
        return EmptyFields;
    }
    
    private boolean checkInvalidUser(String DNI, String user) {
        boolean emptyUser = false;
        
        if (DNI == null || user == null) {
            emptyUser = true;
        }
        return emptyUser;
    }
    
    private boolean checkInvalidStatus(String status, String role) {
        
        boolean invalidStatus = false;

        //this Condional is created to handle cases when estado and role are null
        //cause equals() doesn't acept null values 
        if (status == null) {
            status = "Usuario no existente";
            role = "Role no existente";
        }
        
        if (status.equals("Inactivo") || role.equals("No asignado")) {
            invalidStatus = true;
        }
        
        if (status.equals("Usuario no existente")) {
            invalidStatus = true;
        }
        
        return invalidStatus;
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
        jPanel2 = new javax.swing.JPanel();
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

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 160, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 650, 160, 50));

        bgLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/bgLogin_2.png"))); // NOI18N
        jPanel1.add(bgLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 451, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConectActionPerformed
        login();
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

        /* create and display the form */
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel pnlTopPanel;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
}
