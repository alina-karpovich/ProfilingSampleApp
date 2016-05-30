package org.profilingsampleapp.model;

import java.util.ArrayList;
import java.util.List;

public class TestObject {
    private static final int LIST_LENGTH = 1000;
    private static final String STORED_NUMBER_MESSAGE = "Stored number is ";
    private Integer number;
    private String string;
    private List<String> list;

    public TestObject(final int number) {
        this.number = number;
        this.string = STORED_NUMBER_MESSAGE + Integer.toString(number);
        list = new ArrayList<>();
        for (int i = 0; i < LIST_LENGTH; i++) {
            list.add(string + Integer.toString(i));
        }
    }

    public int getNumber() {
        return number;
    }

    public String getString() {
        return string;
    }

    public List<String> getList() {
        return list;
    }
}
