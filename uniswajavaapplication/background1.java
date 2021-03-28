/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uniswajavaapplication;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Fanelesibonge Malaza
 */
public class background1 extends JPanel{
        private JLabel logoImage;
        private JLabel userlabel;
        private JLabel passlabel;
        private JTextField username;
        private JPasswordField password;
        private JButton loginBtn;
    
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            setBackground(new Color(178,34,34));
        }
        
        public background1(){
            logoImage = new JLabel(new ImageIcon("C:\\Users\\Fanelesibonge Malaza\\Documents\\NetBeansProjects\\UniswaJavaApplication\\src\\uniswajavaapplication\\logo.png"));
            logoImage.setBounds(260,20,290,120);
            userlabel = new JLabel("Username:");
            passlabel = new JLabel("Password:");
           
            userlabel.setBounds(290,130,100,60);
            userlabel.setFont(new Font("Sans Serif", Font.BOLD, 15));
            passlabel.setBounds(290,210,100,60);
            passlabel.setFont(new Font("Sans Serif", Font.BOLD, 15));
            
            username = new JTextField();
            username.setToolTipText("Username");
            username.setBounds(290,170,220,40);
            username.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    if(username.getText().equals("Username")){
                        username.setText("");
                    }
                }

                @Override
                public void keyPressed(KeyEvent e) {
                   if(username.getText().equals("Username")){
                       username.setText("");
                   }
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    
                }
            });
            username.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if(username.getText().equals("Username")){
                        username.setText("");
                    }
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    if(username.getText().equals("Username")){
                        username.setText("");
                    }
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    if(username.getText().equals("Username")){
                        username.setText("");
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
            
            password = new JPasswordField();
            password.setBounds(290, 250, 220, 40);
            
            loginBtn = new JButton("Login");
            loginBtn.setBounds(355,310,90,30);
            loginBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    char[] pass = {'u','s','e','r'}; 
                    if(username.getText().equals("root") && Arrays.equals(password.getPassword(), pass)){
                        FileUpload file = new FileUpload();
                        start begin = new start();
                        begin.setPanel(background1.this, file);
                    }else{
                        JOptionPane.showMessageDialog(null, "Please fill the required fields.");
                    }
                }
            });
          
            setLayout(null);
            add(userlabel);
            add(passlabel);
            add(logoImage);
            add(username);
            add(password);
            add(loginBtn);
            setVisible(true);
        }
}
