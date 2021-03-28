/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uniswajavaapplication;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 *
 * @author Fanelesibonge Malaza
 */
public class FileUpload extends JPanel { 
        List l = new ArrayList();
        List trying;
        List datesList;
        File[] files = null;
            
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            setBackground(new Color(178,34,34));
        }
        
        public FileUpload(){
            JLabel courseLabel = new JLabel("Select Lecturers file(s):(Select all the files from one folder)");
            JLabel datesAndTimes = new JLabel("Select Exam Timetable file:");
            JLabel logoImage;
            JLabel displayList = new JLabel();
            JTextField location1 = new JTextField();
            JTextField location2 = new JTextField();
            JButton btn1 = new JButton("Open");
            JButton btn2 = new JButton("Process Data");
            JButton btn3 = new JButton("Open");
            JButton btn4 = new JButton("Process Data");
            JButton btn5 = new JButton("Next");
            
            courseLabel.setBounds(80,100,130,80);
            courseLabel.setFont(new Font("Sans Serif", Font.BOLD, 15));
            datesAndTimes.setBounds(80,220,230,80);
            datesAndTimes.setFont(new Font("Sans Serif", Font.BOLD, 15));
            logoImage = new JLabel(new ImageIcon("C:\\Users\\Fanelesibonge Malaza\\Documents\\NetBeansProjects\\UniswaJavaApplication\\src\\uniswajavaapplication\\logo.png"));
            logoImage.setBounds(260,10,290,120);
            displayList.setBounds(480,150,200,50);
            location1.setBounds(80,150,350,25);
            location2.setBounds(80,270,350,25);
            btn1.setBounds(200,180,80,30);
            btn2.setBounds(290,180,140,30);
            btn3.setBounds(200,300,80,30);
            btn4.setBounds(290,300,140,30);
            btn5.setBounds(550,420,80,30);
            btn1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                
                    JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                    j.setMultiSelectionEnabled(true);
                    int r = j.showOpenDialog(null);
                    JLabel lecturerFileList = new JLabel();
                    if(r == JFileChooser.APPROVE_OPTION){
                        files = j.getSelectedFiles();
                        for(int i =0;i<files.length; i++){
                            System.out.println(files[i]);
                        }
                        
                        
                        location1.setText(files.length+" files selected");
                        //lecturerFileList.setText(j.getSelectedFile().getAbsolutePath());
                    }else{
                        location1.setText("the user cancelled the operation.");
                    }
                    
                    l.add(lecturerFileList);
                }
            });
            btn2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    
                    trying = getLecturersAndCourses(files);
                    System.out.println(trying);
                    JOptionPane.showMessageDialog(null, trying.toString());
                }
            });
            btn3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                    int r = j.showOpenDialog(null);
                    if(r == JFileChooser.APPROVE_OPTION){
                        location2.setText(j.getSelectedFile().getAbsolutePath());
                    }else{
                        location2.setText("User cancelled the operation");
                    }
                }
            });
            btn4.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    datesList = getDatesVenuesAndTimes(location2.getText().toString());
                    //JOptionPane.showMessageDialog(null, datesList.toString());
                    //System.out.println(datesList.toString());
                }
            });
            
            btn5.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    try{
                        List all = mergeLists(trying, datesList);
                        sortList(all);
                        start begin = new start();
                        OutputFile out = new OutputFile();
                        out.getList(all);
                        begin.setPanel(FileUpload.this, out);
                    
                        //sortList(all);
                    }catch(Exception ex){
                        System.out.println(ex.toString());
                    }
                }
            });
            
            setLayout(null);
            add(courseLabel);
            add(datesAndTimes);
            add(logoImage);
            add(location1);
            add(location2);
            add(btn1);
            add(btn2);
            add(btn3);
            add(btn4);
            add(btn5);
            setVisible(true);
        }
        
        private void compareLists(List list1, List list2){
           
           //list2.removeAll(list1);
           //System.out.println(list2);
           
           //New code to merge array lists without having duplicates
           Set<String> set = new LinkedHashSet<>(list1);
           set.addAll(list2);
           
           List<String> combinedList = new ArrayList<>(set);
           System.out.println(combinedList);
        }
        
        private List mergeLists(List list1, List list2){
            Lecturer lecturer;
            VenuesDatesAndQuantity venues;
            Allocations allocation1;
            List allocations = new ArrayList();
            
            
            
            for(int i=0; i<list1.size(); i++){
                lecturer = (Lecturer)list1.get(i);

                if(lecturer.getCourses() != null){
                    for(int j=0; j<list2.size(); j++){
                        venues = (VenuesDatesAndQuantity) list2.get(j);
                        if(venues.getCourse() != null && venues.getDate() != "no date"  && venues.getVenue() != "no venue"){
                            allocation1 = new Allocations();
                            if(allocations ==  null){
                            allocation1.getLecturerList(lecturer.getName());
                            allocation1.setCount(lecturer.getCount());
                            allocation1.setCourse(lecturer.getCourses());
                            allocation1.setVenue(venues.getVenue());
                            allocation1.setDateAndTime(venues.getDate());
                            allocation1.setQuantity(venues.getQuantity());
                        
                            allocations.add(allocation1);
                        }else{
                            if(lecturer.getCourses().equals(venues.getCourse())){
                                Allocations temp;
                                boolean added = false;
                                for(int k=0;k < allocations.size(); k++){
                                    temp = (Allocations)allocations.get(k);
                                    if(temp.getDateAndTime().equals(venues.getDate()) && temp.getVenue().equals(venues.getVenue())){
                                        if(temp.findLecturer(lecturer.getName())){
                                            //temp.getCourseList(venues.getCourse());
                                            temp.setQuantity(venues.getQuantity());
                                            lecturer.setCount();
                                            temp.setCount(lecturer.getCount());
                                        }else{
                                            temp.getLecturerList(lecturer.getName());
                                            temp.getCourseList(venues.getCourse());
                                            temp.setQuantity(venues.getQuantity());
                                            temp.setCount(lecturer.getCount());
                                        }
                                        added = true;
                                    }
                                }
                            
                                while(added == false){
                                    allocation1.getLecturerList(lecturer.getName());
                                    allocation1.setCourse(lecturer.getCourses());
                                    allocation1.setCount(lecturer.getCount());
                                    allocation1.setVenue(venues.getVenue());
                                    allocation1.setDateAndTime(venues.getDate());
                                    allocation1.setQuantity(venues.getQuantity());
                                    allocations.add(allocation1);
                                    added = true;
                                }               
                            }
                        }
                    }
                }
            }
            }
            //System.out.println(allocations);
            return allocations;
        }
        
        
        private void ratio(List list){
            Allocations temp = null;
            List anotherLect = trying;
            List tempLects;
            Lecturer lect;
            double quant = 0.0;
            int smaller = 0;
            
            
            for(int i = 0; i< list.size(); i++){
                temp = (Allocations)list.get(i);
                tempLects = temp.getLecturer();
                quant = temp.getTotal();
                
                    for(int j=0;j<anotherLect.size(); j++){
                        lect = (Lecturer)anotherLect.get(j);
                        while(quant>temp.getLectureSize()*40){
                        if(tempLects.contains(lect.getName())){
                            break;
                        }else if(lect.getCount() == BigValue(anotherLect) && temp.getCount().contains(BigValue(anotherLect)-1)){
                            smaller = findSmaller(anotherLect);
                            lect = (Lecturer)anotherLect.get(smaller);
                            if(tempLects.contains(lect.getName())){
                               //lect.setCount(); DO nothing
                            }else{
                               temp.getLecturerList(lect.getName());
                               lect.setCount();
                            }
                        }else if(lect.getCount() == BigValue(anotherLect) -1 ){
                            temp.getLecturerList(lect.getName());
                            lect.setCount();
                        }else if(lect.getCount() == BigValue(anotherLect) && BigValue(anotherLect) == 5){
                            continue;
                        }else if(quant<temp.getLectureSize()*40){
                            System.out.println("Quantity: "+quant+"\nSize: "+temp.getLectureSize());
                            break;
                        }
                        }
                    }
                
            }
            //System.out.println(list);
            System.out.println(anotherLect);
        }
        
        private int BigValue(List list){
            int value = 0;
            Lecturer lect;
            for(int i=0;i<list.size(); i++){
                lect = (Lecturer)list.get(i);
                
                if(lect.getCount() > value){
                    value = lect.getCount();
                }
            }
            
            return value;
        }
        
        public int findSmaller(List list){
            int smaller = 10;
            Lecturer lect; 
            int temp = 0;
            
            for (int i = 0; i < list.size(); i++){
                lect= (Lecturer)list.get(i);
                if(lect.getCount() < smaller){
                    smaller = lect.getCount();
                    temp = i;
                } 
            }
            
            return temp;
        }
        
       
        private void addInformation(List list, Allocations allocation,Lecturer lecturer, VenuesDatesAndQuantity venues){
            if(list.isEmpty()){
                allocation.getLecturerList(lecturer.getName());
                allocation.getCourseList(venues.getCourse());
                allocation.setQuantity(venues.getQuantity());
                list.add(allocation);
            }
            
            
        
        }
        
        private void sortList(List list1){
            
            System.out.println("Before selection sort:");
            System.out.println(list1);       //output list before the sort
            
            Collections.sort(list1, new DateComparator());
            System.out.println("\n\nAfter selection sort\n\n");           
            System.out.println(list1);
            
            //adding chief Invigilator
            ChiefInvigilator chief = new ChiefInvigilator();
            chief.setChiefInvigilator(list1);
            System.out.println(list1);

        }
                
        private List getLecturersAndCourses(File[] file){
            List lecturers = new ArrayList();
            FileInputStream fis = null;
            
           
            for(int a = 0; a<file.length;a++){
                String filename = (String)file[a].getAbsolutePath();
                try{
                fis = new FileInputStream(filename);
                Workbook workbook = new XSSFWorkbook(fis);
                int numOfSheets = workbook.getNumberOfSheets();
                boolean yes = false;
                
                for(int i = 0; i < numOfSheets; i++){
                    
                    Sheet sheet = workbook.getSheetAt(i);
                    Iterator rowIterator = sheet.iterator();
                    while(rowIterator.hasNext()){
                        Lecturer lecturer = new Lecturer();
                        Lecturer temp = new Lecturer();
                        Row row = (Row)rowIterator.next();
                        Iterator cellIterator = row.cellIterator();
                        
                        while(cellIterator.hasNext()){
                            Cell cell = (Cell)cellIterator.next();
                            String cellValue = String.valueOf(cell.getStringCellValue());
                            
                            switch(cell.getCellType()){
                                case Cell.CELL_TYPE_STRING:
                                    if(Pattern.matches("^\\w.+\\d+.$", cellValue)){
                                        if(lecturer.getCourses() == null){
                                            lecturer.setCourses(String.valueOf(cell.getStringCellValue()));
                                        }else{
                                            if(lecturer.getName() != null){
                                                temp.setName(lecturer.getName());
                                                temp.setCourses(String.valueOf(cell.getStringCellValue()));
                                                temp.setCount();
                                                yes = true;
                                                break;
                                            }
                                        }
                                    }  else if(Pattern.matches("^\\w.+\\w.*$", cellValue) ){
                                        lecturer.setName(String.valueOf(cell.getStringCellValue()));
                                    }
                                    break;
                                default:
                                    //System.out.println("Wrong type for lecturers");
                            }
                            //check if a lecturer with the same name exists in the list and increment count if true.
                            countLecturerName(lecturers, lecturer);
                        }
                        if(yes == true){
                            lecturers.add(temp);
                            yes = false;
                        }
                        lecturers.add(lecturer);
                    }
                    
                }
                fis.close();
            }catch(FileNotFoundException e){
                e.printStackTrace();
            }catch(IOException e){
                e.printStackTrace();
            }
            }
            
            return lecturers;
        }
        
        private void countLecturerName(List list, Lecturer l){
            int count = 0;
            Lecturer lect = null;
            
                try{
                    for(int i = 0; i < list.size(); i++){
                        lect = (Lecturer)list.get(i);
                        if(lect != null){
                            if(l.getName().equals(lect.getName())){
                                count = count + 1;
                            }
                        }else{
                            System.out.println("Problem at "+ i);
                        }
                    }
                    l.setCount(count + 1);
                    System.out.println(l.getCount());
                }catch(Exception e){
                    System.out.println(e);
                }
        }
        
        private List getDatesVenuesAndTimes(String filename){
            List dates = new ArrayList();
            FileInputStream fis = null;
            String date = "no date";
            String date2 = "no date";
            String v = "no venue";
            String v2 = "no venue";
            try{
                fis = new FileInputStream(filename);
                Workbook workbook = new XSSFWorkbook(fis);
                int numOfSheets = workbook.getNumberOfSheets();
                System.out.println("Sheets: "+numOfSheets);
                for(int i=0;i<numOfSheets;i++){
                    Sheet sheet = workbook.getSheetAt(i);
                    Iterator rowIterator = sheet.rowIterator();
                    
                    while(rowIterator.hasNext()){
                        VenuesDatesAndQuantity data1 = new VenuesDatesAndQuantity();
                        VenuesDatesAndQuantity data2 = new VenuesDatesAndQuantity();
                        Row row = (Row)rowIterator.next();
                        Iterator cellIterator = row.cellIterator();
                        
                        while(cellIterator.hasNext()){
                            Cell cell = (Cell)cellIterator.next();
                            
                            if(cell.getColumnIndex() <= 2){
                                switch(cell.getColumnIndex()){
                                    case 0:
                                        //System.out.println(String.valueOf(cell.getStringCellValue()));
                                        if(cell.getCellType() == Cell.CELL_TYPE_STRING){
                                            data1.setCourse(String.valueOf(cell.getStringCellValue()));
                                        }
                                        if(Pattern.matches("^\\d+\\s\\w+\\s\\d+\\s\\w+$", String.valueOf(cell.getStringCellValue()))){
                                            data1.setDate(String.valueOf(cell.getStringCellValue()));
                                            date = String.valueOf(cell.getStringCellValue());
                                        } 
                                        break;
                                    case 1:
                                        if(cell.getCellType() == Cell.CELL_TYPE_STRING){
                                            if(checkVenues(String.valueOf(cell.getStringCellValue()),data1)){
                                                v = String.valueOf(cell.getStringCellValue());
                                            }else{
                                                data1.setProgram(String.valueOf(cell.getStringCellValue()));
                                            }
                                        }
                                        break;
                                    case 2:
                                        if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
                                            data1.setQuantity(cell.getNumericCellValue());
                                        }
                                        break;
                                    default:
                                        //do nothing for now;
                                        break;
                                }
                            }else if(cell.getColumnIndex() >=3 && cell.getColumnIndex() <=5){
                                switch(cell.getColumnIndex()){
                                    case 3:
                                        //System.out.println(String.valueOf(cell.getStringCellValue()));
                                        data2.setCourse(String.valueOf(cell.getStringCellValue()));
                                        break;
                                    case 4:
                                        if(cell.getCellType() == Cell.CELL_TYPE_STRING){
                                            if(checkVenues(String.valueOf(cell.getStringCellValue()),data2)){
                                                v2 = String.valueOf(cell.getStringCellValue());
                                            }else{
                                                data2.setProgram(String.valueOf(cell.getStringCellValue()));
                                            }
                                        }
                                        break;
                                    case 5:
                                        if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
                                            data2.setQuantity(cell.getNumericCellValue());
                                        }
                                        break;
                                    default:
                                        //do nothing for now;
                                        break;
                                }
                            }
                        }
                        
                        if(data1.getVenue() == null){
                            data1.setVenue(v);
                        }
                        if(data1.getDate() == null){
                            data1.setDate(date);
                        }
                        
                        dates.add(data1);
                        
                        if(data2.getVenue() == null){
                            data2.setVenue(v2);
                        }
                        if(data2.getDate() == null){
                            data2.setDate(date);
                        }
                        
                        
                        dates.add(data2);
                    }
                }
                
                System.out.println(dates);
                fis.close();
            }catch(FileNotFoundException e){
                e.printStackTrace();
            }catch(IOException e){
                e.printStackTrace();
            }
            
            return dates;
        }
        
        public boolean checkVenues(String venue, VenuesDatesAndQuantity v){
            
            switch(venue){
                case "EMPORIUM":
                    v.setVenue(venue);
                    return true;
                case "CHAPEL":
                    v.setVenue(venue);
                    return true;
                case "MPH":
                    v.setVenue(venue);
                    return true;
                case "New Classroom":
                    v.setVenue(venue);
                    return true;
                case "Tent 1":
                    v.setVenue(venue);
                    return true;
                case "Tent 2":
                    v.setVenue(venue);
                    return true;
                case "Tent 3":
                    v.setVenue(venue);
                    return true;
                default:
                    return false;
            }
        }
}