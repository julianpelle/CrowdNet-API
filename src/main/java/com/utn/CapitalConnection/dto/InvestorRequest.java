package com.utn.CapitalConnection.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;

@Schema(description = "Payload for Investor information")
public class InvestorRequest extends UserRequest {

    @Schema(description = "Portfolio value of the investor", example = "25000.75")
    private BigDecimal portfolioValue;

    public BigDecimal getPortfolioValue() {
        return portfolioValue;
    }
}