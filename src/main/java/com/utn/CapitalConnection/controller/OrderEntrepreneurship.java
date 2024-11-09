package com.utn.CapitalConnection.controller;
import com.utn.CapitalConnection.model.Entrepreneurship;
import java.util.Comparator;
public enum OrderEntrepreneurship {
    ID, NOMBRE, META, ESTRELLAS;
    public Comparator<Entrepreneurship> getComparator() { return switch (this) { case ID -> Comparator.comparing(Entrepreneurship::getId); case NOMBRE -> Comparator.comparing(Entrepreneurship::getName); case META -> Comparator.comparing(Entrepreneurship::getGoal); case ESTRELLAS -> Comparator.comparingDouble(Entrepreneurship::getAverageStars); }; } public String getProperty() { return switch (this) { case ID -> "id"; case NOMBRE -> "name"; case META -> "goal"; case ESTRELLAS -> "averageStars"; }; } }

