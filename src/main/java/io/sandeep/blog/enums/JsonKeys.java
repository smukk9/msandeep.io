package io.sandeep.blog.enums;

public enum JsonKeys {
    TAGNAME("tagnames"),
    TAGS("tags"),
    TITLE("title"),
    CONTENT("content"),
    YEAR("year");




    private String value;
    public String getValue() {
        return value;
    }
    JsonKeys(String value) {
        this.value = value;
    }
    }
