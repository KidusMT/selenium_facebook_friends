/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selenium_test;

import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import static org.openqa.selenium.lift.find.InputFinder.submitButton;
import static selenium_test.Selenium_Test.getMyFacebookFriends;

/**
 *
 * @author KidusMT
 */
public class Login extends javax.swing.JFrame {

  
    public Login() {
        initComponents();
        this.setLocation(400, 200);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(290, 441));
        setMinimumSize(new java.awt.Dimension(290, 441));
        setModalExclusionType(java.awt.Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Facebook Letter Faces", 0, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Facebook");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(21, 50, 240, 62);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/selenium_test/fb_menu.PNG"))); // NOI18N
        getContentPane().add(jLabel6);
        jLabel6.setBounds(120, 0, 200, 40);

        jTextField3.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jTextField3.setText("email or username");
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField3KeyPressed(evt);
            }
        });
        getContentPane().add(jTextField3);
        jTextField3.setBounds(110, 110, 150, 25);

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Username");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(20, 110, 80, 19);

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Password");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(20, 140, 80, 19);

        jButton1.setText("Login");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(187, 180, 70, 23);

        jPasswordField1.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        jPasswordField1.setText("jPassword");
        jPasswordField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField1ActionPerformed(evt);
            }
        });
        jPasswordField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPasswordField1KeyPressed(evt);
            }
        });
        getContentPane().add(jPasswordField1);
        jPasswordField1.setBounds(110, 140, 152, 19);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/selenium_test/fb_logo.PNG"))); // NOI18N
        getContentPane().add(jLabel5);
        jLabel5.setBounds(0, 0, 120, 40);

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Made By Few:");
        jLabel7.setToolTipText("");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(100, 350, 160, 14);

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Kidus Mamuye ATR/6157/07");
        jLabel8.setToolTipText("");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(100, 390, 170, 14);

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Samuel Musse ATR/9258/07");
        jLabel9.setToolTipText("");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(100, 370, 170, 14);

        jLabel10.setFont(new java.awt.Font("Gilgongo Tiki", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Selenium Web Project");
        getContentPane().add(jLabel10);
        jLabel10.setBounds(20, 270, 240, 20);

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/selenium_test/url1.jpeg"))); // NOI18N
        getContentPane().add(jLabel4);
        jLabel4.setBounds(0, 0, 320, 410);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String uname = jTextField3.getText();
        String pass = jPasswordField1.getText().toString();
        this.dispose();
        Selenium_Test.getMyFacebookFriends(uname,pass);        
  
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jPasswordField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordField1KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            String uname = jTextField3.getText();
            String pass = jPasswordField1.getText();
            Selenium_Test.getMyFacebookFriends(uname,pass);
        }
             
    }//GEN-LAST:event_jPasswordField1KeyPressed

    private void jTextField3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_TAB){
            String uname = jTextField3.getText();
            String pass = jPasswordField1.getText();
            Selenium_Test.getMyFacebookFriends(uname,pass);
        }
    }//GEN-LAST:event_jTextField3KeyPressed

    private void jPasswordField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordField1ActionPerformed

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
//                JFrame frame = new JFrame();
//                Login l = new Login();
//                l.jButton1ActionPerformed(evt);
//                frame.getRootPane().setDefaultButton(jButton1ActionPerformed);

                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
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
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
