/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uniswajavaapplication;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.filechooser.*;

/**
 * This class will serve to hold the JFrame that will contain all the JPanels that will make the system function
 * @author Fanelesibonge Malaza
 */
public class start {
    
    private static JFrame window;
    public static void main(String[] args) {
        background1 login = new background1();    
        //SplashScreen splash = new SplashScreen();
        window = new JFrame("Uniswa Application");
        FileUpload file = new FileUpload();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(800,500);
        window.setResizable(false);
        window.setLayout(new BorderLayout());
        window.setLocation(270,80);
        window.setContentPane(login);
        window.setVisible(true);
    }    

    public void setPanel(JPanel oldPanel, JPanel newPanel){
        if(oldPanel == window.getContentPane()){
            //window.setVisible(false);
            window.setContentPane(newPanel);
            window.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(null, "Wrong Panels");
        }
    }
    
    public void Exit(){
        this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

