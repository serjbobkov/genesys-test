package ru.bsa.test;

import ru.bsa.test.generator.EventGenerator;
import ru.bsa.test.generator.exception.EventGeneratorException;

import java.io.File;

public class Main {

    public static void main(String[] args) {

        int count = 1000;
        int frequence = 10;

        if (args == null || args.length != 2) {
            System.out.println("bad params. use default: count = 1000  frequence = 10");
            System.out.println("use: $ java Main [COUNT] [FREQUENCE]");
            System.out.println("example: $ java Main 1000 10");

        } else {
            count = Integer.parseInt(args[0]);
            frequence = Integer.parseInt(args[1]);

            System.out.println("use: count = " + count + " frequence = " + frequence);
        }

        System.out.println("output file ./test.json");

        System.out.println("System started. Please, wait.");

        EventGenerator eventGenerator = new EventGenerator(count, frequence, new File("./test.json"));

        try {
            eventGenerator.generate();
        } catch (EventGeneratorException e) {
            System.out.println("Exception occured: " + e.getMessage());
            System.out.println("System finished with error");
        }

        System.out.println("System finished OK");

    }
}
