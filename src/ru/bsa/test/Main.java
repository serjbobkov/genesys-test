package ru.bsa.test;

import ru.bsa.test.generator.EventGenerator;

import java.io.File;

public class Main {

    public static void main(String[] args) {

        int count = 1000;
        int frequence = 10;

        if(args == null || args.length!=2) {
            System.out.println("bad params. use default: count = 1000  frequence = 10");
            System.out.println("use: $ java Main [COUNT] [FREQUENCE]");
            System.out.println("example: $ java Main 1000 10");

        } else {
            count = Integer.parseInt(args[0]);
            frequence = Integer.parseInt(args[1]);

            System.out.println("use: count = " + count +" frequence = "+ frequence );
        }

        System.out.println("output file ./test.json");

        System.out.println("System started");

        EventGenerator eventGenerator = new EventGenerator(count, frequence, new File("./test.json"));
        eventGenerator.generate();

        System.out.println("System finished");
    }
}
