package ru.bsa.test.generator.event;

import java.util.Random;

public class EventUtil {


    private static final Random random = new Random(System.currentTimeMillis());

    /*
    * Generate delay for the next event (in msec)
    *
    * @param eventType current event type
    * */
    public static long generateNextEventDelay(final EventType eventType) {

        if (EventType.START.equals(eventType)) {
            return getRandomNumberInRange(3000, 10000);
        } else if (EventType.JOIN.equals(eventType)) {
            return getRandomNumberInRange(15000, 60000);
        } else {
            throw new RuntimeException("Can't generate delay for event with type = " + eventType);
        }

    }

    private static long getRandomNumberInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        return random.nextInt((max - min) + 1) + min;
    }

    public static ServiceType getRandomServiceType() {
        return ServiceType.values()[(int) (System.currentTimeMillis() % ServiceType.values().length)];
    }

    public static OriginationPage getRandomOriginationPage() {
        return OriginationPage.values()[(int) (System.currentTimeMillis() % OriginationPage.values().length)];
    }

    public static OriginationChannel getRandomOriginationChannel() {
        return OriginationChannel.values()[(int) (System.currentTimeMillis() % OriginationChannel.values().length)];
    }

    public static String getRandomAgentId() {
        return "Agent_"+System.currentTimeMillis()%0xd3;
    }


    public static EndReason getRandomEndReason() {
        return EndReason.values()[(int) (System.currentTimeMillis() % EndReason.values().length)];
    }



}
