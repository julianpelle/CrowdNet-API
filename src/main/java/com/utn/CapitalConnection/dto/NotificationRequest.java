package com.utn.CapitalConnection.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.sql.Timestamp;

@Schema(description = "Payload for Notification information")
public class NotificationRequest {

    @Schema(description = "ID of the notification", example = "1")
    private Long id;

    @Schema(description = "Message of the notification", example = "Your project has received new funds.")
    private String message;

    @Schema(description = "Timestamp of the notification", example = "2023-10-15 12:30:45")
    private Timestamp dateTimeStamp;

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public Timestamp getDateTimeStamp() {
        return dateTimeStamp;
    }
}