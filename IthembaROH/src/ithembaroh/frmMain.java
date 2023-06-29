/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ithembaroh;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Sfiso
 */
public class frmMain extends javax.swing.JFrame {

    /**
     * Creates new form frmMain
     */
    public frmMain() {
        initComponents();
        
        pnlDrawerOptions.setVisible(false);
    }
    
    frmEdit frmE = new frmEdit();
    frmBeneficiaries frmBene = new frmBeneficiaries();
    
    public boolean boolEdit = false;
    int intSelected;
    String strSearchText;
    public String strID = null; 
    DefaultTableModel model;
    JScrollPane jspBene = new JScrollPane();
    JLabel lblBene =  new JLabel();
    
    private void mSearchForClients()  //Searches for any data that matches the entered text.
    {
        String URL = "jdbc:mysql://localhost:3306/burial_society_db";
        String User = "root";
        String Password = "WeWillMakeIt@Life";
        
        Connection conMySQLConnectionString = null;
        Statement stStatement = null;
        ResultSet rsPrincipalMembers = null;
        
         model = (DefaultTableModel)tblDatabaseData.getModel();
              
        try
        {
            conMySQLConnectionString = DriverManager.getConnection(URL, User, Password);
            
            stStatement = conMySQLConnectionString.createStatement();
            
            String strSQLQuery = "SELECT * FROM principal_members "
                               + "WHERE " + cboFilter.getSelectedItem() + " LIKE ('%" + strSearchText + "%')" ;
            
            rsPrincipalMembers = stStatement.executeQuery(strSQLQuery);
            
            ResultSetMetaData rmst = rsPrincipalMembers.getMetaData();
            
            int intColumnCount = rmst.getColumnCount();
            
            
            Vector vRow;
            
            while(rsPrincipalMembers.next())
            {
                vRow = new Vector(intColumnCount);
                for(int i = 1; i <= intColumnCount; i++)
                {
                    vRow.add(rsPrincipalMembers.getString(i));   
                }
                
                model.addRow(vRow);
            }

            if(model.getRowCount() == 0)
            {
                JTable tblBeneficiaries = new JTable();
                jspBene.setViewportView(tblBeneficiaries);
                
               JOptionPane.showMessageDialog(rootPane, "No record containing " + "'" + strSearchText + "' as " + cboFilter.getSelectedItem()+ " was found."); 
            }
            else
            {
                mSearchClientBeneficiaries();
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error" + e);
        }
        finally
        {
            try
            {
                stStatement.close();
                rsPrincipalMembers.close();
                conMySQLConnectionString.close();
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null, "Error");
            }
        }
    }
    
    private void mSearchClientBeneficiaries()  //Searches for any beneficiary data that matches the entered text.
    {
        String URL = "jdbc:mysql://localhost:3306/burial_society_db";
        String User = "root";
        String Password = "WeWillMakeIt@Life";
        
        Connection conMySQLConnectionString = null;
        Statement stStatement = null;
        ResultSet rsSearchBene = null;
    
        try
        {
            conMySQLConnectionString = DriverManager.getConnection(URL, User, Password);
            
            stStatement = conMySQLConnectionString.createStatement();
            
            String strSQLQuery = "SELECT * FROM beneficiaries "
                               + "WHERE PM_ID_Num = '" + tblDatabaseData.getValueAt(0, 2).toString() + "'" ;
            
            rsSearchBene = stStatement.executeQuery(strSQLQuery);
            
            ResultSetMetaData rmst = rsSearchBene.getMetaData();
            
            int intColumnCount = rmst.getColumnCount();
            Vector vColumn = new Vector(intColumnCount);
            
            for(int i = 1; i <= intColumnCount; i++)
            {
                vColumn.add(rmst.getColumnName(i));
            }
            
            Vector vData = new Vector();
            Vector vRow;
            
            while(rsSearchBene.next())
            {
                vRow = new Vector(intColumnCount);
                for(int i = 1; i <= intColumnCount; i++)
                {
                    vRow.add(rsSearchBene.getString(i));   
                }
                
               vData.add(vRow);
            }
            
            JTable tblBeneficiaries = new JTable(vData, vColumn); 
            jspBene.setViewportView(tblBeneficiaries);
 
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error" + e);
        }
        finally
        {
            try
            {
                stStatement.close();
                rsSearchBene.close();
                conMySQLConnectionString.close();
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null, "Error");
            }
        }
    }
    
    private void mGetValuesFromTable()  //Gets values from the table
    {   
        frmBene.strPmLName = tblDatabaseData.getValueAt(0, 0).toString();
        frmBene.strPmFName = tblDatabaseData.getValueAt(0, 1).toString();
        frmBene.strPmID = tblDatabaseData.getValueAt(0, 2).toString();
        frmBene.strPmDOB = tblDatabaseData.getValueAt(0, 3).toString();
        frmBene.strPmAddress = tblDatabaseData.getValueAt(0, 4).toString();
        frmBene.strPmTel = tblDatabaseData.getValueAt(0, 5).toString();
        frmBene.strPmEmail = tblDatabaseData.getValueAt(0, 6).toString();
    }
    
    private void mSetValuesToUpperCase()    //Sets values to upper case
    {
        frmBene.strPmLName = frmBene.strPmLName.toUpperCase();
        frmBene.strPmFName = frmBene.strPmFName.toUpperCase();
        frmBene.strPmID = frmBene.strPmID.toUpperCase();  
        frmBene.strPmDOB = frmBene.strPmDOB.toUpperCase();
        frmBene.strPmAddress  = frmBene.strPmAddress.toUpperCase();
        frmBene.strPmTel = frmBene.strPmTel.toUpperCase();
        frmBene.strPmEmail = frmBene.strPmEmail.toUpperCase();
    }
    
    private void mClearTableData() //Removes all the data iin the table
    {   
        model = (DefaultTableModel)tblDatabaseData.getModel();
        
        for(int i = 0; i < tblDatabaseData.getRowCount(); i++)
        {
            for(int j = 0; j < tblDatabaseData.getColumnCount(); j++)
            {
                model.setValueAt(null, i, j);
            }
        } 
    }
    
    private void mRemoveRows() // removes all the rows in the table
    {
        model = (DefaultTableModel)tblDatabaseData.getModel();
        int intRows = model.getRowCount();
        
        if(intRows != 0)
        {
            for( int i = 0; i < intRows ; i++)
            {
                model.removeRow(0);
            }  
        }
    }
    
    private void mResetToDefaultTable() //removes the second table and restores the defaults table's size
    {
        pnlBackground.add(jspTable, new org.netbeans.lib.awtextra.AbsoluteConstraints(58, 120, 620, 270));// normal table
        pnlBackground.remove(jspBene);
        pnlBackground.remove(lblBene);
    }
    
    private void mAddRow() //Adds a row to the table
    {
        model = (DefaultTableModel)tblDatabaseData.getModel();
        model.addRow(new Object[] {});    
    }

    public void mLoadCreateOptions() //Sets up the interface for creating new data
    {
        btnCreate.setEnabled(false);
        btnEdit.setEnabled(true);
        btnViewAll.setEnabled(true);
        btnDelete.setEnabled(true);
        btnBack.setText("Cancel");
        btnRefresh.setText("Next");
        txtSearchText.setText("");
        cboFilter.setSelectedIndex(0);
        btnSearch.setEnabled(false);
        txtSearchText.setEnabled(false);
        cboFilter.setEnabled(false);
        lblTitle.setText("Add A New Client");
        mRemoveRows();
        mResetToDefaultTable();
        mAddRow();
        pnlDrawerOptions.setVisible(false); 
        
    }
    
    
    
    public void mLoadViewAllOptions()   //Sets up the interface for viewing data frpm database
    {
        btnCreate.setEnabled(true);
        btnEdit.setEnabled(true);
        btnViewAll.setEnabled(false);
        btnDelete.setEnabled(true);
        btnBack.setText("Back");
        btnRefresh.setText("Refresh");
        lblTitle.setText("View Client's Details");
        btnSearch.setEnabled(true);
        txtSearchText.setEnabled(true);
        cboFilter.setEnabled(true);
        txtSearchText.setText("");
        cboFilter.setSelectedIndex(0);
        mRemoveRows();
        
        lblBene.setText("Beneficiaries");
        lblBene.setFont(new java.awt.Font("SansSerif", 2, 18)); 
        lblBene.setForeground(new java.awt.Color(255, 255, 255));
        pnlBackground.add(jspTable, new org.netbeans.lib.awtextra.AbsoluteConstraints(58, 120, 610, 150)); //reduces size of the normal table so the beneficiaries table can fit on the interface
        pnlBackground.add(lblBene, new org.netbeans.lib.awtextra.AbsoluteConstraints(315, 268, -1, -1));
        
        mViewPrincipalMemebers();
        mViewBeneficiaries();
        pnlDrawerOptions.setVisible(false);
    }
    
    public void mLoadDeleteOptions()    //Sets up the interface for data to be deleted
    {
        btnCreate.setEnabled(true);
        btnEdit.setEnabled(true);
        btnViewAll.setEnabled(true);
        btnDelete.setEnabled(false);       
        btnRefresh.setText("Delete");
        btnBack.setText("Cancel");
        lblTitle.setText("Delete Client's Details");
        pnlDrawerOptions.setVisible(false);
        mRemoveRows();
        btnSearch.setEnabled(true);
        txtSearchText.setEnabled(true);
        cboFilter.setEnabled(true);
        txtSearchText.setText("");
        cboFilter.setSelectedIndex(0);
        mResetToDefaultTable();
        mViewPrincipalMemebers();
    }
    
    public void mLoadEditOptions() // Sets up the interface for data to be edited
    {
        btnCreate.setEnabled(true);
        btnEdit.setEnabled(false);
        btnViewAll.setEnabled(true);
        btnDelete.setEnabled(true);
        btnRefresh.setText("Edit");
        btnBack.setText("Cancel");
        lblTitle.setText("Edit");
        boolEdit = true;
        btnSearch.setEnabled(true);
        txtSearchText.setEnabled(true);
        cboFilter.setEnabled(true);
        txtSearchText.setText("");
        cboFilter.setSelectedIndex(0);
        pnlDrawerOptions.setVisible(false);
        mRemoveRows();
        mResetToDefaultTable();
        mViewPrincipalMemebers();
      
    }
    
    private void mDeleteClientFromJTable() //Removes the selected row from the table
    {
        model = (DefaultTableModel)tblDatabaseData.getModel();
            
        for(int i = 0; i < 1; i++)    
        {
            model.removeRow(intSelected);
        }
    }
    
    private void mDeleteClient()    //Deletes data from the database
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
                strID = tblDatabaseData.getValueAt(intSelected, 2).toString();

                String  strQuery = "DELETE FROM principal_members "
                                 + "WHERE ID_Num = " + "'" + strID + "'" ;  
                myStatement.addBatch(strQuery);
                 
                strQuery = "DELETE FROM beneficiaries "
                         + "WHERE PM_ID_Num = " + "'" + strID + "'" ;
                myStatement.addBatch(strQuery);

                myStatement.executeBatch();
                conMySQLConnectionString.commit();
                conMySQLConnectionString.setSavepoint();
                conMySQLConnectionString.rollback();
                myStatement.close();
                
                mDeleteClientFromJTable();
                
                JOptionPane.showMessageDialog(rootPane, "Data has been successfully Deleted");
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
    
    private void mMoveSelectedToEdit() //Moves the selected row of data to be edited
    {
        for(int i =0; i < 1; i++)    
        {
            for(int j = 0; j < tblDatabaseData.getColumnCount(); j++)
            {
                String data = tblDatabaseData.getValueAt(intSelected, j).toString();
                frmE.tblEdit.setValueAt(data, i, j);
            }
        }
        frmE.strOldID = tblDatabaseData.getValueAt(intSelected, 2).toString();
        frmE.show();
    }
    
    public void mViewPrincipalMemebers()    //Loads all the data in the principal members table in the database
    {
        String URL = "jdbc:mysql://localhost:3306/burial_society_db";
        String User = "root";
        String Password = "WeWillMakeIt@Life";
        
        Connection conMySQLConnectionString = null;
        Statement stStatement = null;
        ResultSet rsPrincipalMembers = null;
        
         model = (DefaultTableModel)tblDatabaseData.getModel();
              
        try
        {
            conMySQLConnectionString = DriverManager.getConnection(URL, User, Password);
            
            stStatement = conMySQLConnectionString.createStatement();
            
            String strSQLQuery = "SELECT * FROM principal_members";
            
            rsPrincipalMembers = stStatement.executeQuery(strSQLQuery);
            
            ResultSetMetaData rmst = rsPrincipalMembers.getMetaData();
            
            int intColumnCount = rmst.getColumnCount();

            Vector vRow;
            
            while(rsPrincipalMembers.next())
            {
                vRow = new Vector(intColumnCount);
                for(int i = 1; i <= intColumnCount; i++)
                {
                    vRow.add(rsPrincipalMembers.getString(i));   
                }
                
                model.addRow(vRow);
            }
             //Gets the beneficiaries data
            
            if(model.getRowCount() == 0) // checks if the searched record was found
            {
                JOptionPane.showMessageDialog(rootPane, "No data is currently saved on the database");  
            }    
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error" + e);
        }
        finally
        {
            try
            {
                stStatement.close();
                rsPrincipalMembers.close();
                conMySQLConnectionString.close();
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null, "Error");
            }
        }
    }
    
    
      
    private void mViewBeneficiaries()
    {
        String URL = "jdbc:mysql://localhost:3306/burial_society_db";
        String User = "root";
        String Password = "WeWillMakeIt@Life";
        
        Connection conMySQLConnectionString = null;
        Statement stStatement = null;
        ResultSet rsBeneficiaries = null;
             
        try
        {
            conMySQLConnectionString = DriverManager.getConnection(URL, User, Password);
            
            stStatement = conMySQLConnectionString.createStatement();
            
            String strSQLQuery = "SELECT * FROM beneficiaries";
            
            rsBeneficiaries = stStatement.executeQuery(strSQLQuery);
            
            ResultSetMetaData rmst = rsBeneficiaries.getMetaData();
            
            int intColumnCount = rmst.getColumnCount();
            
            Vector vColumn = new Vector(intColumnCount);
            for(int i = 1; i <= intColumnCount; i++)
            {
                vColumn.add(rmst.getColumnName(i));
            }
            Vector vData = new Vector();
            Vector vRow;
            
            while(rsBeneficiaries.next())
            {
                vRow = new Vector(intColumnCount);
                for(int i = 1; i <= intColumnCount; i++)
                {
                    vRow.add(rsBeneficiaries.getString(i));   
                }
                
                vData.add(vRow);
            }
            
            JTable tblBeneficiaries = new JTable(vData, vColumn);
            
            jspBene.setViewportView(tblBeneficiaries);
            
            pnlBackground.add(jspBene, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 290, 610, 100));
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error" + e);
        }
        finally
        {
            try
            {
                stStatement.close();
                rsBeneficiaries.close();
                conMySQLConnectionString.close();
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null, "Error");
            }
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
        pnlDrawerOptions = new javax.swing.JPanel();
        pnlLogo = new javax.swing.JPanel();
        lblIthemba = new javax.swing.JLabel();
        lblRayOfHope = new javax.swing.JLabel();
        pnlViewAll = new javax.swing.JPanel();
        btnViewAll = new javax.swing.JButton();
        pnlCreate = new javax.swing.JPanel();
        btnCreate = new javax.swing.JButton();
        pnlEdit = new javax.swing.JPanel();
        btnEdit = new javax.swing.JButton();
        pnlDelete = new javax.swing.JPanel();
        btnDelete = new javax.swing.JButton();
        pnlLogoff = new javax.swing.JPanel();
        btnLogoff = new javax.swing.JButton();
        pnlOpenDrawer = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        btnRefresh = new javax.swing.JButton();
        lblTitle = new javax.swing.JLabel();
        jspTable = new javax.swing.JScrollPane();
        tblDatabaseData = new javax.swing.JTable();
        btnBack = new javax.swing.JButton();
        cboFilter = new javax.swing.JComboBox<>();
        txtSearchText = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlBackground.setBackground(new java.awt.Color(102, 153, 255));
        pnlBackground.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlBackgroundMouseClicked(evt);
            }
        });
        pnlBackground.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlDrawerOptions.setBackground(new java.awt.Color(255, 204, 204));

        pnlLogo.setBackground(new java.awt.Color(255, 204, 204));

        lblIthemba.setFont(new java.awt.Font("SansSerif", 2, 36)); // NOI18N
        lblIthemba.setForeground(new java.awt.Color(255, 255, 255));
        lblIthemba.setText("Ithemba");

        lblRayOfHope.setBackground(new java.awt.Color(0, 0, 0));
        lblRayOfHope.setFont(new java.awt.Font("Brush Script MT", 0, 26)); // NOI18N
        lblRayOfHope.setForeground(new java.awt.Color(255, 255, 255));
        lblRayOfHope.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRayOfHope.setText("Ray Of Hope");
        lblRayOfHope.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lblRayOfHope.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout pnlLogoLayout = new javax.swing.GroupLayout(pnlLogo);
        pnlLogo.setLayout(pnlLogoLayout);
        pnlLogoLayout.setHorizontalGroup(
            pnlLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblIthemba, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(pnlLogoLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(lblRayOfHope))
        );
        pnlLogoLayout.setVerticalGroup(
            pnlLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblIthemba)
            .addGroup(pnlLogoLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(lblRayOfHope))
        );

        pnlViewAll.setBackground(new java.awt.Color(255, 153, 153));

        btnViewAll.setBackground(new java.awt.Color(255, 153, 153));
        btnViewAll.setFont(new java.awt.Font("Yu Gothic Medium", 0, 24)); // NOI18N
        btnViewAll.setForeground(new java.awt.Color(255, 255, 255));
        btnViewAll.setText("View All");
        btnViewAll.setBorder(null);
        btnViewAll.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btnViewAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewAllActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlViewAllLayout = new javax.swing.GroupLayout(pnlViewAll);
        pnlViewAll.setLayout(pnlViewAllLayout);
        pnlViewAllLayout.setHorizontalGroup(
            pnlViewAllLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnViewAll, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        pnlViewAllLayout.setVerticalGroup(
            pnlViewAllLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlViewAllLayout.createSequentialGroup()
                .addComponent(btnViewAll, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pnlCreate.setBackground(new java.awt.Color(255, 153, 153));

        btnCreate.setBackground(new java.awt.Color(255, 153, 153));
        btnCreate.setFont(new java.awt.Font("Yu Gothic Medium", 0, 24)); // NOI18N
        btnCreate.setForeground(new java.awt.Color(255, 255, 255));
        btnCreate.setText("Create");
        btnCreate.setBorder(null);
        btnCreate.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlCreateLayout = new javax.swing.GroupLayout(pnlCreate);
        pnlCreate.setLayout(pnlCreateLayout);
        pnlCreateLayout.setHorizontalGroup(
            pnlCreateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCreateLayout.createSequentialGroup()
                .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlCreateLayout.setVerticalGroup(
            pnlCreateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCreateLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlEdit.setBackground(new java.awt.Color(255, 153, 153));

        btnEdit.setBackground(new java.awt.Color(255, 153, 153));
        btnEdit.setFont(new java.awt.Font("Yu Gothic Medium", 0, 24)); // NOI18N
        btnEdit.setForeground(new java.awt.Color(255, 255, 255));
        btnEdit.setText("Edit");
        btnEdit.setBorder(null);
        btnEdit.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlEditLayout = new javax.swing.GroupLayout(pnlEdit);
        pnlEdit.setLayout(pnlEditLayout);
        pnlEditLayout.setHorizontalGroup(
            pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEditLayout.createSequentialGroup()
                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlEditLayout.setVerticalGroup(
            pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlEditLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlDelete.setBackground(new java.awt.Color(255, 153, 153));

        btnDelete.setBackground(new java.awt.Color(255, 153, 153));
        btnDelete.setFont(new java.awt.Font("Yu Gothic Medium", 0, 24)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setText("Delete");
        btnDelete.setBorder(null);
        btnDelete.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlDeleteLayout = new javax.swing.GroupLayout(pnlDelete);
        pnlDelete.setLayout(pnlDeleteLayout);
        pnlDeleteLayout.setHorizontalGroup(
            pnlDeleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDeleteLayout.createSequentialGroup()
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlDeleteLayout.setVerticalGroup(
            pnlDeleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 29, Short.MAX_VALUE)
        );

        pnlLogoff.setBackground(new java.awt.Color(255, 153, 153));
        pnlLogoff.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlLogoffMouseClicked(evt);
            }
        });

        btnLogoff.setBackground(new java.awt.Color(255, 153, 153));
        btnLogoff.setFont(new java.awt.Font("Yu Gothic Medium", 0, 24)); // NOI18N
        btnLogoff.setForeground(new java.awt.Color(255, 255, 255));
        btnLogoff.setText("Logoff");
        btnLogoff.setBorder(null);
        btnLogoff.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btnLogoff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoffActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlLogoffLayout = new javax.swing.GroupLayout(pnlLogoff);
        pnlLogoff.setLayout(pnlLogoffLayout);
        pnlLogoffLayout.setHorizontalGroup(
            pnlLogoffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLogoffLayout.createSequentialGroup()
                .addComponent(btnLogoff, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlLogoffLayout.setVerticalGroup(
            pnlLogoffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLogoffLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnLogoff, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout pnlDrawerOptionsLayout = new javax.swing.GroupLayout(pnlDrawerOptions);
        pnlDrawerOptions.setLayout(pnlDrawerOptionsLayout);
        pnlDrawerOptionsLayout.setHorizontalGroup(
            pnlDrawerOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDrawerOptionsLayout.createSequentialGroup()
                .addGroup(pnlDrawerOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlLogoff, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlCreate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlViewAll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pnlDrawerOptionsLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(pnlLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );
        pnlDrawerOptionsLayout.setVerticalGroup(
            pnlDrawerOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDrawerOptionsLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(pnlLogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(pnlViewAll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(pnlCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(pnlEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(pnlDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(pnlLogoff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 133, Short.MAX_VALUE))
        );

        pnlBackground.add(pnlDrawerOptions, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 230, 480));

        pnlOpenDrawer.setBackground(new java.awt.Color(102, 153, 255));
        pnlOpenDrawer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlOpenDrawerMouseClicked(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        jPanel5.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlOpenDrawerLayout = new javax.swing.GroupLayout(pnlOpenDrawer);
        pnlOpenDrawer.setLayout(pnlOpenDrawerLayout);
        pnlOpenDrawerLayout.setHorizontalGroup(
            pnlOpenDrawerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOpenDrawerLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(pnlOpenDrawerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        pnlOpenDrawerLayout.setVerticalGroup(
            pnlOpenDrawerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOpenDrawerLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlBackground.add(pnlOpenDrawer, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        btnRefresh.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        btnRefresh.setForeground(new java.awt.Color(0, 51, 255));
        btnRefresh.setText("Refresh");
        btnRefresh.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(255, 255, 255), null));
        btnRefresh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });
        pnlBackground.add(btnRefresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 420, 80, 32));

        lblTitle.setFont(new java.awt.Font("SansSerif", 2, 36)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("Dashboard");
        pnlBackground.add(lblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 730, -1));

        jspTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jspTableMouseClicked(evt);
            }
        });

        tblDatabaseData.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        tblDatabaseData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Surname", "First Names", "ID Number", "Date Of Birth", "Address", "Telephone", "Email"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, false, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDatabaseData.setRowHeight(20);
        tblDatabaseData.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblDatabaseData.getTableHeader().setReorderingAllowed(false);
        jspTable.setViewportView(tblDatabaseData);
        if (tblDatabaseData.getColumnModel().getColumnCount() > 0) {
            tblDatabaseData.getColumnModel().getColumn(0).setResizable(false);
            tblDatabaseData.getColumnModel().getColumn(1).setResizable(false);
            tblDatabaseData.getColumnModel().getColumn(2).setResizable(false);
            tblDatabaseData.getColumnModel().getColumn(3).setResizable(false);
            tblDatabaseData.getColumnModel().getColumn(4).setResizable(false);
            tblDatabaseData.getColumnModel().getColumn(5).setResizable(false);
            tblDatabaseData.getColumnModel().getColumn(6).setResizable(false);
        }

        pnlBackground.add(jspTable, new org.netbeans.lib.awtextra.AbsoluteConstraints(58, 120, 620, 270));

        btnBack.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        btnBack.setForeground(new java.awt.Color(255, 51, 51));
        btnBack.setText("< Back");
        btnBack.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(255, 255, 255), null));
        btnBack.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBack.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBack.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        pnlBackground.add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 420, 80, 32));

        cboFilter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "LName", "FName", "ID_Num" }));
        pnlBackground.add(cboFilter, new org.netbeans.lib.awtextra.AbsoluteConstraints(631, 90, 45, 20));

        txtSearchText.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        pnlBackground.add(txtSearchText, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 90, 140, 20));

        btnSearch.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        btnSearch.setForeground(new java.awt.Color(51, 51, 255));
        btnSearch.setText("Find");
        btnSearch.setMargin(new java.awt.Insets(2, 5, 2, 5));
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });
        pnlBackground.add(btnSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(439, 90, 50, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBackground, javax.swing.GroupLayout.PREFERRED_SIZE, 729, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void pnlOpenDrawerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlOpenDrawerMouseClicked
        pnlDrawerOptions.setVisible(true);  //views the menu drawer.       // TODO add your handling code here:
    }//GEN-LAST:event_pnlOpenDrawerMouseClicked

    private void pnlBackgroundMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlBackgroundMouseClicked
    if(pnlDrawerOptions.isVisible())    // if the menu drawer is visible it will be hidden
    {
        pnlDrawerOptions.setVisible(false);
    }        // TODO add your handling code here:
    }//GEN-LAST:event_pnlBackgroundMouseClicked

    private void pnlLogoffMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlLogoffMouseClicked
    // TODO add your handling code here:
    }//GEN-LAST:event_pnlLogoffMouseClicked

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
    frmAdminDashboard frmAD = new frmAdminDashboard(); //returns back to the default dashboard
    frmAD.show();
    this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
intSelected = tblDatabaseData.getSelectedRow();
        
if(btnRefresh.getText() .equals("Edit"))
{   
    if(intSelected != -1)
    {
        mMoveSelectedToEdit();
    }
    else
    {
        JOptionPane.showMessageDialog(rootPane, "Please select a row");  
    }   
}
else if(btnRefresh.getText() .equals("Delete"))
{       
    if(intSelected != -1)
    {
        mDeleteClient();   
    }
    else
    {
        JOptionPane.showMessageDialog(rootPane, "Please select a row");
    }
}
else if(btnRefresh.getText() .equals("Refresh"))
{
    mLoadViewAllOptions();
}
else if(btnRefresh.getText() .equals("Next"))
{
    try
    {
       mGetValuesFromTable();
       mSetValuesToUpperCase();
       frmBene.show();
       mClearTableData(); 
    }
    catch(Exception e)
    {
        JOptionPane.showMessageDialog(rootPane,e);
    }
}
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
    if(btnDelete.isEnabled())
    {
        mLoadDeleteOptions();
    }        // TODO add your handling code here:
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnViewAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewAllActionPerformed
    if(btnViewAll.isEnabled())
    {
        mLoadViewAllOptions();
    }        // TODO add your handling code here:
    }//GEN-LAST:event_btnViewAllActionPerformed

    private void btnLogoffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoffActionPerformed
    frmLogin frmL = new frmLogin();
    frmL.show();
    this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_btnLogoffActionPerformed

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
    if(btnCreate.isEnabled())
    {
       mLoadCreateOptions();
    }        // TODO add your handling code here:
    }//GEN-LAST:event_btnCreateActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
    if(btnEdit.isEnabled())
    {
       mLoadEditOptions();
    }        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditActionPerformed

    private void jspTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jspTableMouseClicked
if(pnlDrawerOptions.isVisible())
{
    pnlDrawerOptions.setVisible(false);
         
}          // TODO add your handling code here:
    }//GEN-LAST:event_jspTableMouseClicked

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed

        strSearchText = txtSearchText.getText();
        int intSelectedSearchOption = cboFilter.getSelectedIndex();

        if(strSearchText.isBlank())
        {
            JOptionPane.showMessageDialog(rootPane, "Search box can not be empty");

        }
        else if(intSelectedSearchOption == 0)
        {
            JOptionPane.showMessageDialog(rootPane, "Please select a search option");
            cboFilter.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0)));
        }
        else
        {
            mRemoveRows();
            mSearchForClients();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSearchActionPerformed

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
            java.util.logging.Logger.getLogger(frmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnBack;
    public javax.swing.JButton btnCreate;
    public javax.swing.JButton btnDelete;
    public javax.swing.JButton btnEdit;
    public javax.swing.JButton btnLogoff;
    public javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnSearch;
    public javax.swing.JButton btnViewAll;
    private javax.swing.JComboBox<String> cboFilter;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jspTable;
    private javax.swing.JLabel lblIthemba;
    private javax.swing.JLabel lblRayOfHope;
    public javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnlBackground;
    private javax.swing.JPanel pnlCreate;
    private javax.swing.JPanel pnlDelete;
    public javax.swing.JPanel pnlDrawerOptions;
    private javax.swing.JPanel pnlEdit;
    private javax.swing.JPanel pnlLogo;
    private javax.swing.JPanel pnlLogoff;
    private javax.swing.JPanel pnlOpenDrawer;
    private javax.swing.JPanel pnlViewAll;
    public javax.swing.JTable tblDatabaseData;
    private javax.swing.JTextField txtSearchText;
    // End of variables declaration//GEN-END:variables
}
