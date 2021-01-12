package com.everyportfolio.user.utility;

import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class DateUtility {
    private Calendar calendar;

    public DateUtility() {
        this.calendar = Calendar.getInstance();
    }

    public Date addMintueByCurDate(int minute) {
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, minute);

        return calendar.getTime();
    }

    public Date addWeekByCurDate(int week) {
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_WEEK, week);

        return calendar.getTime();
    }
}
