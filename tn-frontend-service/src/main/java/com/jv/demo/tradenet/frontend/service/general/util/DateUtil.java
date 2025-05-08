package com.jv.demo.tradenet.frontend.service.general.util;

import lombok.extern.apachecommons.CommonsLog;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@CommonsLog
public class DateUtil {
    public static final String FULL_DTM_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static LocalDateTime convertToLocalDtm(String dateStr) {
        try {
            return LocalDateTime.parse(dateStr);
        }catch (DateTimeParseException p) {
            log.error("Unable to parse the Date String:" + dateStr, p);
        }
        return null;
    }

    public static String convertToStr(LocalDateTime ldtm) {
        try {
            return ldtm.format(DateTimeFormatter.ofPattern(FULL_DTM_FORMAT));
        }catch (DateTimeParseException p) {
            log.error("Unable to parse the LocalDateTime:" + ldtm, p);
        }
        return null;
    }

    public static Date convertToSqlDtm(String dateStr) {
        DateFormat dateFormat = new SimpleDateFormat(FULL_DTM_FORMAT);
        try{
            Date sqlDate = new Date(dateFormat.parse(dateStr).getTime());
            return sqlDate;
        }catch(ParseException p) {
            log.error("Unable to parse the Date String:" + dateStr, p);
        }
        return null;
    }

    public static String convertToStr(Date sqlDate) {
        DateFormat dateFormat = new SimpleDateFormat(FULL_DTM_FORMAT);
        return dateFormat.format(new java.util.Date(sqlDate.getTime()));
    }
}
