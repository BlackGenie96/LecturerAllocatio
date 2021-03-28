/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uniswajavaapplication;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 *
 * @author Fanelesibonge Malaza
 */
public class OutputFile extends JPanel{
    String loc = null;
    List l;
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        setBackground(new Color(178,34,34));
    }
    
    public OutputFile(){
        JLabel locationLabel = new JLabel("Output Location:");
        JLabel logoImage;
        JTextField location = new JTextField();
        JButton save = new JButton("Save");
        JButton exit = new JButton("Exit");
        
        logoImage = new JLabel(new ImageIcon("C:\\Users\\Fanelesibonge Malaza\\Documents\\NetBeansProjects\\UniswaJavaApplication\\src\\uniswajavaapplication\\logo.png"));
        locationLabel.setBounds(80,100,130,80);
        locationLabel.setFont(new Font("Sans Serif", Font.BOLD, 15));    
        logoImage.setBounds(260,10,290,120);
        location.setBounds(80,150,350,25);
        save.setBounds(260,180,80,30);
        exit.setBounds(350,180,80,30);
        
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                j.setAcceptAllFileFilterUsed(false);
                FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .xlsx files", "xlsx");
                j.addChoosableFileFilter(restrict);
                int r = j.showSaveDialog(null);
                if(r == JFileChooser.APPROVE_OPTION){
                    location.setText(j.getSelectedFile().getAbsolutePath());
                    loc = String.valueOf(j.getSelectedFile().getAbsolutePath());
                    writeData(l,loc);
                }else{
                    location.setText("User cancelled the operation");
                }
            }    
        });
        
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        setLayout(null);
        add(logoImage);
        add(locationLabel);
        add(location);
        add(save);
        add(exit);
        setVisible(true);
    }
    
    public void writeData(List l, String path){
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        
        int rowIndex = 0;
        for(int i = 0; i < l.size(); i++){
            Allocations all = (Allocations)l.get(i);
            Row row = sheet.createRow(rowIndex++);
            int cellIndex = 0;
            //first row and first cell enter the date
            row.createCell(cellIndex++).setCellValue("Date:");
            row.createCell(cellIndex++).setCellValue(all.getDateAndTime());
            
            //second row and first cell enter the venue
            row = sheet.createRow(rowIndex++);
            cellIndex = 0;
            row.createCell(cellIndex++).setCellValue("Venue:");
            row.createCell(cellIndex++).setCellValue(all.getVenue());
            
            //for each value in course list add the courses in the same row
            row = sheet.createRow(rowIndex++);
            cellIndex = 0;
            row.createCell(cellIndex++).setCellValue("Courses:");
            for(int j = 0; j < all.getCourseSize(); j++){
                row.createCell(cellIndex++).setCellValue(all.getCourseName(j));
            }
            
            //for each value in lecturer list add the lecturers in the same row
            row = sheet.createRow(rowIndex++);
            cellIndex = 0;
            row.createCell(cellIndex++).setCellValue("Lecturers");
            for(int k = 0; k < all.getLectureSize(); k++){
                row.createCell(cellIndex++).setCellValue(all.getLecturerName(k));
            }
            
            row = sheet.createRow(rowIndex++);
            cellIndex = 0;
            row.createCell(cellIndex++).setCellValue("Total:");
            row.createCell(cellIndex++).setCellValue(all.getTotal());
            row = sheet.createRow(rowIndex++);
        }
        
        //write this data into the excell file
            try{
                FileOutputStream fos = new FileOutputStream(path);
                workbook.write(fos);
                fos.close();
                
                JOptionPane.showMessageDialog(null, path+" is successfully written");
            }catch(FileNotFoundException e){
                e.printStackTrace();
            }catch(IOException e){
                e.printStackTrace();
            }
    }
    
    public void getList(List l){
        this.l = l;
        //return this.l;
    }
}
