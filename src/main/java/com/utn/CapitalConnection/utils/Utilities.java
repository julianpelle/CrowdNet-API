package com.utn.CapitalConnection.utils;


import org.springframework.stereotype.Component;

@Component
public class Utilities {
    public String formatText(String text) {
        return text.trim().toUpperCase();
    }

}
