/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uniswajavaapplication;

/**
 *
 * @author Fanelesibonge Malaza
 */
public class VenuesDatesAndQuantity {
 
    private String courses;
    private String venue;
    private String date;
    private String program;
    private double total;
    private double quantity;
    
    
    public VenuesDatesAndQuantity(){
        //Empty Constructor
    }
    
    public VenuesDatesAndQuantity(String courses, String venue, String date, double quantity){
        this.courses = courses;
        this.venue = venue;
        this.date = date;
        this.quantity = quantity;
    }

    public String getCourse() {
        return courses;
    }

    public void setCourse(String courses) {
        this.courses = courses;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
    
    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }
    @Override
    public String toString(){
        return "Course: "+this.courses
                +"    Program: "+ this.program
                +"     Venue: "+this.venue
                +"   Date: "+this.date
                +"     Quantity: "+String.valueOf(this.quantity)+"\n";
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    
}
