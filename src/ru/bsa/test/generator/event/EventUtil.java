package ru.bsa.test.generator.event;

import java.util.Random;

public class EventUtil {


    private static final Random RANDOM = new Random(System.currentTimeMillis());

    /*
    * Generate delay for the next event (in msec)
    *
    * @param eventType current event type
    * */
    public static long generateNextEventDelay(final EventType eventType) {

        switch (eventType) {
            case START:
                return getRandomNumberInRange(3000, 10000);
            case JOIN:
                return getRandomNumberInRange(15000, 60000);
            default:
                throw new RuntimeException("Can't generate delay for event with type = " + eventType);
        }


    }

    private static long getRandomNumberInRange(int min, int max) {
        return RANDOM.nextInt(max - min) + min;
    }


    public static ServiceType getRandomServiceType() {
        return ServiceType.values()[RANDOM.nextInt(ServiceType.values().length)];
    }

    public static OriginationPage getRandomOriginationPage() {
        return OriginationPage.values()[RANDOM.nextInt(OriginationPage.values().length)];
    }

    public static OriginationChannel getRandomOriginationChannel() {
        return OriginationChannel.values()[RANDOM.nextInt(OriginationChannel.values().length)];
    }

    public static String getRandomAgentId() {
        return "Agent_" + System.currentTimeMillis() % 0xd3;
    }


    public static EndReason getRandomEndReason() {
        return EndReason.values()[RANDOM.nextInt(EndReason.values().length)];
    }


}
