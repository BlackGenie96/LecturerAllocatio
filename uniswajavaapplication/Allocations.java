/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uniswajavaapplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Fanelesibonge Malaza
 */
public class Allocations {
    
    private String lecturer;
    private String course;
    private String venue;
    private String dateAndTime;
    private double quantity;
    private double total = 0.0;
    private int count = 0;
    private List countList = new ArrayList();
    private List lecturerList = new ArrayList<String>();
    private List courseList = new ArrayList();
    private List chiefsList = new ArrayList();
    
    public Allocations(){}
    
    public Allocations(String lecturer, String course, String venue, String dateAndTime, double quantity){
        this.lecturer = lecturer;
        this.course = course;
        this.venue = venue;
        this.dateAndTime = dateAndTime;
        this.quantity = quantity;
    }

    public void getLecturerList(String lec){
        this.lecturerList.add(lec);
    }
    
    public List getLecturer() {
        return this.lecturerList;
    }

    public int getLectureSize(){
        return this.lecturerList.size();
    }
    
    public void setLecturer(String lecturer) {
        this.lecturer=lecturer;
        getLecturerList(lecturer);
    }
    
    public String getLecturerName(int index){
        String name = (String)this.lecturerList.get(index);
        return name;
    }

    public void addToPosition(String name, int index){
        this.lecturerList.remove(index);
        this.lecturerList.add(index, name);
    }
    
    public void setCount(int count){
        getCountList(count);
    }
    
    public void getCountList(int count){
        this.countList.add(count);
    }
    
    public List getCount(){
        return this.countList;
    }
    public List getCourse() {
        return this.courseList;
    }

    public void getCourseList(String cour){
        this.courseList.add(cour);
    }
    
    public String getCourseName(int index){
        String name = (String)this.courseList.get(index);
        return name;
    }
    
    public int getCourseSize(){
        return this.courseList.size();
    }
    
    public void setCourse(String course) {
        this.courseList.add(course);
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public double getQuantity() {
        return quantity;
    }

    public double getTotal(){
        return this.total;
    }
    
    public void setQuantity(double quantity) {
        this.quantity = quantity;
        this.total = getTotal(quantity);
    }
    
    private double getTotal(double quantity){
        double result = this.total;
        result = result + quantity;
        return result;
    }
    
    @Override
    public String toString(){
        String result =  "\n\nTimeTable:\n"+
                "\nTime and Date : \t"+this.dateAndTime +
                "\nVenue: \t"+ this.venue+
                "\nCourses : \t "+this.courseList+
                "\nInvigilators: \t"+this.lecturerList+
                "\nTotal Students: \t"+ this.total;
            
            return result;
    }
    
    public boolean findLecturer(String name){
        boolean found = false;
        
        if(this.lecturerList.contains(name)){
            found = true;
        }
        
        return found;
    }
    
    public String bringLecturer(int position){
        String name = (String)lecturerList.get(position);
        return name;
    }
}
