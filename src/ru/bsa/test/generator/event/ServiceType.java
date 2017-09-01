package ru.bsa.test.generator.event;

public enum ServiceType {
    NEW_ACCOUNT("new account"), PAYMENT("payment"), DELIVERY("delivery");

    private String title;

    ServiceType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
