package com.utn.CapitalConnection.utils;

import com.utn.CapitalConnection.entity.UserEntity;
import com.utn.CapitalConnection.model.User;
import org.springframework.stereotype.Component;

@Component
public class Utilities {
    public String formatText(String text) {
        return text.trim().toUpperCase();
    }

}
