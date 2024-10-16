package com.utn.CapitalConnection.dto;

import com.utn.CapitalConnection.model.Entrepreneur;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Schema(description = "Payload for Entrepreneur information")
public class EntrepreneurRequest extends UserRequest {

    @Schema(description = "Success rate of the entrepreneur", example = "85.5")
    private float successRate;

    @Schema(description = "CBU of the entrepreneur", example = "1234567890123456789012")
    private String cbu;

    @Schema(description = "List of entrepreneurships associated with the entrepreneur")
    private List<EntrepreneurshipRequest> entrepreneurshipList;

    public float getSuccessRate() {
        return successRate;
    }

    public String getCbu() {
        return cbu;
    }

    public List<EntrepreneurshipRequest> getEntrepreneurshipList() {
        return entrepreneurshipList;
    }
}
