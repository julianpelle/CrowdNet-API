package com.utn.CapitalConnection.model;

import java.sql.Timestamp;
import java.util.Objects;

public class Notification {

    private Long id;

    private String message;

    private Timestamp dateTimeStamp;

    public Notification() {
    }

    public Notification(String message, Timestamp dateTimeStamp) {
        this.message = message;
        this.dateTimeStamp = dateTimeStamp;
    }

    public Notification(Long id, String message, Timestamp dateTimeStamp) {
        this.id = id;
        this.message = message;
        this.dateTimeStamp = dateTimeStamp;
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
        this.message = message;
    }

    public Timestamp getDateTimeStamp() {
        return dateTimeStamp;
    }

    public void setDateTimeStamp(Timestamp dateTimeStamp) {
        this.dateTimeStamp = dateTimeStamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notification that = (Notification) o;
        return Objects.equals(id, that.id) && Objects.equals(message, that.message) && Objects.equals(dateTimeStamp, that.dateTimeStamp);
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
