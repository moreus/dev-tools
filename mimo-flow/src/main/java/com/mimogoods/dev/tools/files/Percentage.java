package com.mimogoods.dev.tools.files;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

/**
 *
 */
public class Percentage {

    public static final int FACTOR = 10;
    public static final float THOUSANDTH = 10000;

    static class Event {
        String category;
        Object event;

        public Event(String category) {
            this.category = category;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public Object getEvent() {
            return event;
        }

        public void setEvent(Object event) {
            this.event = event;
        }

        @Override
        public String toString() {
            return "Event{" +
                    "category='" + category + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {
        int error = 2;
        int timeout = 3;
        int rollbackTimeout = 5;
        int total = 100000;
        int min = 0;
        int max = total * FACTOR;
        Set<Integer> set = new Random().ints(min, max)
                .distinct()
                .limit(total)
                .boxed()
                .collect(Collectors.toSet());
        Map<Integer, Event> eventMap = new HashMap<>();
        eventMap = generateEvent(error, total, set, eventMap, new Event("E"));
        eventMap = generateEvent(timeout, total, set, eventMap, new Event("T"));
        eventMap = generateEvent(rollbackTimeout, total, set, eventMap, new Event("R"));

        Set<Integer> newSet = new HashSet<>(set);
        newSet.removeAll(eventMap.keySet());
        eventMap.entrySet().stream().forEach(e -> System.out.println(e.getKey() + ":" + e.getValue()));
    }

    @NotNull
    private static Map<Integer, Event> generateEvent(int percentage, int total, Set<Integer> keys, Map<Integer, Event> eventMap, Event e) {
        Random r = new Random();
        int i = 0, range = total * FACTOR, key = r.nextInt(range);
        int errorNum = (int) (percentage * total / THOUSANDTH);
        while (i < errorNum) {
            if (keys.contains(key) && !eventMap.containsKey(key)) {
                eventMap.put(key, e);
                i++;
            }
            key = r.nextInt(range);

        }
        return eventMap;
    }
}
