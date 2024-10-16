package com.utn.CapitalConnection.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "Payload for Donation information")
public class DonationRequest {
    @Schema(description = "ID of the donation", example = "1")
    private Long id;

    @Schema(description = "Donation amount", example = "100.50")
    private BigDecimal amount;

    @Schema(description = "Date of the donation", example = "2024-10-15T10:15:30")
    private LocalDateTime date;

    @Schema(description = "ID of the related entrepreneurship", example = "5")
    private Long idEntrepreneurship;

    public Long getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Long getIdEntrepreneurship() {
        return idEntrepreneurship;
    }
}
