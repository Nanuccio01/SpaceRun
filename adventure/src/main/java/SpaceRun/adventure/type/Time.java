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
    
        public static String getTime() {
        String time;  
        LocalTime Coomplete_time = LocalTime.now();
        int hour = Coomplete_time.getHour();
        int minute = Coomplete_time.getMinute();
        if(minute<10){
            time = hour+":"+0+minute;
        }else time = hour+":"+minute;
        return time;
    }
}
