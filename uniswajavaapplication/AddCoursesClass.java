/*
 * This class will be mainly concerned with adding the courses to the system
 * either using a file that has the course names and codes listed or by adding
 * the courses individually, one after the other
 */
package uniswajavaapplication;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;

/*********************@author Fanelesibonge Malaza************************/

public class AddCoursesClass extends JPanel {
    
    //declaring and initializing variables
        JLabel filePath;
        JButton openFile;
        JButton next;
        JButton exit;
        JTextField filepath; 
        String message = "Either choose a file that has the course names and codes listed or add individually.";
    
        
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        setBackground(new Color(174,38,38));
        g.drawString(message, 20, 5);
    }
    
    //Action handler for handling actions from the different buttons
    private class handler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
           String com = e.getActionCommand();
           if(com.equals("Open File")){
               //file chooser operation
               JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
               int r = fileChooser.showOpenDialog(null);
               
               //if the user selects a file
               if(r == JFileChooser.APPROVE_OPTION){
                   //set the label and textfield to the path
                   filePath.setText(fileChooser.getSelectedFile().getAbsolutePath());
                   filepath.setText(fileChooser.getSelectedFile().getAbsolutePath());
               }
           }
        }
        
    }
    
    //Constructor for initializing all class variables
    public AddCoursesClass(){
        JPanel content = new JPanel();
        filePath = new JLabel();
        filepath = new JTextField();
        openFile = new JButton("Open File"); 
        next = new JButton("Next");
        exit = new JButton("Exit");
        
        //Customizing layout of components in panel
        filepath.setBounds(50, 70, 30, 60);
        
        content.add(filepath);
        setVisible(true);
    }
}
