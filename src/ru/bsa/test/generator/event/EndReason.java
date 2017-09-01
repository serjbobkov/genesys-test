package ru.bsa.test.generator.event;

public enum EndReason {
    NORMAL("Normal"), ABNORMAL("Abnormal");

    private String title;

    EndReason(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
