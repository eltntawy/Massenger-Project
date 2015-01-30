/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.view;

import com.chat.controller.ChatClientController;
import com.chat.model.Message;
import com.chat.model.MessageFile;
import com.chat.model.User;
import com.chat.view.resource.Resource;
import de.javasoft.plaf.synthetica.SyntheticaStandardLookAndFeel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

/**
 *
 * @author yomna
 */
public class ChatFrame extends javax.swing.JFrame {

    /**
     * Creates new form Chat
     */
    private User Sender;
    private User Receiver;
    private ChatClientController chatController;
    private EditorPanel toolbar;
    private Font font;
    private ConversationEditorPane convEditorPane;
    private String sessionId;
    private String fileName;
    private byte[] filePart;
    private WindowAdapter windowClose = null;

    public ChatFrame() {
        initComponents();
        sessionId = new String();
        fileName = new String();
        filePart = new byte[1024];
        this.pack();
    }

    public ChatFrame(User Sender, User Receiver, ChatClientController chatController, String sessionId) {
        
        this.Sender = Sender;
        this.Receiver = Receiver;
        this.chatController = chatController;
        this.sessionId = sessionId;
        fileName = new String();
        filePart = new byte[1024];
        
        initComponents();

        Font font = new Font(Font.SANS_SERIF, Font.ITALIC, 2);
        toolbar = new EditorPanel(font, Color.BLACK, 2);
        jPanel4.add(toolbar);
        //Message message = new Message (Sender.getUserName(), Receiver.getUserName(),"hjbiugoiuhiu gjhagdi" );
        convEditorPane = new ConversationEditorPane(font, Color.BLACK);
        /*sec.AppendText(message, font, Color.yellow);
         Message message2 = new Message (Sender.getUserName(), Receiver.getUserName(),"hjbiugoiuhiu" );
         sec.AppendText(message2, font, Color.yellow);*/
        jScrollPane1.setViewportView(convEditorPane);
        SenderPanel panel = new SenderPanel();
        panel.setSenderImagelbl(Sender.getUserPicture());
        panel.setSenderNamelbl(Sender.getUserName());
        if(Receiver != null)
        jScrollPane1.setBorder(new TitledBorder(Receiver.getUserName()));
        jPanel3.add(panel);
        SenderPanel Rpanel = new SenderPanel();
        if(Receiver != null) {
            Rpanel.setSenderImagelbl(Receiver.getUserPicture());
            Rpanel.setSenderNamelbl(Receiver.getUserName());
        }
        jPanel3.add(Rpanel);
        convEditorPane.setEditable(false);
        this.pack();
        this.windowClose = new WindowAdapter() {
          public void windowClosed(WindowEvent e){
              ChatFrame.this.chatController.removeChatFrame(ChatFrame.this.sessionId);
          }  
        };
        this.addWindowListener(this.windowClose);

    }
    
    public void setSender (User sender){
        this.Sender = sender;
    }
    public void setReceiver (User receiver){
        this.Receiver = receiver;
    }
    public User getSender(){
        return Sender;
    }
    public User getReceiver(){
        return Receiver;
    }
    public void setSessionId (String sessionId){
        this.sessionId = sessionId;
    }
    
    public String getSessionId (){
        return sessionId;
    }
    
     public void sendMessage() {

        if (!txtChat.getText().equals("")) {
            Message message = new Message(Sender, Receiver, txtChat.getText(), sessionId);
            convEditorPane.AppendText(message, toolbar.getSelectedFont(), toolbar.getSelectedColor());
            txtChat.setText("");
            System.out.println(message.getSenderName());
            System.out.println(message.getReceiverName());
            System.out.println(message.getMessage());
            chatController.sendMessage(message);
        }
    }
    
    public void receiveMessage(Message message) {
        System.out.println("||||||||");
        System.out.println(message.getMessage());
        convEditorPane.AppendText(message, toolbar.getSelectedFont(), toolbar.getSelectedColor());
    
    }
    
    public boolean requestSend(String fileName){
        return chatController.requestSend(fileName, Receiver);
    }
    
    public boolean confirmRequest(String fileName){
        
        int option = JOptionPane.showConfirmDialog(this, "Do you want to receive this file: "+fileName, "Confirm", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (option == JOptionPane.OK_OPTION){
            return true;
        }
        else {
            return false;
        }
    }
    
    public void readFile(File file){
       
        try {
            FileInputStream readFile = new FileInputStream(file);
            readFile.read(filePart);
            MessageFile messageFile = new MessageFile(filePart, Sender, Receiver, sessionId);
            sendFile(messageFile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ChatFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ChatFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void sendFile(MessageFile messageFile){
        chatController.sendFile(messageFile);
    }
    
    public void receiveFile (MessageFile messageFile){
        JFileChooser saveFile = new JFileChooser ();
        saveFile.showSaveDialog(this);
        File receivedFile = new File(saveFile.getSelectedFile().getAbsolutePath());
        if (!receivedFile.exists()){
            try {
                receivedFile.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(ChatFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            FileOutputStream received = new FileOutputStream(receivedFile,true);
            received.write(messageFile.getFile());
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ChatFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ChatFrame.class.getName()).log(Level.SEVERE, null, ex);
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
        jPanel6 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        txtChat = new javax.swing.JTextField();
        btnSend = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Chat");
        setMinimumSize(new java.awt.Dimension(400, 400));

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Chat"));
        jPanel6.setLayout(new java.awt.GridLayout(1, 0));

        jPanel5.setLayout(new javax.swing.BoxLayout(jPanel5, javax.swing.BoxLayout.LINE_AXIS));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Chat Area"));
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setMaximumSize(null);
        jScrollPane1.setPreferredSize(null);
        jPanel5.add(jScrollPane1);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Chat Memeber"));
        jPanel3.setMaximumSize(new java.awt.Dimension(230, 32767));
        jPanel3.setMinimumSize(new java.awt.Dimension(250, 100));
        jPanel3.setPreferredSize(new java.awt.Dimension(200, 100));
        jPanel3.setLayout(new java.awt.GridLayout(2, 0));
        jPanel5.add(jPanel3);

        jPanel6.add(jPanel5);

        getContentPane().add(jPanel6, java.awt.BorderLayout.CENTER);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("font"));
        jPanel4.setToolTipText("font");
        jPanel4.setMaximumSize(new java.awt.Dimension(300, 50));
        jPanel4.setMinimumSize(new java.awt.Dimension(300, 50));
        jPanel4.setPreferredSize(new java.awt.Dimension(300, 50));
        jPanel4.setLayout(new javax.swing.BoxLayout(jPanel4, javax.swing.BoxLayout.PAGE_AXIS));

        txtChat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtChatKeyReleased(evt);
            }
        });

        btnSend.setText("Send");
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });

        jButton1.setText("Attatch File");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(txtChat, javax.swing.GroupLayout.PREFERRED_SIZE, 525, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSend, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtChat, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(btnSend, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel7, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendActionPerformed
        // TODO add your handling code here:
        sendMessage();
    }//GEN-LAST:event_btnSendActionPerformed

    private void txtChatKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtChatKeyReleased
        // TODO add your handling code here:
        
        evt.consume();
        if (evt.getKeyCode() == KeyEvent.VK_ENTER && !txtChat.getText().equals("")) {
            if (txtChat.getText().split(" ").length > 0) {
                sendMessage();
                txtChat.setText("");
            }

        }

        if (txtChat.getText().equals("") || txtChat.getText().split(" ").length == 0) {
            btnSend.setEnabled(false);
        } else {
            btnSend.setEnabled(true);
        }
        
    }//GEN-LAST:event_txtChatKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        //attatch file
        JFileChooser attachFileChooser = new JFileChooser();
        attachFileChooser.showOpenDialog(this);
        fileName = attachFileChooser.getSelectedFile().getName();
        boolean option = requestSend(fileName);
        if (option == true){
            //call send file
            readFile(attachFileChooser.getSelectedFile());
        }
        else {
            //show refused message
        }
    }//GEN-LAST:event_jButton1ActionPerformed

   

    /**
     * @param args the command line arguments
     */
/*    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */

        //</editor-fold>

        /* Create and display the form */
  /*      java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                final JFrame f = new ChatFrame(new User(5,"Youmna", "", "", Resource.IMAGE_AWAY, User.AVAILABLE), new User(2,"Yasmeen", "", "", Resource.IMAGE_AVAILABLE, User.AWAY));
//                final JFrame f = new ChatFrame(new User("Youmna", "", "", Resource.IMAGE_AWAY, User.AVAILABLE), new User("Yasmeen", "", "", Resource.IMAGE_AVAILABLE, User.AWAY));

                try {

                    //UIManager.setLookAndFeel("com.alee.laf.WebLookAndFeel");
                    //UIManager.installLookAndFeel("SeaGlass", "com.seaglasslookandfeel.SeaGlassLookAndFeel");
                    UIManager.setLookAndFeel(new SyntheticaStandardLookAndFeel());
                    //UIManager.setLookAndFeel(new SyntheticaWhiteVisionLookAndFeel());

                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                SwingUtilities.invokeLater(new Runnable() {

                    public void run() {
                        // TODO Auto-generated method stub
    //                    SwingUtilities.updateComponentTreeUI(f);
                    }
                });

  //              f.setVisible(true);
            }
        });
    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSend;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtChat;
    // End of variables declaration//GEN-END:variables
}
