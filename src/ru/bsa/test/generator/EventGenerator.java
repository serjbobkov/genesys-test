package ru.bsa.test.generator;

import ru.bsa.test.filewriter.EventFileWriter;
import ru.bsa.test.generator.tasks.StartEventRunnable;

import java.io.File;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class EventGenerator {

    private int count;
    private int frequence;
    private File file;

    public EventGenerator(final int count, final int frequence, final File file) {
        this.count = count;
        this.frequence = frequence;
        this.file = file;
    }

    public void generate() {

        synchronized (this) {

            //счетчик созданных событий
            CountDownLatch latch = new CountDownLatch(3 * count);

            //тред с очередью записи в файл
            EventFileWriter fileWriter = new EventFileWriter(file);
            fileWriter.start();


            //стартовое событие
            ScheduledExecutorService scheduledThreadPoolExecutor = Executors.newScheduledThreadPool(5);

            StartEventRunnable startEventRunnable = new StartEventRunnable(scheduledThreadPoolExecutor, count,
                    frequence, fileWriter, latch);
            scheduledThreadPoolExecutor.execute(startEventRunnable);



            try {
                latch.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            scheduledThreadPoolExecutor.shutdown();
            fileWriter.setOpen(false);


        }

    }
}
