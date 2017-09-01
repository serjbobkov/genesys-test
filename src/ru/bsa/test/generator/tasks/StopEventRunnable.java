package ru.bsa.test.generator.tasks;

import ru.bsa.test.filewriter.EventFileWriter;
import ru.bsa.test.generator.event.Event;
import ru.bsa.test.generator.event.EventType;
import ru.bsa.test.generator.event.EventUtil;

import java.util.Date;
import java.util.concurrent.CountDownLatch;


public class StopEventRunnable implements Runnable {

    private final Event event;
    private final EventFileWriter eventFileWriter;
    private CountDownLatch latch;

    public StopEventRunnable(final Event event, final EventFileWriter eventFileWriter, final CountDownLatch latch) {
        this.event = event;
        this.eventFileWriter = eventFileWriter;
        this.latch = latch;
    }

    @Override
    public void run() {
        //set params
        event.setEndTime(new Date());
        event.setEventType(EventType.END);
        event.setEndReason(EventUtil.getRandomEndReason());

        System.out.println("Stop event: " + event);

        eventFileWriter.write(event);

        latch.countDown();

    }
}
