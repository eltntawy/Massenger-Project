/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.view;

import com.chat.controller.ChatClientController;
import com.chat.controller.MessengerClientController;
import com.chat.model.Message;
import com.chat.model.MessageFile;
import com.chat.model.User;
import com.chat.view.model.ListComboBoxModel;
import com.chat.view.renderer.ContactListCellRender;
import com.chat.view.resource.Resource;
import com.xml.XMLJAXB;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
    private User loginUser;
    private Vector<User> UserVector;
    private ChatClientController chatController;
     private MessengerClientController messengerController;
    private EditorPanel toolbar;
    private Font font;
    private ConversationEditorPane convEditorPane;
    private String sessionId;
    private String fileName;
    private byte[] filePart;
    private WindowAdapter windowClose = null;
    private JPanel jPanel8;
    private ListComboBoxModel<User> listModel;        
    private ArrayList fileData;
    public ChatFrame() {
        initComponents();
        sessionId = new String();
        fileName = new String();
        filePart = new byte[1024];
        this.pack();
    }

    public ChatFrame(User Sender, Vector<User> UsersVector, MessengerClientController messengerController,ChatClientController chatController, String sessionId) {
        
        this.Sender = Sender;
        this.chatController = chatController;
        this.messengerController = messengerController;
        this.sessionId = sessionId;
        fileName = new String();
        filePart = new byte[1024];
        fileData = new ArrayList();
        UserVector = new Vector();
        this.UserVector = UsersVector;
        loginUser = chatController.getLoginUser();
        initComponents();
        
        Font font = new Font(Font.SANS_SERIF, Font.ITALIC, 2);
        toolbar = new EditorPanel(font, Color.BLACK, 2);
        jPanel8 = new JPanel();
        jPanel8.setPreferredSize(new Dimension(200, 200));
        jPanel4.add(toolbar);
        convEditorPane = new ConversationEditorPane(font, Color.BLACK);
        jScrollPane1.setViewportView(convEditorPane);
        listModel = new ListComboBoxModel<User>();
        if (UsersVector != null){
            for (int i = 0; i < UsersVector.size(); i++){
                listModel.addElement(UsersVector.elementAt(i));
            }
        }
        chatList.setModel(listModel);
        chatList.setCellRenderer(new ContactListCellRender());
        jScrollPane1.setBorder(new TitledBorder(loginUser.getUserName()));
        convEditorPane.setEditable(false);
        this.pack();
        
        this.windowClose = new WindowAdapter() {
          public void windowClosed(WindowEvent e){
              ChatFrame.this.chatController.removeChatFrame(ChatFrame.this.sessionId);
              ChatFrame.this.UserVector.removeElement(loginUser);
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
    public void setReceiverVector (Vector<User> receiversVector){
        this.UserVector = receiversVector;
    }
    public void appendReceiver(User receiver){
        UserVector.addElement(receiver);
    }
    public User getSender(){
        return Sender;
    }
    public User getReceiver(){
        return Receiver;
    }
    public Vector<User> getReceiverVector(){
        return UserVector;
    }
    public void setSessionId (String sessionId){
        this.sessionId = sessionId;
    }
    
    public String getSessionId (){
        return sessionId;
    }
    
     public void sendMessage() {

        if (!txtChat.getText().equals("")) {
            
            Message message = new Message(loginUser, UserVector, txtChat.getText(), sessionId);
         /*   for (int i = 0; i < UserVector.size(); i++){
              if (!loginUser.getUserName().equals(UserVector.elementAt(i).getUserName())){
                  message.setReceiverName(UserVector.elementAt(i));
                  XMLJAXB xmlJAXB = new XMLJAXB();
                  xmlJAXB.appendMsg(message);
              }  
            }
           */ 
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
        appendChatGroup(message.getUsersVector());
        UserVector = message.getUsersVector();
        //message.setUsersVector(UserVector);
        convEditorPane.AppendText(message, toolbar.getSelectedFont(), toolbar.getSelectedColor());
    }
    
    public boolean requestSend(String fileName){
        return chatController.requestSend(fileName, UserVector);
    }
    
    public boolean confirmRequest(String fileName, Vector<User> usersVector){
        
        appendChatGroup(UserVector);
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
	    byte[] filePart = new byte[1024];
	    while (readFile.read(filePart) != -1) {
		fileData.add(filePart);
		filePart = new byte[1024];
	    }	
	    // readFile.read(filePart);
	    MessageFile messageFile = new MessageFile(fileData, file.getName(), Sender, UserVector, sessionId);
	    sendFile(messageFile);
	    JOptionPane.showMessageDialog(this, "File send");
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
        appendChatGroup(messageFile.getUsersVector());
        messageFile.setUsersVector(UserVector);
        JFileChooser saveFile = new JFileChooser ();
        saveFile.setSelectedFile(new File(messageFile.getFileName()));
	saveFile.showSaveDialog(this);
	if (saveFile.getSelectedFile() != null) {
	    File receivedFile = new File(saveFile.getSelectedFile().getAbsolutePath());
	    if (!receivedFile.exists()) {
		try {
		    receivedFile.createNewFile();
		} catch (IOException ex) {
		    Logger.getLogger(ChatFrame.class.getName()).log(Level.SEVERE, null, ex);
		}
	    }
	    try {
		FileOutputStream received = new FileOutputStream(receivedFile, true);
		for (byte[] data : messageFile.getFile()) {
		    received.write(data);
		}

		JOptionPane.showMessageDialog(this, "File Received");

		received.flush();
		received.close();
	    } catch (FileNotFoundException ex) {
		Logger.getLogger(ChatFrame.class.getName()).log(Level.SEVERE, null, ex);
	    } catch (IOException ex) {
		Logger.getLogger(ChatFrame.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}
    }
    
    public void appendChatGroup (Vector<User>receiverVector){
        
        listModel.removeAllElements();
        for (int i = 0; i < receiverVector.size(); i++){
            listModel.addElement(receiverVector.elementAt(i));
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
        jPanel3 = new javax.swing.JScrollPane();
        chatList = new javax.swing.JList();
        jPanel7 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        txtChat = new javax.swing.JTextField();
        btnSend = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

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
        jPanel5.add(jScrollPane1);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Chat Memeber"));
        jPanel3.setMaximumSize(new java.awt.Dimension(230, 32767));
        jPanel3.setMinimumSize(new java.awt.Dimension(250, 100));
        jPanel3.setPreferredSize(new java.awt.Dimension(200, 100));

        chatList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jPanel3.setViewportView(chatList);

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

        jButton1.setIcon(Resource.getImage("Attach.png")
        );
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setText("Add Friend");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtChat)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSend))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 514, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSend, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtChat, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
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
        File file = attachFileChooser.getSelectedFile();
        fileName = file.getName();
     
	if(file.length()> 50000000) { 
		JOptionPane.showMessageDialog(this, "Max file length is 50 MB");
		return;
	    }
        boolean option = requestSend(fileName);
        if (option == true){
            //call send file
            readFile(attachFileChooser.getSelectedFile());
        }
        else {
            //show refused message
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        int counter = 0;  
        String friendName = JOptionPane.showInputDialog(this, "Add Friend to Group Chat:\n", "", JOptionPane.INFORMATION_MESSAGE);
        try {
            for (int i = 0; i < (messengerController.getContactListOfCurrentUser()).size(); i++){
                User friend = (messengerController.getContactListOfCurrentUser()).get(i);
                if (friendName.equals(friend.getUserName())){
                    
                        for (int j = 0; j < UserVector.size(); j++){
                            if (!friend.getUserName().equals(UserVector.elementAt(j).getUserName())){
                                counter++;
                            }
                        }
                    if (counter == UserVector.size() && friend.getStatus() == User.AVAILABLE ){
                        listModel.addElement(friend);
                        appendReceiver(friend);
                    }
                    
                }
            }
        }catch (RemoteException ex){
            
        }catch (SQLException ex){
            
        }
    }//GEN-LAST:event_jButton3ActionPerformed

   

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
    private javax.swing.JList chatList;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtChat;
    // End of variables declaration//GEN-END:variables
}
