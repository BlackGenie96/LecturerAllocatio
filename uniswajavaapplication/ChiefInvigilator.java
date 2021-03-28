/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uniswajavaapplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ChiefInvigilator {

    private boolean chief = false;
    private String name;
    private int count;
    private List chiefList = new ArrayList();
    
    public ChiefInvigilator(String name, boolean chief, int count){
        this.name = name;
        this.count = count;
    }
    
    public ChiefInvigilator(){}
    
    public int getCount(){
        return this.count;
    }
    
    public String getName(){
        return this.name;
    }
    
    public boolean getIfChief(){
        return this.chief;
    }
    
    public void setChiefInvigilator(List lectList){
        List sideList = new ArrayList<>();
        for(int i = 0; i < lectList.size(); i++){
            Allocations allo = (Allocations)lectList.get(i);
            Random rand = new Random();
            int randomNumber = rand.nextInt(allo.getLectureSize());
            String newName = allo.getLecturerName(randomNumber);
            
            if(sideList.isEmpty()){
                newName = newName + "*";
                sideList.add(newName);
                allo.addToPosition(newName, randomNumber);
            }else if(!sideList.contains(newName)){
                newName = newName + "*";
                sideList.add(newName);
                allo.addToPosition(newName, randomNumber);
            }else{
                continue;
            }
            
            //TODO: Use lecturer class to keep count for each lecturer on the side list
            //while comparing with the existing lecturer list of information from the excel file
            //If there is no lecturer add them to the list and increment count, if they are already there, locate them in the list
            //and increment count, then use random number generator to select the chief invigilator
        }
        
    }
}