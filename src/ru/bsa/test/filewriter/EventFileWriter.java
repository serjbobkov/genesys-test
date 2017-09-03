package ru.bsa.test.filewriter;

import ru.bsa.test.filewriter.exception.EventFileWriterException;
import ru.bsa.test.generator.event.Event;
import ru.bsa.test.generator.event.EventJSONConverter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class EventFileWriter extends Thread {

    private final BlockingQueue<Event> queue = new LinkedBlockingQueue<>();
    private final File file;
    private volatile boolean canWrite = false;


    public static final Event FINISH_EVENT = new Event();

    public EventFileWriter(final File file) {
        this.file = file;
    }

    public void write(final Event event) throws EventFileWriterException {
        if (canWrite) {
            try {
                queue.put(event);
            } catch (InterruptedException e) {
                throw new EventFileWriterException(e);
            }
        } else {
            throw new EventFileWriterException("Can't write to file");

        }
    }


    @Override
    public void run() {

        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file))) {

            canWrite = true;

            fileWriter.write("[");
            boolean isFirst = true;


            while (true) {

                Event event = queue.take();

                if (event != FINISH_EVENT) {

                    if (!isFirst) {
                        fileWriter.write(",");
                    } else {
                        isFirst = false;
                    }

                    fileWriter.write("\n" + EventJSONConverter.toJSON(event));

                } else {
                    //close all

                    fileWriter.write("\n]");
                    fileWriter.flush();
                    break;
                }
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            canWrite = false;
        }

    }

}
