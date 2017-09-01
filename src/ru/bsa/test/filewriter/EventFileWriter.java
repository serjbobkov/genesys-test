package ru.bsa.test.filewriter;

import ru.bsa.test.generator.event.Event;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class EventFileWriter extends Thread {

    private final BlockingQueue<String> queue = new LinkedBlockingQueue<>();
    private FileWriter fileWriter;
    private File file;
    private boolean isFirst = true;

    private volatile boolean isOpen = false;

    public EventFileWriter(final File file) {
        this.file = file;
        openFile();
        isOpen = true;
    }


    private void openFile() {
        try {
            fileWriter = new FileWriter(file);
            fileWriter.write("[");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void write(final Event event) {
        try {
            queue.put(event.toJSON());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void run() {
        while (isOpen) {

            String s;

            try {
                s = queue.poll(1L, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            try {
                if (s != null) {
                    if (!isFirst) {
                        fileWriter.write(",");
                    } else {
                        isFirst = false;
                    }

                    fileWriter.write("\n"+s);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        closeFile();

    }


    private void closeFile() {
        try {
            fileWriter.write("\n]");
            fileWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                fileWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}
