/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FeesManager;


import java.awt.Color;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import javax.swing.JFrame;


/**
 *
 * @author niten
 */
public class UpdateFeesDetails extends javax.swing.JFrame {

    /**
     * Creates new form AddFees
     */
    public UpdateFeesDetails() {
        initComponents();
        setIconImage();
        displayCashFirst(); 
        fillComboBox();
        
        
        int receiptNo = getReceiptNo();  
        txt_receiptno.setText(Integer.toString(receiptNo));
        setRecords();
        
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    
    }
    
     private void setIconImage() {
       setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/FeesManager/images/fmlogoR.png")));
    }
     
    public void displayCashFirst()
    {
       lbl_BankName.setVisible(false);
       lbl_DDno.setVisible(false);
       lbl_chequeNO.setVisible(false);
       
       txt_BankName.setVisible(false);
       txt_DDno.setVisible(false);
       txt_chequeNo.setVisible(false);
    }
    
    public boolean validation()
    {
       
        if (DateChooser.getDate()==null)
        {
        JOptionPane.showMessageDialog( this,"Please enter Date");
        return false;
        }
       
         if(Combo_payment_mode.getSelectedItem().toString().equalsIgnoreCase("cheque"))
         {
             if( txt_chequeNo.getText().equals(""))
        {
             JOptionPane.showMessageDialog( this,"Please enter Cheque number "); 
              return false;
        }
         if( txt_BankName.getText().equals(""))
        {
             JOptionPane.showMessageDialog( this,"Please enter Bank name "); 
              return false;
        }
    }
         if(Combo_payment_mode.getSelectedItem().toString().equalsIgnoreCase("dd"))
         {
             if( txt_DDno.getText().equals(""))
        {
             JOptionPane.showMessageDialog( this,"Please enter DD number "); 
              return false;
        }
         if( txt_BankName.getText().equals(""))
        {
             JOptionPane.showMessageDialog( this,"Please enter Bank name "); 
              return false;
        }
    }
         
           if(Combo_payment_mode.getSelectedItem().toString().equalsIgnoreCase("card"))
         {
       
         if( txt_BankName.getText().equals(""))
        {
             JOptionPane.showMessageDialog( this,"Please enter Bank name "); 
              return false;
        }
    }
            if(txt_RecievedFrom.getText().equals("")){
            
        
            JOptionPane.showMessageDialog( this,"Please enter username");
        return false;
        }
          
            if(txt_year1.getText().equals("")|| txt_year2.getText().equals("")|| txt_year1.getText().matches("[0-9]+") == false){
           
            JOptionPane.showMessageDialog( this,"Please enter year1 (in numbers) ");
        return false;
        }
             
            if( txt_year2.getText().equals("")|| txt_year2.getText().matches("[0-9]+") == false){
           
            JOptionPane.showMessageDialog( this,"Please enter year2 (in numbers) ");
        return false;
        }

            if(txt_rollNo.getText().equals("")|| txt_year1.getText().matches("[0-9]+") == false){
           
            JOptionPane.showMessageDialog( this,"Please enter Roll NO (in numbers)");
        return false;
        }
           if (txt_Amount.getText().equals("") )
        {
        JOptionPane.showMessageDialog( this,"Please enter Amount (in numbers) ");
        return false;
        }
        
        return true; 
    }   
     
    
    public void fillComboBox()
    {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
          Connection con=DriverManager.getConnection("jdbc:derby://localhost:1527/Fee_management","niten","niten");
          PreparedStatement pst=con.prepareStatement("select cname from course");
         ResultSet rs=pst.executeQuery();
          while(rs.next()){
              Combo_course.addItem(rs.getString("cname"));
          }
          
        } catch (Exception e) {
            
            e.printStackTrace();
           
        }
    }
    
    public int getReceiptNo(){
        int receiptNo = 0;
        try {
            Connection con = DBConnection.getConnection();
             PreparedStatement pst=con.prepareStatement("select max (reciept_no) from Fees_Details ");
           ResultSet rs= pst.executeQuery();
             
             if(rs.next() == true){
                 receiptNo = rs.getInt(1);
                 
             }
        } catch (Exception e) {  
            e.printStackTrace();
        }
        return receiptNo+1;
    }
    
    
    public String updateData(){
        
        String status  = "";
        
        int recieptNo = Integer.parseInt(txt_receiptno.getText());
        String studentName = txt_RecievedFrom.getText();
        String rollNo = txt_rollNo.getText();
        String paymentMode = Combo_payment_mode.getSelectedItem().toString();
        String chequeNO = txt_chequeNo.getText();
        String bankName = txt_BankName.getText();
        String ddNo = txt_DDno.getText();
        String courseName = txt_CourseName.getText();
        String gstin = txt_GSTno.getText();
        float totalAmount = Float.parseFloat(txt_total.getText());
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(DateChooser.getDate());
       
        float initialAmount = Float.parseFloat(txt_Amount.getText());
        float cgst = Float.parseFloat(txt_cgst.getText());
        float sgst = Float.parseFloat(txt_sgst.getText());
        String totalInWords = txt_totalInWords.getText();
        String remark = txt_Remark.getText();
        int year1 = Integer.parseInt(txt_year1.getText());
        int year2 = Integer.parseInt(txt_year2.getText());
        
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement pst = con.prepareStatement("update fees_details set student_name = ?,roll_no = ?,"
                    + "payment_mode = ?,cheque_no = ?,bank_name = ?,dd_no = ?,course_name = ?,gstin = ?,total_amount = ?,date = ?,"
                    + "amount=?,cgst = ?,sgst = ?,total_in_words = ?,remark = ?,year1 = ?,year2 = ? where reciept_no = ?");
           
        
      
        pst.setString(1, studentName);
        pst.setString(2, rollNo);
        pst.setString(3, paymentMode);
        pst.setString(4, chequeNO);
        pst.setString(5, bankName);
        pst.setString(6, ddNo);
        pst.setString(7, courseName);
        pst.setString(8, gstin);
        pst.setFloat(9, totalAmount);
        pst.setString(10,date );
        pst.setFloat(11, initialAmount);
        pst.setFloat(12, cgst);
        pst.setFloat(13, sgst);
        pst.setString(14,totalInWords);
        pst.setString(15, remark);
        pst.setInt(16,year1);
        pst.setInt(17,year2);
        pst.setInt(18,recieptNo);
        
       int rowCount = pst.executeUpdate();
       if (rowCount ==1 ){
           status = "success";
       }
       else{
           status = "failed";
       }
             
    } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        
        return status;
        
    }
    
    
    public void setRecords(){
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection con=DriverManager.getConnection("jdbc:derby://localhost:1527/Fee_management","niten","niten");
            PreparedStatement pst = con.prepareStatement("select * from fees_details order by reciept_no desc fetch first 1 row only");
            ResultSet rs =  pst.executeQuery();
            rs.next();
            
            txt_receiptno.setText(rs.getString("reciept_no"));
            Combo_payment_mode.setSelectedItem(rs.getString("payment_mode"));
            txt_chequeNo.setText(rs.getString("cheque_no"));
            txt_DDno.setText(rs.getString("dd_no"));
            txt_BankName.setText(rs.getString("bank_name"));
            txt_RecievedFrom.setText(rs.getString("student_name"));
            DateChooser.setDate(rs.getDate("date"));
            txt_year1.setText(rs.getString("year1"));
            txt_year2.setText(rs.getString("year2"));
            txt_rollNo.setText(rs.getString("roll_no"));
            Combo_course.setSelectedItem(rs.getString("course_name"));
            txt_Amount.setText(rs.getString("amount"));
            txt_cgst.setText(rs.getString("cgst"));
            txt_sgst.setText(rs.getString("sgst"));
            txt_total.setText(rs.getString("total_amount"));
            txt_totalInWords.setText(rs.getString("total_in_words"));
            txt_Remark.setText(rs.getString("remark"));
            
        
        
        
        
             } catch (Exception e) {
            e.printStackTrace();
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

        PanalParent = new javax.swing.JPanel();
        txt_GSTno = new javax.swing.JLabel();
        lbl_ModeOfPayment = new javax.swing.JLabel();
        lbl_ReceiptNo = new javax.swing.JLabel();
        lbl_DDno = new javax.swing.JLabel();
        lbl_Date = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lbl_chequeNO = new javax.swing.JLabel();
        lbl_BankName = new javax.swing.JLabel();
        txt_chequeNo = new javax.swing.JTextField();
        txt_receiptno = new javax.swing.JTextField();
        txt_BankName = new javax.swing.JTextField();
        DateChooser = new com.toedter.calendar.JDateChooser();
        Combo_payment_mode = new javax.swing.JComboBox<>();
        txt_DDno = new javax.swing.JTextField();
        PanelChild = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txt_year2 = new javax.swing.JTextField();
        lbl_rollNo = new javax.swing.JLabel();
        txt_CourseName = new javax.swing.JTextField();
        txt_year1 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        Combo_course = new javax.swing.JComboBox<>();
        lbl_RecievedFrom = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txt_RecievedFrom = new javax.swing.JTextField();
        txt_sgst = new javax.swing.JTextField();
        txt_total = new javax.swing.JTextField();
        txt_Amount = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        txt_cgst = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_Remark = new javax.swing.JTextArea();
        txt_totalInWords = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        btn_Print = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txt_rollNo = new javax.swing.JTextField();
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
        setTitle("Update Fees Details");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PanalParent.setBackground(new java.awt.Color(255, 204, 204));
        PanalParent.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_GSTno.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_GSTno.setText("22HVSJH55");
        PanalParent.add(txt_GSTno, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 90, 110, 30));

        lbl_ModeOfPayment.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_ModeOfPayment.setText("Mode of Paymant :");
        PanalParent.add(lbl_ModeOfPayment, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 170, 30));

        lbl_ReceiptNo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_ReceiptNo.setText("Receipt no : GPC - ");
        PanalParent.add(lbl_ReceiptNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 160, 30));

        lbl_DDno.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_DDno.setText("DD no : ");
        PanalParent.add(lbl_DDno, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, 110, 30));

        lbl_Date.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_Date.setText("Date : ");
        PanalParent.add(lbl_Date, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 50, 70, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("GSTIN : ");
        PanalParent.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 90, 110, 30));

        lbl_chequeNO.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_chequeNO.setText("Cheque no:");
        PanalParent.add(lbl_chequeNO, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, 110, 30));

        lbl_BankName.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_BankName.setText("Bank Name : ");
        PanalParent.add(lbl_BankName, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, 110, 30));

        txt_chequeNo.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        txt_chequeNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_chequeNoActionPerformed(evt);
            }
        });
        PanalParent.add(txt_chequeNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 130, 230, 30));

        txt_receiptno.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        txt_receiptno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_receiptnoActionPerformed(evt);
            }
        });
        PanalParent.add(txt_receiptno, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 50, 190, 30));

        txt_BankName.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        txt_BankName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_BankNameActionPerformed(evt);
            }
        });
        PanalParent.add(txt_BankName, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 170, 230, 30));
        PanalParent.add(DateChooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 50, 190, 30));

        Combo_payment_mode.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        Combo_payment_mode.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DD", "Cheque", "Cash", "Card" }));
        Combo_payment_mode.setSelectedIndex(2);
        Combo_payment_mode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Combo_payment_modeActionPerformed(evt);
            }
        });
        PanalParent.add(Combo_payment_mode, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 90, 190, 30));

        txt_DDno.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        txt_DDno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_DDnoActionPerformed(evt);
            }
        });
        PanalParent.add(txt_DDno, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 130, 230, -1));

        PanelChild.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jLabel2.setText("The following payment in the college office for the year");
        PanelChild.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 450, 30));

        txt_year2.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        txt_year2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_year2ActionPerformed(evt);
            }
        });
        PanelChild.add(txt_year2, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 50, 80, -1));

        lbl_rollNo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_rollNo.setText("Roll No :  ");
        PanelChild.add(lbl_rollNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 90, 80, 30));

        txt_CourseName.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        txt_CourseName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_CourseNameActionPerformed(evt);
            }
        });
        PanelChild.add(txt_CourseName, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 190, 360, -1));

        txt_year1.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        txt_year1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_year1ActionPerformed(evt);
            }
        });
        PanelChild.add(txt_year1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 50, 80, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setText("-");
        PanelChild.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 50, 10, 30));

        Combo_course.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        Combo_course.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Combo_courseActionPerformed(evt);
            }
        });
        PanelChild.add(Combo_course, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 90, 360, -1));

        lbl_RecievedFrom.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_RecievedFrom.setText("Recieved From : ");
        PanelChild.add(lbl_RecievedFrom, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 140, 30));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel13.setText("Receiver Signature");
        PanelChild.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 440, 170, 30));
        PanelChild.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 870, 20));
        PanelChild.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 870, 20));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel14.setText("Head");
        PanelChild.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 150, 50, 30));

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel15.setText("Remark : ");
        PanelChild.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 440, 140, 30));

        txt_RecievedFrom.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        txt_RecievedFrom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_RecievedFromActionPerformed(evt);
            }
        });
        PanelChild.add(txt_RecievedFrom, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 190, -1));

        txt_sgst.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        txt_sgst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_sgstActionPerformed(evt);
            }
        });
        PanelChild.add(txt_sgst, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 270, 190, -1));

        txt_total.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        txt_total.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_totalActionPerformed(evt);
            }
        });
        PanelChild.add(txt_total, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 330, 190, -1));

        txt_Amount.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        txt_Amount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_AmountActionPerformed(evt);
            }
        });
        PanelChild.add(txt_Amount, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 190, 190, -1));
        PanelChild.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 440, 250, 30));

        txt_cgst.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        txt_cgst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_cgstActionPerformed(evt);
            }
        });
        PanelChild.add(txt_cgst, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 230, 190, -1));

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel17.setText("Total in Words : ");
        PanelChild.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 370, 140, 30));
        PanelChild.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 310, 250, 30));

        txt_Remark.setColumns(20);
        txt_Remark.setRows(5);
        jScrollPane1.setViewportView(txt_Remark);

        PanelChild.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 430, 410, 70));

        txt_totalInWords.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        txt_totalInWords.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_totalInWordsActionPerformed(evt);
            }
        });
        PanelChild.add(txt_totalInWords, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 370, 420, 30));

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel18.setText("Amount(RS)");
        PanelChild.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 150, 110, 30));

        btn_Print.setBackground(new java.awt.Color(51, 153, 0));
        btn_Print.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        btn_Print.setForeground(new java.awt.Color(255, 255, 255));
        btn_Print.setText("Print");
        btn_Print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_PrintActionPerformed(evt);
            }
        });
        PanelChild.add(btn_Print, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 520, 100, 40));

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel19.setText("Course : ");
        PanelChild.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 90, 30));

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel20.setText("Sr No");
        PanelChild.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 50, 30));

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel21.setText("CGST 9%");
        PanelChild.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 230, 80, 30));

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel22.setText("SGST 9%");
        PanelChild.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 270, 90, 30));

        txt_rollNo.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        txt_rollNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_rollNoActionPerformed(evt);
            }
        });
        PanelChild.add(txt_rollNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 90, 100, -1));

        PanalParent.add(PanelChild, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 1110, 650));

        getContentPane().add(PanalParent, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 0, 1110, 870));

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
        PanalSideBar.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 330, -1));

        getContentPane().add(PanalSideBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 430, 870));

        setSize(new java.awt.Dimension(1556, 909));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_chequeNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_chequeNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_chequeNoActionPerformed

    private void txt_year2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_year2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_year2ActionPerformed

    private void txt_receiptnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_receiptnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_receiptnoActionPerformed

    private void txt_BankNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_BankNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_BankNameActionPerformed

    private void txt_CourseNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_CourseNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_CourseNameActionPerformed

    private void txt_year1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_year1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_year1ActionPerformed

    private void txt_RecievedFromActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_RecievedFromActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_RecievedFromActionPerformed

    private void txt_sgstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_sgstActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_sgstActionPerformed

    private void txt_totalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_totalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_totalActionPerformed

    private void txt_AmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_AmountActionPerformed
       Float amnt = Float.parseFloat(txt_Amount.getText());
      
       Float cgst = (float)(amnt * 0.09);
       Float sgst = (float)(amnt * 0.09); 
       
        txt_cgst.setText(cgst.toString());
        txt_sgst.setText(sgst.toString()); 
        
        float total = amnt + cgst + sgst;
        
        txt_total.setText(Float.toString(total));
        
       txt_totalInWords.setText(NumberToWordsConverter.convert((int) total) + " only ");
        

    }//GEN-LAST:event_txt_AmountActionPerformed

    private void txt_cgstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cgstActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_cgstActionPerformed

    private void txt_totalInWordsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_totalInWordsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_totalInWordsActionPerformed

    private void btn_PrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_PrintActionPerformed
     if  ( validation()== true)
     {
    String result = updateData();
        
        if(result.equals("success")){
            JOptionPane.showMessageDialog(this, "record updated successfully");
       
           Print_Receipt p =new Print_Receipt();
           p.setVisible(true);
           this.dispose();
        }
        else{
            JOptionPane.showMessageDialog(this, "record updation failed");
        }
        
     }
     
 
     
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_PrintActionPerformed

    private void txt_DDnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_DDnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_DDnoActionPerformed

    private void Combo_payment_modeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Combo_payment_modeActionPerformed
        if(Combo_payment_mode.getSelectedIndex()==0)
        {
            lbl_DDno.setVisible(true);
            txt_DDno.setVisible(true);
            lbl_chequeNO.setVisible(false);
            txt_chequeNo.setVisible(false);
            lbl_BankName.setVisible(true);
            txt_BankName.setVisible(true);
        }
        
        if(Combo_payment_mode.getSelectedIndex()==1)
        {
            lbl_DDno.setVisible(false);
            txt_DDno.setVisible(false);
            lbl_chequeNO.setVisible(true);
            txt_chequeNo.setVisible(true);
            lbl_BankName.setVisible(true);
            txt_BankName.setVisible(true);
        }
        
        if(Combo_payment_mode.getSelectedIndex()==2)
        {
            lbl_DDno.setVisible(false);
            txt_DDno.setVisible(false);
            lbl_chequeNO.setVisible(false);
            txt_chequeNo.setVisible(false);
            lbl_BankName.setVisible(false);
            txt_BankName.setVisible(false);
        }
        
          if(Combo_payment_mode.getSelectedIndex()==3)
        {
            lbl_DDno.setVisible(false);
            txt_DDno.setVisible(false);
            lbl_chequeNO.setVisible(false);
            txt_chequeNo.setVisible(false);
            lbl_BankName.setVisible(true);
            txt_BankName.setVisible(true);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_Combo_payment_modeActionPerformed

    private void Combo_courseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Combo_courseActionPerformed
      txt_CourseName.setText(Combo_course.getSelectedItem().toString());
        
// TODO add your handling code here:
    }//GEN-LAST:event_Combo_courseActionPerformed

    private void txt_rollNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_rollNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_rollNoActionPerformed

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
            java.util.logging.Logger.getLogger(UpdateFeesDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UpdateFeesDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UpdateFeesDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UpdateFeesDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UpdateFeesDetails().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> Combo_course;
    private javax.swing.JComboBox<String> Combo_payment_mode;
    private com.toedter.calendar.JDateChooser DateChooser;
    private javax.swing.JPanel PanalCourseList;
    private javax.swing.JPanel PanalEditCourses;
    private javax.swing.JPanel PanalHome;
    private javax.swing.JPanel PanalLogout;
    private javax.swing.JPanel PanalParent;
    private javax.swing.JPanel PanalSearchRecord;
    private javax.swing.JPanel PanalSideBar;
    private javax.swing.JPanel PanalViewAllRecords;
    private javax.swing.JPanel PanelChild;
    private javax.swing.JLabel btnCourseList;
    private javax.swing.JLabel btnEditCourses;
    private javax.swing.JLabel btnHome;
    private javax.swing.JLabel btnLogout;
    private javax.swing.JLabel btnSearchRecord;
    private javax.swing.JLabel btnViewAllRecords;
    private javax.swing.JButton btn_Print;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel lbl_BankName;
    private javax.swing.JLabel lbl_DDno;
    private javax.swing.JLabel lbl_Date;
    private javax.swing.JLabel lbl_ModeOfPayment;
    private javax.swing.JLabel lbl_ReceiptNo;
    private javax.swing.JLabel lbl_RecievedFrom;
    private javax.swing.JLabel lbl_chequeNO;
    private javax.swing.JLabel lbl_rollNo;
    private javax.swing.JTextField txt_Amount;
    private javax.swing.JTextField txt_BankName;
    private javax.swing.JTextField txt_CourseName;
    private javax.swing.JTextField txt_DDno;
    private javax.swing.JLabel txt_GSTno;
    private javax.swing.JTextField txt_RecievedFrom;
    private javax.swing.JTextArea txt_Remark;
    private javax.swing.JTextField txt_cgst;
    private javax.swing.JTextField txt_chequeNo;
    private javax.swing.JTextField txt_receiptno;
    private javax.swing.JTextField txt_rollNo;
    private javax.swing.JTextField txt_sgst;
    private javax.swing.JTextField txt_total;
    private javax.swing.JTextField txt_totalInWords;
    private javax.swing.JTextField txt_year1;
    private javax.swing.JTextField txt_year2;
    // End of variables declaration//GEN-END:variables
}
