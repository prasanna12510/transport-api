package com.cloudsre.services.transport_service.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtils {
    private static final Logger log = LoggerFactory.getLogger(DateUtils.class);

    static final String DATE_FORMAT = "HH:MM:SS";

    public static Timestamp converToTimeStamp(String date) {

        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        Date dateObj = null;
        Timestamp timestamp = null;
        try {
            dateObj = dateFormat.parse(date);

            timestamp = new Timestamp(dateObj.getTime());
            return timestamp;
        } catch (ParseException e) {
            log.error("failed to parse date for time row ");
            log.error(e.getMessage());
            return null;
        }

    }
    
    
    public static Timestamp addMinutesDelayed(Date current, Long delayedMinutes) {
    	
    	Timestamp newTimestamp = new Timestamp(current.getTime());
    	newTimestamp.setTime(newTimestamp.getTime() + TimeUnit.MINUTES.toMillis(delayedMinutes));
    	
    	return newTimestamp;
    	
    }
}