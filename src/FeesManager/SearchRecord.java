/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FeesManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.awt.Color;
import java.awt.Toolkit;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author niten
 */
public class SearchRecord extends javax.swing.JFrame {

    /**
     * Creates new form SearchRecord
     */
    
    DefaultTableModel model = null;
    
    public SearchRecord() {
        initComponents();
        setRecordsToTable();
        setIconImage();

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }
    
    private void setIconImage() {
       setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/FeesManager/images/fmlogoR.png")));
    }
    
    public void setRecordsToTable(){
        try {
            Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement("select * from fees_details ");
             ResultSet rs =  pst.executeQuery();
              
             while(rs.next()){
                 
                 String receiptNo = rs.getString("reciept_no");
                 String rollNo = rs.getString("roll_no");
                 String studentName = rs.getString("student_name");
                 String paymentMode = rs.getString("payment_mode");
                 String courseName = rs.getString("course_name");
                 String totalAmount = rs.getString("total_amount");
                 String remark = rs.getString("remark");
                
                 
                 Object[] obj = {receiptNo,rollNo,studentName,courseName,paymentMode,totalAmount,remark};
                 
                 model = (DefaultTableModel)tbl_studentData.getModel();
                 model.addRow(obj);
                 
             } 
              
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public void search(String str){
        model = (DefaultTableModel)tbl_studentData.getModel();
       TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(model);
        tbl_studentData.setRowSorter(trs);
        trs.setRowFilter(RowFilter.regexFilter(str));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_studentData = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_Search = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        PanalSideBar = new javax.swing.JPanel();
        PanalHome = new javax.swing.JPanel();
        btnHome = new javax.swing.JLabel();
        PanalLogout = new javax.swing.JPanel();
        btnLogout = new javax.swing.JLabel();
        PanalSearchRecord = new javax.swing.JPanel();
        btnSearchRecord = new javax.swing.JLabel();
        PanalEditCourses = new javax.swing.JPanel();
        btnEditCourses = new javax.swing.JLabel();
        PanalCourseList = new javax.swing.JPanel();
        btnCourseList = new javax.swing.JLabel();
        PanalViewAllRecords = new javax.swing.JPanel();
        btnViewAllRecords = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Search Records");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbl_studentData.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tbl_studentData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Receipt No", "Roll No", "Student Name", "Course", "Payment Mode", "Total Amount", "Remark"
            }
        ));
        jScrollPane1.setViewportView(tbl_studentData);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 1020, 580));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 0, 0));
        jLabel1.setText("Search Records");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 20, 290, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 0, 0));
        jLabel2.setText(" Enter Search string : ");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 120, 200, -1));

        txt_Search.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_Search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                txt_SearchMouseReleased(evt);
            }
        });
        txt_Search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_SearchActionPerformed(evt);
            }
        });
        jPanel1.add(txt_Search, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 120, 370, -1));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 70, 370, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 0, 1110, 870));

        PanalSideBar.setBackground(new java.awt.Color(51, 51, 51));
        PanalSideBar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PanalHome.setBackground(new java.awt.Color(51, 51, 51));
        PanalHome.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(255, 255, 255), new java.awt.Color(0, 255, 153), null));
        PanalHome.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnHome.setFont(new java.awt.Font("Sylfaen", 0, 30)); // NOI18N
        btnHome.setForeground(new java.awt.Color(255, 255, 255));
        btnHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FeesManager/images/home.png"))); // NOI18N
        btnHome.setText(" HOME");
        btnHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHomeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnHomeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnHomeMouseExited(evt);
            }
        });
        PanalHome.add(btnHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 300, 70));

        PanalSideBar.add(PanalHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 330, 70));

        PanalLogout.setBackground(new java.awt.Color(51, 51, 51));
        PanalLogout.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.white, new java.awt.Color(0, 255, 153), null));
        PanalLogout.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnLogout.setFont(new java.awt.Font("Sylfaen", 0, 30)); // NOI18N
        btnLogout.setForeground(new java.awt.Color(255, 255, 255));
        btnLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FeesManager/images/logout.png"))); // NOI18N
        btnLogout.setText("Logout");
        btnLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLogoutMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLogoutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLogoutMouseExited(evt);
            }
        });
        PanalLogout.add(btnLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 300, 70));

        PanalSideBar.add(PanalLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 670, 330, 70));

        PanalSearchRecord.setBackground(new java.awt.Color(51, 51, 51));
        PanalSearchRecord.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.white, new java.awt.Color(0, 255, 153), null));
        PanalSearchRecord.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnSearchRecord.setFont(new java.awt.Font("Sylfaen", 0, 30)); // NOI18N
        btnSearchRecord.setForeground(new java.awt.Color(255, 255, 255));
        btnSearchRecord.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FeesManager/images/search2.png"))); // NOI18N
        btnSearchRecord.setText(" Search Record");
        btnSearchRecord.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSearchRecordMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSearchRecordMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSearchRecordMouseExited(evt);
            }
        });
        PanalSearchRecord.add(btnSearchRecord, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 300, 70));

        PanalSideBar.add(PanalSearchRecord, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, 330, 70));

        PanalEditCourses.setBackground(new java.awt.Color(51, 51, 51));
        PanalEditCourses.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.white, new java.awt.Color(0, 255, 153), null));
        PanalEditCourses.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnEditCourses.setFont(new java.awt.Font("Sylfaen", 0, 30)); // NOI18N
        btnEditCourses.setForeground(new java.awt.Color(255, 255, 255));
        btnEditCourses.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FeesManager/images/edit2.png"))); // NOI18N
        btnEditCourses.setText(" Edit Courses");
        btnEditCourses.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEditCoursesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEditCoursesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEditCoursesMouseExited(evt);
            }
        });
        PanalEditCourses.add(btnEditCourses, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 300, 70));

        PanalSideBar.add(PanalEditCourses, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 340, 330, 70));

        PanalCourseList.setBackground(new java.awt.Color(51, 51, 51));
        PanalCourseList.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.white, new java.awt.Color(0, 255, 153), null));
        PanalCourseList.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnCourseList.setFont(new java.awt.Font("Sylfaen", 0, 30)); // NOI18N
        btnCourseList.setForeground(new java.awt.Color(255, 255, 255));
        btnCourseList.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FeesManager/images/list.png"))); // NOI18N
        btnCourseList.setText("Course List");
        btnCourseList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCourseListMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCourseListMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCourseListMouseExited(evt);
            }
        });
        PanalCourseList.add(btnCourseList, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 300, 70));

        PanalSideBar.add(PanalCourseList, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 450, 330, 70));

        PanalViewAllRecords.setBackground(new java.awt.Color(51, 51, 51));
        PanalViewAllRecords.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.white, new java.awt.Color(0, 255, 153), null));
        PanalViewAllRecords.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnViewAllRecords.setFont(new java.awt.Font("Sylfaen", 0, 30)); // NOI18N
        btnViewAllRecords.setForeground(new java.awt.Color(255, 255, 255));
        btnViewAllRecords.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FeesManager/images/view all record.png"))); // NOI18N
        btnViewAllRecords.setText("View All Records");
        btnViewAllRecords.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnViewAllRecordsMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnViewAllRecordsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnViewAllRecordsMouseExited(evt);
            }
        });
        PanalViewAllRecords.add(btnViewAllRecords, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 300, 70));

        PanalSideBar.add(PanalViewAllRecords, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 560, 330, 70));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 204, 102));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FeesManager/images/fmlogoR.png"))); // NOI18N
        jLabel3.setText("  Fees Manager");
        PanalSideBar.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 330, 100));

        getContentPane().add(PanalSideBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 430, 870));

        setSize(new java.awt.Dimension(1555, 911));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_SearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_SearchActionPerformed

    private void txt_SearchMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_SearchMouseReleased
         String searchString = txt_Search.getText();
         search(searchString);
         
        
// TODO add your handling code here:
    }//GEN-LAST:event_txt_SearchMouseReleased

    private void btnHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHomeMouseClicked
        home home = new home();
        home.setVisible(true);
        this.dispose();

        // TODO add your handling code here:
    }//GEN-LAST:event_btnHomeMouseClicked

    private void btnHomeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHomeMouseEntered
        Color clr= new Color(0,102,0);
        PanalHome.setBackground(clr);   // TODO add your handling code here:
    }//GEN-LAST:event_btnHomeMouseEntered

    private void btnHomeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHomeMouseExited
        Color clr= new Color(51,51,51);
        PanalHome.setBackground(clr);  // TODO add your handling code here:
    }//GEN-LAST:event_btnHomeMouseExited

    private void btnLogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLogoutMouseClicked
        System.exit(0);
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLogoutMouseClicked

    private void btnLogoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLogoutMouseEntered
        Color clr= new Color(255,0,0);
        PanalLogout.setBackground(clr);  // TODO add your handling code here:
    }//GEN-LAST:event_btnLogoutMouseEntered

    private void btnLogoutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLogoutMouseExited

        Color clr= new Color(51,51,51);
        PanalLogout.setBackground(clr);
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLogoutMouseExited

    private void btnSearchRecordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchRecordMouseClicked
        SearchRecord searchString = new SearchRecord();
        searchString.setVisible(true);
        this.dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSearchRecordMouseClicked

    private void btnSearchRecordMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchRecordMouseEntered
        Color clr= new Color(0,102,0);
        PanalSearchRecord.setBackground(clr);
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSearchRecordMouseEntered

    private void btnSearchRecordMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchRecordMouseExited
        Color clr= new Color(51,51,51);
        PanalSearchRecord.setBackground(clr);
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSearchRecordMouseExited

    private void btnEditCoursesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditCoursesMouseClicked

        EditCourse EditCourse = new EditCourse();
        EditCourse.setVisible(true);
        this.dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditCoursesMouseClicked

    private void btnEditCoursesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditCoursesMouseEntered
        Color clr= new Color(0,102,0);
        PanalEditCourses.setBackground(clr);
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditCoursesMouseEntered

    private void btnEditCoursesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditCoursesMouseExited
        Color clr= new Color(51,51,51);
        PanalEditCourses.setBackground(clr);
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditCoursesMouseExited

    private void btnCourseListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCourseListMouseClicked
        View_Course course = new View_Course();
        course.setVisible(true);
        this.dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCourseListMouseClicked

    private void btnCourseListMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCourseListMouseEntered
        Color clr= new Color(0,102,0);
        PanalCourseList.setBackground(clr);
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCourseListMouseEntered

    private void btnCourseListMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCourseListMouseExited
        Color clr= new Color(51,51,51);
        PanalCourseList.setBackground(clr);
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCourseListMouseExited

    private void btnViewAllRecordsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnViewAllRecordsMouseClicked
        ViewRecords viewRecords = new ViewRecords();
        viewRecords.setVisible(true);
        this.dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnViewAllRecordsMouseClicked

    private void btnViewAllRecordsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnViewAllRecordsMouseEntered
        Color clr= new Color(0,102,0);
        PanalViewAllRecords.setBackground(clr);
        // TODO add your handling code here:
    }//GEN-LAST:event_btnViewAllRecordsMouseEntered

    private void btnViewAllRecordsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnViewAllRecordsMouseExited
        Color clr= new Color(51,51,51);
        PanalViewAllRecords.setBackground(clr);
        // TODO add your handling code here:
    }//GEN-LAST:event_btnViewAllRecordsMouseExited

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
            java.util.logging.Logger.getLogger(SearchRecord.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SearchRecord.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SearchRecord.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SearchRecord.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SearchRecord().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanalCourseList;
    private javax.swing.JPanel PanalEditCourses;
    private javax.swing.JPanel PanalHome;
    private javax.swing.JPanel PanalLogout;
    private javax.swing.JPanel PanalSearchRecord;
    private javax.swing.JPanel PanalSideBar;
    private javax.swing.JPanel PanalViewAllRecords;
    private javax.swing.JLabel btnCourseList;
    private javax.swing.JLabel btnEditCourses;
    private javax.swing.JLabel btnHome;
    private javax.swing.JLabel btnLogout;
    private javax.swing.JLabel btnSearchRecord;
    private javax.swing.JLabel btnViewAllRecords;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tbl_studentData;
    private javax.swing.JTextField txt_Search;
    // End of variables declaration//GEN-END:variables
}
