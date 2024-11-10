package com.utn.CapitalConnection.model;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Entrepreneurship {

    private Long id;
    private String id_user;
private String name;
private List<String> images;
private String description;
private List<String> videos;
private BigDecimal goal;
private String category;

    public Entrepreneurship() {
    }

    public Entrepreneurship(String id_user,String name, String description, BigDecimal goal, String category) {
        this.id_user = id_user;
        this.name = name;
        this.images = new ArrayList<>();
        this.description = description;
        this.videos = new ArrayList<>();
        this.goal = goal;
        this.category = category;
    }

    public Entrepreneurship(Long id,String id_user, String name, String description, BigDecimal goal, String category) {
        this.id_user = id_user;
        this.id = id;
        this.name = name;
        this.images = new ArrayList<>();
        this.description = description;
        this.videos = new ArrayList<>();
        this.goal = goal;
        this.category = category;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getVideos() {
        return videos;
    }

    public void setVideos(List<String> videos) {
        this.videos = videos;
    }

    public BigDecimal getGoal() {
        return goal;
    }

    public void setGoal(BigDecimal goal) {
        this.goal = goal;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entrepreneurship that = (Entrepreneurship) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(images, that.images) && Objects.equals(description, that.description) && Objects.equals(videos, that.videos) && Objects.equals(goal, that.goal) && category == that.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, images, description, videos, goal, category);
    }
}
