/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SpaceRun.adventure.type;

import java.time.LocalTime;

/**
 *
 * @author Gaetano
 */
public class Time {
    
    public static String addZero(int num){
        String numString = "";  
        
        if (num < 10) {
            numString = "0" + num;
        }
        return numString;
    }
    
    
    public static String getTime() {
        String time;  
        
        LocalTime Coomplete_time = LocalTime.now();
        String hours = String.valueOf(addZero(Coomplete_time.getHour()));
        String minutes = String.valueOf(addZero(Coomplete_time.getMinute()));
        String seconds = String.valueOf(addZero(Coomplete_time.getSecond()));
        
        time = hours+":"+minutes+":"+seconds;
        return time;
    }
}
