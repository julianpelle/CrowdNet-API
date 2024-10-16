package com.utn.CapitalConnection.dto;

import com.utn.CapitalConnection.types.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;

@Schema(description = "Payload for Entrepreneurship information")
public class EntrepreneurshipRequest {

    @Schema(description = "ID of the entrepreneurship", example = "1")
    private Long id;

    @Schema(description = "Name of the entrepreneurship", example = "Eco Solutions")
    private String name;

    @Schema(description = "Pictures of the entrepreneurship saved on Links")
    private ArrayList<String> pictures;

    @Schema(description = "Description of the entrepreneurship", example = "A project focused on eco-friendly solutions.")
    private String description;

    @Schema(description = "Videos of the entrepreneurship saved on Links")
    private ArrayList<String> videos;

    @Schema(description = "Funding goal of the entrepreneurship", example = "10000.0")
    private float goal;

    @Schema(description = "Category of the entrepreneurship", example = "Technology")
    private Category category;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getPictures() {
        return pictures;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<String> getVideos() {
        return videos;
    }

    public float getGoal() {
        return goal;
    }

    public Category getCategory() {
        return category;
    }

}
