/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ithembaroh;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Sfiso
 */
public class frmPrincipalMember extends javax.swing.JFrame {

    /**
     * Creates new form frmPrincipalMember
     */
    public frmPrincipalMember() {
        initComponents();
    }
    
    String strPmSurname;
    String strPmFullnames;
    String strPmIdNum;
    String strPmDob;
    String strPmAddress;
    String strPmTel;
    String strPmEmail;
    String strPmCoverId;
    String BeneFName;
    String BeneLName;
    String BeneRela;
    String BeneID;
       
    private void mGetValues()   //Gets values from textfield
    {
         strPmSurname = txtSurname.getText().toUpperCase();
         strPmFullnames = txtFullNames.getText().toUpperCase();
         strPmIdNum = txtIDNumber.getText().toUpperCase();
         strPmDob = dccDateOfBirth.getText().toUpperCase();
         strPmAddress = txtAddress.getText().toUpperCase();
         strPmTel = txtTelephone.getText().toUpperCase();
         strPmEmail = txtEmail.getText().toUpperCase();
         strPmCoverId = txtCover.getText().toUpperCase();
    }
    
    private void mClearText() //clears textfields
    {
        DefaultTableModel model = (DefaultTableModel)tblBeneficiaryDetails.getModel();
        
        txtSurname.setText("");
        txtFullNames.setText("");
        txtIDNumber.setText("");      
        dccDateOfBirth.setText("");
        txtAddress.setText("");
        txtTelephone.setText("");
        txtEmail.setText("");
        txtCover.setText("");
  
        for(int i = 0; i < tblBeneficiaryDetails.getRowCount(); i++) //clears data i the table
        {
            for(int j = 0; j < tblBeneficiaryDetails.getColumnCount(); j++)
            {
                model.setValueAt(null, i, j);
            }
        }  
    }
    
    private void mSaveBeneficiariesDetails() //saves beneficiary data to the database
    {
        String URL = "jdbc:mysql://localhost:3306/burial_society_db";
        String User = "root";
        String Password = "WeWillMakeIt@Life";
              
        try
        {
            Connection conMySQLConnectionString = DriverManager.getConnection(URL, User, Password);
            conMySQLConnectionString.setAutoCommit(false);
            
            try(Statement myStatement = conMySQLConnectionString.createStatement())
            {
                String strInsert = "INSERT INTO principal_members (LName, FName, ID_num, DOB, Address, Tel, Email)"
                                 + " VALUES(" + "'" + strPmSurname + "'" + ", " + "'" + strPmFullnames + "'" + ", " + "'" +strPmIdNum + "'" 
                                 + ", " + "'" + strPmDob + "'" + ", " + "'" +strPmAddress + "'" + ", " + "'" + strPmTel + "'" + ", " + "'" +strPmEmail + "'" + ")";
                myStatement.addBatch(strInsert);
                
                for(int i = 0; i < tblBeneficiaryDetails.getRowCount(); i++)
                {
                    for(int j = 0; j < tblBeneficiaryDetails.getColumnCount(); j++)
                    {
                        BeneFName = tblBeneficiaryDetails.getValueAt(i, 0).toString().toUpperCase();
                        BeneLName = tblBeneficiaryDetails.getValueAt(i, 1).toString().toUpperCase();
                        BeneRela = tblBeneficiaryDetails.getValueAt(i, 2).toString().toUpperCase();
                        BeneID = tblBeneficiaryDetails.getValueAt(i, 3).toString().toUpperCase();    
                    }
                    strInsert = "INSERT INTO beneficiaries (PM_ID_Num, LName, FName, Relationship, IDNum)"
                                         + " VALUES("+ "'" + strPmIdNum + "'" + ", " + "'" + BeneLName + "'" + ", " + "'" + BeneFName + "'" + ", " + "'" + BeneRela + "'" + ", " + "'" + BeneID + "'" + ")";
                    myStatement.addBatch(strInsert);  
                }

                myStatement.executeBatch();
                conMySQLConnectionString.commit();
                conMySQLConnectionString.setSavepoint();
                conMySQLConnectionString.rollback();
                myStatement.close();
                
                JOptionPane.showMessageDialog(rootPane, "Data has been successfully saved");
                mClearText();
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(rootPane, e);
            }    
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(rootPane, e);
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

        pnlBackground = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        pnlSurname = new javax.swing.JPanel();
        lblSurname = new javax.swing.JLabel();
        txtSurname = new javax.swing.JTextField();
        pnlFullName = new javax.swing.JPanel();
        lblFullNames = new javax.swing.JLabel();
        txtFullNames = new javax.swing.JTextField();
        pnlIdNumber = new javax.swing.JPanel();
        lblIdNumber = new javax.swing.JLabel();
        txtIDNumber = new javax.swing.JTextField();
        pnlDateOfBirth = new javax.swing.JPanel();
        lblDateOfBirth = new javax.swing.JLabel();
        dccDateOfBirth = new datechooser.beans.DateChooserCombo();
        pnlAddress = new javax.swing.JPanel();
        lblAddress = new javax.swing.JLabel();
        txtAddress = new javax.swing.JTextField();
        pnlTelephone = new javax.swing.JPanel();
        lblTelephone = new javax.swing.JLabel();
        txtTelephone = new javax.swing.JTextField();
        pnlEmail = new javax.swing.JPanel();
        lblEmail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        pnlCover = new javax.swing.JPanel();
        lblCover = new javax.swing.JLabel();
        txtCover = new javax.swing.JTextField();
        lblTitle1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBeneficiaryDetails = new javax.swing.JTable();
        btnSave = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        mniLogoff = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlBackground.setBackground(new java.awt.Color(102, 153, 255));

        lblTitle.setFont(new java.awt.Font("SansSerif", 2, 36)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle.setText("Principal Member");

        pnlSurname.setBackground(new java.awt.Color(51, 102, 255));

        lblSurname.setFont(new java.awt.Font("Consolas", 0, 24)); // NOI18N
        lblSurname.setForeground(new java.awt.Color(255, 255, 255));
        lblSurname.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSurname.setText("Surname:");
        lblSurname.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lblSurname.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        txtSurname.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        txtSurname.setToolTipText("Enter your username");
        txtSurname.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        javax.swing.GroupLayout pnlSurnameLayout = new javax.swing.GroupLayout(pnlSurname);
        pnlSurname.setLayout(pnlSurnameLayout);
        pnlSurnameLayout.setHorizontalGroup(
            pnlSurnameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSurnameLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(lblSurname)
                .addGap(0, 0, 0)
                .addComponent(txtSurname, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE))
        );
        pnlSurnameLayout.setVerticalGroup(
            pnlSurnameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSurnameLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(lblSurname, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(txtSurname, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pnlFullName.setBackground(new java.awt.Color(51, 102, 255));

        lblFullNames.setFont(new java.awt.Font("Consolas", 0, 24)); // NOI18N
        lblFullNames.setForeground(new java.awt.Color(255, 255, 255));
        lblFullNames.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFullNames.setText("Full Names:");
        lblFullNames.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lblFullNames.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        txtFullNames.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        txtFullNames.setToolTipText("Enter your username");
        txtFullNames.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        javax.swing.GroupLayout pnlFullNameLayout = new javax.swing.GroupLayout(pnlFullName);
        pnlFullName.setLayout(pnlFullNameLayout);
        pnlFullNameLayout.setHorizontalGroup(
            pnlFullNameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFullNameLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(lblFullNames)
                .addGap(0, 0, 0)
                .addComponent(txtFullNames, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE))
        );
        pnlFullNameLayout.setVerticalGroup(
            pnlFullNameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFullNameLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(lblFullNames, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(txtFullNames, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pnlIdNumber.setBackground(new java.awt.Color(51, 102, 255));

        lblIdNumber.setFont(new java.awt.Font("Consolas", 0, 24)); // NOI18N
        lblIdNumber.setForeground(new java.awt.Color(255, 255, 255));
        lblIdNumber.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIdNumber.setText("ID Number:");
        lblIdNumber.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lblIdNumber.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        txtIDNumber.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        txtIDNumber.setToolTipText("Enter your username");
        txtIDNumber.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        javax.swing.GroupLayout pnlIdNumberLayout = new javax.swing.GroupLayout(pnlIdNumber);
        pnlIdNumber.setLayout(pnlIdNumberLayout);
        pnlIdNumberLayout.setHorizontalGroup(
            pnlIdNumberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlIdNumberLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(lblIdNumber)
                .addGap(0, 0, 0)
                .addComponent(txtIDNumber, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))
        );
        pnlIdNumberLayout.setVerticalGroup(
            pnlIdNumberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlIdNumberLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(lblIdNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(txtIDNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pnlDateOfBirth.setBackground(new java.awt.Color(51, 102, 255));

        lblDateOfBirth.setFont(new java.awt.Font("Consolas", 0, 24)); // NOI18N
        lblDateOfBirth.setForeground(new java.awt.Color(255, 255, 255));
        lblDateOfBirth.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDateOfBirth.setText("Date Of Birth:");
        lblDateOfBirth.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lblDateOfBirth.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        dccDateOfBirth.setCurrentView(new datechooser.view.appearance.AppearancesList("Swing",
            new datechooser.view.appearance.ViewAppearance("custom",
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    true,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 255),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(128, 128, 128),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.LabelPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.LabelPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(255, 0, 0),
                    false,
                    false,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                (datechooser.view.BackRenderer)null,
                false,
                true)));
    dccDateOfBirth.setCalendarBackground(new java.awt.Color(255, 255, 255));
    try {
        dccDateOfBirth.setDefaultPeriods(new datechooser.model.multiple.PeriodSet());
    } catch (datechooser.model.exeptions.IncompatibleDataExeption e1) {
        e1.printStackTrace();
    }
    dccDateOfBirth.setFieldFont(new java.awt.Font("Verdana", java.awt.Font.PLAIN, 14));
    dccDateOfBirth.setShowOneMonth(true);

    javax.swing.GroupLayout pnlDateOfBirthLayout = new javax.swing.GroupLayout(pnlDateOfBirth);
    pnlDateOfBirth.setLayout(pnlDateOfBirthLayout);
    pnlDateOfBirthLayout.setHorizontalGroup(
        pnlDateOfBirthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(pnlDateOfBirthLayout.createSequentialGroup()
            .addGap(3, 3, 3)
            .addComponent(lblDateOfBirth)
            .addGap(0, 0, 0)
            .addComponent(dccDateOfBirth, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE))
    );
    pnlDateOfBirthLayout.setVerticalGroup(
        pnlDateOfBirthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(pnlDateOfBirthLayout.createSequentialGroup()
            .addGap(0, 0, 0)
            .addGroup(pnlDateOfBirthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(dccDateOfBirth, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblDateOfBirth, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(0, 0, 0))
    );

    pnlAddress.setBackground(new java.awt.Color(51, 102, 255));

    lblAddress.setFont(new java.awt.Font("Consolas", 0, 24)); // NOI18N
    lblAddress.setForeground(new java.awt.Color(255, 255, 255));
    lblAddress.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    lblAddress.setText("Address:");
    lblAddress.setVerticalAlignment(javax.swing.SwingConstants.TOP);
    lblAddress.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

    txtAddress.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
    txtAddress.setToolTipText("Enter your username");
    txtAddress.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

    javax.swing.GroupLayout pnlAddressLayout = new javax.swing.GroupLayout(pnlAddress);
    pnlAddress.setLayout(pnlAddressLayout);
    pnlAddressLayout.setHorizontalGroup(
        pnlAddressLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(pnlAddressLayout.createSequentialGroup()
            .addGap(3, 3, 3)
            .addComponent(lblAddress)
            .addGap(0, 0, 0)
            .addComponent(txtAddress, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE))
    );
    pnlAddressLayout.setVerticalGroup(
        pnlAddressLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(pnlAddressLayout.createSequentialGroup()
            .addGap(1, 1, 1)
            .addComponent(lblAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
    );

    pnlTelephone.setBackground(new java.awt.Color(51, 102, 255));

    lblTelephone.setFont(new java.awt.Font("Consolas", 0, 24)); // NOI18N
    lblTelephone.setForeground(new java.awt.Color(255, 255, 255));
    lblTelephone.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    lblTelephone.setText("Telephone:");
    lblTelephone.setVerticalAlignment(javax.swing.SwingConstants.TOP);
    lblTelephone.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

    txtTelephone.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
    txtTelephone.setToolTipText("Enter your username");
    txtTelephone.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

    javax.swing.GroupLayout pnlTelephoneLayout = new javax.swing.GroupLayout(pnlTelephone);
    pnlTelephone.setLayout(pnlTelephoneLayout);
    pnlTelephoneLayout.setHorizontalGroup(
        pnlTelephoneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(pnlTelephoneLayout.createSequentialGroup()
            .addGap(3, 3, 3)
            .addComponent(lblTelephone)
            .addGap(0, 0, 0)
            .addComponent(txtTelephone, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))
    );
    pnlTelephoneLayout.setVerticalGroup(
        pnlTelephoneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(pnlTelephoneLayout.createSequentialGroup()
            .addGap(1, 1, 1)
            .addComponent(lblTelephone, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addComponent(txtTelephone, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
    );

    pnlEmail.setBackground(new java.awt.Color(51, 102, 255));

    lblEmail.setFont(new java.awt.Font("Consolas", 0, 24)); // NOI18N
    lblEmail.setForeground(new java.awt.Color(255, 255, 255));
    lblEmail.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    lblEmail.setText("Email:");
    lblEmail.setVerticalAlignment(javax.swing.SwingConstants.TOP);
    lblEmail.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

    txtEmail.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
    txtEmail.setToolTipText("Enter your username");
    txtEmail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

    javax.swing.GroupLayout pnlEmailLayout = new javax.swing.GroupLayout(pnlEmail);
    pnlEmail.setLayout(pnlEmailLayout);
    pnlEmailLayout.setHorizontalGroup(
        pnlEmailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(pnlEmailLayout.createSequentialGroup()
            .addGap(3, 3, 3)
            .addComponent(lblEmail)
            .addGap(0, 0, 0)
            .addComponent(txtEmail))
    );
    pnlEmailLayout.setVerticalGroup(
        pnlEmailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(pnlEmailLayout.createSequentialGroup()
            .addGap(1, 1, 1)
            .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
    );

    pnlCover.setBackground(new java.awt.Color(51, 102, 255));

    lblCover.setFont(new java.awt.Font("Consolas", 0, 24)); // NOI18N
    lblCover.setForeground(new java.awt.Color(255, 255, 255));
    lblCover.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    lblCover.setText("Cover:");
    lblCover.setVerticalAlignment(javax.swing.SwingConstants.TOP);
    lblCover.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

    txtCover.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
    txtCover.setToolTipText("Enter your username");
    txtCover.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

    javax.swing.GroupLayout pnlCoverLayout = new javax.swing.GroupLayout(pnlCover);
    pnlCover.setLayout(pnlCoverLayout);
    pnlCoverLayout.setHorizontalGroup(
        pnlCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(pnlCoverLayout.createSequentialGroup()
            .addGap(3, 3, 3)
            .addComponent(lblCover)
            .addGap(0, 0, 0)
            .addComponent(txtCover, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE))
    );
    pnlCoverLayout.setVerticalGroup(
        pnlCoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(pnlCoverLayout.createSequentialGroup()
            .addGap(1, 1, 1)
            .addComponent(lblCover, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addComponent(txtCover, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
    );

    lblTitle1.setFont(new java.awt.Font("SansSerif", 2, 24)); // NOI18N
    lblTitle1.setForeground(new java.awt.Color(255, 255, 255));
    lblTitle1.setText("Beneficiaries");

    tblBeneficiaryDetails.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N
    tblBeneficiaryDetails.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {
            {null, null, null, null},
            {null, null, null, null},
            {null, null, null, null}
        },
        new String [] {
            "Name", "Surname", "Relationship", "ID Number"
        }
    ) {
        Class[] types = new Class [] {
            java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
        };

        public Class getColumnClass(int columnIndex) {
            return types [columnIndex];
        }
    });
    tblBeneficiaryDetails.setColumnSelectionAllowed(true);
    tblBeneficiaryDetails.setRowHeight(25);
    tblBeneficiaryDetails.getTableHeader().setReorderingAllowed(false);
    jScrollPane1.setViewportView(tblBeneficiaryDetails);
    tblBeneficiaryDetails.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
    if (tblBeneficiaryDetails.getColumnModel().getColumnCount() > 0) {
        tblBeneficiaryDetails.getColumnModel().getColumn(0).setResizable(false);
        tblBeneficiaryDetails.getColumnModel().getColumn(1).setResizable(false);
        tblBeneficiaryDetails.getColumnModel().getColumn(2).setResizable(false);
        tblBeneficiaryDetails.getColumnModel().getColumn(3).setResizable(false);
    }

    btnSave.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
    btnSave.setForeground(new java.awt.Color(0, 51, 255));
    btnSave.setText("SAVE");
    btnSave.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(255, 255, 255), null));
    btnSave.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    btnSave.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnSaveActionPerformed(evt);
        }
    });

    btnClear.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
    btnClear.setForeground(new java.awt.Color(255, 51, 51));
    btnClear.setText("CLEAR");
    btnClear.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(255, 255, 255), null));
    btnClear.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    btnClear.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnClearActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout pnlBackgroundLayout = new javax.swing.GroupLayout(pnlBackground);
    pnlBackground.setLayout(pnlBackgroundLayout);
    pnlBackgroundLayout.setHorizontalGroup(
        pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(pnlBackgroundLayout.createSequentialGroup()
            .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlBackgroundLayout.createSequentialGroup()
                    .addGap(260, 260, 260)
                    .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(pnlBackgroundLayout.createSequentialGroup()
                    .addGap(318, 318, 318)
                    .addComponent(lblTitle1))
                .addGroup(pnlBackgroundLayout.createSequentialGroup()
                    .addGap(29, 29, 29)
                    .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(pnlIdNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(pnlSurname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(15, 15, 15)
                    .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(pnlFullName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(pnlDateOfBirth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(pnlBackgroundLayout.createSequentialGroup()
                    .addGap(29, 29, 29)
                    .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(pnlEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnlAddress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(15, 15, 15)
                    .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(pnlTelephone, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(pnlCover, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGap(0, 23, Short.MAX_VALUE))
        .addGroup(pnlBackgroundLayout.createSequentialGroup()
            .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlBackgroundLayout.createSequentialGroup()
                    .addGap(98, 98, 98)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 617, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(pnlBackgroundLayout.createSequentialGroup()
                    .addGap(179, 179, 179)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(281, 281, 281)
                    .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    pnlBackgroundLayout.setVerticalGroup(
        pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(pnlBackgroundLayout.createSequentialGroup()
            .addComponent(lblTitle)
            .addGap(29, 29, 29)
            .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlSurname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(pnlFullName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(15, 15, 15)
            .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlIdNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(pnlDateOfBirth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(15, 15, 15)
            .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(pnlTelephone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(15, 15, 15)
            .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(pnlCover, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(34, 34, 34)
            .addComponent(lblTitle1)
            .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlBackgroundLayout.createSequentialGroup()
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(24, 115, Short.MAX_VALUE))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBackgroundLayout.createSequentialGroup()
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(35, 35, 35))))
    );

    jMenu1.setText("File");

    mniLogoff.setText("Logoff");
    mniLogoff.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            mniLogoffActionPerformed(evt);
        }
    });
    jMenu1.add(mniLogoff);

    jMenuBar1.add(jMenu1);

    setJMenuBar(jMenuBar1);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(pnlBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(pnlBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );

    pack();
    setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void mniLogoffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniLogoffActionPerformed
        frmLogin frmL = new frmLogin();
        frmL.show();
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_mniLogoffActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
 // TODO add your handling code here:
 
    mGetValues();
     if(strPmSurname.isBlank())
     {
         JOptionPane.showMessageDialog(rootPane, "Please enter your surname", "MISSING DETAILS", JOptionPane.WARNING_MESSAGE);
         txtSurname.requestFocusInWindow();
        
     }
     else if(strPmFullnames.isBlank())
     {
         JOptionPane.showMessageDialog(rootPane, "Please enter your full names", "MISSING DETAILS", JOptionPane.WARNING_MESSAGE);
         txtFullNames.requestFocusInWindow();
     }
     else if(strPmIdNum.isBlank())
     {
         JOptionPane.showMessageDialog(rootPane, "Please enter your ID number", "MISSING DETAILS", JOptionPane.WARNING_MESSAGE);
        txtIDNumber.requestFocusInWindow();
     }
     else if(strPmDob.isBlank())
     {
         JOptionPane.showMessageDialog(rootPane, "Please select date of birth", "MISSING DETAILS", JOptionPane.WARNING_MESSAGE);
        dccDateOfBirth.requestFocusInWindow();
     }
     else if(strPmAddress.isBlank())
     {
         JOptionPane.showMessageDialog(rootPane, "Please enter your address", "MISSING DETAILS", JOptionPane.WARNING_MESSAGE);
        txtAddress.requestFocusInWindow();
     }
     else if(strPmTel.isBlank())
     {
         JOptionPane.showMessageDialog(rootPane, "Please enter your telephone Number", "MISSING DETAILS", JOptionPane.WARNING_MESSAGE);
        txtTelephone.requestFocusInWindow();
     }
     else if(strPmEmail.isBlank())
     {
        JOptionPane.showMessageDialog(rootPane, "Please enter your email", "MISSING DETAILS", JOptionPane.WARNING_MESSAGE);
        txtEmail.requestFocusInWindow();
     }
     else
     {
        mSaveBeneficiariesDetails(); 
     }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
    mClearText();        // TODO add your handling code here:
    }//GEN-LAST:event_btnClearActionPerformed

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
            java.util.logging.Logger.getLogger(frmPrincipalMember.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmPrincipalMember.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmPrincipalMember.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmPrincipalMember.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmPrincipalMember().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnSave;
    private datechooser.beans.DateChooserCombo dccDateOfBirth;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAddress;
    private javax.swing.JLabel lblCover;
    private javax.swing.JLabel lblDateOfBirth;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblFullNames;
    private javax.swing.JLabel lblIdNumber;
    private javax.swing.JLabel lblSurname;
    private javax.swing.JLabel lblTelephone;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblTitle1;
    private javax.swing.JMenuItem mniLogoff;
    private javax.swing.JPanel pnlAddress;
    private javax.swing.JPanel pnlBackground;
    private javax.swing.JPanel pnlCover;
    private javax.swing.JPanel pnlDateOfBirth;
    private javax.swing.JPanel pnlEmail;
    private javax.swing.JPanel pnlFullName;
    private javax.swing.JPanel pnlIdNumber;
    private javax.swing.JPanel pnlSurname;
    private javax.swing.JPanel pnlTelephone;
    private javax.swing.JTable tblBeneficiaryDetails;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtCover;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFullNames;
    private javax.swing.JTextField txtIDNumber;
    private javax.swing.JTextField txtSurname;
    private javax.swing.JTextField txtTelephone;
    // End of variables declaration//GEN-END:variables
}
