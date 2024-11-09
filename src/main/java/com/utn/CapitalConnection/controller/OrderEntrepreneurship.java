package com.utn.CapitalConnection.controller;

public enum OrderEntrepreneurship {
    ID("id"),
    NAME("name"),
    STARS("stars"),
    GOAL("goal");

    private final String property;

    OrderEntrepreneurship(String property) {
        this.property = property;
    }

    public String getProperty() {
        return property;
    }

    public static OrderEntrepreneurship fromString(String text) {
        for (OrderEntrepreneurship order : OrderEntrepreneurship.values()) {
            if (order.property.equalsIgnoreCase(text)) {
                return order;
            }
        }
        throw new IllegalArgumentException("No enum constant for text " + text);
    }
}