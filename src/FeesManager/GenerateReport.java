/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FeesManager;

import java.awt.Color;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author niten
 */
public class GenerateReport extends javax.swing.JFrame {

    
     DefaultTableModel model = null;
    /**
     * Creates new form GenerateReport
     */
    public GenerateReport() {
        initComponents();
        fillComboBox();
        
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
              
    }
    
     private void setIconImage() {
       setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/FeesManager/images/fmlogoR.png")));
    }
    
    public void fillComboBox()
    {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
          Connection con=DriverManager.getConnection("jdbc:derby://localhost:1527/Fee_management","niten","niten");
          
         PreparedStatement pst=con.prepareStatement("select cname from course");
         ResultSet rs=pst.executeQuery();
          while(rs.next()){
              Combo_CourseDetails.addItem(rs.getString("cname"));
          }
          
        } catch (Exception e) {
            
            e.printStackTrace();
           
        }
    }
    
    
      public void setRecordsToTable(){
        
          String cname =  Combo_CourseDetails.getSelectedItem().toString();
          
          SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
          String fromDate = dateFormat.format(DateChooser_From.getDate());
          String toDate = dateFormat.format(DateChooser_To.getDate());
          
          Float amountTotal = 0.0f;
          

          try {
             Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement("select * from fees_details where date between ? and ? and course_name = ? ");
             
             pst.setString(1,fromDate);
             pst.setString(2, toDate);
             pst.setString(3, cname);
                    
             
             ResultSet rs =  pst.executeQuery();
              
             while(rs.next()){
                 
                 String receiptNo = rs.getString("reciept_no");
                 String rollNo = rs.getString("roll_no");
                 String studentName = rs.getString("student_name");
                 String courseName = rs.getString("course_name");
                 float totalAmount = rs.getFloat( "total_amount");              
                 String remark = rs.getString("remark");
                
                 amountTotal = amountTotal + totalAmount;
                 
                 Object[] obj = {receiptNo,rollNo,studentName,courseName,totalAmount ,remark};
                 
                 model = (DefaultTableModel)tbl_Report.getModel();
                 model.addRow(obj);
                 
             } 
             
             lbl_Course.setText(cname);
             lbl_totalAmountCollection.setText(amountTotal.toString());
             lbl_AmountInWords.setText(NumberToWordsConverter.convert(amountTotal.intValue()));
             
             
        } catch (Exception e) {
            e.printStackTrace();
        }        
    }
      
       public void clearTable(){
        DefaultTableModel model = (DefaultTableModel)tbl_Report.getModel();
        model.setRowCount(1);
    }
       
       public void exportToExcel(){
         XSSFWorkbook wb = new XSSFWorkbook();
         XSSFSheet  ws = wb.createSheet();
         DefaultTableModel model = (DefaultTableModel)tbl_Report.getModel();
            
         TreeMap<String,Object[]>map = new TreeMap<>();
            
         map.put("0", new Object[]{model.getColumnName(0),model.getColumnName(1),model.getColumnName(2),
         model.getColumnName(3),model.getColumnName(4),model.getColumnName(5)});
           
         for (int i = 1; i < model.getRowCount(); i++){
                
         map.put(Integer.toString(i), new Object[]{model.getValueAt(i, 0), model.getValueAt(i, 1),model.getValueAt(i, 2),model.getValueAt(i, 3),
         model.getValueAt(i, 3),model.getValueAt(i, 4),model.getValueAt(i, 5)});
        }
         
         Set<String> id = map.keySet(); 
            
         XSSFRow fRow;
         int rowId = 0;
         
         for(String key : id){
             fRow = ws.createRow(rowId++);
             
             Object[] value = map.get(key);
             
             int cellId = 0;
             
             for (Object object : value){
                 XSSFCell cell = fRow.createCell(cellId++);
                 cell.setCellValue(object.toString());
             }
         }
        
           try (FileOutputStream fos = new FileOutputStream(new File(txt_filePath.getText())))
           {
                
               wb.write(fos);
               JOptionPane.showMessageDialog(this, "File Exported Successfully : " + txt_filePath.getText());
                
           } catch (Exception e) {
               e.getStackTrace();
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Combo_CourseDetails = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btn_Print = new javax.swing.JButton();
        btn_ExportToExcel = new javax.swing.JButton();
        txt_filePath = new javax.swing.JTextField();
        btn_Submit = new javax.swing.JButton();
        Btn_Browse = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_Report = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        lbl_AmountInWords = new javax.swing.JLabel();
        lbl_Course = new javax.swing.JLabel();
        lbl_totalAmountCollection = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        DateChooser_To = new com.toedter.calendar.JDateChooser();
        DateChooser_From = new com.toedter.calendar.JDateChooser();
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
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("View Report");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 0, 0));
        jLabel1.setText("To : ");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 90, 40, 20));

        Combo_CourseDetails.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        Combo_CourseDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Combo_CourseDetailsActionPerformed(evt);
            }
        });
        jPanel1.add(Combo_CourseDetails, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 30, 320, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 0, 0));
        jLabel3.setText("Select Date : ");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 120, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 0, 0));
        jLabel4.setText("From : ");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, 70, -1));

        btn_Print.setBackground(new java.awt.Color(153, 153, 0));
        btn_Print.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btn_Print.setForeground(new java.awt.Color(255, 255, 255));
        btn_Print.setText("Print");
        btn_Print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_PrintActionPerformed(evt);
            }
        });
        jPanel1.add(btn_Print, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 150, 100, 40));

        btn_ExportToExcel.setBackground(new java.awt.Color(0, 102, 0));
        btn_ExportToExcel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btn_ExportToExcel.setForeground(new java.awt.Color(255, 255, 255));
        btn_ExportToExcel.setText("Export to Excel");
        btn_ExportToExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ExportToExcelActionPerformed(evt);
            }
        });
        jPanel1.add(btn_ExportToExcel, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 210, 180, 40));

        txt_filePath.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_filePath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_filePathActionPerformed(evt);
            }
        });
        jPanel1.add(txt_filePath, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 220, 370, 30));

        btn_Submit.setBackground(new java.awt.Color(204, 0, 51));
        btn_Submit.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btn_Submit.setForeground(new java.awt.Color(255, 255, 255));
        btn_Submit.setText("Submit");
        btn_Submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SubmitActionPerformed(evt);
            }
        });
        jPanel1.add(btn_Submit, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 100, 40));

        Btn_Browse.setBackground(new java.awt.Color(0, 204, 204));
        Btn_Browse.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Btn_Browse.setForeground(new java.awt.Color(255, 255, 255));
        Btn_Browse.setText("Browse");
        Btn_Browse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_BrowseActionPerformed(evt);
            }
        });
        jPanel1.add(Btn_Browse, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 100, 40));

        jScrollPane1.setForeground(new java.awt.Color(102, 0, 0));

        tbl_Report.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tbl_Report.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null}
            },
            new String [] {
                "Receipt No", "Roll No", "Student Name", "Course Name", "Total Amount", "Remark"
            }
        ));
        jScrollPane1.setViewportView(tbl_Report);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 1060, 520));

        jPanel2.setBackground(new java.awt.Color(255, 153, 153));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_AmountInWords.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_AmountInWords.setForeground(new java.awt.Color(102, 0, 0));
        lbl_AmountInWords.setText("  ");
        jPanel2.add(lbl_AmountInWords, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 500, -1));

        lbl_Course.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_Course.setForeground(new java.awt.Color(102, 0, 0));
        lbl_Course.setText("  ");
        jPanel2.add(lbl_Course, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, 350, -1));

        lbl_totalAmountCollection.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_totalAmountCollection.setForeground(new java.awt.Color(102, 0, 0));
        lbl_totalAmountCollection.setText("  ");
        jPanel2.add(lbl_totalAmountCollection, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 60, 280, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 0, 0));
        jLabel9.setText("Total Amount Collected : ");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 220, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 0, 0));
        jLabel10.setText("Total Amount in Words : ");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 210, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 0, 0));
        jLabel8.setText("Select Course : ");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 150, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 10, 530, 170));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 0, 0));
        jLabel5.setText("Select Course : ");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 150, -1));
        jPanel1.add(DateChooser_To, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 90, 130, -1));
        jPanel1.add(DateChooser_From, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 90, 130, -1));

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

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 204, 102));
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FeesManager/images/fmlogoR.png"))); // NOI18N
        jLabel6.setText("  Fees Manager");
        PanalSideBar.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 330, -1));

        getContentPane().add(PanalSideBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 430, 870));

        setSize(new java.awt.Dimension(1555, 909));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void Combo_CourseDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Combo_CourseDetailsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Combo_CourseDetailsActionPerformed

    private void btn_PrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_PrintActionPerformed
         SimpleDateFormat Date_Format = new SimpleDateFormat("YYYY-MM-dd"); 
         String datefrom=  Date_Format.format(DateChooser_From.getDate());
         String dateto=  Date_Format.format(DateChooser_To.getDate());
       
         MessageFormat header=new MessageFormat("Report From "+datefrom+" To " +dateto);
         MessageFormat footer=new MessageFormat("page{0,number,integer}");
         try {
             tbl_Report.print(JTable.PrintMode.FIT_WIDTH, header, footer);
            
         } catch (Exception e) {
            e.getMessage();
         } 
         // TODO add your handling code here:
    }//GEN-LAST:event_btn_PrintActionPerformed

    private void btn_ExportToExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ExportToExcelActionPerformed
//            throws FileNotFoundException
          exportToExcel();
                    
// TODO add your handling code here:
    }//GEN-LAST:event_btn_ExportToExcelActionPerformed

    private void btn_SubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SubmitActionPerformed
        clearTable();
        setRecordsToTable();   
        
// TODO add your handling code here:
    }//GEN-LAST:event_btn_SubmitActionPerformed

    private void Btn_BrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_BrowseActionPerformed
         JFileChooser fileChooser = new JFileChooser();
         fileChooser.showOpenDialog(this);
        
         SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
         String date = dateFormat.format(new Date());
         
         try {
            File f = fileChooser.getSelectedFile();
            String path = f.getAbsolutePath();
             path = path + "_" + date + ".xlsx";
             txt_filePath.setText(path);
            
        } catch (Exception e) {
           e.getStackTrace();
        }
// TODO add your handling code here:
    }//GEN-LAST:event_Btn_BrowseActionPerformed

    private void txt_filePathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_filePathActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_filePathActionPerformed

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
            java.util.logging.Logger.getLogger(GenerateReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GenerateReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GenerateReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GenerateReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GenerateReport().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btn_Browse;
    private javax.swing.JComboBox<String> Combo_CourseDetails;
    private com.toedter.calendar.JDateChooser DateChooser_From;
    private com.toedter.calendar.JDateChooser DateChooser_To;
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
    private javax.swing.JButton btn_ExportToExcel;
    private javax.swing.JButton btn_Print;
    private javax.swing.JButton btn_Submit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_AmountInWords;
    private javax.swing.JLabel lbl_Course;
    private javax.swing.JLabel lbl_totalAmountCollection;
    private javax.swing.JTable tbl_Report;
    private javax.swing.JTextField txt_filePath;
    // End of variables declaration//GEN-END:variables
}
