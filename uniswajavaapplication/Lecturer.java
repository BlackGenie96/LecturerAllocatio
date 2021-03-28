/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uniswajavaapplication;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fanelesibonge Malaza
 */
public class Lecturer {

    private int id;
    private String name;
    private String courses;
    private List courseList = new ArrayList();
    private int count = 0;
    private boolean invigilator;

    public int getCount() {
        return count;
    }

    public void setCount() {
        this.count = getCount() + 1;
    }
    
    public void setCount(int Count){
        this.count = Count;
    }

    public boolean isInvigilator() {
        return invigilator;
    }

    public void setInvigilator(boolean invigilator) {
        this.invigilator = invigilator;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourses() {
        return courses;
    }

    public void setCourses(String courses) {
        this.courses = courses;
    }
    
    @Override
    public String toString(){
        return "Name: "+this.name+";   Courses: "+this.courses+";   Count: "+this.count+"\n";
    }
}
