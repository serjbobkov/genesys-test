package ru.bsa.test.generator.event;

public enum EventType {
    START("start"), JOIN("join"), END("end");

    private String title;

    EventType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
