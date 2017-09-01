package ru.bsa.test.generator.event;

public enum OriginationPage {

    LOGIN("login"), BALANCE("balance"), TRANSFER("transfer");

    private String title;

    OriginationPage(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

}
