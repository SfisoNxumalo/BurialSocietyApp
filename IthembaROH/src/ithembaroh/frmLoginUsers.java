/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ithembaroh;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Sfiso
 */
public class frmLoginUsers extends javax.swing.JFrame {

    /**
     * Creates new form frmLoginUsers
     */
    public frmLoginUsers() {
        initComponents();
        mViewUsers();
    }
    
    int intSelected;
    String strSearchText;
    DefaultTableModel model;

    public void mViewUsers()
    {
        String URL = "jdbc:mysql://localhost:3306/burial_society_db";
        String User = "root";
        String Password = "WeWillMakeIt@Life";
        
        Connection conMySQLConnectionString = null;
        Statement stStatement = null;
        ResultSet rsPrincipalMembers = null;
        
         model = (DefaultTableModel)tblUsers.getModel();
              
        try
        {
            conMySQLConnectionString = DriverManager.getConnection(URL, User, Password);
            
            stStatement = conMySQLConnectionString.createStatement();
            
            String strSQLQuery = "SELECT * FROM users_login";
            
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
    
    private void mRemoveRows() // removes all the rows in the table
    {
        model = (DefaultTableModel)tblUsers.getModel();
        int intRows = model.getRowCount();
        
        if(intRows != 0)
        {
            for( int i = 0; i < intRows ; i++)
            {
                model.removeRow(0);
            }  
        }
    }
    
    private void mDeleteClientFromJTable() //Removes the selected row from the table
    {
        model = (DefaultTableModel)tblUsers.getModel();
            
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

        Connection conMySQLConnectionString;
        Statement stStatement = null;
        
        try
        {
            conMySQLConnectionString = DriverManager.getConnection(URL, User, Password);


            String strUserID = tblUsers.getValueAt(intSelected, 0).toString();

            String  strDelete = "DELETE FROM users_login "
                              + "WHERE id = " + "'" + strUserID + "'" ;  
            
            stStatement = conMySQLConnectionString.prepareStatement(strDelete);
            stStatement.execute(strDelete);

            mDeleteClientFromJTable();

            JOptionPane.showMessageDialog(rootPane, "Data has been successfully Deleted");
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(rootPane, e);
        }
        finally
        {
            try
            {
                stStatement.close();
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(rootPane, "Conectioncould not be closed.");
            }
        }
    }
    
    private void mSearchForClients()  //Searches for any data that matches the entered text.
    {
        String URL = "jdbc:mysql://localhost:3306/burial_society_db";
        String User = "root";
        String Password = "WeWillMakeIt@Life";
        
        Connection conMySQLConnectionString = null;
        Statement stStatement = null;
        ResultSet rsPrincipalMembers = null;
        
         model = (DefaultTableModel)tblUsers.getModel();
              
        try
        {
            conMySQLConnectionString = DriverManager.getConnection(URL, User, Password);
            
            stStatement = conMySQLConnectionString.createStatement();
            
            String strSQLQuery = "SELECT * FROM users_login "
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
               JOptionPane.showMessageDialog(rootPane, "No record containing " + "'" + strSearchText + "' as " + cboFilter.getSelectedItem()+ " was found."); 
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
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlBackground = new javax.swing.JPanel();
        btnRemove = new javax.swing.JButton();
        lblTitle = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();
        cboFilter = new javax.swing.JComboBox<>();
        txtSearchText = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUsers = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlBackground.setBackground(new java.awt.Color(102, 153, 255));
        pnlBackground.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlBackgroundMouseClicked(evt);
            }
        });
        pnlBackground.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnRemove.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        btnRemove.setForeground(new java.awt.Color(0, 51, 255));
        btnRemove.setText("Remove");
        btnRemove.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(255, 255, 255), null));
        btnRemove.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });
        pnlBackground.add(btnRemove, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 290, 80, 32));

        lblTitle.setFont(new java.awt.Font("SansSerif", 2, 36)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("Users");
        pnlBackground.add(lblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 520, -1));

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
        pnlBackground.add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 290, 80, 32));

        cboFilter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "id", "Username" }));
        pnlBackground.add(cboFilter, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 63, 45, 20));

        txtSearchText.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        pnlBackground.add(txtSearchText, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 63, 140, 20));

        btnSearch.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        btnSearch.setForeground(new java.awt.Color(51, 51, 255));
        btnSearch.setText("Find");
        btnSearch.setMargin(new java.awt.Insets(2, 5, 2, 5));
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });
        pnlBackground.add(btnSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 63, 90, 20));

        tblUsers.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        tblUsers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Role", "Username", "Password"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblUsers.setRowHeight(20);
        jScrollPane1.setViewportView(tblUsers);
        if (tblUsers.getColumnModel().getColumnCount() > 0) {
            tblUsers.getColumnModel().getColumn(0).setResizable(false);
            tblUsers.getColumnModel().getColumn(1).setResizable(false);
            tblUsers.getColumnModel().getColumn(2).setResizable(false);
            tblUsers.getColumnModel().getColumn(3).setResizable(false);
        }

        pnlBackground.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 460, 170));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBackground, javax.swing.GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
    intSelected = tblUsers.getSelectedRow();
 
    if(intSelected != -1)
    {
        mDeleteClient();
    }
    else
    {
        JOptionPane.showMessageDialog(rootPane, "Please select a row");
    }
    }//GEN-LAST:event_btnRemoveActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        frmAdminDashboard frmAD = new frmAdminDashboard(); //returns back to the default dashboard
        frmAD.show();
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_btnBackActionPerformed

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

    private void pnlBackgroundMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlBackgroundMouseClicked
               // TODO add your handling code here:
    }//GEN-LAST:event_pnlBackgroundMouseClicked

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
            java.util.logging.Logger.getLogger(frmLoginUsers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmLoginUsers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmLoginUsers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmLoginUsers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmLoginUsers().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnBack;
    public javax.swing.JButton btnRemove;
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox<String> cboFilter;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnlBackground;
    private javax.swing.JTable tblUsers;
    private javax.swing.JTextField txtSearchText;
    // End of variables declaration//GEN-END:variables
}
