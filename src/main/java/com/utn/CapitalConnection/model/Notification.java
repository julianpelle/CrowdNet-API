package com.utn.CapitalConnection.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Objects;

public class Notification {

    private Long id;

    @NotBlank(message = "Message cannot be blank")
    private String message;

    @NotNull(message = "Timestamp is required")
    private Timestamp dateTimeStamp;

    public Notification() {
    }

    public Notification(String message, Timestamp dateTimeStamp) {
        try {
            setMessage(message);
            setDateTimeStamp(dateTimeStamp);
        } catch (IllegalArgumentException e) {
            System.out.println("Error during initialization: " + e.getMessage());
        }
    }

    public Notification(Long id, String message, Timestamp dateTimeStamp) {
        this.id = id;
        try {
            setMessage(message);
            setDateTimeStamp(dateTimeStamp);
        } catch (IllegalArgumentException e) {
            System.out.println("Error during initialization: " + e.getMessage());
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        try {
            if (message == null || message.trim().isEmpty()) {
                throw new IllegalArgumentException("Message cannot be null or blank");
            }
            this.message = message;
        } catch (IllegalArgumentException e) {
            System.out.println("Error setting message: " + e.getMessage());
        }
    }

    public Timestamp getDateTimeStamp() {
        return dateTimeStamp;
    }

    public void setDateTimeStamp(Timestamp dateTimeStamp) {
        try {
            if (dateTimeStamp == null) {
                throw new IllegalArgumentException("Timestamp is required");
            }
            this.dateTimeStamp = dateTimeStamp;
        } catch (IllegalArgumentException e) {
            System.out.println("Error setting timestamp: " + e.getMessage());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notification that = (Notification) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(message, that.message) &&
                Objects.equals(dateTimeStamp, that.dateTimeStamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, message, dateTimeStamp);
    }

    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", dateTimeStamp=" + dateTimeStamp +
                '}';
    }
}
