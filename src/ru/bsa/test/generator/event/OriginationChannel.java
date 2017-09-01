package ru.bsa.test.generator.event;

public enum OriginationChannel {

    WEBCHAT("webchat"), SMS("sms"), WECHAT("wechat");

    private String title;

    OriginationChannel(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

}
