package ru.bsa.test.generator.tasks;

import ru.bsa.test.filewriter.EventFileWriter;
import ru.bsa.test.generator.event.Event;
import ru.bsa.test.generator.event.EventType;
import ru.bsa.test.generator.event.EventUtil;

import java.io.FileWriter;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class JoinEventRunnable implements Runnable {

    private final ScheduledExecutorService scheduledThreadPoolExecutor;
    private final Event event;
    private final EventFileWriter fileWriter;
    private final CountDownLatch latch;


    public JoinEventRunnable(ScheduledExecutorService scheduledThreadPoolExecutor, Event event,
                             EventFileWriter fileWriter, CountDownLatch latch) {
        this.scheduledThreadPoolExecutor = scheduledThreadPoolExecutor;
        this.event = event;
        this.fileWriter = fileWriter;
        this.latch = latch;
    }

    @Override
    public void run() {
        //fill event
        long delay = EventUtil.generateNextEventDelay(event.getEventType());

        event.setEventType(EventType.JOIN);
        event.setDeliveryTime(new Date());
        event.setAgentId(EventUtil.getRandomAgentId());

        System.out.println("Join event: " + event);

        //write
        fileWriter.write(event);

        //schedule
        StopEventRunnable stopEventRunnable = new StopEventRunnable(event, fileWriter, latch);
        scheduledThreadPoolExecutor.schedule(stopEventRunnable, delay, TimeUnit.MILLISECONDS);

        latch.countDown();

    }

}
