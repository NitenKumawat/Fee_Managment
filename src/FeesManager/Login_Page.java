/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FeesManager;

import java.awt.Toolkit;
import java.sql.*;
import javax.swing.JOptionPane;
import java.util.Date;
import javax.swing.JFrame;
/**
 *
 * @author niten
 */
public class Login_Page extends javax.swing.JFrame {

    /**
     * Creates new form Login_Page
     */
    public Login_Page() {
        initComponents();
        setIconImage();
        
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }
     private void setIconImage() {
       setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/FeesManager/images/fmlogoR.png")));
    }
    
 public void userVerification(String username,String password)
     {
    try
    {
    Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
     Connection con=DriverManager.getConnection("jdbc:derby://localhost:1527/Fee_management","niten","niten");
    String sql="select * from Signup where Username=? and password=?";
    PreparedStatement pst=con.prepareStatement(sql);

    pst.setString(1,username);
    pst.setString(2,password);
    ResultSet rs=pst.executeQuery();
    if(rs.next())
{
     JOptionPane.showMessageDialog(this,"Login successful");
    home home=new home();
    home.show();
    this.dispose();
}
else
{
    JOptionPane.showMessageDialog(this,"wrong Username or Password");
}
}
catch(Exception e)
{
  
}
}
 


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        btnSignup = new javax.swing.JButton();
        BtnExit = new javax.swing.JButton();
        btnLogin = new javax.swing.JButton();
        lblError = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 204, 204));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 0, 0));
        jLabel2.setText("Username : ");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(54, 52, 131, 26));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 0, 0));
        jLabel3.setText("Password : ");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(54, 118, 131, 26));

        txtUsername.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsernameActionPerformed(evt);
            }
        });
        jPanel2.add(txtUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 52, 205, -1));

        btnSignup.setBackground(new java.awt.Color(153, 153, 0));
        btnSignup.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnSignup.setForeground(new java.awt.Color(255, 255, 255));
        btnSignup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FeesManager/images/signup.png"))); // NOI18N
        btnSignup.setText("Signup");
        btnSignup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSignupActionPerformed(evt);
            }
        });
        jPanel2.add(btnSignup, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 233, 134, -1));

        BtnExit.setBackground(new java.awt.Color(255, 0, 0));
        BtnExit.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnExit.setForeground(new java.awt.Color(255, 255, 255));
        BtnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FeesManager/images/exit.png"))); // NOI18N
        BtnExit.setText("Exit");
        BtnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnExitActionPerformed(evt);
            }
        });
        jPanel2.add(BtnExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 233, -1, -1));

        btnLogin.setBackground(new java.awt.Color(51, 153, 0));
        btnLogin.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FeesManager/images/login.png"))); // NOI18N
        btnLogin.setText(" Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        jPanel2.add(btnLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(415, 233, -1, 41));

        lblError.setBackground(new java.awt.Color(255, 255, 255));
        lblError.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblError.setForeground(new java.awt.Color(255, 0, 51));
        jPanel2.add(lblError, new org.netbeans.lib.awtextra.AbsoluteConstraints(54, 164, 379, 22));

        txtPassword.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel2.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 120, 205, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 560, 310));

        jPanel4.setBackground(new java.awt.Color(51, 51, 51));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Login");
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 20, 110, 60));

        jLabel5.setBackground(new java.awt.Color(255, 0, 0));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FeesManager/images/admin.png"))); // NOI18N
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, 130, 70));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 204, 102));
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FeesManager/images/fmlogoR.png"))); // NOI18N
        jLabel6.setText("  Fees Manager");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 330, 100));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 560, 100));

        setSize(new java.awt.Dimension(560, 412));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsernameActionPerformed

    private void btnSignupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSignupActionPerformed
      Signup_Page Signup=new Signup_Page();
      Signup.show();
      this.dispose();
    }//GEN-LAST:event_btnSignupActionPerformed

    private void BtnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnExitActionPerformed
      System.exit(0);
    }//GEN-LAST:event_BtnExitActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        String username=txtUsername.getText();
        String password=txtPassword.getText();
        if (username.trim().equals("")||password.trim().equals(""))
        {
                lblError.setText("Please enter Username and Password");
            
        }
        else
        {
            userVerification(username,password); 
        }
    }//GEN-LAST:event_btnLoginActionPerformed

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
            java.util.logging.Logger.getLogger(Login_Page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login_Page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login_Page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login_Page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login_Page().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnExit;
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnSignup;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel lblError;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
