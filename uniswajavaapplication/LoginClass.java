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
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Fanelesibonge Malaza
 */
public class LoginClass extends JPanel {
    
    private String loginTitle = "Login";
    private JTextField username;
    private JPasswordField password;
    private JButton loginBtn;
    private LoginTitle title;
    
    //this class will be responsible for showing the title "LOGIN" on the window
    private class LoginTitle extends JPanel{
        
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            //setTextColor(Color.black);
            setBackground(new Color(178,34,34));
            setBounds(370, 100, 200, 20);
            setFont(new Font("serif", Font.BOLD, 50));
            //g.drawImage(logoImage, 0, 0,this);
        }
    }
    
    private class loginHandler implements ActionListener{
        public void actionPerformed(ActionEvent event){
            JOptionPane.showMessageDialog(null, "Login button has been pressed");
        }
    }
    
    private class keyHandler implements KeyListener{

        @Override
        public void keyTyped(KeyEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            if(username.getText().equals("Username"))
                username.setText("");
        }

        @Override
        public void keyPressed(KeyEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            if(username.getText().equals("Username"))
                username.setText("");
        }

        @Override
        public void keyReleased(KeyEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    
    public LoginClass(){
        //initialising variables
        
        try{
        JPanel panel = new JPanel(); 
        title = new LoginTitle();
        username = new JTextField();
        password = new JPasswordField();
        loginBtn = new JButton("LOGIN");
        
        BufferedImage image = ImageIO.read(new File("C:\\Users\\Fanelesibonge Malaza\\Documents\\NetBeansProjects\\UniswaJavaApplication\\src\\uniswajavaapplication\\logo.png"));
        JLabel logo = new JLabel(new ImageIcon(image));
        add(logo);
        
        //styling username textfield
        username.setText("Username");
        username.setToolTipText("Username");
        username.setBounds(290, 140, 220, 40);
        username.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mousePressed(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                if(username.getText().equals("Username")){
                    username.setText("");
                }else{
                    JOptionPane.showMessageDialog(null, "Something is up");
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        keyHandler key = new keyHandler();
        username.addKeyListener(key);
        
        //password style
        password.setBounds(290, 220, 220, 40);
        password.setToolTipText("Password");
        
        //login button style
        loginBtn.setBackground(Color.white);
        loginBtn.setBounds(355, 290, 90, 30 );
        loginHandler handler = new loginHandler();
        loginBtn.addActionListener(handler);
        
        setLayout(null);
        Color brick_red = new Color(178,34,34);
        setBackground(brick_red);
        //add(logo);
        add(title);
        add(username);
        add(password);
        add(loginBtn);
        
        setVisible(true);
        }catch(IOException ex){
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }
}
