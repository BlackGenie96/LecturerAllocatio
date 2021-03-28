/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uniswajavaapplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.regex.Pattern;

/**
 *
 * @author Fanelesibonge Malaza
 */
public class DateComparator implements Comparator<Allocations> {
    
    @Override
    public int compare(Allocations o1, Allocations o2) {
        //compare the date strings
        String allocations1 = o1.getDateAndTime();
        String allocations2 = o2.getDateAndTime();
        
        String[] date1 = allocations1.split(" ");
        String day1 = date1[0];
        String month1 = convertMonth(date1[1]);
        String year1 = date1[2];
        String time1 = getTime(date1[3]);
        String newDate1 = day1+"-"+month1+"-"+year1+" "+time1;
        
        String[] date2 = allocations2.split(" ");
        String day2 = date2[0];
        String month2 = convertMonth(date2[1]);
        String year2 = date2[2];
        String time2 = getTime(date2[3]);
        String newDate2 = day2+"-"+month2+"-"+year2+" "+time2;
        Date date_1 = null;
        Date date_2 = null;
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss a");
            date_1 = sdf.parse(newDate1);
            date_2 = sdf.parse(newDate2);
            
        }catch(ParseException ex){
            System.out.println(ex.toString());
        }
        
        if(date_1.compareTo(date_2) == 0){
            return 0;
        }else if(date_1.compareTo(date_2) > 0){
            return 1;
        }else{
            return -1;
        }
        
    }
    
    private String convertMonth(String month){
            String equiv = "00";

            if(Pattern.matches("^.*(\\bJanuary\\b).*$", month)){
                equiv = "01";
                //System.out.println(equiv);
            }
            if(Pattern.matches("^.*(\\bFebruary\\b).*$", month)){
                equiv = "02";
                //System.out.println(equiv);
            }
            if(Pattern.matches("^.*(\\bMarch\\b).*$", month)){
                equiv = "03";
                //System.out.println(equiv);
            }
            if(Pattern.matches("^.*(\\bApril\\b).*$", month)){
                equiv = "04";
                //System.out.println(equiv);
            }
            if(Pattern.matches("^.*(\\bMay\\b).*$", month)){
                equiv = "05";
                //System.out.println(equiv);
            }
            if(Pattern.matches("^.*(\\bJune\\b).*$", month)){
                equiv = "06";
                //System.out.println(equiv);
            }
            if(Pattern.matches("^.*(\\bJuly\\b).*$", month)){
                equiv = "07";
                //System.out.println(equiv);
            }
            if(Pattern.matches("^.*(\\bAugust\\b).*$", month)){
                equiv = "08";
                //System.out.println(equiv);
            }
            if(Pattern.matches("^.*(\\bSeptember\\b).*$", month)){
                equiv = "09";
                //System.out.println(equiv);
            }
            if(Pattern.matches("^.*(\\bOctober\\b).*$", month)){
                equiv = "10";
                //System.out.println(equiv);
            }
            if(Pattern.matches("^.*(\\bNovember\\b).*$", month)){
                equiv = "11";
                //System.out.println(equiv);
            }
            if(Pattern.matches("^.*(\\bDecember\\b).*$", month)){
                equiv = "12";
                //System.out.println(equiv);
            }
            
            return equiv;
        }
    
    private String getTime(String examTime){
            String time = null;
            
            switch(examTime){
                case "Afternoon":
                    time = "14:00:00 PM ";
                    break;
                case "Morning":
                    time = "09:00:00 AM";
                    break;
                case "afternoon":
                    time = "14:00:00 PM";
                    break;
                case "morning":
                    time = "09:00:00 AM";
                    break;
                default:
                    time = "Time has not been specified.";
            }
            return time;
        }
    
}
