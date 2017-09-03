package ru.bsa.test.generator.tasks;

import ru.bsa.test.filewriter.EventFileWriter;
import ru.bsa.test.filewriter.exception.EventFileWriterException;
import ru.bsa.test.generator.event.Event;
import ru.bsa.test.generator.event.EventType;
import ru.bsa.test.generator.event.EventUtil;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class StartEventRunnable implements Runnable {

    private final ScheduledExecutorService scheduledThreadPoolExecutor;
    private int counter;
    private int countAtTime;
    private EventFileWriter fileWriter;
    private CountDownLatch countDownLatch;


    public StartEventRunnable(final ScheduledExecutorService scheduledThreadPoolExecutor,
                              final int counter,
                              final int countAtTime,
                              final EventFileWriter fileWriter,
                              final CountDownLatch countDownLatch) {
        this.scheduledThreadPoolExecutor = scheduledThreadPoolExecutor;
        this.counter = counter;
        this.countAtTime = countAtTime;
        this.fileWriter = fileWriter;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {


        while (counter > 0) {

            Long start = System.currentTimeMillis();

            for (int i = 0; i < countAtTime && counter > 0; i++, counter--) {

                //create event
                Event event = new Event(UUID.randomUUID(), EventType.START, new Date(), new Date(),
                        EventUtil.getRandomServiceType(),
                        EventUtil.getRandomOriginationPage(),
                        EventUtil.getRandomOriginationChannel());

                //write event

                Event copy = event.copy();

                try {
                    fileWriter.write(event);
                } catch (EventFileWriterException e) {
                    throw new RuntimeException(e);
                }

                //create join scheduled event
                final long delay = EventUtil.generateNextEventDelay(event.getEventType());

                JoinEventRunnable joinEventRunnable = new JoinEventRunnable(scheduledThreadPoolExecutor, copy, fileWriter, countDownLatch);
                scheduledThreadPoolExecutor.schedule(joinEventRunnable, delay, TimeUnit.MILLISECONDS);

            }

            Long end = System.currentTimeMillis();


            try {
                Thread.sleep(1000L - (end - start));
            } catch (InterruptedException e) {
                //skip it
            }

        }


//        if (counter > 0) {
//            scheduledThreadPoolExecutor.schedule(this, 1L, TimeUnit.SECONDS);
//        }


    }
}
