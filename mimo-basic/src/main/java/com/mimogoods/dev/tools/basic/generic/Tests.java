package com.mimogoods.dev.tools.basic.generic;

import com.mimogoods.dev.tools.basic.exception.TestDelegateException;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.concurrent.TimeUnit;

public class Tests {
    public static void main(String[] args) {
        for (int i = 0 ;i < 100000; i ++) {
            try {
                TimeUnit.NANOSECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new TestDelegateException(e, "Interrupted exception during sleep.", "-1");
            }
            Instant now = LocalDateTime.now(Clock.systemUTC()).atZone(ZoneId.systemDefault()).toInstant();
//          System.out.println(String.valueOf(now.toEpochMilli()).substring(3, 13) + String.format("%03d",now.getNano()).substring(0,3));
            System.out.println(String.format("%03s",now.getNano()));
        }
    }
}
