package com.midhilaj.cocoalabs.cocoalabs;

import com.orm.SugarRecord;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by midhilaj on 10/16/18.
 */

public class TimeDetails extends SugarRecord{
    Date currentTime = Calendar.getInstance().getTime();
    public int get__id() {
        return __id;
    }

    public void set__id(int __id) {
        this.__id = __id;
    }

    int __id;
    public TimeDetails(int hour, int minute, int second){
        setHour(hour).setMinute(minute).setSecond(second);
    }
    public TimeDetails( ){

        setHour(currentTime.getHours()).setMinute(currentTime.getMinutes()).setSecond(currentTime.getSeconds());
    }

    @Override
    public long save() {

        return super.save();
    }

    int hour=0; //= instance.get(Calendar.HOUR);
    int minute =0;//= instance.get(Calendar.MINUTE);

    public int getHour() {
        return hour;
    }

    public TimeDetails setHour(int hour) {
        this.hour = hour;
        return this;
    }

    public int getMinute() {
        return minute;
    }

    public TimeDetails setMinute(int minute) {
        this.minute = minute;
        return this;
    }

    public int getSecond() {
        return second;
    }

    public TimeDetails setSecond(int second) {
        this.second = second;
        return this;
    }

    int second =0;// instance.get(Calendar.SECOND);

    public String getTime_() {
        return getHour()+":hr "+getMinute()+":min "+getSecond()+"sec";//..t;
    }

    public String getBattery_p() {
        return battery_p;
    }

    public TimeDetails setBattery_p(String battery_p) {
        this.battery_p = battery_p;
    return this;
    }

    String battery_p;

    public String getBatteryP() {
        return battery_p+"%";
    }
}
