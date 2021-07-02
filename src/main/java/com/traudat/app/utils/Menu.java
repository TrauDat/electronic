package com.traudat.app.utils;

public enum Menu {
    Home("Elic Dashboard"),
    Elic("Tiny Elic"),
    Sales("Sales History"),
    Category("Category Management"),
    Product("Product Management");

    private String title;

    Menu(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getFxml() {
        return String.format("%s.fxml", name());
    }
}
