package com.mimogoods.dev.tools.commons.utils;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Objects;

public interface StandardFunctions {
    static Double add(Double a, Double b) {
        return a + b;
    }
    static boolean isNull(Object object) {
        return Objects.isNull(object);
    }

    static boolean nonNull(Object object) {
        return Objects.nonNull(object);
    }
    static <T> boolean present(T t) {
        return Objects.nonNull(t);
    }

    static boolean greaterOrEqualsThanZero(Integer i) {
        return i.compareTo(0) >= 0;
    }

    static <T extends Comparable> boolean greaterOrEqualsThan(T a, T b) {
        return a.compareTo(b) >= 0;
    }

    static <T extends Comparable> boolean lesserOrEqualsThan(T a, T b) {
        return a.compareTo(b) <= 0;
    }
    static boolean greaterOrEqualsThanZero(Double d) {
        return d.compareTo((double) 0) >= 0;
    }

    static boolean greaterThanZero(Double object) {
        return object.compareTo((double) 0) > 0;
    }

    static boolean lesserOrEqualsThanZero(Integer object) {
        return object.compareTo(0) <= 0;
    }

    static boolean lesserOrEqualsThanZero(Double object) {
        return object.compareTo((double) 0) <= 0;
    }

    static <T> boolean equal(T a, T b) {
        return a.equals(b);
    }

    static boolean greaterThanToday(Date date) {
        return ZonedDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).toLocalDateTime()
                .isAfter(LocalDateTime.now().truncatedTo(ChronoUnit.DAYS));
    }
    static boolean greaterOrEqualsThanToday(Date date) {
        return ZonedDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).toLocalDateTime()
                .compareTo(LocalDateTime.now().truncatedTo(ChronoUnit.DAYS)) >= 0;
    }
    static boolean blank(String s) {
        return StringUtils.isBlank(s);
    }

}
