package com.mimogoods.dev.tools.commons.utils;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

public class Converters {

    public static Integer getInteger(BigDecimal source) {
        return source == null ? null : source.intValue();
    }

    public static Integer getInteger(Integer source) {
        return source;
    }
    public static Double getDouble(BigDecimal source) {
        return source == null ? null : source.doubleValue();
    }

    public static Double getDouble(Double source) {
        return source;
    }

    public static BigDecimal getId(BigDecimal source) {
        return source == null ? null : source;
    }

    public static Date getDate(LocalDateTime source) {
        return source == null ? null : Timestamp.valueOf(source);
    }

    public static Date getDate(Date source) {
        return source;
    }

    public static String getString(String source) {
        return source;
    }

    public static String getString(Integer source) {
        return source.toString();
    }

    public static BigDecimal getBigDecimal(Double source) {
        return source == null ? null : new BigDecimal(source);
    }

    public static BigDecimal getBigDecimal(Integer source) {
        return source == null ? null : new BigDecimal(source);
    }

    public static Integer getInteger(String source) {
        return source == null ? null : Integer.parseInt(source);
    }

    public static LocalDateTime getLocalDateTime(Date source) {
        return source == null ? null : new Timestamp(source.getTime()).toLocalDateTime();
    }
    public static Boolean getBoolean(Boolean source) {
        return source;
    }
}
