/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FeesManager;





import java.awt.Toolkit;
import java.sql.*;
import java.text.SimpleDateFormat;

import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author niten
 */
public class Signup_Page extends javax.swing.JFrame {

    /**
     * Creates new form Signup_Page
     */
       String fname, lname,uname,password,con_pass,contact_no;
        Date dob;
    int id=0;
    
    public Signup_Page() {
        initComponents();
        setIconImage();
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
       
    }
    
     private void setIconImage() {
       setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/FeesManager/images/fmlogoR.png")));
    }
    
    public int getId()
    {
        ResultSet rs=null;
        try{
             Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        Connection con=DriverManager.getConnection("jdbc:derby://localhost:1527/Fee_management","niten","niten");
       String sql="select max(id) from Signup";
       Statement st=con.createStatement();
        rs=st.executeQuery(sql);
            while (rs.next())
            {
                
                id=rs.getInt(1); 
                id++;
            }
        }
        catch(Exception e)
         {
              e.printStackTrace();
         }
        return id;
                    
    }
    
    boolean validation()
    {
     
        
        fname = txt_firstname.getText();
        lname = txt_lastname.getText();
        uname = txt_username.getText();
        password = txt_password.getText();
        con_pass=txt_con_password.getText();
        contact_no=txt_contactno.getText();
        dob=txt_dob.getDate();
        
        
        if(fname.equals(""))
        {
            JOptionPane.showMessageDialog(this, "please enter Firstname ");
            return false;
        }
        
            
        if(lname.equals(""))
        {
            JOptionPane.showMessageDialog(this, "please enter Lastname ");
            return false;
        }
        
            
        if(uname.equals(""))
        {
            JOptionPane.showMessageDialog(this, "please enter Usertname ");
            return false;
        }
        
            
        if(password.equals(""))
        {
            JOptionPane.showMessageDialog(this, "please enter Password");
            return false;
        }
        
            
        if(con_pass.equals(""))
        {
            JOptionPane.showMessageDialog(this, "please confirm the Password ");
            return false;
        }
        
            
        if(dob.equals(null))
        {
            JOptionPane.showMessageDialog(this, "please enter Date of Birth ");
            return false;
        }
        
        if(!password.equals(con_pass))
        {
            JOptionPane.showMessageDialog(this,"Password not matched");
            return false;
        }
           
     return true;
            
 }
    
    public void checkPassword()
    {
        password = txt_password.getText();
         if(password.length()<8)
        {
            lbl_password_error.setText("Password should be 8 digits");
            
        }
         else{
             lbl_password_error.setText("");
         }
    }
    
    public void checkContactNo()
    {
      contact_no=txt_contactno.getText();
      if(contact_no.length()==10)
      {
       lbl_contact_error.setText("");
      }
      else
      {
       lbl_contact_error.setText("Contact no should be of 10 digits");   
      }
    }
    
   void insertDetails()
   {
       SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
       String myDob=format.format(dob);
       try
       {
           Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        Connection con=DriverManager.getConnection("jdbc:derby://localhost:1527/Fee_management","niten","niten");
       String sql="insert into Signup values(?,?,?,?,?,?,?)";
        PreparedStatement stmt=con.prepareStatement(sql); 
        stmt.setInt(1,getId());
        stmt.setString(2,fname);
        stmt.setString(3,lname);
        stmt.setString(4,uname);
        stmt.setString(5,password);
        stmt.setString(6,myDob);
        stmt.setString(7,contact_no); 
        int i=stmt.executeUpdate();
        if(i>0)
        {
            JOptionPane.showMessageDialog(this, "record inserted");
        }
        else 
        {
             JOptionPane.showMessageDialog(this,"record not inserted");
        }
        
       } 
       catch(Exception e)
       {
           e.printStackTrace();
       }
   }
    

    
  //  
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_firstname = new javax.swing.JTextField();
        txt_contactno = new javax.swing.JTextField();
        txt_lastname = new javax.swing.JTextField();
        txt_password = new javax.swing.JPasswordField();
        txt_con_password = new javax.swing.JPasswordField();
        txt_dob = new com.toedter.calendar.JDateChooser();
        btn_signup = new javax.swing.JButton();
        btn_login = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txt_username = new javax.swing.JTextField();
        lbl_password_error = new javax.swing.JLabel();
        lbl_contact_error = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Signup");
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 45)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Signup");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 20, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 204, 102));
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FeesManager/images/fmlogoR.png"))); // NOI18N
        jLabel10.setText("  Fees Manager");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 330, 90));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 780, 110));

        jPanel4.setBackground(new java.awt.Color(255, 204, 204));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 0, 0));
        jLabel1.setText("Firstname : ");
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 33, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 0, 0));
        jLabel3.setText("Lastname : ");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 95, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 0, 0));
        jLabel4.setText("Password : ");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 204, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 0, 0));
        jLabel5.setText("Confirm Password : ");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 273, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 0, 0));
        jLabel6.setText("D.O.B : ");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 329, -1, 31));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 0, 0));
        jLabel7.setText("Contact no : ");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 393, -1, -1));

        txt_firstname.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txt_firstname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_firstnameActionPerformed(evt);
            }
        });
        jPanel4.add(txt_firstname, new org.netbeans.lib.awtextra.AbsoluteConstraints(259, 29, 233, -1));

        txt_contactno.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txt_contactno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_contactnoActionPerformed(evt);
            }
        });
        txt_contactno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_contactnoKeyReleased(evt);
            }
        });
        jPanel4.add(txt_contactno, new org.netbeans.lib.awtextra.AbsoluteConstraints(259, 389, 233, -1));

        txt_lastname.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txt_lastname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_lastnameActionPerformed(evt);
            }
        });
        jPanel4.add(txt_lastname, new org.netbeans.lib.awtextra.AbsoluteConstraints(259, 94, 233, -1));

        txt_password.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txt_password.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_passwordKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_passwordKeyReleased(evt);
            }
        });
        jPanel4.add(txt_password, new org.netbeans.lib.awtextra.AbsoluteConstraints(259, 203, 233, -1));

        txt_con_password.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jPanel4.add(txt_con_password, new org.netbeans.lib.awtextra.AbsoluteConstraints(259, 272, 233, -1));

        txt_dob.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jPanel4.add(txt_dob, new org.netbeans.lib.awtextra.AbsoluteConstraints(259, 329, 233, 31));

        btn_signup.setBackground(new java.awt.Color(51, 153, 0));
        btn_signup.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        btn_signup.setForeground(new java.awt.Color(255, 255, 255));
        btn_signup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FeesManager/images/signup.png"))); // NOI18N
        btn_signup.setText(" Signup");
        btn_signup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_signupActionPerformed(evt);
            }
        });
        jPanel4.add(btn_signup, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 460, 150, -1));

        btn_login.setBackground(new java.awt.Color(153, 153, 0));
        btn_login.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        btn_login.setForeground(new java.awt.Color(255, 255, 255));
        btn_login.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FeesManager/images/login.png"))); // NOI18N
        btn_login.setText(" Login");
        btn_login.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_loginMouseClicked(evt);
            }
        });
        btn_login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_loginActionPerformed(evt);
            }
        });
        jPanel4.add(btn_login, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 465, 120, 40));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 0, 0));
        jLabel8.setText("Username : ");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 151, -1, -1));

        txt_username.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txt_username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_usernameActionPerformed(evt);
            }
        });
        jPanel4.add(txt_username, new org.netbeans.lib.awtextra.AbsoluteConstraints(259, 147, 233, -1));

        lbl_password_error.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        lbl_password_error.setForeground(new java.awt.Color(255, 0, 0));
        jPanel4.add(lbl_password_error, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 203, 260, 31));

        lbl_contact_error.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        lbl_contact_error.setForeground(new java.awt.Color(255, 51, 51));
        jPanel4.add(lbl_contact_error, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 389, 260, 31));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 0, 0));
        jLabel9.setText("Already have account ");
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 480, 150, -1));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 780, 550));

        setSize(new java.awt.Dimension(780, 658));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_lastnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_lastnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_lastnameActionPerformed

    private void txt_contactnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_contactnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_contactnoActionPerformed

    private void txt_firstnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_firstnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_firstnameActionPerformed

    private void btn_loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_loginActionPerformed
               Login_Page login = new Login_Page();
       login.setVisible(true);
       this.dispose();
// TODO add your handling code here:
    }//GEN-LAST:event_btn_loginActionPerformed

    private void btn_signupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_signupActionPerformed
          if(  validation())
          {
               insertDetails();
          }
    }//GEN-LAST:event_btn_signupActionPerformed

    private void txt_usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_usernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_usernameActionPerformed

    private void txt_passwordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_passwordKeyPressed
        checkPassword();
    }//GEN-LAST:event_txt_passwordKeyPressed

    private void txt_passwordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_passwordKeyReleased
         checkPassword();
    }//GEN-LAST:event_txt_passwordKeyReleased

    private void txt_contactnoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_contactnoKeyReleased
        checkContactNo();
    }//GEN-LAST:event_txt_contactnoKeyReleased

    private void btn_loginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_loginMouseClicked

        // TODO add your handling code here:
    }//GEN-LAST:event_btn_loginMouseClicked

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
            java.util.logging.Logger.getLogger(Signup_Page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Signup_Page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Signup_Page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Signup_Page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Signup_Page().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_login;
    private javax.swing.JButton btn_signup;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel lbl_contact_error;
    private javax.swing.JLabel lbl_password_error;
    private javax.swing.JPasswordField txt_con_password;
    private javax.swing.JTextField txt_contactno;
    private com.toedter.calendar.JDateChooser txt_dob;
    private javax.swing.JTextField txt_firstname;
    private javax.swing.JTextField txt_lastname;
    private javax.swing.JPasswordField txt_password;
    private javax.swing.JTextField txt_username;
    // End of variables declaration//GEN-END:variables
}
